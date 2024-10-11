package com.wakabaa.onebot.handler;

import java.io.IOException;
import java.nio.ByteBuffer;
import java.util.concurrent.ExecutorService;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.web.socket.BinaryMessage;
import org.springframework.web.socket.CloseStatus;
import org.springframework.web.socket.TextMessage;
import org.springframework.web.socket.WebSocketSession;
import org.springframework.web.socket.handler.ConcurrentWebSocketSessionDecorator;
import org.springframework.web.socket.handler.TextWebSocketHandler;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.wakabaa.onebot.entity.OneBotMessage;
import com.wakabaa.onebot.factory.BotContainer;
import com.wakabaa.onebot.factory.BotFactory;

@Component
public class WebSocketHandler extends TextWebSocketHandler {

	private ObjectMapper objectMapper = new ObjectMapper();

	@Autowired
	private BotFactory botFactory; // Bot工厂
	@Autowired
	private BotContainer botContainer; // Bot容器

	@Override
	public void afterConnectionEstablished(WebSocketSession session) throws Exception {
		// 当建立连接后，可以在这里做一些初始化操作
		String xSelfIdHeader = session.getHandshakeHeaders().get("x-self-id") != null
				? session.getHandshakeHeaders().get("x-self-id").get(0)
				: null;
		long xSelfId = (xSelfIdHeader != null) ? Long.parseLong(xSelfIdHeader) : 0L;
		if (xSelfId == 0L) {
			try {
				session.close(CloseStatus.NORMAL);
			} catch (IOException e) {
				e.printStackTrace();
			}
			return;
		}
		System.out.println(xSelfId + "  connected");
		botContainer.getBots().put(xSelfId,
				botFactory.createBot(xSelfId, new ConcurrentWebSocketSessionDecorator(session, 3000, 40960)));
	}

	@Override
	protected void handleTextMessage(WebSocketSession session, TextMessage message) throws Exception {
		// 处理接收到的文本消息
		String payload = message.getPayload();
		System.out.println("Received message: " + payload);

		// 可以选择向客户端发送消息
		session.sendMessage(new TextMessage("Echo: " + payload));
	}

	// 处理二进制消息
	@Override
	protected void handleBinaryMessage(WebSocketSession session, BinaryMessage message) {
		String xSelfIdHeader = session.getHandshakeHeaders().get("x-self-id") != null
				? session.getHandshakeHeaders().get("x-self-id").get(0)
				: null;
		long xSelfId = (xSelfIdHeader != null) ? Long.parseLong(xSelfIdHeader) : 0L;
		if (xSelfId == 0L) {
			try {
				session.close(CloseStatus.NORMAL);
			} catch (IOException e) {
				e.printStackTrace();
			}
			return;
		}

		ByteBuffer byteBuffer = message.getPayload();
		byte[] bytes = new byte[byteBuffer.remaining()];
		byteBuffer.get(bytes);
		String jsonMessage = new String(bytes);
		try {
			OneBotMessage oneBotMessage = objectMapper.readValue(jsonMessage, OneBotMessage.class);
			if ("message".equals(oneBotMessage.getPost_type())) {
				System.out.println("Bot(" + xSelfId + ") Group(" + oneBotMessage.getGroup_id() + ") Sender(" + xSelfId
						+ ") -> " + oneBotMessage.getRaw_message());
			}
		} catch (Exception e) {
			System.out.println("Received binary message: " + message.getPayloadLength() + " bytes");
		}
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
