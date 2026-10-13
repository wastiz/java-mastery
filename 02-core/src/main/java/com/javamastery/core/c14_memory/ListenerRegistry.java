package com.javamastery.core.c14_memory;

import java.util.function.Consumer;

/**
 * C14 · Слабые ссылки: реестр слушателей, который не мешает GC собирать слушателей,
 * на которые больше никто не ссылается (классическая утечка — «забыли отписаться»).
 *
 * <p>Используй {@code WeakHashMap} (ключ — слушатель) или {@code List<WeakReference<...>>}.
 * {@code size()} должен возвращать число ЖИВЫХ слушателей.
 */
public class ListenerRegistry<E> {
    // TODO: хранилище со слабыми ссылками

    public void register(Consumer<E> listener) {
        throw new UnsupportedOperationException("TODO");
    }

    public void unregister(Consumer<E> listener) {
        throw new UnsupportedOperationException("TODO");
    }

    public void publish(E event) {
        throw new UnsupportedOperationException("TODO");
    }

    public int size() {
        throw new UnsupportedOperationException("TODO");
    }
}
