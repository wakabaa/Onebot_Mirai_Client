package com.wakabaa.onebot.entity;

import java.util.List;

public class OneBotMessage {
	private long time;
	private long self_id;
	private String post_type;
	private String message_type;
	private String sub_type;
	private long message_id;
	private long group_id;
	private long user_id;
	private List<Message> message;
	private String raw_message;
	private Sender sender;

	public long getTime() {
		return time;
	}

	public void setTime(long time) {
		this.time = time;
	}

	public long getSelf_id() {
		return self_id;
	}

	public void setSelf_id(long self_id) {
		this.self_id = self_id;
	}

	public String getPost_type() {
		return post_type;
	}

	public void setPost_type(String post_type) {
		this.post_type = post_type;
	}

	public String getMessage_type() {
		return message_type;
	}

	public void setMessage_type(String message_type) {
		this.message_type = message_type;
	}

	public String getSub_type() {
		return sub_type;
	}

	public void setSub_type(String sub_type) {
		this.sub_type = sub_type;
	}

	public long getMessage_id() {
		return message_id;
	}

	public void setMessage_id(long message_id) {
		this.message_id = message_id;
	}

	public long getGroup_id() {
		return group_id;
	}

	public void setGroup_id(long group_id) {
		this.group_id = group_id;
	}

	public long getUser_id() {
		return user_id;
	}

	public void setUser_id(long user_id) {
		this.user_id = user_id;
	}

	public List<Message> getMessage() {
		return message;
	}

	public void setMessage(List<Message> message) {
		this.message = message;
	}

	public String getRaw_message() {
		return raw_message;
	}

	public void setRaw_message(String raw_message) {
		this.raw_message = raw_message;
	}

	public Sender getSender() {
		return sender;
	}

	public void setSender(Sender sender) {
		this.sender = sender;
	}
}
