package com.le.bc.commons;

/**
 * Document Start 
 * UID工具类
 * Document End 
 * Author: songwenpeng@le.com
 * Time: 2016年10月27日 上午1:16:47
 */
public class UIDUtil {
	//	TO交易单号 
	public static String TRADE_ORDER_PREFIX = "TO";
	//	PO支付单号    
	public static String PAYMENT_ORDER_PREFIX = "PO";
	//	RO退款单号    
	public static String REFUND_ORDER_PREFIX = "RO";
	//	FO出款单号    
	public static String FUNDOUT_ORDER_PREFIX = "FO";
	//	CO渠道单号    
	public static String CHANNEL_ORDER_PREFIX = "CO";
	//	AU认证单号   
	public static String AUTH_ORDER_PREFIX = "AU";
	//	CA认证渠道单号   
	public static String CHANNELAUTH_ORDER_PREFIX = "CA";
	//	REQ请求流水号  
	public static String REQUEST_SEQUENCE_PREFIX = "RQ";

	/**
	 * Document Start 
	 * 生成一个交易单号
	 * Document End 
	 * Author: songwenpeng@le.com
	 * Time: 2016年8月25日 下午5:09:21
	 * @return
	 */
	public static String genTradeOrderNO() {
		return TRADE_ORDER_PREFIX + System.nanoTime() + genRandomIn3();
	}

	/**
	 * Document Start 
	 * 生成一个支付单号
	 * Document End 
	 * Author: songwenpeng@le.com
	 * Time: 2016年8月25日 下午5:12:07
	 * @return
	 */
	public static String genPaymentOrderNO() {
		return PAYMENT_ORDER_PREFIX + System.nanoTime() + genRandomIn3();
	}

	/**
	 * Document Start 
	 * 生成一个退款订单号
	 * Document End 
	 * Author: songwenpeng@le.com
	 * Time: 2016年8月25日 下午5:13:00
	 * @return
	 */
	public static String genRefundOrderNO() {
		return REFUND_ORDER_PREFIX + System.nanoTime() + genRandomIn3();
	}

	/**
	 * Document Start 
	 * 生成一个出款单号
	 * Document End 
	 * Author: songwenpeng@le.com
	 * Time: 2016年8月25日 下午5:13:59
	 * @return
	 */
	public static String genFundoutOrderNO() {
		return FUNDOUT_ORDER_PREFIX + System.nanoTime() + genRandomIn3();
	}

	/**
	 * Document Start 
	 * 生成一个CA认证渠道单号
	 * Document End 
	 * Author: songwenpeng@le.com
	 * Time: 2016年8月25日 下午5:14:42
	 * @return
	 */
	public static String genChannelOrderNO() {
		return CHANNELAUTH_ORDER_PREFIX + System.nanoTime() + genRandomIn3();
	}

	/**
	 * Document Start 
	 * 生成一个认证单号
	 * Document End 
	 * Author: songwenpeng@le.com
	 * Time: 2016年8月25日 下午5:15:09
	 * @return
	 */
	public static String genAuthOrderNO() {
		return AUTH_ORDER_PREFIX + System.nanoTime() + genRandomIn3();
	}

	/**
	 * Document Start 
	 * 生成一个CA认证渠道单号
	 * Document End 
	 * Author: songwenpeng@le.com
	 * Time: 2016年8月25日 下午5:16:05
	 * @return
	 */
	public static String genChannelAuthOrderNO() {
		return REFUND_ORDER_PREFIX + System.nanoTime() + genRandomIn3();
	}

	/**
	 * Document Start 
	 * 生成一个请求流水号
	 * Document End 
	 * Author: songwenpeng@le.com
	 * Time: 2016年8月25日 下午5:55:31
	 * @return
	 */
	public static String genRequestSequenceNO() {
		return REQUEST_SEQUENCE_PREFIX + System.nanoTime() + genRandomIn3();
	}
	
	/**
	 * Document Start 
	 * 获取一个KeyID
	 * Document End 
	 * Author: songwenpeng@le.com
	 * Time: 2016年8月25日 下午5:19:03
	 * @return
	 */
	public static String genKeyID() {
		return "" + System.currentTimeMillis() + System.nanoTime();
	}
	
	/**
	 * Document Start 
	 * 生成个人会员ID
	 * Document End 
	 * Author: songwenpeng@le.com
	 * Time: 2016年8月25日 下午5:36:28
	 * @return
	 */
	public static String genPersonalUserID(){
		return "1" + System.nanoTime() + genRandomIn3();
	}
	
	/**
	 * Document Start 
	 * 生成企业会员ID
	 * Document End 
	 * Author: songwenpeng@le.com
	 * Time: 2016年8月25日 下午5:36:49
	 * @return
	 */
	public static String genCompanyUserID(){
		return "2" + System.nanoTime() + genRandomIn3();
	}
	
	/**
	 * Document Start 
	 * 获取三位的随机数
	 * Document End 
	 * Author: songwenpeng@le.com
	 * Time: 2016年8月25日 下午5:06:52
	 * @return
	 */
	public static String genRandomIn3() {
		long seed = Math.round(Math.random() * 100);
		if (seed <= 99 && seed > 9)
			return "0" + Long.toString(seed);
		else if (seed >= 0 && seed <= 9)
			return "00" + Long.toString(seed);
		else
			return Long.toString(seed);
	}
}
