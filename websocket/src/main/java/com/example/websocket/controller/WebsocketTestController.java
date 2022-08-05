package com.example.websocket.controller;

import com.example.websocket.bean.WebSocket;
import com.example.websocket.bean.WebSocketManager;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import java.io.IOException;
import java.util.List;

@Controller
public class WebsocketTestController {

    @RequestMapping("/")
    public String index(){
        return "test.html";
    }

    @RequestMapping("/ws")
    public String wc(){
        return "ws.html";
    }

    @RequestMapping("/test")
    public String test(@RequestParam(name = "msg") String msg) throws IOException {
        for(WebSocket socket : WebSocketManager.getWebSockets()){
            List<String> unReadMessages = socket.getUnReadMessages();
            unReadMessages.add(msg);

            ObjectMapper objectMapper = new ObjectMapper();
            String json = objectMapper.writeValueAsString(unReadMessages);

            socket.getSession().getBasicRemote().sendText(json);
        }
        return "redirect:/";
    }
}
