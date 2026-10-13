package com.javamastery.basics.b05_strings;

/**
 * B05 · Строки, StringBuilder, string pool.
 */
public final class Strings {
    private Strings() {}

    /** Палиндром, игнорируя регистр и всё, кроме букв и цифр. "A man, a plan, a canal: Panama" → true. */
    public static boolean isPalindrome(String s) {
        throw new UnsupportedOperationException("TODO");
    }

    /** Являются ли строки анаграммами (регистр не важен, пробелы игнорируются). */
    public static boolean isAnagram(String a, String b) {
        throw new UnsupportedOperationException("TODO");
    }

    /**
     * Сжатие строки: "aaabccdddd" → "a3bc2d4" (одиночный символ без числа).
     * Если сжатая строка не короче исходной — вернуть исходную.
     * Используй StringBuilder, а не конкатенацию в цикле (почему — смотри теорию).
     */
    public static String compress(String s) {
        throw new UnsupportedOperationException("TODO");
    }

    /** Развернуть порядок слов: "  the sky   is blue " → "blue is sky the" (лишние пробелы убрать). */
    public static String reverseWords(String s) {
        throw new UnsupportedOperationException("TODO");
    }

    /**
     * Шифр Цезаря: сдвинуть латинские буквы на shift позиций по кругу, сохраняя регистр.
     * Остальные символы не трогать. shift может быть отрицательным и больше 26.
     */
    public static String caesar(String s, int shift) {
        throw new UnsupportedOperationException("TODO");
    }

    /** "hello world from java" → "Hello World From Java". Несколько пробелов подряд сохраняются как есть. */
    public static String titleCase(String s) {
        throw new UnsupportedOperationException("TODO");
    }

    /**
     * Самый длинный общий префикс массива строк (LeetCode 14). Пустой массив → "".
     */
    public static String longestCommonPrefix(String[] words) {
        throw new UnsupportedOperationException("TODO");
    }
}
