/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.mycompany.mathtest;

import java.io.IOException;
import java.nio.ByteBuffer;
import java.nio.channels.AsynchronousFileChannel;
import java.nio.file.Path;
import java.nio.file.*;
import java.util.concurrent.Callable;
import java.util.concurrent.Future;
import java.util.logging.Level;
import java.util.logging.Logger;
import org.apache.commons.math3.primes.Primes;

/**
 *
 * @author goran
 */
public class CalcPrimes implements Callable<String>{
    
    private final int iThisPrimes;
    private boolean bCalcSerieOrNr =false;
    private boolean bWriteDownData =false;
    private String sPath = null;

    /**
     * constructor
     * @param iNr, int
     * @param bWrite 
     * @param bSerieOrNr 
     * @param sFullPathToTxt 
     */
    public CalcPrimes(int iNr, boolean bWrite, boolean bSerieOrNr, String sFullPathToTxt){
        this.iThisPrimes = iNr;
        this.bWriteDownData = bWrite;
        this.bCalcSerieOrNr = bSerieOrNr;
        this.sPath = sFullPathToTxt;
    }
    
    /**
     * constructor
     * @param iNr, int
     * @param bSerieOrTestNr, boolean (serie true, check nr false) 
     */
    public CalcPrimes(int iNr, boolean bSerieOrTestNr){
        this.iThisPrimes = iNr;
        this.bCalcSerieOrNr = bSerieOrTestNr;
    }
    
    /**
     * run
     */
    @Override
    public String call(){
        if(true == bCalcSerieOrNr){
            calcSeriesOfPrimes();
            return "";
        }else{
           return isThisAprime();
        }       
    }
    
    /**
     * calcSeriesOfPrimes
     */
    private void calcSeriesOfPrimes(){ 
        StringBuffer sBuffer = new  StringBuffer();
        for(int i = 0; i<iThisPrimes; i++){
         if(true == Primes.isPrime(i)){
             if(true == bWriteDownData){
                 sBuffer.append("This is a prime: ").append(i).append("\n");
             }else{
                System.out.println("This is a prime: " +i+ " : " +Primes.isPrime(i));
             }
         }        
        }
        writeDownPrime(sBuffer);
    }
    
    /**
     * isThisAprime
     */
    private String isThisAprime(){
        boolean bol = Primes.isPrime(iThisPrimes);
        String sendBack;
        if(false == bol){
            sendBack = "This is not a prime number: " + iThisPrimes + "\nNearest prime is: " +Primes.nextPrime(iThisPrimes);
//            System.out.println("This is not a prime number: " + iThisPrimes);
//            System.out.println("Nearest prime is: " +Primes.nextPrime(iThisPrimes));
        }else{
//            System.out.println("This is a prime number: " + iThisPrimes);
            sendBack = "This is a prime number: " + iThisPrimes;
        }
        return sendBack;
    }

    private void writeDownPrime(StringBuffer sInput){
        try { 
            Path pCreateFile = Paths.get(sPath);
            if(!Files.exists(pCreateFile)){
                Files.createFile(pCreateFile);        
            }
            Path filePath = Paths.get(sPath);
            AsynchronousFileChannel fileChannel;
            fileChannel = AsynchronousFileChannel.open(filePath, StandardOpenOption.WRITE);
            ByteBuffer buffer = ByteBuffer.allocate(10240);
            buffer.put(sInput.toString().getBytes("ISO-8859-1"));
            buffer.flip();
            //Ass
            Future<Integer> opr = fileChannel.write(buffer, 0);
            while(!opr.isDone()){
//                System.out.println("write");
            }
            buffer.clear();
            fileChannel.close();
        } catch (IOException ex) {
            Logger.getLogger(CalcPrimes.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
}
