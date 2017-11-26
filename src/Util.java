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

public class Util {
	/*
	 * @ysName:Ҫ��
	 * @docContent:�ĵ�����
	 * @ysFlag:һЩ���Ա�־Ҫ�ش��ڵ��ַ���
	 * ����0��ʾ�����ڸ�Ҫ��
	 * ����1��ʾҪ��Ϊ���ǡ�
	 * ����-1��ʾҪ��Ϊ����
	 */
   public static int ifContainYS(String docContent,String[] ysFlag,String regex) {
	   int res = 0;
	  // System.out.println(regex);
	   String[] sentence = docContent.split(regex);
	   for(String s:sentence) {
		  if(ifContainFlag(s,ysFlag)) {
			  System.out.println(s);
			   if(s.contains("��")||s.contains("û")||s.contains("��")||s.contains("δ")) {
				   res = -1;
				  
			   }
			   else {
				   res = 1; 
			   }
		  }		  
	   }
	   return res;	   
   }
	public static Boolean ifContainFlag(String s,String[] ysFlag) {
		for(String flag:ysFlag) {
			if(s.contains(flag)) {
				return true;
			}
		}
		return false;
	}
   public static String getAJJBQKString(Document doc) {
	   Element root = doc.getRootElement();   
  	   
  	    Element docele = root.element("QW");
  	    if(docele!=null) {
  	    	if(docele.element("AJJBQK")!=null) {
  	    	   Element ajjbqk = docele.element("AJJBQK");	
  	        	if(ajjbqk.attribute("value")!=null) {
  	    		String content = ajjbqk.attributeValue("value");
  	    		return content;
  	        	}
  	    	}
  	    }
  	    return null;
   }
   
}
