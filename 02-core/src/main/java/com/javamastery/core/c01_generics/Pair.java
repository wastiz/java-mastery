package com.javamastery.core.c01_generics;

import java.util.function.Function;

/**
 * C01 · Обобщённый неизменяемый класс с двумя параметрами типа.
 */
public final class Pair<A, B> {
    private final A first;
    private final B second;

    private Pair(A first, B second) {
        this.first = first;
        this.second = second;
    }

    /** Фабрика — компилятор сам выведет типы: Pair.of("a", 1) → Pair&lt;String, Integer&gt;. */
    public static <A, B> Pair<A, B> of(A first, B second) {
        throw new UnsupportedOperationException("TODO");
    }

    public A first() { return first; }

    public B second() { return second; }

    /** Поменять местами: Pair&lt;A,B&gt; → Pair&lt;B,A&gt;. */
    public Pair<B, A> swap() {
        throw new UnsupportedOperationException("TODO");
    }

    /** Преобразовать первый элемент. Обрати внимание на сигнатуру с wildcard'ами. */
    public <C> Pair<C, B> mapFirst(Function<? super A, ? extends C> f) {
        throw new UnsupportedOperationException("TODO");
    }

    public <C> Pair<A, C> mapSecond(Function<? super B, ? extends C> f) {
        throw new UnsupportedOperationException("TODO");
    }

    @Override
    public boolean equals(Object o) {
        throw new UnsupportedOperationException("TODO");
    }

    @Override
    public int hashCode() {
        throw new UnsupportedOperationException("TODO");
    }

    @Override
    public String toString() {
        return "(" + first + ", " + second + ")";
    }
}
