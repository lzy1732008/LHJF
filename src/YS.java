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
public class YS {
	private static Util util= new Util();
	/*
	 * row3:被告拒不到庭或中途退庭
	 */ 
	public void row1_1(Document document,Element newroot) {
		
	}
	public static int  row3(Document document,Element newroot) {
	 	Element root = document.getRootElement();   
  	   // Element SFCZJHQKJFYBDNode = newroot.addElement("SFCZJHQKJFYBD").addAttribute("nameCN", "是否被告拒不到庭或中途退庭");
  	    int res = 0; 
  	    
  	    Element docele = root.element("QW");
  	    if(docele!=null) {
  	    	if(docele.attribute("value")!=null) {
  	    		String content = docele.attributeValue("value");
  	    		String[] sentence = content.split("。|；");
  	   	        for(String s:sentence) {
  	   		        if(s.contains("被告")&&(s.contains("退庭")||s.contains("不到庭")||s.contains("未到庭"))) {
  	   		        	res = 1;
  	   		  }		  
  	   	   }
  	    		
  	    	}
  	    }
        return res;   
	}
	/*
	 * 宣判前原告申请撤诉
	 */
	public static int  row4(Document document,Element newroot) {
	 	Element root = document.getRootElement();   
  	   // Element SFCZJHQKJFYBDNode = newroot.addElement("SFCZJHQKJFYBD").addAttribute("nameCN", "是否被告拒不到庭或中途退庭");
  	    int res = 0; 
  	    
  	    Element docele = root.element("QW");
  	    if(docele!=null) {
  	    	if(docele.attribute("value")!=null) {
  	    		String content = docele.attributeValue("value");
  	    		String[] sentence = content.split("。|；");
  	   	        for(String s:sentence) {
  	   		        if(s.contains("原告")&&s.contains("撤")&&s.contains("诉")) {
  	   		        	res = 1;
  	   		        	System.out.println(s);
  	   		  }		  
  	   	   }
  	    		
  	    	}
  	    }
        return res;   
	}
	/*
	 * 涉及子女抚养教育
	 */
	public static int  row5(Document document,Element newroot) {
	 	Element root = document.getRootElement();   
  	   // Element SFCZJHQKJFYBDNode = newroot.addElement("SFCZJHQKJFYBD").addAttribute("nameCN", "是否被告拒不到庭或中途退庭");
  	    int res = 0; 
  	    
  	    Element docele = root.element("QW");
  	    if(docele!=null) {
  	    	if(docele.attribute("value")!=null) {
  	    		String content = docele.attributeValue("value");
  	    		String[] sentence = content.split("。|；|，");
  	   	        for(String s:sentence) {
  	   		        if(s.contains("子")||s.contains("女")||s.contains("男")||s.contains("女")||s.contains("孩")) {
  	   		        	res = 1;	   		        
  	   		        	String[] ysFlag = {"教育","上学","学习","学业","抚养"};
  	   		        	
  	   		        	
  	   		        	if(s.contains("教育")||s.contains("上学")||s.contains("学习")||s.contains("学业")||) {
  	   		               	System.out.println("study>>>>>>>>>>>:"+s);
  	   		        	}
  	   		  }		  
  	   	   }
  	    		
  	    	}
  	    }
        return res;   
	}
	
	
	
	

}
