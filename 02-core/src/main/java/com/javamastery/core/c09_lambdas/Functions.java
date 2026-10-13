package com.javamastery.core.c09_lambdas;

import java.util.function.*;

/**
 * C09 · Лямбды, функциональные интерфейсы, замыкания, method references.
 */
public final class Functions {
    private Functions() {}

    /** Композиция произвольного числа функций слева направо: pipeline(f, g, h).apply(x) == h(g(f(x))). Пусто → identity. */
    @SafeVarargs
    public static <T> UnaryOperator<T> pipeline(UnaryOperator<T>... fs) {
        throw new UnsupportedOperationException("TODO");
    }

    /**
     * Мемоизация: вернуть функцию, которая для одного и того же аргумента вызывает f только один раз.
     * Должно работать и для результата null.
     */
    public static <T, R> Function<T, R> memoize(Function<T, R> f) {
        throw new UnsupportedOperationException("TODO");
    }

    /** Каррирование: f(a, b) → a -> (b -> f(a, b)). */
    public static <A, B, R> Function<A, Function<B, R>> curry(BiFunction<A, B, R> f) {
        throw new UnsupportedOperationException("TODO");
    }

    /** Обратное каррированию. */
    public static <A, B, R> BiFunction<A, B, R> uncurry(Function<A, Function<B, R>> f) {
        throw new UnsupportedOperationException("TODO");
    }

    /** Ленивое значение: supplier вызывается при первом get() и только один раз. */
    public static <T> Supplier<T> lazy(Supplier<T> supplier) {
        throw new UnsupportedOperationException("TODO");
    }

    /** Генератор id: каждый вызов возвращает prefix-1, prefix-2, ... Счётчик — в замыкании (лямбда не может менять локальную int — почему?). */
    public static Supplier<String> idGenerator(String prefix) {
        throw new UnsupportedOperationException("TODO");
    }

    /** Предикат «все сразу»: true, если все предикаты true (пусто → true). Используй Predicate::and. */
    @SafeVarargs
    public static <T> Predicate<T> allOf(Predicate<? super T>... ps) {
        throw new UnsupportedOperationException("TODO");
    }

    /** Свой функциональный интерфейс с тремя аргументами. Уже объявлен — используй его в applyTwice ниже. */
    @FunctionalInterface
    public interface TriFunction<A, B, C, R> {
        R apply(A a, B b, C c);

        default <V> TriFunction<A, B, C, V> andThen(Function<? super R, ? extends V> after) {
            throw new UnsupportedOperationException("TODO");
        }
    }

    /** Ссылки на методы: верни 4 функции, используя ТОЛЬКО method references (::). */
    public static Function<String, Integer> parseInt() {
        throw new UnsupportedOperationException("TODO: static method reference");
    }

    public static Function<String, String> toUpper() {
        throw new UnsupportedOperationException("TODO: unbound instance method reference");
    }

    public static Predicate<String> equalsIgnoringCase(String target) {
        throw new UnsupportedOperationException("TODO: bound instance method reference (метод вызывается у конкретного объекта target)");
    }

    public static Supplier<java.util.List<String>> listFactory() {
        throw new UnsupportedOperationException("TODO: constructor reference");
    }
}
