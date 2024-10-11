package com.wakabaa.onebot.config;

import org.springframework.context.annotation.Configuration;
import org.springframework.web.socket.config.annotation.WebSocketConfigurer;
import org.springframework.web.socket.config.annotation.WebSocketHandlerRegistry;

import com.wakabaa.onebot.handler.WebSocketHandler;

@Configuration
public class WebSocketConfig implements WebSocketConfigurer {
	@Override
	public void registerWebSocketHandlers(WebSocketHandlerRegistry registry) {
		// 注册 WebSocketHandler，并设置要监听的 URL
		registry.addHandler(new WebSocketHandler(), "/ws/cq").setAllowedOrigins("*");
	}
}
