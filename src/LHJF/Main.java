package LHJF;

import java.io.File;
import java.io.FileOutputStream;
import java.io.FileWriter;

import org.dom4j.Document;
import org.dom4j.DocumentHelper;
import org.dom4j.Element;
import org.dom4j.io.OutputFormat;
import org.dom4j.io.SAXReader;
import org.dom4j.io.XMLWriter;

public class Main {
	private static YS YSMETHOD = new YS();
	public static void main(String args[]) {
		SAXReader reader = new SAXReader();
		File file = new File("/users/wenny/Downloads/2013测试");
		File[] list = file.listFiles();
		String res = "";
		System.out.print(list.length);
		try{

			for(int j =0 ;j < 234; j++){
				Document document = reader.read(list[j]);
				String fileName = list[j].getName();
				//	 System.out.println(list[j].getName()+"order:"+j);
				// if(!list[j].getName().contains("简易")){
				//第一部分*******************
				//创建新xml
				Element newroot0 = DocumentHelper.createElement("write");
				   Document documentnew = DocumentHelper.createDocument(newroot0);
				   initXML(document,newroot0);
				   Element newroot = newroot0.addElement("LHJHYSTQ").addAttribute("nameCN", "离婚纠纷要素提取");
				YSMETHOD.row101(document,newroot);

				OutputFormat format = new OutputFormat("    ",true);
				format.setEncoding("UTF-8");//设置编码格式
				XMLWriter xmlWriter = new XMLWriter(new FileOutputStream("E:\\离婚纠纷文书要素提取\\"+fileName+"要素提取.xml"),format);
				xmlWriter.write(documentnew);
				xmlWriter.close();
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
