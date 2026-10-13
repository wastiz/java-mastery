package com.javamastery.core.c03_arraylist;

import org.junit.jupiter.api.Test;

import java.lang.ref.WeakReference;
import java.util.ArrayList;
import java.util.ConcurrentModificationException;
import java.util.Iterator;
import java.util.List;
import java.util.NoSuchElementException;

import static org.junit.jupiter.api.Assertions.*;

class MyArrayListTest {

    @Test
    void addGetSize() {
        MyArrayList<String> l = new MyArrayList<>();
        assertTrue(l.isEmpty());
        l.add("a");
        l.add("c");
        l.add(1, "b");
        l.add(0, "start");
        assertEquals(4, l.size());
        assertEquals("[start, a, b, c]", l.toString());
        assertEquals("b", l.get(2));
        assertEquals("b", l.set(2, "B"));
        assertEquals("B", l.get(2));
    }

    @Test
    void bounds() {
        MyArrayList<Integer> l = new MyArrayList<>();
        l.add(1);
        assertThrows(IndexOutOfBoundsException.class, () -> l.get(1));
        assertThrows(IndexOutOfBoundsException.class, () -> l.get(-1));
        assertThrows(IndexOutOfBoundsException.class, () -> l.add(3, 5));
        assertThrows(IndexOutOfBoundsException.class, () -> l.remove(1));
        assertThrows(IllegalArgumentException.class, () -> new MyArrayList<>(-1));
    }

    @Test
    void growsByOneAndAHalf() {
        MyArrayList<Integer> l = new MyArrayList<>();
        assertEquals(10, l.capacity());
        for (int i = 0; i < 11; i++) l.add(i);
        assertEquals(15, l.capacity());
        for (int i = 0; i < 5; i++) l.add(i);
        assertEquals(22, l.capacity());
        MyArrayList<Integer> zero = new MyArrayList<>(0);
        zero.add(1);
        assertTrue(zero.capacity() >= 1);
    }

    @Test
    void removal() {
        MyArrayList<String> l = new MyArrayList<>();
        for (String s : new String[]{"a", "b", null, "c", "b"}) l.add(s);
        assertEquals("b", l.remove(1));
        assertTrue(l.remove(null));
        assertTrue(l.remove("b"));
        assertFalse(l.remove("zzz"));
        assertEquals("[a, c]", l.toString());
        assertEquals(1, l.indexOf("c"));
        assertTrue(l.contains("a"));
        assertNull(l.rawSlot(2), "освобождённая ячейка должна обнуляться");
        assertNull(l.rawSlot(3));
    }

    @Test
    void removeReleasesReference() throws Exception {
        MyArrayList<Object> l = new MyArrayList<>();
        Object big = new byte[1024];
        WeakReference<Object> ref = new WeakReference<>(big);
        l.add(big);
        l.remove(0);
        big = null;
        for (int i = 0; i < 50 && ref.get() != null; i++) {
            System.gc();
            Thread.sleep(10);
        }
        assertNull(ref.get(), "список всё ещё держит ссылку на удалённый объект");
    }

    @Test
    void clearAndTrim() {
        MyArrayList<Integer> l = new MyArrayList<>(100);
        for (int i = 0; i < 5; i++) l.add(i);
        l.trimToSize();
        assertEquals(5, l.capacity());
        l.ensureCapacity(50);
        assertTrue(l.capacity() >= 50);
        l.clear();
        assertEquals(0, l.size());
        assertNull(l.rawSlot(0));
    }

    @Test
    void iteration() {
        MyArrayList<Integer> l = new MyArrayList<>();
        for (int i = 1; i <= 5; i++) l.add(i);
        List<Integer> seen = new ArrayList<>();
        for (int x : l) seen.add(x);
        assertEquals(List.of(1, 2, 3, 4, 5), seen);
        Iterator<Integer> it = l.iterator();
        while (it.hasNext()) if (it.next() % 2 == 0) it.remove();
        assertEquals("[1, 3, 5]", l.toString());
        assertThrows(NoSuchElementException.class, it::next);
    }

    @Test
    void iteratorRemoveTwice() {
        MyArrayList<Integer> l = new MyArrayList<>();
        l.add(1);
        l.add(2);
        Iterator<Integer> it = l.iterator();
        assertThrows(IllegalStateException.class, it::remove);
        it.next();
        it.remove();
        assertThrows(IllegalStateException.class, it::remove);
    }

    @Test
    void failFast() {
        MyArrayList<Integer> l = new MyArrayList<>();
        for (int i = 0; i < 5; i++) l.add(i);
        assertThrows(ConcurrentModificationException.class, () -> {
            for (Integer x : l) if (x == 2) l.add(99);
        });
        assertThrows(ConcurrentModificationException.class, () -> {
            for (Integer x : l) if (x == 1) l.remove(0);
        });
    }
}
