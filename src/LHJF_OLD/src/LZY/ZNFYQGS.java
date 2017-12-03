package LHJF_OLD.src.LZY;

import org.dom4j.Document;
import org.dom4j.Element;

import java.util.List;
import java.util.regex.Pattern;

/*
 * 子女抚养权归属需查明的事实
 */
public class ZNFYQGS {
	/*
	 * 27.父母双方对十周岁以上的未成年子女随父或随母生活发生争执的，该子女的意见
	 */
	public static void row27(Document document, Element newroot){
	  Element root = document.getRootElement();
	  Element ZVYJNode = newroot.addElement("SZSYSZNYJ").addAttribute("nameCN", "父母双方对十周岁以上的未成年子女随父或随母生活发生争执的，该子女的意见");
	  Pattern pattern = Pattern.compile(JBSS.datePattern);
	  String xsdate ="";//相识时间
	  String rhxs = "未提及";
	  String zvyj = "未提及";
	  
	  if(root.element("CPFXGC")!=null){
	  Element cpfxgcNode = root.element("CPFXGC");
	  if(cpfxgcNode.attribute("value")!=null){
		 String qwStr = cpfxgcNode.attributeValue("value");
		 String[] qwStrarray = qwStr.split("。");
		 for(String qw:qwStrarray){
			 if((qw.contains("婚生")||qw.contains("原、被告")||qw.contains("双方"))&&(qw.contains("子")||qw.contains("女"))){
				 if((qw.contains("未超过")||qw.contains("未满")||qw.contains("不满")||qw.contains("不足")||qw.contains("未足")||qw.contains("以下"))&&qw.contains("十周岁")){
				    zvyj = "未超过十周岁";
				 }
				 else if(qw.contains("意见")||qw.contains("意愿")||qw.contains("愿意随")||qw.contains("愿随")){
					 {
						 String[] qwarrayList = qw.split("，");
						 for(String qwarray:qwarrayList){
							 int start=0;
							 int end=0;
							 if(qwarray.contains("随")&&qwarray.contains("生活")){
								 start = qwarray.indexOf("随");
								 end = qwarray.indexOf("生活");
									 
							 }	 
							 else if(qwarray.contains("由")&&qwarray.contains("抚养")){
								 start = qwarray.indexOf("由");
								 end = qwarray.indexOf("抚养");							 
							 }
							 if(start<end){
							    	 zvyj = qwarray.substring(start,end+2);
							    	
						     }
							    	 
							 }
						 
						 }
				 }
					 
				 }
				 			 
			 }
		 }
	  System.out.println(zvyj);
	  }
	  ZVYJNode.addAttribute("value",zvyj);
	  }
	
	/*
	 * 28.两周岁以下的子女，是否存在可随父方生活的情形
	 */
	public static void row28(Document document, Element newroot){
	Element root = document.getRootElement();
	Element LZSYXZNNode = newroot.addElement("LZSYXZNSFCZKSFFSH").addAttribute("nameCN", "两周岁以下的子女，是否存在可随父方生活的情形");
	String ifcz = "否";  
	String bgxb = "";//被告性别
	String ygxb = "";//原告性别
	int flag = 0 ;
	int flagy = 0;
	int flagb = 0;

	if(root.element("SSCYRQJ")!=null){//获取原被告性别
		Element SSCYRQJNode = root.element("SSCYRQJ");
		if(SSCYRQJNode.attribute("value")!=null){
			String[] sccyr = SSCYRQJNode.attributeValue("value").split("。");
			//System.out.println(SSCYRQJNode.attributeValue("value"));
			for(String cyrall:sccyr){
			    String cyr = cyrall.trim();
				if(cyr.startsWith("原告")||cyr.startsWith("被告")){
					flag = 0;
				if(cyr.startsWith("原告")){
					flag=1;
				}
				if(cyr.startsWith("被告")){
					flag=-1;
				}
				
				String[] cyrsplit = cyr.split("，");
				for(int i = 1;i<cyrsplit.length;i++){
					if((cyrsplit[i].contains("男")||cyrsplit[i].contains("女"))&&flag==1&&flagy!=1){
						ygxb = cyrsplit[i];
						flagy =1;
						break;
					}else if((cyrsplit[i].contains("男")||cyrsplit[i].contains("女"))&&flag==-1&&flagb!=1){
						bgxb = cyrsplit[i];
						flagb=1;
						break;
					}						
				}					 
				}
			//	System.out.println("y"+ygxb);
			//    System.out.println("b"+bgxb);
			}
		}
		LZSYXZNNode.addAttribute("value",ifcz);
	}
	  if(root.element("CPFXGC")!=null){
	  Element cpfxgcNode = root.element("CPFXGC");
	  if(cpfxgcNode.attribute("value")!=null){
		 String qwStr = cpfxgcNode.attributeValue("value");
		 String[] qwStrarray = qwStr.split("。");
		 for(String qw:qwStrarray){
			 if((qw.contains("婚生")||qw.contains("原、被告")||qw.contains("双方"))&&(qw.contains("子")||qw.contains("女"))){
			//	 System.out.println("all"+qw);
				 if((qw.contains("两周岁")||qw.contains("两岁"))&&(qw.contains("未满")&&qw.contains("不满")||qw.contains("不足")||qw.contains("未足")||qw.contains("以下"))){				 
					 int start=0;
					 int end=0;
					 String sub = "";
					 if(qw.contains("随")&&qw.contains("生活")){
						 start = qw.indexOf("随");
						 end = qw.indexOf("生活");
							 
					 }	 
					 else if(qw.contains("由")&&qw.contains("抚养")){
						 start = qw.indexOf("由");
						 end = qw.indexOf("抚养");							 
					 }
					 if(start<end){
						 sub = qw.substring(start,end+2);
					    	
				     }else{
				    	 sub =qw;
				     }
					 if(sub.contains("父")){
						 ifcz = "是";
						 System.out.println("row28"+ifcz);
						 
					 }
					 else if(sub.contains("原告")&&ygxb.contains("男")){
						 ifcz = "是";
						 System.out.println("row28"+ifcz);
					 }
					 else if(sub.contains("被告")&&bgxb.contains("男")){
						 ifcz = "是";
						 System.out.println("row28"+ifcz);
					 }
				//	  System.out.println(document.getName());
					 break;
					 
				 }
					 
				 }
				 			 
			 }
		 }
	
	  }
	 
	  }
	 /*
	  * 28-1.母方患有久治不愈的传染性疾病或其他严重疾病，子女不宜与其共同生活的情形：
	  */
	public static void row281(Document document, Element newroot){
		Element root = document.getRootElement();
		Element YZJBNode = newroot.addElement("MFYZJB").addAttribute("nameCN", "母方患有久治不愈的传染性疾病或其他严重疾病，子女不宜与其共同生活的情形");
		String bgxb = "";//被告性别
		String ygxb = "";//原告性别
		int flag = 0 ;
		int flagy = 0;
		int flagb = 0;
		int flagzvnl = 0;
		String ifcc = "否";
		//是否子女两周岁以下
		 if(root.element("CPFXGC")!=null){
			  Element cpfxgcNode = root.element("CPFXGC");
			  if(cpfxgcNode.attribute("value")!=null){
				 String qwStr = cpfxgcNode.attributeValue("value");
				 String[] qwStrarray = qwStr.split("。");
				 for(String qw:qwStrarray){
					 if((qw.contains("婚生")||qw.contains("原、被告")||qw.contains("双方"))&&(qw.contains("子")||qw.contains("女"))){
					//	 System.out.println("all"+qw);
						 if((qw.contains("两周岁")||qw.contains("两岁"))&&(qw.contains("未满")&&qw.contains("不满")||qw.contains("不足")||qw.contains("未足")||qw.contains("以下"))){
							 flagzvnl =1;
						 }
					 }
						 }
			  }
		 }
		
		if(flagzvnl == 1){
		
		if(root.element("SSCYRQJ")!=null){//获取原被告性别
			Element SSCYRQJNode = root.element("SSCYRQJ");
			if(SSCYRQJNode.attribute("value")!=null){
				String[] sccyr = SSCYRQJNode.attributeValue("value").split("。");
				//System.out.println(SSCYRQJNode.attributeValue("value"));
				for(String cyrall:sccyr){
				    String cyr = cyrall.trim();
				    if(cyr.startsWith("原告")||cyr.startsWith("被告")){
						flag = 0;
					if(cyr.startsWith("原告")){
						flag=1;
					}
					if(cyr.startsWith("被告")){
						flag=-1;
					}
					
					String[] cyrsplit = cyr.split("，");
					for(int i = 1;i<cyrsplit.length;i++){
						if((cyrsplit[i].contains("男")||cyrsplit[i].contains("女"))&&flag==1&&flagy!=1){
							ygxb = cyrsplit[i];
							flagy =1;
							break;
						}else if((cyrsplit[i].contains("男")||cyrsplit[i].contains("女"))&&flag==-1&&flagb!=1){
							bgxb = cyrsplit[i];
							flagb=1;
							break;
						}						
					}					 
					}
				//	System.out.println("y"+ygxb);
				//    System.out.println("b"+bgxb);
				}
			}
		}
		String flagsex ="XXX";
		if(bgxb.contains("女")){
			flagsex = "被告";
		}else if(ygxb.contains("女")){
			flagsex = "原告";
		}
	//	System.out.println("flag"+flagsex);
		// System.out.println(flagsex);
	//	 System.out.println(ygxb);
	//	 System.out.println(bgxb);
	//	 System.out.println(document.getName());
		String alljd = "";
		//首先判断母方是否患病
		if(root.element("CPFXGC")!=null){
			  Element cpfxgcNode = root.element("CPFXGC");
			  if(cpfxgcNode.attribute("value")!=null){
				 String qwStr = cpfxgcNode.attributeValue("value");
				 alljd +=qwStr;
			
	        	}
     	}	 		 
		 if(root.element("AJJBQK")!=null&&flag!=2){
       	  Element ajjbqkNode = root.element("AJJBQK");
       	  if(ajjbqkNode.element("CMSSD")!=null){
       		  List<Element> cmssdNode = ajjbqkNode.elements("CMSSD");
       		  for(Element e:cmssdNode){
   	          if(e.attribute("value")!=null){  
   	        	  String qwStr = e.attributeValue("value");
   	        	  alljd += qwStr;
   	        	  }
   	          }
       		  }
       	  }
		 String[] qwStrarray = alljd.split("，|。|；|、");
		 for(String qw:qwStrarray){ //判断母方是否患病
			 if((qw.contains("病")||qw.contains("症"))&&qw.contains(flagsex)){
				 flag =2;
				 break;
			 }
		 }
		 qwStrarray = alljd.split("。");
		 if(flag ==2){	
			// System.out.println(flagsex);
			// System.out.println(ygxb);
			// System.out.println(bgxb);
			// System.out.println(document.getName());
			 for(String qw:qwStrarray){
				 if((qw.contains("孩子")||qw.contains("子")||qw.contains("女"))&&
					(qw.contains("抚育")||qw.contains("抚养"))&&(qw.contains("病")||qw.contains("症"))&&(
					 qw.contains("不宜")||qw.contains("不利"))){
					 ifcc = "是";
					
					 System.out.println("row28-1"+ifcc);
					 
					 break;
					
				 }
			 }
		 }
		}
		YZJBNode.addAttribute("value",ifcc);
		
	
}
	/*
	 * 28-2.母方有抚养条件不尽抚养义务，而父方要求子女随其生活的情形
	 */

    public static void row282(Document document, Element newroot){
    	Element root = document.getRootElement();
    	Element MFBJFYYWNode = newroot.addElement("MFYFYTJBJFYYW").addAttribute("nameCN", "母方有抚养条件不尽抚养义务，而父方要求子女随其生活的情形");
		String bgxb = "";//被告性别
		String ygxb = "";//原告性别
		int flag = 0 ;
		int flagy = 0;
		int flagb = 0;
		int flagzvnl = 0;
		String ifcc = "否";
		//是否子女两周岁以下
		 if(root.element("CPFXGC")!=null){
			  Element cpfxgcNode = root.element("CPFXGC");
			  if(cpfxgcNode.attribute("value")!=null){
				 String qwStr = cpfxgcNode.attributeValue("value");
				 String[] qwStrarray = qwStr.split("。");
				 for(String qw:qwStrarray){
					 if((qw.contains("婚生")||qw.contains("原、被告")||qw.contains("双方"))&&(qw.contains("子")||qw.contains("女"))){
					//	 System.out.println("all"+qw);
						 if((qw.contains("两周岁")||qw.contains("两岁"))&&(qw.contains("未满")&&qw.contains("不满")||qw.contains("不足")||qw.contains("未足")||qw.contains("以下"))){
							 flagzvnl =1;
						 }
					 }
						 }
			  }
		 }
		
		if(flagzvnl == 1){
		if(root.element("SSCYRQJ")!=null){//获取原被告性别
			Element SSCYRQJNode = root.element("SSCYRQJ");
			if(SSCYRQJNode.attribute("value")!=null){
				String[] sccyr = SSCYRQJNode.attributeValue("value").split("。");
				//System.out.println(SSCYRQJNode.attributeValue("value"));
				for(String cyrall:sccyr){
				    String cyr = cyrall.trim();
				    if(cyr.startsWith("原告")||cyr.startsWith("被告")){
						flag = 0;
					if(cyr.startsWith("原告")){
						flag=1;
					}
					if(cyr.startsWith("被告")){
						flag=-1;
					}
					
					String[] cyrsplit = cyr.split("，");
					for(int i = 1;i<cyrsplit.length;i++){
						if((cyrsplit[i].contains("男")||cyrsplit[i].contains("女"))&&flag==1&&flagy!=1){
							ygxb = cyrsplit[i];
							flagy =1;
							break;
						}else if((cyrsplit[i].contains("男")||cyrsplit[i].contains("女"))&&flag==-1&&flagb!=1){
							bgxb = cyrsplit[i];
							flagb=1;
							break;
						}						
					}					 
					}
				//	System.out.println("y"+ygxb);
				//    System.out.println("b"+bgxb);
				}
			}
		}
		String flagsex ="";
		String uflagsex = "";
		if(bgxb.contains("女")){
			flagsex = "被告";
			uflagsex = "原告";
		}else if(ygxb.contains("女")){
			flagsex = "原告";
			uflagsex = "被告";
		}
	
		int flagq =0;
		//首先看母亲有没有尽到抚养义务
		if(root.element("CPFXGC")!=null){
			  Element cpfxgcNode = root.element("CPFXGC");
			  if(cpfxgcNode.attribute("value")!=null){
				 String qwStr = cpfxgcNode.attributeValue("value");
				   String[] qwStrarray = qwStr.split("。|，");
					for(String qw:qwStrarray){
						if((qw.contains(flagsex)||qw.contains("母"))&&qw.contains("抚养义务")
							&&(qw.contains("不履行")||qw.contains("未尽")||qw.contains("未履行"))){
							int d1 = 10000;
							int d2 = 10000;
							int d3 = 10000;
							int dt = 10000;
							int d = qw.indexOf("抚养");
							
							if(qw.contains("原告")){
								d1 = Math.abs(qw.indexOf("原告")-d);
							}
							if(qw.contains("被告")){
								d2 =  Math.abs(qw.indexOf("被告")-d);
							}
							if(qw.contains(flagsex)){
								dt =  Math.abs(qw.indexOf(flagsex)-d);
							}
							if(qw.contains("母亲")){
								d3 =  Math.abs(qw.indexOf("母亲")-d);
							}
							if(dt<=Math.min(d1, d2)||d3<=Math.min(d1, d2)){
							
							
							flagq =1 ;
							}
						}
					}
			
	        	}
   	}
		if(flagq ==1){
		  String nodeStr = "";
		  if(uflagsex.equals("原告")){
			  nodeStr = "YGSCD";
		  }else{
			  nodeStr = "BGBCD";
		  }
		  if(root.element("AJJBQK")!=null){
			  Element ajNode = root.element("AJJBQK");
			  if(ajNode.element(nodeStr)!=null){
				  Element node = ajNode.element(nodeStr);
				  if(node.attribute("value")!=null){
					  String[] nodeStrall = node.attributeValue("value").split("，|。");
					  for(String str:nodeStrall){
						  if((str.contains("婚生")||str.contains("原、被告")|| str.contains("双方"))&&
							 (str.contains("子")||str.contains("女"))&&(str.contains("抚养")||str.contains("生活"))){
							  if(str.contains("本人")||str.contains("自己")||str.contains("我")||str.contains(uflagsex)||str.contains("父")){
								  ifcc ="是";
								  System.out.println(document.getName());
							  }
						  }
					  }
				  }
			  }
		  }
		}
		}
		MFBJFYYWNode.addAttribute("value",ifcc);
    }
 /*
  * 28-3.因其他原因，子女确无法随母方生活的情形
  */
    public static void row283(Document document, Element newroot){
    	Element root = document.getRootElement();
    	Element QTYYNode = newroot.addElement("YQTYYZVWFSMFSH").addAttribute("nameCN", "因其他原因，子女确无法随母方生活的情形");
		String bgxb = "";//被告性别
		String ygxb = "";//原告性别
		int flag = 0 ;
		int flagy = 0;
		int flagb = 0;
		int flagzvnl = 0;
		String ifcc = "否";
		 if(root.element("CPFXGC")!=null){
			  Element cpfxgcNode = root.element("CPFXGC");
			  if(cpfxgcNode.attribute("value")!=null){
				 String qwStr = cpfxgcNode.attributeValue("value");
				 String[] qwStrarray = qwStr.split("。");
				 for(String qw:qwStrarray){
					 if((qw.contains("婚生")||qw.contains("原、被告")||qw.contains("双方"))&&(qw.contains("子")||qw.contains("女"))){
					//	 System.out.println("all"+qw);
						 if((qw.contains("两周岁")||qw.contains("两岁"))&&(qw.contains("未满")&&qw.contains("不满")||qw.contains("不足")||qw.contains("未足")||qw.contains("以下"))){
							 flagzvnl =1;
						 }
					 }
						 }
			  }
		 }
		
		if(flagzvnl == 1){
		if(root.element("SSCYRQJ")!=null){//获取原被告性别
			Element SSCYRQJNode = root.element("SSCYRQJ");
			if(SSCYRQJNode.attribute("value")!=null){
				String[] sccyr = SSCYRQJNode.attributeValue("value").split("。");
				//System.out.println(SSCYRQJNode.attributeValue("value"));
				for(String cyrall:sccyr){
				    String cyr = cyrall.trim();
				    if(cyr.startsWith("原告")||cyr.startsWith("被告")){
						flag = 0;
					if(cyr.startsWith("原告")){
						flag=1;
					}
					if(cyr.startsWith("被告")){
						flag=-1;
					}					
					String[] cyrsplit = cyr.split("，");
					for(int i = 1;i<cyrsplit.length;i++){
						if((cyrsplit[i].contains("男")||cyrsplit[i].contains("女"))&&flag==1&&flagy!=1){
							ygxb = cyrsplit[i];
							flagy =1;
							break;
						}else if((cyrsplit[i].contains("男")||cyrsplit[i].contains("女"))&&flag==-1&&flagb!=1){
							bgxb = cyrsplit[i];
							flagb=1;
							break;
						}						
					}					 
					}
				//	System.out.println("y"+ygxb);
				//    System.out.println("b"+bgxb);
				}
			}
		}
		String flagsex ="";
		String uflagsex = "";
		if(bgxb.contains("女")){
			flagsex = "被告";
			uflagsex = "原告";
		}else if(ygxb.contains("女")){
			flagsex = "原告";
			uflagsex = "被告";
		}
		
		 if(root.element("CPFXGC")!=null){
			  Element cpfxgcNode = root.element("CPFXGC");
			  if(cpfxgcNode.attribute("value")!=null){
				 String qwStr = cpfxgcNode.attributeValue("value");
				 String[] qwStrarray = qwStr.split("。|，");
				 for(String qw:qwStrarray){
					 if((qw.contains("婚生")||qw.contains("原、被告")|| qw.contains("双方"))&&
							 (qw.contains("子")||qw.contains("女"))
							 &&(qw.contains("不随")||qw.contains("不由")||qw.contains("无法随"))&&
							 (qw.contains("母")||qw.contains(flagsex))){
						 ifcc = "是";
						 System.out.println(qw);
					 }
				 }
				 }
		 }
		
		}
		QTYYNode.addAttribute("value",ifcc);
    }
    /*
     * 25.起诉时子女生活、学习现状
     */
  
    public static void row25(Document document, Element newroot){
    	Element root = document.getRootElement();
    	Element ZNZKNode = newroot.addElement("QSSZNSHXXXZ").addAttribute("nameCN", "起诉时子女生活、学习现状");
    	String allStr ="";
    	if(root.element("AJJBQK")!=null){
      	  Element ajjbqkNode = root.element("AJJBQK");
      	  if(ajjbqkNode.element("CMSSD")!=null){
      		  List<Element> cmssdNode = ajjbqkNode.elements("CMSSD");
      		  for(Element e:cmssdNode){
  	          if(e.attribute("value")!=null){   	        	  
  	        	 String qwStr = e.attributeValue("value");
  	        	 String[] qwStrarray = qwStr.split("。");
  	        	
  	        	 	        	 
  	        	 for(String qw:qwStrarray){
  	        		 int flag = 0; 
  	        		 String[] qwlist = qw.split("，");
  	        		 for(String qwlistsp:qwlist){
  	        		 if((qwlistsp.contains("婚生")||qwlistsp.contains("原、被告")||qwlistsp.contains("原告之")||qwlistsp.contains("被告之")||qwlistsp.contains("双方")||qwlistsp.contains("生"))&&(qwlistsp.contains("子")||qwlistsp.contains("女"))){       			 	        			
  	        		    flag  = 1;
  	        		    allStr += qwlistsp+"。"; 
  	        		    
  	        		 }else if(qwlistsp.contains("现")){
  	        			if(flag == 1){
  	        		    	allStr += qwlistsp;
  	        		    	flag = 0;
  	        		    }
  	        		 }
  	        		 }
  	        		 
  	        	 }
  	        	 System.out.println(allStr);
  	        	
  	          }
      		  }
                	}
     		
    	}
    	if(allStr.equals("")){
    		ZNZKNode.addAttribute("value","未提及");
    	}else{
    		ZNZKNode.addAttribute("value",allStr);
    	}
    }
    
}
