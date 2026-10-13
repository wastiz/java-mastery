package com.javamastery.concurrency.k03_executors;

import org.junit.jupiter.api.Test;

import java.time.Duration;
import java.util.Arrays;
import java.util.List;
import java.util.concurrent.Callable;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.stream.IntStream;

import static org.junit.jupiter.api.Assertions.*;

class ParallelTasksTest {

    @Test
    void parallelSum() throws Exception {
        int[] data = IntStream.rangeClosed(1, 1_000_001).toArray();
        assertEquals(Arrays.stream(data).asLongStream().sum(), ParallelTasks.parallelSum(data, 4));
        assertEquals(6, ParallelTasks.parallelSum(new int[]{1, 2, 3}, 8));
        assertEquals(0, ParallelTasks.parallelSum(new int[0], 2));
    }

    @Test
    void timeout() throws Exception {
        ExecutorService pool = Executors.newFixedThreadPool(4);
        try {
            List<Callable<String>> tasks = List.of(
                    () -> "fast",
                    () -> { Thread.sleep(5_000); return "slow"; },
                    () -> { throw new IllegalStateException("boom"); },
                    () -> { Thread.sleep(50); return "ok"; });
            long start = System.nanoTime();
            List<String> r = ParallelTasks.runAllWithTimeout(pool, tasks, Duration.ofMillis(500));
            long ms = (System.nanoTime() - start) / 1_000_000;
            assertEquals(Arrays.asList("fast", null, null, "ok"), r);
            assertTrue(ms < 2000, "должны вернуться по таймауту, а не ждать медленную задачу: " + ms);
        } finally {
            pool.shutdownNow();
        }
    }

    @Test
    void fastest() throws Exception {
        ExecutorService pool = Executors.newFixedThreadPool(3);
        try {
            String r = ParallelTasks.fastest(pool, List.of(
                    () -> { Thread.sleep(1000); return "turtle"; },
                    () -> { Thread.sleep(10); return "rabbit"; },
                    () -> { throw new RuntimeException("fell"); }));
            assertEquals("rabbit", r);
        } finally {
            pool.shutdownNow();
        }
    }
}
