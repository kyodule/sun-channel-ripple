package com.ripple.client.websocket;

import java.lang.ref.WeakReference;
import java.net.URI;

import org.java_websocket.client.WebSocketClient;
import org.java_websocket.drafts.Draft_17;
import org.java_websocket.handshake.ServerHandshake;
import org.json.JSONObject;

class WS extends WebSocketClient {

    WeakReference<ITransportEventHandler> h;

    public WS(URI serverURI) {
        super(serverURI, new Draft_17());
    }

    public void muteEventHandler() {
        h.clear();
    }

    public void setEventHandler(ITransportEventHandler eventHandler) {
        h = new WeakReference<ITransportEventHandler>(eventHandler);
    }

    @Override
    public void onOpen(ServerHandshake handshakedata) {
        ITransportEventHandler handler = h.get();
        if (handler != null) {
            handler.onConnected();
        }
    }

    @Override
    public void onMessage(String message) {
        ITransportEventHandler handler = h.get();
        if (handler != null) {
            handler.onMessage(new JSONObject(message));
        }
    }

    @Override
    public void onClose(int code, String reason, boolean remote) {
        ITransportEventHandler handler = h.get();
        if (handler != null) {
            handler.onDisconnected(false);
        }
    }

    @Override
    public void onError(Exception ex) {
        ITransportEventHandler handler = h.get();
        if (handler != null) {
            handler.onError(ex);
        }
    }
}

public class JavaWebSocketTransportImpl implements WebSocketTransport {

    WeakReference<ITransportEventHandler> handler;
    WS client = null;

    @Override
    public void setHandler(ITransportEventHandler events) {
        handler = new WeakReference<ITransportEventHandler>(events);
        if (client != null) {
            client.setEventHandler(events);
        }
    }

    @Override
    public void sendMessage(JSONObject msg) {
        client.send(msg.toString());
    }

    @Override
    public void connect(URI uri) {
        ITransportEventHandler curHandler = handler.get();
        if (curHandler == null) {
            throw new RuntimeException("must call setEventHandler() before connect(...)");
        }
        disconnect();
        client = new WS(uri);

        client.setEventHandler(curHandler);
        curHandler.onConnecting(1);
        client.connect();
    }

    @Override
    public void disconnect() {
        if (client != null) {
            ITransportEventHandler handler = this.handler.get();
            // Before we mute the handler, call disconnect
            if (handler != null) {
                handler.onDisconnected(false);
            }
            client.muteEventHandler();
            client = null;
        }
    }
}
