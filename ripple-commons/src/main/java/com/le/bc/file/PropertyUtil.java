package com.le.bc.file;

import java.io.IOException;
import java.io.InputStream;
import java.util.Properties;

/**
 * Document Start
 * 属性文件工具类
 * Document End
 * songwenpeng@letv.com
 * 乐视控股（北京）有限公司
 * 2016年1月20日 下午6:49:21
 */
public class PropertyUtil {

	private static String propertiesFileName = "config.properties";

	/**
	 * Document Start
	 * 获取指定属性的String值
	 * Document End
	 * songwenpeng@letv.com
	 * 乐视控股（北京）有限公司
	 * 2016年1月20日 下午7:42:18
	 * @param pro
	 * @param propertiesFileName
	 * @return
	 * @throws IOException
	 */
	public static String getPropertyByName(String propertyName) {
		InputStream in = null;
		String value = null;
		try {
			Properties prop = new Properties();
			in = PropertyUtil.class.getClassLoader().getResourceAsStream(propertiesFileName);
			if (null == in)
				throw new RuntimeException("没有找到配置文件" + propertiesFileName);
			prop.load(in);
			in.close();
			value = prop.getProperty(propertyName);
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			if (null != in)
				try {
					in.close();
				} catch (IOException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
		}
		return value;
	}
	
	

}
