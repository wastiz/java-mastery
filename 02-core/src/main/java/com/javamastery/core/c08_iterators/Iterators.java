package com.javamastery.core.c08_iterators;

import java.util.Iterator;
import java.util.NoSuchElementException;
import java.util.function.Predicate;

/**
 * C08 · Ленивые итераторы-обёртки. Ничего не вычисляй заранее — только по запросу next()/hasNext().
 */
public final class Iterators {
    private Iterators() {}

    /** Итератор с подглядыванием (LeetCode 284): peek() возвращает следующий элемент, не сдвигаясь. */
    public interface PeekingIterator<T> extends Iterator<T> {
        T peek();
    }

    public static <T> PeekingIterator<T> peeking(Iterator<T> source) {
        throw new UnsupportedOperationException("TODO");
    }

    /** «Сплющить» итератор итераторов (пустые внутренние итераторы пропускаются). */
    public static <T> Iterator<T> flatten(Iterator<? extends Iterator<? extends T>> its) {
        throw new UnsupportedOperationException("TODO");
    }

    /** Ленивый фильтр. */
    public static <T> Iterator<T> filter(Iterator<T> source, Predicate<? super T> p) {
        throw new UnsupportedOperationException("TODO");
    }

    /** Бесконечный итератор чисел Фибоначчи: 0, 1, 1, 2, 3, 5, ... (long). */
    public static Iterator<Long> fibonacci() {
        throw new UnsupportedOperationException("TODO");
    }

    /** Не более n элементов. */
    public static <T> Iterator<T> limit(Iterator<T> source, int n) {
        throw new UnsupportedOperationException("TODO");
    }
}
