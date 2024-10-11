package com.wakabaa.onebot.handler;

import java.awt.Frame;
import java.util.Map;
import java.util.concurrent.CompletableFuture;
import java.util.concurrent.ConcurrentHashMap;

import org.springframework.stereotype.Component;

@Component
public class ApiSender {
	   private final Map<String, CompletableFuture<Frame>> echoFutureMap = new ConcurrentHashMap<>();

	    public Map<String, CompletableFuture<Frame>> getEchoFutureMap() {
	        return echoFutureMap;
	    }
}
