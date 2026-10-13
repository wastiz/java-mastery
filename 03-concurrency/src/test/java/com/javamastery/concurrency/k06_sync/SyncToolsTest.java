package com.javamastery.concurrency.k06_sync;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.Timeout;

import java.time.Duration;
import java.util.List;
import java.util.concurrent.atomic.AtomicInteger;

import static org.junit.jupiter.api.Assertions.*;

class SyncToolsTest {

    @Test
    @Timeout(10)
    void poolLimitsAndReuses() throws Exception {
        AtomicInteger created = new AtomicInteger();
        ConnectionPool<String> pool = new ConnectionPool<>(2, () -> "conn-" + created.incrementAndGet());
        String a = pool.acquire(Duration.ofMillis(100));
        String b = pool.acquire(Duration.ofMillis(100));
        assertNotNull(a);
        assertNotNull(b);
        assertEquals(0, pool.available());
        long start = System.nanoTime();
        assertNull(pool.acquire(Duration.ofMillis(200)));
        assertTrue((System.nanoTime() - start) / 1_000_000 >= 150);
        pool.release(a);
        assertEquals(1, pool.available());
        assertEquals(a, pool.acquire(Duration.ofMillis(100)), "соединения должны переиспользоваться");
        assertEquals(2, created.get());
        assertThrows(IllegalArgumentException.class, () -> pool.release("foreign"));
        pool.release(b);
        assertThrows(IllegalArgumentException.class, () -> pool.release(b));
    }

    @Test
    @Timeout(10)
    void waitingThreadGetsReleasedConnection() throws Exception {
        ConnectionPool<Object> pool = new ConnectionPool<>(1, Object::new);
        Object c = pool.acquire(Duration.ofMillis(100));
        Object[] got = new Object[1];
        Thread t = new Thread(() -> {
            try {
                got[0] = pool.acquire(Duration.ofSeconds(5));
            } catch (InterruptedException ignored) {
            }
        });
        t.start();
        Thread.sleep(200);
        pool.release(c);
        t.join(2000);
        assertSame(c, got[0]);
    }

    @Test
    @Timeout(10)
    void raceStartsTogether() throws Exception {
        long[] starts = new long[3];
        List<Race.Runner> runners = List.of(
                new Race.Runner("slow", () -> { starts[0] = System.nanoTime(); Thread.sleep(300); return null; }),
                new Race.Runner("fast", () -> { starts[1] = System.nanoTime(); Thread.sleep(50); return null; }),
                new Race.Runner("crash", () -> { starts[2] = System.nanoTime(); throw new RuntimeException("fell"); }),
                new Race.Runner("mid", () -> { Thread.sleep(150); return null; }));
        assertEquals(List.of("fast", "mid", "slow"), Race.start(runners));
        long spread = Math.max(starts[0], Math.max(starts[1], starts[2])) - Math.min(starts[0], Math.min(starts[1], starts[2]));
        assertTrue(spread < 30_000_000, "участники должны стартовать одновременно, разброс " + spread / 1_000_000 + "ms");
    }
}
