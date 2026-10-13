package com.javamastery.core.c05_hashmap;

import org.junit.jupiter.api.Test;

import java.util.HashSet;
import java.util.Random;
import java.util.Set;

import static org.junit.jupiter.api.Assertions.*;

class MyHashMapTest {

    @Test
    void basicOps() {
        MyHashMap<String, Integer> m = new MyHashMap<>();
        assertTrue(m.isEmpty());
        assertNull(m.put("one", 1));
        assertNull(m.put("two", 2));
        assertEquals(1, m.put("one", 11));
        assertEquals(11, m.get("one"));
        assertEquals(2, m.size());
        assertTrue(m.containsKey("two"));
        assertFalse(m.containsKey("three"));
        assertEquals(-1, m.getOrDefault("three", -1));
        assertEquals(2, m.remove("two"));
        assertNull(m.remove("two"));
        assertEquals(1, m.size());
    }

    @Test
    void nullKeyAndValue() {
        MyHashMap<String, String> m = new MyHashMap<>();
        m.put(null, "nothing");
        m.put("k", null);
        assertEquals("nothing", m.get(null));
        assertTrue(m.containsKey("k"));
        assertNull(m.get("k"));
        assertEquals("fallback", m.getOrDefault("absent", "fallback"));
        assertNull(m.getOrDefault("k", "fallback"), "ключ есть, значение null — default не возвращаем");
        assertEquals("nothing", m.remove(null));
    }

    @Test
    void capacityIsPowerOfTwo() {
        assertEquals(16, new MyHashMap<>().capacity());
        assertEquals(32, new MyHashMap<>(17).capacity());
        assertEquals(1, new MyHashMap<>(1).capacity());
        assertThrows(IllegalArgumentException.class, () -> new MyHashMap<>(0));
    }

    @Test
    void resizeAtLoadFactor() {
        MyHashMap<Integer, Integer> m = new MyHashMap<>();
        for (int i = 0; i < 12; i++) m.put(i, i);
        assertEquals(16, m.capacity());
        m.put(12, 12);
        assertEquals(32, m.capacity());
        for (int i = 0; i <= 12; i++) assertEquals(i, m.get(i));
    }

    /** Ключи с одинаковым hashCode — все в одном бакете, но работают корректно. */
    record BadKey(int id) {
        @Override
        public int hashCode() {
            return 7;
        }
    }

    @Test
    void collisions() {
        MyHashMap<BadKey, Integer> m = new MyHashMap<>(64);
        for (int i = 0; i < 20; i++) m.put(new BadKey(i), i);
        assertEquals(20, m.size());
        assertEquals(20, m.maxChainLength());
        for (int i = 0; i < 20; i++) assertEquals(i, m.get(new BadKey(i)));
        assertEquals(10, m.remove(new BadKey(10)));
        assertNull(m.get(new BadKey(10)));
        assertEquals(19, m.size());
    }

    @Test
    void spreadHelpsWithHighBits() {
        // ключи отличаются только старшими битами hashCode — без h ^ (h >>> 16) все попали бы в один бакет
        MyHashMap<Integer, Integer> m = new MyHashMap<>(1 << 16);
        for (int i = 0; i < 16; i++) m.put(i << 16, i);
        assertTrue(m.maxChainLength() <= 2, "используй hash(key) для индекса бакета");
    }

    @Test
    void randomizedAgainstJdk() {
        Random rnd = new Random(42);
        MyHashMap<Integer, Integer> mine = new MyHashMap<>();
        java.util.HashMap<Integer, Integer> jdk = new java.util.HashMap<>();
        for (int i = 0; i < 20_000; i++) {
            int k = rnd.nextInt(2000);
            switch (rnd.nextInt(3)) {
                case 0 -> assertEquals(jdk.put(k, i), mine.put(k, i));
                case 1 -> assertEquals(jdk.remove(k), mine.remove(k));
                default -> assertEquals(jdk.get(k), mine.get(k));
            }
        }
        assertEquals(jdk.size(), mine.size());
        assertEquals(jdk.keySet(), new HashSet<>(mine.keys()));
        Set<Integer> keys = new HashSet<>(mine.keys());
        assertEquals(mine.size(), keys.size());
    }
}
