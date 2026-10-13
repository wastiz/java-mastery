package com.javamastery.basics.b02_primitives;

/**
 * B02 · Примитивы, обёртки, битовые операции, переполнение.
 */
public final class Primitives {
    private Primitives() {}

    /**
     * Сложить два int. Если результат переполняет int — бросить {@link ArithmeticException}.
     * Нельзя использовать {@code Math.addExact} и {@code long} — только битовая магия или сравнения.
     */
    public static int safeAdd(int a, int b) {
        throw new UnsupportedOperationException("TODO");
    }

    /** Является ли n степенью двойки (1, 2, 4, 8...). Без циклов, одним выражением с битами. */
    public static boolean isPowerOfTwo(long n) {
        throw new UnsupportedOperationException("TODO");
    }

    /** Количество единичных битов в числе (как {@code Integer.bitCount}, но без него). Работает и для отрицательных. */
    public static int countBits(int n) {
        throw new UnsupportedOperationException("TODO");
    }

    /**
     * Двоичное представление неотрицательного числа без ведущих нулей ("0" для нуля).
     * Без {@code Integer.toBinaryString}. Отрицательное → {@link IllegalArgumentException}.
     */
    public static String toBinary(int n) {
        throw new UnsupportedOperationException("TODO");
    }

    /**
     * Ловушка собеседований: сравни две обёртки ПРАВИЛЬНО.
     * Метод должен возвращать true, если значения равны (в том числе оба null), иначе false.
     * Подсказка: почему {@code a == b} иногда работает (для -128..127), а иногда нет?
     */
    public static boolean sameValue(Integer a, Integer b) {
        throw new UnsupportedOperationException("TODO");
    }

    /**
     * Перевести температуру из Фаренгейта в Цельсий и округлить до одного знака после запятой
     * (half-up: 2.25 → 2.3). Формула: (f - 32) * 5 / 9.
     * Осторожно с целочисленным делением!
     */
    public static double fahrenheitToCelsius(int fahrenheit) {
        throw new UnsupportedOperationException("TODO");
    }

    /**
     * Поменять местами байты 16-битного значения: 0x1234 → 0x3412.
     * На входе значение 0..0xFFFF, на выходе тоже.
     */
    public static int swapBytes(int value16) {
        throw new UnsupportedOperationException("TODO");
    }
}
