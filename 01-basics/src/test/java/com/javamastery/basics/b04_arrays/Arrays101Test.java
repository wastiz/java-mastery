package com.javamastery.basics.b04_arrays;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class Arrays101Test {

    @Test
    void rotate() {
        int[] a = {1, 2, 3, 4, 5};
        Arrays101.rotateRight(a, 2);
        assertArrayEquals(new int[]{4, 5, 1, 2, 3}, a);

        int[] b = {1, 2, 3};
        Arrays101.rotateRight(b, 7);
        assertArrayEquals(new int[]{3, 1, 2}, b);

        int[] c = {};
        Arrays101.rotateRight(c, 3);
        assertArrayEquals(new int[]{}, c);

        int[] d = {1, 2, 3, 4};
        Arrays101.rotateRight(d, 0);
        assertArrayEquals(new int[]{1, 2, 3, 4}, d);
    }

    @Test
    void merge() {
        assertArrayEquals(new int[]{1, 2, 3, 4, 5, 6}, Arrays101.mergeSorted(new int[]{1, 3, 5}, new int[]{2, 4, 6}));
        assertArrayEquals(new int[]{1, 1, 2}, Arrays101.mergeSorted(new int[]{1}, new int[]{1, 2}));
        assertArrayEquals(new int[]{7}, Arrays101.mergeSorted(new int[]{}, new int[]{7}));
    }

    @Test
    void transpose() {
        int[][] m = {{1, 2, 3}, {4, 5, 6}};
        int[][] t = Arrays101.transpose(m);
        assertEquals(3, t.length);
        assertArrayEquals(new int[]{1, 4}, t[0]);
        assertArrayEquals(new int[]{3, 6}, t[2]);
    }

    @Test
    void spiral() {
        int[][] m = {
                {1, 2, 3, 4},
                {5, 6, 7, 8},
                {9, 10, 11, 12}
        };
        assertArrayEquals(new int[]{1, 2, 3, 4, 8, 12, 11, 10, 9, 5, 6, 7}, Arrays101.spiral(m));
        assertArrayEquals(new int[]{1, 2, 3}, Arrays101.spiral(new int[][]{{1}, {2}, {3}}));
        assertArrayEquals(new int[]{1, 2, 4, 3}, Arrays101.spiral(new int[][]{{1, 2}, {3, 4}}));
    }

    @Test
    void removeDuplicates() {
        int[] a = {0, 0, 1, 1, 1, 2, 2, 3, 3, 4};
        int k = Arrays101.removeDuplicates(a);
        assertEquals(5, k);
        assertArrayEquals(new int[]{0, 1, 2, 3, 4}, java.util.Arrays.copyOf(a, k));
        assertEquals(0, Arrays101.removeDuplicates(new int[]{}));
    }

    @Test
    void prefixSums() {
        int[] a = {3, -1, 4, 1, 5};
        long[] p = Arrays101.prefixSums(a);
        assertArrayEquals(new long[]{0, 3, 2, 6, 7, 12}, p);
        assertEquals(4, Arrays101.rangeSum(p, 1, 3));
        assertEquals(12, Arrays101.rangeSum(p, 0, 4));
        assertEquals(5, Arrays101.rangeSum(p, 4, 4));
    }

    @Test
    void prefixSumsDoNotOverflow() {
        int[] a = {Integer.MAX_VALUE, Integer.MAX_VALUE};
        long[] p = Arrays101.prefixSums(a);
        assertEquals(2L * Integer.MAX_VALUE, Arrays101.rangeSum(p, 0, 1));
    }
}
