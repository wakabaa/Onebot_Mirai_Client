package com.wakabaa.onebot;

import org.springframework.web.socket.CloseStatus;
import org.springframework.web.socket.TextMessage;
import org.springframework.web.socket.WebSocketSession;
import org.springframework.web.socket.handler.TextWebSocketHandler;

import com.fasterxml.jackson.databind.ObjectMapper;

public class WebSocketHandler extends TextWebSocketHandler {
	
	private ObjectMapper objectMapper = new ObjectMapper();

	 @Override
	    public void afterConnectionEstablished(WebSocketSession session) throws Exception {
	        // 当建立连接后，可以在这里做一些初始化操作
	        System.out.println("New connection established: " + session.getId());
	    }

	    @Override
	    protected void handleTextMessage(WebSocketSession session, TextMessage message) throws Exception {
	        // 处理接收到的文本消息
	        String payload = message.getPayload();
	        System.out.println("Received message: " + payload);
	        
	        // 可以选择向客户端发送消息
	        session.sendMessage(new TextMessage("Echo: " + payload));
	    }

	    @Override
	    public void afterConnectionClosed(WebSocketSession session, CloseStatus status) throws Exception {
	        // 当连接关闭时，可以在这里做一些清理工作
	        System.out.println("Connection closed: " + session.getId() + ", status: " + status);
	    }

	    @Override
	    public void handleTransportError(WebSocketSession session, Throwable exception) throws Exception {
	        // 处理传输错误
	        System.err.println("Error in WebSocket connection: " + exception.getMessage());
	    }
}
