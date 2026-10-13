package com.javamastery.basics.b13_nested;

import java.util.ArrayList;
import java.util.List;
import java.util.function.Consumer;

/**
 * B13 · Анонимные классы и лямбды как слушатели событий, effectively final.
 */
public class EventSource {
    private final List<Consumer<String>> listeners = new ArrayList<>();

    public void subscribe(Consumer<String> l) {
        listeners.add(l);
    }

    public void emit(String event) {
        for (Consumer<String> l : listeners) l.accept(event);
    }

    /**
     * Подписать счётчик событий: вернуть объект Counter, у которого count() растёт на 1 при каждом событии,
     * начинающемся с prefix. Реализуй через АНОНИМНЫЙ класс Consumer (не лямбду), который
     * изменяет поле возвращаемого Counter.
     */
    public Counter countEvents(String prefix) {
        throw new UnsupportedOperationException("TODO");
    }

    /** Простой изменяемый счётчик. */
    public static class Counter {
        int n;

        public int count() {
            return n;
        }
    }
}
