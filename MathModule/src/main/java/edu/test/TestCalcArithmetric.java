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

import edu.mymath.CalcArithmetic;

/**
 * TestCalcArithmetric
 * @author Goran Lindqvist
 *
 */
public class TestCalcArithmetric {

	/**
	 * TestCalcArithmetric:test1
	 * @throws InterruptedException
	 * @throws ExecutionException
	 * @throws ParseException
	 */
	@Test
	public void test1() throws InterruptedException, ExecutionException, ParseException {
		String sTestValue = "[9.0]";
		final ExecutorService expool;
		final Future<String> sData;
		expool = Executors.newFixedThreadPool(2);
		sData = expool.submit(new CalcArithmetic(5, 4, "add", true));
		String jString = sData.get();
		JSONParser jParser =  new JSONParser();
		Object obj = jParser.parse(jString);
		JSONObject jObj = (JSONObject) obj;
		String outPut = jObj.get("addition").toString();
		System.out.println("1_TestCalcArithmetric: add "+outPut);
		assertEquals(sTestValue, outPut);
		expool.awaitTermination(2, TimeUnit.SECONDS);
		expool.shutdown();		
	}
	
	/**
	 * TestCalcArithmetric:test2
	 * @throws InterruptedException
	 * @throws ExecutionException
	 * @throws ParseException
	 */
	@Test
	public void test2() throws InterruptedException, ExecutionException, ParseException{
		String sTestValue = "[-2.0]";
		final ExecutorService expool;
		final Future<String> sData;
		expool = Executors.newFixedThreadPool(2);
		sData = expool.submit(new CalcArithmetic(5, 7, "sub", false));
		String jString = sData.get();
		JSONParser jParser =  new JSONParser();
		Object obj = jParser.parse(jString);
		JSONObject jObj = (JSONObject) obj;
		String outPut = jObj.get("subtraction").toString();
		System.out.println("2_TestCalcArithmetric: sub "+outPut);
		assertEquals(sTestValue, outPut);
		expool.awaitTermination(2, TimeUnit.SECONDS);
		expool.shutdown();		
	}

	/**
	 * TestCalcArithmetric:test3
	 * @throws InterruptedException
	 * @throws ExecutionException
	 * @throws ParseException
	 */
	@Test
	public void test3() throws InterruptedException, ExecutionException, ParseException{
		String sTestValue = "[51.0]";
		final ExecutorService expool;
		final Future<String> sData;
		expool = Executors.newFixedThreadPool(2);
		sData = expool.submit(new CalcArithmetic(3, 17, "mult", false));
		String jString = sData.get();
		JSONParser jParser =  new JSONParser();
		Object obj = jParser.parse(jString);
		JSONObject jObj = (JSONObject) obj;
		String outPut = jObj.get("multiplication").toString();
		System.out.println("3_TestCalcArithmetric: mult "+outPut);
		assertEquals(sTestValue, outPut);
		expool.awaitTermination(2, TimeUnit.SECONDS);
		expool.shutdown();				
	}
	
	/**
	 * TestCalcArithmetric:test4
	 * @throws InterruptedException
	 * @throws ExecutionException
	 * @throws ParseException
	 */
	@Test
	public void test4() throws InterruptedException, ExecutionException, ParseException{
		String sTestValue = "[-37.5]";
		final ExecutorService expool;
		final Future<String> sData;
		expool = Executors.newFixedThreadPool(2);
		sData = expool.submit(new CalcArithmetic(450, -12, "div", false));
		String jString = sData.get();
		JSONParser jParser =  new JSONParser();
		Object obj = jParser.parse(jString);
		JSONObject jObj = (JSONObject) obj;
		String outPut = jObj.get("division").toString();
		System.out.println("4_TestCalcArithmetric: div "+outPut);
		assertEquals(sTestValue, outPut);
		expool.awaitTermination(2, TimeUnit.SECONDS);
		expool.shutdown();			
	}
	
	/**
	 * TestCalcArithmetric:test5
	 * @throws InterruptedException
	 * @throws ExecutionException
	 * @throws ParseException
	 */
	@Test
	public void test5() throws InterruptedException, ExecutionException, ParseException{
		String sTestValue = "[\"you cant divide with 0\"]";
		final ExecutorService expool;
		final Future<String> sData;
		expool = Executors.newFixedThreadPool(2);
		sData = expool.submit(new CalcArithmetic(451, 0, "div", false));
		String jString = sData.get();
		JSONParser jParser =  new JSONParser();
		Object obj = jParser.parse(jString);
		JSONObject jObj = (JSONObject) obj;
		String outPut = jObj.get("Warning").toString();
		System.out.println("5_TestCalcArithmetric: div "+outPut);
		assertEquals(sTestValue, outPut);
		expool.awaitTermination(2, TimeUnit.SECONDS);
		expool.shutdown();		
	}
}