package com.javamastery.core.c01_generics;

import java.util.*;
import java.util.function.Function;
import java.util.function.Predicate;

/**
 * C01 · Обобщённые методы, ограниченные типы (bounded types), wildcards, PECS.
 *
 * <p>Главное правило: <b>P</b>roducer <b>E</b>xtends, <b>C</b>onsumer <b>S</b>uper.
 * Сигнатуры уже даны — изучи их, это и есть половина задачи.
 */
public final class GenericAlgorithms {
    private GenericAlgorithms() {}

    /**
     * Максимум коллекции. Пустая коллекция → NoSuchElementException.
     * Почему {@code T extends Comparable<? super T>}, а не просто {@code Comparable<T>}?
     * (Подсказка: java.sql.Timestamp / наследник класса, реализующего Comparable базового класса.)
     */
    public static <T extends Comparable<? super T>> T max(Collection<? extends T> items) {
        throw new UnsupportedOperationException("TODO");
    }

    /** Скопировать все элементы из src в dst (в конец). Классический пример PECS из Collections.copy. */
    public static <T> void copyAll(Collection<? extends T> src, Collection<? super T> dst) {
        throw new UnsupportedOperationException("TODO");
    }

    /** Оставить элементы, подходящие под предикат. Предикат может принимать супертип элемента. */
    public static <T> List<T> filter(Collection<? extends T> items, Predicate<? super T> p) {
        throw new UnsupportedOperationException("TODO");
    }

    /** Сгруппировать по ключу, сохраняя порядок первого появления ключей (LinkedHashMap). Без Stream API! */
    public static <T, K> Map<K, List<T>> groupBy(Collection<? extends T> items, Function<? super T, ? extends K> key) {
        throw new UnsupportedOperationException("TODO");
    }

    /** Сумма любых чисел (Integer, Long, Double, BigDecimal...) как double. */
    public static double sum(Collection<? extends Number> numbers) {
        throw new UnsupportedOperationException("TODO");
    }

    /** Поменять местами два элемента списка неизвестного типа (List&lt;?&gt;). Подсказка: вспомогательный метод с захватом типа (wildcard capture). */
    public static void swap(List<?> list, int i, int j) {
        throw new UnsupportedOperationException("TODO");
    }

    // TODO: вспомогательный метод (wildcard capture)
}
