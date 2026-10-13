package com.javamastery.spring.core.events;

/** Событие предметной области. Готово. */
public record OrderPlacedEvent(long orderId, String customer, long totalCents) {}
