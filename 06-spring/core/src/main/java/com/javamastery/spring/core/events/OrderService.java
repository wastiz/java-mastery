package com.javamastery.spring.core.events;

import org.springframework.context.ApplicationEventPublisher;
import org.springframework.stereotype.Service;

import java.util.concurrent.atomic.AtomicLong;

/**
 * S01 · Публикация событий: сервис заказов не знает, кто слушает (слабая связанность).
 * {@code placeOrder} генерирует id (1, 2, 3...), публикует OrderPlacedEvent и возвращает id.
 */
// TODO: аннотация
public class OrderService {

    private final AtomicLong ids = new AtomicLong();

    // TODO: зависимость ApplicationEventPublisher

    public long placeOrder(String customer, long totalCents) {
        throw new UnsupportedOperationException("TODO");
    }
}
