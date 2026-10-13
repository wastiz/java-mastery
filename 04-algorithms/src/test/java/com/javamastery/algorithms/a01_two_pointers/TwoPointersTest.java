package com.javamastery.algorithms.a01_two_pointers;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.Timeout;

import java.util.List;
import java.util.Random;

import static org.junit.jupiter.api.Assertions.*;

class TwoPointersTest {

    @Test
    void twoSum() {
        assertArrayEquals(new int[]{0, 1}, TwoPointers.twoSum(new int[]{2, 7, 11, 15}, 9));
        assertArrayEquals(new int[]{1, 2}, TwoPointers.twoSum(new int[]{3, 2, 4}, 6));
        assertArrayEquals(new int[]{0, 1}, TwoPointers.twoSum(new int[]{3, 3}, 6));
    }

    @Test
    @Timeout(2)
    void twoSumIsLinear() {
        int n = 200_000;
        int[] a = new int[n];
        for (int i = 0; i < n; i++) a[i] = i * 2;
        assertArrayEquals(new int[]{n - 2, n - 1}, TwoPointers.twoSum(a, a[n - 2] + a[n - 1]));
    }

    @Test
    void twoSumSorted() {
        assertArrayEquals(new int[]{1, 2}, TwoPointers.twoSumSorted(new int[]{2, 7, 11, 15}, 9));
        assertArrayEquals(new int[]{1, 3}, TwoPointers.twoSumSorted(new int[]{2, 3, 4}, 6));
    }

    @Test
    void longestSubstring() {
        assertEquals(3, TwoPointers.lengthOfLongestSubstring("abcabcbb"));
        assertEquals(1, TwoPointers.lengthOfLongestSubstring("bbbbb"));
        assertEquals(3, TwoPointers.lengthOfLongestSubstring("pwwkew"));
        assertEquals(0, TwoPointers.lengthOfLongestSubstring(""));
        assertEquals(2, TwoPointers.lengthOfLongestSubstring("abba"));
    }

    @Test
    void maxArea() {
        assertEquals(49, TwoPointers.maxArea(new int[]{1, 8, 6, 2, 5, 4, 8, 3, 7}));
        assertEquals(1, TwoPointers.maxArea(new int[]{1, 1}));
    }

    @Test
    void moveZeroes() {
        int[] a = {0, 1, 0, 3, 12};
        TwoPointers.moveZeroes(a);
        assertArrayEquals(new int[]{1, 3, 12, 0, 0}, a);
    }

    @Test
    void threeSum() {
        assertEquals(List.of(List.of(-1, -1, 2), List.of(-1, 0, 1)), TwoPointers.threeSum(new int[]{-1, 0, 1, 2, -1, -4}));
        assertEquals(List.of(List.of(0, 0, 0)), TwoPointers.threeSum(new int[]{0, 0, 0, 0}));
        assertEquals(List.of(), TwoPointers.threeSum(new int[]{0, 1, 1}));
    }

    @Test
    @Timeout(3)
    void threeSumIsQuadratic() {
        Random r = new Random(1);
        int[] a = new int[3000];
        for (int i = 0; i < a.length; i++) a[i] = r.nextInt(2001) - 1000;
        assertFalse(TwoPointers.threeSum(a).isEmpty());
    }

    @Test
    void minSubArrayLen() {
        assertEquals(2, TwoPointers.minSubArrayLen(7, new int[]{2, 3, 1, 2, 4, 3}));
        assertEquals(1, TwoPointers.minSubArrayLen(4, new int[]{1, 4, 4}));
        assertEquals(0, TwoPointers.minSubArrayLen(11, new int[]{1, 1, 1, 1, 1, 1, 1, 1}));
    }
}
