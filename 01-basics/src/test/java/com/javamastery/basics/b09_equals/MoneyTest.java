package com.javamastery.basics.b09_equals;

import org.junit.jupiter.api.Test;

import java.math.BigDecimal;
import java.util.Arrays;
import java.util.HashSet;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

class MoneyTest {

    @Test
    void scaleIsNormalized() {
        assertEquals(Money.of("10.5", "EUR"), Money.of("10.50", "EUR"));
        assertEquals(Money.of("10.5", "EUR").hashCode(), Money.of("10.50", "EUR").hashCode());
        assertEquals(1, new HashSet<>(List.of(Money.of("1", "EUR"), Money.of("1.00", "EUR"))).size());
        assertEquals("10.50 EUR", Money.of("10.5", "EUR").toString());
    }

    @Test
    void roundingHalfEven() {
        assertEquals("0.12 EUR", Money.of("0.125", "EUR").toString());
        assertEquals("0.14 EUR", Money.of("0.135", "EUR").toString());
    }

    @Test
    void noFloatingPointErrors() {
        Money m = Money.of("0.1", "USD").plus(Money.of("0.2", "USD"));
        assertEquals(Money.of("0.3", "USD"), m);
    }

    @Test
    void arithmetic() {
        assertEquals(Money.of("7.50", "EUR"), Money.of("10", "EUR").minus(Money.of("2.5", "EUR")));
        assertEquals(Money.of("3.33", "EUR"), Money.of("10", "EUR").times(new BigDecimal("0.333")));
        assertThrows(IllegalArgumentException.class, () -> Money.of("1", "EUR").plus(Money.of("1", "USD")));
    }

    @Test
    void currencyValidation() {
        assertThrows(IllegalArgumentException.class, () -> Money.of("1", "eur"));
        assertThrows(IllegalArgumentException.class, () -> Money.of("1", "EURO"));
    }

    @Test
    void allocate() {
        Money[] parts = Money.of("10.00", "EUR").allocate(3);
        assertEquals("[3.34 EUR, 3.33 EUR, 3.33 EUR]", Arrays.toString(parts));
        Money[] cents = Money.of("0.05", "EUR").allocate(3);
        assertEquals("[0.02 EUR, 0.02 EUR, 0.01 EUR]", Arrays.toString(cents));
        Money sum = Money.of("0", "EUR");
        for (Money p : Money.of("100.01", "EUR").allocate(7)) sum = sum.plus(p);
        assertEquals(Money.of("100.01", "EUR"), sum);
    }
}
