package com.ripple.client.services;

import org.json.JSONObject;

import com.ripple.client.enums.Command;
import com.ripple.client.requests.Request;
import com.ripple.client.responses.Response;

/**
 * Document Start 
 * 获取网关余额服务
 * Document End 
 * Author: 扶摇直上 songwenpeng@le.com
 * Time: 2016年7月16日 下午11:59:30
 */
public class GatewayBalancesService extends Service {

	/**
	 * Document Start 
	 * 获取网关余额
	 * Document End 
	 * Author: 扶摇直上 songwenpeng@le.com
	 * Time: 2016年7月17日 下午1:12:04
	 * @param gatewayAddress
	 */
	public void getGatewayBalance(String gatewayAddress, String[] hotAddress) {
		// TODO Auto-generated method stub
		Request request = this.getClient().newRequest(Command.gateway_balances);
		request.json("account", gatewayAddress);
		request.json("hotwallet", hotAddress);
		request.json("strict", true);

		logger.info(">>getGatewayBalance>>请求开始,请求数据为" + request.json().toString());
		//调用成功 回调
		request.once(Request.OnSuccess.class, new Request.OnSuccess() {
			@Override
			public void called(Response response) {
				result = response.message;
				logger.info(">>getGatewayBalance>>本次请求结束,响应数据为" + result.toString());
				doSuccess(result);
			}
		});
		request.request();
	}

	/**
	 * Document Start 
	 * 返回的数据类型如下所示
	 * 
	//	 {
	//    "result": {
	//        "ledger_current_index": 22655372, 
	//        "balances": {
	//            "rraBDW7zYFUUcJoVyC6Dk9jZYQxf6V1Mkj": [
	//                {
	//                    "currency": "NBI", 
	//                    "value": "44040.64364364365"
	//                }, 
	//                {
	//                    "currency": "MMB", 
	//                    "value": "10000000"
	//                }
	//            ]
	//        }, 
	//        "validated": false, 
	//        "obligations": {
	//            "MMB": "290089969.9999999", 
	//            "NBI": "199123.356356356"
	//        }, 
	//        "account": "rLDykZiJ2Zm6UB937jfZPiRnnh85X7CBCS"
	//    }, 
	//    "id": 0, 
	//    "type": "response", 
	//    "status": "success"
	//}
	 * Document End 
	 * Author: 扶摇直上 songwenpeng@le.com
	 * Time: 2016年7月17日 下午1:24:35
	 * @see com.ripple.client.services.Service#doSuccess(org.json.JSONObject)
	 */
	@Override
	public void doSuccess(JSONObject result) {
		// TODO Auto-generated method stub
		super.doSuccess(result);
	}
}
