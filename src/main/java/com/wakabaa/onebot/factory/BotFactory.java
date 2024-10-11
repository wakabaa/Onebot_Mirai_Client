package com.wakabaa.onebot.factory;

import org.springframework.stereotype.Component;
import org.springframework.web.socket.handler.ConcurrentWebSocketSessionDecorator;

import com.wakabaa.onebot.entity.Bot;

@Component
public class BotFactory {
	public Bot createBot(Long selfId, ConcurrentWebSocketSessionDecorator sessionDecorator) {
		return new Bot(selfId, sessionDecorator);
	}
}
