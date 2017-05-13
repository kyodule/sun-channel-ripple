package com.ripple.client.websocket;

import org.json.JSONObject;

/**
 * Document Start 
 * WebSocketClient网络连接事件接口
 * Document End 
 * Author: 扶摇直上 songwenpeng@le.com
 * Time: 2016年7月16日 下午10:41:28
 */
public interface ITransportEventHandler {

	void onMessage(JSONObject msg);

	void onConnecting(int attempt);

	void onDisconnected(boolean willReconnect);

	void onError(Exception error);

	void onConnected();
}
