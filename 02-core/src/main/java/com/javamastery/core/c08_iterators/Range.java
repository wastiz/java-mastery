package com.javamastery.core.c08_iterators;

import java.util.Iterator;

/**
 * C08 · Свой Iterable: Range.of(0, 10, 3) → 0, 3, 6, 9. Шаг может быть отрицательным: of(5, 0, -2) → 5, 3, 1.
 * Конец не включается. step == 0 → IllegalArgumentException. Каждый вызов iterator() — новый независимый обход.
 * Объект можно использовать в for-each.
 */
public final class Range implements Iterable<Integer> {
    // TODO: поля

    private Range(int from, int to, int step) {
        throw new UnsupportedOperationException("TODO");
    }

    public static Range of(int from, int to) {
        throw new UnsupportedOperationException("TODO");
    }

    public static Range of(int from, int to, int step) {
        throw new UnsupportedOperationException("TODO");
    }

    @Override
    public Iterator<Integer> iterator() {
        throw new UnsupportedOperationException("TODO");
    }
}
