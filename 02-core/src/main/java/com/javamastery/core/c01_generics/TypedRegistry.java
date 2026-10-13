package com.javamastery.core.c01_generics;

/**
 * C01 · Типобезопасный гетерогенный контейнер (Effective Java, Item 33).
 *
 * <p>Хранит по одному значению на каждый тип: registry.put(String.class, "hi"); registry.get(String.class) → "hi".
 * Никаких unchecked-кастов в get — используй {@code Class.cast}.
 * put с null-классом → NullPointerException. put значения не того типа через raw-тип
 * ({@code ((Class) Integer.class)} и строка) → ClassCastException прямо в put.
 */
public class TypedRegistry {
    // TODO: хранилище

    public <T> void put(Class<T> type, T value) {
        throw new UnsupportedOperationException("TODO");
    }

    public <T> T get(Class<T> type) {
        throw new UnsupportedOperationException("TODO");
    }

    public boolean contains(Class<?> type) {
        throw new UnsupportedOperationException("TODO");
    }

    public int size() {
        throw new UnsupportedOperationException("TODO");
    }
}
