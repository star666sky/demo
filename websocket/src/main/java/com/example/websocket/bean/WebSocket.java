package com.example.websocket.bean;

import org.springframework.stereotype.Component;

import javax.websocket.*;
import javax.websocket.server.ServerEndpoint;
import java.util.ArrayList;
import java.util.List;

@ServerEndpoint("/ws")
@Component
public class WebSocket {

    private List<String> unReadMessages = new ArrayList<>();

    private Session session;

    @OnOpen
    public void onOpen(Session session){
        this.session = session;
        WebSocketManager.getWebSockets().add(this);
        System.out.println("webSocket连接打开了，当前连接数：" + WebSocketManager.getWebSockets().size());
    }

    @OnClose
    public void onClose(){
        WebSocketManager.getWebSockets().remove(this);
        System.out.println("webSocket连接关闭了，当前连接数：" + WebSocketManager.getWebSockets().size());
    }

    @OnError
    public void onError(Throwable t){
        System.out.println("webSocket连接出错了，出错原因：" + t.getMessage());
    }

    @OnMessage
    public void onMessage(String text){
        System.out.println("webSocket连接收到消息了，消息是：" + text);
    }

    public List<String> getUnReadMessages() {
        return unReadMessages;
    }

    public Session getSession() {
        return session;
    }

}
