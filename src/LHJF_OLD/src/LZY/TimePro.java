package LHJF_OLD.src.LZY;

import java.util.*;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class TimePro {
	 public static void getDate(String str,ArrayList s ,String regx){ //提取出来字符串中所有符合正则表达式regex的字符串，并存进arrayList中
	    	//ArrayList s = new ArrayList();
	    	
	    	String date1 = getDateEach(str,regx);
	    	
	    	if(!date1.equals("")&&date1!=null){   //此处有修改
	    		s.add(date1);
	    		int index = str.indexOf(date1)+date1.length();
	    		String newstr = str.substring(index);
	    		//System.out.println(newstr);
	    		getDate(newstr,s,regx);
	    	}
	    //	System.out.println(s);
	    }
	    	  public static String getDateEach(String str,String regex) {
	    	        
	    	      //  System.out.println("请输入：");
	    	      //  String str = null;// = "20140308是三八节";
	    	      //  Scanner scan = new Scanner(System.in);
	    	     //   str = scan.next();
	    	      //  String regex = "(\\d{4}年(\\d{1,2}月)?(\\d{1,2}日)?)";
	    	        Pattern pattern = Pattern.compile(regex);
	    	        Matcher m = pattern.matcher(str);
	    	        String datestr = "";
	    	        if (m.find()) {
	    	            datestr = m.group();
	    	    //        System.out.println(datestr);
	    	        }
	    	        return datestr;
	    	    }
	    	  public   static   void  removeDuplicateWithOrder(List list)   {//去掉List中的重复元素，只留一个
	    	      Set set  =   new  HashSet();
	    	      List newList  =   new  ArrayList();
	    	   for  (Iterator iter  =  list.iterator(); iter.hasNext();)   {
	    	         Object element  =  iter.next();
	    	         if  (set.add(element))
	    	            newList.add(element);
	    	     } 
	    	     list.clear();
	    	     list.addAll(newList);
	    	   //  System.out.println( " remove duplicate "   +  list);
	    	 } 
	    	  
	    	  /*
	    	   * 提取字符串中所有满足正则regx,但是不满足uregex的字符串
	    	   */
	    	   public static void getDaterTr(String str,ArrayList s ,String regx,String uregx){ //
	       	       
	      	        ArrayList s2  = new ArrayList();   	        	       
	      	        getDate(str,s2,uregx);
	      //	        System.out.println(s2);
	      	        String newstr = "";
	   	 	  	if(s2.size()>0){
	   	        	for(int i=0;i< s2.size();i++){
	   	   	        	newstr = str.replace((String)s2.get(i), "");
	   	   	        	
	   	   	        }
	   	        }
	   	      //  System.out.println("cut"+newstr);
	   	        
	   	        getDate(newstr,s,regx);   	
	      }
	    	   /*
	    	    * 
	    	    */
	    	   public static String getRightDate(String str,String[] keyWords,int fh,String regex){
	    	    	String datef = "";
	    	    	String dateh = "";
	    	    	int disf = 1000000;
	    	    	int dish = 1000000;
	    	    	for(int i = 0 ;i < keyWords.length; i++){
	    	    		if(str.contains(keyWords[i])){
	    	    //		System.out.println("关键词:"+keyWords[i]);
	    	    		int flag = 0;
	    	    		ArrayList a = new ArrayList();
	    	    		getDate(str,a,regex);
	    	        	Iterator it = a.iterator();
	    	        	
	    	    		while(it.hasNext()){
	    	    			
	    	    			String allDate = (String) it.next();
	    	    	//		System.out.println("date"+allDate);
	    	        		
	    	        		
	    	        		int start = str.indexOf(allDate);
	    	        		int end = start+allDate.length()-1;	    	 
	    	        	//	System.out.println(start+"-"+end+":"+str.indexOf(keyWords[i]));
	    	        				  if(end < str.indexOf(keyWords[i])){
	    	        	//				  System.out.println("substring"+str.substring(end, str.indexOf(keyWords[i])));
	    	        					if(!(str.substring(end, str.indexOf(keyWords[i])).contains("，"))){
	    	        						return allDate;
	    	        					}
	    	        				    if(disf > str.indexOf(keyWords[i])-end){
	    	        					   disf = str.indexOf(keyWords[i])-end;
	    	        				 	   datef = allDate;
	    	        				 	 //  System.out.println("disf"+disf);
	    	        				     }
	    	        				  }      		
	    	        				    if(start > str.indexOf(keyWords[i])){
	    	        				    	if(!(str.substring(str.indexOf(keyWords[i]),start).contains("，"))&&fh!=0){
	    	        				    		return allDate;
	    	        				    	}
	    	        				    	if( dish > (start - str.indexOf(keyWords[i]))){
	    	        						dish = start - str.indexOf(keyWords[i]);
	    	        						dateh = allDate;
	    	        						//System.out.println("dish"+dish);
	    	        					    }  					
	    	        			    	}       				
	    	        	 }
	    	    		break;      		    
	    	        } 
	    	    	}
	    	    	if(fh == 0){
	    	    		return datef;
	    	    	}
	    	    	if(fh == 1){
	    	    		
	    	    		return dish>disf?datef:dateh;
	    	    	}
	    			return dateh;
	    		
	    	     
	    	  }
	    	   /*
	    	    * 提取字符串中所有的数字
	    	    */
	    		public static String getNumbers(String content) {
	    			Pattern pattern = Pattern.compile("[0-9]+([．]{1}[0-9]+){0,1}");  
	    		    Matcher matcher = pattern.matcher(content);  
	    		    while (matcher.find()) {  
	    		       return matcher.group(0);  
	    		    }  
	    		    return "";  
	    		} 
	    		/*
	    		 * 找到该字符串中包含哪些关键字
	    		 */
	    	    public static ArrayList<String> ifContain(String str,String[] keyWords){ //找到该字符串中包含哪些关键字
	    	    	int startindex=-1;
	    	    	ArrayList<String> re = new ArrayList();
	    	    	for(int i =0 ;i < keyWords.length; i++){
	    	    		if(str.contains(keyWords[i])){
	    	    			re.add(keyWords[i]);
	    	    			
	    	    		}
	    	    	}
	    	    	return re;
	    	    	
	    	    }

	    	    public static String dateFormate(String date){
	    	    	String newdate ;
	    	    	newdate = new String(date.replace("年", "-"));
//	    	    	date = new String(date.replace('月', '-'));
//	    	    	date = new String(date.replace('日', '-'));
//	    	    	date = new String(date.replace('时', ':'));
//	    	    	date = new String(date.replace('分', ' '));
//	    	    	if(date.indexOf(date.length()-1)=='-'||date.indexOf(date.length()-1)==':'){
//	    	    		date = date.substring(0,date.length()-2);
//	    	    	}
	    	    	return newdate;
	    	    }
}
