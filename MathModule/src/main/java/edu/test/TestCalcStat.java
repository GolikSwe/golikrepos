package edu.test;

import static org.junit.Assert.*;
import java.util.ArrayList;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.Future;
import java.util.concurrent.TimeUnit;
import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;
import org.json.simple.parser.ParseException;
import org.junit.Test;

import edu.mymath.CalcStat;

/**
 * TestCalcStat
 * @author Goran Lindqvist
 *
 */
public class TestCalcStat {

	/**
	 * test1
	 * @throws ParseException
	 * @throws InterruptedException
	 * @throws ExecutionException
	 */
	@Test
	public void test1() throws ParseException, InterruptedException, ExecutionException {	
		String sTestValue = "20.5";
		final ExecutorService expool;
		final Future<String> sData;
		ArrayList<Integer> iList = new ArrayList<>();
		iList.add(12);
		iList.add(21);
		iList.add(34);
		iList.add(12);
		iList.add(20);
		iList.add(24);
		//CalcStat csTest = new CalcStat("mean", iList);
		expool = Executors.newFixedThreadPool(2);
		sData = expool.submit(new CalcStat("mean",iList));
		String jString = sData.get();
		JSONParser jParser =  new JSONParser();
		Object obj = jParser.parse(jString);
		JSONObject jObj = (JSONObject) obj;
		String outPut = jObj.get("mean").toString();
		System.out.println("1: "+outPut);
		assertEquals(sTestValue, outPut);
		expool.awaitTermination(2, TimeUnit.SECONDS);
		expool.shutdown();			
	}
	
	/**
	 * test2
	 * @throws ParseException
	 * @throws InterruptedException
	 * @throws ExecutionException
	 */
	@Test
	public void test2() throws ParseException, InterruptedException, ExecutionException{
		String sTestValue = "11.533963316506613";
		final ExecutorService expool;
		final Future<String> sData;
		ArrayList<Integer> iList = new ArrayList<>();
		iList.add(12);
		iList.add(13);
		iList.add(11);
		iList.add(14);
		iList.add(7);
		iList.add(14);
		//CalcStat csTest = new CalcStat("mean", iList);
		expool = Executors.newFixedThreadPool(2);
		sData = expool.submit(new CalcStat("geomean",iList));
		String jString = sData.get();
		JSONParser jParser =  new JSONParser();
		Object obj = jParser.parse(jString);
		JSONObject jObj = (JSONObject) obj;
		String outPut = jObj.get("geometric mean").toString();
		System.out.println("2: "+outPut);
	assertEquals(sTestValue, outPut);
		expool.awaitTermination(2, TimeUnit.SECONDS);
		expool.shutdown();		
	}
}
