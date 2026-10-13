package com.javamastery.core.c03_arraylist;

import java.util.ConcurrentModificationException;
import java.util.Iterator;
import java.util.NoSuchElementException;

/**
 * C03 · Свой ArrayList — классика собеседования «как устроен ArrayList».
 *
 * <ul>
 *   <li>внутри — массив Object[]; начальная ёмкость 10 (или заданная в конструкторе);</li>
 *   <li>при переполнении ёмкость растёт в 1.5 раза: newCap = old + (old &gt;&gt; 1), как в JDK;</li>
 *   <li>get/set/remove с неверным индексом → IndexOutOfBoundsException;</li>
 *   <li>remove очищает освободившуюся ячейку (иначе — утечка памяти!);</li>
 *   <li>итератор fail-fast: если список изменили не через итератор во время обхода → ConcurrentModificationException;</li>
 *   <li>iterator().remove() удаляет последний возвращённый элемент; повторный вызов без next() → IllegalStateException.</li>
 * </ul>
 * Нельзя использовать java.util коллекции внутри. Arrays.copyOf и System.arraycopy — можно.
 */
public class MyArrayList<E> implements Iterable<E> {
    // TODO: поля: массив, размер, счётчик модификаций

    public MyArrayList() {
        throw new UnsupportedOperationException("TODO");
    }

    public MyArrayList(int initialCapacity) {
        throw new UnsupportedOperationException("TODO");
    }

    public int size() {
        throw new UnsupportedOperationException("TODO");
    }

    public boolean isEmpty() {
        throw new UnsupportedOperationException("TODO");
    }

    /** Текущая длина внутреннего массива (для тестов роста). */
    public int capacity() {
        throw new UnsupportedOperationException("TODO");
    }

    public void add(E e) {
        throw new UnsupportedOperationException("TODO");
    }

    public void add(int index, E e) {
        throw new UnsupportedOperationException("TODO");
    }

    // TODO: вспомогательные методы

    @SuppressWarnings("unchecked")
    public E get(int index) {
        throw new UnsupportedOperationException("TODO");
    }

    @SuppressWarnings("unchecked")
    public E set(int index, E e) {
        throw new UnsupportedOperationException("TODO: вернуть старое значение");
    }

    @SuppressWarnings("unchecked")
    public E remove(int index) {
        throw new UnsupportedOperationException("TODO");
    }

    /** Удалить первое вхождение (сравнение через equals, null поддерживается). */
    public boolean remove(Object o) {
        throw new UnsupportedOperationException("TODO");
    }

    public int indexOf(Object o) {
        throw new UnsupportedOperationException("TODO");
    }

    public boolean contains(Object o) {
        throw new UnsupportedOperationException("TODO");
    }

    public void clear() {
        throw new UnsupportedOperationException("TODO");
    }

    /** Ёмкость не меньше minCapacity. */
    public void ensureCapacity(int minCapacity) {
        throw new UnsupportedOperationException("TODO");
    }

    /** Ужать массив до size. */
    public void trimToSize() {
        throw new UnsupportedOperationException("TODO");
    }

    /** Для тестов: элемент внутреннего массива по индексу (даже за пределами size). */
    Object rawSlot(int i) {
        throw new UnsupportedOperationException("TODO");
    }

    @Override
    public Iterator<E> iterator() {
        throw new UnsupportedOperationException("TODO");
    }

    @Override
    public String toString() {
        throw new UnsupportedOperationException("TODO: формат как у java.util.ArrayList: [a, b, c]");
    }
}
