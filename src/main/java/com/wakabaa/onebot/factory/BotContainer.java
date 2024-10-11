package com.wakabaa.onebot.factory;

import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;

import org.springframework.stereotype.Component;

import com.wakabaa.onebot.entity.Bot;

@Component
public class BotContainer {
    private Map<Long, Bot> bots = new ConcurrentHashMap<>();

    public Map<Long, Bot> getBots() {
        return bots;
    }
}
