package com.wakabaa.onebot.entity;

import org.springframework.web.socket.handler.ConcurrentWebSocketSessionDecorator;

public class Bot {
	private Long selfId;
	private ConcurrentWebSocketSessionDecorator sessionDecorator;

	public Bot(Long selfId, ConcurrentWebSocketSessionDecorator sessionDecorator) {
		this.selfId = selfId;
		this.sessionDecorator = sessionDecorator;
	}

	public Long getSelfId() {
		return selfId;
	}

	public void setSelfId(Long selfId) {
		this.selfId = selfId;
	}

	public ConcurrentWebSocketSessionDecorator getSessionDecorator() {
		return sessionDecorator;
	}

	public void setSessionDecorator(ConcurrentWebSocketSessionDecorator sessionDecorator) {
		this.sessionDecorator = sessionDecorator;
	}
	
	
}
