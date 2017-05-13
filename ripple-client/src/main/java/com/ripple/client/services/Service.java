package com.ripple.client.services;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.json.JSONObject;

import com.le.bc.constants.Constants;
import com.ripple.client.Client;
import com.ripple.client.websocket.JavaWebSocketTransportImpl;

/**
 * Document Start
 * RippleAPIS服务主类
 * Document End
 * songwenpeng@letv.com
 * 乐视控股（北京）有限公司
 * 2016年7月8日 上午12:58:32
 */
public class Service {
	public Logger logger = LogManager.getLogger(Service.class);
	//创建区块链链接客户端
	private Client client;
	//成功调用返回的数据
	public JSONObject result = null;
	
	public Service() {
		this.client = new Client(new JavaWebSocketTransportImpl());
		this.client.connect(Constants.GLOBAL_RIPPLE_CHAIN_URL);
	}
	
	public Service(String url) {
		this.client = new Client(new JavaWebSocketTransportImpl());
		this.client.connect(url);
	}

	public Client getClient() {
		return client;
	}

	public void setClient(Client client) {
		this.client = client;
	}

	/**
	 * Document Start 
	 * 成功调用服务后
	 * 对结果进行处理做
	 * 成功调用后的触发事件
	 * Document End 
	 * Author: 扶摇直上 songwenpeng@le.com
	 * Time: 2016年7月17日 上午12:06:45
	 * @param result
	 */
	public void doSuccess(JSONObject result) {
		this.result = result;
		System.out.println(">>>>>>>>>>>>>>>" + result.toString());
	}
}
