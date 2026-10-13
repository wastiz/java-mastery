package com.javamastery.basics.b09_equals;

import java.math.BigDecimal;

/**
 * B09 · Деньги. Почему нельзя хранить деньги в double? (0.1 + 0.2 != 0.3)
 *
 * <ul>
 *   <li>сумма — BigDecimal со scale = 2 (округление HALF_EVEN, «банковское»);</li>
 *   <li>валюта — 3 заглавные буквы ("EUR"), иначе IllegalArgumentException;</li>
 *   <li>Money.of("10.5", "EUR") равен Money.of("10.50", "EUR") — подумай, как BigDecimal.equals сравнивает scale;</li>
 *   <li>plus/minus для разных валют → IllegalArgumentException;</li>
 *   <li>allocate(n) — разделить сумму на n частей так, чтобы сумма частей точно равнялась исходной
 *       (10.00 / 3 → 3.34, 3.33, 3.33 — лишние центы отдаются первым частям);</li>
 *   <li>toString() → "10.50 EUR".</li>
 * </ul>
 */
public final class Money {
    // TODO: поля

    private Money(BigDecimal amount, String currency) {
        throw new UnsupportedOperationException("TODO");
    }

    public static Money of(String amount, String currency) {
        throw new UnsupportedOperationException("TODO");
    }

    public static Money of(BigDecimal amount, String currency) {
        throw new UnsupportedOperationException("TODO");
    }

    public BigDecimal amount() {
        throw new UnsupportedOperationException("TODO");
    }

    public String currency() {
        throw new UnsupportedOperationException("TODO");
    }

    public Money plus(Money other) {
        throw new UnsupportedOperationException("TODO");
    }

    public Money minus(Money other) {
        throw new UnsupportedOperationException("TODO");
    }

    public Money times(BigDecimal factor) {
        throw new UnsupportedOperationException("TODO");
    }

    public Money[] allocate(int parts) {
        throw new UnsupportedOperationException("TODO");
    }

    // TODO: вспомогательные методы

    @Override
    public boolean equals(Object o) {
        throw new UnsupportedOperationException("TODO");
    }

    @Override
    public int hashCode() {
        throw new UnsupportedOperationException("TODO");
    }

    @Override
    public String toString() {
        throw new UnsupportedOperationException("TODO");
    }
}
