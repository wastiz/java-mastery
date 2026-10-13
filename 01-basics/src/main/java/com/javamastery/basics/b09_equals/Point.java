package com.javamastery.basics.b09_equals;

/**
 * B09 · equals/hashCode — самый частый вопрос на собесе по Java Core.
 *
 * <p>Точка с целыми координатами. Реализуй equals, hashCode и toString ("Point(1, 2)") ВРУЧНУЮ
 * (без records и без Objects.hash — чтобы понять, что там происходит).
 * Контракт: рефлексивность, симметричность, транзитивность, consistency, x.equals(null) == false.
 */
public final class Point {
    private final int x;
    private final int y;

    public Point(int x, int y) {
        this.x = x;
        this.y = y;
    }

    public int x() { return x; }
    public int y() { return y; }

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
        throw new UnsupportedOperationException("TODO");
    }
}
