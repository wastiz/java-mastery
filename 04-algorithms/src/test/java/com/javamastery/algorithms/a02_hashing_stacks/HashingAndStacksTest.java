package com.javamastery.algorithms.a02_hashing_stacks;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.Timeout;

import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

class HashingAndStacksTest {

    @Test
    void groupAnagrams() {
        assertEquals(List.of(List.of("eat", "tea", "ate"), List.of("tan", "nat"), List.of("bat")),
                HashingAndStacks.groupAnagrams(new String[]{"eat", "tea", "tan", "ate", "nat", "bat"}));
    }

    @Test
    void longestConsecutive() {
        assertEquals(4, HashingAndStacks.longestConsecutive(new int[]{100, 4, 200, 1, 3, 2}));
        assertEquals(9, HashingAndStacks.longestConsecutive(new int[]{0, 3, 7, 2, 5, 8, 4, 6, 0, 1}));
        assertEquals(0, HashingAndStacks.longestConsecutive(new int[]{}));
    }

    @Test
    @Timeout(2)
    void longestConsecutiveLinear() {
        int[] a = new int[300_000];
        for (int i = 0; i < a.length; i++) a[i] = a.length - i;
        assertEquals(300_000, HashingAndStacks.longestConsecutive(a));
    }

    @Test
    void temperatures() {
        assertArrayEquals(new int[]{1, 1, 4, 2, 1, 1, 0, 0}, HashingAndStacks.dailyTemperatures(new int[]{73, 74, 75, 71, 69, 72, 76, 73}));
    }

    @Test
    void rpn() {
        assertEquals(9, HashingAndStacks.evalRPN(new String[]{"2", "1", "+", "3", "*"}));
        assertEquals(6, HashingAndStacks.evalRPN(new String[]{"4", "13", "5", "/", "+"}));
        assertEquals(22, HashingAndStacks.evalRPN(new String[]{"10", "6", "9", "3", "+", "-11", "*", "/", "*", "17", "+", "5", "+"}));
    }

    @Test
    void minStack() {
        var s = new HashingAndStacks.MinStack();
        s.push(-2);
        s.push(0);
        s.push(-3);
        assertEquals(-3, s.getMin());
        s.pop();
        assertEquals(0, s.top());
        assertEquals(-2, s.getMin());
        s.push(-2);
        s.pop();
        assertEquals(-2, s.getMin());
    }

    @Test
    void subarraySum() {
        assertEquals(2, HashingAndStacks.subarraySum(new int[]{1, 1, 1}, 2));
        assertEquals(2, HashingAndStacks.subarraySum(new int[]{1, 2, 3}, 3));
        assertEquals(4, HashingAndStacks.subarraySum(new int[]{1, -1, 1, -1}, 0));
    }
}
