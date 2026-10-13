package com.javamastery.concurrency.k01_threads;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.Timeout;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

class ThreadsTest {

    @Test
    void unsafeCounterLosesUpdates() throws Exception {
        // демонстрация: обычно теряется часть инкрементов (тест не падает, если повезло)
        Counters.UnsafeCounter c = new Counters.UnsafeCounter();
        Counters.runConcurrently(8, 100_000, c::increment);
        System.out.println("UnsafeCounter: ожидали 800000, получили " + c.get());
        assertTrue(c.get() <= 800_000);
    }

    @Test
    void syncCounter() throws Exception {
        Counters.SyncCounter c = new Counters.SyncCounter();
        Counters.runConcurrently(8, 100_000, c::increment);
        assertEquals(800_000, c.get());
    }

    @Test
    void atomicCounter() throws Exception {
        Counters.AtomicCounter c = new Counters.AtomicCounter();
        Counters.runConcurrently(8, 100_000, c::increment);
        assertEquals(800_000, c.get());
    }

    @Test
    void runConcurrentlyRunsInParallel() throws Exception {
        List<String> names = Collections.synchronizedList(new ArrayList<>());
        long ms = Counters.runConcurrently(4, 1, () -> {
            names.add(Thread.currentThread().getName());
            try {
                Thread.sleep(200);
            } catch (InterruptedException e) {
                throw new RuntimeException(e);
            }
        });
        assertEquals(4, names.stream().distinct().count());
        assertTrue(ms < 700, "потоки должны работать параллельно, а не по очереди: " + ms + "ms");
    }

    @Test
    @Timeout(5)
    void workerStops() throws Exception {
        StoppableWorker w = new StoppableWorker();
        Thread t = new Thread(w);
        t.start();
        Thread.sleep(100);
        w.stop();
        t.join(2000);
        assertFalse(t.isAlive(), "поток не увидел флаг остановки");
        assertTrue(w.iterations() > 0);
    }

    @Test
    @Timeout(10)
    void boundedBufferProducerConsumer() throws Exception {
        BoundedBuffer<Integer> buf = new BoundedBuffer<>(3);
        List<Integer> consumed = Collections.synchronizedList(new ArrayList<>());
        Thread producer = new Thread(() -> {
            try {
                for (int i = 0; i < 1000; i++) buf.put(i);
            } catch (InterruptedException ignored) {
            }
        });
        Thread consumer = new Thread(() -> {
            try {
                for (int i = 0; i < 1000; i++) consumed.add(buf.take());
            } catch (InterruptedException ignored) {
            }
        });
        producer.start();
        consumer.start();
        producer.join();
        consumer.join();
        assertEquals(1000, consumed.size());
        for (int i = 0; i < 1000; i++) assertEquals(i, consumed.get(i));
        assertEquals(0, buf.size());
    }

    @Test
    @Timeout(5)
    void putBlocksWhenFull() throws Exception {
        BoundedBuffer<String> buf = new BoundedBuffer<>(1);
        buf.put("a");
        Thread t = new Thread(() -> {
            try {
                buf.put("b");
            } catch (InterruptedException ignored) {
            }
        });
        t.start();
        Thread.sleep(200);
        assertEquals(Thread.State.WAITING, t.getState(), "put в полный буфер должен ждать (wait)");
        assertEquals("a", buf.take());
        t.join(1000);
        assertEquals("b", buf.take());
    }
}
