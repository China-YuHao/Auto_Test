package com.Auto_Test.utils.httpclient;

import java.io.IOException;
import java.util.ArrayList;

import org.apache.http.HttpEntity;
import org.apache.http.NameValuePair;
import org.apache.http.client.ClientProtocolException;
import org.apache.http.client.entity.UrlEncodedFormEntity;
import org.apache.http.client.methods.CloseableHttpResponse;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.impl.client.CloseableHttpClient;
import org.apache.http.impl.client.HttpClientBuilder;
import org.apache.http.util.EntityUtils;

public class HttpClient_Post {
	private String entityStr;
	//封装POST方法
	public String Post(String POST_URL,ArrayList<NameValuePair> list) {
		try {
			//把参数放入请求体中
			UrlEncodedFormEntity entityParam = new UrlEncodedFormEntity(list, "UTF-8");
			CloseableHttpClient httpClient = HttpClientBuilder.create().build();
			HttpPost httpPost = new HttpPost(POST_URL);
			httpPost.setEntity(entityParam);
			//发起请求
			CloseableHttpResponse response = httpClient.execute(httpPost);
			//获取返回状态，并判断是否连接成功。
			if (response.getStatusLine().getStatusCode()==200) {
				System.out.println("POST连接成功");
			} else {
				System.out.println("POST连接异常");
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
