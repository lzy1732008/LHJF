package LHJF;

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
	public static int ifContainYS(String docContent, String[] ysFlag, String regex) {
		int res = 0;
		// System.out.println(regex);
		String[] sentence = docContent.split(regex);
		for (String s : sentence) {
			if (ifContainFlag(s, ysFlag)) {
				System.out.println(s);
			//	if (s.contains("无") || s.contains("没") || s.contains("不") || s.contains("未")) {
			//		res = -1;

			//	} else {
					res = 1;
				}
			}

		return res;
	}

	public static Boolean ifContainFlag(String s, String[] ysFlag) {
		for (String flag : ysFlag) {
			if (s.contains(flag)) {
				return true;
			}
		}
		return false;
	}

	public static String getAJJBQKString(Document doc) {
		Element root = doc.getRootElement();

		Element docele = root.element("QW");
		if (docele != null) {
			if (docele.element("AJJBQK") != null) {
				Element ajjbqk = docele.element("AJJBQK");
				if (ajjbqk.attribute("value") != null) {
					String content = ajjbqk.attributeValue("value");
					return content;
				}
			}
		}
		return null;
	}

	public static Boolean windowForKey(String s,String[] keypairs,int wsize){
		if(s.length()<=wsize){
			return IfWinContainKey(s,keypairs);
		}
		for(int i = 0 ; i < s.length() - wsize; i++){
			if(IfWinContainKey(s,keypairs)){
				return true;
			}
		}
		return false;




	}

	public static Boolean IfWinContainKey(String win,String[] keypairs){
		for(int i = 0 ; i < keypairs.length;i++){
			String[] pair = keypairs[i].split(";");
			int flag = 1;
			for(int j = 0;j < pair.length;j++){
				if(!win.contains(pair[j])){
					flag = 0;
				}
			}
			if(flag == 1 ){
				return true;
			}
		}
		return false;


	}
}