package com.anshion.excel;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.util.ArrayList;
import java.util.Arrays;

import org.json.JSONArray;
import org.json.JSONObject;


import jxl.Cell;
import jxl.Sheet;
import jxl.Workbook;
import com.anshion.excel.CreateXML;


public class ExcelToJson
 {
    public static ArrayList<User> userList = new ArrayList();
    
    static String[] agrs_input;
    static String fileName;
    static String filePath;

	public static void main(String[] agrs){
		
		
		String path = agrs[0];
	//	path = "d://test.xls";
		fileName = path.substring(path.lastIndexOf("\\") + 1);
		filePath = path.substring(0, path.lastIndexOf("\\"));

	
		 

		readExcelFile();

		CreateXML xmlCreater = new CreateXML();
		xmlCreater.ProuduceXml();
		
		CreateJson.Create(); 
	
     }
     
	//读取d://test.xls文件
	private static void readExcelFile() {
		// TODO Auto-generated method stub
		 try
         {
			 String sourcefile = filePath + "/" + fileName;
             InputStream is = new FileInputStream(sourcefile);
             Workbook rwb = Workbook.getWorkbook(is);
           //获得总的Sheets，得到sheet的层数
             Sheet[] sheets = rwb.getSheets();
             int sheetLen = sheets.length;
             //获得第一个Sheets 的结果
            jxl.Sheet rs = rwb.getSheet(0); 
            int num_row = rs.getRows();//得到行数
            int num_column=rs.getColumns();//得到列数 
                   
            for(int j=0;j < num_row ;j++)
             {
                Cell[] cell  = rs.getRow(j);//得到第j行的所有值
                
                for(int column_index=0;column_index<num_column;column_index++){
                 String value  = cell[column_index].getContents();//得到第j行，第column_indexs列的值
                 System.out.print(""+""+rs.getRow(j)[column_index].getContents()+"   ");
                 
                }
                User newUser = new User(cell[0].getContents(),cell[1].getContents(),cell[2].getContents(),cell[3].getContents());
                userList.add(newUser);
             }
	             
         }
         catch(Exception ex)
        {
               ex.printStackTrace();
         }
	}
	
	public static String FileName(){
		return fileName.substring(0, fileName.lastIndexOf("."));
	}
 }
