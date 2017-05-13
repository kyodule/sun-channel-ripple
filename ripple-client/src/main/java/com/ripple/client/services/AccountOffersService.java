package com.ripple.client.services;

import org.json.JSONObject;

import com.ripple.client.enums.Command;
import com.ripple.client.requests.Request;
import com.ripple.client.responses.Response;

/**
 * Document Start 
 * 获取指定账户的出价
 * Document End 
 * Author: 扶摇直上 songwenpeng@le.com
 * Time: 2016年7月21日 下午10:38:34
 */
public class AccountOffersService extends Service {

	/**
	 * Document Start 
	 * 获取指定账户的出价
	 * Document End 
	 * Author: 扶摇直上 songwenpeng@le.com
	 * Time: 2016年7月21日 下午10:40:31
	 * @param accountAddress
	 */
	public void getAccountOffersService(String accountAddress) {
		Request request = this.getClient().newRequest(Command.account_offers);
		request.json("account", accountAddress);
		logger.info(">>getAccountOffersService>>请求开始,请求数据为" + request.json().toString());
		//调用成功 回调
		request.once(Request.OnSuccess.class, new Request.OnSuccess() {
			@Override
			public void called(Response response) {
				result = response.message;
				logger.info(">>getAccountOffersService>>本次请求结束,响应数据为" + result.toString());
				doSuccess(result);
			}
		});
		request.request();
	}

	/**
	 * Document Start 
	 * 返回的数据格式如下所示
	 * 
// 	{
//	    "result": {
//	        "ledger_current_index": 22763163,
//	        "offers": [{
//	            "flags": 0,
//	            "quality": "0.000000007599140009999998",
//	            "seq": 6578020,
//	            "taker_gets": "29740867287",
//	            "taker_pays": {
//	                "currency": "USD",
//	                "issuer": "rMwjYedjc7qqtKYVLiAccJSmCwih4LnE2q",
//	                "value": "226.0050145327418"
//	            }
//        	}],  //出价者
//	        "validated": false,
//	        "account": "rLDykZiJ2Zm6UB937jfZPiRnnh85X7CBCS"
//	    },
//	    "id": 0,
//	    "type": "response",
//	    "status": "success"
//	}
	 * Document End 
	 * Author: 扶摇直上 songwenpeng@le.com
	 * Time: 2016年7月17日 下午1:26:25
	 * @see com.ripple.client.services.Service#doSuccess(org.json.JSONObject)
	 */
	@Override
	public void doSuccess(JSONObject result) {
		// TODO Auto-generated method stub
		super.doSuccess(result);
	}

}
