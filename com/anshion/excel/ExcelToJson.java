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
     
	//��ȡd://test.xls�ļ�
	private static void readExcelFile() {
		// TODO Auto-generated method stub
		 try
         {
			 String sourcefile = filePath + "/" + fileName;
             InputStream is = new FileInputStream(sourcefile);
             Workbook rwb = Workbook.getWorkbook(is);
           //����ܵ�Sheets���õ�sheet�Ĳ���
             Sheet[] sheets = rwb.getSheets();
             int sheetLen = sheets.length;
             //��õ�һ��Sheets �Ľ��
            jxl.Sheet rs = rwb.getSheet(0); 
            int num_row = rs.getRows();//�õ�����
            int num_column=rs.getColumns();//�õ����� 
                   
            for(int j=0;j < num_row ;j++)
             {
                Cell[] cell  = rs.getRow(j);//�õ���j�е�����ֵ
                
                for(int column_index=0;column_index<num_column;column_index++){
                 String value  = cell[column_index].getContents();//�õ���j�У���column_indexs�е�ֵ
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
