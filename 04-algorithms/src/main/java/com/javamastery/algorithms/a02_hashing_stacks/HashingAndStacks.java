package com.javamastery.algorithms.a02_hashing_stacks;

import java.util.List;

/**
 * A02 · Хеш-таблицы, стеки, монотонный стек.
 */
public final class HashingAndStacks {
    private HashingAndStacks() {}

    /** LeetCode 49. Group Anagrams. Порядок групп и слов внутри — по первому появлению. */
    public static List<List<String>> groupAnagrams(String[] words) {
        throw new UnsupportedOperationException("TODO");
    }

    /** LeetCode 128. Longest Consecutive Sequence за O(n) (без сортировки!). [100,4,200,1,3,2] → 4 */
    public static int longestConsecutive(int[] nums) {
        throw new UnsupportedOperationException("TODO");
    }

    /** LeetCode 739. Daily Temperatures: сколько дней ждать более тёплого дня (0, если никогда). O(n) — монотонный стек. */
    public static int[] dailyTemperatures(int[] t) {
        throw new UnsupportedOperationException("TODO");
    }

    /** LeetCode 150. Evaluate Reverse Polish Notation: ["2","1","+","3","*"] → 9. Деление — целочисленное к нулю. */
    public static int evalRPN(String[] tokens) {
        throw new UnsupportedOperationException("TODO");
    }

    /** LeetCode 155. Min Stack: push, pop, top, getMin — все за O(1). */
    public static class MinStack {
        // TODO: поля

        public void push(int x) {
            throw new UnsupportedOperationException("TODO");
        }

        public void pop() {
            throw new UnsupportedOperationException("TODO");
        }

        public int top() {
            throw new UnsupportedOperationException("TODO");
        }

        public int getMin() {
            throw new UnsupportedOperationException("TODO");
        }
    }

    /** LeetCode 560. Количество подмассивов с суммой k (числа могут быть отрицательными). O(n) — префиксные суммы + HashMap. */
    public static int subarraySum(int[] nums, int k) {
        throw new UnsupportedOperationException("TODO");
    }
}
