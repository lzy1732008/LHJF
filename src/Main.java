import java.io.File;
import java.io.FileOutputStream;
import java.io.FileWriter;
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
		File file = new File("/users/wenny/Downloads/2013����");
		File[] list = file.listFiles();
		String res = "";
		System.out.print(list.length);
		try{

			for(int j =0 ;j < 234; j++){
				Document document = reader.read(list[j]);
				String fileName = list[j].getName();
				//	 System.out.println(list[j].getName()+"order:"+j);
				// if(!list[j].getName().contains("����")){
				//��һ����*******************
				//������xml
				Element newroot0 = DocumentHelper.createElement("write");
				//   Document documentnew = DocumentHelper.createDocument(newroot0);
				//   initXML(document,newroot0);
				//   Element newroot = newroot0.addElement("LHJHYSTQ").addAttribute("nameCN", "������Ҫ����ȡ");
				YSMETHOD.row100(document);


			}
			//�����д��txt�ļ���
			FileWriter fw = new FileWriter("res.txt");
			fw.write(res);

		}catch(Exception e){
			e.printStackTrace();
		}





	}

	public static void initXML(Document document,Element newroot){//��ԭ�ļӽ�ȥ
		System.out.println("��ʼ��ȡԭxml�ڵ�");
		Element root = document.getRootElement();
		newroot.add((Element)root.clone());
		//System.out.println(root.getText());
		System.out.println("��ȡ�ɹ�");
	}
}
