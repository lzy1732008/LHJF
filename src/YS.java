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
	 * row3:����ܲ���ͥ����;��ͥ
	 */ 
	public void row1_1(Document document,Element newroot) {
		
	}
	public static int  row3(Document document) {
	 	Element root = document.getRootElement();   
  	   // Element SFCZJHQKJFYBDNode = newroot.addElement("SFCZJHQKJFYBD").addAttribute("nameCN", "�Ƿ񱻸�ܲ���ͥ����;��ͥ");
  	    int res = 0; 
  	    
  	    Element docele = root.element("QW");
  	    if(docele!=null) {
  	    	if(docele.attribute("value")!=null) {
  	    		String content = docele.attributeValue("value");
  	    		String[] sentence = content.split("��|��");
  	   	        for(String s:sentence) {
  	   		        if(s.contains("����")&&(s.contains("��ͥ")||s.contains("����ͥ")||s.contains("δ��ͥ"))) {
  	   		        	res = 1;
  	   		  }		  
  	   	   }
  	    		
  	    	}
  	    }
        return res;   
	}
	/*
	 * ����ǰԭ�����볷��
	 */
	public static int  row4(Document document) {
	 	Element root = document.getRootElement();   
  	   // Element SFCZJHQKJFYBDNode = newroot.addElement("SFCZJHQKJFYBD").addAttribute("nameCN", "�Ƿ񱻸�ܲ���ͥ����;��ͥ");
  	    int res = 0; 
  	    
  	    Element docele = root.element("QW");
  	    if(docele!=null) {
  	    	if(docele.attribute("value")!=null) {
  	    		String content = docele.attributeValue("value");
  	    		String[] sentence = content.split("��|��");
  	   	        for(String s:sentence) {
  	   		        if(s.contains("ԭ��")&&s.contains("��")&&s.contains("��")) {
  	   		        	res = 1;
  	   		        	System.out.println(s);
  	   		  }		  
  	   	   }
  	    		
  	    	}
  	    }
        return res;   
	}
	/*
	 * �漰��Ů��������
	 */
	public static int  row5(Document document) {
		int res = 0; 
 	     String content = util.getAJJBQKString(document);
 	     String[] sentence = content.split("��|��");
 	   	 for(String s:sentence) {
 	   		 if(s.contains("��")||s.contains("Ů")||s.contains("��")||s.contains("Ů")||s.contains("��")) {
 	   		    res = 1;	   		        
 	   		    String[] ysFlag = {"����","����","ѧҵ"};
 	   		    System.out.println(s);
 	   		    if(util.ifContainFlag(s, ysFlag)) {
 	   		        res = 1;
 	   		    }
 	   		  }		  
 	   	   }	    		    
       return res;  
	}
	/*
	 * �漰��Ů����ѡ�������
	 */
	public static int  row5_2(Document document) {
	 	 int res = 0; 
  	     String content = util.getAJJBQKString(document);
  	     String[] sentence = content.split("��|��");
  	   	 for(String s:sentence) {
  	   		 if(s.contains("��")||s.contains("Ů")||s.contains("��")||s.contains("Ů")||s.contains("��")) {
  	   		    res = 1;	   		        
  	   		    String[] ysFlag = {"�����","������"};
  	   		    System.out.println(s);
  	   		    if(util.ifContainFlag(s, ysFlag)) {
  	   		        res = 1;
  	   		    }
  	   		  }		  
  	   	   }	    		    
        return res;   
	} 
	/*
	 * �в�������Ů
	 */
	public static int row6(Document document) {
		 int res = 0; 
  	     String content = util.getAJJBQKString(document);
  	     if(content!=null) {
  	     String regex = "��|��";
  	     String[] ysFlag = {"������","����"};
  	     util.ifContainYS(content, ysFlag, regex);
  	     }
  	     return res;
	}
	/*
	 * �л����񹲺͹��������Ϸ�����ʮ������֤��
	 */
	public static int row7(Document document) {
		int res = 0; 
 	     String content = util.getAJJBQKString(document);
 	     if(content!=null) {
 	    	String regex = "��|��|��";
 	  	    String[] sentence = content.split(regex);
 	  	    for(String s:sentence) {
 	  	    	if(s.contains("֤��")) {
 	  	    		System.out.println("֤�ݣ�"+s);
 	  	    		if(s.contains("�ṩ")||s.contains("�ռ�")||s.contains("����")) {
 	  	    			res =1;
 	  	    			System.out.println("֤��22��"+s);
 	  	    		}
 	  	    }
 	  	    }
 	     }
 	     return res;
	}
	
	/*
	 * �л����񹲺͹��������Ϸ���һ����ʮ����:��һ����ʮ���� ��������Ժ�����ɳ��ķ�ͥ������ʵ�����Ȩ�������ϵ��ȷ�����鲻��ļ򵥵����°��������ñ��¹涨��
	 */
	public static int row8(Document document) {
		int res = 0; 
 	    String content = util.getAJJBQKString(document);
 	    String regex = "��|��|��";
 	    if(content!=null) {
	  	    String[] sentence = content.split(regex);
	  	    for(String s:sentence) {
	  	    	if(s.contains("��")||s.contains("����")) {
	  	    		System.out.println("�򵥣�"+s);
	  	    		if(s.contains("���°���")||s.contains("����")) {
	  	    			res =1;
	  	    			System.out.println("���°�����"+s);
	  	    		}
	  	    }
	  	    }
	     }	    
 	    return res;
	}
	
	/*
	 * �л����񹲺͹�����������ʮ����
	 */
	public static int row100(Document document) {
		int res = 0; 
 	    String content = util.getAJJBQKString(document);
 	    String regex = "��|��|��";
 	    if(content!=null) {
	  	    String[] sentence = content.split(regex);
	  	    for(String s:sentence) {
	  	    	if(s.contains("��")||s.contains("����")) {
	  	    		System.out.println("�򵥣�"+s);
	  	    		if(s.contains("���°���")||s.contains("����")) {
	  	    			res =1;
	  	    			System.out.println("���°�����"+s);
	  	    		}
	  	    }
	  	    }
	     }	    
 	    return res;
	}

}
