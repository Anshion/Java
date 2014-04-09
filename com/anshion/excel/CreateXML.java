package com.anshion.excel;

import java.io.FileOutputStream;


import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.parsers.ParserConfigurationException;
import javax.xml.transform.Result;
import javax.xml.transform.Source;
import javax.xml.transform.Transformer;
import javax.xml.transform.TransformerConfigurationException;
import javax.xml.transform.TransformerFactory;
import javax.xml.transform.dom.DOMSource;
import javax.xml.transform.stream.StreamResult;

import org.w3c.dom.Document;
import org.w3c.dom.Element;
import org.w3c.dom.Node;

import com.anshion.excel.ExcelToJson;

public class CreateXML {
	DocumentBuilderFactory dbf  = null;
	DocumentBuilder db = null;
	Document doc = null;
	TransformerFactory tff = null;
	Transformer tf = null;
	Source in = null;
	Result out = null;
	String xmlpath = null;
	
	public CreateXML()
	{
		try {
			dbf = DocumentBuilderFactory.newInstance();//实例化工厂类
			dbf.setValidating(false);//不进行有效性检查
			dbf.setNamespaceAware(true);
			
		
			db = dbf.newDocumentBuilder();
	
			doc = (Document) db.newDocument();//实例化Document类
			
			tff = TransformerFactory.newInstance();
			try {
				tf = tff.newTransformer();
			} catch (TransformerConfigurationException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			
			in= new DOMSource((Node) doc);
		
		} catch (ParserConfigurationException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}//实例化DocumentBuilder类
		
	}

/*=====================================================
 *============生成xml文件
 *=====================================================*/
	public void ProuduceXml()
	{
		
		try
		{
		
		Element users = ((org.w3c.dom.Document) doc).createElement("users");//生产根元素students		
		((Node) doc).appendChild(users);//将根元素添加到根节点后面
		
		
		for(int i=0;i<ExcelToJson.userList.size();i++){
			Element user = ProuduceElement(ExcelToJson.userList.get(i).getName(), ExcelToJson.userList.get(i).getAge(),ExcelToJson.userList.get(i).getAddress(),ExcelToJson.userList.get(i).getPhone());
			
		    users.appendChild(user);
			
		}
		
		out=new StreamResult(new FileOutputStream(System.getProperty("user.dir")+ "//" + ExcelToJson.FileName() + ".xml"));//生成输出源
		tf.transform(in,out); 
		}
		catch(Exception e)
		{
			System.out.println(e.getMessage());
		}
	}
	
	public Element ProuduceElement(String aname, String _age,String _address,String _phone) throws Exception
	{
		
//		<student>
//		<name>id</name>
//		<age>descpricecount</age>
//		<address/>
//		<phone/>
//		</student>
		Element user = ((org.w3c.dom.Document) doc).createElement("student");//生成student元素(students的子元素)
		
		
		
		Element name = ((org.w3c.dom.Document) doc).createElement("name");//创建name子元素
		name.appendChild(((org.w3c.dom.Document) doc).createTextNode(aname));//在name元素后添加文本节点
		user.appendChild(name);//添加student的子元素name
		
		
		//设置student的属性id的值
		
		
		Element age = doc.createElement("age");
		age.appendChild(doc.createTextNode(_age));
		user.appendChild(age);
		
		Element address = doc.createElement("address");
		address.appendChild(doc.createTextNode(_address));
		user.appendChild(address);
		
		
		Element phone= doc.createElement("phone");
		phone.appendChild(doc.createTextNode(_phone));
		user.appendChild(phone);
		return user;
}
}
