package com.ripple.client.services;

import org.json.JSONObject;

import com.ripple.client.enums.Command;
import com.ripple.client.requests.Request;
import com.ripple.client.responses.Response;

/**
 * Document Start 
 * Submit服务
 * Submit有两种模式
 * Submit-only：只是提交模式 此种模式是已经签名的数据 tx_blob
 * Sign-and-submit:签名加提交模式 此种模式是原始数据需要先签名然后提交 此种模式用户开发环境和测试环境
 * 使用tx命令监听Submit是否成功
 * 可以重复Submit 幂等性 因为有Sequence号 所以不会重复
 * Document End 
 * songwenpeng@letv.com
 * 乐视控股（北京）有限公司 
 * 2016年7月5日 下午2:37:09
 */
public class SubmitService extends Service{

	/**
	 * Document Start 
	 * Submit模式
	 * Document End 
	 * Author: 扶摇直上 songwenpeng@le.com
	 * Time: 2016年7月17日 下午1:54:22
	 * @param transactionSignedBlob 签名的交易二进制数据
	 * @param failHard 是否重试 若设置 ture不重试 false 重试
	 */
	public void submitOnlyMode(String transactionSignedBlob,boolean failHard) {
		Request request = this.getClient().newRequest(Command.submit);
		request.json("tx_blob", transactionSignedBlob);
		request.json("fail_hard", failHard);
		logger.info(">>submitOnlyMode>>请求开始,请求数据为" + request.json().toString());
		//调用成功 回调
		request.once(Request.OnSuccess.class, new Request.OnSuccess() {
			@Override
			public void called(Response response) {
				result = response.message;
				logger.info(">>submitOnlyMode>>本次请求结束,响应数据为" + result.toString());
				doSuccess(result);
			}
		});
		request.request();
	}
	
	/**
	 * Document Start 
	 * sign并且Submit模式
	 * Document End 
	 * Author: 扶摇直上 songwenpeng@le.com
	 * Time: 2016年7月22日 下午11:36:16
	 * @param tx_json Transaction JSON格式数据
	 * @param secret 秘钥
	 * @param failHard 是否重试 若设置 ture不重试 false 重试
	 */
	public void signAndSubmitMode(JSONObject tx_json,String secret,boolean failHard){
		Request request = this.getClient().newRequest(Command.submit);
		request.json("tx_json", tx_json);
		request.json("secret", secret);
		request.json("fail_hard", failHard);
		logger.info(">>signAndSubmitMode>>请求开始,请求数据为" + request.json().toString());
		//调用成功 回调
		request.once(Request.OnSuccess.class, new Request.OnSuccess() {
			@Override
			public void called(Response response) {
				result = response.message;
				logger.info(">>signAndSubmitMode>>本次请求结束,响应数据为" + result.toString());
				doSuccess(result);
			}
		});
		request.request();
	}
	
	@Override
	public void doSuccess(JSONObject result) {
		// TODO Auto-generated method stub
		super.doSuccess(result);
	}

}
