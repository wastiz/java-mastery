package com.javamastery.concurrency.k01_threads;

/**
 * K01 · Producer-Consumer на «голых» wait()/notifyAll() — классика собеседований.
 *
 * <ul>
 *   <li>put блокируется, пока буфер полон; take — пока пуст;</li>
 *   <li>порядок FIFO;</li>
 *   <li>используй ТОЛЬКО synchronized + wait/notifyAll (никаких BlockingQueue и Lock);</li>
 *   <li>помни про spurious wakeups — wait всегда в цикле while, а не в if.</li>
 * </ul>
 */
public class BoundedBuffer<T> {
    // TODO: поля

    public BoundedBuffer(int capacity) {
        throw new UnsupportedOperationException("TODO");
    }

    public void put(T item) throws InterruptedException {
        throw new UnsupportedOperationException("TODO");
    }

    @SuppressWarnings("unchecked")
    public T take() throws InterruptedException {
        throw new UnsupportedOperationException("TODO");
    }

    public synchronized int size() {
        throw new UnsupportedOperationException("TODO");
    }
}
