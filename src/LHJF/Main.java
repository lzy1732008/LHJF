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
				String path = list[j].getPath();
					 System.out.println(list[j].getPath()+"order:"+j);

				//第一部分*******************
				//创建新xml
				Element newroot0 = DocumentHelper.createElement("write");
				Document documentnew = DocumentHelper.createDocument(newroot0);
				initXML(document,newroot0);
				Element newroot = newroot0.addElement("LHJHYSTQ").addAttribute("nameCN", "离婚纠纷要素提取");

				YSMETHOD.row1(document,newroot);
				YSMETHOD.row2(document,newroot);
				YSMETHOD.row3(document,newroot);
				YSMETHOD.row4(document,newroot);
				YSMETHOD.row5(document,newroot);
				YSMETHOD.row6(document,newroot);
				YSMETHOD.row7(document,newroot);
				YSMETHOD.row8(document,newroot);
				YSMETHOD.row9(document,newroot);
				YSMETHOD.row10(document,newroot);
				YSMETHOD.row11(document,newroot);
				YSMETHOD.row12(document,newroot);
				YSMETHOD.row13(document,newroot);
				YSMETHOD.row14(document,newroot);
				YSMETHOD.row15(document,newroot);
				YSMETHOD.row16(document,newroot);
				YSMETHOD.row17(document,newroot);
				YSMETHOD.row18(document,newroot);
				YSMETHOD.row19(document,newroot);
				YSMETHOD.row20(document,newroot);
				YSMETHOD.row21(document,newroot);
				YSMETHOD.row22(document,newroot);
				YSMETHOD.row23(document,newroot);
				YSMETHOD.row24(document,newroot);
				YSMETHOD.row25(document,newroot);

				HyfjsExtractor.parentBuyHouse(path,document,newroot);
				HyfjsExtractor.hasChildrenIndependent(path,document,newroot);
				HyfjsExtractor.aboutHouse(path,document,newroot);
				HyfjsExtractor.raiseSpouse(path,document,newroot);
				HyfjsExtractor.marriageRegistration(path,document,newroot);
				HyfjsExtractor.childMaintenance(path,document,newroot);
				HyfjsExtractor.hyInOneYear(path,document,newroot);
				HyfjsExtractor.ccyd(path,document,newroot);
				HyfjsExtractor.zycc(path,document,newroot);
				HyfjsExtractor.hywx(path,document,newroot);
				HyfjsExtractor.gthd(path,document,newroot);





				OutputFormat format = new OutputFormat("    ",true);
				format.setEncoding("UTF-8");//设置编码格式
				XMLWriter xmlWriter = new XMLWriter(new FileOutputStream("/users/wenny/Downloads/2013测试xmlres/"+fileName+"要素提取.xml"),format);
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
