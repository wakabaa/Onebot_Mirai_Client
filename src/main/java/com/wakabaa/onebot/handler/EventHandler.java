package com.wakabaa.onebot.handler;

import org.springframework.stereotype.Component;

import com.wakabaa.onebot.entity.Bot;

@Component
public class EventHandler {

	public void handlePrivateMessageEvent(Bot bot, PrivateMessageEvent event) {
		// 处理私聊消息事件的逻辑
	}

	public void handleGroupMessageEvent(Bot bot, GroupMessageEvent event) {
		// 处理群组消息事件的逻辑
	}
}
