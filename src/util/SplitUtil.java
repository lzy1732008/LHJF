package util;

import java.util.ArrayList;

public class SplitUtil {

	public static  ArrayList<String> getWholeContent(String str) {
		ArrayList<String> contentlist= new ArrayList<String>();
		String[] jhsplit=str.split("[。.]");
		for(int i=0;i<jhsplit.length;i++){
			String content=jhsplit[i];
			String []dhsplit=content.split("[；;]");
			for(int j=0;j<dhsplit.length;j++){
				if(dhsplit[j].length()>0){
					contentlist.add(dhsplit[j]);
				}
			}
		}
		return contentlist;
	}

	public static boolean containsAll(String src,String[] words){
		int size = words.length;
		for(int i=0;i<size;i++){
			if(src.contains(words[i])){
				return true;
			}
		}
		return false;
	}

	public static void main(String[] args){
		String s = "nihao woshi bushi zhefewf";
		String[] words = {"ni","wo","ahah"};
		String[] words1 = {"ni","wo","shi"};
		System.out.println(SplitUtil.containsAll(s, words));
		System.out.println(SplitUtil.containsAll(s, words1));
	}

}
