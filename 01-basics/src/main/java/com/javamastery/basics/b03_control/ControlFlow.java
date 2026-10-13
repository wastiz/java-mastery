package com.javamastery.basics.b03_control;

import java.util.List;

/**
 * B03 · Условия, циклы, switch-выражения.
 */
public final class ControlFlow {
    private ControlFlow() {}

    /**
     * FizzBuzz с изюминкой: для чисел 1..n.
     * Делится на 3 → "Fizz", на 5 → "Buzz", на оба → "FizzBuzz",
     * содержит цифру 7 (в десятичной записи) → "Lucky" (это правило важнее всех остальных),
     * иначе само число строкой.
     */
    public static List<String> fizzBuzz(int n) {
        throw new UnsupportedOperationException("TODO");
    }

    public enum DayType { WORKDAY, WEEKEND, UNKNOWN }

    /**
     * Используй switch-ВЫРАЖЕНИЕ (с {@code ->}, Java 14+), без if.
     * "mon".."fri" → WORKDAY, "sat","sun" → WEEKEND, всё остальное → UNKNOWN. Регистр не важен, null → UNKNOWN.
     */
    public static DayType dayType(String day) {
        throw new UnsupportedOperationException("TODO");
    }

    /**
     * Гипотеза Коллатца: если n чётное — n/2, иначе 3n+1. Сколько шагов, чтобы дойти до 1?
     * collatzSteps(1) = 0, collatzSteps(6) = 8. n <= 0 → IllegalArgumentException.
     * Осторожно: промежуточные значения могут не влезть в int.
     */
    public static int collatzSteps(long n) {
        throw new UnsupportedOperationException("TODO");
    }

    /**
     * Найти первую пару (i, j), i &lt; j, в матрице чисел, где matrix[i][j] == target,
     * перебирая строки сверху вниз и столбцы слева направо, условие i &lt; j относится к индексам строки и столбца.
     * Вернуть {row, col} или {-1, -1}. Используй break с меткой (labeled break) — это тоже спрашивают.
     */
    public static int[] findAboveDiagonal(int[][] matrix, int target) {
        throw new UnsupportedOperationException("TODO");
    }

    /**
     * Високосный год по григорианскому календарю (делится на 4, но не на 100, кроме кратных 400).
     */
    public static boolean isLeap(int year) {
        throw new UnsupportedOperationException("TODO");
    }
}
