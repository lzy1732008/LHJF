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
	public static int  row3(Document document) {
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
	public static int  row4(Document document) {
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
	public static int  row5(Document document) {
		int res = 0; 
 	     String content = util.getAJJBQKString(document);
 	     String[] sentence = content.split("。|；");
 	   	 for(String s:sentence) {
 	   		 if(s.contains("子")||s.contains("女")||s.contains("男")||s.contains("女")||s.contains("孩")) {
 	   		    res = 1;	   		        
 	   		    String[] ysFlag = {"教育","抚养","学业"};
 	   		    System.out.println(s);
 	   		    if(util.ifContainFlag(s, ysFlag)) {
 	   		        res = 1;
 	   		    }
 	   		  }		  
 	   	   }	    		    
       return res;  
	}
	/*
	 * 涉及子女生活费、教育费
	 */
	public static int  row5_2(Document document) {
	 	 int res = 0; 
  	     String content = util.getAJJBQKString(document);
  	     String[] sentence = content.split("。|；");
  	   	 for(String s:sentence) {
  	   		 if(s.contains("子")||s.contains("女")||s.contains("男")||s.contains("女")||s.contains("孩")) {
  	   		    res = 1;	   		        
  	   		    String[] ysFlag = {"生活费","抚养费"};
  	   		    System.out.println(s);
  	   		    if(util.ifContainFlag(s, ysFlag)) {
  	   		        res = 1;
  	   		    }
  	   		  }		  
  	   	   }	    		    
        return res;   
	} 
	/*
	 * 有哺乳期子女
	 */
	public static int row6(Document document) {
		 int res = 0; 
  	     String content = util.getAJJBQKString(document);
  	     if(content!=null) {
  	     String regex = "。|；";
  	     String[] ysFlag = {"哺乳期","襁褓"};
  	     util.ifContainYS(content, ysFlag, regex);
  	     }
  	     return res;
	}
	/*
	 * 中华人民共和国民事诉讼法第六十四条：证据
	 */
	public static int row7(Document document) {
		int res = 0; 
 	     String content = util.getAJJBQKString(document);
 	     if(content!=null) {
 	    	String regex = "。|；|，";
 	  	    String[] sentence = content.split(regex);
 	  	    for(String s:sentence) {
 	  	    	if(s.contains("证据")) {
 	  	    		System.out.println("证据："+s);
 	  	    		if(s.contains("提供")||s.contains("收集")||s.contains("调查")) {
 	  	    			res =1;
 	  	    			System.out.println("证据22："+s);
 	  	    		}
 	  	    }
 	  	    }
 	     }
 	     return res;
	}
	
	/*
	 * 中华人民共和国民事诉讼法第一百四十二条:第一百四十二条 基层人民法院和它派出的法庭审理事实清楚、权利义务关系明确、争议不大的简单的民事案件，适用本章规定。
	 */
	public static int row8(Document document) {
		int res = 0; 
 	    String content = util.getAJJBQKString(document);
 	    String regex = "。|；|，";
 	    if(content!=null) {
	  	    String[] sentence = content.split(regex);
	  	    for(String s:sentence) {
	  	    	if(s.contains("简单")||s.contains("简易")) {
	  	    		System.out.println("简单："+s);
	  	    		if(s.contains("民事案件")||s.contains("程序")) {
	  	    			res =1;
	  	    			System.out.println("民事案件："+s);
	  	    		}
	  	    }
	  	    }
	     }	    
 	    return res;
	}
	
	/*
	 * 中华人民共和国婚姻法第三十八条
	 */
	public static int row100(Document document) {
		int res = 0; 
 	    String content = util.getAJJBQKString(document);
 	    String regex = "。|；|，";
 	    if(content!=null) {
	  	    String[] sentence = content.split(regex);
	  	    for(String s:sentence) {
	  	    	if(s.contains("简单")||s.contains("简易")) {
	  	    		System.out.println("简单："+s);
	  	    		if(s.contains("民事案件")||s.contains("程序")) {
	  	    			res =1;
	  	    			System.out.println("民事案件："+s);
	  	    		}
	  	    }
	  	    }
	     }	    
 	    return res;
	}

}
