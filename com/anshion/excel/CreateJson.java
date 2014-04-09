package com.anshion.excel;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;

import org.json.JSONArray;

public class CreateJson {

	public static void Create(){
		
		FileOutputStream out = null;
		 
        try {
        	
        	String json = new JSONArray(ExcelToJson.userList).toString();
        	
        	out = new FileOutputStream(System.getProperty("user.dir") + "//"+ ExcelToJson.FileName() + ".json");
			out.write(json.getBytes());
			
			 out.close();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
        
       
		
	}
}
