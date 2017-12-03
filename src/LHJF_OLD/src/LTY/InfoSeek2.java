package LHJF_OLD.src.LTY;

import LHJF_OLD.src.LZY.TimePro;
import org.dom4j.Attribute;
import org.dom4j.Document;
import org.dom4j.Element;

import java.util.ArrayList;
import java.util.List;

public class InfoSeek2 {
	//14.是否存在一方曾起诉离婚，经法院处理后夫妻感情未改善的情形
		public static void row14(Document document, Element newroot){
			Element sfcqslhNode=newroot.addElement("SFCQSLH");
			sfcqslhNode.addAttribute("nameCN", "是否曾起诉离婚，经法院处理后夫妻感情未改善的情形");
			int fnull = 0;
			Element root = document.getRootElement();
	  	  	String ifcz = "否";
	  	  	int flag = 0 ;
	  	  	if(root.element("AJJBQK")!=null){//案件基本情况
	  	  		Element ajjbqkNode = root.element("AJJBQK");
	  	  		if(ajjbqkNode.element("CMSSD")!=null){//查明事实段
	  	  			List<Element> cmssdNode = ajjbqkNode.elements("CMSSD");
	  	  			for(Element e:cmssdNode){
	  	  				if(e.attribute("value")!=null){ 	        	  
	  	  					String[]  qwStrarray= e.attributeValue("value").split("。");
	  	  					//是否曾起诉离婚
	  	  					for(String qw:qwStrarray){
	  	  						if(qw.contains("离婚")&&(qw.contains("起诉")||qw.contains("诉讼")||qw.contains("诉至"))&&!qw.contains("撤诉")){
	  	  							fnull = 1;
	  	        			
	  	  							if(qw.contains("曾")||qw.contains("次")||qw.contains("驳回")||qw.contains("判决")||qw.contains("调解")||qw.contains("不准")){
	  	  								flag =1;
	  	  								break;
	  	  							}
	  	  						}
	  	  					}     	 
	  	  					//是否关系未改善
	  	  					if(flag == 1 ){
	  	  						qwStrarray= e.attributeValue("value").split("。|，|；");
	  	  						for(String qw:qwStrarray){
	  	  							if(((qw.contains("感情")||qw.contains("关系"))&&(qw.contains("破裂")||(qw.contains("未")&&qw.contains("改善"))))||(qw.contains("共同生活")&&qw.contains("无法"))){
	  	  								ifcz = "是";
	  	  							}
	  	  						}
	  	  					}
	  	  				}
	  	  			}
	  	  		}
	  	  	}
	  	  	if(fnull == 0){
	  	  	sfcqslhNode.addAttribute("value","未提及");
	  	  	}else{
	  	  	sfcqslhNode.addAttribute("value",ifcz);
	  	  	}
	    }
		//17.是否存在一方患有法定禁止结婚疾病的，或一方有生理缺陷，或其它原因不能发生性行为，且难以治愈的情形
		public static void sfhyjbqx(Document document, Element newroot){
			Element sfhyjbqxNode=newroot.addElement("SFHYJBQX");
			sfhyjbqxNode.addAttribute("nameCN", "是否存在一方患有法定禁止结婚疾病的，或一方有生理缺陷，或其它原因不能发生性行为，且难以治愈的情形");
			String sf="否";
			int fnull = 0 ;
			Element root=document.getRootElement();
			if(root.element("AJJBQK")!=null){
				Element ajjbqkNode=root.element("AJJBQK");
				if(ajjbqkNode.element("CMSSD")!=null){
					List<Element> cmssdNode=ajjbqkNode.elements("CMSSD");
					for(Element e:cmssdNode){
						if(e.attribute("value")!=null){
							String[] qwStr=e.attributeValue("value").split("。");
							/*for(String qw:qwStr){
								if(((qw.contains("遗传")||qw.contains("传染")||qw.contains("精神")||qw.contains("性")
										||qw.contains("艾滋")||qw.contains("淋"))&&qw.contains("病"))
										||qw.contains("生理缺陷")
										||((qw.contains("无法")||qw.contains("不能"))&&qw.contains("性行为"))
											&&((qw.contains("治愈")||qw.contains("恢复"))&&(qw.contains("无法")||qw.contains("不能")))){
									sf="存在";
								}
							}*/
							for(String qw:qwStr){
								if(((qw.contains("遗传")||qw.contains("传染")||qw.contains("精神")||qw.contains("性")
										||qw.contains("艾滋")||qw.contains("淋"))&&qw.contains("病"))){
									fnull = 1;
									sf="是";
								}else if(qw.contains("生理缺陷")){
									sf="是";
									fnull = 1;
								}else if((qw.contains("无法")||qw.contains("不能"))&&qw.contains("性行为")){
									fnull = 1;
									if((qw.contains("治愈")||qw.contains("恢复"))&&(qw.contains("无法")||qw.contains("不能"))){
										sf="是";
										
									}
								}
							}
						}
					}
				}
			}
			if(fnull == 0){
				sfhyjbqxNode.addAttribute("value","未提及");
			}else{
			sfhyjbqxNode.addAttribute("value",sf);
			}
		}
		
		//18.是否存在婚前隐瞒了精神病，婚后经治不愈，或者婚前知道对方患有精神病而与其结婚，或一方在夫妻共同生活期间患精神病，久治不愈的情形
		
		//19.是否一方欺骗对方，或者在结婚登记时弄虚作假，骗取《结婚证》的情形
		public static void sfqpzj(Document document, Element newroot){
			Element sfqpzjNode=newroot.addElement("SFQPZJ");
			sfqpzjNode.addAttribute("nameCN", "是否一方欺骗对方，或者在结婚登记时弄虚作假，骗取《结婚证》的情形");
			String sf="否";
			int fnull = 0;
			Element root=document.getRootElement();
			if(root.element("AJJBQK")!=null){
				Element ajjbqkNode=root.element("AJJBQK");
				if(ajjbqkNode.element("CMSSD")!=null){
					List<Element> cmssdNode=ajjbqkNode.elements("CMSSD");
					for(Element e:cmssdNode){
						if(e.attribute("value")!=null){
							String[] qwStr=e.attributeValue("value").split("。");
							for(String qw:qwStr){
								if((qw.contains("结婚登记")||qw.contains("信息")||qw.contains("身份"))){
									fnull = 1 ;
								if((qw.contains("欺骗")||qw.contains("哄骗")||qw.contains("诱骗"))
										||
										(qw.contains("作假")||qw.contains("造假"))&&(qw.contains("结婚登记")||qw.contains("信息")||qw.contains("身份"))){
									sf="是";
									
								}
							}
							}
						}
					}
				}
			}
			if(fnull == 0){
			sfqpzjNode.addAttribute("value","未提及");
			}else{
			sfqpzjNode.addAttribute("value",sf);
			}
		}
		//20.是否存在双方办理结婚登记后，未同居生活，无和好可能的情形
		public static void sfwtjwhh(Document document, Element newroot){
			Element sfwtjwhhNode=newroot.addElement("SFWTJWHH");
			sfwtjwhhNode.addAttribute("nameCN", "是否存在双方办理结婚登记后，未同居生活，无和好可能的情形");
			String sf="否";
			int fnull = 0;
			Element root=document.getRootElement();
			if(root.element("AJJBQK")!=null){
				Element ajjbqkNode=root.element("AJJBQK");
				if(ajjbqkNode.element("CMSSD")!=null){
					List<Element> cmssdNode=ajjbqkNode.elements("CMSSD");
					for(Element e:cmssdNode){
						if(e.attribute("value")!=null){
							String[] qwStr=e.attributeValue("value").split("。");
							for(String qw:qwStr){
								
								if((qw.contains("同居")||qw.contains("居住")||((qw.contains("共同")
										||qw.contains("一起"))&&qw.contains("生活")))&&(qw.contains("未")||qw.contains("没"))
										||((qw.contains("和好")||qw.contains("复合"))&&(qw.contains("无")||qw.contains("没"))&&qw.contains("可能"))){
									fnull = 1;
									if(qw.contains("办理结婚登记后")){
										sf="存在";
									}	
								}
							}
						}
					}
				}
			}
			if(fnull == 0){
				sfwtjwhhNode.addAttribute("value","未提及");
			}else{
			sfwtjwhhNode.addAttribute("value",sf);
			}
		}
		//21.是否存在包办、买卖婚姻、婚后一方随即提出离婚，或者虽共同生活多年，但确未建立起夫妻感情的情形
		public static void sfbbmbhy(Document document, Element newroot){
			Element sfbbmbhyNode=newroot.addElement("SFBBMBHY");
			sfbbmbhyNode.addAttribute("nameCN", "是否存在包办、买卖婚姻、婚后一方随即提出离婚，或者虽共同生活多年，但确未建立起夫妻感情的情形");
			String ifbbmb="否";
			int fnull = 0;
			Element root=document.getRootElement();
			if(root.element("AJJBQK")!=null){
				Element ajjbqkNode=root.element("AJJBQK");
				if(ajjbqkNode.element("CMSSD")!=null){
					List<Element> cmssdNode=ajjbqkNode.elements("CMSSD");
					for(Element e:cmssdNode){
						if(e.attribute("value")!=null){
							String[]  qwStrarray= e.attributeValue("value").split("。");
							for(String qw:qwStrarray){
								if(qw.contains("婚姻")&&(qw.contains("包办")||qw.contains("买办"))){
									fnull=1;
								if(qw.contains("婚姻")&&(qw.contains("包办")||qw.contains("买办"))
										||qw.contains("离婚")&&(qw.contains("立刻")||qw.contains("马上")||qw.contains("随即"))
										||((qw.contains("感情")||qw.contains("关系"))&&qw.contains("未确立"))){
									ifbbmb="存在";
								}
								}
							}
						}
					}
				}
			}
			if(fnull == 0){
				sfbbmbhyNode.addAttribute("value","未提及");
			}else{
			sfbbmbhyNode.addAttribute("value",ifbbmb);
			}
		}
		//22.起诉时女方是否怀孕或者哺乳期
		public static void sfhybrq(Document document, Element newroot){
			Element sfhybrqNode=newroot.addElement("SFHYBRQ");
			sfhybrqNode.addAttribute("nameCN", "起诉时女方是否怀孕或者哺乳期");
			String ifhy="否";
			int fnull = 0;
			Element root=document.getRootElement();
			if(root.element("AJJBQK")!=null){
				Element ajjbqkNode = root.element("AJJBQK");
	  	  		if(ajjbqkNode.element("CMSSD")!=null){//查明事实段
	  	  			List<Element> cmssdNode = ajjbqkNode.elements("CMSSD");
	  	  			for(Element e:cmssdNode){
	  	  				if(e.attribute("value")!=null){ 	        	  
	  	  					String[]  qwStrarray= e.attributeValue("value").split("。");
	  	  					//起诉时是否怀孕或者哺乳期
	  	  					for(String qw:qwStrarray){
	  	  						if(qw.contains("处")||qw.contains("正")&&(qw.contains("怀孕")||qw.contains("哺乳期"))){
	  	  							fnull = 1;
	  	  							if(qw.contains("诉讼")&&(qw.contains("期")||qw.contains("间"))){
	  	  								ifhy="是";
	  	  								break;
	  	  							}
	  	  						}
	  	  					}
	  	  				}
	  	  			}
	  	  		}
			}
			if(fnull == 0){
				sfhybrqNode.addAttribute("value","未提及");
			}else{
			sfhybrqNode.addAttribute("value",ifhy);
			}
		}
		
		//24.生育子女情况
		public static void syznqk(Document document, Element newroot){
			String znqk="";
			Element syznqkNode=newroot.addElement("SYZNQK");
			syznqkNode.addAttribute("nameCN", "生育子女情况");
			int fnull=0;	
			Element root=document.getRootElement();
			String text=root.getName();
			System.out.println(text);
			if(text=="QW"){
				List<Attribute> qwAttr=root.attributes();
				for(Attribute attr:qwAttr){
					if(attr.getName()=="value"){
						ArrayList<String> li=new ArrayList();
						String qwValue=attr.getValue();
						String[] qwValues=qwValue.split("，|。|；");
						//for(int i=0;i<qwValues.length;i++)
							//System.out.println(qwValues[i]);
						for(int i=0;i<qwValues.length;i++){
							if((qwValues[i].contains("生")||qwValues[i].contains("育")||qwValues[i].contains("原、被告")
									||qwValues[i].contains("婚生")||qwValues[i].contains("双方"))&&(qwValues[i].contains("子")||qwValues[i].contains("女")||qwValues[i].contains("男")||qwValues[i].contains("女孩"))){
								String[] keywords={"生","育","双方"};
								fnull = 1;
								//System.out.println("有");
								for(int j=0;j<keywords.length;j++){
									//System.out.println("有");
									int start=qwValues[i].indexOf(keywords[j]);
									//System.out.println(start);
									znqk=qwValues[i].substring(start+1);
									li.add(znqk);
									
								}			
							}else if(qwValues[i].contains("未生育")||qwValues[i].contains("未育有")){
								li.add("无子女");
								fnull = 1;
							}
						}
						
						TimePro.removeDuplicateWithOrder(li);
						String syznqk=li.toString();						
						syznqkNode.addAttribute("value",syznqk);
					}
				}
			}
			if(fnull == 0){
				syznqkNode.addAttribute("value","未提及");
			}
		}

		//26.子女有无重大疾病或其他特殊情况
		public static void zntsqk(Document document, Element newroot)
		{
			Element zntsqkNode=newroot.addElement("ZNTSQK");
			zntsqkNode.addAttribute("nameCN", "子女有无重大疾病或其他特殊情况");
			String ifhb="否";
			int fnull=0;
			Element root=document.getRootElement();
			if(root.element("AJJBQK")!=null){
				Element ajjbqkNode = root.element("AJJBQK");
	  	  		if(ajjbqkNode.element("CMSSD")!=null){//查明事实段
	  	  			List<Element> cmssdNode = ajjbqkNode.elements("CMSSD");
	  	  			for(Element e:cmssdNode){
	  	  				if(e.attribute("value")!=null){ 	        	  
	  	  					String[]  qwStrarray= e.attributeValue("value").split("。");
	  	  					//子女有无重大疾病或其他特殊情况
	  	  					for(String qw:qwStrarray){
	  	  						if(qw.contains("患有")||qw.contains("患病")||qw.contains("事故")
	  	  								||qw.contains("病情")||qw.contains("遭遇")||qw.contains("遇到")){
	  	  							fnull = 1;
	  	  							if(qw.contains("子")||qw.contains("女")){
	  	  								ifhb=qw.toString();
	  	  							}
	  	  						}
	  	  					}
	  	  				}
	  	  			}
	  	  		}
			}
			if(fnull == 0){
				zntsqkNode.addAttribute("value","未提及");
			}else{
			zntsqkNode.addAttribute("value",ifhb);
			}
		}

}
