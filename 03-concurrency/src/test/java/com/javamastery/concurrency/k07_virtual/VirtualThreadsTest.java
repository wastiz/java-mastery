package com.javamastery.concurrency.k07_virtual;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.Timeout;

import java.time.Duration;
import java.util.List;
import java.util.concurrent.atomic.AtomicInteger;
import java.util.stream.IntStream;

import static org.junit.jupiter.api.Assertions.*;

class VirtualThreadsTest {

    @Test
    @Timeout(15)
    void tenThousandBlockingCalls() throws Exception {
        List<Integer> inputs = IntStream.range(0, 10_000).boxed().toList();
        long start = System.nanoTime();
        List<Integer> out = VirtualThreads.mapBlocking(inputs, x -> {
            VirtualThreads.sleep(Duration.ofMillis(100));
            return x * 2;
        });
        long ms = (System.nanoTime() - start) / 1_000_000;
        assertEquals(10_000, out.size());
        assertEquals(19_998, out.get(9_999));
        assertTrue(ms < 5_000, "10k задач по 100мс на виртуальных потоках должны занять ~секунду, а заняли " + ms + "ms");
    }

    @Test
    void startVirtual() throws Exception {
        boolean[] virtual = new boolean[1];
        Thread t = VirtualThreads.startVirtual("worker-1", () -> virtual[0] = Thread.currentThread().isVirtual());
        t.join();
        assertTrue(virtual[0]);
        assertEquals("worker-1", t.getName());
    }

    @Test
    @Timeout(15)
    void limited() throws Exception {
        AtomicInteger current = new AtomicInteger(), peak = new AtomicInteger();
        List<Integer> out = VirtualThreads.mapBlockingLimited(IntStream.range(0, 200).boxed().toList(), x -> {
            int c = current.incrementAndGet();
            peak.accumulateAndGet(c, Math::max);
            VirtualThreads.sleep(Duration.ofMillis(20));
            current.decrementAndGet();
            return x;
        }, 10);
        assertEquals(200, out.size());
        assertEquals(199, out.get(199));
        assertTrue(peak.get() <= 10, "одновременно выполнялось " + peak.get() + " > 10");
        assertTrue(peak.get() >= 5, "ограничение слишком жёсткое: " + peak.get());
    }
}
