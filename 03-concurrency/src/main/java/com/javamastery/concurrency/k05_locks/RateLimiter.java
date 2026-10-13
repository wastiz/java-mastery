package com.javamastery.concurrency.k05_locks;

import java.util.function.LongSupplier;

/**
 * K05 · Token Bucket rate limiter (его часто просят на system design / coding-интервью).
 *
 * <ul>
 *   <li>корзина вмещает {@code capacity} токенов, изначально полная;</li>
 *   <li>токены пополняются со скоростью {@code refillPerSecond} (дробно — 0.5 токена за секунду тоже валидно);</li>
 *   <li>{@code tryAcquire()} — забрать 1 токен, если есть, иначе false (не блокируется);</li>
 *   <li>время берётся из clock (наносекунды) — чтобы тест мог «перематывать» время;</li>
 *   <li>потокобезопасность: используй ReentrantLock (а не synchronized) — потренируйся с try/finally.</li>
 * </ul>
 */
public class RateLimiter {
    // TODO: поля

    public RateLimiter(int capacity, double refillPerSecond, LongSupplier nanoClock) {
        throw new UnsupportedOperationException("TODO");
    }

    public boolean tryAcquire() {
        throw new UnsupportedOperationException("TODO");
    }
}
