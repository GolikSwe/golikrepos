package edu.mymath;

import java.io.File;
import java.io.IOException;
import java.nio.ByteBuffer;
import java.nio.channels.AsynchronousFileChannel;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.nio.file.StandardOpenOption;
import java.util.concurrent.Callable;
import java.util.concurrent.Future;

import org.apache.commons.math3.primes.Primes;

public class CalcPrimes implements Callable<String> {

	private final int iThisPrimenr;
	private boolean bCalcSerieOrNr = false;
	private boolean bWriteDownData = false;
	private String sPath = "/home/goran/testing/logs/test.txt";
	
	/**
	 * constructor 1
	 * @param iNr
	 * @param bSerieOrTestNr
	 */
	public CalcPrimes(int iNr, boolean bSerieOrTestNr){
		this.iThisPrimenr = iNr;
		this.bCalcSerieOrNr = bSerieOrTestNr;
	}
	
	/**
	 * constructor 2
	 * @param iNr
	 * @param bWrite
	 * @param bSerieOrNr
	 * @param sFullPathToTxt
	 */
	public CalcPrimes(int iNr, boolean bWrite, boolean bSerieOrNr, String sFullPathToTxt) {
		this.iThisPrimenr = iNr;
		this.bWriteDownData = bWrite;
		this.bCalcSerieOrNr = bSerieOrNr;
		this.sPath = sFullPathToTxt;
	}
	
	/**
	 * Call
	 */
	@Override
	public String call(){
		if(true ==  bCalcSerieOrNr){
			calcSeriesOfPrime();
			return "SeriesOfPrime";
		}else{
			return isThisPrime();
		}
	}

	/**
	 * isThisPrime
	 * @return String
	 */
	private String isThisPrime(){
		String sendBack;
		boolean bol = Primes.isPrime(iThisPrimenr);
		if(false == bol){
			sendBack = "This i not a prime: " +iThisPrimenr+ "\n Nearest prime is: " + Primes.nextPrime(iThisPrimenr); 
		}else{
			sendBack = "This is a prime: " +iThisPrimenr;
		}
		return sendBack;
	}
	
	private void calcSeriesOfPrime(){
		try{
			StringBuffer sBuf = new StringBuffer();
			for(int i = 0; i<iThisPrimenr ; i++){
				if(true == Primes.isPrime(i)){
					sBuf.append("This is A prime: ").append(i).append("\n");
				}else{
					sBuf.append("This is NOT a prime: ").append(i).append("\n");
				}
			}//end for
			//write
			writeDown(sBuf);
		}catch(Exception ex){
			System.out.println("Error:CalcPrimes.isThisPrime: " +ex);
		}
	}
	
	/**
	 * writeDown
	 * @param sInput string buffer
	 */
	private void writeDown(StringBuffer sInput){
		try{
			Path pCreateFile = Paths.get(sPath);
			if(!Files.exists(pCreateFile)){
				Files.createFile(pCreateFile);
			}
			Path filePath = Paths.get(sPath);
			AsynchronousFileChannel fileChannel;
			fileChannel = AsynchronousFileChannel.open(filePath, StandardOpenOption.WRITE);
			ByteBuffer buf = ByteBuffer.allocateDirect(10000000);
			buf.put(sInput.toString().getBytes("ISO-8859-1"));
			buf.flip();
			Future<Integer> opr = fileChannel.write(buf, 0);
			while(!opr.isDone()){};
			buf.clear();
			fileChannel.close();
		}catch(IOException ioex){
			System.out.println("Error:CalcPrimes.writeDown: " +ioex);
		}
	}
}
