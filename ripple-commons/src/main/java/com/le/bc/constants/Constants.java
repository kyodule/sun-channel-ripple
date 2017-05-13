package com.le.bc.constants;

import com.le.bc.file.PropertyUtil;

/**
 * Document Start 
 * 所有的常量设置在此接口中设定 
 * Document End 
 * songwenpeng@letv.com 
 * 乐视控股（北京）有限公司
 * 2016年7月3日 下午2:43:46
 */
public interface Constants {

	// 手续费
	public static final String RIPPLE_GATEWAY_FEE_AMOUNT = PropertyUtil.getPropertyByName("RIPPLE_GATEWAY_FEE_AMOUNT");
	// Ripple官方访问地址
	public static final String GLOBAL_RIPPLE_CHAIN_URL = PropertyUtil.getPropertyByName("GLOAL_RIPPLE_CHAIN_URL");
	//Rippled本地服务器访问地址
	public static final String LOCAL_RIPPLE_CHAIN_URL = PropertyUtil.getPropertyByName("LOCAL_RIPPLE_CHAIN_URL");
	
	//转出备付金XRP账号配置
	public static final String XRP_ADDRESS = PropertyUtil.getPropertyByName("XRP_ADDRESS");
	public static final String XRP_Key = PropertyUtil.getPropertyByName("XRP_Key");
	public static final String XRP_ACCOUNT_RESERVE = PropertyUtil.getPropertyByName("XRP_ACCOUNT_RESERVE");
}
