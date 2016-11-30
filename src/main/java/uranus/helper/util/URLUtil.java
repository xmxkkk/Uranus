package uranus.helper.util;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;

import org.apache.http.client.ClientProtocolException;
import org.apache.http.client.config.RequestConfig;
import org.apache.http.client.methods.CloseableHttpResponse;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.impl.client.CloseableHttpClient;
import org.apache.http.impl.client.HttpClients;

public class URLUtil {
	static CloseableHttpClient httpclient = HttpClients.createDefault();
	public static String url2str(String url,String charset) throws IOException{
		String result=null;
		HttpGet httpGet=new HttpGet(url);
		try {
			RequestConfig config=RequestConfig.custom().setConnectTimeout(Constant.TIMEOUT).setConnectionRequestTimeout(Constant.TIMEOUT).setSocketTimeout(Constant.TIMEOUT).build();
			httpGet.setConfig(config);
			CloseableHttpResponse response=httpclient.execute(httpGet);
			if(response.getStatusLine().getStatusCode()==200){
				result=is2str(response.getEntity().getContent(),charset);
			}else{
				if(!httpGet.isAborted()){
					httpGet.abort();
				}
				result= null;
			}
		} catch (ClientProtocolException e) {
			if(!httpGet.isAborted()){
				httpGet.abort();
			}
			throw e;
		} catch (IOException e) {
			if(!httpGet.isAborted()){
				httpGet.abort();
			}
			throw e;
		}finally {
			if(!httpGet.isAborted()){
				httpGet.abort();
			}
		}
		
		return result;
	}
	private static String is2str(InputStream is,String charset) throws IOException{
		BufferedReader br=new BufferedReader(new InputStreamReader(is, charset));
		return is2str(br);
	}
	
	private static String is2str(BufferedReader br) throws IOException{
		StringBuffer sb = new StringBuffer();
		char[] buf = new char[102400];
		int len = 0;
		while ((len = br.read(buf, 0, buf.length)) > 0) {
			sb.append(new String(buf, 0, len));
		}
		br.close();
		return sb.toString();
	}
}
