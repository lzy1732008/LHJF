package LHJF;

import org.dom4j.Document;
import org.dom4j.Element;
import sun.jvm.hotspot.debugger.posix.elf.ELFException;

public class YS {
	private static Util util= new Util();
	/*
	 * row3:����ܲ���ͥ����;��ͥ
	 */

	public static int  row1(Document document,Element newroot) {
		Element root = document.getRootElement();
		Element SFBGJBDTHZTTTNode = newroot.addElement("SFBGJBDTHZTTT").addAttribute("nameCN", "�Ƿ񱻸�ܲ���ͥ����;��ͥ");
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
		SFBGJBDTHZTTTNode.addAttribute("value",res+"");
		return res;
	}
	/*
	 * ����ǰԭ�����볷��
	 */
	public static int  row2(Document document,Element newroot) {
		Element root = document.getRootElement();
		Element SFBGJBDTHZTTTNode = newroot.addElement("YGSQCS").addAttribute("nameCN", "ԭ�����볷��");

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
		SFBGJBDTHZTTTNode.addAttribute("value",res+"");
		return res;
	}
	/*
	 * �漰��Ů��������
	 */
	public static int  row3(Document document,Element newroot) {
		Element SFBGJBDTHZTTTNode = newroot.addElement("SJZNFYJY").addAttribute("nameCN", "�漰��Ů��������");

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
		SFBGJBDTHZTTTNode.addAttribute("value",res+"");
		return res;
	}
	/*
	 * �漰��Ů����ѡ�������
	 */
	public static int  row4(Document document,Element newroot) {
		Element SFBGJBDTHZTTTNode = newroot.addElement("SJZNSHFJYF").addAttribute("nameCN", "�漰��Ů����ѡ�������");

		int res = 0;
		String content = util.getAJJBQKString(document);
		String[] sentence = content.split("��|��");
		for(String s:sentence) {
			if(s.contains("��")||s.contains("Ů")||s.contains("��")||s.contains("Ů")||s.contains("��")) {
				res = 1;
				String[] ysFlag = {"�����","������","ѧ��","������"};
				System.out.println(s);
				if(util.ifContainFlag(s, ysFlag)) {
					res = 1;
				}
			}
		}
		SFBGJBDTHZTTTNode.addAttribute("value",res+"");
		return res;
	}
	/*
	 * �в�������Ů
	 */
	public static int row5(Document document,Element newroot) {
		Element SFBGJBDTHZTTTNode = newroot.addElement("YBRQZN").addAttribute("nameCN", "�в�������Ů");

		int res = 0;
		String content = util.getAJJBQKString(document);
		if(content!=null) {
			String regex = "��|��";
			String[] ysFlag = {"������","����"};
			util.ifContainYS(content, ysFlag, regex);
		}
		SFBGJBDTHZTTTNode.addAttribute("value",res+"");
		return res;
	}
	/*
	 * �л����񹲺͹��������Ϸ�����ʮ������֤��
	 */
	public static int row6(Document document,Element newroot) {
		Element SFBGJBDTHZTTTNode = newroot.addElement("ZJ").addAttribute("nameCN", "֤��");
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
		SFBGJBDTHZTTTNode.addAttribute("value",res+"");
		return res;
	}

	/*
	 * �л����񹲺͹��������Ϸ���һ����ʮ����:��һ����ʮ���� ��������Ժ�����ɳ��ķ�ͥ������ʵ�����Ȩ�������ϵ��ȷ�����鲻��ļ򵥵����°��������ñ��¹涨��
	 */
	public static int row7(Document document,Element newroot) {
		Element SFBGJBDTHZTTTNode = newroot.addElement("MSAJ").addAttribute("nameCN", "���°���");
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
		SFBGJBDTHZTTTNode.addAttribute("value",res+"");
		return res;
	}


	/*
	 * �л����񹲺͹�����������ʮ����:�漰��ĸ̽����Ů
	 */
	public static int row8(Document document,Element newroot) {
		Element SFBGJBDTHZTTTNode = newroot.addElement("SJFMTWZN").addAttribute("nameCN", "�漰��ĸ̽����Ů");
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
		SFBGJBDTHZTTTNode.addAttribute("value",res+"");
		return res;
	}
	/*
	 * �л����񹲺͹���������ʮ����:һ�����˲в���
	 */
	public static int row9(Document document,Element newroot) {
		Element SFBGJBDTHZTTTNode = newroot.addElement("YFYSCBZ").addAttribute("nameCN", "һ�����˲в���");
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
		SFBGJBDTHZTTTNode.addAttribute("value",res+"");
		return res;
	}
	/*
	 * �л����񹲺͹���������ʮ�������Ƿ��������������ͬ��ȷ��ֻ������һ���ĲƲ�
	 */
	public static int row10(Document document,Element newroot) {
		Element SFBGJBDTHZTTTNode = newroot.addElement("SFYYZHZYHTZQDZGFHQYFDCC").addAttribute("nameCN", "�Ƿ��������������ͬ��ȷ��ֻ������һ���ĲƲ�");
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
		SFBGJBDTHZTTTNode.addAttribute("value",res+"");
		return res;

	}
	/*
	 * �л����񹲺͹��������Ϸ���ʮ����:��������
	 */
	public static int row11(Document document,Element newroot) {
		Element SFBGJBDTHZTTTNode = newroot.addElement("MSSS").addAttribute("nameCN", "��������");

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
		SFBGJBDTHZTTTNode.addAttribute("value",res+"");
		return res;

	}

	/*
	 * �л����񹲺͹�����������ʮһ�������޹�ͬ���ծ
	 */
	public static int row12(Document document,Element newroot) {
		Element SFBGJBDTHZTTTNode = newroot.addElement("FQGTSHFZ").addAttribute("nameCN", "���޹�ͬ���ծ");
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
		SFBGJBDTHZTTTNode.addAttribute("value",res+"");
		return res;

	}
	/*
	 * �л����񹲺͹��������Ϸ���һ����ʮ��������������
	 */
	public static int row13(Document document,Element newroot) {
		Element SFBGJBDTHZTTTNode = newroot.addElement("GKSL").addAttribute("nameCN", "��������");
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
		SFBGJBDTHZTTTNode.addAttribute("value",res+"");
		return res;
	}

	/*
	 * �л����񹲺͹�������������ʮ����������һ����������
	 */
	public static int row14(Document document,Element newroot) {
		Element SFBGJBDTHZTTTNode = newroot.addElement("LHHYFSHKN").addAttribute("nameCN", "����һ����������");
		int res = 0;
		String content = util.getAJJBQKString(document);
		if(content!=null) {
			String[] sentence = content.split("��|��|��");
			for(String s:sentence) {
				if(s.contains("ԭ��")||s.contains("����")) {
					if (s.contains("����") ) {
						//ʹ�û�������
						String[] keypairs = {"����;����","����;����","�޷�;����"};
						if(util.windowForKey(s,keypairs,8)){
							System.out.println(s);
						}
					}

				}
			}
		}
		SFBGJBDTHZTTTNode.addAttribute("value",res+"");
		return res;
	}

	public static int row15(Document document,Element newroot) {
		Element SFBGJBDTHZTTTNode = newroot.addElement("QQFHCL").addAttribute("nameCN", "���󷵻�����");
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
		SFBGJBDTHZTTTNode.addAttribute("value",res+"");
		return res;
	}
	/*
	�������Ժ���������л����񹲺͹���������������Ľ��ͣ�һ��������:�Է������干ͬ����
	 */
	public static int row16(Document document,Element newroot) {
		Element SFBGJBDTHZTTTNode = newroot.addElement("YFQMYGTSH").addAttribute("nameCN", "�Է������干ͬ����");
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
		SFBGJBDTHZTTTNode.addAttribute("value",res+"");
		return res;
	}
	/*
    �������Ժ��������Ժ������鰸��������Ů������������ɾ��������3����������ĸ����Ҫ����Ů��������
     */
	public static int row17(Document document,Element newroot) {
		Element SFBGJBDTHZTTTNode = newroot.addElement("FFHMFJYQZNSQSH").addAttribute("nameCN", "������ĸ����Ҫ����Ů��������");
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
		SFBGJBDTHZTTTNode.addAttribute("value",res+"");
		return res;
	}
	/*
    �л����񹲺͹�����������ʮһ��:˫����Ը���
     */
	public static int row18(Document document,Element newroot) {
		Element SFBGJBDTHZTTTNode = newroot.addElement("SFZYLH").addAttribute("nameCN", "˫����Ը���");
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
		SFBGJBDTHZTTTNode.addAttribute("value",res+"");
		return res;
	}

/*
�л����񹲺͹�����������ʮ������������ͬ�ӻ��ػ飨�ػ飻����ż��������ͬ�ӣ�
 */
public static  int row19(Document document,Element newroot){
	Element SFBGJBDTHZTTTNode = newroot.addElement("YTRTJHCH").addAttribute("nameCN", "������ͬ�ӻ��ػ�");

	int JD =0;
	int flagq = 0;
	int flagh = 0;
	String qwStr = "";
	qwStr = util.getAJJBQKString(document);
	if(qwStr!=null && !qwStr.equals("")) {
		String[] qwStrarray = qwStr.split("��|��");
		if (qwStrarray != null) {
			for (String qw : qwStrarray) {
				if (qw.contains("������") || qw.contains("������") ||
						qw.contains("����") || qw.contains("ͬ��") ||
						qw.contains("����") || qw.contains("��������ϵ") || qwStr.contains("�ػ�")) {
					JD = 1;
					System.out.println(qw);
					break;
				}
			}

		}
	}
	SFBGJBDTHZTTTNode.addAttribute("value",JD+"");
	return JD;

}
/*
�л����񹲺͹�����������ʮ��������ϰ���Ĳ��������ȶ�ϰ�Ž̲��ģ�
 */

public static  int row20(Document document,Element newroot){
	Element SFBGJBDTHZTTTNode = newroot.addElement("EX").addAttribute("nameCN", "��ϰ");
	int JD =0;
	String qwStr = "";
	qwStr = util.getAJJBQKString(document);
	if(qwStr!=null && !qwStr.equals("")) {
		String[] qwStrarray = qwStr.split("��|��");
		if (qwStrarray != null) {
			for (String qw : qwStrarray) {
				if(qw.contains("�Ĳ�")||qw.contains("����")||qw.contains("���")||qw.contains("��ϰ")
						){
					JD = 1;
					System.out.println(qw);
					break;
				}
			}

		}
	}
	SFBGJBDTHZTTTNode.addAttribute("value",JD+"");
	return JD;

}
/*
�л����񹲺͹�����������ʮ������:�־�������
 */
	public static int row21(Document document, Element newroot) {
		Element SFBGJBDTHZTTTNode = newroot.addElement("FJMLN").addAttribute("nameCN", "�־�������");
		int res = 0;
		System.out.println(document.getName());
		String content = util.getAJJBQKString(document);
		if(content!=null) {
			String[] sentence = content.split("��|��");
			for(String s:sentence) {
				if(s.contains("�־�")||s.contains("��ҳ���")){
					//System.out.println(s);
					res = 1;

				}

			}
		}
		SFBGJBDTHZTTTNode.addAttribute("value",res+"");

		return res;
	}

	/*
	�漰���޹�ͬ�Ʋ�
	 */
	public static int row22(Document document,Element newroot) {
		Element SFBGJBDTHZTTTNode = newroot.addElement("SJFQGTCC").addAttribute("nameCN", "�漰���޹�ͬ�Ʋ�");
		int res = 0;
		System.out.println(document.getName());
		String content = util.getAJJBQKString(document);
		if(content!=null) {
			String[] sentence = content.split("��|��|��");
			for(String s:sentence){
				if(s.contains("����")&&s.contains("�Ʋ�")){
					System.out.println(s);
				}
			}


		}
		SFBGJBDTHZTTTNode.addAttribute("value",res+"");

		return res;
	}
/*
�ұ�

 */
/*
 * 10.�Ƿ���ڼ�ͥ������Ű����������ͥ��Ա������
 * ʹ��CMSSD�е�������Ϊ�ж�
 */
public static void row23(Document document, Element newroot){
	Element SFCZJTBLNodeYQJTCYNode = newroot.addElement("SFCZJTBLNodeYQJTCY").addAttribute("nameCN", "�Ƿ���ڼ�ͥ������Ű����������ͥ��Ա������");
	Element root = document.getRootElement();
	String JD ="0";
	int fnull = 0;
	if(root.element("AJJBQK")!=null){
		Element ajjbqkNode = root.element("AJJBQK");
		if(ajjbqkNode.element("CMSSD")!=null){
			Element cmssdNode = ajjbqkNode.element("CMSSD");
			if(cmssdNode.attribute("value")!=null){

				String qwStr = cmssdNode.attributeValue("value");
				System.out.println(qwStr);
				String[] qwStrarray = qwStr.split("��|��|��");
				for(String qw:qwStrarray){
					if(( qw.contains("Ź��")||qw.contains("Ű��")|| qw.contains("����")||
							qw.contains("����")||qw.contains("����")||qw.contains("����"))){
						fnull = 1;
						if(( qw.contains("Ź��")||qw.contains("Ű��")|| qw.contains("����")||
								qw.contains("����")||qw.contains("����")||qw.contains("����"))&&
								(!qw.contains("��δ")||!qw.contains("û��")||!qw.contains("��δ"))){
							JD = "1";
						}
					}
				}

			}
		}
	}
	System.out.println(JD);

		SFCZJTBLNodeYQJTCYNode.addAttribute("value",JD);

}
/*
��2����������Ů
 */
public static int row24(Document document,Element newroot) {
	Element SFBGJBDTHZTTTNode = newroot.addElement("YLZSYXZN").addAttribute("nameCN", "��2����һ����Ů");
	int res = 0;
	System.out.println(document.getName());
	String content = util.getAJJBQKString(document);
	if(content!=null) {
		String[] sentence = content.split("��|��");
		for(String s:sentence) {
			if ((s.contains("����") || s.contains("ԭ������") || s.contains("˫��")) && (s.contains("��") || s.contains("Ů") || s.contains("����") || s.contains("�к�"))) {
				System.out.println("all" + s);
				if ((s.contains("������") || s.contains("����") || s.contains("2����") || s.contains("2��")) && (s.contains("δ��") && s.contains("����") || s.contains("����") || s.contains("δ��") || s.contains("����"))) {
					System.out.println(s);
					res = 1;
					break;
				}
			}
		}
	}
	SFBGJBDTHZTTTNode.addAttribute("value",res+"");
	return res;
}
/*
���ǰ��Ů�Ƿ����游ĸ����
 */
public static int row25(Document document,Element newroot) {
	Element SFBGJBDTHZTTTNode = newroot.addElement("YLZSYXZN").addAttribute("nameCN", "��2����һ����Ů");
	int res = 0;
	System.out.println(document.getName());
	String content = util.getAJJBQKString(document);
	if(content!=null) {
		String[] sentence = content.split("��|��");
		for(String s:sentence) {
			if((s.contains("��ĸ")||s.contains("�游")||s.contains("үү")||
					s.contains("����")||s.contains("�⹫")||s.contains("����"))&&(s.contains("����")||s.contains("��ס")||s.contains("����")||
					s.contains("�ɳ�"))) {
				System.out.println(s);
				res = 1;


			}
		}
	}
	SFBGJBDTHZTTTNode.addAttribute("value",res+"");
	return res;
}




}

