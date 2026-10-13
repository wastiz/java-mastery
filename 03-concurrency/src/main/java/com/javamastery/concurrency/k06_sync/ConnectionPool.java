package com.javamastery.concurrency.k06_sync;

import java.time.Duration;
import java.util.function.Supplier;

/**
 * K06 · Semaphore: пул соединений на N штук.
 *
 * <ul>
 *   <li>acquire(timeout) — взять соединение; если все заняты, ждать не дольше timeout; не дождались → null;</li>
 *   <li>release(c) — вернуть; возврат «чужого» или уже возвращённого объекта → IllegalArgumentException;</li>
 *   <li>соединения создаются лениво через factory, всего не больше size штук за всё время;</li>
 *   <li>available() — сколько можно взять прямо сейчас.</li>
 * </ul>
 */
public class ConnectionPool<C> {
    // TODO: поля

    public ConnectionPool(int size, Supplier<C> factory) {
        throw new UnsupportedOperationException("TODO");
    }

    public C acquire(Duration timeout) throws InterruptedException {
        throw new UnsupportedOperationException("TODO");
    }

    public void release(C connection) {
        throw new UnsupportedOperationException("TODO");
    }

    public int available() {
        throw new UnsupportedOperationException("TODO");
    }
}
