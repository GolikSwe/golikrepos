package edu.mymath;

import java.io.IOException;
import java.nio.ByteBuffer;
import java.nio.channels.AsynchronousFileChannel;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.nio.file.StandardOpenOption;
import java.util.concurrent.Future;
import org.apache.log4j.Logger;

/**
 * Common
 * @author Goran Lindqvist
 */
public class Common {

 	//logger
	final static Logger log  = Logger.getLogger(edu.mymath.Common.class);
	
	/**
	 * writeDown
	 * @param sInput string buffer.
	 * @param sPathForFile, string.
	 */
	public boolean writeDownToFile(StringBuffer sInput, String sPathForFile){
		log.info("Common.writeDown:start");
		try{
			Path pCreateFile = Paths.get(sPathForFile);
			if(!Files.exists(pCreateFile)){
				Files.createFile(pCreateFile);
			}
			Path filePath = Paths.get(sPathForFile);
			AsynchronousFileChannel fileChannel;
			fileChannel = AsynchronousFileChannel.open(filePath, StandardOpenOption.WRITE);
			ByteBuffer buf = ByteBuffer.allocateDirect(10000000);
			buf.put(sInput.toString().getBytes("ISO-8859-1"));
			buf.flip();
			Future<Integer> opr = fileChannel.write(buf, 0);
			while(!opr.isDone()){};
			buf.clear();
			fileChannel.close();
			log.info("Common.writeDown:stop");
		}catch(IOException ioex){
			System.out.println("ERROR:Common.writeDown: " +ioex.toString());
			log.info("ERROR:Common.writeDown: " +ioex.toString());
			return false;
		}
		return true;
	}	
}
