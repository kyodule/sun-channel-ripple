package com.ripple.client.services;

import org.json.JSONObject;

import com.ripple.client.enums.Command;
import com.ripple.client.requests.Request;
import com.ripple.client.responses.Response;

/**
 * Document Start 
 * 获取账户信任线服务
 * account_info
 * Document End 
 * songwenpeng@letv.com
 * 乐视控股（北京）有限公司 
 * 2016年7月5日 下午2:37:09
 */
public class AccountLinesService extends Service {

	/**
	 * Document Start 
	 * 获取指定账户的信任线信息 
	 * Document End 
	 * songwenpeng@letv.com 
	 * 乐视控股（北京）有限公司
	 * 2016年7月8日 上午1:11:58
	 * 
	 * @param accountAddress
	 */
	public void getAccountLines(String accountAddress) {
		Request request = this.getClient().newRequest(Command.account_lines);
		request.json("account", accountAddress);
		logger.info(">>getAccountLines>>请求开始,请求数据为" + request.json().toString());
		//调用成功 回调
		request.once(Request.OnSuccess.class, new Request.OnSuccess() {
			@Override
			public void called(Response response) {
				result = response.message;
				logger.info(">>getAccountLines>>本次请求结束,响应数据为" + result.toString());
				doSuccess(result);
			}
		});
		request.request();
	}

	/**
	 * Document Start 
	 * 返回的数据格式如下所示
	//{
	//    "result": {
	//        "lines": [
	//            {
	//                "balance": "-44040.64364364365", 
	//                "limit": "300000", 
	//                "quality_in": 0, 
	//                "quality_out": 0, 
	//                "currency": "NBI", 
	//                "limit_peer": "300000", 
	//                "account": "rraBDW7zYFUUcJoVyC6Dk9jZYQxf6V1Mkj"
	//            }, 
	//            {
	//                "balance": "-19.74", 
	//                "limit": "0", 
	//                "quality_in": 0, 
	//                "quality_out": 0, 
	//                "currency": "NBI", 
	//                "limit_peer": "10000", 
	//                "account": "r48rFNfaPJX7Rau7bXMyK1gc3zCMYe4qSe"
	//            }, 
	//            {
	//                "no_ripple": true, 
	//                "balance": "-1.96996996996997", 
	//                "limit": "0", 
	//                "quality_in": 0, 
	//                "quality_out": 0, 
	//                "currency": "NBI", 
	//                "limit_peer": "1000000000", 
	//                "account": "rP1vykjP7o1WBcXjdgiKyyLi2PT4cfS3FF", 
	//                "no_ripple_peer": true
	//            }, 
	//            {
	//                "no_ripple": true, 
	//                "balance": "0", 
	//                "limit": "0", 
	//                "quality_in": 0, 
	//                "quality_out": 0, 
	//                "currency": "NBI", 
	//                "limit_peer": "0", 
	//                "account": "rpJB2tF2vjPxDiiGAbVuWYRxTwTDEyvAgg", 
	//                "no_ripple_peer": true
	//            }, 
	//            {
	//                "balance": "0", 
	//                "limit": "0", 
	//                "quality_in": 0, 
	//                "quality_out": 0, 
	//                "currency": "NBI", 
	//                "limit_peer": "1000000000", 
	//                "account": "rHa3trZg2MzGTkGA4PLmsTPo9azHUGbhUV", 
	//                "no_ripple_peer": true
	//            }, 
	//            {
	//                "balance": "-851", 
	//                "limit": "0", 
	//                "quality_in": 0, 
	//                "quality_out": 0, 
	//                "currency": "NBI", 
	//                "limit_peer": "1000000000", 
	//                "account": "rKhytnCTzDvd9cfoqGZ3BdHKpjU65oSZaQ", 
	//                "no_ripple_peer": true
	//            }, 
	//            {
	//                "balance": "-61", 
	//                "limit": "0", 
	//                "quality_in": 0, 
	//                "quality_out": 0, 
	//                "currency": "NBI", 
	//                "limit_peer": "1000000000", 
	//                "account": "r9YsnpXzYhAbuX8jBJoCeaMqhRShcScGSq", 
	//                "no_ripple_peer": true
	//            }, 
	//            {
	//                "balance": "0", 
	//                "limit": "0", 
	//                "quality_in": 0, 
	//                "quality_out": 0, 
	//                "currency": "CNY", 
	//                "limit_peer": "10000", 
	//                "account": "r48rFNfaPJX7Rau7bXMyK1gc3zCMYe4qSe"
	//            }, 
	//            {
	//                "balance": "-1068.05005005005", 
	//                "limit": "0", 
	//                "quality_in": 0, 
	//                "quality_out": 0, 
	//                "currency": "NBI", 
	//                "limit_peer": "10000", 
	//                "account": "rTaVGCHrhBwQyLCM9UjzFDetnCgfGDESN"
	//            }, 
	//            {
	//                "balance": "0", 
	//                "limit": "0", 
	//                "quality_in": 0, 
	//                "quality_out": 0, 
	//                "currency": "CNY", 
	//                "limit_peer": "1000", 
	//                "account": "rTaVGCHrhBwQyLCM9UjzFDetnCgfGDESN", 
	//                "no_ripple_peer": true
	//            }, 
	//            {
	//                "balance": "-100", 
	//                "limit": "0", 
	//                "quality_in": 0, 
	//                "quality_out": 0, 
	//                "currency": "MMB", 
	//                "limit_peer": "200000", 
	//                "account": "r48rFNfaPJX7Rau7bXMyK1gc3zCMYe4qSe"
	//            }, 
	//            {
	//                "balance": "-10000000", 
	//                "limit": "300000000", 
	//                "quality_in": 0, 
	//                "quality_out": 0, 
	//                "currency": "MMB", 
	//                "limit_peer": "300000000", 
	//                "account": "rraBDW7zYFUUcJoVyC6Dk9jZYQxf6V1Mkj"
	//            }, 
	//            {
	//                "balance": "0", 
	//                "limit": "50000", 
	//                "quality_in": 0, 
	//                "quality_out": 0, 
	//                "currency": "CNY", 
	//                "limit_peer": "200000", 
	//                "account": "rraBDW7zYFUUcJoVyC6Dk9jZYQxf6V1Mkj"
	//            }, 
	//            {
	//                "balance": "0", 
	//                "limit": "0", 
	//                "quality_in": 0, 
	//                "quality_out": 0, 
	//                "currency": "USD", 
	//                "limit_peer": "1", 
	//                "account": "r48rFNfaPJX7Rau7bXMyK1gc3zCMYe4qSe"
	//            }, 
	//            {
	//                "balance": "-189998895.004004", 
	//                "limit": "0", 
	//                "quality_in": 0, 
	//                "quality_out": 0, 
	//                "currency": "MMB", 
	//                "limit_peer": "300000000", 
	//                "account": "rTaVGCHrhBwQyLCM9UjzFDetnCgfGDESN"
	//            }, 
	//            {
	//                "balance": "-193979.6103503503", 
	//                "limit": "0", 
	//                "quality_in": 0, 
	//                "quality_out": 0, 
	//                "currency": "NBI", 
	//                "limit_peer": "200000", 
	//                "account": "rJrd583hyiL8kCk8dKgN77Z5ZiTTjU1Upz"
	//            }, 
	//            {
	//                "balance": "-96968833", 
	//                "limit": "0", 
	//                "quality_in": 0, 
	//                "quality_out": 0, 
	//                "currency": "MMB", 
	//                "limit_peer": "100000000", 
	//                "account": "rJrd583hyiL8kCk8dKgN77Z5ZiTTjU1Upz"
	//            }, 
	//            {
	//                "balance": "-121231", 
	//                "limit": "0", 
	//                "quality_in": 0, 
	//                "quality_out": 0, 
	//                "currency": "MMB", 
	//                "limit_peer": "1000000000", 
	//                "account": "rD9Y7EvgByV1vHBdhWW9hJUqdaGw2RHPyk", 
	//                "no_ripple_peer": true
	//            }, 
	//            {
	//                "balance": "-570", 
	//                "limit": "0", 
	//                "quality_in": 0, 
	//                "quality_out": 0, 
	//                "currency": "MMB", 
	//                "limit_peer": "1000000000", 
	//                "account": "rn9DJ3dMMa8mYp8cFLE8a7hJNSVMaYRvqu", 
	//                "no_ripple_peer": true
	//            }, 
	//            {
	//                "balance": "-998.998998998999", 
	//                "limit": "0", 
	//                "quality_in": 0, 
	//                "quality_out": 0, 
	//                "currency": "NBI", 
	//                "limit_peer": "1000000000", 
	//                "account": "rn9DJ3dMMa8mYp8cFLE8a7hJNSVMaYRvqu", 
	//                "no_ripple_peer": true
	//            }, 
	//            {
	//                "balance": "-900325.995995996", 
	//                "limit": "0", 
	//                "quality_in": 0, 
	//                "quality_out": 0, 
	//                "currency": "MMB", 
	//                "limit_peer": "1000000000", 
	//                "account": "rarc5z5BipNG82uNgt6cmGAcoPgMThD1mT", 
	//                "no_ripple_peer": true
	//            }, 
	//            {
	//                "balance": "-1000000", 
	//                "limit": "0", 
	//                "quality_in": 0, 
	//                "quality_out": 0, 
	//                "currency": "MMB", 
	//                "limit_peer": "1000000000", 
	//                "account": "rrpGVhxPNy56Wryi2iLLQm1E7jhduDdxSg", 
	//                "no_ripple_peer": true
	//            }, 
	//            {
	//                "balance": "-994.98998998999", 
	//                "limit": "0", 
	//                "quality_in": 0, 
	//                "quality_out": 0, 
	//                "currency": "NBI", 
	//                "limit_peer": "1000000000", 
	//                "account": "rrpGVhxPNy56Wryi2iLLQm1E7jhduDdxSg", 
	//                "no_ripple_peer": true
	//            }, 
	//            {
	//                "balance": "-1100000", 
	//                "limit": "0", 
	//                "quality_in": 0, 
	//                "quality_out": 0, 
	//                "currency": "MMB", 
	//                "limit_peer": "1000000000", 
	//                "account": "r4kxoBDRatgQxF1FyzQZQUtvS3SWj2B6hY", 
	//                "no_ripple_peer": true
	//            }, 
	//            {
	//                "balance": "-4", 
	//                "limit": "0", 
	//                "quality_in": 0, 
	//                "quality_out": 0, 
	//                "currency": "MMB", 
	//                "limit_peer": "1000000000", 
	//                "account": "rHgwoNhXPqFVQohPCzqosuJDxMkQAV6b3i", 
	//                "no_ripple_peer": true
	//            }, 
	//            {
	//                "balance": "-998.996996996997", 
	//                "limit": "0", 
	//                "quality_in": 0, 
	//                "quality_out": 0, 
	//                "currency": "NBI", 
	//                "limit_peer": "1000000000", 
	//                "account": "rHgwoNhXPqFVQohPCzqosuJDxMkQAV6b3i", 
	//                "no_ripple_peer": true
	//            }, 
	//            {
	//                "balance": "0", 
	//                "limit": "0", 
	//                "quality_in": 0, 
	//                "quality_out": 0, 
	//                "currency": "NBI", 
	//                "limit_peer": "1000000000", 
	//                "account": "rwy5mhBbmrv8xiZwbHCu8SPnxR3r7znjq5", 
	//                "no_ripple_peer": true
	//            }, 
	//            {
	//                "balance": "0", 
	//                "limit": "0", 
	//                "quality_in": 0, 
	//                "quality_out": 0, 
	//                "currency": "MMB", 
	//                "limit_peer": "1000000000", 
	//                "account": "rwy5mhBbmrv8xiZwbHCu8SPnxR3r7znjq5", 
	//                "no_ripple_peer": true
	//            }, 
	//            {
	//                "balance": "-11", 
	//                "limit": "0", 
	//                "quality_in": 0, 
	//                "quality_out": 0, 
	//                "currency": "MMB", 
	//                "limit_peer": "1000000000", 
	//                "account": "rESqf9vjqCEMe3pw8RFdAoPSUwuTUqFqES", 
	//                "no_ripple_peer": true
	//            }, 
	//            {
	//                "balance": "0", 
	//                "limit": "0", 
	//                "quality_in": 0, 
	//                "quality_out": 0, 
	//                "currency": "CNY", 
	//                "limit_peer": "20000", 
	//                "account": "rJrd583hyiL8kCk8dKgN77Z5ZiTTjU1Upz"
	//            }, 
	//            {
	//                "balance": "-98", 
	//                "limit": "0", 
	//                "quality_in": 0, 
	//                "quality_out": 0, 
	//                "currency": "NBI", 
	//                "limit_peer": "1000000000", 
	//                "account": "rarc5z5BipNG82uNgt6cmGAcoPgMThD1mT", 
	//                "no_ripple_peer": true
	//            }, 
	//            {
	//                "balance": "0", 
	//                "limit": "0", 
	//                "quality_in": 0, 
	//                "quality_out": 0, 
	//                "currency": "MMB", 
	//                "limit_peer": "1000000000", 
	//                "account": "rGXTKkF9AEfVr2v1dvsC56NxTMynuN93bX", 
	//                "no_ripple_peer": true
	//            }, 
	//            {
	//                "balance": "0", 
	//                "limit": "0", 
	//                "quality_in": 0, 
	//                "quality_out": 0, 
	//                "currency": "MMB", 
	//                "limit_peer": "1000000000", 
	//                "account": "r4W1j1x3T71tCexng9432MXcw5qL5jyLrk", 
	//                "no_ripple_peer": true
	//            }, 
	//            {
	//                "balance": "0", 
	//                "limit": "0", 
	//                "quality_in": 0, 
	//                "quality_out": 0, 
	//                "currency": "NBI", 
	//                "limit_peer": "1000000000", 
	//                "account": "rGXTKkF9AEfVr2v1dvsC56NxTMynuN93bX", 
	//                "no_ripple_peer": true
	//            }, 
	//            {
	//                "balance": "0", 
	//                "limit": "0", 
	//                "quality_in": 0, 
	//                "quality_out": 0, 
	//                "currency": "NBI", 
	//                "limit_peer": "1000000000", 
	//                "account": "r4W1j1x3T71tCexng9432MXcw5qL5jyLrk", 
	//                "no_ripple_peer": true
	//            }, 
	//            {
	//                "balance": "-50", 
	//                "limit": "0", 
	//                "quality_in": 0, 
	//                "quality_out": 0, 
	//                "currency": "NBI", 
	//                "limit_peer": "1000000000", 
	//                "account": "rD7ewsQgi3JH6HYGYEsVwVveSvD94UqrvA", 
	//                "no_ripple_peer": true
	//            }, 
	//            {
	//                "balance": "0", 
	//                "limit": "0", 
	//                "quality_in": 0, 
	//                "quality_out": 0, 
	//                "currency": "MMB", 
	//                "limit_peer": "1000000000", 
	//                "account": "rnSzd4WVPY1vYDPC8M49Bk9LNqVygUyMrU", 
	//                "no_ripple_peer": true
	//            }, 
	//            {
	//                "balance": "-1", 
	//                "limit": "0", 
	//                "quality_in": 0, 
	//                "quality_out": 0, 
	//                "currency": "NBI", 
	//                "limit_peer": "1000000000", 
	//                "account": "rnSzd4WVPY1vYDPC8M49Bk9LNqVygUyMrU", 
	//                "no_ripple_peer": true
	//            }
	//        ], 
	//        "account": "rLDykZiJ2Zm6UB937jfZPiRnnh85X7CBCS"
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
