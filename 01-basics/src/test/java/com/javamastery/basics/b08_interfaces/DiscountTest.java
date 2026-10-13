package com.javamastery.basics.b08_interfaces;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class DiscountTest {

    @Test
    void basic() {
        assertEquals(1000, Discount.none().apply(1000));
        assertEquals(900, Discount.percent(10).apply(1000));
    }

    @Test
    void percentRounding() {
        assertEquals(669, Discount.percent(33).apply(999));
        assertEquals(0, Discount.percent(100).apply(12345));
        assertThrows(IllegalArgumentException.class, () -> Discount.percent(101));
        assertThrows(IllegalArgumentException.class, () -> Discount.percent(-1));
    }

    @Test
    void fixedNeverNegative() {
        assertEquals(500, Discount.fixed(500).apply(1000));
        assertEquals(0, Discount.fixed(500).apply(300));
    }

    @Test
    void chain() {
        Discount d = Discount.percent(10).andThen(Discount.fixed(100));
        assertEquals(800, d.apply(1000));
        Discount reversed = Discount.fixed(100).andThen(Discount.percent(10));
        assertEquals(810, reversed.apply(1000));
    }

    @Test
    void threshold() {
        Discount d = Discount.ifAtLeast(5000, Discount.percent(20));
        assertEquals(4999, d.apply(4999));
        assertEquals(4000, d.apply(5000));
    }

    @Test
    void best() {
        Discount d = Discount.best(Discount.percent(10), Discount.fixed(300));
        assertEquals(700, d.apply(1000));
        assertEquals(9000, d.apply(10000));
        assertEquals(50, Discount.best().apply(50));
    }

    @Test
    void lambdaIsADiscount() {
        Discount half = p -> p / 2;
        assertEquals(250, half.andThen(Discount.none()).apply(500));
    }
}
