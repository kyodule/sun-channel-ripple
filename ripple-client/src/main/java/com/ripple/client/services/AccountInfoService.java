package com.ripple.client.services;

import org.json.JSONObject;

import com.ripple.client.enums.Command;
import com.ripple.client.requests.Request;
import com.ripple.client.responses.Response;

/**
 * Document Start 
 * 获取账户信息服务
 * account_info
 * Document End 
 * songwenpeng@letv.com
 * 乐视控股（北京）有限公司 
 * 2016年7月5日 下午2:37:09
 */
public class AccountInfoService extends Service {

	/**
	 * Document Start 
	 * 获取指定账户的信息 
	 * Document End 
	 * songwenpeng@letv.com 
	 * 乐视控股（北京）有限公司
	 * 2016年7月8日 上午1:11:58
	 * 
	 * @param accountAddress
	 */
	public void getAccountInfo(String accountAddress) {
		Request request = this.getClient().newRequest(Command.account_info);
		request.json("account", accountAddress);
		logger.info(">>getAccountInfo>>请求开始,请求数据为" + request.json().toString());
		//调用成功 回调
		request.once(Request.OnSuccess.class, new Request.OnSuccess() {
			@Override
			public void called(Response response) {
				result = response.message;
				logger.info(">>getAccountInfo>>本次请求结束,响应数据为" + result.toString());
				doSuccess(result);
			}
		});
		request.request();
	}

	/**
	 * Document Start 
	 * 返回的数据格式如下所示
	//	{
	//    "result": {
	//        "ledger_current_index": 22655474, 
	//        "validated": false, 
	//        "account_data": {
	//            "Account": "rLDykZiJ2Zm6UB937jfZPiRnnh85X7CBCS", 
	//            "OwnerCount": 4, 
	//            "PreviousTxnLgrSeq": 22608934, 
	//            "LedgerEntryType": "AccountRoot", 
	//            "index": "27BAFA346D13657A83C8E70E1A2D6C85B3F1A39CA787A24E93764FBE1A59BCAA", 
	//            "PreviousTxnID": "2386E2CB7FBA13716B0C241753AE923607B3496FFAD7D349F0AA1BD823AAFD14", 
	//            "Flags": 8388608, 
	//            "Sequence": 27, 
	//            "Balance": "159969712"
	//        }
	//    }, 
	//    "id": 0, 
	//    "type": "response", 
	//    "status": "success"
	//}
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
