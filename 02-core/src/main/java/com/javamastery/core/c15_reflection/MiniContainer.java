package com.javamastery.core.c15_reflection;

import java.lang.annotation.*;

/**
 * C15 · Мини DI-контейнер — чтобы понять, что делает Spring «под капотом».
 *
 * <ul>
 *   <li>{@code register(Class)} — зарегистрировать класс-реализацию;</li>
 *   <li>{@code get(Class type)} — вернуть экземпляр: если type — интерфейс или абстрактный класс, найти
 *       ЕДИНСТВЕННУЮ зарегистрированную реализацию (0 или 2+ → IllegalStateException);</li>
 *   <li>создание через конструктор: если конструкторов несколько — взять помеченный @Inject,
 *       если он один — взять его; зависимости-параметры конструктора разрешать рекурсивно;</li>
 *   <li>все бины — синглтоны (кэшировать), кроме классов с аннотацией @Prototype — для них каждый раз новый объект;</li>
 *   <li>после создания вызвать методы с @PostConstruct (без параметров);</li>
 *   <li>циклическая зависимость (A → B → A) → IllegalStateException, сообщение должно содержать "Circular".</li>
 * </ul>
 */
public class MiniContainer {

    @Retention(RetentionPolicy.RUNTIME) @Target(ElementType.CONSTRUCTOR)
    public @interface Inject {}

    @Retention(RetentionPolicy.RUNTIME) @Target(ElementType.TYPE)
    public @interface Prototype {}

    @Retention(RetentionPolicy.RUNTIME) @Target(ElementType.METHOD)
    public @interface PostConstruct {}

    // TODO: поля

    public MiniContainer register(Class<?> implementation) {
        throw new UnsupportedOperationException("TODO");
    }

    public <T> T get(Class<T> type) {
        throw new UnsupportedOperationException("TODO");
    }

    // TODO: вспомогательные методы
}
