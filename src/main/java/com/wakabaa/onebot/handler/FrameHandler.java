package com.wakabaa.onebot.handler;

import java.awt.Frame;
import java.util.concurrent.CompletableFuture;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.wakabaa.onebot.entity.Bot;
import com.wakabaa.onebot.factory.BotContainer;

@Component
public class FrameHandler {

	@Autowired
	private EventHandler eventHandler;

	@Autowired
	private ApiSender apiSender;

	@Autowired
	private BotContainer botContainer;

	public void handleFrame(Frame frame) {
		Long botId = frame.getBotId();
		Bot bot = botContainer.getBots().get(botId);

		if (bot == null) {
			// 如果 bot 不存在，直接返回
			return;
		}

		// 根据 frameType 进行不同的事件处理
		switch (frame.getFrameType()) {
		case TPrivateMessageEvent:
			eventHandler.handlePrivateMessageEvent(bot, frame.getPrivateMessageEvent());
			break;
		case TGroupMessageEvent:
			eventHandler.handleGroupMessageEvent(bot, frame.getGroupMessageEvent());
			break;
		case TGroupUploadNoticeEvent:
			eventHandler.handleGroupUploadNoticeEvent(bot, frame.getGroupUploadNoticeEvent());
			break;
		case TGroupAdminNoticeEvent:
			eventHandler.handleGroupAdminNoticeEvent(bot, frame.getGroupAdminNoticeEvent());
			break;
		case TGroupDecreaseNoticeEvent:
			eventHandler.handleGroupDecreaseNoticeEvent(bot, frame.getGroupDecreaseNoticeEvent());
			break;
		case TGroupIncreaseNoticeEvent:
			eventHandler.handleGroupIncreaseNoticeEvent(bot, frame.getGroupIncreaseNoticeEvent());
			break;
		case TGroupBanNoticeEvent:
			eventHandler.handleGroupBanNoticeEvent(bot, frame.getGroupBanNoticeEvent());
			break;
		case TFriendAddNoticeEvent:
			eventHandler.handleFriendAddNoticeEvent(bot, frame.getFriendAddNoticeEvent());
			break;
		case TGroupRecallNoticeEvent:
			eventHandler.handleGroupRecallNoticeEvent(bot, frame.getGroupRecallNoticeEvent());
			break;
		case TFriendRecallNoticeEvent:
			eventHandler.handleFriendRecallNoticeEvent(bot, frame.getFriendRecallNoticeEvent());
			break;
		case TFriendRequestEvent:
			eventHandler.handleFriendRequestEvent(bot, frame.getFriendRequestEvent());
			break;
		case TGroupRequestEvent:
			eventHandler.handleGroupRequestEvent(bot, frame.getGroupRequestEvent());
			break;
		case TGroupTempMessageEvent:
			eventHandler.handleGroupTempMessageEvent(bot, frame.getGroupTempMessageEvent());
			break;

		// 如果是 API 调用的响应结果
		default:
			CompletableFuture<Frame> future = apiSender.getEchoFutureMap().get(frame.getEcho());
			if (future != null) {
				future.complete(frame); // 完成异步 API 响应
			}
			break;
		}
	}
}
