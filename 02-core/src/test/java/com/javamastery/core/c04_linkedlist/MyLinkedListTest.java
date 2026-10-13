package com.javamastery.core.c04_linkedlist;

import org.junit.jupiter.api.Test;

import java.util.ArrayList;
import java.util.List;
import java.util.NoSuchElementException;

import static org.junit.jupiter.api.Assertions.*;

class MyLinkedListTest {

    private MyLinkedList<Integer> of(int... xs) {
        MyLinkedList<Integer> l = new MyLinkedList<>();
        for (int x : xs) l.addLast(x);
        return l;
    }

    @Test
    void dequeOperations() {
        MyLinkedList<Integer> l = new MyLinkedList<>();
        l.addLast(2);
        l.addFirst(1);
        l.addLast(3);
        assertEquals(List.of(1, 2, 3), l.toList());
        assertEquals(List.of(3, 2, 1), l.toListBackwards());
        assertEquals(1, l.getFirst());
        assertEquals(3, l.getLast());
        assertEquals(1, l.removeFirst());
        assertEquals(3, l.removeLast());
        assertEquals(2, l.removeLast());
        assertEquals(0, l.size());
        assertEquals(List.of(), l.toListBackwards());
        assertThrows(NoSuchElementException.class, l::removeFirst);
        assertThrows(NoSuchElementException.class, l::getLast);
    }

    @Test
    void getWalksFromNearestEnd() {
        MyLinkedList<Integer> l = of(0, 1, 2, 3, 4, 5, 6, 7, 8, 9);
        assertEquals(1, l.get(1));
        assertEquals(1, l.lastGetSteps());
        assertEquals(8, l.get(8));
        assertEquals(1, l.lastGetSteps());
        assertEquals(9, l.get(9));
        assertEquals(0, l.lastGetSteps());
        assertThrows(IndexOutOfBoundsException.class, () -> l.get(10));
    }

    @Test
    void removeByValue() {
        MyLinkedList<Integer> l = of(5, 6, 7, 6);
        assertTrue(l.remove(Integer.valueOf(6)));
        assertEquals(List.of(5, 7, 6), l.toList());
        assertTrue(l.remove(Integer.valueOf(5)));
        assertTrue(l.remove(Integer.valueOf(6)));
        assertEquals(List.of(7), l.toList());
        assertEquals(List.of(7), l.toListBackwards());
        assertFalse(l.remove(Integer.valueOf(100)));
    }

    @Test
    void reverse() {
        MyLinkedList<Integer> l = of(1, 2, 3, 4);
        l.reverse();
        assertEquals(List.of(4, 3, 2, 1), l.toList());
        assertEquals(List.of(1, 2, 3, 4), l.toListBackwards());
        assertEquals(4, l.getFirst());
        l.addLast(0);
        assertEquals(List.of(4, 3, 2, 1, 0), l.toList());
        MyLinkedList<Integer> empty = new MyLinkedList<>();
        empty.reverse();
        assertEquals(0, empty.size());
    }

    @Test
    void iterates() {
        List<Integer> seen = new ArrayList<>();
        for (int x : of(3, 1, 2)) seen.add(x);
        assertEquals(List.of(3, 1, 2), seen);
    }
}
