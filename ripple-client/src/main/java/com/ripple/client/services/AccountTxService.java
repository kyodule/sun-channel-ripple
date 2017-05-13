package com.ripple.client.services;

import org.json.JSONObject;

import com.ripple.client.enums.Command;
import com.ripple.client.requests.Request;
import com.ripple.client.responses.Response;

/**
 * Document Start 
 * 获取指定账户支付的货币
 * account_info
 * Document End 
 * songwenpeng@letv.com
 * 乐视控股（北京）有限公司 
 * 2016年7月5日 下午2:37:09
 */
public class AccountTxService extends Service {

	/**
	 * Document Start 
	 * 获取指定账户支付的货币
	 * Document End 
	 * Author: 扶摇直上 songwenpeng@le.com
	 * Time: 2016年7月21日 下午10:37:27
	 * @param accountAddress
	 */
	public void getAccountTx(String accountAddress) {
		Request request = this.getClient().newRequest(Command.account_tx);
		request.json("account", accountAddress);
		request.json("ledger_index_min", -1);
		request.json("ledger_index_max", -1);
		logger.info(">>getAccountTx>>请求开始,请求数据为" + request.json().toString());
		//调用成功 回调
		request.once(Request.OnSuccess.class, new Request.OnSuccess() {
			@Override
			public void called(Response response) {
				result = response.message;
				logger.info(">>getAccountTx>>本次请求结束,响应数据为" + result.toString());
				doSuccess(result);
			}
		});
		request.request();
	}

	/**
	 * Document Start 
	 * 返回的数据格式如下所示
	 * Document End 
	 * Author: 扶摇直上 songwenpeng@le.com
	 * Time: 2016年7月17日 下午1:26:25
	 * validated : If true, this data comes from a validated ledger.
	 * @see com.ripple.client.services.Service#doSuccess(org.json.JSONObject)
	 */
	@Override
	public void doSuccess(JSONObject result) {
		// TODO Auto-generated method stub
		super.doSuccess(result);
	}

}
