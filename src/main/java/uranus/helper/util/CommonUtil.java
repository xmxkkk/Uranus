package uranus.helper.util;

import java.io.IOException;
import java.security.MessageDigest;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;

import info.debatty.java.stringsimilarity.Levenshtein;
import uranus.helper.middel.Count;

public class CommonUtil {
	public static double compare(String feature_a,String feature_b){
		Levenshtein l=new Levenshtein();
		double dis=l.distance(feature_a, feature_b);
		int len=feature_a.length()>feature_b.length()?feature_a.length():feature_b.length();
		return dis/len;
	}
	
	
	public final static String md5(String s) {
        char hexDigits[]={'0','1','2','3','4','5','6','7','8','9','A','B','C','D','E','F'};       
        try {
            byte[] btInput = s.getBytes();
            // 获得MD5摘要算法的 MessageDigest 对象
            MessageDigest mdInst = MessageDigest.getInstance("MD5");
            // 使用指定的字节更新摘要
            mdInst.update(btInput);
            // 获得密文
            byte[] md = mdInst.digest();
            // 把密文转换成十六进制的字符串形式
            int j = md.length;
            char str[] = new char[j * 2];
            int k = 0;
            for (int i = 0; i < j; i++) {
                byte byte0 = md[i];
                str[k++] = hexDigits[byte0 >>> 4 & 0xf];
                str[k++] = hexDigits[byte0 & 0xf];
            }
            return new String(str);
        } catch (Exception e) {
            e.printStackTrace();
            return null;
        }
    }
	public static void wait2000(Count count){
		while(true){
			try {
				Thread.sleep(2000);
			} catch (InterruptedException e) {
				e.printStackTrace();
			}
			if(count.count()==0){
				break;
			}
		}
	}
	public static void wait(int x){
		try {
			Thread.sleep(x);
		} catch (InterruptedException e) {
			e.printStackTrace();
		}
	}
	public static void shutdown(){
		try {
			Runtime.getRuntime().exec("shutdown -s -t 10");
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
	
	public static int hml(Map<String, Double> base,String code,boolean asc){
		double baseVal=base.get(code);
		
		int i=1;
		Iterator<String> set=base.keySet().iterator();
		while(set.hasNext()){
			String k=set.next();
			if(baseVal>base.get(k)){
				i++;
			}
		}
		
		
		
		return i;
	}
	public static boolean compareExpressionString(String value,String express){
		if(value==null)return false;
		String orString[]=express.split("\\|\\|");
		List<Boolean> list=new ArrayList<Boolean>();
		for(int i=0;i<orString.length;i++){
			orString[i]=orString[i].trim();
			if(orString[i].contains("||")){
				list.add(compareExpressionString(value, orString[i]));
			}else{
				List<Boolean> tempList=new ArrayList<Boolean>();
				String[] andString=orString[i].split("&&");
				for(int j=0;j<andString.length;j++){
					andString[j]=andString[j].trim();
					if(andString[j].startsWith("!=")){
						if(!value.equals(andString[j].replace("!=", ""))){
							tempList.add(true);
						}else{
							tempList.add(false);
						}
					}else if(andString[j].startsWith("=")){
						if(value.equals(andString[j].replace("=", ""))){
							tempList.add(true);
						}else{
							tempList.add(false);
						}
					}else if(andString[j].startsWith("IN")){
						if(value.contains(andString[j].replace("IN", ""))){
							tempList.add(true);
						}else{
							tempList.add(false);
						}
					}else if(andString[j].startsWith("!IN")){
						if(!value.contains(andString[j].replace("!IN", ""))){
							tempList.add(true);
						}else{
							tempList.add(false);
						}
					}else{
						throw new RuntimeException("表达式错误");
					}
				}
				boolean tempResult=true;
				for(int j=0;j<tempList.size();j++){
					if(tempList.get(j)==false){
						tempResult=false;break;
					}
				}
				list.add(tempResult);
			}
		}
		boolean tempResult=false;
		for(int i=0;i<list.size();i++){
			if(list.get(i)){
				tempResult=true;
			}
		}
		
		return tempResult;
	}
	public static boolean compareExpressionDouble(Double value,String express){
		if(value==null)return false;
		
		String orString[]=express.split("\\|\\|");
		List<Boolean> list=new ArrayList<Boolean>();
		for(int i=0;i<orString.length;i++){
			orString[i]=orString[i].trim();
			if(orString[i].contains("||")){
				list.add(compareExpressionDouble(value, orString[i]));
			}else{
				List<Boolean> tempList=new ArrayList<Boolean>();
				String[] andString=orString[i].split("&&");
				for(int j=0;j<andString.length;j++){
					andString[j]=andString[j].trim();
					if(andString[j].startsWith("<=")){
						if(value<=Double.parseDouble(andString[j].replace("<=", ""))){
							tempList.add(true);
						}else{
							tempList.add(false);
						}
					}else if(andString[j].startsWith(">=")){
						if(value>=Double.parseDouble(andString[j].replace(">=", ""))){
							tempList.add(true);
						}else{
							tempList.add(false);
						}
					}else if(andString[j].startsWith("<")){
						if(value<Double.parseDouble(andString[j].replace("<", ""))){
							tempList.add(true);
						}else{
							tempList.add(false);
						}
					}else if(andString[j].startsWith(">")){
						if(value>Double.parseDouble(andString[j].replace(">", ""))){
							tempList.add(true);
						}else{
							tempList.add(false);
						}
					}else if(andString[j].startsWith("!=")){
						if(value!=Double.parseDouble(andString[j].replace("!=", ""))){
							tempList.add(true);
						}else{
							tempList.add(false);
						}
					}else if(andString[j].startsWith("=")){
						if(value==Double.parseDouble(andString[j].replace("=", ""))){
							tempList.add(true);
						}else{
							tempList.add(false);
						}
					}else{
						throw new RuntimeException("表达式错误");
					}
				}
				boolean tempResult=true;
				for(int j=0;j<tempList.size();j++){
					if(tempList.get(j)==false){
						tempResult=false;break;
					}
				}
				list.add(tempResult);
			}
		}
		boolean tempResult=false;
		for(int i=0;i<list.size();i++){
			if(list.get(i)){
				tempResult=true;
			}
		}
		
		return tempResult;
	}
	
	public static Map<String, Double> cast(Map<String, Object> param){
		Map<String, Double> paramResult=new HashMap<String, Double>();
		Iterator<String> it=param.keySet().iterator();
		while(it.hasNext()){
			String key=it.next();
			paramResult.put(key, new Double(param.get(key).toString()));
		}
		return paramResult;
	}
}
