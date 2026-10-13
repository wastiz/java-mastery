package com.javamastery.spring.core.aop;

/** Интерфейс бина, который будем оборачивать в прокси. Готово. */
public interface PriceCalculator {
    long price(String sku, int qty);
}
