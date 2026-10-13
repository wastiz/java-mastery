package com.javamastery.concurrency.k03_executors;

import java.time.Duration;
import java.util.List;
import java.util.concurrent.Callable;
import java.util.concurrent.ExecutorService;

/**
 * K03 · ExecutorService, Future, invokeAll, корректное завершение пула.
 */
public final class ParallelTasks {
    private ParallelTasks() {}

    /**
     * Сумма массива: разбить на {@code parts} кусков, посчитать каждый в пуле (submit → Future), сложить результаты.
     * Пул создай сам (Executors.newFixedThreadPool) и ОБЯЗАТЕЛЬНО закрой (shutdown / try-with-resources в Java 21).
     */
    public static long parallelSum(int[] data, int parts) throws Exception {
        throw new UnsupportedOperationException("TODO");
    }

    /**
     * Выполнить задачи в переданном пуле с общим таймаутом. Вернуть результаты в том же порядке;
     * для задачи, которая не успела или упала, — null. Подсказка: invokeAll(tasks, timeout, unit).
     */
    public static <T> List<T> runAllWithTimeout(ExecutorService pool, List<Callable<T>> tasks, Duration timeout) throws InterruptedException {
        throw new UnsupportedOperationException("TODO");
    }

    /** Вернуть результат ПЕРВОЙ успешно завершившейся задачи (остальные отменяются). invokeAny. */
    public static <T> T fastest(ExecutorService pool, List<Callable<T>> tasks) throws Exception {
        throw new UnsupportedOperationException("TODO");
    }
}
