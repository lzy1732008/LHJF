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
	 * row3:����ܲ���ͥ����;��ͥ
	 */ 
	public void row1_1(Document document,Element newroot) {
		
	}
	public static int  row3(Document document) {
	 	Element root = document.getRootElement();   
  	   // Element SFCZJHQKJFYBDNode = newroot.addElement("SFCZJHQKJFYBD").addAttribute("nameCN", "�Ƿ񱻸�ܲ���ͥ����;��ͥ");
  	    int res = 0; 
  	    
  	    Element docele = root.element("QW");
  	    if(docele!=null) {
  	    	if(docele.attribute("value")!=null) {
  	    		String content = docele.attributeValue("value");
  	    		String[] sentence = content.split("��|��");
  	   	        for(String s:sentence) {
  	   		        if(s.contains("����")&&(s.contains("��ͥ")||s.contains("����ͥ")||s.contains("δ��ͥ"))) {
  	   		        	res = 1;
  	   		  }		  
  	   	   }
  	    		
  	    	}
  	    }
        return res;   
	}
	/*
	 * ����ǰԭ�����볷��
	 */
	public static int  row4(Document document) {
	 	Element root = document.getRootElement();   
  	   // Element SFCZJHQKJFYBDNode = newroot.addElement("SFCZJHQKJFYBD").addAttribute("nameCN", "�Ƿ񱻸�ܲ���ͥ����;��ͥ");
  	    int res = 0; 
  	    
  	    Element docele = root.element("QW");
  	    if(docele!=null) {
  	    	if(docele.attribute("value")!=null) {
  	    		String content = docele.attributeValue("value");
  	    		String[] sentence = content.split("��|��");
  	   	        for(String s:sentence) {
  	   		        if(s.contains("ԭ��")&&s.contains("��")&&s.contains("��")) {
  	   		        	res = 1;
  	   		        	System.out.println(s);
  	   		  }		  
  	   	   }
  	    		
  	    	}
  	    }
        return res;   
	}
	/*
	 * �漰��Ů��������
	 */
	public static int  row5(Document document) {
		int res = 0; 
 	     String content = util.getAJJBQKString(document);
 	     String[] sentence = content.split("��|��");
 	    if(content!=null) {
 	   	 for(String s:sentence) {
 	   		 if(s.contains("��")||s.contains("Ů")||s.contains("��")||s.contains("Ů")||s.contains("��")) {
 	   		    res = 1;	   		        
 	   		    String[] ysFlag = {"����","����","ѧҵ"};
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
	 * �漰��Ů����ѡ�������
	 */
	public static int  row5_2(Document document) {
	 	 int res = 0; 
  	     String content = util.getAJJBQKString(document);
  	     String[] sentence = content.split("��|��");
  	   	 for(String s:sentence) {
  	   		 if(s.contains("��")||s.contains("Ů")||s.contains("��")||s.contains("Ů")||s.contains("��")) {
  	   		    res = 1;	   		        
  	   		    String[] ysFlag = {"�����","������"};
  	   		    System.out.println(s);
  	   		    if(util.ifContainFlag(s, ysFlag)) {
  	   		        res = 1;
  	   		    }
  	   		  }		  
  	   	   }	    		    
        return res;   
	} 
	/*
	 * �в�������Ů
	 */
	public static int row6(Document document) {
		 int res = 0; 
  	     String content = util.getAJJBQKString(document);
  	     if(content!=null) {
  	     String regex = "��|��";
  	     String[] ysFlag = {"������","����"};
  	     util.ifContainYS(content, ysFlag, regex);
  	     }
  	     return res;
	}
	/*
	 * �л����񹲺͹��������Ϸ�����ʮ������֤��
	 */
	public static int row7(Document document) {
		int res = 0; 
 	     String content = util.getAJJBQKString(document);
 	     if(content!=null) {
 	    	String regex = "��|��|��";
 	  	    String[] sentence = content.split(regex);
 	  	    for(String s:sentence) {
 	  	    	if(s.contains("֤��")) {
 	  	    		System.out.println("֤�ݣ�"+s);
 	  	    		if(s.contains("�ṩ")||s.contains("�ռ�")||s.contains("����")) {
 	  	    			res =1;
 	  	    			System.out.println("֤��22��"+s);
 	  	    		}
 	  	    }
 	  	    }
 	     }
 	     return res;
	}
	
	/*
	 * �л����񹲺͹��������Ϸ���һ����ʮ����:��һ����ʮ���� ��������Ժ�����ɳ��ķ�ͥ������ʵ�����Ȩ�������ϵ��ȷ�����鲻��ļ򵥵����°��������ñ��¹涨��
	 */
	public static int row8(Document document) {
		int res = 0; 
 	    String content = util.getAJJBQKString(document);
 	    String regex = "��|��|��";
 	    if(content!=null) {
	  	    String[] sentence = content.split(regex);
	  	    for(String s:sentence) {
	  	    	if(s.contains("��")||s.contains("����")) {
	  	    		System.out.println("�򵥣�"+s);
	  	    		if(s.contains("���°���")||s.contains("����")) {
	  	    			res =1;
	  	    			System.out.println("���°�����"+s);
	  	    		}
	  	    }
	  	    }
	     }	    
 	    return res;
	}
	
	/*
	 * �л����񹲺͹�����������ʮ����
	 */
	public static int row9(Document document) {
		int res = 0; 
 	    String content = util.getAJJBQKString(document);
 	    String regex = "��|��|��";
 	    if(content!=null) {
	  	    String[] sentence = content.split(regex);
	  	    for(String s:sentence) {
	  	    	if(s.contains("��")||s.contains("����")) {
	  	    		System.out.println("�򵥣�"+s);
	  	    		if(s.contains("���°���")||s.contains("����")) {
	  	    			res =1;
	  	    			System.out.println("���°�����"+s);
	  	    		}
	  	    }
	  	    }
	     }	    
 	    return res;
	}
	/*
	 * �л����񹲺͹�����������ʮ����:�漰��ĸ̽����Ů
	 */
	public static int row10(Document document) {
		int res = 0; 
 	     String content = util.getAJJBQKString(document);
 	     if(content!=null) {
 	     String[] sentence = content.split("��|��");
 	   	 for(String s:sentence) {
 	   		 if(s.contains("��")||s.contains("Ů")||s.contains("��")||s.contains("Ů")||s.contains("��")) {
 	   		    res = 1;	   		        
 	   		    String[] ysFlag = {"̽��","����"};
 	   		  //  System.out.println(s);
 	   		    if(util.ifContainFlag(s, ysFlag)) {
 	   		        System.out.println("������������������������������"+s);
 	   		    	res = 1;
 	   		    }
 	   		  }		  
 	   	   }	    
 	     }
       return res;
	}
	/*
	 * �л����񹲺͹���������ʮ����:һ�����˲в���
	 */
	public static int row11_1(Document document) {
		int res = 0; 
 	     String content = util.getAJJBQKString(document);
 	     if(content!=null) {
 	     String[] sentence = content.split("��|��|��");
 	   	 for(String s:sentence) {
 	   	     if(s.contains("�˲в���")||s.contains("�˲з�")||s.contains("�˲н�")) {
 	   	    	 System.out.println(s);
 	   	     }
 	   	   }	    
 	     }
       return res;
	}
	/*
	 * �л����񹲺͹���������ʮ�������Ƿ��������������ͬ��ȷ��ֻ������һ���ĲƲ�
	 */
	public static int row11_2(Document document) {
		int res = 0;
		String content = util.getAJJBQKString(document);
		if(content!=null) {
			 String[] sentence = content.split("��|��|��");
	 	   	 for(String s:sentence) {
	 	   	     if(s.contains("����")||s.contains("����")||s.contains("����")) {
	 	   	    	 System.out.println(s);
	 	   	    	 if(!s.contains("˫��")&&!s.contains("��ͬ")) {
	 	   	    		 System.out.print("˫��"+s);
	 	   	    		 res = 1;
	 	   	    	 }
	 	   	    	 
	 	   	     }
	 	   	   }	
		}
		return res;
		
	}
	/*
	 * �л����񹲺͹��������Ϸ���ʮ����:��������
	 */
	public static int row12(Document document) {
		int res = 0;
		String content = util.getAJJBQKString(document);
		if(content!=null) {
			 String[] sentence = content.split("��|��|��");
	 	   	 for(String s:sentence) {
	 	   	     if(s.contains("����")) {
	 	   	    	 System.out.println(s);
	 	   	     }
	 	   	   }	
		}
		return res;
		
	}
	
	/*
	 * �л����񹲺͹�����������ʮһ�������޹�ͬ���ծ
	 */
	public static int row13(Document document) {
		int res = 0;
		String content = util.getAJJBQKString(document);
		if(content!=null) {
			 String[] sentence = content.split("��|��|��");
	 	   	 for(String s:sentence) {
	 	   		 if(s.contains("��ͬ")||s.contains("˫��")) {
	 	   			 String[] ysFlag = {"ծ��","Ƿ��","��ծ"};
	 	   			 if(util.ifContainFlag(s, ysFlag)) {
                        res = 1;	 	   				 
	 	   			 }
	 	   		 }
	 	   	   }	
		}
		return res;
		
	}
	/*
	 * �л����񹲺͹��������Ϸ���һ����ʮ��������������
	 */
	public static int row14(Document document) {
		int res = 0;
		String content = util.getAJJBQKString(document);
		if(content!=null) {
			 String[] sentence = content.split("��|��|��");
	 	   	 for(String s:sentence) {
	 	   		 if(s.contains("����")&&s.contains("����")) {
//	 	   			 String[] ysFlag = {"ծ��","Ƿ��","��ծ"};
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
	 * �л����񹲺͹�������������ʮ����������һ����������
	 */
	public static int row15(Document document) {
		int res = 0;
		String content = util.getAJJBQKString(document);
		if(content!=null) {
			String[] sentence = content.split("��|��|��");
			for(String s:sentence) {
				if(s.contains("ԭ��")||s.contains("����")) {
					if (s.contains("����") ) {
						//ʹ�û�������
						String[] keypairs = {"�������","����;����","�޷�;����"};
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
	�������Ժ���������л����񹲺͹���������������Ľ��ͣ�������ʮ�� �����󷵻�����
	 */
	public static int row16(Document document) {
		int res = 0;
		String content = util.getAJJBQKString(document);
		if(content!=null) {
			String[] sentence = content.split("��|��|��");
			for(String s:sentence) {
				if(s.contains("ԭ��")||s.contains("����")) {
					if (s.contains("����") ) {
						//ʹ�û�������
						String[] keypairs = {"�������","����;����","�޷�;����"};
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
			String[] sentence = content.split("��|��");
			for(String s:sentence) {
				if(s.contains("����")&&(s.contains("Ҫ��")||s.contains("����")||s.contains("����"))){
					System.out.println(s);
					String[]  ysflag = {"����","�黹"};
					if(util.ifContainFlag(s,ysflag)){
						res = 1;
					}


				}
			}
		}
		return res;
	}
	/*
	�������Ժ���������л����񹲺͹���������������Ľ��ͣ�һ��������:�Է������干ͬ����
	 */
	public static int row18(Document document) {
		int res = 0;
		String content = util.getAJJBQKString(document);
		if(content!=null) {
			String[] sentence = content.split("��|��|��");
			for(String s:sentence) {
				if(s.contains("����")&&s.contains("����")&&s.contains("����")){
					System.out.println(s);



				}
			}
		}
		return res;
	}
/*
�������Ժ��������Ժ������鰸��������Ů������������ɾ��������3����������ĸ����Ҫ����Ů��������
 */
public static int row19(Document document) {
	int res = 0;
	String content = util.getAJJBQKString(document);
	if(content!=null) {
		String[] sentence = content.split("��|��");
		for(String s:sentence) {
			String[] znflag = {"��","Ů","����","��","�к�","Ů��"};
			if(util.ifContainFlag(s,znflag)){
				//System.out.println(s);
				//�չˣ��������������
				String[] fyflag = {"�չ�","����","����"};
				if(util.ifContainFlag(s,fyflag)){
					String[] qqflag = {"Ҫ��","����","ϣ��","����"};
					//Ҫ������ϣ��������
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
�л����񹲺͹�����������ʮһ��:˫����Ը���
 */
	public static int row100(Document document) {
		int res = 0;
		String content = util.getAJJBQKString(document);
		if(content!=null) {
			String[] sentence = content.split("��|��|��");
			for(String s:sentence) {
				if(s.contains("���")){
				//	System.out.println(s);
					if((s.contains("��Ը")||s.contains("ͬ��"))&&!s.contains("��ͬ��")&&!s.contains("����Ը")){
						System.out.println(s);
					}
				}
			}
		}
		return res;
	}






}
