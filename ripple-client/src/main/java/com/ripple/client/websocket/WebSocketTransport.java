package com.ripple.client.websocket;

import org.json.JSONObject;

import java.net.URI;

/**
 * Document Start 
 * WebSocket协议
 * Document End 
 * Author: 扶摇直上 songwenpeng@le.com
 * Time: 2016年7月16日 下午10:37:32
 */
public interface WebSocketTransport {
    public abstract void setHandler(ITransportEventHandler events);
    public abstract void sendMessage(JSONObject msg);
    public abstract void connect(URI url);
    /**
     * It's the responsibility of implementations to trigger
     * {@link com.ripple.client.websocket.ITransportEventHandler#onDisconnected}
     */
    public abstract void disconnect();
}
