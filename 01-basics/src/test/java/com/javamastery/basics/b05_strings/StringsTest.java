package com.javamastery.basics.b05_strings;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class StringsTest {

    @Test
    void palindrome() {
        assertTrue(Strings.isPalindrome("A man, a plan, a canal: Panama"));
        assertTrue(Strings.isPalindrome(""));
        assertTrue(Strings.isPalindrome(".,"));
        assertFalse(Strings.isPalindrome("race a car"));
        assertFalse(Strings.isPalindrome("0P"));
    }

    @Test
    void anagram() {
        assertTrue(Strings.isAnagram("Listen", "Silent"));
        assertTrue(Strings.isAnagram("Dormitory", "dirty room"));
        assertFalse(Strings.isAnagram("abc", "abd"));
        assertFalse(Strings.isAnagram("aab", "abb"));
    }

    @Test
    void compress() {
        assertEquals("a3bc2d4", Strings.compress("aaabccdddd"));
        assertEquals("abc", Strings.compress("abc"));
        assertEquals("aabb", Strings.compress("aabb"));      // "a2b2" не короче
        assertEquals("x10", Strings.compress("xxxxxxxxxx"));
        assertEquals("", Strings.compress(""));
    }

    @Test
    void reverseWords() {
        assertEquals("blue is sky the", Strings.reverseWords("  the sky   is blue "));
        assertEquals("one", Strings.reverseWords("one"));
        assertEquals("", Strings.reverseWords("   "));
    }

    @Test
    void caesar() {
        assertEquals("Khoor, Zruog!", Strings.caesar("Hello, World!", 3));
        assertEquals("Hello, World!", Strings.caesar("Khoor, Zruog!", -3));
        assertEquals("abc", Strings.caesar("xyz", 29));
        assertEquals("Привет", Strings.caesar("Привет", 5));
    }

    @Test
    void titleCase() {
        assertEquals("Hello World From Java", Strings.titleCase("hello world from java"));
        assertEquals("Java  Rocks", Strings.titleCase("jAVA  ROCKS"));
    }

    @Test
    void lcp() {
        assertEquals("fl", Strings.longestCommonPrefix(new String[]{"flower", "flow", "flight"}));
        assertEquals("", Strings.longestCommonPrefix(new String[]{"dog", "racecar", "car"}));
        assertEquals("", Strings.longestCommonPrefix(new String[]{}));
        assertEquals("solo", Strings.longestCommonPrefix(new String[]{"solo"}));
    }
}
