package com.javamastery.core.c07_queues;

import java.util.List;

/**
 * C07 · PriorityQueue: планировщик задач.
 *
 * <p>Задачи с приоритетом (больше — важнее). Среди равных приоритетов — FIFO (кто раньше добавлен).
 * PriorityQueue сам по себе НЕ стабилен — подумай, как обеспечить FIFO.
 * poll() на пустом → null. drain() — забрать все задачи в порядке выполнения.
 */
public class TaskScheduler {

    public record Task(String name, int priority) {}

    // TODO: очередь и счётчик последовательности

    public void submit(String name, int priority) {
        throw new UnsupportedOperationException("TODO");
    }

    public Task poll() {
        throw new UnsupportedOperationException("TODO");
    }

    public int size() {
        throw new UnsupportedOperationException("TODO");
    }

    public List<Task> drain() {
        throw new UnsupportedOperationException("TODO");
    }
}
