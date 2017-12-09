package LHJF;

import org.dom4j.Document;
import org.dom4j.Element;

public class YS {
	private static Util util = new Util();
	/*
	 * row3:被告拒不到庭或中途退庭
	 */

	public static int row1(Document document, Element newroot) {
		Element root = document.getRootElement();
		Element SFBGJBDTHZTTTNode = newroot.addElement("SFBGJBDTHZTTT").addAttribute("nameCN", "是否被告拒不到庭或中途退庭");
		int res = 0;

		Element docele = root.element("QW");
		if (docele != null) {
			if (docele.attribute("value") != null) {
				String content = docele.attributeValue("value");
				if (content != null && !content.equals("")) {
					String[] sentence = content.split("。|；");
					for (String s : sentence) {
						if (s.contains("被告") && (s.contains("退庭") || s.contains("不到庭") || s.contains("未到庭"))) {
							res = 1;
							break;
						}
					}
				}
			}
		}
		SFBGJBDTHZTTTNode.addAttribute("value", res + "");
		return res;
	}

	/*
	 * 宣判前原告申请撤诉
	 */
	public static int row2(Document document, Element newroot) {
		Element root = document.getRootElement();
		Element SFBGJBDTHZTTTNode = newroot.addElement("YGSQCS").addAttribute("nameCN", "原告申请撤诉");

		int res = 0;

		Element docele = root.element("QW");
		if (docele != null) {
			if (docele.attribute("value") != null) {
				String content = docele.attributeValue("value");
				if (content != null && !content.equals("")) {
					String[] sentence = content.split("。|；");
					for (String s : sentence) {
						if (s.contains("原告") && s.contains("撤") && s.contains("诉")) {
							res = 1;
							break;
							//System.out.println(s);
						}
					}
				}

			}
		}
		SFBGJBDTHZTTTNode.addAttribute("value", res + "");
		return res;
	}

	/*
	 * 涉及子女抚养教
	 */
	public static int row3(Document document, Element newroot) {
		Element SFBGJBDTHZTTTNode = newroot.addElement("SJZNFYJY").addAttribute("nameCN", "涉及子女抚养教育");

		int res = 0;
		String content = util.getAJJBQKString(document);

		if (content != null && !content.equals("")) {
			String[] sentence = content.split("。|；");
			for (String s : sentence) {
				if (s.contains("子") || s.contains("女") || s.contains("男") || s.contains("女") || s.contains("孩")) {
					
					String[] ysFlag = {"教育", "抚养", "学业"};
					System.out.println(s);
					if (util.ifContainFlag(s, ysFlag)) {
						res = 1;
						break;
					}
				}
			}
		}
		SFBGJBDTHZTTTNode.addAttribute("value", res + "");
		return res;
	}

	/*
	 * 涉及子女生活费、教育费
	 */
	public static int row4(Document document, Element newroot) {
		Element SFBGJBDTHZTTTNode = newroot.addElement("SJZNSHFJYF").addAttribute("nameCN", "涉及子女生活费、教育费");

		int res = 0;
		String content = util.getAJJBQKString(document);
		if (content != null && !content.equals("")) {
			String[] sentence = content.split("。|；");
			for (String s : sentence) {
				if (s.contains("子") || s.contains("女") || s.contains("男") || s.contains("女") || s.contains("孩")) {
					res = 1;
					String[] ysFlag = {"生活费", "抚养费", "学费", "教育费"};
					System.out.println(s);
					if (util.ifContainFlag(s, ysFlag)) {
						res = 1;
						break;
					}
				}
			}
		}
		SFBGJBDTHZTTTNode.addAttribute("value", res + "");
		return res;
	}

	/*
	 * 有哺乳期子女
	 */
	public static int row5(Document document, Element newroot) {
		Element SFBGJBDTHZTTTNode = newroot.addElement("YBRQZN").addAttribute("nameCN", "有哺乳期子女");

		int res = 0;
		String content = util.getAJJBQKString(document);
		if (content != null && !content.equals("")) {
			String regex = "。|；";
			String[] ysFlag = {"哺乳期", "襁褓"};
			util.ifContainYS(content, ysFlag, regex);
		}
		SFBGJBDTHZTTTNode.addAttribute("value", res + "");
		return res;
	}

	/*
	 * 中华人民共和国民事诉讼法第六十四条：证据
	 */
	public static int row6(Document document, Element newroot) {
		Element SFBGJBDTHZTTTNode = newroot.addElement("ZJ").addAttribute("nameCN", "证据");
		int res = 0;
		String content = util.getAJJBQKString(document);
		if (content != null && !content.equals("")) {
			String regex = "。|；|，";
			String[] sentence = content.split(regex);
			for (String s : sentence) {
				if (s.contains("证据")) {
					System.out.println("证据：" + s);
					if (s.contains("提供") || s.contains("收集") || s.contains("调查")) {
						res = 1;
						break;
						//System.out.println("证据22："+s);
					}
				}
			}
		}
		SFBGJBDTHZTTTNode.addAttribute("value", res + "");
		return res;
	}

	/*
	 * 中华人民共和国民事诉讼法第一百四十二条:第一百四十二条 基层人民法院和它派出的法庭审理事实清楚、权利义务关系明确、争议不大的简单的民事案件，适用本章规定。
	 */
	public static int row7(Document document, Element newroot) {
		Element SFBGJBDTHZTTTNode = newroot.addElement("MSAJ").addAttribute("nameCN", "民事案件");
		int res = 0;
		String content = util.getAJJBQKString(document);
		String regex = "。|；|，";
		if (content != null && !content.equals("")) {
			String[] sentence = content.split(regex);
			for (String s : sentence) {
				if (s.contains("简单") || s.contains("简易")) {
					System.out.println("简单：" + s);
					if (s.contains("民事案件") || s.contains("程序")) {
						res = 1;
						//System.out.println("民事案件："+s);
						break;
					}
				}
			}
		}
		SFBGJBDTHZTTTNode.addAttribute("value", res + "");
		return res;
	}


	/*
	 * 中华人民共和国婚姻法第三十八条:涉及父母探望子女
	 */
	public static int row8(Document document, Element newroot) {
		Element SFBGJBDTHZTTTNode = newroot.addElement("SJFMTWZN").addAttribute("nameCN", "涉及父母探望子女");
		int res = 0;
		String content = util.getAJJBQKString(document);
		if (content != null && !content.equals("")) {
			String[] sentence = content.split("。|；");
			for (String s : sentence) {
				if (s.contains("子") || s.contains("女") || s.contains("男") || s.contains("女") || s.contains("孩")) {
					res = 1;
					String[] ysFlag = {"探望", "看望"};
					//  System.out.println(s);
					if (util.ifContainFlag(s, ysFlag)) {
						System.out.println("看望》》》》》》》》》》》》》" + s);
						res = 1;
						break;
					}
				}
			}
		}
		SFBGJBDTHZTTTNode.addAttribute("value", res + "");
		return res;
	}

	/*
	 * 中华人民共和国婚姻法第十八条:一方因身体受到伤害获得的医疗费、残疾人生活补助费等费用；****************************
	 */
	public static int row9(Document document, Element newroot) {
		Element SFBGJBDTHZTTTNode = newroot.addElement("YFYLFCJRSHBZF").addAttribute("nameCN", "一方医疗费、残疾人生活补助费等费用");
		int res = 0;
		String content = util.getAJJBQKString(document);
		if (content != null && !content.equals("")) {
			String[] sentence = content.split("。|；|，");
			for (String s : sentence) {
				if (s.contains("伤残补助") || s.contains("伤残费") || s.contains("伤残金")) {
					//System.out.println(s);
					res = 1;
					break;
				}
				else if(s.contains("伤")&&s.contains("医疗费")) {
					res = 1;
					break;
				}
			}
		}
		SFBGJBDTHZTTTNode.addAttribute("value", res + "");
		return res;
	}

	/*
	 * 中华人民共和国婚姻法第十八条：是否有遗嘱或赠与合同中确定只归夫或妻一方的财产***********************************
	 */
	public static int row10(Document document, Element newroot) {
		Element SFBGJBDTHZTTTNode = newroot.addElement("SFYYZHZYHTZQDZGFHQYFDCC").addAttribute("nameCN", "是否有遗嘱或赠与合同中确定只归夫或妻一方的财产");
		int res = 0;
		String content = util.getAJJBQKString(document);
		if (content != null && !content.equals("")) {
			String[] sentence = content.split("。|；|，");
			for (String s : sentence) {
				if (s.contains("遗嘱") || s.contains("赠与") || s.contains("遗赠")||(s.contains("个人")&&s.contains("财产"))) {
					System.out.println(s);
					if (!s.contains("双方") && !s.contains("共同")) {
						//System.out.print("双方"+s);
						res = 1;
						break;
					}

				}
			}
		}
		SFBGJBDTHZTTTNode.addAttribute("value", res + "");
		return res;

	}

	/*
	 * 中华人民共和国民事诉讼法第十三条:民事诉讼
	 */
	public static int row11(Document document, Element newroot) {
		Element SFBGJBDTHZTTTNode = newroot.addElement("MSSS").addAttribute("nameCN", "民事诉讼");

		int res = 0;
		String content = util.getAJJBQKString(document);
		if (content != null && !content.equals("")) {
			String[] sentence = content.split("。|；|，");
			for (String s : sentence) {
				if (s.contains("民事")) {
					res = 1;
					break;
				}
			}
		}
		SFBGJBDTHZTTTNode.addAttribute("value", res + "");
		return res;

	}

	/*
	 * 中华人民共和国婚姻法第四十一条：夫妻共同生活负债
	 */
	public static int row12(Document document, Element newroot) {
		Element SFBGJBDTHZTTTNode = newroot.addElement("FQGTSHFZ").addAttribute("nameCN", "夫妻共同生活负债");
		int res = 0;
		String content = util.getAJJBQKString(document);
		if (content != null && !content.equals("")) {
			String[] sentence = content.split("。|；|，");
			for (String s : sentence) {
				if (s.contains("共同") || s.contains("双方")) {
					String[] ysFlag = {"债务", "欠款", "负债"};
					if (util.ifContainFlag(s, ysFlag)) {
						res = 1;
						break;
					}
				}
			}
		}
		SFBGJBDTHZTTTNode.addAttribute("value", res + "");
		return res;

	}

	/*
	 * 中华人民共和国民事诉讼法第一百三十四条：公开审理
	 */
	public static int row13(Document document, Element newroot) {
		Element SFBGJBDTHZTTTNode = newroot.addElement("GKSL").addAttribute("nameCN", "公开审理");
		int res = 0;
		String content = util.getAJJBQKString(document);
		if (content != null && !content.equals("")) {
			String[] sentence = content.split("。|；|，");
			for (String s : sentence) {
				if (s.contains("公开") && s.contains("审理")) {
//	 	   			 String[] ysFlag = {"债务","欠款","负债"};
//	 	   			 if(util.ifContainFlag(s, ysFlag)) {
//                        res = 1;
//	 	   			 }
					//System.out.println(s);
					res = 1;
					break;
				}
			}
		}
		SFBGJBDTHZTTTNode.addAttribute("value", res + "");
		return res;
	}

	/*
	 * 中华人民共和国婚姻法：第四十二条，离婚后一方生活困难******************
	 */
	public static int row14(Document document, Element newroot) {
		Element SFBGJBDTHZTTTNode = newroot.addElement("LHHYFSHKN").addAttribute("nameCN", "离婚后一方生活困难");
		int res = 0;
		String content = util.getAJJBQKString(document);
		if (content != null && !content.equals("")) {
			String[] sentence = content.split("。|；");
			for (String s : sentence) {
			
					if (s.contains("生活")) {
						//使用滑动窗口
						String[] keypairs = {"生活;困难", "生活;艰难", "无法;生活","保障;生活","生活;补助"};
						if (util.windowForKey(s, keypairs, 8)) {
							res = 1;
							break;
						}
						if(s.contains("补偿")&&(s.contains("原告")||s.contains("被告"))) {
							res  = 1;
							break;
						}
						
					

				}
			}
		}
		SFBGJBDTHZTTTNode.addAttribute("value", res + "");
		return res;
	}

	public static int row15(Document document, Element newroot) {
		Element SFBGJBDTHZTTTNode = newroot.addElement("QQFHCL").addAttribute("nameCN", "请求返还彩礼");
		int res = 0;
		String content = util.getAJJBQKString(document);
		if (content != null && !content.equals("")) {
			String[] sentence = content.split("。|；");
			for (String s : sentence) {
				if (s.contains("彩礼") && (s.contains("要求") || s.contains("主张") || s.contains("请求"))) {
					System.out.println(s);
					String[] ysflag = {"返还", "归还"};
					if (util.ifContainFlag(s, ysflag)) {
						res = 1;
						break;
					}


				}
			}
		}
		SFBGJBDTHZTTTNode.addAttribute("value", res + "");
		return res;
	}

	/*
	最高人民法院关于适用中华人民共和国婚姻法若干问题的解释（一）第五条:以夫妻名义共同生活
	 */
	public static int row16(Document document, Element newroot) {
		Element SFBGJBDTHZTTTNode = newroot.addElement("YFQMYGTSH").addAttribute("nameCN", "以夫妻名义共同生活");
		int res = 0;
		String content = util.getAJJBQKString(document);
		if (content != null && !content.equals("")) {
			String[] sentence = content.split("。|；|，");
			for (String s : sentence) {
				if (s.contains("生活") && s.contains("夫妻") && s.contains("名义")) {
					res = 1;
					break;


				}
			}
		}
		SFBGJBDTHZTTTNode.addAttribute("value", res + "");
		return res;
	}

	/*
    最高人民法院关于人民法院审理离婚案件处理子女抚养问题的若干具体意见第3条：父方和母方均要求子女随其生活
     */
	public static int row17(Document document, Element newroot) {
		Element SFBGJBDTHZTTTNode = newroot.addElement("FFHMFJYQZNSQSH").addAttribute("nameCN", "父方和母方均要求子女随其生活");
		int res = 0;
		String content = util.getAJJBQKString(document);
		if (content != null && !content.equals("")) {
			String[] sentence = content.split("。|；");
			for (String s : sentence) {
				String[] znflag = {"子", "女", "孩子", "儿", "男孩", "女孩"};
				if (util.ifContainFlag(s, znflag)) {
					//System.out.println(s);
					//照顾，抚养，生活，抚育
					String[] fyflag = {"照顾", "抚养", "生活"};
					if (util.ifContainFlag(s, fyflag)) {
						String[] qqflag = {"要求", "请求", "希望", "争议"};
						//要求、请求、希望、争议
						if (util.ifContainFlag(s, qqflag)) {
							//System.out.println(s);
							res = 1;
							break;
						}
					}

				}
			}
		}
		SFBGJBDTHZTTTNode.addAttribute("value", res + "");
		return res;
	}

	/*
    中华人民共和国婚姻法第三十一条:双方自愿离婚***************
     */
	public static int row18(Document document, Element newroot) {
		Element SFBGJBDTHZTTTNode = newroot.addElement("SFZYLH").addAttribute("nameCN", "双方自愿离婚");
		int res = 0;
		String content = util.getAJJBQKString(document);
		if (content != null && !content.equals("")) {
			String[] sentence = content.split("。|；");
			for (String s : sentence) {
				if (s.contains("离婚")) {
					//	System.out.println(s);
					if ((s.contains("自愿") || s.contains("同意")) ) {
						//System.out.println(s);
						res = 1;
						break;
					}
				}
			}
		}
		SFBGJBDTHZTTTNode.addAttribute("value", res + "");
		return res;
	}

	/*
    中华人民共和国婚姻法第三十二条：与他人同居或重婚（重婚；有配偶者与他人同居）
     */
	public static int row19(Document document, Element newroot) {
		Element SFBGJBDTHZTTTNode = newroot.addElement("YTRTJHCH").addAttribute("nameCN", "与他人同居或重婚");

		int JD = 0;
		int flagq = 0;
		int flagh = 0;
		String qwStr = "";
		qwStr = util.getAJJBQKString(document);
		if (qwStr != null && !qwStr.equals("")) {
			String[] qwStrarray = qwStr.split("，|。");
			if (qwStrarray != null) {
				for (String qw : qwStrarray) {
					if (qw.contains("婚外情") || qw.contains("第三者") ||
							qw.contains("出轨") || qw.contains("同居") ||
							qw.contains("暧昧") || qw.contains("不正当关系") || qwStr.contains("重婚")) {
						JD = 1;
						//System.out.println(qw);
						break;
					}
				}

			}
		}
		SFBGJBDTHZTTTNode.addAttribute("value", JD + "");
		return JD;

	}
/*
中华人民共和国婚姻法第三十二条：恶习（赌博、吸毒等恶习屡教不改）
 */

	public static int row20(Document document, Element newroot) {
		Element SFBGJBDTHZTTTNode = newroot.addElement("EX").addAttribute("nameCN", "恶习");
		int JD = 0;
		String qwStr = "";
		qwStr = util.getAJJBQKString(document);
		if (qwStr != null && !qwStr.equals("")) {
			String[] qwStrarray = qwStr.split("，|。");
			if (qwStrarray != null) {
				for (String qw : qwStrarray) {
					if (qw.contains("赌博") || qw.contains("吸毒") || qw.contains("酗酒") || qw.contains("恶习")
							) {
						JD = 1;
						//System.out.println(qw);
						break;
					}
				}

			}
		}
		SFBGJBDTHZTTTNode.addAttribute("value", JD + "");
		return JD;

	}

	/*
    中华人民共和国婚姻法第三十二条：:分居满两年
     */
	public static int row21(Document document, Element newroot) {
		Element SFBGJBDTHZTTTNode = newroot.addElement("FJMLN").addAttribute("nameCN", "分居满两年");
		int res = 0;
		System.out.println(document.getName());
		String content = util.getAJJBQKString(document);
		if (content != null && !content.equals("")) {
			String[] sentence = content.split("。|；");
			for (String s : sentence) {
				if (s.contains("分居") || s.contains("离家出走")) {
					//System.out.println(s);
					res = 1;
					break;
				}

			}
		}
		SFBGJBDTHZTTTNode.addAttribute("value", res + "");

		return res;
	}

	/*
	涉及夫妻共同财产********************************添加上“家中财产”
	 */
	public static int row22(Document document, Element newroot) {
		Element SFBGJBDTHZTTTNode = newroot.addElement("SJFQGTCC").addAttribute("nameCN", "涉及夫妻共同财产");
		int res = 0;
		System.out.println(document.getName());
		String content = util.getAJJBQKString(document);
		if (content != null && !content.equals("")) {
			String[] sentence = content.split("，|；|。");
			for (String s : sentence) {
				if (s.contains("夫妻") && s.contains("财产")) {
					//	System.out.println(s);
					res = 1;
					break;
				}
			}


		}
		SFBGJBDTHZTTTNode.addAttribute("value", res + "");

		return res;
	}

	/*
    家暴

     */
	/*
	 * 10.是否存在家庭暴力或虐待、遗弃家庭成员的情形
	 * 使用CMSSD中的内容作为判断
	 */
	public static void row23(Document document, Element newroot) {
		Element SFCZJTBLNodeYQJTCYNode = newroot.addElement("SFCZJTBLNodeYQJTCY").addAttribute("nameCN", "是否存在家庭暴力或虐待、遗弃家庭成员的情形");
		Element root = document.getRootElement();
		String JD = "0";
		int fnull = 0;
		if (root.element("AJJBQK") != null) {
			Element ajjbqkNode = root.element("AJJBQK");
			if (ajjbqkNode.element("CMSSD") != null) {
				Element cmssdNode = ajjbqkNode.element("CMSSD");
				if (cmssdNode.attribute("value") != null) {

					String qwStr = cmssdNode.attributeValue("value");
					//System.out.println(qwStr);
					if (qwStr != null && !qwStr.equals("")) {
						String[] qwStrarray = qwStr.split("，|。|；");
						for (String qw : qwStrarray) {
							if ((qw.contains("殴打") || qw.contains("虐待") || qw.contains("暴力") ||
									qw.contains("抛弃") || qw.contains("遗弃") || qw.contains("动手"))) {
								fnull = 1;
								if ((qw.contains("殴打") || qw.contains("虐待") || qw.contains("暴力") ||
										qw.contains("抛弃") || qw.contains("遗弃") || qw.contains("动手")) &&
										(!qw.contains("并未") || !qw.contains("没有") || !qw.contains("从未"))) {
									JD = "1";
									break;
								}
							}
						}
					}

				}
			}
		}
		System.out.println(JD);

		SFCZJTBLNodeYQJTCYNode.addAttribute("value", JD);

	}

	/*
    有2周岁以下子女************************
     */
	public static int row24(Document document, Element newroot) {
		Element SFBGJBDTHZTTTNode = newroot.addElement("YLZSYXZN").addAttribute("nameCN", "有2周岁以下子女");
		int res = 0;
		System.out.println(document.getName());
		String content = util.getAJJBQKString(document);
		if (content != null && !content.equals("")) {
			String[] sentence = content.split("；|。");
			for (String s : sentence) {
				if ((s.contains("婚生") || s.contains("原、被告") || s.contains("双方")) && (s.contains("子") || s.contains("女") || s.contains("孩子") || s.contains("男孩"))) {
					System.out.println("all" + s);
					if ((s.contains("两周岁") || s.contains("两岁") || s.contains("2周岁") || s.contains("2岁")) && (s.contains("未满") && s.contains("不满") || s.contains("不足") || s.contains("未足") || s.contains("以下"))) {
						//System.out.println(s);
						res = 1;
						break;
					}
					if(s.contains("一岁")||s.contains("一周岁")||s.contains("1岁")||s.contains("1周岁")||s.contains("未满周岁")||s.contains("哺乳期")){
						res = 1;
						break;
					}
				}
			}
		}
		SFBGJBDTHZTTTNode.addAttribute("value", res + "");
		return res;
	}

	/*
    离婚前子女是否随祖父母生活
     */
	public static int row25(Document document, Element newroot) {
		Element SFBGJBDTHZTTTNode = newroot.addElement("LHQZNSFSZFMSH").addAttribute("nameCN", "离婚前子女是否随祖父母生活");
		int res = 0;
		System.out.println(document.getName());
		String content = util.getAJJBQKString(document);
		if (content != null && !content.equals("")) {
			String[] sentence = content.split("；|。");
			for (String s : sentence) {
				if ((s.contains("祖母") || s.contains("祖父") || s.contains("爷爷") ||
						s.contains("奶奶") || s.contains("外公") || s.contains("外婆")) && (s.contains("生活") || s.contains("居住") || s.contains("抚养") ||
						s.contains("成长"))) {
					//	System.out.println(s);
					res = 1;
					break;

				}
			}
		}
		SFBGJBDTHZTTTNode.addAttribute("value", res + "");
		return res;
	}

	/*
        一方被依法判处长期徒刑或违法
        */
	public static int row26(Document document, Element newroot) {
		Element SFBGJBDTHZTTTNode = newroot.addElement("YFBYFPCCQTXHWF").addAttribute("nameCN", "一方被依法判处长期徒刑或违法");
		int res = 0;
		System.out.println(document.getName());
		String content = util.getAJJBQKString(document);
		if (content != null && !content.equals("")) {
			String[] sentence = content.split("；|。");
			for (String s : sentence) {
				if (s.contains("判刑") || s.contains("服刑") || s.contains("判处") || s.contains("有期徒刑") ||
						(s.contains("犯") && s.contains("罪")) || s.contains("违法") || s.contains("刑")) {
					//	System.out.println(s);
					res = 1;
					break;

				}
			}
		}
		SFBGJBDTHZTTTNode.addAttribute("value", res + "");
		return res;
	}

	/*
    婚后未建立起夫妻感情***************************
     */
	public static int row27(Document document, Element newroot) {
	  int res = 0 ;
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
  			 res = 1;
  		 }
       }
  	  
  	    HQQFLJNode.addAttribute("value", res+"");
		return res;
	}

	/*
    婚前财产
     */
	public static int row28(Document document, Element newroot) {
		Element SFBGJBDTHZTTTNode = newroot.addElement("HQCC").addAttribute("nameCN", "婚前财产");
		int res = 0;
		System.out.println(document.getName());
		String content = util.getAJJBQKString(document);
		if (content != null && !content.equals("")) {
			String[] sentence = content.split("；|。");
			for (String s : sentence) {
				if (s.contains("婚前") && s.contains("财产")) {
					res = 1;
					System.out.println(s);
					break;
				}
			}
		}
		SFBGJBDTHZTTTNode.addAttribute("value", res + "");
		return res;
	}


	/*
    涉及赡养父母*****************
     */
	public static int row29(Document document, Element newroot) {
		Element SFBGJBDTHZTTTNode = newroot.addElement("SYFM").addAttribute("nameCN", "赡养父母");
		int res = 0;
		System.out.println(document.getName());
		String content = util.getAJJBQKString(document);
		if (content != null && !content.equals("")) {
			String[] sentence = content.split("；|。|，");
			for (String s : sentence) {
				if (s.contains("赡养")) {


					res = 1;
					System.out.println(s);
					break;
				}
			}
		}
		SFBGJBDTHZTTTNode.addAttribute("value", res + "");
		return res;
	}

	/*
    已结婚登记
     */
	public static int row30(Document document, Element newroot) {
		Element SFBGJBDTHZTTTNode = newroot.addElement("YJHDJ").addAttribute("nameCN", "已结婚登记");
		int res = 0;
		System.out.println(document.getName());
		String content = util.getAJJBQKString(document);
		if (content != null && !content.equals("")) {
			String[] sentence = content.split("；|。");
			for (String s : sentence) {
				if ((s.contains("结婚") && s.contains("登记"))||(s.contains("领证"))) {
					res = 1;
					System.out.println(s);
					break;
				}
			}
		}
		SFBGJBDTHZTTTNode.addAttribute("value", res + "");
		return res;
	}

	/*
    家庭关系是否和睦*************************建议删除
     */
	public static int row31(Document document, Element newroot) {
		Element SFBGJBDTHZTTTNode = newroot.addElement("JTGXSFHM").addAttribute("nameCN", "家庭关系是否和睦");
		int res = 0;
		System.out.println(document.getName());
		String content = util.getAJJBQKString(document);
		if (content != null && !content.equals("")) {
			String[] sentence = content.split("；|。|，");
			for (String s : sentence) {
				if (s.contains("家庭") || s.contains("相处") || s.contains("关系")) {

					if (s.contains("和睦") || s.contains("融洽")) {
						if (!s.contains("难以") && !s.contains("不") && !s.contains("无法")) {
							res = 1;
							break;
						}
					}
				}
				
				
				
			}

		}
		SFBGJBDTHZTTTNode.addAttribute("value", res + "");
		return res;
	}

	/*
	财产转移
	 */
	public static int row32(Document document, Element newroot) {
		Element SFBGJBDTHZTTTNode = newroot.addElement("CCZY").addAttribute("nameCN", "擦产转移");
		int res = 0;
		System.out.println(document.getName());
		String content = util.getAJJBQKString(document);
		if (content != null && !content.equals("")) {
			String[] sentence = content.split("；|。");
			for (String s : sentence) {
				if(s.contains("转移")){
					res = 1;
					break;
				}
			}

		}
		SFBGJBDTHZTTTNode.addAttribute("value", res + "");
		return res;
	}



}

