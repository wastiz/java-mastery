package com.javamastery.core.c07_queues;

import java.util.List;

/**
 * C07 · Deque, Set, PriorityQueue в алгоритмах.
 */
public final class QueueAlgorithms {
    private QueueAlgorithms() {}

    /**
     * Максимум в скользящем окне размера k (LeetCode 239) за O(n) — монотонная очередь на ArrayDeque.
     * [1,3,-1,-3,5,3,6,7], k=3 → [3,3,5,5,6,7]
     */
    public static int[] slidingWindowMax(int[] a, int k) {
        throw new UnsupportedOperationException("TODO");
    }

    /** Убрать дубликаты, сохранив порядок первого появления. */
    public static <T> List<T> distinctKeepOrder(List<T> items) {
        throw new UnsupportedOperationException("TODO");
    }

    /**
     * k наибольших элементов, отсортированных по убыванию, за O(n log k) — куча (min-heap) размера k.
     */
    public static List<Integer> topK(int[] a, int k) {
        throw new UnsupportedOperationException("TODO");
    }

    /**
     * Проверка скобочной последовательности для (), [], {} — стек на ArrayDeque (не используй java.util.Stack — почему?).
     */
    public static boolean balanced(String s) {
        throw new UnsupportedOperationException("TODO");
    }

    /**
     * Слить k отсортированных списков в один отсортированный (LeetCode 23) через PriorityQueue.
     */
    public static List<Integer> mergeKSorted(List<List<Integer>> lists) {
        throw new UnsupportedOperationException("TODO");
    }
}
