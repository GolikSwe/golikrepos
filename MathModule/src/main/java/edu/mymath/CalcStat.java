package edu.mymath;

import java.util.ArrayList;
import java.util.concurrent.Callable;
import org.apache.log4j.Logger;
import org.json.simple.JSONArray;
import org.json.simple.JSONObject;
import org.apache.commons.math3.stat.descriptive.DescriptiveStatistics;

/**
 * CalcStat
 * Simple statistics module
 * @author Goran Lindqvist
 *
 */
public class CalcStat extends Common implements Callable<String> {
	
//	private String sCallValueBack;
	private String sSelectStat;
	private Boolean bWrite;
	private double dValue = 0;
	private String sTypeOfStat;
	private String sPath = "/home/goran/testing/logs/test1.txt";
	ArrayList<Integer> iCalcValues = new ArrayList<>();
	//instans
	DescriptiveStatistics stat = new DescriptiveStatistics();	
	final static Logger log = Logger.getLogger(edu.mymath.CalcStat.class);
	
	/**
	 * constructor
	 * @param sSelectCalcMethod, string.
	 * @param iList, arraylist.
	 * @param writeToFile, booolean.
	 */
	public CalcStat(String sSelectCalcMethod, ArrayList<Integer> iList, Boolean writeToFile){
		this.sSelectStat = sSelectCalcMethod;
		this.iCalcValues = iList;
		this.bWrite = writeToFile;
		int i=0;
		while (i< iList.size()) {	
			stat.addValue(iCalcValues.get(i));
			i++;
		}		
		
		this.iCalcValues.addAll(iList);
	}
	
	/**
	 * call
	 */
	public String call() throws Exception{
		log.info("CalcStat.call:start");
		return doStat(sSelectStat);
	}

	/**
	 * doStat
	 * @param sInput, string.
	 * @return string.
	 */
	@SuppressWarnings("unchecked")
	private String doStat(String sInput){
		log.info("CalcStat.doStat:start");
		JSONObject jObj = new JSONObject();
		JSONArray jList = new JSONArray();
		try{			
			switch(sInput){
				case "mean":{
					dValue = stat.getMean();
					sTypeOfStat = "mean";
				break;
				}
				case "geomean":{
					dValue = stat.getGeometricMean();
					sTypeOfStat = "geometric mean";
				break;
				}
				case "variance":{
					dValue = stat.getVariance();
					sTypeOfStat = "variance";
				break;
				}
				default:{
					sTypeOfStat ="USE: mean, geomean or variance";
				break;
				}			
			}//end switch
			log.info("CalcStat.doValue: " + sTypeOfStat +" : "+ dValue);
			if(true == sTypeOfStat.contains("USE:")){
				log.info("CalcStat.doStat:stop");
				jList.add(sTypeOfStat);
				jObj.put("Error", sTypeOfStat);
				return jObj.toJSONString();
			} else{
				jList.add(dValue);
				jObj.put(sTypeOfStat, dValue);
				if(true == bWrite){
					StringBuffer sBuf = new StringBuffer();
					sBuf.append("********************************").append("\n");
					sBuf.append("********:" +sTypeOfStat+ ":********").append("\n");
					sBuf.append(jObj.toJSONString());
					writeDownToFile(sBuf,sPath);
					log.info("CalcStat.doStat:write to file");	
				}
				log.info("CalcStat.doStat:stop");
				return jObj.toJSONString();
				//return sTypeOfStat +" : "+ Double.toString(dValue);
			}
		}catch(Exception ex){
			System.out.println("ERROR:CalcStat.doStat: " +ex.toString());
			log.error("ERROR:CalcStat.doStat: " +ex.toString());
			return "ERROR";
		}	
	}
}
