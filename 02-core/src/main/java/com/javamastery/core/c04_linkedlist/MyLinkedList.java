package com.javamastery.core.c04_linkedlist;

import java.util.Iterator;
import java.util.List;
import java.util.NoSuchElementException;

/**
 * C04 · Двусвязный список (как java.util.LinkedList) — и почему он почти никогда не лучше ArrayList.
 *
 * <ul>
 *   <li>addFirst/addLast/removeFirst/removeLast — O(1);</li>
 *   <li>get(i) — идёт от ближайшего конца (если i &lt; size/2 — с головы, иначе с хвоста);</li>
 *   <li>removeFirst/removeLast/getFirst/getLast на пустом → NoSuchElementException;</li>
 *   <li>reverse() — развернуть IN-PLACE, меняя ссылки, без создания новых узлов;</li>
 *   <li>для тестов: lastGetSteps() — сколько переходов по ссылкам сделал последний get(i).</li>
 * </ul>
 */
public class MyLinkedList<E> implements Iterable<E> {
    // TODO: узел и поля

    public void addFirst(E e) {
        throw new UnsupportedOperationException("TODO");
    }

    public void addLast(E e) {
        throw new UnsupportedOperationException("TODO");
    }

    public E removeFirst() {
        throw new UnsupportedOperationException("TODO");
    }

    public E removeLast() {
        throw new UnsupportedOperationException("TODO");
    }

    public E getFirst() {
        throw new UnsupportedOperationException("TODO");
    }

    public E getLast() {
        throw new UnsupportedOperationException("TODO");
    }

    public E get(int index) {
        throw new UnsupportedOperationException("TODO");
    }

    /** Удалить первое вхождение. */
    public boolean remove(Object o) {
        throw new UnsupportedOperationException("TODO");
    }

    public int size() {
        throw new UnsupportedOperationException("TODO");
    }

    public void reverse() {
        throw new UnsupportedOperationException("TODO");
    }

    public int lastGetSteps() {
        throw new UnsupportedOperationException("TODO");
    }

    /** Содержимое с головы до хвоста — для тестов. Также проверь обратные ссылки! */
    public List<E> toList() {
        throw new UnsupportedOperationException("TODO");
    }

    /** Содержимое с хвоста к голове по prev-ссылкам — для тестов. */
    public List<E> toListBackwards() {
        throw new UnsupportedOperationException("TODO");
    }

    // TODO: вспомогательные методы

    @Override
    public Iterator<E> iterator() {
        throw new UnsupportedOperationException("TODO");
    }
}
