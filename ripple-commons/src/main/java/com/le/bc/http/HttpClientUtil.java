package com.le.bc.http;

import java.io.File;
import java.io.IOException;
import java.net.URL;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import org.apache.http.HttpEntity;
import org.apache.http.NameValuePair;
import org.apache.http.client.config.RequestConfig;
import org.apache.http.client.entity.UrlEncodedFormEntity;
import org.apache.http.client.methods.CloseableHttpResponse;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.conn.ssl.DefaultHostnameVerifier;
import org.apache.http.conn.util.PublicSuffixMatcher;
import org.apache.http.conn.util.PublicSuffixMatcherLoader;
import org.apache.http.entity.ContentType;
import org.apache.http.entity.StringEntity;
import org.apache.http.entity.mime.MultipartEntityBuilder;
import org.apache.http.entity.mime.content.FileBody;
import org.apache.http.entity.mime.content.StringBody;
import org.apache.http.impl.client.CloseableHttpClient;
import org.apache.http.impl.client.HttpClients;
import org.apache.http.message.BasicNameValuePair;
import org.apache.http.util.EntityUtils;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import com.alibaba.fastjson.JSONObject;

/**
 * Document Start 
 * 使用HTTPClient 4.5版本实现的 不带连接池工具类
 * http://hc.apache.org/httpcomponents-client-4.5.x/
 * Document End 
 * Author: songwenpeng@le.com
 * Time: 2016年10月1日 下午9:37:39
 */
public class HttpClientUtil {
	public static Logger logger = LogManager.getLogger(HttpClientUtil.class);
	//超时时间设置
	private RequestConfig requestConfig = RequestConfig.custom().setSocketTimeout(15000).setConnectTimeout(15000).setConnectionRequestTimeout(15000).build();

	private static HttpClientUtil instance = null;

	private HttpClientUtil() {

	}

	/**
	 * Document Start 
	 * 单例模式
	 * 获取HTTPClient实例
	 * Document End 
	 * Author: songwenpeng@le.com
	 * Time: 2016年10月1日 下午9:41:05
	 * @return
	 */
	public static HttpClientUtil getInstance() {
		if (instance == null) {
			instance = new HttpClientUtil();
		}
		return instance;
	}

	/**
	 * Document Start 
	 * 发送POST请求 不带参数
	 * Document End 
	 * Author: songwenpeng@le.com
	 * Time: 2016年10月1日 下午9:41:55
	 * @param httpUrl
	 * @return
	 */
	public String sendHttpPost(String httpUrl) {
		// 创建httpPost
		HttpPost httpPost = new HttpPost(httpUrl);
		return sendHttpPost(httpPost);
	}

	/**
	 * Document Start 
	 * 发送POST请求
	 * 带JSON参数
	 * Document End 
	 * Author: songwenpeng@le.com
	 * Time: 2016年10月1日 下午9:49:45
	 * @param httpUrl
	 * @param requestJson
	 * @return
	 */
	public String sendHttpPost(String httpUrl, JSONObject requestJson) {
		HttpPost httpPost = new HttpPost(httpUrl);// 创建httpPost    
		//设置参数  
		StringEntity entity = new StringEntity(requestJson.toString(), "UTF-8");
		entity.setContentEncoding("UTF-8");
		entity.setContentType("application/json");
		httpPost.setEntity(entity);
		return sendHttpPost(httpPost);
	}

	/**
	 * Document Start 
	 * 发送POST请求
	 * 带参数 参数格式 key1=value1&key2=value2
	 * Document End 
	 * Author: songwenpeng@le.com
	 * Time: 2016年10月1日 下午9:42:14
	 * @param httpUrl
	 * @param params
	 * @return
	 */
	public String sendHttpPost(String httpUrl, String params) {
		HttpPost httpPost = new HttpPost(httpUrl);// 创建httpPost    
		//设置参数  
		StringEntity stringEntity = new StringEntity(params, "UTF-8");
		stringEntity.setContentType("application/x-www-form-urlencoded");
		httpPost.setEntity(stringEntity);
		return sendHttpPost(httpPost);
	}

	/**
	 * Document Start 
	 * 发送 post请求 
	 * 带参数
	 * 参数格式为Map
	 * Document End 
	 * Author: songwenpeng@le.com
	 * Time: 2016年10月1日 下午9:43:10
	 * @param httpUrl
	 * @param maps
	 * @return
	 */
	public String sendHttpPost(String httpUrl, Map<String, String> maps) {
		HttpPost httpPost = new HttpPost(httpUrl);// 创建httpPost    
		// 创建参数队列    
		List<NameValuePair> nameValuePairs = new ArrayList<NameValuePair>();
		for (String key : maps.keySet()) {
			nameValuePairs.add(new BasicNameValuePair(key, maps.get(key)));
		}
		try {
			httpPost.setEntity(new UrlEncodedFormEntity(nameValuePairs, "UTF-8"));
		} catch (Exception e) {
			e.printStackTrace();
		}
		return sendHttpPost(httpPost);
	}

	/** 
	 * 发送 post请求（带文件） 
	 * @param httpUrl 地址 
	 * @param maps 参数 
	 * @param fileLists 附件 
	 */
	/**
	 * Document Start 
	 * 发送POST请求
	 * 带参数 和文件
	 * Document End 
	 * Author: songwenpeng@le.com
	 * Time: 2016年10月1日 下午9:44:56
	 * @param httpUrl
	 * @param maps
	 * @param fileLists
	 * @return
	 */
	public String sendHttpPost(String httpUrl, Map<String, String> maps, List<File> fileLists) {
		HttpPost httpPost = new HttpPost(httpUrl);// 创建httpPost    
		MultipartEntityBuilder meBuilder = MultipartEntityBuilder.create();
		for (String key : maps.keySet()) {
			meBuilder.addPart(key, new StringBody(maps.get(key), ContentType.TEXT_PLAIN));
		}
		for (File file : fileLists) {
			FileBody fileBody = new FileBody(file);
			meBuilder.addPart("files", fileBody);
		}
		HttpEntity reqEntity = meBuilder.build();
		httpPost.setEntity(reqEntity);
		return sendHttpPost(httpPost);
	}

	/**
	 * Document Start 
	 * 发送Post请求 
	 * 基础方法
	 * Document End 
	 * Author: songwenpeng@le.com
	 * Time: 2016年10月1日 下午9:45:41
	 * @param httpPost
	 * @return
	 */
	private String sendHttpPost(HttpPost httpPost) {
		CloseableHttpClient httpClient = null;
		CloseableHttpResponse response = null;
		HttpEntity entity = null;
		String responseContent = null;
		try {
			// 创建默认的httpClient实例.  
			httpClient = HttpClients.createDefault();
			httpPost.setConfig(requestConfig);
			// 执行请求  
			response = httpClient.execute(httpPost);
			entity = response.getEntity();
			responseContent = EntityUtils.toString(entity, "UTF-8");
		} catch (Exception e) {
			e.printStackTrace();
			logger.info("【sendPost: " + httpPost.getURI() + " Exception】" + e.getMessage());
		} finally {
			try {
				// 关闭连接,释放资源  
				if (response != null) {
					response.close();
				}
				if (httpClient != null) {
					httpClient.close();
				}
			} catch (IOException e) {
				e.printStackTrace();
			}
		}
		return responseContent;
	}

	/**
	 * Document Start 
	 * 发送 get请求
	 * Document End 
	 * Author: songwenpeng@le.com
	 * Time: 2016年10月1日 下午9:46:55
	 * @param httpUrl
	 * @return
	 */
	public String sendHttpGet(String httpUrl) {
		HttpGet httpGet = new HttpGet(httpUrl);// 创建get请求  
		return sendHttpGet(httpGet);
	}

	/**
	 * Document Start 
	 * 发送 get请求Https
	 * Document End 
	 * Author: songwenpeng@le.com
	 * Time: 2016年10月1日 下午9:47:15
	 * @param httpUrl
	 * @return
	 */
	public String sendHttpsGet(String httpUrl) {
		HttpGet httpGet = new HttpGet(httpUrl);// 创建get请求  
		return sendHttpsGet(httpGet);
	}

	/**
	 * Document Start 
	 * 发送Get请求 
	 * Document End 
	 * Author: songwenpeng@le.com
	 * Time: 2016年10月1日 下午9:47:25
	 * @param httpGet
	 * @return
	 */
	private String sendHttpGet(HttpGet httpGet) {
		CloseableHttpClient httpClient = null;
		CloseableHttpResponse response = null;
		HttpEntity entity = null;
		String responseContent = null;
		try {
			// 创建默认的httpClient实例.  
			httpClient = HttpClients.createDefault();
			httpGet.setConfig(requestConfig);
			// 执行请求  
			response = httpClient.execute(httpGet);
			entity = response.getEntity();
			responseContent = EntityUtils.toString(entity, "UTF-8");
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			try {
				// 关闭连接,释放资源  
				if (response != null) {
					response.close();
				}
				if (httpClient != null) {
					httpClient.close();
				}
			} catch (IOException e) {
				e.printStackTrace();
			}
		}
		return responseContent;
	}

	/**
	 * Document Start 
	 * 发送Get请求Https
	 * Document End 
	 * Author: songwenpeng@le.com
	 * Time: 2016年10月1日 下午9:47:39
	 * @param httpGet
	 * @return
	 */
	private String sendHttpsGet(HttpGet httpGet) {
		CloseableHttpClient httpClient = null;
		CloseableHttpResponse response = null;
		HttpEntity entity = null;
		String responseContent = null;
		try {
			// 创建默认的httpClient实例.  
			PublicSuffixMatcher publicSuffixMatcher = PublicSuffixMatcherLoader.load(new URL(httpGet.getURI().toString()));
			DefaultHostnameVerifier hostnameVerifier = new DefaultHostnameVerifier(publicSuffixMatcher);
			httpClient = HttpClients.custom().setSSLHostnameVerifier(hostnameVerifier).build();
			httpGet.setConfig(requestConfig);
			// 执行请求  
			response = httpClient.execute(httpGet);
			entity = response.getEntity();
			responseContent = EntityUtils.toString(entity, "UTF-8");
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			try {
				// 关闭连接,释放资源  
				if (response != null) {
					response.close();
				}
				if (httpClient != null) {
					httpClient.close();
				}
			} catch (IOException e) {
				e.printStackTrace();
			}
		}
		return responseContent;
	}
}
