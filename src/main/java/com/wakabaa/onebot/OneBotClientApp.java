package com.wakabaa.onebot;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.web.socket.config.annotation.EnableWebSocket;
import org.springframework.web.socket.config.annotation.WebSocketConfigurer;
import org.springframework.web.socket.config.annotation.WebSocketHandlerRegistry;

import com.wakabaa.onebot.handler.WebSocketHandler;

@SpringBootApplication
@ComponentScan(basePackages = {"com.wakabaa.onebot"})
@EnableWebSocket
public class OneBotClientApp implements WebSocketConfigurer {
	public static void main(String[] args) {
		SpringApplication.run(OneBotClientApp.class, args);
	}

	@Override
	public void registerWebSocketHandlers(WebSocketHandlerRegistry registry) {
		// 注册 WebSocket 处理器，并设置监听的路径
		registry.addHandler(myWebSocketHandler(), "/ws/cq/")
		        .setAllowedOrigins("*");
	}

	// 使用 Bean 注解来管理 WebSocketHandler 实例
	@Bean
	public WebSocketHandler myWebSocketHandler() {
		return new WebSocketHandler();
	}
}