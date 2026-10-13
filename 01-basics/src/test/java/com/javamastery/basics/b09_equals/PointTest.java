package com.javamastery.basics.b09_equals;

import org.junit.jupiter.api.Test;

import java.util.HashMap;
import java.util.HashSet;
import java.util.Map;
import java.util.Set;

import static org.junit.jupiter.api.Assertions.*;

class PointTest {

    @Test
    void contract() {
        Point a = new Point(1, 2), b = new Point(1, 2), c = new Point(1, 2);
        assertEquals(a, a);
        assertEquals(a, b);
        assertEquals(b, a);
        assertEquals(b, c);
        assertEquals(a, c);
        assertNotEquals(a, null);
        assertNotEquals(a, "Point(1, 2)");
        assertNotEquals(a, new Point(2, 1));
        assertEquals(a.hashCode(), b.hashCode());
    }

    @Test
    void worksInHashCollections() {
        Set<Point> set = new HashSet<>();
        set.add(new Point(3, 4));
        assertTrue(set.contains(new Point(3, 4)));
        Map<Point, String> map = new HashMap<>();
        map.put(new Point(0, 0), "origin");
        assertEquals("origin", map.get(new Point(0, 0)));
    }

    @Test
    void hashSpreads() {
        Set<Integer> hashes = new HashSet<>();
        for (int x = 0; x < 30; x++) for (int y = 0; y < 30; y++) hashes.add(new Point(x, y).hashCode());
        assertTrue(hashes.size() > 800, "hashCode слишком часто совпадает: " + hashes.size() + " уникальных из 900");
    }

    @Test
    void subclassIsNotEqual() {
        // anonymous subclass is impossible because Point is final — and that is intentional
        assertTrue(java.lang.reflect.Modifier.isFinal(Point.class.getModifiers()));
    }

    @Test
    void toStringFormat() {
        assertEquals("Point(1, -2)", new Point(1, -2).toString());
    }
}
