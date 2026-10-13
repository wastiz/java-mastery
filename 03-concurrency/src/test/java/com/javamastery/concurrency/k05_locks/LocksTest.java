package com.javamastery.concurrency.k05_locks;

import org.junit.jupiter.api.Test;

import java.util.Map;
import java.util.concurrent.atomic.AtomicInteger;
import java.util.concurrent.atomic.AtomicLong;
import java.util.function.Function;

import static org.junit.jupiter.api.Assertions.*;

class LocksTest {

    static final long SEC = 1_000_000_000L;

    @Test
    void rateLimiterBurstAndRefill() {
        AtomicLong now = new AtomicLong(0);
        RateLimiter rl = new RateLimiter(3, 1.0, now::get);
        assertTrue(rl.tryAcquire());
        assertTrue(rl.tryAcquire());
        assertTrue(rl.tryAcquire());
        assertFalse(rl.tryAcquire());
        now.addAndGet(SEC / 2);
        assertFalse(rl.tryAcquire());
        now.addAndGet(SEC / 2);
        assertTrue(rl.tryAcquire());
        now.addAndGet(100 * SEC);
        int got = 0;
        while (rl.tryAcquire()) got++;
        assertEquals(3, got, "корзина не должна переполняться сверх capacity");
    }

    @Test
    void fractionalRefill() {
        AtomicLong now = new AtomicLong(0);
        RateLimiter rl = new RateLimiter(1, 0.5, now::get);
        assertTrue(rl.tryAcquire());
        now.addAndGet(SEC);
        assertFalse(rl.tryAcquire());
        now.addAndGet(SEC);
        assertTrue(rl.tryAcquire());
    }

    @Test
    void rateLimiterThreadSafe() throws Exception {
        RateLimiter rl = new RateLimiter(1000, 0.000001, () -> 0L);
        AtomicInteger ok = new AtomicInteger();
        Thread[] ts = new Thread[8];
        for (int i = 0; i < ts.length; i++) {
            ts[i] = new Thread(() -> {
                for (int j = 0; j < 1000; j++) if (rl.tryAcquire()) ok.incrementAndGet();
            });
            ts[i].start();
        }
        for (Thread t : ts) t.join();
        assertEquals(1000, ok.get());
    }

    @Test
    void wordCount() throws Exception {
        ConcurrentStats s = new ConcurrentStats();
        Thread[] ts = new Thread[8];
        for (int i = 0; i < ts.length; i++) {
            ts[i] = new Thread(() -> {
                for (int j = 0; j < 10_000; j++) s.record(j % 2 == 0 ? "even" : "odd");
            });
            ts[i].start();
        }
        for (Thread t : ts) t.join();
        assertEquals(40_000, s.count("even"));
        assertEquals(Map.of("even", 40_000L, "odd", 40_000L), s.snapshot());
        assertEquals(0, s.count("none"));
    }

    @Test
    void onceCache() throws Exception {
        AtomicInteger loads = new AtomicInteger();
        Function<String, String> cache = ConcurrentStats.onceCache(k -> {
            loads.incrementAndGet();
            try {
                Thread.sleep(50);
            } catch (InterruptedException ignored) {
            }
            return k.toUpperCase();
        });
        Thread[] ts = new Thread[20];
        for (int i = 0; i < ts.length; i++) {
            ts[i] = new Thread(() -> assertEquals("KEY", cache.apply("key")));
            ts[i].start();
        }
        for (Thread t : ts) t.join();
        assertEquals(1, loads.get());
    }

    @Test
    void configConsistency() throws Exception {
        ConcurrentStats.Config cfg = new ConcurrentStats.Config();
        cfg.putAll(Map.of("host", "a", "port", "a"));
        AtomicInteger inconsistent = new AtomicInteger();
        Thread writer = new Thread(() -> {
            for (int i = 0; i < 20_000; i++) {
                String v = String.valueOf(i);
                cfg.putAll(Map.of("host", v, "port", v));
            }
        });
        Thread reader = new Thread(() -> {
            for (int i = 0; i < 20_000; i++) {
                String[] p = cfg.getPair("host", "port");
                if (!p[0].equals(p[1])) inconsistent.incrementAndGet();
            }
        });
        writer.start();
        reader.start();
        writer.join();
        reader.join();
        assertEquals(0, inconsistent.get());
        assertEquals("19999", cfg.get("host"));
    }
}
