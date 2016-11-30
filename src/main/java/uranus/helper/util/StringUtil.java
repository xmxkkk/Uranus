package uranus.helper.util;

import java.util.List;
import java.util.Map;

public class StringUtil {
	@SuppressWarnings("rawtypes")
	public static boolean isBlank(Object obj){
		if(obj==null){
			return true;
		}
		
		if(obj instanceof List){
			return ((List) obj).size()==0;
		}else if(obj instanceof Map){
			return ((Map) obj).size()==0;
		}else if(obj instanceof String){
			return obj.equals("");
		}
		return false; 
	}
	
	public static String textFormat(String text,String param){
		String[] params=param.split(",");
		int idx=0;
		while(text.contains("{"+idx+"}")){
			text=text.replace("{"+idx+"}", params[idx]);
			idx++;
		}
		return text;
	}
	
}
