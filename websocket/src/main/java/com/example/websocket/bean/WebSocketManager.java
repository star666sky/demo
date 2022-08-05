package com.example.websocket.bean;

import java.util.concurrent.CopyOnWriteArraySet;

public class WebSocketManager {

    private static CopyOnWriteArraySet<WebSocket> webSockets = new CopyOnWriteArraySet<>();

    public static CopyOnWriteArraySet<WebSocket> getWebSockets() {
        return webSockets;
    }
}
