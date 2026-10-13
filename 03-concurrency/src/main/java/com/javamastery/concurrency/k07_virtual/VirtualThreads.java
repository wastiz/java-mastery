package com.javamastery.concurrency.k07_virtual;

import java.time.Duration;
import java.util.List;
import java.util.function.Function;

/**
 * K07 · Виртуальные потоки (Java 21, Project Loom).
 */
public final class VirtualThreads {
    private VirtualThreads() {}

    /**
     * Обработать все входы «блокирующей» функцией (например, HTTP-вызов, который спит 100 мс).
     * Каждой задаче — свой виртуальный поток: Executors.newVirtualThreadPerTaskExecutor().
     * Порядок результатов = порядок входов. 10 000 задач по 100 мс должны уложиться в пару секунд.
     */
    public static <T, R> List<R> mapBlocking(List<T> inputs, Function<T, R> blockingCall) throws Exception {
        throw new UnsupportedOperationException("TODO");
    }

    /** Запустить runnable в НОВОМ виртуальном потоке и вернуть этот поток (уже запущенный). Thread.ofVirtual(). */
    public static Thread startVirtual(String name, Runnable r) {
        throw new UnsupportedOperationException("TODO");
    }

    /**
     * То же, что mapBlocking, но не более maxConcurrent одновременных вызовов (например, у внешнего API лимит).
     * Виртуальные потоки НЕ пулят — ограничение делают семафором.
     */
    public static <T, R> List<R> mapBlockingLimited(List<T> inputs, Function<T, R> blockingCall, int maxConcurrent) throws Exception {
        throw new UnsupportedOperationException("TODO");
    }

    /** Утилита для тестов: «блокирующий вызов». */
    public static void sleep(Duration d) {
        try {
            Thread.sleep(d);
        } catch (InterruptedException e) {
            Thread.currentThread().interrupt();
            throw new RuntimeException(e);
        }
    }
}
