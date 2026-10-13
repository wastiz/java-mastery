package com.javamastery.basics.b11_records;

/**
 * B11 · Record с компактным конструктором (валидация) и собственными методами.
 *
 * <p>Полуоткрытый интервал целых чисел [from, to). from &gt; to → IllegalArgumentException.
 */
public record Range(int from, int to) {

    public Range {
        throw new UnsupportedOperationException("TODO: компактный конструктор: только проверка");
    }

    public static Range of(int from, int to) {
        throw new UnsupportedOperationException("TODO");
    }

    public int length() {
        throw new UnsupportedOperationException("TODO");
    }

    public boolean isEmpty() {
        throw new UnsupportedOperationException("TODO");
    }

    public boolean contains(int x) {
        throw new UnsupportedOperationException("TODO");
    }

    public boolean overlaps(Range other) {
        throw new UnsupportedOperationException("TODO");
    }

    /** Пересечение двух интервалов; если не пересекаются — пустой Range(0, 0). */
    public Range intersect(Range other) {
        throw new UnsupportedOperationException("TODO");
    }
}
