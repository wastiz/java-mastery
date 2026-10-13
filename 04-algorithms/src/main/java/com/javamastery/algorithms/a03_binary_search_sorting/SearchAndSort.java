package com.javamastery.algorithms.a03_binary_search_sorting;

/**
 * A03 · Бинарный поиск и сортировки. Arrays.sort / Collections.sort использовать НЕЛЬЗЯ (кроме mergeIntervals).
 */
public final class SearchAndSort {
    private SearchAndSort() {}

    /** LeetCode 704. Индекс target в отсортированном массиве или -1. Осторожно с переполнением при вычислении mid! */
    public static int binarySearch(int[] a, int target) {
        throw new UnsupportedOperationException("TODO");
    }

    /** LeetCode 35. Позиция вставки (lower bound): первый индекс i, где a[i] &gt;= target. */
    public static int lowerBound(int[] a, int target) {
        throw new UnsupportedOperationException("TODO");
    }

    /** LeetCode 33. Поиск в повёрнутом отсортированном массиве (без дубликатов). O(log n). */
    public static int searchRotated(int[] a, int target) {
        throw new UnsupportedOperationException("TODO");
    }

    /** LeetCode 69. Целая часть квадратного корня без Math.sqrt — бинарный поиск по ответу. */
    public static int sqrt(int x) {
        throw new UnsupportedOperationException("TODO");
    }

    /** Сортировка слиянием (стабильная, O(n log n)). Вернуть новый массив. */
    public static int[] mergeSort(int[] a) {
        throw new UnsupportedOperationException("TODO");
    }

    /** Быстрая сортировка in-place (разбиение Хоара или Ломуто, случайный опорный элемент — иначе O(n²) на отсортированных). */
    public static void quickSort(int[] a) {
        throw new UnsupportedOperationException("TODO");
    }

    // TODO: вспомогательные методы

    /** LeetCode 56. Merge Intervals. Здесь сортировку использовать можно. Результат отсортирован по началу. */
    public static int[][] mergeIntervals(int[][] intervals) {
        throw new UnsupportedOperationException("TODO");
    }

    /** LeetCode 215. k-й по величине элемент. В среднем O(n) — Quickselect (или кучей за O(n log k)). */
    public static int kthLargest(int[] nums, int k) {
        throw new UnsupportedOperationException("TODO");
    }
}
