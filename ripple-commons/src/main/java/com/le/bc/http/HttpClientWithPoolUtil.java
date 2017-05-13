package com.le.bc.http;

import java.io.IOException;
import java.io.InterruptedIOException;
import java.io.UnsupportedEncodingException;
import java.net.UnknownHostException;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.Set;

import javax.net.ssl.SSLException;
import javax.net.ssl.SSLHandshakeException;

import org.apache.http.HttpEntity;
import org.apache.http.HttpEntityEnclosingRequest;
import org.apache.http.HttpHost;
import org.apache.http.HttpRequest;
import org.apache.http.NameValuePair;
import org.apache.http.NoHttpResponseException;
import org.apache.http.client.HttpRequestRetryHandler;
import org.apache.http.client.config.RequestConfig;
import org.apache.http.client.entity.UrlEncodedFormEntity;
import org.apache.http.client.methods.CloseableHttpResponse;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.client.methods.HttpRequestBase;
import org.apache.http.client.protocol.HttpClientContext;
import org.apache.http.config.Registry;
import org.apache.http.config.RegistryBuilder;
import org.apache.http.conn.ConnectTimeoutException;
import org.apache.http.conn.routing.HttpRoute;
import org.apache.http.conn.socket.ConnectionSocketFactory;
import org.apache.http.conn.socket.LayeredConnectionSocketFactory;
import org.apache.http.conn.socket.PlainConnectionSocketFactory;
import org.apache.http.conn.ssl.SSLConnectionSocketFactory;
import org.apache.http.entity.StringEntity;
import org.apache.http.impl.client.CloseableHttpClient;
import org.apache.http.impl.client.HttpClients;
import org.apache.http.impl.conn.PoolingHttpClientConnectionManager;
import org.apache.http.message.BasicNameValuePair;
import org.apache.http.protocol.HttpContext;
import org.apache.http.util.EntityUtils;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import com.alibaba.fastjson.JSONObject;

/**
 * Document Start 
 * 使用HTTPClient 4.5版本实现的带连接池工具类
 * http://hc.apache.org/httpcomponents-client-4.5.x/
 * Document End 
 * Author: songwenpeng@le.com
 * Time: 2016年10月1日 下午10:04:25
 */
public class HttpClientWithPoolUtil {
	public static Logger logger = LogManager.getLogger(HttpClientWithPoolUtil.class);
	//超时时间
	static final int TIME_OUT = 30 * 1000;
	//设置连接池的最大连接数
	static final int MAX_TOTAL = 200;
	//设置每个路由的最大连接数 每个路由指的是每个URL例如 http://www.le.com/pay
	static final int DEFAULT_MAX_PER_ROUTE = 20;
	//设置每个站点的最大连接数 例如http://www.le.com
	static final int MAX_PER_ROUTE = 100;

	//关闭的Client
	private static CloseableHttpClient httpClient = null;

	private final static Object syncLock = new Object();

	/**
	 * Document Start 
	 * 配置
	 * Document End 
	 * Author: songwenpeng@le.com
	 * Time: 2016年10月1日 下午10:11:52
	 * @param httpRequestBase
	 */
	private static void config(HttpRequestBase httpRequestBase) {
		// 设置Header等
		// httpRequestBase.setHeader("User-Agent", "Mozilla/5.0");
		// httpRequestBase
		// .setHeader("Accept",
		// "text/html,application/xhtml+xml,application/xml;q=0.9,*/*;q=0.8");
		// httpRequestBase.setHeader("Accept-Language",
		// "zh-CN,zh;q=0.8,en-US;q=0.5,en;q=0.3");// "en-US,en;q=0.5");
		// httpRequestBase.setHeader("Accept-Charset",
		// "ISO-8859-1,utf-8,gbk,gb2312;q=0.7,*;q=0.7");
		// 配置请求的超时设置
		RequestConfig requestConfig = RequestConfig.custom().setConnectionRequestTimeout(TIME_OUT).setConnectTimeout(TIME_OUT).setSocketTimeout(TIME_OUT).build();
		httpRequestBase.setConfig(requestConfig);
	}

	/**
	 * Document Start 
	 * 获取HttpClient对象
	 * Document End 
	 * Author: songwenpeng@le.com
	 * Time: 2016年10月1日 下午10:12:05
	 * @param url
	 * @return
	 */
	public static CloseableHttpClient getHttpClient(String url) {
		String hostname = url.split("/")[2];
		int port = 80;
		if (hostname.contains(":")) {
			String[] arr = hostname.split(":");
			hostname = arr[0];
			port = Integer.parseInt(arr[1]);
		}
		if (httpClient == null) {
			synchronized (syncLock) {
				if (httpClient == null) {
					httpClient = createHttpClient(hostname, port);
				}
			}
		}
		return httpClient;
	}

	/**
	 * Document Start 
	 * 创建HttpClient对象
	 * Document End 
	 * Author: songwenpeng@le.com
	 * Time: 2016年10月1日 下午10:13:10
	 * @param maxTotal
	 * @param maxPerRoute
	 * @param maxRoute
	 * @param hostname
	 * @param port
	 * @return
	 */
	public static CloseableHttpClient createHttpClient(String hostname, int port) {
		ConnectionSocketFactory plainsf = PlainConnectionSocketFactory.getSocketFactory();
		LayeredConnectionSocketFactory sslsf = SSLConnectionSocketFactory.getSocketFactory();
		Registry<ConnectionSocketFactory> registry = RegistryBuilder.<ConnectionSocketFactory> create().register("http", plainsf).register("https", sslsf).build();
		PoolingHttpClientConnectionManager cm = new PoolingHttpClientConnectionManager(registry);
		// 将最大连接数增加
		cm.setMaxTotal(MAX_TOTAL);
		// 将每个路由基础的连接增加
		cm.setDefaultMaxPerRoute(DEFAULT_MAX_PER_ROUTE);
		HttpHost httpHost = new HttpHost(hostname, port);
		// 将目标主机的最大连接数增加
		cm.setMaxPerRoute(new HttpRoute(httpHost), MAX_PER_ROUTE);
		// 请求重试处理
		HttpRequestRetryHandler httpRequestRetryHandler = new HttpRequestRetryHandler() {
			public boolean retryRequest(IOException exception, int executionCount, HttpContext context) {
				if (executionCount >= 5) {// 如果已经重试了5次，就放弃
					return false;
				}
				if (exception instanceof NoHttpResponseException) {// 如果服务器丢掉了连接，那么就重试
					return true;
				}
				if (exception instanceof SSLHandshakeException) {// 不要重试SSL握手异常
					return false;
				}
				if (exception instanceof InterruptedIOException) {// 超时
					return false;
				}
				if (exception instanceof UnknownHostException) {// 目标服务器不可达
					return false;
				}
				if (exception instanceof ConnectTimeoutException) {// 连接被拒绝
					return false;
				}
				if (exception instanceof SSLException) {// SSL握手异常
					return false;
				}

				HttpClientContext clientContext = HttpClientContext.adapt(context);
				HttpRequest request = clientContext.getRequest();
				// 如果请求是幂等的，就再次尝试
				if (!(request instanceof HttpEntityEnclosingRequest)) {
					return true;
				}
				return false;
			}
		};

		CloseableHttpClient httpClient = HttpClients.custom().setConnectionManager(cm).setRetryHandler(httpRequestRetryHandler).build();
		return httpClient;
	}

	private static void setPostParams(HttpPost httpost, Map<String, Object> params) {
		List<NameValuePair> nvps = new ArrayList<NameValuePair>();
		Set<String> keySet = params.keySet();
		for (String key : keySet) {
			nvps.add(new BasicNameValuePair(key, params.get(key).toString()));
		}
		try {
			httpost.setEntity(new UrlEncodedFormEntity(nvps, "UTF-8"));
		} catch (UnsupportedEncodingException e) {
			e.printStackTrace();
		}
	}

	/**
	 * Document Start 
	 * POST请求URL
	 * Document End 
	 * Author: songwenpeng@le.com
	 * Time: 2016年10月1日 下午10:22:10
	 * @param url
	 * @param params
	 * @return
	 * @throws IOException
	 */
	public static String post(String url, Map<String, Object> params) throws IOException {
		HttpPost httppost = new HttpPost(url);
		config(httppost);
		setPostParams(httppost, params);
		CloseableHttpResponse response = null;
		try {
			response = getHttpClient(url).execute(httppost, HttpClientContext.create());
			HttpEntity entity = response.getEntity();
			String result = EntityUtils.toString(entity, "utf-8");
			EntityUtils.consume(entity);
			return result;
		} catch (Exception e) {
			//e.printStackTrace();
			throw e;
		} finally {
			try {
				if (response != null)
					response.close();
			} catch (IOException e) {
				e.printStackTrace();
			}
		}
	}

	/**
	 * Document Start 
	 * POST请求
	 * 请求参数为JSON格式
	 * Document End 
	 * Author: songwenpeng@le.com
	 * Time: 2016年10月1日 下午10:57:44
	 * @param url
	 * @param objet
	 * @return
	 * @throws IOException
	 */
	public static String post(String url,JSONObject object) throws IOException {
		HttpPost httppost = new HttpPost(url);
		config(httppost);
		
		//设置参数  
		StringEntity requestEntity = new StringEntity(object.toJSONString(), "UTF-8");
		requestEntity.setContentEncoding("UTF-8");
		requestEntity.setContentType("application/json");
		httppost.setEntity(requestEntity);
		
		CloseableHttpResponse response = null;
		try {
			response = getHttpClient(url).execute(httppost, HttpClientContext.create());
			HttpEntity entity = response.getEntity();
			String result = EntityUtils.toString(entity, "utf-8");
			EntityUtils.consume(entity);
			return result;
		} catch (Exception e) {
			//e.printStackTrace();
			throw e;
		} finally {
			try {
				if (response != null)
					response.close();
			} catch (IOException e) {
				e.printStackTrace();
			}
		}
	}
	/**
	 * Document Start 
	 * GET请求URL
	 * Document End 
	 * Author: songwenpeng@le.com
	 * Time: 2016年10月1日 下午10:23:30
	 * @param url
	 * @return
	 */
	public static String get(String url) {
		HttpGet httpget = new HttpGet(url);
		config(httpget);
		CloseableHttpResponse response = null;
		try {
			response = getHttpClient(url).execute(httpget, HttpClientContext.create());
			HttpEntity entity = response.getEntity();
			String result = EntityUtils.toString(entity, "utf-8");
			EntityUtils.consume(entity);
			return result;
		} catch (IOException e) {
			e.printStackTrace();
		} finally {
			try {
				if (response != null)
					response.close();
			} catch (IOException e) {
				e.printStackTrace();
			}
		}
		return null;
	}

}
