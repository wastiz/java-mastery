package com.javamastery.algorithms.a06_graphs;

import org.junit.jupiter.api.Test;

import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

class GraphsTest {

    @Test
    void islands() {
        char[][] g = {
                "11000".toCharArray(),
                "11000".toCharArray(),
                "00100".toCharArray(),
                "00011".toCharArray()};
        assertEquals(3, Graphs.numIslands(g));
        char[][] big = new char[1000][1000];
        for (char[] row : big) java.util.Arrays.fill(row, '1');
        assertEquals(1, Graphs.numIslands(big), "рекурсивный DFS на 10^6 клеток может упасть со StackOverflowError — используй явный стек или BFS");
    }

    @Test
    void courses() {
        List<Integer> order = Graphs.courseOrder(4, new int[][]{{1, 0}, {2, 0}, {3, 1}, {3, 2}});
        assertEquals(4, order.size());
        assertTrue(order.indexOf(0) < order.indexOf(1));
        assertTrue(order.indexOf(1) < order.indexOf(3));
        assertTrue(order.indexOf(2) < order.indexOf(3));
        assertEquals(List.of(), Graphs.courseOrder(2, new int[][]{{1, 0}, {0, 1}}));
        assertEquals(3, Graphs.courseOrder(3, new int[][]{}).size());
    }

    @Test
    void maze() {
        assertEquals(13, Graphs.shortestPath(new String[]{
                "S.#.....",
                ".##.###.",
                "....#..E"}));
        assertEquals(-1, Graphs.shortestPath(new String[]{
                "S#E"}));
        assertEquals(1, Graphs.shortestPath(new String[]{"SE"}));
    }

    @Test
    void dijkstra() {
        int[][] edges = {{0, 1, 4}, {0, 2, 1}, {2, 1, 2}, {1, 3, 1}, {2, 3, 5}};
        assertArrayEquals(new long[]{0, 3, 1, 4, Long.MAX_VALUE}, Graphs.dijkstra(5, edges, 0));
    }

    @Test
    void components() {
        assertEquals(2, Graphs.countComponents(new int[][]{{1, 1, 0}, {1, 1, 0}, {0, 0, 1}}));
        assertEquals(3, Graphs.countComponents(new int[][]{{1, 0, 0}, {0, 1, 0}, {0, 0, 1}}));
    }
}
