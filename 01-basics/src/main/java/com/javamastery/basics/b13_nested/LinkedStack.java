package com.javamastery.basics.b13_nested;

import java.util.Iterator;
import java.util.NoSuchElementException;

/**
 * B13 · Вложенные классы: private static nested class (Node) + inner class (итератор).
 *
 * <p>Стек на связном списке. Итерация — от вершины к основанию.
 * pop/peek на пустом стеке → NoSuchElementException.
 * Почему Node должен быть static? Что будет храниться в объекте non-static inner класса?
 */
public class LinkedStack<T> implements Iterable<T> {
    // TODO: static nested Node + поля head/size

    public void push(T value) {
        throw new UnsupportedOperationException("TODO");
    }

    public T pop() {
        throw new UnsupportedOperationException("TODO");
    }

    public T peek() {
        throw new UnsupportedOperationException("TODO");
    }

    public int size() {
        throw new UnsupportedOperationException("TODO");
    }

    public boolean isEmpty() {
        throw new UnsupportedOperationException("TODO");
    }

    @Override
    public Iterator<T> iterator() {
        throw new UnsupportedOperationException("TODO: верни экземпляр inner-класса (или анонимного класса)");
    }

    // TODO: inner-класс итератора
}
