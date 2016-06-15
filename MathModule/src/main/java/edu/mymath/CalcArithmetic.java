package edu.mymath;

import java.util.concurrent.Callable;
import org.apache.log4j.Logger;
import org.json.simple.JSONArray;
import org.json.simple.JSONObject;

/**
 * CalcArithmetic
 * @author Goran Lindqvist
 *
 */
public class CalcArithmetic extends Common implements Callable<String> {

	private int iNr1;
	private int iNr2;
	private String sOpr;
	private Boolean bWrite;
	private String sPath = "/home/goran/testing/logs/test2.txt";
		
	final static Logger log = Logger.getLogger(edu.mymath.CalcArithmetic.class); 
	
	/**
	 * constructor
	 * @param t1, int.
	 * @param t2, int.
	 * @param opr, add, sub, mult or div.
	 * @param writeToFile, boolean.
	 */
	public CalcArithmetic(int t1, int t2, String opr, Boolean writeToFile){
		this.iNr1 = t1;
		this.iNr2 = t2;
		this.sOpr = opr;
		this.bWrite = writeToFile;
	}
	
	/**
	 * call
	 */
	public String call() {
		return simpleCalc(sOpr);
	}
	
	/**
	 * simpleCalc.
	 * @param sKey, add, sub, mult or div.
	 * @return String.
	 */
	@SuppressWarnings("unchecked")
	private String simpleCalc(String sKey){
		log.info("CalcArithmetic.simpleCalc:start");
		double dRes = 0;
		String sMathOpr = "";
		//instans
		JSONObject jObj = new JSONObject();
		JSONArray jList = new JSONArray();		
		try{
			switch (sKey) {
			case "add":{
				sMathOpr = "addition";
				dRes = iNr1+iNr2;
				jList.add(dRes);
				break;
			}
			case "sub":{
				sMathOpr = "subtraction";
				dRes = iNr1-iNr2;
				jList.add(dRes);
				break;
			}
			case "mult":{
				sMathOpr = "multiplication";
				dRes = iNr1*iNr2;
				jList.add(dRes);
				break;
			}
			case "div":{
				if(0 == iNr2){
					log.error("You cant divide with zero");
					sMathOpr = "Warning";
					jList.add("you cant divide with 0");
				}else{
					sMathOpr = "division";
					//need to typecast int => double
					double dNr1 = (double) iNr1;
					double dNr2 = (double) iNr2; 
					dRes = dNr1/dNr2;
					jList.add(dRes);
				}
				break;
			}
			default:
				log.error("Choise add, sub, mult or div, not: " +sKey);
				System.out.println("");
				sMathOpr ="ERROR";
				jList.add("choise add, sub, mult or div"); 
				break;
			}
			jObj.put(sMathOpr, jList);
			//write to file
			if(true == bWrite){
				StringBuffer sBuf = new StringBuffer();
				sBuf.append("********************************").append("\n");
				sBuf.append("********:" +sMathOpr+ ":********").append("\n");
				sBuf.append(jObj.toJSONString());
				writeDownToFile(sBuf,sPath);
				log.info("CalcArithmetic.simpleCalc:write to file");					
			}			
			log.info("CalcArithmetic.simpleCalc: " +"opr "+ sKey +" :value " +dRes);
		}catch(Exception ex){
			System.err.println("ERROR:CalcArithmetic.simpleCalc: " +ex.toString());
			log.error("ERROR:CalcArithmetic.simpleCalc: " +ex.toString());
			jList.add("ERROR:CalcArithmetic.simpleCalc: " +ex.toString());
			jObj.put("ERROR", jList);
			return jObj.toJSONString();
		}
		log.info("CalcArithmetic.simpleCalc:stop");
		return jObj.toJSONString();
	}
}
