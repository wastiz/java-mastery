package com.javamastery.core.c06_lru;

import org.junit.jupiter.api.Test;

import java.util.List;
import java.util.function.IntFunction;

import static org.junit.jupiter.api.Assertions.*;

class LruCacheTest {

    void scenario(IntFunction<LruCache<String, Integer>> factory) {
        LruCache<String, Integer> c = factory.apply(2);
        c.put("a", 1);
        c.put("b", 2);
        assertEquals(1, c.get("a"));          // a становится свежим
        c.put("c", 3);                        // вытесняет b
        assertNull(c.get("b"));
        assertEquals(List.of("a", "c"), c.keysOldestFirst());
        c.put("a", 10);                       // обновление тоже освежает
        c.put("d", 4);                        // вытесняет c
        assertEquals(List.of("a", "d"), c.keysOldestFirst());
        assertEquals(10, c.get("a"));
        assertEquals(2, c.size());
        assertNull(c.get("zzz"));
    }

    @Test
    void linkedHashMapVersion() {
        scenario(LinkedHashMapLru::new);
    }

    @Test
    void manualVersion() {
        scenario(ManualLru::new);
    }

    @Test
    void capacityOne() {
        for (IntFunction<LruCache<String, Integer>> f : List.<IntFunction<LruCache<String, Integer>>>of(LinkedHashMapLru::new, ManualLru::new)) {
            LruCache<String, Integer> c = f.apply(1);
            c.put("x", 1);
            c.put("y", 2);
            assertEquals(List.of("y"), c.keysOldestFirst());
            assertThrows(IllegalArgumentException.class, () -> f.apply(0));
        }
    }

    @Test
    void manualIsFast() {
        LruCache<Integer, Integer> c = new ManualLru<>(50_000);
        // операции должны быть O(1)
        assertTimeoutPreemptively(java.time.Duration.ofSeconds(2), () -> {
            for (int i = 0; i < 300_000; i++) {
                c.put(i, i);
                c.get(i / 2);
            }
        });
        assertEquals(50_000, c.size());
    }
}
