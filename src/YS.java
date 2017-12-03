import java.io.File;
import java.io.FileOutputStream;
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
public class YS {
	private static Util util= new Util();
	/*
	 * row3:被告拒不到庭或中途退庭
	 */ 
	public void row1_1(Document document,Element newroot) {
		
	}
	public static int  row3(Document document) {
	 	Element root = document.getRootElement();   
  	   // Element SFCZJHQKJFYBDNode = newroot.addElement("SFCZJHQKJFYBD").addAttribute("nameCN", "是否被告拒不到庭或中途退庭");
  	    int res = 0; 
  	    
  	    Element docele = root.element("QW");
  	    if(docele!=null) {
  	    	if(docele.attribute("value")!=null) {
  	    		String content = docele.attributeValue("value");
  	    		String[] sentence = content.split("。|；");
  	   	        for(String s:sentence) {
  	   		        if(s.contains("被告")&&(s.contains("退庭")||s.contains("不到庭")||s.contains("未到庭"))) {
  	   		        	res = 1;
  	   		  }		  
  	   	   }
  	    		
  	    	}
  	    }
        return res;   
	}
	/*
	 * 宣判前原告申请撤诉
	 */
	public static int  row4(Document document) {
	 	Element root = document.getRootElement();   
  	   // Element SFCZJHQKJFYBDNode = newroot.addElement("SFCZJHQKJFYBD").addAttribute("nameCN", "是否被告拒不到庭或中途退庭");
  	    int res = 0; 
  	    
  	    Element docele = root.element("QW");
  	    if(docele!=null) {
  	    	if(docele.attribute("value")!=null) {
  	    		String content = docele.attributeValue("value");
  	    		String[] sentence = content.split("。|；");
  	   	        for(String s:sentence) {
  	   		        if(s.contains("原告")&&s.contains("撤")&&s.contains("诉")) {
  	   		        	res = 1;
  	   		        	System.out.println(s);
  	   		  }		  
  	   	   }
  	    		
  	    	}
  	    }
        return res;   
	}
	/*
	 * 涉及子女抚养教育
	 */
	public static int  row5(Document document) {
		int res = 0; 
 	     String content = util.getAJJBQKString(document);
 	     String[] sentence = content.split("。|；");
 	    if(content!=null) {
 	   	 for(String s:sentence) {
 	   		 if(s.contains("子")||s.contains("女")||s.contains("男")||s.contains("女")||s.contains("孩")) {
 	   		    res = 1;	   		        
 	   		    String[] ysFlag = {"教育","抚养","学业"};
 	   		    System.out.println(s);
 	   		    if(util.ifContainFlag(s, ysFlag)) {
 	   		        res = 1;
 	   		    }
 	   		  }		  
 	   	   }	 
 	    }
       return res;  
	}
	/*
	 * 涉及子女生活费、教育费
	 */
	public static int  row5_2(Document document) {
	 	 int res = 0; 
  	     String content = util.getAJJBQKString(document);
  	     String[] sentence = content.split("。|；");
  	   	 for(String s:sentence) {
  	   		 if(s.contains("子")||s.contains("女")||s.contains("男")||s.contains("女")||s.contains("孩")) {
  	   		    res = 1;	   		        
  	   		    String[] ysFlag = {"生活费","抚养费"};
  	   		    System.out.println(s);
  	   		    if(util.ifContainFlag(s, ysFlag)) {
  	   		        res = 1;
  	   		    }
  	   		  }		  
  	   	   }	    		    
        return res;   
	} 
	/*
	 * 有哺乳期子女
	 */
	public static int row6(Document document) {
		 int res = 0; 
  	     String content = util.getAJJBQKString(document);
  	     if(content!=null) {
  	     String regex = "。|；";
  	     String[] ysFlag = {"哺乳期","襁褓"};
  	     util.ifContainYS(content, ysFlag, regex);
  	     }
  	     return res;
	}
	/*
	 * 中华人民共和国民事诉讼法第六十四条：证据
	 */
	public static int row7(Document document) {
		int res = 0; 
 	     String content = util.getAJJBQKString(document);
 	     if(content!=null) {
 	    	String regex = "。|；|，";
 	  	    String[] sentence = content.split(regex);
 	  	    for(String s:sentence) {
 	  	    	if(s.contains("证据")) {
 	  	    		System.out.println("证据："+s);
 	  	    		if(s.contains("提供")||s.contains("收集")||s.contains("调查")) {
 	  	    			res =1;
 	  	    			System.out.println("证据22："+s);
 	  	    		}
 	  	    }
 	  	    }
 	     }
 	     return res;
	}
	
	/*
	 * 中华人民共和国民事诉讼法第一百四十二条:第一百四十二条 基层人民法院和它派出的法庭审理事实清楚、权利义务关系明确、争议不大的简单的民事案件，适用本章规定。
	 */
	public static int row8(Document document) {
		int res = 0; 
 	    String content = util.getAJJBQKString(document);
 	    String regex = "。|；|，";
 	    if(content!=null) {
	  	    String[] sentence = content.split(regex);
	  	    for(String s:sentence) {
	  	    	if(s.contains("简单")||s.contains("简易")) {
	  	    		System.out.println("简单："+s);
	  	    		if(s.contains("民事案件")||s.contains("程序")) {
	  	    			res =1;
	  	    			System.out.println("民事案件："+s);
	  	    		}
	  	    }
	  	    }
	     }	    
 	    return res;
	}
	
	/*
	 * 中华人民共和国婚姻法第三十八条
	 */
	public static int row9(Document document) {
		int res = 0; 
 	    String content = util.getAJJBQKString(document);
 	    String regex = "。|；|，";
 	    if(content!=null) {
	  	    String[] sentence = content.split(regex);
	  	    for(String s:sentence) {
	  	    	if(s.contains("简单")||s.contains("简易")) {
	  	    		System.out.println("简单："+s);
	  	    		if(s.contains("民事案件")||s.contains("程序")) {
	  	    			res =1;
	  	    			System.out.println("民事案件："+s);
	  	    		}
	  	    }
	  	    }
	     }	    
 	    return res;
	}
	/*
	 * 中华人民共和国婚姻法第三十八条:涉及父母探望子女
	 */
	public static int row10(Document document) {
		int res = 0; 
 	     String content = util.getAJJBQKString(document);
 	     if(content!=null) {
 	     String[] sentence = content.split("。|；");
 	   	 for(String s:sentence) {
 	   		 if(s.contains("子")||s.contains("女")||s.contains("男")||s.contains("女")||s.contains("孩")) {
 	   		    res = 1;	   		        
 	   		    String[] ysFlag = {"探望","看望"};
 	   		  //  System.out.println(s);
 	   		    if(util.ifContainFlag(s, ysFlag)) {
 	   		        System.out.println("看望》》》》》》》》》》》》》"+s);
 	   		    	res = 1;
 	   		    }
 	   		  }		  
 	   	   }	    
 	     }
       return res;
	}
	/*
	 * 中华人民共和国婚姻法第十八条:一方有伤残补助
	 */
	public static int row11_1(Document document) {
		int res = 0; 
 	     String content = util.getAJJBQKString(document);
 	     if(content!=null) {
 	     String[] sentence = content.split("。|；|，");
 	   	 for(String s:sentence) {
 	   	     if(s.contains("伤残补助")||s.contains("伤残费")||s.contains("伤残金")) {
 	   	    	 System.out.println(s);
 	   	     }
 	   	   }	    
 	     }
       return res;
	}
	/*
	 * 中华人民共和国婚姻法第十八条：是否有遗嘱或赠与合同中确定只归夫或妻一方的财产
	 */
	public static int row11_2(Document document) {
		int res = 0;
		String content = util.getAJJBQKString(document);
		if(content!=null) {
			 String[] sentence = content.split("。|；|，");
	 	   	 for(String s:sentence) {
	 	   	     if(s.contains("遗嘱")||s.contains("赠与")||s.contains("遗赠")) {
	 	   	    	 System.out.println(s);
	 	   	    	 if(!s.contains("双方")&&!s.contains("共同")) {
	 	   	    		 System.out.print("双方"+s);
	 	   	    		 res = 1;
	 	   	    	 }
	 	   	    	 
	 	   	     }
	 	   	   }	
		}
		return res;
		
	}
	/*
	 * 中华人民共和国民事诉讼法第十三条:民事诉讼
	 */
	public static int row12(Document document) {
		int res = 0;
		String content = util.getAJJBQKString(document);
		if(content!=null) {
			 String[] sentence = content.split("。|；|，");
	 	   	 for(String s:sentence) {
	 	   	     if(s.contains("民事")) {
	 	   	    	 System.out.println(s);
	 	   	     }
	 	   	   }	
		}
		return res;
		
	}
	
	/*
	 * 中华人民共和国婚姻法第四十一条：夫妻共同生活负债
	 */
	public static int row13(Document document) {
		int res = 0;
		String content = util.getAJJBQKString(document);
		if(content!=null) {
			 String[] sentence = content.split("。|；|，");
	 	   	 for(String s:sentence) {
	 	   		 if(s.contains("共同")||s.contains("双方")) {
	 	   			 String[] ysFlag = {"债务","欠款","负债"};
	 	   			 if(util.ifContainFlag(s, ysFlag)) {
                        res = 1;	 	   				 
	 	   			 }
	 	   		 }
	 	   	   }	
		}
		return res;
		
	}
	/*
	 * 中华人民共和国民事诉讼法第一百三十四条：公开审理
	 */
	public static int row14(Document document) {
		int res = 0;
		String content = util.getAJJBQKString(document);
		if(content!=null) {
			 String[] sentence = content.split("。|；|，");
	 	   	 for(String s:sentence) {
	 	   		 if(s.contains("公开")&&s.contains("审理")) {
//	 	   			 String[] ysFlag = {"债务","欠款","负债"};
//	 	   			 if(util.ifContainFlag(s, ysFlag)) {
//                        res = 1;	 	   				 
//	 	   			 }
	 	   			 System.out.println(s);
	 	   			 res = 1;
	 	   		 }
	 	   	   }	
		}
		return res;
	}	
    
	/*
	 * 中华人民共和国婚姻法：第四十二条，离婚后一方生活困难
	 */
	public static int row15(Document document) {
		int res = 0;
		String content = util.getAJJBQKString(document);
		if(content!=null) {
			String[] sentence = content.split("。|；|，");
			for(String s:sentence) {
				if(s.contains("原告")||s.contains("被告")) {
					if (s.contains("生活") ) {
						//使用滑动窗口
						String[] keypairs = {"生活；困难","生活;艰难","无法;生活"};
						if(util.windowForKey(s,keypairs,8)){
							System.out.println(s);
						}
					}

				}
			}
		}
		return res;
	}
	/*
	最高人民法院关于适用中华人民共和国婚姻法若干问题的解释（二）第十条 ；请求返还彩礼
	 */
	public static int row16(Document document) {
		int res = 0;
		String content = util.getAJJBQKString(document);
		if(content!=null) {
			String[] sentence = content.split("。|；|，");
			for(String s:sentence) {
				if(s.contains("原告")||s.contains("被告")) {
					if (s.contains("生活") ) {
						//使用滑动窗口
						String[] keypairs = {"生活；困难","生活;艰难","无法;生活"};
						if(util.windowForKey(s,keypairs,8)){
							System.out.println(s);
						}
					}

				}
			}
		}
		return res;
	}
	public static int row17(Document document) {
		int res = 0;
		String content = util.getAJJBQKString(document);
		if(content!=null) {
			String[] sentence = content.split("。|；");
			for(String s:sentence) {
				if(s.contains("彩礼")&&(s.contains("要求")||s.contains("主张")||s.contains("请求"))){
					System.out.println(s);
					String[]  ysflag = {"返还","归还"};
					if(util.ifContainFlag(s,ysflag)){
						res = 1;
					}


				}
			}
		}
		return res;
	}
	/*
	最高人民法院关于适用中华人民共和国婚姻法若干问题的解释（一）第五条:以夫妻名义共同生活
	 */
	public static int row18(Document document) {
		int res = 0;
		String content = util.getAJJBQKString(document);
		if(content!=null) {
			String[] sentence = content.split("。|；|，");
			for(String s:sentence) {
				if(s.contains("生活")&&s.contains("夫妻")&&s.contains("名义")){
					System.out.println(s);



				}
			}
		}
		return res;
	}
/*
最高人民法院关于人民法院审理离婚案件处理子女抚养问题的若干具体意见第3条：父方和母方均要求子女随其生活
 */
public static int row19(Document document) {
	int res = 0;
	String content = util.getAJJBQKString(document);
	if(content!=null) {
		String[] sentence = content.split("。|；");
		for(String s:sentence) {
			String[] znflag = {"子","女","孩子","儿","男孩","女孩"};
			if(util.ifContainFlag(s,znflag)){
				//System.out.println(s);
				//照顾，抚养，生活，抚育
				String[] fyflag = {"照顾","抚养","生活"};
				if(util.ifContainFlag(s,fyflag)){
					String[] qqflag = {"要求","请求","希望","争议"};
					//要求、请求、希望、争议
					if(util.ifContainFlag(s,qqflag)){
						System.out.println(s);
					}
				}

			}
		}
	}
	return res;
}
/*
中华人民共和国婚姻法第三十一条:双方自愿离婚
 */
	public static int row100(Document document) {
		int res = 0;
		String content = util.getAJJBQKString(document);
		if(content!=null) {
			String[] sentence = content.split("。|；|，");
			for(String s:sentence) {
				if(s.contains("离婚")){
				//	System.out.println(s);
					if((s.contains("自愿")||s.contains("同意"))&&!s.contains("不同意")&&!s.contains("不自愿")){
						System.out.println(s);
					}
				}
			}
		}
		return res;
	}






}
