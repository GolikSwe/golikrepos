/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.mycompany.mathtest;
import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.Future;
import java.util.concurrent.TimeUnit;
import java.util.logging.Level;
import java.util.logging.Logger;
/**
 *
 * @author goran
 */
public class Math {

    ExecutorService exPool = Executors.newFixedThreadPool(5);    
    private Future<String> sData = null;
    private String sDataBack; 
    List<Integer> dDataList = new ArrayList<>();
    private String sOpr;
    private int iNr;
    private boolean  bSerieOrNr;

    /**
     * setDataList
     * @param iNr1
     * @param iNr2
     * @param iNr3
     * @param iNr4
     * @param iNr5 
     */
    public void setdDataList(int iNr1, int iNr2, int iNr3, int iNr4, int iNr5) {
       dDataList.add(iNr1);
       dDataList.add(iNr2);
       dDataList.add(iNr3);
       dDataList.add(iNr4);
       dDataList.add(iNr5);
    }

    public void setOpr(String opr) {
        this.sOpr = opr;
    }
    
    public String getSingelOrSerieOfPrime(int inr, boolean bserieOrNr){
        try {
            this.iNr = inr;
            this.bSerieOrNr = bserieOrNr;
            sData = exPool.submit(new CalcPrimes(iNr, bSerieOrNr));
            sDataBack = sData.get();
            exPool.shutdown();
            return sDataBack;
        } catch (InterruptedException | ExecutionException ex) {
            Logger.getLogger(Math.class.getName()).log(Level.SEVERE, null, ex);
            return "error";
        }
    }
          

    public String getdStatData() {
        try {
            sData = exPool.submit(new StatModul(sOpr, dDataList));
            exPool.awaitTermination(3, TimeUnit.SECONDS);
            exPool.shutdown();
            
            return sData.get();
        } catch (InterruptedException | ExecutionException ex) {
            Logger.getLogger(Math.class.getName()).log(Level.SEVERE, null, ex);
            return "error";
        }
    }
    
    public String getAritmetikData(){
        try {
            sData = exPool.submit(new AritmetikModul(sOpr, dDataList));
            exPool.shutdown();
            return sData.get();
            
        } catch (InterruptedException | ExecutionException ex) {
            Logger.getLogger(Math.class.getName()).log(Level.SEVERE, null, ex);
            return "error";
        }
    }
    
    
    /**
     * @param args the command line arguments
     */
//    public static void main(String[] args) {
//        try {
//            final ExecutorService expool;
//            final Future<String> sData;
//            List<Integer> dDataList = new ArrayList<>();
////            dDataList.add(1);
////            dDataList.add(2);
////            dDataList.add(3);
//            dDataList.add(110);
//            expool = Executors.newFixedThreadPool(5);
////            sData=expool.submit(new StatModul("sum", dDataList));
//            expool.submit(new CalcPrimes(100, true, true, "/home/goran/testing/logs/prime1.log"));
////            expool.submit(new CalcPrimes(15229, false));
//            sData=expool.submit(new AritmetikModul("log", dDataList));
//            System.out.println("Data: "+sData.get());
//            expool.shutdownNow();
//        } catch (InterruptedException | ExecutionException ex) {
//            Logger.getLogger(Math.class.getName()).log(Level.SEVERE, null, ex);
//        }
//    }    
    
}
