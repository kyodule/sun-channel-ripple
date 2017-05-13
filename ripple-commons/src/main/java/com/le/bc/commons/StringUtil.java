package com.le.bc.commons;

import org.apache.commons.lang.StringUtils;

import javax.servlet.http.HttpServletRequest;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.*;

/**
 * Document Start 
 * String工具类
 * Document End 
 * Author: songwenpeng@le.com
 * Time: 2016年9月4日 下午3:55:52
 */
public class StringUtil extends StringUtils {

	private StringUtil() {
		super();
	}

	public final static boolean isTrimEmpty(Object obj) {
		return null == obj || obj.toString().trim().length() <= 0;
	}

	public final static boolean isEmpty(String... strList) {
		for (String str : strList) {
			if (isEmpty(str)) {
				return true;
			}
		}
		return false;
	}

	public final static boolean isEmptyNotTrim(Object obj) {
		return null == obj || obj.toString().length() <= 0;
	}

	public final static boolean isNoTrimEmpty(Object obj) {
		return !StringUtil.isTrimEmpty(obj);
	}

	public static boolean isNumeric(String str) {
		return isNoTrimEmpty(str) && StringUtils.isNumeric(str);
	}

	public final static List splitTrimList(String str, String separatorChars) {
		String[] strArray = StringUtil.splitTrim(str, separatorChars);
		return null == strArray ? null : new ArrayList<String>(Arrays.asList(strArray));
	}

	public final static List splitTrimList(String str, String separatorChars, String prefixTrimChars, String suffixTrimChars) {
		String[] strArray = StringUtil.splitTrim(str, separatorChars, prefixTrimChars, suffixTrimChars);
		return null == strArray ? null : new ArrayList<String>(Arrays.asList(strArray));
	}

	public final static String[] splitTrim(String str, String separatorChars) {
		if (null == str) {
			return null;
		}
		List<String> list = new ArrayList<String>();
		String[] values = str.split(separatorChars);
		for (int i = 0, len = values.length; i < len; i++) {
			if (StringUtil.isTrimEmpty(values[i])) {
				continue;
			}
			list.add(values[i].trim());
		}
		if (list.size() <= 0) {
			return new String[] {};
		}
		values = new String[list.size()];
		list.toArray(values);
		return values;
	}

	public final static String[] splitTrim(String str, String separatorChars, String prefixTrimChars, String suffixTrimChars) {
		if (null == str) {
			return null;
		}
		if (StringUtil.isNotEmpty(prefixTrimChars) && str.startsWith(prefixTrimChars)) {
			str = str.substring(prefixTrimChars.length());
		}
		if (StringUtil.isNotEmpty(suffixTrimChars) && str.endsWith(suffixTrimChars)) {
			str = str.substring(0, str.length() - suffixTrimChars.length());
		}
		return StringUtil.splitTrim(str, separatorChars);
	}

	public static String objToTrimedStr(Object param) {
		return (param != null) ? param.toString().trim() : "";
	}

	public static String conversionIdTo15(String code) {
		if (code == null || "".equals(code) || code.length() != 18) {
			return "";
		}
		StringBuffer buffStr = new StringBuffer();
		buffStr.append(code.substring(0, 6));
		buffStr.append(code.substring(8, code.length() - 1));
		return buffStr.toString();
	}

	public static String conversionIdTo18(String idStr) {
		char c[] = idStr.toCharArray();
		char w[] = new char[18];
		System.arraycopy(c, 0, w, 0, 6);
		System.arraycopy(c, 6, w, 8, 9);
		w[6] = '1';
		w[7] = '9';
		w[17] = getCheckNum(w);
		return new String(w);
	}

	private static char getCheckNum(char[] c) {
		final int w[] = new int[] { 7, 9, 10, 5, 8, 4, 2, 1, 6, 3, 7, 9, 10, 5, 8, 4, 2 };
		final char y[] = new char[] { '1', '0', 'X', '9', '8', '7', '6', '5', '4', '3', '2' };

		if (c.length < 17) {
			throw new IllegalArgumentException();
		}

		int id[] = new int[c.length];
		for (int i = 0; i < 17; i++) {
			id[i] = Character.getNumericValue(c[i]);
		}

		int sum = 0;
		for (int i = 0; i < 17; i++) {
			sum += id[i] * w[i];
		}

		int i = sum % 11;
		return y[i];
	}

	public static String ToSBC(String input) {
		char[] c = input.toCharArray();
		for (int i = 0; i < c.length; i++) {
			if (c[i] == 32) {
				c[i] = (char) 12288;
				continue;
			}
			if (c[i] < 127)
				c[i] = (char) (c[i] + 65248);
		}
		return new String(c);
	}

	public static String encryptionMd5(String plainText) {
		String re_md5 = new String();
		try {
			MessageDigest md = MessageDigest.getInstance("MD5");
			md.update(plainText.getBytes());
			byte b[] = md.digest();

			int i;

			StringBuffer buf = new StringBuffer("");
			for (int offset = 0; offset < b.length; offset++) {
				i = b[offset];
				if (i < 0)
					i += 256;
				if (i < 16)
					buf.append("0");
				buf.append(Integer.toHexString(i));
			}

			re_md5 = buf.toString();

		} catch (NoSuchAlgorithmException e) {
			//TODO exception 处理
			re_md5 = new String();
		}
		return re_md5;
	}

	public static String longToDateStr(long longDate, String dateFormat) {
		if (longDate == 0) {
			return null;
		}

		if (isTrimEmpty(dateFormat)) {
			dateFormat = "yyyy-MM-DD HH:mm:ss.SSS";
		}

		SimpleDateFormat df = new SimpleDateFormat(dateFormat);
		return df.format(new Date(longDate));
	}

	public static Date parseDate(String dateString, String dateFormat) {
		if (isTrimEmpty(dateFormat)) {
			dateFormat = "yyyy-MM-DD HH:mm:ss.SSS";
		}
		SimpleDateFormat df = new SimpleDateFormat(dateFormat);
		Date date = new Date();
		try {
			date = df.parse(dateString);
		} catch (ParseException e) {
			//TODO exception 处理
			date = new Date();
		}
		return date;
	}

	public static long strToDateLong(String dateString, String dateFormat) {
		Date date = new Date();
		try {
			date = parseDate(dateString, dateFormat);
		} catch (Exception e) {
			//TODO exception 处理
			return 0;
		}
		return date.getTime();
	}

	public static String jsonFormat(String jsonStr) {
		int level = 0;
		StringBuffer jsonForMatStr = new StringBuffer();
		for (int i = 0; i < jsonStr.length(); i++) {
			char c = jsonStr.charAt(i);
			if (level > 0 && '\n' == jsonForMatStr.charAt(jsonForMatStr.length() - 1)) {
				jsonForMatStr.append(getJsonLevelStr(level));
			}
			switch (c) {
			case '{':
			case '[':
				jsonForMatStr.append(c + "\n");
				level++;
				break;
			case ',':
				jsonForMatStr.append(c + "\n");
				break;
			case '}':
			case ']':
				jsonForMatStr.append("\n");
				level--;
				jsonForMatStr.append(getJsonLevelStr(level));
				jsonForMatStr.append(c);
				break;
			default:
				jsonForMatStr.append(c);
				break;
			}
		}

		return jsonForMatStr.toString();

	}

	// 将long类型的时间转换为yyyyMMdd格式
	public static String getYYYYMMDDDateStr(long merDateLong) {
		Date merDate = new Date(merDateLong);
		SimpleDateFormat merDateFormat = new SimpleDateFormat("yyyyMMdd");
		String merDateStr = merDateFormat.format(merDate);
		return merDateStr;
	}

	// 将long类型的时间转换为yyyyMMdd格式
	public static String getYYMMDDHHMMSSFFFFDateStr(long merDateLong) {
		Date merDate = new Date(merDateLong);
		SimpleDateFormat merDateFormat = new SimpleDateFormat("yyMMddHHmmssFFFF");
		String merDateStr = merDateFormat.format(merDate);
		return merDateStr;
	}

	// 将时间转换为当日0点的long时间
	public static long getDayZeroTimeInMilis(long dateLong) {
		Calendar calendar = Calendar.getInstance();
		calendar.setTime(new Date(dateLong));
		//将小时至0  
		calendar.set(Calendar.HOUR_OF_DAY, 0);
		//将分钟至0  
		calendar.set(Calendar.MINUTE, 0);
		//将秒至0  
		calendar.set(Calendar.SECOND, 0);
		//将毫秒至0  
		calendar.set(Calendar.MILLISECOND, 0);

		return calendar.getTime().getTime();

	}

	private static String getJsonLevelStr(int level) {
		StringBuffer levelStr = new StringBuffer();
		for (int levelI = 0; levelI < level; levelI++) {
			levelStr.append("\t");
		}
		return levelStr.toString();
	}

	/**
	 * 获取http请求的详细信息,包括
	 * 
	 * @param request
	 *            请求
	 * @param params
	 *            参数map
	 * @return http请求详细信息的String
	 */
	public static String getHttpServletRequestInfo(HttpServletRequest request, Map<String, String> params) {
		StringBuffer stringBuffer = new StringBuffer();
		stringBuffer.append("From:").append(request.getRemoteAddr()).append(":").append(request.getRemotePort());
		if (params != null) {
			stringBuffer.append(" params: ").append("{");
			for (String key : params.keySet()) {
				stringBuffer.append(" ").append(key).append(":").append(params.get(key));
			}
			stringBuffer.append("}");
		}
		return stringBuffer.toString();
	}

	/**
	 * Document Start 获取字符串中的数字 Document End zhangxiaojun1@letv.com 乐视控股（北京）有限公司
	 * 2016年1月8日 下午12:59:43
	 * 
	 * @param str
	 * @return
	 */
	public static String getNumInString(String str) {
		str = str.trim();
		String result = "";
		if (!StringUtil.isTrimEmpty(str)) {
			for (int i = 0; i < str.length(); i++) {
				if (str.charAt(i) >= 48 && str.charAt(i) <= 57) {
					result += str.charAt(i);
				}
			}
		}
		return result;
	}
}
