package com.javamastery.core.c01_generics;

import org.junit.jupiter.api.Test;

import java.math.BigDecimal;
import java.sql.Timestamp;
import java.util.*;

import static org.junit.jupiter.api.Assertions.*;

class GenericsTest {

    @Test
    void pair() {
        Pair<String, Integer> p = Pair.of("age", 30);
        assertEquals("(30, age)", p.swap().toString());
        Pair<Integer, Integer> lengths = p.mapFirst(String::length);
        assertEquals(Pair.of(3, 30), lengths);
        Pair<String, String> s = p.mapSecond(Object::toString);
        assertEquals("30", s.second());
        assertEquals(Pair.of("a", null), Pair.of("a", null));
        assertEquals(Pair.of("a", 1).hashCode(), Pair.of("a", 1).hashCode());
        assertNotEquals(Pair.of("a", 1), Pair.of("a", 2));
    }

    @Test
    void max() {
        assertEquals(9, GenericAlgorithms.max(List.of(3, 9, 1)));
        assertEquals("pear", GenericAlgorithms.max(Set.of("apple", "pear", "fig")));
        assertThrows(NoSuchElementException.class, () -> GenericAlgorithms.max(List.<Integer>of()));
        // Timestamp extends java.util.Date, а Comparable реализован как Comparable<Date>:
        Timestamp a = new Timestamp(1000), b = new Timestamp(5000);
        Timestamp latest = GenericAlgorithms.max(List.of(a, b));
        assertEquals(b, latest);
    }

    @Test
    void pecs() {
        List<Integer> ints = List.of(1, 2, 3);
        List<Number> numbers = new ArrayList<>(List.of(0.5));
        GenericAlgorithms.copyAll(ints, numbers);
        assertEquals(List.of(0.5, 1, 2, 3), numbers);
        List<Object> objects = new ArrayList<>();
        GenericAlgorithms.copyAll(ints, objects);
        assertEquals(3, objects.size());
    }

    @Test
    void filterWithSuperPredicate() {
        java.util.function.Predicate<Object> notNull = Objects::nonNull;
        List<String> r = GenericAlgorithms.filter(Arrays.asList("a", null, "b"), notNull);
        assertEquals(List.of("a", "b"), r);
    }

    @Test
    void groupByKeepsOrder() {
        Map<Integer, List<String>> g = GenericAlgorithms.groupBy(List.of("bb", "a", "cc", "ddd", "e"), String::length);
        assertEquals(List.of(2, 1, 3), new ArrayList<>(g.keySet()));
        assertEquals(List.of("bb", "cc"), g.get(2));
    }

    @Test
    void sum() {
        assertEquals(6.5, GenericAlgorithms.sum(List.of(1, 2L, 3.5)), 1e-9);
        assertEquals(1.25, GenericAlgorithms.sum(Set.of(new BigDecimal("1.25"))), 1e-9);
    }

    @Test
    void swap() {
        List<String> l = new ArrayList<>(List.of("x", "y", "z"));
        GenericAlgorithms.swap(l, 0, 2);
        assertEquals(List.of("z", "y", "x"), l);
    }

    @Test
    @SuppressWarnings({"unchecked", "rawtypes"})
    void typedRegistry() {
        TypedRegistry r = new TypedRegistry();
        r.put(String.class, "hello");
        r.put(Integer.class, 42);
        String s = r.get(String.class);
        int i = r.get(Integer.class);
        assertEquals("hello", s);
        assertEquals(42, i);
        assertNull(r.get(Long.class));
        assertTrue(r.contains(Integer.class));
        assertEquals(2, r.size());
        assertThrows(NullPointerException.class, () -> r.put(null, "x"));
        assertThrows(ClassCastException.class, () -> r.put((Class) Integer.class, "not an int"));
    }
}
