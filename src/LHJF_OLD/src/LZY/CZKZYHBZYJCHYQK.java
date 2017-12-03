package LHJF_OLD.src.LZY;

import org.dom4j.Document;
import org.dom4j.Element;

import java.text.ParseException;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

/*
 * 存在可准予或不准予解除婚姻关系的情形
 * 不知道逻辑对不对，
 */
public class CZKZYHBZYJCHYQK {
	/*
	 * 8.是否存在婚前缺乏了解，草率结婚，婚后未建立起夫妻感情，难以共同生活的情形
	 */
      public static void row8(Document document, Element newroot){
    	  Element root = document.getRootElement();
    	  Element HQQFLJNode = newroot.addElement("HQQFLJ").addAttribute("nameCN", "是否存在婚前缺乏了解，草率结婚，婚后未建立起夫妻感情，难以共同生活的情形");
          String JD ="否";
          int flagq = 0;
          int flagh = 0;
    	  if(root.attribute("value")!=null){
    		 String qwStr = root.attributeValue("value");
    		 String[] qwStrarray = qwStr.split("，|。");
    		 for(String qw:qwStrarray){   			
    			 if((qw.contains("婚前")&&(qw.contains("缺乏")||qw.contains("缺少"))&&qw.contains("了解"))||qw.contains("草率结婚")){
    				 System.out.println(qw);
    				 flagq =1;    				 
    			 }
    			 if(((qw.contains("未")||qw.contains("不能")||qw.contains("没有"))&&qw.contains("建立")&&qw.contains("夫妻感情"))||
    				((qw.contains("难以")||qw.contains("无法")||qw.contains("不能"))&&(qw.contains("共同生活")||qw.contains("与被告生活")||qw.contains("与原告生活")))){
    				 flagh =1;   
    				 System.out.println(qw);
    		     }
    	  }
    		 if(flagq*flagh==1){
    			 JD = "是";
    		 }
         }
    	  System.out.println(JD);
    	  if(flagq+flagh == 0){
    		  HQQFLJNode.addAttribute("value","未提及");
    	  }else{
    	  HQQFLJNode.addAttribute("value",JD);
    	  }
      }
      
      /*
       * 9.是否存在性格不合，经常发生矛盾，难以共同生活的情形
       */
      public static void row9(Document document, Element newroot){
    	  Element root = document.getRootElement();
    	  Element XGBHNode = newroot.addElement("XGBH").addAttribute("NameCN", "是否存在性格不合，经常发生矛盾，难以共同生活的情形");
          String JD ="否";
          int flagq = 0;
          int flagh = 0;
          
    	  if(root.attribute("value")!=null){
    		 String qwStr = root.attributeValue("value");
    		 String[] qwStrarray = qwStr.split("，|。");
    		 for(String qw:qwStrarray){   			
    			 if((qw.contains("性格")&&(qw.contains("不和")||qw.contains("不合")||
    				 qw.contains("差异")||qw.contains("不同")||qw.contains("不投")))&&
    				 (qw.contains("矛盾")||qw.contains("争执")||
    				 qw.contains("不睦")||qw.contains("不和")||
      				 qw.contains("争吵")||qw.contains("殴打")||qw.contains("虐待")||
      				 qw.contains("暴力")||qw.contains("吵打")||qw.contains("打骂") )){
    				 System.out.println(qw);
    				 flagq =1;    				 
    			 }
    			 if(((qw.contains("难以")||qw.contains("无法")||qw.contains("不能"))&&(qw.contains("共同生活")||qw.contains("与被告生活")||qw.contains("与原告生活")))){
    				 flagh =1;   
    				 System.out.println(qw);
    		     }
    	  }
    		 if(flagq*flagh==1){
    			 JD = "是";
    		 }
         }
    	  System.out.println(JD);
    	  if(flagq+flagh == 0){
    		  XGBHNode.addAttribute("value","未提及");  
    	  }else{
    	  XGBHNode.addAttribute("value",JD);
    	  }
      }
      
      /*
       * 10.是否存在家庭暴力或虐待、遗弃家庭成员的情形
       * 使用CMSSD中的内容作为判断
       */
      public static void row10(Document document, Element newroot){
    	  Element SFCZJTBLNodeYQJTCYNode = newroot.addElement("SFCZJTBLNodeYQJTCY").addAttribute("nameCN", "是否存在家庭暴力或虐待、遗弃家庭成员的情形");
    	  Element root = document.getRootElement();
          String JD ="否";
          int fnull = 0;
          if(root.element("AJJBQK")!=null){
        	  Element ajjbqkNode = root.element("AJJBQK");
        	  if(ajjbqkNode.element("CMSSD")!=null){
        		  Element cmssdNode = ajjbqkNode.element("CMSSD");
    	          if(cmssdNode.attribute("value")!=null){
    	        	  
    	        	 String qwStr = cmssdNode.attributeValue("value");
    	        	 System.out.println(qwStr);
    	        	 String[] qwStrarray = qwStr.split("，|。");
    	        	 for(String qw:qwStrarray){   		
    	        		 if(( qw.contains("殴打")||qw.contains("虐待")|| qw.contains("暴力")||
    	      			    	 qw.contains("抛弃")||qw.contains("遗弃")||qw.contains("动手"))){
    	        			 fnull = 1;
    	        		 if(( qw.contains("殴打")||qw.contains("虐待")|| qw.contains("暴力")||
      			    	 qw.contains("抛弃")||qw.contains("遗弃")||qw.contains("动手"))&&
    	        		 (!qw.contains("并未")||!qw.contains("没有")||!qw.contains("从未"))){   				    
    				     JD = "是";  					 
    			 }
    	        		 }
    			
    	  }
    		 
         }
          }
          }
    	  System.out.println(JD);
    	  if(fnull == 0){
    		  SFCZJTBLNodeYQJTCYNode.addAttribute("value","未提及");
    	  }else{
    	  SFCZJTBLNodeYQJTCYNode.addAttribute("value",JD);
    	  }
      }
      
      /*
       * 11.是否存在一方或双方当事人有婚外情、与他人同居等情形
       */
      public static void row11(Document document, Element newroot){
    	  Element root = document.getRootElement();
    	  Element SFCZYFHSFDSRYHWQNode = newroot.addElement("SFCZYFHSFDSRYHWQ").addAttribute("nameCN", "是否存在一方或双方当事人有婚外情、与他人同居等情形");
          String JD ="否";
          int flagq = 0;
          int flagh = 0;
          if(root.element("AJJBQK")!=null){
        	  Element ajjbqkNode = root.element("AJJBQK");
        	  if(ajjbqkNode.element("CMSSD")!=null){
        		  Element cmssdNode = ajjbqkNode.element("CMSSD");
    	          if(cmssdNode.attribute("value")!=null){ 	        	  
    	        	 String qwStr = cmssdNode.attributeValue("value");
    	        	 System.out.println(qwStr);
    	        	 String[] qwStrarray = qwStr.split("，|。");
    	        	 for(String qw:qwStrarray){   			
    		        	 if(qw.contains("婚外情")||qw.contains("第三者")||
    		        		qw.contains("出轨")||qw.contains("同居")||
    		         		qw.contains("暧昧")||qw.contains("不正当关系")){
    		        		 JD = "是"; 
    		        		 break;
    			         }   			
    	             }
    		
                    }
    	      	
      }
          }
          SFCZYFHSFDSRYHWQNode.addAttribute("value",JD);
      }
      
      
      /*
       * 12.是否存在一方或双方当事人有赌博、吸毒等恶习的情形
       * 这个恶习不知道有没有统计全部
       */
      public static void row12(Document document, Element newroot){
    	  Element root = document.getRootElement();
    	  Element SFCZYFHSFDSRYHWQNode = newroot.addElement("SFCZYFHSFDSRYDBXDDEXDQX").addAttribute("nameCN", "是否存在一方或双方当事人有赌博、吸毒等恶习的情形");
          String JD ="否";
          int flagq = 0;
          int flagh = 0;
          if(root.element("AJJBQK")!=null){
        	  Element ajjbqkNode = root.element("AJJBQK");
        	  if(ajjbqkNode.element("CMSSD")!=null){
        		  Element cmssdNode = ajjbqkNode.element("CMSSD");
    	          if(cmssdNode.attribute("value")!=null){ 	        	  
    	        	 String qwStr = cmssdNode.attributeValue("value");
    	        	 
    	        	 String[] qwStrarray = qwStr.split("，|。");
    	        	 for(String qw:qwStrarray){   			
    		        	 if(qw.contains("赌博")||qw.contains("吸毒")||qw.contains("酗酒")||qw.contains("恶习")
    		        		){
    		        		 JD = "是"; 
    		        		 System.out.println(qw);
    		        		 break;
    			         }   			
    	             }
    		
                    }
    	      	
      }
          }
          SFCZYFHSFDSRYHWQNode.addAttribute("value",JD);
      }
      /*
       * 13.是否存在一方被依法判处有期徒刑以上刑罚，或其他违法、犯罪行为严重伤害夫妻感情的情形：                。刑期起止时间:    羁押地点
       */
      public static void row13(Document document, Element newroot){
    	  Element root = document.getRootElement();
    	  Element PXNode = newroot.addElement("SFCZYFBYFPCYQTXYSXF").addAttribute("nameCN", "是否存在一方被依法判处有期徒刑以上刑罚，或其他违法、犯罪行为严重伤害夫妻感情的情形以及刑期起止时间、羁押地点");
          Element PXSHFQGQNode = PXNode.addElement("SFCZYFBYFPCYQTXSHFQGQ").addAttribute("nameCN", "是否存在一方被依法判处有期徒刑以上刑罚，或其他违法、犯罪行为严重伤害夫妻感情的情形");
    	  Element XQQZSJNode = PXNode.addElement("XQQZSJ").addAttribute("nameCN", "刑期起止时间");
    	  Element FXKSSJNode = XQQZSJNode.addElement("FXKSSJ").addAttribute("nameCN", "服刑开始时间");
    	  Element FXJSSJNode = XQQZSJNode.addElement("FXJSSJ").addAttribute("nameCN", "服刑结束时间");
    	  Element JYDDNode = PXNode.addElement("JYDD").addAttribute("nameCN","羁押地点");
          String JD ="否";  
      
          
          int flagq = 0;
          int flagh = 0;
          String allStr="";
          Pattern r = Pattern.compile(JBSS.datePattern);
          if(root.element("AJJBQK")!=null){
        	  Element ajjbqkNode = root.element("AJJBQK");
        	  if(ajjbqkNode.element("CMSSD")!=null){
        		  Element cmssdNode = ajjbqkNode.element("CMSSD");
    	          if(cmssdNode.attribute("value")!=null){ 	        	  
    	        	 allStr += cmssdNode.attributeValue("value");   	        	
    	  }
         }
          }
          if(root.element("CPFXGC")!=null){
        	  allStr += root.element("CPFXGC").attributeValue("value");
        	  
          }     
          String[] qwStrarray = allStr.split("，|。");
     	  for(String qw:qwStrarray){   						
	        	 if(qw.contains("判刑")||qw.contains("服刑")||qw.contains("判处")||qw.contains("有期徒刑")||
	        	   (qw.contains("犯")&&qw.contains("罪"))||qw.contains("违法")||qw.contains("刑")){
	          		 flagq = 1;
		         }
		       if(qw.contains("感情")&&(qw.contains("破裂")||qw.contains("伤害")&&(!qw.contains("并未")||!qw.contains("没有")))){
			         flagh =1;
	        	 }
		 
     	 }          
    	  if(flagq*flagh==1){
    		  JD ="是";
    	  }
    	 
    	  
    	  //服刑开始时间、地点、期满
    	  if(root.attribute("value")!=null){
    		  qwStrarray = root.attributeValue("value").split("。");
    		  //服刑开始时间
    		  ArrayList fxkssj = new ArrayList();//服刑开始时间
    		  ArrayList fxjssj = new ArrayList();//服刑结束时间
    		  ArrayList fxdd = new ArrayList();//服刑地点
    		  for(String qw:qwStrarray){
    			  Matcher m = r.matcher(qw);
    			  Boolean rs = m.find();
    			  if(rs){
    				  //服刑开始时间
    			    if(qw.contains("被判刑")||qw.contains("被判处")||qw.contains("被判有期徒刑")||
    			       qw.contains("判刑")||qw.contains("触犯法律")||qw.contains("劳动教养")||qw.contains("劳动改造")||
    			      (qw.contains("因")&&qw.contains("罪")&&qw.indexOf("因")<qw.indexOf("罪"))
    			       ){
    			    	 String[] keyWords = {"被判刑","被判处","被判有期徒刑","判刑","触犯法律","因","罪"};
    			    	 fxkssj.add(TimePro.getRightDate(qw, keyWords, 0, JBSS.datePattern));
    			    	 System.out.println("服刑开始时间："+qw);
    			    }
    			    else if(qw.contains("服刑日期")){//从服刑日期中提取开始和结束时间
    			    	String[] splitarray = qw.split("，|；");
    			    	ArrayList splitdate = new ArrayList();
    			    	for(String qwarray:splitarray){
    			    		if(qwarray.contains("服刑日期")){
    			    			Matcher splitm = r.matcher(qwarray);
    			    			if(splitm.find()){
    			    				TimePro.getDate(qwarray, splitdate, JBSS.datePattern);
    			    				if(splitdate.size()>=1){
    			    					fxkssj.add(splitdate.get(0));
    			    					 System.out.println("服刑开始时间："+qw);
    			    				}
    			    				if(splitdate.size()>=2){
    			    					fxjssj.add(splitdate.get(1));
    			    					 System.out.println("服刑结束时间："+qw);
    			    				}
    			    			}
    			    		}
    			    	}
    			    }
    			    if(qw.contains("出狱")||qw.contains("刑满")||qw.contains("释放")||
    			    		((qw.contains("刑期")||qw.contains("服刑"))&&qw.contains("期满"))){//服刑结束时间
    			    	    String[] keyWords = {"出狱","刑满","释放","期满"};
    			    	    fxjssj.add(TimePro.getRightDate(qw, keyWords, 0, JBSS.datePattern));
    			    	    System.out.println("服刑结束时间："+qw);
    			    }
    				  
    		      }
    		  
    	  	  
    	      }
    		  //羁押地点
    		  qwStrarray = root.attributeValue("value").split("。|，|；");
    		  for(String qw:qwStrarray){
    			  if((qw.contains("监狱")||qw.contains("所")||qw.contains("局"))&&(
    					  qw.contains("服刑")||qw.contains("羁押")||qw.contains("押于")||qw.contains("押在")    					  
    					  )){
    				  fxdd.add(qw);
    				  System.out.println("服刑地点"+qw);
    			  }
    		  }
    		  
    		  
    		  System.out.println(fxkssj);
    		  System.out.println(fxjssj);
    		  System.out.println(fxdd);
    		  Iterator it = fxkssj.iterator();
    		  if(fxkssj.size() == 0 ){
    			  FXKSSJNode.addAttribute("value","未提及");  
    		  }else{
        	  while(it.hasNext()){
        		  String str = (String) it.next();
        		  if(!str.equals("")){
        			  FXKSSJNode.addAttribute("value",str); 
        		  }
        	  }
    		  }
    		  
    		  if(fxjssj.size()==0){
    			  FXJSSJNode.addAttribute("value","未提及");   
    		  }else{
        	  it = fxjssj.iterator();
        	  while(it.hasNext()){
        		  String str = (String) it.next();
        		  if(!str.equals("")){
        			  FXJSSJNode.addAttribute("value",str); 
        		  }
        	  }
    		  }
    		  
    		  if(fxdd.size()==0){
    			  JYDDNode.addAttribute("value","未提及");   
    		  }else{
        	  it = fxdd.iterator();
        	  while(it.hasNext()){
        		  String str = (String) it.next();
        		  if(!str.equals("")){
        			  JYDDNode.addAttribute("value",str); 
        		  }
        	  }
    		  }
    		  
    	  
      }
    	  PXSHFQGQNode.addAttribute("value",JD);
    	  
    	  
    	  
      }
      /*
       *14 是否存在一方曾起诉离婚，经法院处理后夫妻感情未改善的情形
       */
      
      public static void row14(Document document, Element newroot){
    	  Element root = document.getRootElement();
    	  Element QSLHNode = newroot.addElement("SFCZYFCQSLHQK").addAttribute("nameCN", "是否存在一方曾起诉离婚，经法院处理后夫妻感情未改善的情形");
    	  String ifcz = "否";
    	  int flag = 0 ;
    	  if(root.element("AJJBQK")!=null){
        	  Element ajjbqkNode = root.element("AJJBQK");
        	  if(ajjbqkNode.element("CMSSD")!=null){
        		  List<Element> cmssdNode = ajjbqkNode.elements("CMSSD");
        		  for(Element e:cmssdNode){
    	          if(e.attribute("value")!=null){ 	        	  
    	        	 String[]  qwStrarray= e.attributeValue("value").split("。");
    	        	 //是否曾起诉离婚
    	        	 for(String qw:qwStrarray){
    	        		 if(qw.contains("离婚")&&(qw.contains("起诉")||qw.contains("诉讼")||qw.contains("诉至"))&&!qw.contains("撤诉")){
    	        			
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
    	  System.out.println(ifcz);
    	  QSLHNode.addAttribute("value",ifcz);
     
      }
/*
 * 18.是否存在婚前隐瞒了精神病，婚后经治不愈，或者婚前知道对方患有精神病而与其结婚，或一方在夫妻共同生活期间患精神病，久治不愈的情形：
 */
      public static void row18(Document document, Element newroot){
    	  Element root = document.getRootElement();
    	  Element JSBNode = newroot.addElement("JSBQK").addAttribute("nameCN", "是否存在婚前隐瞒了精神病，婚后经治不愈，或者婚前知道对方患有精神病而与其结婚，或一方在夫妻共同生活期间患精神病，久治不愈的情形");
    	  String ifcz = "否";
    	  int flag = 0 ;
    	  
    	     			  
    	  if(root.element("AJJBQK")!=null){
        	  Element ajjbqkNode = root.element("AJJBQK");
        	  if(ajjbqkNode.element("CMSSD")!=null){
        		  List<Element> cmssdNode = ajjbqkNode.elements("CMSSD");
        		  for(Element e:cmssdNode){
    	          if(e.attribute("value")!=null){ 	        	  
    	        	 String[]  qwStrarray= e.attributeValue("value").split("。");
    	        	 //是否存在婚前隐瞒了精神病，婚后经治不愈
    	        	 for(String qw:qwStrarray){
    	        		 if(qw.contains("精神")&&(qw.contains("婚前")&&qw.contains("隐瞒")&&qw.contains("婚后")&&(qw.contains("不愈")||qw.contains("发病")||qw.contains("未愈")||qw.contains("未痊愈")||qw.contains("治疗")))&&!qw.contains("出院")){
    	        			 System.out.println(qw);
    	        			 System.out.println(document.getName());
    	        			 ifcz = "是";
    	        		 }
    	        		 
    	        		 if(qw.contains("婚前")&&qw.contains("精神")&&(qw.contains("知晓")||qw.contains("知道")||qw.contains("了解")||qw.contains("知情"))){
    	        			 System.out.println(qw);
    	        			 System.out.println(document.getName());
    	        			 ifcz = "是";
    	        			
    	        		 }
    	        		 if((qw.contains("精神病")||qw.contains("精神残疾")||qw.contains("精神障碍")||qw.contains("精神分裂症"))&&(qw.contains("婚后")||qw.contains("婚初")||qw.contains("生活"))&&(qw.contains("不愈")||qw.contains("发病")||qw.contains("未愈")||qw.contains("未痊愈")||qw.contains("治疗"))&&!qw.contains("出院")){
    	        			 System.out.println(qw);
    	        			 System.out.println(document.getName());
    	        			 ifcz = "是";
    	        		 }
    	        		 if(ifcz.equals("是")){
    	        			   	        			
    	        			 break;
    	        			 
    	        			 
    	        		 }
    	        	 }
    	        	 
    	          }
        		  }
        	  }
    	  }
    	  JSBNode.addAttribute("value",ifcz);	  
        	  
    	        	 
      }
      /*
       * 15.是否存在因感情不和分居满二年，或经法院判决不准离婚后又分居满一年，互不履行夫妻义务的情形
       */
      
      public static void row15(Document document, Element newroot) throws ParseException{
    	  Element root = document.getRootElement();
    	  Element FJNode = newroot.addElement("FJQK").addAttribute("nameCN", "是否存在因感情不和分居满二年，或经法院判决不准离婚后又分居满一年，互不履行夫妻义务的情形");
    	  String ifcz = "否";
    	  String allStr = "";
    	  int flagfj2n = 0;
    	  int flagtype = 0;
    	  if(root.element("AJJBQK")!=null){
        	  Element ajjbqkNode = root.element("AJJBQK");
        	  if(ajjbqkNode.element("CMSSD")!=null){
        		  List<Element> cmssdNode = ajjbqkNode.elements("CMSSD");
        		  for(Element e:cmssdNode){
    	          if(e.attribute("value")!=null){
    	             allStr += e.attributeValue("value");
    	          }
        		  }
    	          }
      }
    	  if(root.element("CPFXGC")!=null){
    		  List<Element> cpfxgcNode = root.elements("CPFXGC");
    		  for(Element e:cpfxgcNode){
    			  if(e.attribute("value")!=null){
     	             allStr += e.attributeValue("value");
    		  }
    	  }
      }
    	  
    	  String[] qwStr = allStr.split("。");
    	 
    	  
    	  //使用日期差值
    	  String fjkssj = "";
    	  String nowdate = "";
    	  String qslhsj = "";
    	  String[] keyWords = {"分居","离家出走"};
    	  int type=1;
    	
    	  
    	  
    	  
    	  if(root.element("WW")!=null){//获取审判时间
    		  Element wwNode = root.element("WW");
    		  if(wwNode.element("CPSJ")!=null){
    			  Element cpsjNode = wwNode.element("CPSJ");
    			  if(cpsjNode.attribute("value")!=null){
    				  nowdate = TimePro.getDateEach(cpsjNode.attributeValue("value"), JBSS.datePattern);
    			  }
    		  }else{
    			  String[] fileName = document.getName().split("/");
    			  
    			  nowdate = TimePro.getNumbers(fileName[fileName.length-1]);
    		//	  System.out.println("name"+document.getName());
    		//	  System.out.println("nowdate"+nowdate);
    		  }
    	  }
    	  
    //是否因感情不和分居两年
    	  for(String qw:qwStr){
    		  if(qw.contains("生活琐事")||qw.contains("矛盾")||qw.contains("性格不合")||
   					qw.contains("吵架")||qw.contains("感情破裂")||qw.contains("感情已破裂")||
   					qw.contains("不睦")||qw.contains("不和")||
   					qw.contains("争吵")||qw.contains("殴打")||qw.contains("虐待")||
   					qw.contains("暴力")||qw.contains("吵打")||qw.contains("打骂")){ 
    		  if(qw.contains("分居")||qw.contains("离家出走")){			  

    			  fjkssj =  TimePro.getRightDate(qw, keyWords, 1, JBSS.datePattern);
    			 
    			  break;
    		  }
    		  }
    	  }
    	 
    	  //或经法院判决不准离婚后又分居满一年
    	  for(String qw:qwStr){
    		  if(qw.contains("离婚")&&(qw.contains("起诉")||qw.contains("诉讼")||qw.contains("诉至"))&&!qw.contains("撤诉")){    			
     			 if(qw.contains("曾")||qw.contains("次")||qw.contains("驳回")||qw.contains("判决")||qw.contains("调解")||qw.contains("不准")){
     			   if(qw.contains("分居")&&qw.indexOf("离婚")<qw.indexOf("分居")){
     				  qslhsj =  TimePro.getRightDate(qw, keyWords, 1, JBSS.datePattern);
     				  type=2;
     				  break;
     			   }
     			 }
    		  }
     			 
    	  }
    	  
    	  
    	  
    	  
    	  String startdate ="";
    	  String enddate ="";
    	  String qssj = "";
          
    	  startdate = TimePro.getNumbers(fjkssj);
          enddate = TimePro.getNumbers(nowdate);
          qssj = TimePro.getNumbers(qslhsj);

    	  
    	  System.out.println("开始分居时间"+startdate);
    	  System.out.println("曾起诉时间"+qssj);
    	  System.out.println("现在时间"+enddate);
    	  //通过算年份差值
    	  if(!startdate.equals("")&&!enddate.equals("")){
    		 int dis  =Integer.parseInt(enddate)-Integer.parseInt(startdate);
    		 if(dis>=2){
    			 flagfj2n = 1;
    		 }
    	  }
    	  
    	  if(type == 2){
    		  if(!qssj.equals("")&&!enddate.equals("")){
    	    		 int dis  =Integer.parseInt(enddate)-Integer.parseInt(qssj);
    	    		 if(dis>=1){
    	    			 flagfj2n = 1;
    	    		 }
    	    	  }
    	  }
    	
    	  if(flagfj2n==1){
    		  ifcz = "是";
    		  System.out.println(ifcz+"VVVVVVVVVVVVVVVVVVVVVVVVVVv");
    	  }
    	  
    	  
    	  FJNode.addAttribute("value",ifcz);   	  
    	  
      }
      
      /*
       * 16.是否存在一方下落不明满二年，对方起诉离婚，经公告查找确无下落的情形
       */
      public static void row16(Document document, Element newroot){
    	  Element root = document.getRootElement();
    	  Element FJNode = newroot.addElement("XLBMQK").addAttribute("nameCN", "是否存在一方下落不明满二年，对方起诉离婚，经公告查找确无下落的情形");
    	  String ifcz = "否";
    	  String allStr = "";
    	  if(root.element("AJJBQK")!=null){
        	  Element ajjbqkNode = root.element("AJJBQK");
        	  if(ajjbqkNode.element("CMSSD")!=null){
        		  List<Element> cmssdNode = ajjbqkNode.elements("CMSSD");
        		  for(Element e:cmssdNode){
    	          if(e.attribute("value")!=null){
    	             allStr += e.attributeValue("value");
    	          }
        		  }
    	          }
      }
    	  if(root.element("CPFXGC")!=null){
    		  List<Element> cpfxgcNode = root.elements("CPFXGC");
    		  for(Element e:cpfxgcNode){
    			  if(e.attribute("value")!=null){
     	             allStr += e.attributeValue("value");
    		  }
    	  }
      }
    	  
    	  String[] qwStr = allStr.split("。"); 
    	  String czsj = "";//出走事件
    	  String cznf = "";//出走年份
    	  String nowdate  = "";//当前时间
    	  int flag1 = 0;
    	  int  flagifsz = 0;//公告查找
    	  int flagm2n = 0;
    	  String[] keyWords = {"离家","出走","下落不明","去向不明"};
    	  //获取出走时间
    	  for(String qw:qwStr){
    		 if((qw.contains("离家")||qw.contains("出走")&&(qw.contains("下落不明")||qw.contains("去向不明")))){
    			 czsj = TimePro.getRightDate(qw,keyWords , 1, JBSS.datePattern);
    			 flag1 =1;
    			 break;
    		 }
    	  }
    	  if(!czsj.equals("")){
    		  cznf = TimePro.getNumbers(czsj);
    	  }
    	  System.out.println("出走时间"+cznf);
    	  //是否进行公告查找
    	  if(flag1==1){
    	  for(String qw:qwStr){
    		  if(qw.contains("公告")&&(qw.contains("确无下落")||qw.contains("未到"))){
    			  flagifsz = 1;
    		  }
    	  }
    	  }
    	  
    	  //获取当前时间
    	  if(root.element("WW")!=null){//获取审判时间
    		  Element wwNode = root.element("WW");
    
			if(wwNode.element("CPSJ")!=null){
    			  Element cpsjNode = wwNode.element("CPSJ");
    			  if(cpsjNode.attribute("value")!=null){
    				  nowdate  = TimePro.getDateEach(cpsjNode.attributeValue("value"), JBSS.datePattern);
    			  }
    		  }else{
    			  String[] fileName = document.getName().split("/");
    			  
    			  nowdate = TimePro.getNumbers(fileName[fileName.length-1]);
    		  }
    	  }
    	  String nowdate2 = TimePro.getNumbers(nowdate);
    	  System.out.println("现在时间"+nowdate);
    	  if(!cznf.equals("")&&!nowdate2.equals("")){
	    		 int dis  =Integer.parseInt(nowdate2)-Integer.parseInt(cznf);
	    		 if(dis>=2){
	    			flagm2n  = 1;
	    		 }
	    	  }
    	  if(flagm2n*flagifsz==1){
    		  ifcz = "是";
    		  System.out.println(ifcz+"VVVVVVVVVVVVVVVVVVVv");
    	  }
    	  FJNode.addAttribute("value",ifcz);
    	  
    	  
      }
      
}
      
      
      
      

