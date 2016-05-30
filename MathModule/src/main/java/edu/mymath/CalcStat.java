package edu.mymath;

import java.util.ArrayList;
import java.util.concurrent.Callable;
import org.apache.log4j.Logger;

import org.apache.commons.math3.stat.descriptive.DescriptiveStatistics;

/**
 * CalcStat
 * Simple statistics module
 * @author goran
 *
 */
public class CalcStat implements Callable<String> {
	
//	private String sCallValueBack;
	private String sSelectStat;
	private double dValue = 0;
	private String sTypeOfStat;	
	ArrayList<Integer> iCalcValues = new ArrayList<>();
	//instans
	DescriptiveStatistics stat = new DescriptiveStatistics();	
	final static Logger log = Logger.getLogger(edu.mymath.CalcStat.class);
	
	/**
	 * constructor
	 * @param sSelectCalcMethod
	 * @param iList
	 */
	public CalcStat(String sSelectCalcMethod, ArrayList<Integer> iList){
		this.sSelectStat = sSelectCalcMethod;
		this.iCalcValues = iList;
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
	 * @param sInput, string
	 * @return string
	 */
	private String doStat(String sInput){
		log.info("CalcStat.doStat:start");
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
				return sTypeOfStat;
			} else{
				log.info("CalcStat.doStat:stop");
				return sTypeOfStat +" : "+ Double.toString(dValue);
			}
		}catch(Exception ex){
			System.out.println("ERROR:CalcStat.doStat: " +ex.toString());
			log.info("ERROR:CalcStat.doStat: " +ex.toString());
			return "ERROR";
		}	
	}
}
