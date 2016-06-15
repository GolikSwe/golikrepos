/**
 * This is just for test
 * working code
 */
package edu.mymath;

import java.util.ArrayList;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.Future;
import java.util.concurrent.TimeUnit;
import org.apache.log4j.Logger;

/**
 * MathClass
 * @author Goran Lindqvist
 */
public class MathClass {

	final static Logger log  = Logger.getLogger(edu.mymath.MathClass.class);
	final static ExecutorService expool = Executors.newFixedThreadPool(2);	
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
	 * @see Just for test. 
	 * @param args.
	 */
	public static void main(String[] args) {
		System.out.println("Start");
		log.info("MathClass.main:start");
		ArrayList<Integer> iMyList = new ArrayList<>();
		iMyList.add(12);
		iMyList.add(15);
		iMyList.add(11);
		iMyList.add(14);
		iMyList.add(7);
		iMyList.add(16);
		iMyList.add(9);
		iMyList.add(10);
		iMyList.add(8);
		try{
			System.out.println(runMe1("writeprime", 1500, 0));
			System.out.println(runMe2("mean",iMyList));
			System.out.println(runMe1("findprime", 2336,0));
			System.out.println(runMe2("geomean",iMyList));
			System.out.println(runMe1("mult", 3, 9));
			System.out.println(runMe1("add", 3, 9));
			System.out.println(runMe1("div", 3, 9));
			System.out.println(runMe1("sub", 3, 9));
			expool.awaitTermination(3, TimeUnit.SECONDS);
			expool.shutdown();			
			log.info("MathClass.main:close");
		}catch(Exception ex){
			log.info("ERROR MathClass:main: " + ex.getMessage());
			System.out.println("Error:MathClass.main: "+ex.getMessage());
		}
		System.out.println("Stop");
	}

	/**
	 * runMe1
	 * @param sSelectMath, (add, sub, mult, div, findprime, writeprime).
	 * @param t1, int.
	 * @param t2, int.
	 * @return string.
	 */
	private static String runMe1(String sSelectMath, int t1, int t2){
		log.info("MathClass.runMe1:start");
		try{
			Future<String>sData = null;
			switch (sSelectMath) {
			case "arit": {
				break;
			}
			case "findprime":{
				sData = expool.submit(new CalcPrimes(t1, false));
				break;
			}
			case "writeprime":{
				sData = expool.submit(new CalcPrimes(t1, true));
				break;
			}
			case "mult":{
				sData = expool.submit(new CalcArithmetic(t1, t2, "mult", false));				
				break;
			}
			case "add":{
				sData = expool.submit(new CalcArithmetic(t1, t2, "add", false));
				break;
			}
			case "sub":{
				sData = expool.submit(new CalcArithmetic(t1, t2, "sub", false));
				break;
			}
			case "div":{
				sData = expool.submit(new CalcArithmetic(t1, t2, "div", true));
				break;
			}			
			default:
				sData = null;
				break;
			}
			log.info("MathClass.runMe1:call class");
			String sTemp = sData.get();
			log.info("MathClass.runMe1:close");
			return sTemp;
		}catch(Exception ex){
			log.info("ERROR MathClass:runMe1: " + ex.getMessage());
			System.out.println("Error:MathClass.runMe1: "+ex.getMessage());
			return "ERROR MathClass:runMe1: " + ex.getMessage(); 
		}
	}
	
	/**
	 * runMe2
	 * @param sSelectStat, (mean, geomean, variance).
	 * @param iList, arraylist.
	 * @return string.
	 */
	private static String runMe2(String sSelectStat, ArrayList<Integer>iList){
		
		log.info("MathClass.runMe2:start");
		try{
			Future<String>sData;
			if(sSelectStat.equals("mean")|| sSelectStat.equals("geomean")|| sSelectStat.equals("variance")){
				sData = expool.submit(new CalcStat(sSelectStat,iList, true));				
			}else{
				sData = null;
			}
			log.info("MathClass.runMe2:call class");
			log.info("MathClass.runMe2:close");
			return sData.get();
		}catch(Exception ex){
			log.info("ERROR MathClass:runMe2: " + ex.getMessage());
			System.out.println("Error:MathClass.runMe2: "+ex.getMessage());
			return "ERROR MathClass:runMe2: " + ex.getMessage(); 
		}
	}
}
