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

public class MathClass {

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
			sData = expool.submit(new CalcPrimes(100, false));
			System.out.println("Data: " + sData.get());
			Thread.sleep(1000);
			expool.shutdown();
		}catch(Exception ex){
			System.out.println("Error:MathClass.main: "+ex.getMessage());
		}
		
		System.out.println("Stop");
	}

}
