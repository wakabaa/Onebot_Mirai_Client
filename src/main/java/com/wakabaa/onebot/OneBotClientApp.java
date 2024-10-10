package com.wakabaa.onebot;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;
import org.springframework.web.socket.config.annotation.EnableWebSocket;
import org.springframework.web.socket.config.annotation.WebSocketConfigurer;
import org.springframework.web.socket.config.annotation.WebSocketHandlerRegistry;

@SpringBootApplication
@EnableWebSocket
public class OneBotClientApp implements WebSocketConfigurer {
	public static void main(String[] args) {
		SpringApplication.run(OneBotClientApp.class, args);
	}

	@Override
	public void registerWebSocketHandlers(WebSocketHandlerRegistry registry) {
		registry.addHandler(WebSocketHandler(), "/ws/cq/").setAllowedOrigins("*");
	}

	@Bean
	public WebSocketHandler WebSocketHandler() {
		return new WebSocketHandler();
	}
}