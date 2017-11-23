import java.io.File;
import java.io.FileOutputStream;
import java.util.ArrayList;
import java.util.Collection;
import java.util.Iterator;
import java.util.List;
import java.util.Scanner;
import java.util.regex.*;

import org.dom4j.Document;
import org.dom4j.DocumentException;
import org.dom4j.DocumentHelper;
import org.dom4j.Element;
import org.dom4j.io.OutputFormat;
import org.dom4j.io.SAXReader;
import org.dom4j.io.XMLWriter;
import org.dom4j.Attribute;

public class Main {
	private static YS YSMETHOD = new YS();
	public static void main(String args[]) {
	SAXReader reader = new SAXReader();
	 File file = new File("C:\\Users\\Dell\\Desktop\\法条推荐\\离婚纠纷一审2013.tar\\离婚纠纷一审2013\\2013");
	 File[] list = file.listFiles();
	 try{
		
		 for(int j =0 ;j < 100; j++){ 
			 Document document = reader.read(list[j]);
			 String fileName = list[j].getName();
			 System.out.println(list[j].getName()+"order:"+j);
			// if(!list[j].getName().contains("简易")){
 		//第一部分*******************
			 //创建新xml    			   
			    Element newroot0 = DocumentHelper.createElement("write");  
		        Document documentnew = DocumentHelper.createDocument(newroot0); 
		        initXML(document,newroot0);    		     
		        Element newroot = newroot0.addElement("LHJHYSTQ").addAttribute("nameCN", "离婚纠纷要素提取");
		        YSMETHOD.row5(document,newroot);
		 }
		 
     }catch(Exception e){
			 e.printStackTrace();
	}
}
     
public static void initXML(Document document,Element newroot){//将原文加进去
     System.out.println("开始获取原xml节点");
     Element root = document.getRootElement();
     newroot.add((Element)root.clone());	     
	 //System.out.println(root.getText());
	 System.out.println("获取成功");
}
}
