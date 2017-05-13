package com.le.bc.http;

import java.io.IOException;

import org.apache.http.client.ClientProtocolException;
import org.apache.http.client.methods.CloseableHttpResponse;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.entity.StringEntity;
import org.apache.http.impl.client.CloseableHttpClient;
import org.apache.http.impl.client.HttpClients;
import org.apache.http.util.EntityUtils;

import com.alibaba.fastjson.JSONObject;
import com.le.bc.commons.StringUtil;

/**
 * Document Start 
 * 发送HTTP POST JSON请求
 * Document End 
 * Author: songwenpeng@le.com
 * Time: 2016年9月4日 下午3:49:09
 */
public class HttpPostJSON
{

	/**
	 * Document Start
	 * HttpPost发送json
	 * Document End
	 * zhangxiaojun1@letv.com
	 * 乐视控股（北京）有限公司
	 * 2016年1月5日 下午4:12:59
	 * @param url
	 * @param requestJson
	 * @return
	 * @throws ClientProtocolException
	 * @throws IOException
	 */
	public static String sendPost(String url, JSONObject requestJson) throws ClientProtocolException, IOException
	{
		HttpPost httpPost = new HttpPost(url);
		httpPost.setHeader("Content-Type", "application/x-www-form-urlencoded;charset=UTF-8");
		httpPost.setHeader("charset", "UTF-8");

		// json处理
		StringEntity entity = new StringEntity(requestJson.toString(), "utf-8");
		entity.setContentEncoding("UTF-8");
		entity.setContentType("application/json");
		httpPost.setEntity(entity);

		// 发送请求
		CloseableHttpClient httpClient = HttpClients.createDefault();
		CloseableHttpResponse httpResponse = httpClient.execute(httpPost);

		// 解析结果
		String result = EntityUtils.toString(httpResponse.getEntity());
		result = StringUtil.replace(result, "\r\n", "");

		// 返回结果
		return result;
	}
}
