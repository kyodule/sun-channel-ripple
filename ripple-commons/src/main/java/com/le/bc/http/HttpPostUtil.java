package com.le.bc.http;

import java.io.BufferedReader;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.OutputStream;
import java.io.Reader;
import java.io.UnsupportedEncodingException;
import java.net.HttpURLConnection;
import java.net.URL;
import java.util.ArrayList;
import java.util.Collections;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;

import org.apache.commons.lang3.StringUtils;

/**
 * Document Start
 * HttpPost工具类
 * Document End
 * songwenpeng@letv.com
 * 乐视控股（北京）有限公司
 * 2015年12月23日 下午2:14:10
 */
public class HttpPostUtil {

	/**
	 * Document Start
	 * POST调用
	 * Document End
	 * songwenpeng@letv.com
	 * 乐视控股（北京）有限公司
	 * 2015年12月23日 下午2:14:33
	 * @param httpUrl
	 * @param params
	 * @return
	 */
	public static String callHttpPOST(String httpUrl, Map<String, String> params) {
		return callHttpPOST(httpUrl, params, null);
	}

	/**
	 * Document Start
	 * POST调用
	 * Document End
	 * songwenpeng@letv.com
	 * 乐视控股（北京）有限公司
	 * 2015年12月23日 下午2:14:35
	 * @param httpUrl
	 * @param params
	 * @param encod
	 * @return
	 */
	public static String callHttpPOST(String httpUrl, Map<String, String> params, String encod) {
		String strReturn = "";
		try {
			if (StringUtils.isEmpty(encod)) {
				encod = "UTF-8";
			}
			URL url = new java.net.URL(httpUrl);
			HttpURLConnection conn = (HttpURLConnection) url.openConnection();
			conn.setConnectTimeout(30000);
			conn.setReadTimeout(30000);
			conn.setRequestMethod("POST");
			String paramsStr = createLinkString(params);
			System.out.println("send message url:" + httpUrl + "?" + paramsStr);
			if (!StringUtils.isEmpty(paramsStr)) {
				conn.setDoOutput(true);
				OutputStream out = conn.getOutputStream();

				out.write(paramsStr.getBytes(encod));
				out.flush();
				out.close();
			}

			java.io.InputStream ins2 = conn.getInputStream();
			strReturn = convertStreamToString(ins2, encod);

			ins2.close();
		} catch (Exception e) {
			e.printStackTrace();
			System.out.println("call HTTPAPI faile URL=" + httpUrl + "  params=" + createLinkString(params) + "  faile case=" + e.getMessage());
		}

		return strReturn;
	}

	/**
	 * Document Start
	 * Stream转换成String
	 * Document End
	 * songwenpeng@letv.com
	 * 乐视控股（北京）有限公司
	 * 2015年12月23日 下午2:15:41
	 * @param ins
	 * @param encod
	 * @return
	 */
	private static String convertStreamToString(InputStream ins, String encod) {
		String strRet = "";
		try {
			StringBuffer sbuf = new StringBuffer();
			InputStreamReader reader = new InputStreamReader(ins, encod);
			Reader buf = new BufferedReader(reader);
			int ch;
			while ((ch = buf.read()) > -1) {
				sbuf.append((char) ch);
			}
			buf.close();
			strRet = sbuf.toString();
			sbuf.delete(0, sbuf.length());
		} catch (Exception e) {
			e.printStackTrace();
			return "";
		}
		return strRet;
	}

	/**
	 * Document Start
	 * 将Map类型参数使用&字符进行链接
	 * 并且参数按照a~z进行排序
	 * Document End
	 * songwenpeng@letv.com
	 * 乐视控股（北京）有限公司
	 * 2015年12月23日 下午2:15:00
	 * @param params
	 * @return
	 */
	public static String createLinkString(Map<String, String> params) {
		String prestr = StringUtils.EMPTY;
		if (params == null || params.size() <= 0) {
			return prestr;
		}
		List<String> keys = new ArrayList<String>(params.keySet());
		Collections.sort(keys);

		for (int i = 0; i < keys.size(); i++) {
			String key = keys.get(i);
			String value = params.get(key);

			if (i == keys.size() - 1) {
				prestr = prestr + key + "=" + value;
			} else {
				prestr = prestr + key + "=" + value + "&";
			}
		}
		return prestr;
	}

	/**
	 * Document Start 
	 * Request参数进行抽取 形成Map格式
	 * Document End 
	 * Author: songwenpeng@le.com
	 * Time: 2016年8月14日 上午11:58:14
	 * @param request
	 * @return
	 */
	public static Map<String, String> getParamaterMap(HttpServletRequest request) {
		StringBuffer paramStr = new StringBuffer("");
		Map<String, String> params = new HashMap<String, String>();
		Map<String, String[]> requestParams = request.getParameterMap();
		for (Iterator<String> iter = requestParams.keySet().iterator(); iter.hasNext();) {
			String name = (String) iter.next();
			String[] values = (String[]) requestParams.get(name);
			String valueStr = "";
			for (int i = 0; i < values.length; i++) {
				valueStr = (i == values.length - 1) ? valueStr + values[i] : valueStr + values[i] + ",";
			}
			// 乱码解决，这段代码在出现乱码时使用。如果mysign和sign不相等也可以使用这段代码转化
			try {
				if (request.getCharacterEncoding().toUpperCase().equals("ISO-8859-1")) {
					valueStr = new String(valueStr.getBytes("ISO-8859-1"), "UTF-8");
				}

			} catch (UnsupportedEncodingException e) {
				e.printStackTrace();
			}
			paramStr.append(name).append("[").append(valueStr).append("],");
			params.put(name, valueStr);
		}
		return params;
	}
}
