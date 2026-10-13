package com.javamastery.core.c16_patterns;

import java.util.function.Consumer;

/**
 * C16 · Observer / Publish-Subscribe с типизированными событиями.
 *
 * <ul>
 *   <li>subscribe(Type.class, handler) — подписка на события типа Type И ЕГО ПОДТИПОВ;</li>
 *   <li>publish(event) — доставить всем подходящим подписчикам в порядке подписки;</li>
 *   <li>subscribe возвращает Subscription — вызов cancel() отписывает;</li>
 *   <li>исключение в одном обработчике не должно мешать остальным; такие исключения собираются и доступны через errors();</li>
 *   <li>publish возвращает количество обработчиков, получивших событие.</li>
 * </ul>
 */
public class EventBus {

    @FunctionalInterface
    public interface Subscription {
        void cancel();
    }

    // TODO: хранилище подписчиков

    @SuppressWarnings("unchecked")
    public <E> Subscription subscribe(Class<E> type, Consumer<? super E> handler) {
        throw new UnsupportedOperationException("TODO");
    }

    public int publish(Object event) {
        throw new UnsupportedOperationException("TODO");
    }

    public java.util.List<Throwable> errors() {
        throw new UnsupportedOperationException("TODO");
    }
}
