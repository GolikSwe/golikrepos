package edu.test;

import static org.junit.Assert.*;

import java.util.concurrent.ExecutionException;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.Future;
import java.util.concurrent.TimeUnit;
import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;
import org.json.simple.parser.ParseException;
import org.junit.Test;
import edu.mymath.CalcPrimes;

public class TestCalcPrimes {

	/**
	 * test1
	 * @throws InterruptedException
	 * @throws ExecutionException
	 * @throws ParseException
	 */
	@Test
	public void test1() throws InterruptedException, ExecutionException, ParseException {
		String sTestValue = "This is a prime: 11";
		final ExecutorService expool;
		final Future<String> sData;
		//CalcStat csTest = new CalcStat("mean", iList);
		expool = Executors.newFixedThreadPool(2);
		sData = expool.submit(new CalcPrimes(11, false));
		String jString = sData.get();
		JSONParser jParser =  new JSONParser();
		Object obj = jParser.parse(jString);
		JSONObject jObj = (JSONObject) obj;
		String outPut = jObj.get("Prime").toString();
		System.out.println("1: "+outPut);
		assertEquals(sTestValue, outPut);
		expool.awaitTermination(2, TimeUnit.SECONDS);
		expool.shutdown();				
	}
	
	/**
	 * test2
	 * @throws InterruptedException
	 * @throws ParseException
	 * @throws ExecutionException
	 */
	@Test
	public void test2() throws InterruptedException, ParseException, ExecutionException{
		String sTestValue = "Write primes to file... succeeded";
		final ExecutorService expool;
		final Future<String> sData;
		//CalcStat csTest = new CalcStat("mean", iList);
		expool = Executors.newFixedThreadPool(2);
		sData = expool.submit(new CalcPrimes(10, true));
		String jString = sData.get();
		JSONParser jParser =  new JSONParser();
		Object obj = jParser.parse(jString);
		JSONObject jObj = (JSONObject) obj;
		String outPut = jObj.get("Primes").toString();
		System.out.println("1: "+outPut);
		assertEquals(sTestValue, outPut);
		expool.awaitTermination(2, TimeUnit.SECONDS);
		expool.shutdown();				
	}

}
