package com.javamastery.spring.core.lifecycle;

import jakarta.annotation.PostConstruct;
import jakarta.annotation.PreDestroy;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.List;

/**
 * S01 · Жизненный цикл бина.
 *
 * <p>Порядок событий записывается в {@link #EVENTS} (static — чтобы тест увидел их и после закрытия контекста):
 * "constructor" → "postConstruct" → (работа) → "preDestroy".
 * После @PostConstruct {@code isWarm()} == true, а в кэше лежат ключи "a", "b", "c".
 */
// TODO: аннотация
public class CacheWarmer {

    public static final List<String> EVENTS = new ArrayList<>();

    private final List<String> cache = new ArrayList<>();
    private boolean warm;

    public CacheWarmer() {
        EVENTS.add("constructor");
    }

    // TODO: метод инициализации

    // TODO: метод уничтожения

    public boolean isWarm() {
        return warm;
    }

    public List<String> cache() {
        return List.copyOf(cache);
    }
}
