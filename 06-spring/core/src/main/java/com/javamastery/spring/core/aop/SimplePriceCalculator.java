package com.javamastery.spring.core.aop;

import org.springframework.stereotype.Component;

/** Бин с @Timed. Готово (кроме аннотации компонента). */
// TODO: аннотация
@Timed
public class SimplePriceCalculator implements PriceCalculator {
    @Override
    public long price(String sku, int qty) {
        return 250L * qty;
    }
}
