package edu.mymath;

import java.util.concurrent.Callable;
import org.apache.log4j.Logger;
import org.json.simple.JSONArray;
import org.json.simple.JSONObject;
import org.apache.commons.math3.primes.Primes;

/**
 * CalcPrimes
 * @author Goran Lindqvist
 *
 */
public class CalcPrimes extends Common implements Callable<String> {

	private final int iThisPrimenr;
	private boolean bCalcSerieOrNr = false;
	private String sPath = "/home/goran/testing/logs/test.txt";
	
	//instans
	JSONObject jObj = new JSONObject();
	JSONArray jList = new JSONArray();
	
	//logger
	final static Logger log = Logger.getLogger(edu.mymath.CalcPrimes.class);
	
	/**
	 * constructor 1
	 * @param iNr, int.
	 * @param bSerieOrTestNr, boolean.
	 */
	public CalcPrimes(int iNr, boolean bSerieOrTestNr){
		this.iThisPrimenr = iNr;
		this.bCalcSerieOrNr = bSerieOrTestNr;
		log.info("CalcPrimes:constructor: " +Integer.toString(iNr) +" : "+ bSerieOrTestNr);
	}
	
	/**
	 * constructor 2
	 * @param iNr, int.
	 * @param bWrite, boolean.
	 * @param bSerieOrNr, boolean.
	 * @param sFullPathToTxt, string.
	 */
	public CalcPrimes(int iNr, boolean bWrite, boolean bSerieOrNr, String sFullPathToTxt) {
		this.iThisPrimenr = iNr;
		this.bCalcSerieOrNr = bSerieOrNr;
		this.sPath = sFullPathToTxt;
	}
	
	/**
	 * Call
	 */
	@Override
	public String call(){
		log.info("CalcPrimes.call:start");
		if(true ==  bCalcSerieOrNr){			
			log.info("CalcPrimes.call:stop");
			return calcSeriesOfPrime();
		}else{
			log.info("CalcPrimes.call:stop");
			return isThisPrime();
		}
	}

	/**
	 * isThisPrime
	 * @return String
	 */
	@SuppressWarnings("unchecked")
	private String isThisPrime(){
		log.info("CalcPrimes.isThisPrime:start");
		try{
			String sendBack;
			boolean bol = Primes.isPrime(iThisPrimenr);
			if(false == bol){
				jList.add("This i not a prime: " +iThisPrimenr);
				jList.add("Nearest prime is: " + Primes.nextPrime(iThisPrimenr));
				jObj.put("Prime", jList);
				sendBack = jObj.toJSONString();
				log.info("CalcPrimes.isThisPrime:Data: " +sendBack);
			}else{
				jObj.put("Prime", "This is a prime: " +iThisPrimenr);
				sendBack = jObj.toJSONString();
				log.info("CalcPrimes.isThisPrime:Data: " +sendBack);
			}
			log.info("CalcPrimes.isThisPrime:stop");
			return sendBack;
		}catch(Exception ex){
			System.out.println("ERROR:CalcPrimes.isThisPrime: " +ex.toString());
			log.info("ERROR:CalcPrimes.isThisPrime: " +ex.toString());
			return "ERROR";
		}
	}
	
	/**
	 * calcSeriesOfPrime
	 * @return, string.
	 */
	@SuppressWarnings("unchecked")
	private String calcSeriesOfPrime(){
		log.info("CalcPrimes.calcSeriesOfPrime:start");
		try{
			int j = 0;
			StringBuffer sBuf = new StringBuffer();
			for(int i = 0; i<iThisPrimenr ; i++){
				j= j+1;
				if(true == Primes.isPrime(i)){
					sBuf.append("This is A prime: ").append(i).append("\n");
				}else{
					sBuf.append("This is NOT a prime: ").append(i).append("\n");
				}	
			}//end for
			log.info("CalcPrimes.isThisPrime: nr of count: " +j);
			//write
			boolean bol = writeDownToFile(sBuf,sPath);
			if(true == bol){
				log.info("CalcPrimes.calcSeriesOfPrime:stop");
				jObj.put("Primes", "Write primes to file... succeeded");
				return jObj.toJSONString();
			}else{
				log.info("CalcPrimes.calcSeriesOfPrime:stop");
				jObj.put("Primes", "Write primes to file... NOT succeeded");
				return jObj.toJSONString();
			}
			
		}catch(Exception ex){
			System.out.println("ERROR:CalcPrimes.isThisPrime: " +ex.toString());
			log.error("ERROR:CalcPrimes.isThisPrime: " +ex.toString());
			return "";
		}
	}
}
