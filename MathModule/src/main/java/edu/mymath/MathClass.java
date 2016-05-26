/**
 * This is just for test
 * working code
 */
package edu.mymath;

import java.awt.List;
import java.util.ArrayList;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.Future;
import org.apache.log4j.Logger;

public class MathClass {

	final static Logger log  = Logger.getLogger(edu.mymath.MathClass.class);
	ArrayList<Integer> dDataList = new ArrayList<>();
	
	/**
	 * constructor
	 * @param iNr1
	 * @param iNr2
	 * @param iNr3
	 * @param iNr4
	 * @param iNr5
	 * @param iNr6
	 */
	public void setDataList(int iNr1,int iNr2,int iNr3,int iNr4,int iNr5,int iNr6){
		dDataList.add(iNr1);
		dDataList.add(iNr2);
		dDataList.add(iNr3);
		dDataList.add(iNr4);
		dDataList.add(iNr5);
		dDataList.add(iNr5);
	}
	
	/**
	 * main
	 * @param args
	 */
	public static void main(String[] args) {
		System.out.println("Start");
		log.info("MathClass.main:start");
		try{
			final ExecutorService expool;
			final Future<String> sData;
			ArrayList<Integer> dDList = new ArrayList<>();
			dDList.add(2);
			dDList.add(3);
			dDList.add(1);
			dDList.add(1);
			dDList.add(7);
			dDList.add(3);
			expool = Executors.newFixedThreadPool(5);
			log.info("MathClass.main:create thread pool");
			sData = expool.submit(new CalcPrimes(100, true));
			log.info("MathClass.main:call class");
			System.out.println("Data: " + sData.get());
			Thread.sleep(1000);
			expool.shutdown();
			log.info("MathClass.main:close");
		}catch(Exception ex){
			log.info("ERROR MathClass:main: " + ex.getMessage());
			System.out.println("Error:MathClass.main: "+ex.getMessage());
		}
		
		System.out.println("Stop");
	}

}
