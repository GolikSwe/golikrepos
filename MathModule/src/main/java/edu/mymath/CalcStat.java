package edu.mymath;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.concurrent.Callable;

import org.apache.commons.math3.stat.descriptive.DescriptiveStatistics;
/**
 * CalcStat
 * Simple statistics module
 * @author goran
 *
 */
public class CalcStat implements Callable<String> {
	
	private String sCallValueBack;
	private String sSelectStat;
	private double dValue = 0;
	private String sTypeOfStat;
	
	ArrayList<Integer> iCalcValues = new ArrayList<>();
	
	/**
	 * constructor
	 * @param sSelectCalcMethod
	 * @param iList
	 */
	public CalcStat(String sSelectCalcMethod, ArrayList<Integer> iList){
		this.sSelectStat = sSelectCalcMethod;
		this.iCalcValues.addAll(iList);
	}
	
	/**
	 * call
	 */
	public String call() throws Exception{
		
		return "";
	}

	/**
	 * doStat
	 * @param sInput
	 * @return
	 */
	private String doStat(String sInput){
		DescriptiveStatistics stat = new DescriptiveStatistics();
		try{
			Iterator<Integer> itr =  iCalcValues.iterator();
			while (itr.hasNext()) {	
				stat.addValue(iCalcValues.get(itr.next()));
			}
			
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
					sTypeOfStat ="USE: mean, geomean or vraiance";
				break;
				}			
			}//end switch
			if(true == sTypeOfStat.contains("USE:")){
				return sTypeOfStat;
			} else{
				return sTypeOfStat +" : "+ Double.toString(dValue);
			}
		}catch(Exception ex){
			
			return "ERROR";
		}	
	}
}
