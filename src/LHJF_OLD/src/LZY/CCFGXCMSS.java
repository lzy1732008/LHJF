package LHJF_OLD.src.LZY;

import org.dom4j.Document;
import org.dom4j.Element;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

/*
 * 财产分割需查明事实
 */
public class CCFGXCMSS {
	 /*
	  * 34夫妻共同财产
	  * 把所有的情况都放进去了，其他中没有分是不是共同财产
	  */
      public static void row34(Document document, Element newroot){
    	  Element root = document.getRootElement();
    	  Element FQGTCCNode = newroot.addElement("FQGTCC").addAttribute("nameCN", "夫妻共同财产");
    	  ArrayList fqgtcc = new ArrayList(); //夫妻共同财产列表
    	  ArrayList house = new ArrayList();//房产
    	  ArrayList money = new ArrayList();//现金及存款
    	  ArrayList car = new ArrayList();//车辆
    	  ArrayList otherAk = new ArrayList(); //其他情况
    	  
    	  Element QBGTCCNode = FQGTCCNode.addElement("QBGTCC").addAttribute("nameCN", "全部共同财产");
    	  Element FCNode = FQGTCCNode.addElement("FC").addAttribute("nameCN", "房产");
    	  Element XJJCKNode = FQGTCCNode.addElement("XJJCK").addAttribute("nameCN", "现金及存款");
    	  Element CLNode = FQGTCCNode.addElement("CL").addAttribute("nameCN", "车辆");
    	  Element QTQKNode = FQGTCCNode.addElement("QTQK").addAttribute("nameCN", "其他情况");
    	  
    	  int fnull1 = 0;
    	  int fnull2 = 0;
    	  int fnull3 = 0;
    	  int fnull4 = 0;
    	  int fnull5 = 0;
    	  
    	  
          if(root.element("AJJBQK")!=null){
        	  Element ajjbqkNode = root.element("AJJBQK");
        	  if(ajjbqkNode.element("CMSSD")!=null){
        		  List<Element> cmssdNode = ajjbqkNode.elements("CMSSD");
        		  for(Element e:cmssdNode){
    	          if(e.attribute("value")!=null){   	        	  
    	        	 String qwStr = e.attributeValue("value");
    	        	 String[] qwStrarray = qwStr.split("。|，");
    	         	 int flag = 0 ;//看是否下一句还有
    	        	 for(String qw:qwStrarray){
    	        		 if((qw.contains("共同财产")||qw.contains("家庭财产"))&&
    	        			(!qw.contains("未购置")&&!qw.contains("没有")&&!qw.contains("争议")&&!qw.contains("无")&&!qw.contains("争执"))){
    	        			 fnull1 = 1;
    	     //   			 System.out.println(qw);
    	        			 int keyindex = 0;
    	        			 if(qw.contains("共同财产")){
    	        				 keyindex  = qw.indexOf("共同财产");
    	        	    	 }
    	        	    	 else if(qw.contains("家庭财产")){
    	        	    		 keyindex  = qw.indexOf("家庭财产");
    	        	    	 }
    	        			 //财产在前面的情况 
    	        	     if(qw.contains("为共同财产")||qw.contains("为家庭财产")||qw.contains("是共同财产")||qw.contains("是家庭财产")||qw.contains("系")){
    	        	    	 int end=0;
    	        	    	 end = keyindex;
    	        	    	 if(end>4){
    	        	    	 String[] ccfg = qw.substring(0, end-4).split("、");
    	        	    	 for(String fcc:ccfg){
    	        	    		 if((fcc.contains("一")||fcc.contains("二")||fcc.contains("两")||fcc.contains("俩")||fcc.contains("三")||fcc.contains("四")||fcc.contains("五")||fcc.contains("六")||fcc.contains("七")||fcc.contains("八")||fcc.contains("九")||fcc.contains("十")||
 	    	        	    			fcc.contains("9")||fcc.contains("8")||fcc.contains("7")||fcc.contains("6")||fcc.contains("5")||fcc.contains("4")||fcc.contains("3")||fcc.contains("2")||fcc.contains("1")||fcc.contains("0"))&&!fcc.contains("一致")){
    	        	    		 fqgtcc.add(fcc); 
    	        	    		 flag=1;
    	        	    		 //判断财产种类
    	        	    		 if(fcc.contains("住房")||fcc.contains("楼")||fcc.contains("房")||fcc.contains("别墅")){
    	        	    			 house.add(fcc);
    	        	    		 }else if(fcc.contains("现金")||fcc.contains("存款")){
    	        	    			 money.add(fcc);
    	        	    		 }else if(fcc.contains("车")){
    	        	    			 car.add(fcc);
    	        	    		 }
    	        	    		
    	        	    		 
    	        	    		 }
    	        	    	 }
    	        	    	 }  	    	 
    	        	     }
    	        	     //财产在后面
    	        	     else{
    	        	    	 String[] ccfg = qw.substring(keyindex+4).split("、");
    	        	    	 for(String fcc:ccfg){
    	        	    		 if((fcc.contains("一")||fcc.contains("二")||fcc.contains("两")||fcc.contains("俩")||fcc.contains("三")||fcc.contains("四")||fcc.contains("五")||fcc.contains("六")||fcc.contains("七")||fcc.contains("八")||fcc.contains("九")||fcc.contains("十")||
    	    	        	    			fcc.contains("9")||fcc.contains("8")||fcc.contains("7")||fcc.contains("6")||fcc.contains("5")||fcc.contains("4")||fcc.contains("3")||fcc.contains("2")||fcc.contains("1")||fcc.contains("0"))&&!fcc.contains("一致")){
    	    	        	    		 fqgtcc.add(fcc); 
    	    	        	    		 flag=1;
    	    	        	    		//判断财产种类
    	    	        	    		 if(fcc.contains("住房")||fcc.contains("楼")||fcc.contains("房")||fcc.contains("别墅")){
    	    	        	    			 house.add(fcc);
    	    	        	    		 }else if(fcc.contains("现金")||fcc.contains("存款")){
    	    	        	    			 money.add(fcc);
    	    	        	    		 }else if(fcc.contains("车")){
    	    	        	    			 car.add(fcc);
    	    	        	    		 }   	    	        	    		 
    	    	        	    		 }
    	        	    	 }
    	        	    	 
    	        	     }
    	        	     
    	        		 }
    	        		
    	        		 
    	        		
    	        		 
    	        	 }
    	        	 
    	        	 //共同财产的另一种表示
    	        	 qwStrarray = qwStr.split("。");
    	       
    	        	 for(String qw:qwStrarray){
	        		   if((qw.contains("原、被告")||qw.contains("双方"))&&(qw.contains("出资")||qw.contains("购买")||qw.contains("购置"))&&(!qw.contains("未")&&!qw.contains("没有")&&!qw.contains("不是"))){
	        			 fnull1 =1;
	        			 int keyindex = 0;
	       // 			 System.out.println(qw);
	        			 if(qw.contains("出资")){
	        				 keyindex  = qw.indexOf("出资");
	        	    	 }
	        	    	 else if(qw.contains("购买")){
	        	    		 keyindex  = qw.indexOf("购买");
	        	    	 }else if(qw.contains("购置")){
	        	    		 keyindex  = qw.indexOf("购置");
	        	    	 }
	        			 String[] fccall = qw.substring(keyindex).split("、|，");
	        			 for(String fcc:fccall){
	        			 if((fcc.contains("一")||fcc.contains("二")||fcc.contains("两")||fcc.contains("俩")||fcc.contains("三")||fcc.contains("四")||fcc.contains("五")||fcc.contains("六")||fcc.contains("七")||fcc.contains("八")||fcc.contains("九")||fcc.contains("十")||
  	        	    			fcc.contains("9")||fcc.contains("8")||fcc.contains("7")||fcc.contains("6")||fcc.contains("5")||fcc.contains("4")||fcc.contains("3")||fcc.contains("2")||fcc.contains("1")||fcc.contains("0"))&&!fcc.contains("一致")){
  	        	    		 fqgtcc.add(fcc); 
  	        	    		 flag=1;
  	        	    		//判断财产种类
  	        	    		 if(fcc.contains("住房")||fcc.contains("楼")||fcc.contains("房")||fcc.contains("别墅")){
  	        	    			 house.add(fcc);
  	        	    			 fnull2 =1;
  	        	    		 }else if(fcc.contains("现金")||fcc.contains("存款")){
  	        	    			 money.add(fcc);
  	        	    			 fnull3 = 1;
  	        	    		 }else if(fcc.contains("车")){
  	        	    			 car.add(fcc);
  	        	    			 fnull4 = 1;
  	        	    		 }   	    	        	    		 
  	        	    		 }
	        			 }
	        			 
	        			 
       		 }
	        		   else if((qw.contains("另有")||qw.contains("还有")||qw.contains("其他"))&&flag==1){
	        			   fnull1 = 1;
	        			   String[] fccall = qw.split("、|，|，");
		        			 for(String fcc:fccall){
		        			 if((fcc.contains("一")||fcc.contains("二")||fcc.contains("两")||fcc.contains("俩")||fcc.contains("三")||fcc.contains("四")||fcc.contains("五")||fcc.contains("六")||fcc.contains("七")||fcc.contains("八")||fcc.contains("九")||fcc.contains("十")||
	  	        	    			fcc.contains("9")||fcc.contains("8")||fcc.contains("7")||fcc.contains("6")||fcc.contains("5")||fcc.contains("4")||fcc.contains("3")||fcc.contains("2")||fcc.contains("1")||fcc.contains("0"))&&!fcc.contains("一致")
		        					){
	  	        	    		 fqgtcc.add(fcc); 
	  	        	    		 flag=1;
	  	        	    		//判断财产种类
	  	        	    		 if(fcc.contains("住房")||fcc.contains("楼")||fcc.contains("房")||fcc.contains("别墅")){
	  	        	    			 house.add(fcc);
	  	        	    			 fnull2 =1;
	  	        	    		 }else if(fcc.contains("现金")||fcc.contains("存款")){
	  	        	    			 money.add(fcc);
	  	        	    			 fnull3 =1;
	  	        	    		 }else if(fcc.contains("车")){
	  	        	    			 car.add(fcc);
	  	        	    			 fnull4=1;
	  	        	    		 }   	    	        	    		 
	  	        	    		 }
		        			 }
		        			 flag =0 ;
	        		   }
    	          }
    	        	 
    	        	 //其他事项
    	        	 qwStrarray = qwStr.split("。");
    	        	 for(String qw:qwStrarray){
    	        		 if(qw.contains("在原告处")||qw.contains("在被告处")||
    	        			qw.contains("由原告保管")||qw.contains("由被告保管")||
    	        			qw.contains("原告名下")||qw.contains("被告名下")){
    	        			 fnull5=1;
    	        			 otherAk.add(qw);
    	        		 }
    	        	 }
    	        	 
    	        	 
    	        	 
    	          }
        	  }
        	  }
          }
//          System.out.println("all"+fqgtcc);   
//          System.out.println("house:"+house);
//          System.out.println("money"+money);
//          System.out.println("car"+car);
          Iterator it = fqgtcc.iterator();
          if(fnull1 == 0){
        	  QBGTCCNode.addAttribute("value", "未提及"); 
          }else{
          String p1 = "";
    	  while(it.hasNext()){
    		  String str = (String) it.next();
    		  if(!str.equals("")){
    			  p1+=str+";";
    			  
    		  }
    	  }
    	  QBGTCCNode.addAttribute("value", p1); 
          }
          
          if(fnull2 == 0){
        	  FCNode.addAttribute("value",  "未提及"); 
          }else{
          String p2 = "";
    	  it = house.iterator();
    	  while(it.hasNext()){
    		  String str = (String) it.next();
    		  
    		  if(!str.equals("")){
    			  p2 += str+";";
    		
    		  }
    	  }
    	  FCNode.addAttribute("value", p2); 
          }
    	  
          if(fnull3 == 0){
        	  XJJCKNode.addAttribute("value", "未提及"); 
          }else{
        	  String p3 = "";
    	  it = money.iterator();
    	  while(it.hasNext()){
    		  String str = (String) it.next();
    		  if(!str.equals("")){
    			  p3 += str+";";
    			  
    		  }
    	  }
    	  XJJCKNode.addAttribute("value", p3); 
          }
          
          if(fnull4 == 0){
        	  CLNode.addAttribute("value", "未提及");  
          }else{
        	  String p4 ="";
    	  it = car.iterator();
    	  while(it.hasNext()){
    		  String str = (String) it.next();
    		  if(!str.equals("")){
    		
    			  p4+=str+";";
    		  }
    	  }
    	  CLNode.addAttribute("value", p4); 
          }
          
          if(fnull5 == 0){
        	  QTQKNode.addAttribute("value", "未提及");   
          }else{
        	  String p5 ="";
    	  it = otherAk.iterator();
    	  while(it.hasNext()){
    		  String str = (String) it.next();
    		  if(!str.equals("")){
    			  p5 +=str+";";
    			  
    		  }
    	  }
    	  QTQKNode.addAttribute("value", p5); 
          }
    	  
    	  
         
          
      }

     
     
}
