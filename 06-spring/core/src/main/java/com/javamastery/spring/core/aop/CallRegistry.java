package com.javamastery.spring.core.aop;

import org.springframework.stereotype.Component;

import java.util.List;
import java.util.concurrent.CopyOnWriteArrayList;

/** Сюда прокси записывает вызовы: "PriceCalculator.price". Готово (кроме аннотации). */
// TODO: аннотация
public class CallRegistry {
    private final List<String> calls = new CopyOnWriteArrayList<>();

    public void record(String call) {
        calls.add(call);
    }

    public List<String> calls() {
        return List.copyOf(calls);
    }
}
