package com.wakabaa.onebot;

import jakarta.websocket.OnOpen;
import jakarta.websocket.server.ServerEndpoint;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.stereotype.Component;
import org.springframework.web.socket.server.standard.ServerEndpointExporter;

@ServerEndpoint(value="/api/websocket/{user_id}")
@Component
public class WebSocketServer {
    @OnOpen
    public void onOpen(){

  }
}
