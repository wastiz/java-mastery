package com.javamastery.basics.b02_primitives;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.CsvSource;

import static org.junit.jupiter.api.Assertions.*;

class PrimitivesTest {

    @Test
    void safeAddNormal() {
        assertEquals(5, Primitives.safeAdd(2, 3));
        assertEquals(-1, Primitives.safeAdd(Integer.MAX_VALUE, Integer.MIN_VALUE));
        assertEquals(Integer.MAX_VALUE, Primitives.safeAdd(Integer.MAX_VALUE - 1, 1));
    }

    @Test
    void safeAddOverflow() {
        assertThrows(ArithmeticException.class, () -> Primitives.safeAdd(Integer.MAX_VALUE, 1));
        assertThrows(ArithmeticException.class, () -> Primitives.safeAdd(Integer.MIN_VALUE, -1));
        assertThrows(ArithmeticException.class, () -> Primitives.safeAdd(1_500_000_000, 1_500_000_000));
    }

    @ParameterizedTest
    @CsvSource({"1,true", "2,true", "1024,true", "0,false", "-8,false", "6,false", "4611686018427387904,true", "4611686018427387905,false"})
    void powerOfTwo(long n, boolean expected) {
        assertEquals(expected, Primitives.isPowerOfTwo(n));
    }

    @Test
    void countBits() {
        assertEquals(0, Primitives.countBits(0));
        assertEquals(3, Primitives.countBits(0b1011_0000));
        assertEquals(32, Primitives.countBits(-1));
        assertEquals(1, Primitives.countBits(Integer.MIN_VALUE));
    }

    @Test
    void toBinary() {
        assertEquals("0", Primitives.toBinary(0));
        assertEquals("101", Primitives.toBinary(5));
        assertEquals(Integer.toBinaryString(Integer.MAX_VALUE), Primitives.toBinary(Integer.MAX_VALUE));
        assertThrows(IllegalArgumentException.class, () -> Primitives.toBinary(-1));
    }

    @Test
    void sameValueHandlesCacheAndNulls() {
        Integer a = 1000, b = 1000;
        assertTrue(Primitives.sameValue(a, b));
        assertTrue(Primitives.sameValue(127, 127));
        assertTrue(Primitives.sameValue(null, null));
        assertFalse(Primitives.sameValue(null, 1));
        assertFalse(Primitives.sameValue(1, null));
        assertFalse(Primitives.sameValue(1, 2));
    }

    @Test
    void fahrenheit() {
        assertEquals(0.0, Primitives.fahrenheitToCelsius(32));
        assertEquals(100.0, Primitives.fahrenheitToCelsius(212));
        assertEquals(37.2, Primitives.fahrenheitToCelsius(99));
        assertEquals(-17.8, Primitives.fahrenheitToCelsius(0));
        assertEquals(-40.0, Primitives.fahrenheitToCelsius(-40));
    }

    @Test
    void swapBytes() {
        assertEquals(0x3412, Primitives.swapBytes(0x1234));
        assertEquals(0x00FF, Primitives.swapBytes(0xFF00));
        assertEquals(0xFF01, Primitives.swapBytes(0x01FF));
    }
}
