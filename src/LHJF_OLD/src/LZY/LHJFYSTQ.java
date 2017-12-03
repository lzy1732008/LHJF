package LHJF_OLD.src.LZY;

import LHJF_OLD.src.GYW.*;
import LHJF_OLD.src.LTY.*;
import LHJF_OLD.src.LYP.*;
import org.dom4j.Document;
import org.dom4j.DocumentHelper;
import org.dom4j.Element;
import org.dom4j.io.OutputFormat;
import org.dom4j.io.SAXReader;
import org.dom4j.io.XMLWriter;

import java.io.File;
import java.io.FileOutputStream;


public class LHJFYSTQ {
      public static void main(String args[]){
    	 SAXReader reader = new SAXReader();
     	 File file = new File("E:\\fortherstudy\\任务\\婚姻纠纷\\民事案由_离婚纠纷测试文书集10000篇");
     	 File[] list = file.listFiles();
     	 try{
     		
     		 for(int j =0 ;j < list.length; j++){ 
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
     		        
     		//要素提取部分
     		       Element JBSSNode = newroot.addElement("JBSS").addAttribute("nameCN", "基本事实");
     		      JBSS.XSSJandRHXS(document,JBSSNode);
     		      JBSS.QLLASJ(document, JBSSNode);  
     		      JBSS.JHDJSJ(document, JBSSNode);
     		      JBSS.JBJHYSSJ(document, JBSSNode);
     		      JBSS.FQBH(document, JBSSNode);  
     		      JBSS.FJ(document, JBSSNode);
     		      JBSS.JHQKJFYBD(document, JBSSNode);
     		      
     		      
     		      
    		      Element ZYBZYLHQKNode = newroot.addElement("SFCZKZYHBKZYJCHYQK").addAttribute("nameCN", "是否存在可准予或不准予解除婚姻关系的情形");
     		      CZKZYHBZYJCHYQK.row8(document, ZYBZYLHQKNode);
     		      CZKZYHBZYJCHYQK.row9(document, ZYBZYLHQKNode);  
     		      CZKZYHBZYJCHYQK.row10(document, ZYBZYLHQKNode);  
     		      CZKZYHBZYJCHYQK.row11(document, ZYBZYLHQKNode);  
     		      CZKZYHBZYJCHYQK.row12(document, ZYBZYLHQKNode);
     		      CZKZYHBZYJCHYQK.row13(document, ZYBZYLHQKNode);
     		      CZKZYHBZYJCHYQK.row14(document, ZYBZYLHQKNode);
    	          CZKZYHBZYJCHYQK.row15(document, ZYBZYLHQKNode);
    	           CZKZYHBZYJCHYQK.row16(document, ZYBZYLHQKNode);//16.是否存在一方下落不明满二年，对方起诉离婚，经公告查找确无下落的情形：                 。
				 LHJF_OLD.src.LTY.InfoSeek2.sfhyjbqx(document, ZYBZYLHQKNode);//17
     		       CZKZYHBZYJCHYQK.row18(document, ZYBZYLHQKNode);
				 LHJF_OLD.src.LTY.InfoSeek2.sfqpzj(document, ZYBZYLHQKNode); //19
				 LHJF_OLD.src.LTY.InfoSeek2.sfwtjwhh(document, ZYBZYLHQKNode);//20
				 LHJF_OLD.src.LTY.InfoSeek2.sfbbmbhy(document, ZYBZYLHQKNode);//21
				 LHJF_OLD.src.LTY.InfoSeek2.sfhybrq(document, ZYBZYLHQKNode);//22
     		       //23
     		       
     		       
     		       
     		       Element ZNFYQNode = newroot.addElement("ZVFYQGSXCMSS").addAttribute("nameCN", "子女抚养权归属需查明的事实");
				 LHJF_OLD.src.LTY.InfoSeek2.syznqk(document, ZNFYQNode);//24
     		       ZNFYQGS.row25(document, ZNFYQNode); //25
				 LHJF_OLD.src.LTY.InfoSeek2.zntsqk(document, ZNFYQNode);                                    //26
     		       ZNFYQGS.row27(document, ZNFYQNode);
     		       Element LZSNNode1=ZNFYQNode.addElement("LZSYXZNKSFYFYWT").addAttribute("nameCN", "两周岁以下子女可随父方抚养问题");
     		       ZNFYQGS.row28(document, LZSNNode1);
     		       ZNFYQGS.row281(document, LZSNNode1);
     		       ZNFYQGS.row282(document, LZSNNode1); 
     		       ZNFYQGS.row283(document, LZSNNode1); 
     		       Element LZSNNode=ZNFYQNode.addElement("LZSYXZNKSYFFYWT").addAttribute("nameCN", "两周岁以下子女可随一方抚养问题");
				 LHJF_OLD.src.GYW.InfoTest2.row29(document, LZSNNode); //29
     		   //    GYW.InfoTest2.row29_1(document, LZSNNode);//29-1
     		  //     GYW.InfoTest2.row29_2(document, LZSNNode); //29-2
     		   //    GYW.InfoTest2.row29_3(document, LZSNNode);//29-3
     		   //    GYW.InfoTest2.row29_4(document, LZSNNode);//29-4
				 LHJF_OLD.src.GYW.InfoTest2.row30(document, ZNFYQNode);//30
     		       //31
     		       
     		       
     		       
     		       Element CCFGXCMSSNode = newroot.addElement("CCFGXCMSS").addAttribute("nameCN", "财产分割需查明的事实");
				 LHJF_OLD.src.LYP.infoSeek.YGHQ(document, CCFGXCMSSNode);//32
				 LHJF_OLD.src.LYP.infoSeek.BGHQ(document, CCFGXCMSSNode);//33
         	       CCFGXCMSS.row34(document, CCFGXCMSSNode);
				 LHJF_OLD.src.LYP.infoSeek.YBGDWandSR(document, CCFGXCMSSNode);//35 //36
         	       //37
         	       
         	       
         	       
         	       Element FQGTZQZWQKNode = newroot.addElement("FQGTZQZWQK").addAttribute("nameCN", "夫妻共同债权、债务情况");
				 LHJF_OLD.src.LYP.infoSeek.FQZQ(document, FQGTZQZWQKNode);//38
				 LHJF_OLD.src. LYP.infoSeek.FQZW(document, FQGTZQZWQKNode);//39
         	       //40
     		   
         	       
         	      OutputFormat format = new OutputFormat("    ",true);
         		 format.setEncoding("UTF-8");//设置编码格式  
         		 XMLWriter xmlWriter = new XMLWriter(new FileOutputStream("E:\\离婚纠纷文书要素提取\\"+fileName+"要素提取.xml"),format);
         		 xmlWriter.write(documentnew);  
         		 xmlWriter.close();   
 			 }
//     		 }
     		 
            }catch(Exception e){
    			 e.printStackTrace();
    		 }
      
      }
      public static void initXML(Document document, Element newroot){//将原文加进去
 	     System.out.println("开始获取原xml节点");
 	     Element root = document.getRootElement();
 	     newroot.add((Element)root.clone());
 		 //System.out.println(root.getText());
 		 System.out.println("获取成功");
   }
     
      
}
