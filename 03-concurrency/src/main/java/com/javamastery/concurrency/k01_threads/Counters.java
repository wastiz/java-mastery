package com.javamastery.concurrency.k01_threads;

/**
 * K01 · Race condition, synchronized, volatile.
 */
public final class Counters {
    private Counters() {}

    /** Счётчик, НЕ потокобезопасный. Не меняй — он для демонстрации (тест покажет, что он теряет инкременты). */
    public static class UnsafeCounter {
        private int value;

        public void increment() {
            value++;
        }

        public int get() {
            return value;
        }
    }

    /** Потокобезопасный счётчик через synchronized. */
    public static class SyncCounter {
        // TODO: поле

        public void increment() {
            throw new UnsupportedOperationException("TODO");
        }

        public int get() {
            throw new UnsupportedOperationException("TODO");
        }
    }

    /** Потокобезопасный счётчик через AtomicLong / LongAdder (без synchronized). */
    public static class AtomicCounter {
        // TODO: поле

        public void increment() {
            throw new UnsupportedOperationException("TODO");
        }

        public long get() {
            throw new UnsupportedOperationException("TODO");
        }
    }

    /**
     * Запустить {@code threads} потоков (обычные new Thread), каждый вызывает action.run() ровно {@code times} раз.
     * Дождаться завершения всех (join). Вернуть время работы в миллисекундах.
     */
    public static long runConcurrently(int threads, int times, Runnable action) throws InterruptedException {
        throw new UnsupportedOperationException("TODO");
    }
}
