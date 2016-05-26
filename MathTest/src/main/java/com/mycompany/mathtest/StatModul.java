/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.mycompany.mathtest;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.Callable;
import org.apache.commons.math3.stat.descriptive.DescriptiveStatistics;
/**
 *
 * @author goran
 */
public class StatModul implements Callable<String>{

    protected String sStatBack;
    private final String sChoise;
    private double dTemp;
    List<Integer> dStatData = new ArrayList<>();
    DescriptiveStatistics statistics = new DescriptiveStatistics();

    /**
     * constructor
     * @param sSelect
     * @param dListOfValues 
     */
    public StatModul(String sSelect, List dListOfValues) {
        this.sChoise = sSelect; 
        this.dStatData = dListOfValues;
        int i =0;
        while(i< dListOfValues.size()){
            statistics.addValue(dStatData.get(i));
            i++;
        }
        
    }
     
    /**
     * call
     * @return
     * @throws Exception 
     */        
    @Override
    public String call()throws Exception{
        String name = null;
        try{
            switch(sChoise){
                case "mean":{dTemp = statistics.getMean();
                    name = "mean";
                    break;
                }
                case "qmean":{dTemp = statistics.getQuadraticMean();
                    name = "qmean";
                    break;
                }
                case "variance":{dTemp = statistics.getPopulationVariance();
                    name = "variance";
                    break;                    
                }
                case "sum":{dTemp = statistics.getSum();
                    name = "sum";
                    break;
                }
                default:{ dTemp = -1;
                    break;
                }
            }//end switch
           return sStatBack =  name + " " +Double.toString(dTemp);
       }catch(Exception ex){
            System.err.println("StatModul:Error: " +ex.toString());
            return "StatModul:Error: " +ex.toString();
       }
    }
}
