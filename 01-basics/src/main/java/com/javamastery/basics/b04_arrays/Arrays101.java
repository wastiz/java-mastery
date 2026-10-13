package com.javamastery.basics.b04_arrays;

/**
 * B04 · Массивы. Стандартные утилиты {@code java.util.Arrays} использовать можно только там, где явно разрешено.
 */
public final class Arrays101 {
    private Arrays101() {}

    /** Сдвинуть массив вправо на k позиций IN-PLACE за O(n) и O(1) памяти. k может быть больше длины. [1,2,3,4,5], k=2 → [4,5,1,2,3] */
    public static void rotateRight(int[] a, int k) {
        throw new UnsupportedOperationException("TODO");
    }

    // TODO: вспомогательные методы, если нужны

    /** Слить два отсортированных массива в новый отсортированный массив за O(n + m). */
    public static int[] mergeSorted(int[] a, int[] b) {
        throw new UnsupportedOperationException("TODO");
    }

    /** Транспонировать прямоугольную матрицу (n x m → m x n). */
    public static int[][] transpose(int[][] m) {
        throw new UnsupportedOperationException("TODO");
    }

    /** Обход матрицы по спирали по часовой стрелке, начиная с левого верхнего угла. */
    public static int[] spiral(int[][] m) {
        throw new UnsupportedOperationException("TODO");
    }

    /**
     * Удалить дубликаты из ОТСОРТИРОВАННОГО массива in-place (LeetCode 26).
     * Вернуть новую длину k; первые k элементов — уникальные значения по возрастанию.
     */
    public static int removeDuplicates(int[] sorted) {
        throw new UnsupportedOperationException("TODO");
    }

    /**
     * Префиксные суммы: вернуть массив p длины n+1, где p[i] = a[0] + ... + a[i-1].
     * Затем — метод rangeSum(p, l, r) = сумма a[l..r] включительно за O(1).
     */
    public static long[] prefixSums(int[] a) {
        throw new UnsupportedOperationException("TODO");
    }

    public static long rangeSum(long[] prefix, int l, int r) {
        throw new UnsupportedOperationException("TODO");
    }
}
