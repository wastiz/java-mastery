package com.javamastery.algorithms.a03_binary_search_sorting;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.Timeout;

import java.util.Arrays;
import java.util.Random;

import static org.junit.jupiter.api.Assertions.*;

class SearchAndSortTest {

    @Test
    void binarySearch() {
        int[] a = {-1, 0, 3, 5, 9, 12};
        assertEquals(4, SearchAndSort.binarySearch(a, 9));
        assertEquals(-1, SearchAndSort.binarySearch(a, 2));
        assertEquals(0, SearchAndSort.binarySearch(a, -1));
        assertEquals(-1, SearchAndSort.binarySearch(new int[0], 1));
    }

    @Test
    void lowerBound() {
        int[] a = {1, 3, 3, 5, 6};
        assertEquals(1, SearchAndSort.lowerBound(a, 3));
        assertEquals(3, SearchAndSort.lowerBound(a, 4));
        assertEquals(5, SearchAndSort.lowerBound(a, 7));
        assertEquals(0, SearchAndSort.lowerBound(a, 0));
    }

    @Test
    void rotated() {
        assertEquals(4, SearchAndSort.searchRotated(new int[]{4, 5, 6, 7, 0, 1, 2}, 0));
        assertEquals(-1, SearchAndSort.searchRotated(new int[]{4, 5, 6, 7, 0, 1, 2}, 3));
        assertEquals(0, SearchAndSort.searchRotated(new int[]{1}, 1));
        assertEquals(1, SearchAndSort.searchRotated(new int[]{3, 1}, 1));
    }

    @Test
    void sqrt() {
        assertEquals(2, SearchAndSort.sqrt(8));
        assertEquals(0, SearchAndSort.sqrt(0));
        assertEquals(46340, SearchAndSort.sqrt(Integer.MAX_VALUE));
    }

    @Test
    @Timeout(5)
    void sorting() {
        Random r = new Random(3);
        for (int n : new int[]{0, 1, 2, 10, 1000, 200_000}) {
            int[] a = r.ints(n, -1000, 1000).toArray();
            int[] expected = a.clone();
            Arrays.sort(expected);
            assertArrayEquals(expected, SearchAndSort.mergeSort(a));
            int[] b = a.clone();
            SearchAndSort.quickSort(b);
            assertArrayEquals(expected, b);
        }
    }

    @Test
    @Timeout(5)
    void quickSortOnSortedAndEqualInput() {
        int[] sorted = new int[200_000];
        for (int i = 0; i < sorted.length; i++) sorted[i] = i;
        SearchAndSort.quickSort(sorted);
        assertEquals(199_999, sorted[199_999]);
        int[] same = new int[200_000];
        SearchAndSort.quickSort(same);
        assertEquals(0, same[100]);
    }

    @Test
    void mergeIntervals() {
        int[][] r = SearchAndSort.mergeIntervals(new int[][]{{8, 10}, {1, 3}, {2, 6}, {15, 18}});
        assertArrayEquals(new int[][]{{1, 6}, {8, 10}, {15, 18}}, r);
        assertArrayEquals(new int[][]{{1, 5}}, SearchAndSort.mergeIntervals(new int[][]{{1, 4}, {4, 5}}));
        assertArrayEquals(new int[][]{{1, 10}}, SearchAndSort.mergeIntervals(new int[][]{{1, 10}, {2, 3}}));
    }

    @Test
    void kth() {
        assertEquals(5, SearchAndSort.kthLargest(new int[]{3, 2, 1, 5, 6, 4}, 2));
        assertEquals(4, SearchAndSort.kthLargest(new int[]{3, 2, 3, 1, 2, 4, 5, 5, 6}, 4));
    }
}
