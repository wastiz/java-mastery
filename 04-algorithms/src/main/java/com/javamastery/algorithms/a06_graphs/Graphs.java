package com.javamastery.algorithms.a06_graphs;

import java.util.List;

/**
 * A06 · Графы: DFS/BFS по сетке, топологическая сортировка, кратчайшие пути.
 */
public final class Graphs {
    private Graphs() {}

    /** LeetCode 200. Число островов ('1' — суша, '0' — вода; соседи по 4 направлениям). Массив можно портить. */
    public static int numIslands(char[][] grid) {
        throw new UnsupportedOperationException("TODO");
    }

    // TODO: вспомогательные методы

    /**
     * LeetCode 207/210. Course Schedule: n курсов, prerequisites[i] = {курс, пререквизит}.
     * Вернуть порядок прохождения (любой валидный) или пустой список, если есть цикл. Алгоритм Кана (BFS по входящим степеням).
     */
    public static List<Integer> courseOrder(int n, int[][] prerequisites) {
        throw new UnsupportedOperationException("TODO");
    }

    /**
     * Кратчайший путь в лабиринте: '.' — проход, '#' — стена, 'S' — старт, 'E' — выход. Ходы по 4 направлениям.
     * Вернуть число шагов или -1. BFS.
     */
    public static int shortestPath(String[] maze) {
        throw new UnsupportedOperationException("TODO");
    }

    /**
     * Дейкстра: граф из n вершин, рёбра {from, to, weight} (ориентированные, вес &gt;= 0).
     * Вернуть массив кратчайших расстояний от src; недостижимые — Long.MAX_VALUE. PriorityQueue.
     */
    public static long[] dijkstra(int n, int[][] edges, int src) {
        throw new UnsupportedOperationException("TODO");
    }

    /** LeetCode 547. Число компонент связности (матрица смежности). Реализуй через Union-Find (DSU) с path compression. */
    public static int countComponents(int[][] isConnected) {
        throw new UnsupportedOperationException("TODO");
    }

    // TODO: find для DSU
}
