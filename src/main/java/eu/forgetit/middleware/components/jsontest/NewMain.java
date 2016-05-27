/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package eu.forgetit.middleware.components.jsontest;
import java.io.FileWriter;
import java.io.IOException;
import org.json.simple.JSONArray;
import org.json.simple.JSONObject;
/**
 *Just testing simple json
 * @author goran
 */
public class NewMain {

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {
	JSONObject obj = new JSONObject();
	obj.put("name", "Goran Lindqvist");
	obj.put("age", new Integer(23));

	JSONArray list = new JSONArray();
	list.add("msg nr1 input");
	list.add("msg nr2 input");
	list.add("msg nr3 input");

	obj.put("messages", list);

	try {

		FileWriter file = new FileWriter("/home/goran/testing/logs/test1.txt");
		file.write(obj.toJSONString());
		file.flush();
		file.close();

	} catch (IOException e) {
		e.printStackTrace();
	}

	System.out.print(obj);

     }
    }
    
//}
