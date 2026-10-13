package com.javamastery.algorithms.a07_dp;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.Timeout;

import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

class DynamicProgrammingTest {

    @Test
    @Timeout(1)
    void stairs() {
        assertEquals(1, DynamicProgramming.climbStairs(1));
        assertEquals(3, DynamicProgramming.climbStairs(3));
        assertEquals(4660046610375530309L, DynamicProgramming.climbStairs(90));
    }

    @Test
    void rob() {
        assertEquals(4, DynamicProgramming.rob(new int[]{1, 2, 3, 1}));
        assertEquals(12, DynamicProgramming.rob(new int[]{2, 7, 9, 3, 1}));
        assertEquals(0, DynamicProgramming.rob(new int[]{}));
    }

    @Test
    void coins() {
        assertEquals(3, DynamicProgramming.coinChange(new int[]{1, 2, 5}, 11));
        assertEquals(-1, DynamicProgramming.coinChange(new int[]{2}, 3));
        assertEquals(0, DynamicProgramming.coinChange(new int[]{1}, 0));
        assertEquals(20, DynamicProgramming.coinChange(new int[]{186, 419, 83, 408}, 6249));
    }

    @Test
    void lcs() {
        assertEquals(3, DynamicProgramming.lcs("abcde", "ace"));
        assertEquals(0, DynamicProgramming.lcs("abc", "def"));
    }

    @Test
    @Timeout(2)
    void lis() {
        assertEquals(4, DynamicProgramming.lis(new int[]{10, 9, 2, 5, 3, 7, 101, 18}));
        assertEquals(1, DynamicProgramming.lis(new int[]{7, 7, 7, 7}));
        int[] big = new int[200_000];
        for (int i = 0; i < big.length; i++) big[i] = i;
        assertEquals(200_000, DynamicProgramming.lis(big));
    }

    @Test
    void edit() {
        assertEquals(3, DynamicProgramming.editDistance("horse", "ros"));
        assertEquals(5, DynamicProgramming.editDistance("intention", "execution"));
        assertEquals(3, DynamicProgramming.editDistance("", "abc"));
    }

    @Test
    void knapsack() {
        assertEquals(9, DynamicProgramming.knapsack(new int[]{1, 3, 4, 5}, new int[]{1, 4, 5, 7}, 7));
        assertEquals(0, DynamicProgramming.knapsack(new int[]{10}, new int[]{100}, 5));
    }

    @Test
    void wordBreak() {
        assertTrue(DynamicProgramming.wordBreak("leetcode", List.of("leet", "code")));
        assertTrue(DynamicProgramming.wordBreak("applepenapple", List.of("apple", "pen")));
        assertFalse(DynamicProgramming.wordBreak("catsandog", List.of("cats", "dog", "sand", "and", "cat")));
    }
}
