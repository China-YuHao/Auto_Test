package com.Auto_Test.utils.httpclient;

import java.io.IOException;
import java.net.URISyntaxException;
import java.util.ArrayList;

import org.apache.http.HttpEntity;
import org.apache.http.NameValuePair;
import org.apache.http.client.ClientProtocolException;
import org.apache.http.client.methods.CloseableHttpResponse;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.client.utils.URIBuilder;
import org.apache.http.impl.client.CloseableHttpClient;
import org.apache.http.impl.client.HttpClients;
import org.apache.http.util.EntityUtils;

import com.Auto_Test.utils.Logs;

public class HttpClient_Get {
	
	public String entityStr = null;
	public CloseableHttpResponse response = null;
	Logs log = new Logs(getClass());
	
	public String Get(String GET_URL,ArrayList<NameValuePair> list) throws URISyntaxException {
		try {
			CloseableHttpClient httpClient = HttpClients.createDefault();
	        
			URIBuilder uriBuilder = new URIBuilder(GET_URL);
	        uriBuilder.setParameters(list);
	        // 根据带参数的URI对象构建GET请求对象
	        HttpGet httpGet = new HttpGet(uriBuilder.build());
	        // 执行请求
	        response = httpClient.execute(httpGet);
	      //获取返回状态，并判断是否连接成功。
			if (response.getStatusLine().getStatusCode()==200) {
				log.info("GET连接成功");
			} else {
				log.error("GET连接异常");
			}
	        // 获得响应的实体对象
	        HttpEntity entity = response.getEntity();
	        // 使用Apache提供的工具类进行转换成字符串
	        entityStr = EntityUtils.toString(entity, "UTF-8");
			//关闭请求
			httpClient.close();
		} catch (ClientProtocolException e) {
	        System.err.println("Http协议异常");
	        e.printStackTrace();
	    } catch (IOException e) {
	        System.err.println("IO异常");
	        e.printStackTrace();
	    }
		return entityStr;
	}

}
