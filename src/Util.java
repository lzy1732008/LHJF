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
	 * @ysName:要素
	 * @docContent:文档内容
	 * @ysFlag:一些可以标志要素存在的字符串
	 * 返回0表示不存在该要素
	 * 返回1表示要素为“是”
	 * 返回-1表示要素为“否”
	 */
   public static int ifContainYS(String ysName,String docContent,String[] ysFlag) {
	   int res = 0;
	   String[] sentence = docContent.split("，|。|；|、");
	   for(String s:sentence) {
		  if(ifContainFlag(s,ysFlag)) {
			   if(s.contains("无")||s.contains("没")||s.contains("不")||s.contains("未")) {
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
   
   
}
