package LHJF_OLD.src.GYW;

import org.dom4j.Document;
import org.dom4j.Element;

import java.util.Iterator;

public class InfoTest2 {
	static boolean Is29 = false;
	  
    /**
     * 30.是否存在父、母抚养未成年子女的条件基本相同，双方均要求子女与其共同生活，但子女单独随祖父母或外祖父母共同生活多年，
     * 且祖父母或外祖父母要求并有能力帮助子女照顾孙子女或外孙子女的，可作为子女随父或母生活的优先条件予以考虑的情形
     * @param document
     * @param newroot
     */
	public static void  row30(Document document, Element newroot){
		Element root = document.getRootElement();
		String Grandp = "未考虑或不存在";//祖父母因素
		Element row30Node = newroot.addElement("GRANDP").addAttribute("nameCN", "祖父母因素");
		Iterator it = root.elementIterator();
		int fnull = 0;
		while(it.hasNext()){
			 Element Node = (Element) it.next();
		if(Node.getName() =="CPFXGC") {
			  if(Node.attribute("value")!=null){
				 
				 String qwStr = Node.attributeValue("value");
				 String[] qwStrarray = qwStr.split("。|；");
				 for(String qw:qwStrarray){
					 if((qw.contains("祖母")||qw.contains("祖父")||qw.contains("爷爷")||
							 qw.contains("奶奶")||qw.contains("外公")||qw.contains("外婆"))&&(qw.contains("生活")||qw.contains("居住")||qw.contains("抚养")||
							 qw.contains("成长"))&&(!(qw.contains("房屋")||qw.contains("财产")))) {
						 fnull = 1 ;
						 Grandp = qw;
						 System.out.println(document.getName());
						 System.out.println(Grandp);
						
					 }
					
					
 		          }
              }
		  }
	 }
			   if(fnull == 0){
				   row30Node.addAttribute("value", "未提及");
			   }else{
				if(!(Grandp.equals("未考虑或不存在"))) {
					row30Node.addAttribute("value", "是");
					}
				else {
					row30Node.addAttribute("value", "否");
				}
			   }
	}
	
	/**
	 * 29-4.是否存在子女随其生活，对子女成长有利，而另一方患有久治不愈的传染性疾病或其他严重疾病，
	 *  或者有其他不利于子女身心健康的情形，不宜与子女共同生活的情形：  
	 * @param document
	 * @param newroot
	 */
	public static void row29_4(Document document, Element newroot){
		Element root = document.getRootElement();
		Element row29_4Node = newroot.addElement("YFBLYS").addAttribute("nameCN", "一方不利因素");
		String yfblys = "未提及或不考虑";//一方不利因素
		Iterator it = root.elementIterator();
		int fnull = 0;
		while(it.hasNext()){
			 Element Node = (Element) it.next();
		if(Node.getName() =="CPFXGC") {
			if(Node.attribute("value")!=null) {
				String qwStr = Node.attributeValue("value");
				String[] qwStrarray = qwStr.split("。|；");
				for(String qw:qwStrarray) {
					if((qw.contains("成长有利")||qw.contains("成长不利")
							||qw.contains("有利于孩子")||qw.contains("不利于孩子")
							||qw.contains("有利于子女")||qw.contains("有利于小孩")
							||qw.contains("由原告抚养为宜")||qw.contains("由被告抚养为宜")
							||(((qw.contains("有利于")||qw.contains("不利于"))&&qw.contains("身心健康"))))
							||((qw.contains("病")||qw.contains("患"))
							&&(((qw.contains("婚生")||qw.contains("双方"))&&(qw.contains("子")||qw.contains("女")))
							 &&(qw.contains("由")||qw.contains("跟")||qw.contains("随"))
							 &&(qw.contains("被告")||qw.contains("原告")||qw.contains("父亲")||qw.contains("母亲"))
							 &&(qw.contains("生活")||qw.contains("抚养")||qw.contains("抚育"))))){
						fnull = 1;
						System.out.println(document.getName());
						yfblys = qw;
						System.out.println(qw);
						if(!yfblys.equals("未提及或不考虑")) {
							Is29 = true;
							System.out.println("29-4");
							break;
						}
					}
					
				}
			}
		}
		}
		
			if(fnull == 0){
				row29_4Node.addAttribute("value","未提及");
			}else{
			if(!(yfblys.equals("未提及或不考虑"))) {
				row29_4Node.addAttribute("value","是");
			}
			else {
				row29_4Node.addAttribute("value","否");
			}
			}
		
	}
	/**
	 * 29-3.无其他子女，而另一方有其他子女的情形：
	 * @param document
	 * @param newroot
	 */
	public static void row29_3(Document document, Element newroot){
		Element root = document.getRootElement();
		Element row29_3Node = newroot.addElement("YFYQTZN").addAttribute("nameCN", "婚前有其他子女的因素");
		String yfyqtzn = "未提及或不考虑";//一方有其他子女
		Iterator it = root.elementIterator();
        int fnull = 0;
		while(it.hasNext()){
			 Element Node = (Element) it.next();
		if(Node.getName() =="CPFXGC") {
			if(Node.attribute("value")!=null) {
				String qwStr = Node.attributeValue("value");
				String[] qwStrarray = qwStr.split("。|；");
				for(String qw:qwStrarray) {
					if((qw.contains("婚前子女")||qw.contains("婚前所生"))
							&&(!qw.contains("离婚前子女"))&&(!qw.contains("离婚前所生"))){
						fnull=1;
						System.out.println(document.getName());
						yfyqtzn = qw;
						System.out.println(qw);
						if(!yfyqtzn.equals("未提及或不考虑")) {
							Is29 = true;
							System.out.println("29-3");
							break;
						}
					}
				}
			}
		}
		}
		 
	        if(fnull == 0){
	        	row29_3Node.addAttribute("value","未提及");
	        }else{
			if(!(yfyqtzn.equals("未提及或不考虑"))) {
				row29_3Node.addAttribute("value","是");
			}
			else {
				row29_3Node.addAttribute("value","否");
			}
	        }
	}
	/**
	 * 29-2.子女随其生活时间较长，改变生活环境对子女健康成长明显不利的情形
	 * @param document
	 * @param newroot
	 */
	public static void row29_2(Document document, Element newroot){
		Element root = document.getRootElement();
		String shhj = "未提及或不考虑";//生活环境因素
		Iterator it = root.elementIterator();
		int fnull = 0;
		while(it.hasNext()){
			 Element Node = (Element) it.next();
		if(Node.getName() =="CPFXGC") {
			if(Node.attribute("value")!=null) {
				String qwStr = Node.attributeValue("value");
				String[] qwStrarray = qwStr.split("。|；");
				for(String qw:qwStrarray) {
					if(qw.contains("改变")&&qw.contains("生活环境")
							&&(qw.contains("有利")||qw.contains("不利"))){
						fnull =1;
						System.out.println(document.getName());
						shhj = qw;
						System.out.println(qw);
						if(!shhj.equals("未提及或不考虑")) {
							Is29 = true;
							System.out.println("29-2");
							break;
						}
					}
				}
			}
		}
		}
		   
			Element row29_2Node = newroot.addElement("row29_2").addAttribute("nameCN", "生活环境因素");
			if(fnull == 0){
				row29_2Node.addAttribute("value","未提及");
			}else{
			if(!(shhj.equals("未提及或不考虑"))) {
				row29_2Node.addAttribute("value","是");
			}else {
				row29_2Node.addAttribute("value","否");
			}
			}
		
	}
	/**
	 * 29-1.已做绝育手术或因其他原因丧失生育能力的情形
	 * @param document
	 * @param newroot
	 */
	public static void row29_1(Document document, Element newroot){
		Element root = document.getRootElement();
		String jyys = "未提及或不考虑";//绝育因素
		Iterator it = root.elementIterator();
		int fnull = 0;
		while(it.hasNext()){
			 Element Node = (Element) it.next();
		if(Node.getName() =="CPFXGC") {
			if(Node.attribute("value")!=null) {
				String qwStr = Node.attributeValue("value");
				String[] qwStrarray = qwStr.split("。|；");
				for(String qw:qwStrarray) {
					if((qw.contains("生育能力")||(qw.contains("生育")&&qw.contains("疾病"))||qw.contains("无法生育")||qw.contains("绝育"))
							&&((qw.contains("婚生")||qw.contains("双方"))&&(qw.contains("子")||qw.contains("女"))
									&&(qw.contains("由")||qw.contains("跟")||qw.contains("随"))
							 &&(qw.contains("被告")||qw.contains("原告")||qw.contains("父亲")||qw.contains("母亲"))
							 &&(qw.contains("生活")||qw.contains("抚养")||qw.contains("抚育")))
							){
						fnull = 1;
						System.out.println(document.getName());
						jyys = qw;
						System.out.println(jyys);
						if(!jyys.equals("未提及或不考虑")) {
							Is29 = true;
							System.out.println("29-1");
							break;
						}
					}
				}
			}
		}
		}
		
			Element row29_1Node = newroot.addElement("JYYS").addAttribute("nameCN", "绝育因素");
			if(fnull == 0){
				row29_1Node.addAttribute("value","未提及");
			}else{
			if(!(jyys.equals("未提及或不考虑"))) row29_1Node.addAttribute("value","是");
			else row29_1Node.addAttribute("value","否");
			}
	}
	/**
	 * 29.对两周岁以上未成年的子女，是否存在可予优先考虑与一方生活的情形
	 * @param document
	 * @param newroot
	 */
	public static void row29(Document document, Element newroot){
		Element row29Node = newroot.addElement("YXGSYF").addAttribute("nameCN", "两周岁以上子女优先考虑抚养权的因素");
	    int fnull = 0;
		row29_1(document,newroot);
		row29_2(document,newroot);
		row29_3(document,newroot);
		row29_4(document,newroot);
		if(Is29 == true) {
			row29Node.addAttribute("value","是");
			System.out.println("存在********************************************");			
		}else{
			row29Node.addAttribute("value","否");
		}		
	    Is29 = false;
}
}
