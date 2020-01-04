package com.Auto_Test.Case;

import java.io.IOException;
import java.net.URISyntaxException;
import java.util.ArrayList;
import java.util.List;

import org.apache.http.NameValuePair;
import org.apache.http.message.BasicNameValuePair;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.Test;

import com.Auto_Test.utils.httpclient.HttpClient_Get;
import com.Auto_Test.utils.httpclient.HttpClient_Post;
import com.Auto_Test.utils.Logs;
import com.Auto_Test.utils.ReadCSV;
import com.Auto_Test.utils.Verify;


public class Test_Case_AutoInterface {
	
		//定义一个list对象，获取CSV文件中的数据
		public static List<String[]> csvlist = null;
		//传参list
		public ArrayList<NameValuePair> list;
		Logs log = new Logs(getClass());
		
		ReadCSV readcsv = new ReadCSV();
		HttpClient_Post Post = new HttpClient_Post();
		HttpClient_Get Get = new HttpClient_Get();
		
		@BeforeClass
		public void read() throws IOException{
			log.info("获取CSV文件");
			csvlist = readcsv.redcsv();
		}
		
		@Test
		public void test_case() throws URISyntaxException{
			//拿到连接类型进行判断进入那个连接方式中
			for (int i = 0; i < csvlist.size(); i++) {
				//获取参数名称与参数值
				BasicNameValuePair p1 = new BasicNameValuePair(csvlist.get(i)[2],csvlist.get(i)[3]);
				BasicNameValuePair p2 = new BasicNameValuePair(csvlist.get(i)[4],csvlist.get(i)[5]);
				BasicNameValuePair p3 = new BasicNameValuePair(csvlist.get(i)[6],csvlist.get(i)[7]);
				//实例化list
				list = new ArrayList<NameValuePair>();
				//根据参数是否有值获取到list中
				if (csvlist.get(i)[3].toString()!=null && !"".equals(csvlist.get(i)[3].toString().trim())) {list.add(p1);}
				if (csvlist.get(i)[5].toString()!=null && !"".equals(csvlist.get(i)[5].toString().trim())) {list.add(p2);}
				if (csvlist.get(i)[7].toString()!=null && !"".equals(csvlist.get(i)[7].toString().trim())) {list.add(p3);}
				//判断连接类型，进行不同方式的连接。
				if ((csvlist.get(i)[8]).equals("SET")) {
					String entity = Get.Get(csvlist.get(i)[1], list);
					System.out.println(entity);
				} else {
					String entity = Post.Post(csvlist.get(i)[1], list);
					System.out.println(entity);
				}
			}
			Verify.assertEquals("Get", "Post", "数值对比");
			System.out.println("数值对比结束！");
		}

}
