package LHJF_OLD.src.LYP;

import org.dom4j.Document;
import org.dom4j.Element;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class infoSeek {

/**
 *@Description:创建有关夫妻债务、债权情况的节点

 */

    public  static  void  createLoanNode(Document doc, Element newroot)
    {
        Element FQZWZQNode = newroot.addElement("FQZWZQ");
        FQZWZQNode.addAttribute("nameCN","夫妻债务、债权情况");
        FQZW(doc,FQZWZQNode);
        FQZQ(doc,FQZWZQNode);
    }

    /**
     *@Description:检查传过来的list中是否有重复项

     */

    public static boolean checkReapt(ArrayList<String>list,String obj)
    {
        boolean flag=false;
        Iterator<String> it=list.iterator();
        while (it.hasNext())
        {
            String exit=it.next();
            if(obj.contains(exit)||exit.contains(obj))
            {
                flag=true;
                break;
            }
        }
        return flag;
    }

/**
 *@Description:如果关于财产信息的括号中不是有关价值（XX元）的信息就去掉

 */
    public static String removeRound(String input)
    {

       int i=0;
        while (input.contains("（")&&i<5)
        {
            int start=input.indexOf("（");
            int end=input.indexOf("）");

            if(!(start<end+1&&start>1&&input.substring(start,end+1).contains("元")))
            {
                input=input.substring(0,start-1)+input.substring(end+1);
                System.out.println("input"+input);
                i++;
            }

        }
        return input;
    }

/**
 *@Description:去掉传过来的字符串中的指定字

 */
    public static String awayString(String needAeay,String target)
    {

        int start=needAeay.indexOf(target);
        if(start == -1)
        {
            return needAeay;
        }
        //int targetLength=target.length();
        else
        {
            int length=target.length();
            String result="";
            result=needAeay.substring(0,start)+needAeay.substring(start+length);


            return result;
        }

    }

/**
 *@Description:用于检查原告和被告财产的方法

 */
    public static void checkWealth(String user, Document doc, Element newroot, Element HQCCNode)
    {
        Element root=doc.getRootElement();
        ArrayList<String> wealthOfUser=new ArrayList<String >();//个人财产List
        String otherUser="";//定义另一个角色


        if(user.equals("被告"))
        {
            otherUser="原告";
            HQCCNode = newroot.addElement("BGHQCC");
            HQCCNode.addAttribute("nameCN","被告婚前财产");
        }

        else
        {
            otherUser="被告";
            HQCCNode = newroot.addElement("YGHQCC");
            HQCCNode.addAttribute("nameCN","原告婚前财产");
        }
        if(root.element("AJJBQK")!=null)
        {
            Element ajjbqkNode=root.element("AJJBQK");
            if(ajjbqkNode.element("CMSSD")!=null)
            {
                List<Element> cmssdNodes=ajjbqkNode.elements("CMSSD");
                for(Element e:cmssdNodes)
                {
                    if(e.attribute("value")!=null)
                    {
                        wealthOfUser=analyseString(e,wealthOfUser,user,otherUser);
                    }
                }

            }


        }

        //再检查一下CPJG节点（法院仲裁信息节点）有些会有财产记录
        if(root.element("CPJG")!=null)
        {
            Element cpjgNode=root.element("CPJG");
            if(cpjgNode.attribute("value")!=null)
            {
                wealthOfUser=analyseString(cpjgNode,wealthOfUser,user,otherUser);

            }
        }

        if(wealthOfUser.size()==0)
        {
            HQCCNode.addAttribute("value","未提及");
        }
        else
        {
            HQCCNode.addAttribute("value",wealthOfUser.toString());
        }

        System.out.println(user+"婚前财产："+wealthOfUser);
    }


/**
 *@Description:将不同节点用于提取婚前财产的方法提取出来

 */
    public static ArrayList<String> analyseString(Element e, ArrayList<String>resultList, String user, String otherUser)
    {


        String qwStr=e.attributeValue("value");
        String[] eachQw=qwStr.split("，|。|；");

        //  boolean flag=false; //用一个flag来标记当前元素中是否是个人财产部分
        for(String eachStr:eachQw)
        {

            //有冒号的格式
            if(eachStr.contains(user)&&eachStr.contains("：")&&!(eachStr.contains("争议"))&&(eachStr.contains("彩礼")||eachStr.contains("陪嫁")||
                    eachStr.contains("个人财产")||eachStr.contains("婚前财产")))
            {

                String[] wealth=eachStr.split("：");
                    //     wealth[1]=removeRound(wealth[1]);
                String[] allWealth=wealth[1].split("、");
                //将财产添加进ArrayList
                for(String addStr:allWealth)
                {

                    if(!(checkReapt(resultList,addStr)))
                    {
                        if(addStr.contains("归"+user+"所有"))
                        {

                            addStr=awayString(addStr,("归"+user+"所有"));
                            resultList.add(addStr);
                        }
                        else if(addStr.contains("及"))
                        {
                            String[] end=addStr.split("及");
                            //检查最后两项
                            for(String end2:end)
                            {
                                if(!(checkReapt(resultList,end2)))
                                {
                                    resultList.add(end2);

                                }
                            }
                        }
                        else
                        {
                            resultList.add(addStr);
                        }

                    }
                }
            }

            //没有冒号的格式
            else if(eachStr.contains(user)&&eachStr.contains("在"+otherUser)&&!(eachStr.contains("争议"))&&(eachStr.contains("婚前财产")||eachStr.contains("个人财产")))
            {
                String [] wealth=eachStr.split("个人财产|个人财产有");
                String [] allWealth = null;
                if(wealth.length>=2){
                 //         wealth[1]=removeRound(wealth[1]);   //代码修改处
                          allWealth = wealth[1].split("、");
                
                
                

                for(String addStr:allWealth)
                {
                    if(!(checkReapt(resultList,addStr)))
                    {
                        if(addStr.contains("及"))
                        {
                            String[] end=addStr.split("及");
                            //检查最后两项
                            for(String end2:end)
                            {
                                if(!(checkReapt(resultList,end2)))
                                {
                                    resultList.add(end2);
                                }
                            }
                        }
                        else if(addStr.contains("归"+user+"所有"))
                        {
                            addStr=awayString(addStr,("归"+user+"所有"));
                            resultList.add(addStr);

                        }
                        else
                        {
                            resultList.add(addStr);
                        }


                    }
                }
            }
            }

        }
        return resultList;
    }

    /**
     *@Description:检查原告婚前财产

     */

    public static void YGHQ(Document doc, Element newroot)
    {
            Element YGHQCCNode=null;
            checkWealth("原告",doc,newroot,YGHQCCNode);

    }

    /**
     *@Description:检查被告婚前财产

     */
    public static void BGHQ(Document doc, Element newroot)
    {
        Element BGHQCCNode=null;
        checkWealth("被告",doc,newroot,BGHQCCNode);
    }


    /**
     *@Description:夫妻共同财产

     */
    public static void FQGT(Document doc, Element newroot)
    {

    }

    /**
     *@Description:原告和被告现任职单位以及收入

     */

    public static void YBGDWandSR(Document doc, Element newroot)
    {
        Element root=doc.getRootElement();
        Element BGGZDWNode=newroot.addElement("BGGZDW");
        BGGZDWNode.addAttribute("nameCN","被告工作单位");

        Element YGGZDWNode=newroot.addElement("YGGZDW");
        YGGZDWNode.addAttribute("nameCN","原告工作单位");

        String plaintWork=" ";//原告工作单位
        String defendWork=" ";//被告工作单位
        if(root.attribute("value")!=null)
        {
            String qwStr=root.attributeValue("value");
            String[] qwStrArray=qwStr.split("，|。|：|；");
            int length=qwStrArray.length;
            //都是先介绍原告，再介绍被告
            for(int i = 0;i<length;i++)
            {
                if((qwStrArray[i].contains("职工")||qwStrArray[i].contains("职员")||qwStrArray[i].contains("公司")||qwStrArray[i].contains("工人")||qwStrArray[i].contains("职业")||qwStrArray[i].contains("无业")||qwStrArray[i].contains("务农")||qwStrArray[i].contains("集团")||qwStrArray[i].contains("厂")||qwStrArray[i].contains("干部"))
                        &&!(qwStrArray[i].contains("原因"))&&!(qwStrArray[i].contains("住"))&&!(qwStrArray[i].contains("该房")))
                {

                    qwStrArray[i]=awayString(qwStrArray[i],"双方均是");

                    //原告为空
                    if((plaintWork.equals(" ")))
                    {
                        plaintWork=qwStrArray[i];
                        continue;
                    }

                    //原告非空，被告为空
                    if(!(plaintWork.equals(" "))&&(defendWork.equals(" ")))
                    {

                        defendWork=qwStrArray[i];

                    }

                }
            }
        }

        if (plaintWork.equals(" "))
        {
            YGGZDWNode.addAttribute("value","未提及");

        }
        else
        {
            YGGZDWNode.addAttribute("value",plaintWork);
        }

        if(defendWork.equals(" "))
        {
            BGGZDWNode.addAttribute("value","未提及");
        }
        else
        {
            BGGZDWNode.addAttribute("value",defendWork);
        }


        System.out.println("原告工作单位："+plaintWork);
        System.out.println("被告工作单位:"+defendWork);

    }



    /**
     *@Description:夫妻共同债权（作为提供钱款的那一方）

     */
    public static void FQZQ(Document doc, Element newroot)
    {
            Element root=doc.getRootElement();

            Element FQZQNode=newroot.addElement("FQZQ");
            FQZQNode.addAttribute("nameCN","夫妻共同债权");

            ArrayList<String>provideList=new ArrayList<String>();
            boolean flag=false;//检查是否给别人提供过钱
            Pattern pattern=Pattern.compile("\\d+");
            Matcher matcher;
        
            if(root.attribute("value")!=null)
            {
                String qwStr=root.attributeValue("value");
                String[] qwStrarray=qwStr.split("，|。|；");
                for(String analyse:qwStrarray)
                {
                    if (analyse.contains("共同")&&analyse.contains("债权"))
                    {   
                        String[] result=analyse.split("债权");
                        if(result.length>=2 &&result[1].contains("为"))
                        {
                            result[1]=awayString(result[1],"为");
                        }
                        if(result.length>=2){
                        provideList.add(result[1]);
                        flag=true;
                        }
                    }


                }               
            }
            if(flag == false){
            	FQZQNode.addAttribute("value","未提及");
            }else{
            FQZQNode.addAttribute("value",provideList.toString());
            }

            System.out.println("夫妻共同债权："+provideList);
    }

    /**
     *@Description:夫妻共同债务（作为需要还别人钱的那一方）

     */
    public static void FQZW(Document doc, Element newroot)
    {
        Element root=doc.getRootElement();

        Element FQZWNode=newroot.addElement("FQZW");
        FQZWNode.addAttribute("nameCN","夫妻共同债务");
        
        ArrayList<String>loanList=new ArrayList<String>();
        boolean flag=false;//检查是否有贷款项
        Pattern pattern=Pattern.compile("\\d+");
        Matcher matcher;
        if(root.attribute("value")!=null)
        {
            String qwStr=root.attributeValue("value");
            String[] qwStrarray=qwStr.split("，|。|、|：|；");
            for(String analyse:qwStrarray)
            {
                if(analyse.contains("共同")&&(analyse.contains("偿还")||analyse.contains("还款")))
                {
                    String[] reason=analyse.split("偿还|还款");
                    for(String addReason:reason)
                    {
                        if(!(checkReapt(loanList,addReason)))
                        {
                            matcher=pattern.matcher(addReason);
                            if(matcher.find())
                            {
                                    if(addReason.contains("元")||addReason.contains("¥"))
                                    {
                                        loanList.add(addReason);
                                        flag=true;

                                    }


                            }

                        }
                    }

                }
            }

            
        }
        if(flag == false){
        	FQZWNode.addAttribute("value","未提及");
        }else{
        FQZWNode.addAttribute("value",loanList.toString());
        }
        System.out.println("夫妻共同债务:"+loanList);
    }
}
