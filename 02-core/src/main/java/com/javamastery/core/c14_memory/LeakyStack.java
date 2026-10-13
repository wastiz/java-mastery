package com.javamastery.core.c14_memory;

import java.util.Arrays;
import java.util.EmptyStackException;

/**
 * C14 · Утечка памяти в Java (да, они бывают, даже с GC). Пример из Effective Java, Item 7.
 *
 * <p>Этот стек работает правильно функционально, но держит ссылки на «вытолкнутые» объекты,
 * и GC не может их собрать. Найди и исправь утечку. Тест проверяет это через WeakReference.
 * Остальную логику не меняй.
 */
public class LeakyStack<T> {
    private Object[] elements = new Object[16];
    private int size;

    public void push(T e) {
        if (size == elements.length) elements = Arrays.copyOf(elements, 2 * size + 1);
        elements[size++] = e;
    }

    @SuppressWarnings("unchecked")
    public T pop() {
        if (size == 0) throw new EmptyStackException();
        return (T) elements[--size];
    }

    public int size() {
        return size;
    }
}
