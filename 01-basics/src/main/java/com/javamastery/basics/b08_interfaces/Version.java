package com.javamastery.basics.b08_interfaces;

/**
 * B08 · Comparable: семантические версии "MAJOR.MINOR.PATCH" (например "1.10.2").
 *
 * <p>parse("1.2") тоже допустимо — недостающие части равны 0. Неверный формат → IllegalArgumentException.
 * Сравнение — численное по частям: 1.10.0 &gt; 1.9.9.
 * toString() → всегда три части: "1.2.0".
 * equals/hashCode согласованы с compareTo.
 */
public final class Version implements Comparable<Version> {
    // TODO: поля

    public Version(int major, int minor, int patch) {
        throw new UnsupportedOperationException("TODO");
    }

    public static Version parse(String s) {
        throw new UnsupportedOperationException("TODO");
    }

    @Override
    public int compareTo(Version o) {
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
        throw new UnsupportedOperationException("TODO");
    }
}
