/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.mycompany.mathtest;
import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.Callable;
import org.apache.commons.math3.analysis.function.*;


/**
 *
 * @author goran
 */
public class AritmetikModul implements Callable<String> {

    List<Integer> dAritData = new ArrayList();
    private final String sChoise;
    
    /**
     * constructor
     * @param sSelect, string
     * @param dListOfValues, list 
     */
    public AritmetikModul(String sSelect, List dListOfValues) {
        this.sChoise = sSelect;
        int i=0;
       // dAritData = dListOfValues;
        while(i< dListOfValues.size()){
            this.dAritData.add((Integer) dListOfValues.get(i));
            i++;
        }
    }

    /**
     * call
     * @return string 
     */
    @Override
    public String call(){
        String s1 = null;
        if(2<dAritData.size() || sChoise.equals("log")){
            logData();
        }else{
            s1 = calcValue();
        }
        return s1;
    }
    
    /**
     * primenr
     */
    private void logData(){
        int i=0;
        Log log = new Log();
        Log10 log10 = new Log10();
        Log1p log1 = new Log1p();
        while(i< dAritData.size()){ 
            System.out.println("Nr: "+ dAritData.get(i)+" natural log : " + log.value(dAritData.get(i)));
            System.out.println("Nr: "+ dAritData.get(i)+" log10 : " + log10.value(dAritData.get(i)));
            System.out.println("Nr: "+ dAritData.get(i)+" log1 : " + log1.value(dAritData.get(i)));
            i++;
        }
    }
    
    /**
     * calcValue
     * @return string
     */
    private String calcValue(){
        double dResult = -1;
        switch(sChoise){
            case "add":{dResult = dAritData.get(0) + dAritData.get(1);
                break;
            }
            case "sub":{dResult = dAritData.get(0) - dAritData.get(1); 
                break;
            }
            case "mult":{dResult = dAritData.get(0) * dAritData.get(1);
                break;   
            }
            case "div": { 
                if( 0==dAritData.get(1)){
                    break;
                } else{
                    dResult = dAritData.get(0) / dAritData.get(1);
                    break;
                }
            } 
            default: {dResult = -1;
                    break;
            }      
        }
        return Double.toString(dResult);         
    }
}
