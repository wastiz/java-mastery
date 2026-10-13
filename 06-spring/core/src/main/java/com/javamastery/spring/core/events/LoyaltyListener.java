package com.javamastery.spring.core.events;

import org.springframework.context.event.EventListener;
import org.springframework.stereotype.Component;

import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;

/**
 * S01 · Слушатель: за каждые полные 10 евро (1000 центов) заказа клиент получает 1 балл.
 */
// TODO: аннотация
public class LoyaltyListener {

    private final Map<String, Long> points = new ConcurrentHashMap<>();

    // TODO: метод-слушатель события

    public long pointsOf(String customer) {
        return points.getOrDefault(customer, 0L);
    }
}
