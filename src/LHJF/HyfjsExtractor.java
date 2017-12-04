package LHJF;

import java.io.IOException;
import java.util.List;

import javax.xml.parsers.ParserConfigurationException;
import javax.xml.xpath.XPathExpressionException;

import org.dom4j.Document;
import org.dom4j.DocumentException;
import org.dom4j.DocumentHelper;
import org.dom4j.Element;
import org.dom4j.io.OutputFormat;
import org.dom4j.io.SAXReader;
import org.dom4j.io.XMLWriter;
import org.dom4j.Attribute;
import org.xml.sax.SAXException;

import util.SplitUtil;
import util.StringUtil;
import util.XMLReader;
/**
 * 对婚姻法解释一二三的内容抽取类
 * @author 服兰
 *
 */
public class HyfjsExtractor {

	/**
	 * 父母为子女购房
	 */
	public static void parentBuyHouse(String path,Document document,Element newroot) throws XPathExpressionException, ParserConfigurationException, SAXException, IOException{
		Element root = document.getRootElement();
		Element FMWZNGFNode = newroot.addElement("FMWZNGF").addAttribute("nameCN", "父母为子女购房");
		String nodepath="//QW//AJJBQK//CMSSD/@value";
		String cmssd = XMLReader.getXMLNode(path, nodepath);
		String cpfxgcpath = "//QW//CPFXGC/@value";
		String cpfxgc = XMLReader.getXMLNode(path, cpfxgcpath);
		boolean flag = false;
		if(!StringUtil.isBlank(cmssd+cpfxgc)){
			List<String> splitResult = SplitUtil.getWholeContent(cmssd);
			for(String ss:splitResult){
				if((StringUtil.contains(ss, "父母")||StringUtil.contains(ss, "母亲")||StringUtil.contains(ss, "父亲")||StringUtil.contains(ss, "之父")||StringUtil.contains(ss, "之母"))&&
						(StringUtil.contains(ss, "房屋")||StringUtil.contains(ss, "房子")||StringUtil.contains(ss, "房款")||StringUtil.contains(ss, "建楼款"))&&
						(StringUtil.contains(ss, "出资")&&StringUtil.contains(ss, "给付"))){
					flag = true;
					break;
				}
			}
		}
		if(flag){
			FMWZNGFNode.addAttribute("value", "1");
		}else{
			FMWZNGFNode.addAttribute("value", "0");
		}
	}

	/**
	 * 不能独立生活的子女
	 * 婚姻法第二十一条规定的“不能独立生活的子女”，
	 * 是指尚在校接受高中及其以下学历教育，或者丧失或未完全丧失劳动能力等非因主观原因而无法维持正常生活的成年子女。
	 */
	public static void hasChildrenIndependent(String path,Document document,Element newroot) throws XPathExpressionException, ParserConfigurationException, SAXException, IOException{
//		先做关于年龄的，在做关于学历的，在做关于身体残疾的
		Element root = document.getRootElement();
		Element DLSHZNNode = newroot.addElement("DLSHZN").addAttribute("nameCN", "不能独立生活的子女");
		String nodepath="//QW//AJJBQK/@value";
		String ajjbqk = XMLReader.getXMLNode(path, nodepath);
		boolean flag = false;
		if(!StringUtil.isBlank(ajjbqk)){
			List<String> ajjbqkList = SplitUtil.getWholeContent(ajjbqk);
			for(String s:ajjbqkList){
//				首先必须关于子女
				if(s.contains("孩子")||s.contains("子女")||s.contains("子女")||s.contains("婚生")||s.contains("生育")||s.contains("儿子")||s.contains("女儿")){
//					残疾
					if(s.contains("残疾")||s.contains("劳动能力")){
						flag = true;
						break;
					}
//					读书的情况
					else if(s.contains("年级")||s.contains("学校")||s.contains("初中")||s.contains("小学")||s.contains("高中")||s.contains("大学")||s.contains("中学")){
						flag = true;
						break;
					}
				}
			}
		}
		if(flag){
			DLSHZNNode.addAttribute("value", "1");
		}else{
			DLSHZNNode.addAttribute("value", "0");
		}
	}


	/**
	 * 以夫妻名义生活
	 * 当事人依据婚姻法第十条规定向人民法院申请宣告婚姻无效的，申请时，法定的无效婚姻情形已经消失的，人民法院不予支持。
	 */
	public static void dliveAsspouse(String path,Document document,Element newroot) throws XPathExpressionException, ParserConfigurationException, SAXException, IOException{

	}

	/**
	 * 涉及房屋
	 * 在案件基本情况中是否包含“房屋”
	 */
	public static void aboutHouse(String path,Document document,Element newroot) throws XPathExpressionException, ParserConfigurationException, SAXException, IOException{
		Element root = document.getRootElement();
		Element node = newroot.addElement("SJFW").addAttribute("nameCN", "涉及房屋");
		String nodepath="//QW//AJJBQK/@value";
		String ajjbqk = XMLReader.getXMLNode(path, nodepath);
		boolean flag = false;
		String[] words = {"房屋","住房","房子"};
		if(!StringUtil.isBlank(ajjbqk) && SplitUtil.containsAll(ajjbqk, words)){
			node.addAttribute("value", "1");
		}else{
			node.addAttribute("value", "0");
		}
	}

	/**
	 * 涉及夫妻间互相抚养
	 * 在案件基本情况中是否包含“房屋”
	 */
	public static void raiseSpouse(String path,Document document,Element newroot) throws XPathExpressionException, ParserConfigurationException, SAXException, IOException{
		Element root = document.getRootElement();
		Element node = newroot.addElement("FQJHXFY").addAttribute("nameCN", "涉及夫妻间互相抚养");
		String nodepath="//QW//AJJBQK/@value";
		String ajjbqk = XMLReader.getXMLNode(path, nodepath);
		boolean flag = false;
		if(!StringUtil.isBlank(ajjbqk)){
			List<String> ajjbqkList = SplitUtil.getWholeContent(ajjbqk);
			String[] wordsSf = {"原告","被告"};
			String[] wordsKey = {"治疗","疾病","患有","患病","医疗费","瘫痪","伤残"};
			String[] wordsKey2 = {"经济来源","生活困难","无收入","无其他收入"};
			for(String s:ajjbqkList){
				if(SplitUtil.containsAll(s, wordsSf)&&(SplitUtil.containsAll(s, wordsKey)||SplitUtil.containsAll(s, wordsKey2))){
					flag = true;
					break;
				}
			}
		}
		if(flag)
			node.addAttribute("value", "1");
		else
			node.addAttribute("value", "0");
	}

	/**
	 * 已结婚登记
	 */
	public static void marriageRegistration(String path,Document document,Element newroot) throws XPathExpressionException, ParserConfigurationException, SAXException, IOException{
		Element root = document.getRootElement();
		Element node = newroot.addElement("JHDJ").addAttribute("nameCN", "已结婚登记");
		String nodepath="//QW//AJJBQK/@value";
		String ajjbqk = XMLReader.getXMLNode(path, nodepath);
		String[] wordsKey = {"结婚证","登记结婚"};
		if(!StringUtil.isBlank(ajjbqk)&&(StringUtil.contains(ajjbqk, "结婚证")||StringUtil.contains(ajjbqk, "登记结婚"))){
			node.addAttribute("value", "1");
		}else{
			node.addAttribute("value", "0");
		}
	}


	/**
	 * 子女医疗费用
	 *
	 * 婚姻法第二十一条所称“抚养费”，包括子女生活费、教育费、医疗费等费用。
	 */
	public static void childMaintenance(String path,Document document,Element newroot) throws XPathExpressionException, ParserConfigurationException, SAXException, IOException{
		Element root = document.getRootElement();
		Element node = newroot.addElement("ZNYLF").addAttribute("nameCN", "子女医疗费用");
		String nodepath="//QW//AJJBQK//YGSCD/@value";
		String ajjbqk = XMLReader.getXMLNode(path, nodepath);
		String[] wordsKey1 = {"子女","儿子","婚生子","女儿","婚生女","孩子","儿女"};
		String[] wordsKey = {"生活费","教育费","医疗费","抚养费"};
		boolean flag = false;
		if(!StringUtil.isBlank(ajjbqk)){
			List<String> ajjbqkList = SplitUtil.getWholeContent(ajjbqk);
			for(String s:ajjbqkList){
				if(SplitUtil.containsAll(s, wordsKey1)&&SplitUtil.containsAll(s, wordsKey)){
					flag = true;
					break;
				}
			}
		}
		if(flag){
			node.addAttribute("value", "1");
		}else{
			node.addAttribute("value", "0");
		}
	}


	/**
	 * 女方是否怀孕或分娩后一年内或中止妊娠后六个月
	 */
	public static void hyInOneYear(String path,Document document,Element newroot) throws XPathExpressionException, ParserConfigurationException, SAXException, IOException{
		Element root = document.getRootElement();
		Element node = newroot.addElement("HYMFRS").addAttribute("nameCN", "女方是否怀孕或分娩后一年内或中止妊娠后六个月");
		String nodepath="//QW//CPFXGC/@value";
		String cpfxgc = XMLReader.getXMLNode(path, nodepath);
		if(!StringUtil.isBlank(cpfxgc)){
			String[] words = {"妊娠","分娩","哺乳期","怀孕"};
			if(SplitUtil.containsAll(cpfxgc, words)){
				node.addAttribute("value", "1");
			}else{
				node.addAttribute("value", "0");
			}
		}else{
			node.addAttribute("value", "0");
		}
	}

	/**
	 * 有关财产书面约定
	 */
	public static void ccyd(String path,Document document,Element newroot) throws XPathExpressionException, ParserConfigurationException, SAXException, IOException{
		Element root = document.getRootElement();
		Element node = newroot.addElement("CCYD").addAttribute("nameCN", "有关财产书面约定");
		String nodepath="//QW//CPFXGC/@value";
		String cpfxgc = XMLReader.getXMLNode(path, nodepath);
		if(!StringUtil.isBlank(cpfxgc)){
			String[] words = {"约定","协议"};
			if(SplitUtil.containsAll(cpfxgc, words)){
				node.addAttribute("value", "1");
			}else{
				node.addAttribute("value", "0");
			}
		}else{
			node.addAttribute("value", "0");
		}
	}

	/**
	 * 转移财产
	 */
	public static void zycc(String path,Document document,Element newroot) throws XPathExpressionException, ParserConfigurationException, SAXException, IOException{
		Element root = document.getRootElement();
		Element node = newroot.addElement("ZYCC").addAttribute("nameCN", "是否存在转移财产等行为");
		String nodepath="//QW//CPFXGC/@value";
		String cpfxgc = XMLReader.getXMLNode(path, nodepath);
		boolean flag = false;
		if(!StringUtil.isBlank(cpfxgc)){
			List<String> cpfxgcList = SplitUtil.getWholeContent(cpfxgc);
			String[] keyWords = {"房","车","楼","财产","存款"};
			String[] keyWords1 = {"过户","转移","转让"};
			for(String s:cpfxgcList){
				if(SplitUtil.containsAll(s, keyWords)&&SplitUtil.containsAll(s, keyWords1)){
					flag = true;
					break;
				}
			}
		}
		if(flag){
			node.addAttribute("value", "1");
		}else{
			node.addAttribute("value", "0");
		}
	}

	/**
	 * 婚姻无效
	 */
	public static void hywx(String path,Document document,Element newroot) throws XPathExpressionException, ParserConfigurationException, SAXException, IOException{
		Element root = document.getRootElement();
		Element node = newroot.addElement("HYWX").addAttribute("nameCN", "是否属于无效婚姻");
		String nodepath="//QW//CPFXGC/@value";
		String cpfxgc = XMLReader.getXMLNode(path, nodepath);
		boolean flag = false;
		if(!StringUtil.isBlank(cpfxgc)){
			List<String> cpfxgcList = SplitUtil.getWholeContent(cpfxgc);
			String[] sicknessWords = {"遗传性疾病","传染病","精神病","艾滋病","淋病","梅毒","麻风病","精神分裂症","躁狂抑郁型精神病","痴呆症"};
			for(String s:cpfxgcList){
				if(SplitUtil.containsAll(s, sicknessWords)||StringUtil.contains(s, "旁系血亲")||StringUtil.contains(s, "婚姻无效")){
					flag = true;
					break;
				}
			}
		}
		if(flag){
			node.addAttribute("value", "1");
		}else{
			node.addAttribute("value", "0");
		}
	}

	/**
	 * 共同还贷
	 */
	public static void gthd(String path,Document document,Element newroot) throws XPathExpressionException, ParserConfigurationException, SAXException, IOException{
		Element root = document.getRootElement();
		Element node = newroot.addElement("GTGD").addAttribute("nameCN", "是否属于无效婚姻");
		String nodepath="//QW//CPFXGC/@value";
		String cpfxgc = XMLReader.getXMLNode(path, nodepath);
		boolean flag = false;
		if(!StringUtil.isBlank(cpfxgc)){
			List<String> cpfxgcList = SplitUtil.getWholeContent(cpfxgc);
			String[] keyWord ={"还贷","偿还贷款"};
			for(String s:cpfxgcList){
				if(SplitUtil.containsAll(s, keyWord)&&StringUtil.contains(s, "共同")){
					flag = true;
					break;
				}
			}
		}
		if(flag){
			node.addAttribute("value", "1");
		}else{
			node.addAttribute("value", "0");
		}
	}
}
