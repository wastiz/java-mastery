package com.javamastery.basics.b03_control;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.CsvSource;

import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

class ControlFlowTest {

    @Test
    void fizzBuzz() {
        List<String> r = ControlFlow.fizzBuzz(30);
        assertEquals(30, r.size());
        assertEquals("1", r.get(0));
        assertEquals("Fizz", r.get(2));
        assertEquals("Buzz", r.get(4));
        assertEquals("Lucky", r.get(6));   // 7
        assertEquals("FizzBuzz", r.get(14));
        assertEquals("Lucky", r.get(16));  // 17
        assertEquals("Lucky", r.get(26));  // 27 делится на 3, но Lucky важнее
        assertEquals("FizzBuzz", r.get(29));
    }

    @Test
    void fizzBuzzEmpty() {
        assertTrue(ControlFlow.fizzBuzz(0).isEmpty());
    }

    @ParameterizedTest
    @CsvSource({"mon,WORKDAY", "FRI,WORKDAY", "Sat,WEEKEND", "sun,WEEKEND", "holiday,UNKNOWN"})
    void dayType(String day, ControlFlow.DayType expected) {
        assertEquals(expected, ControlFlow.dayType(day));
    }

    @Test
    void dayTypeNull() {
        assertEquals(ControlFlow.DayType.UNKNOWN, ControlFlow.dayType(null));
    }

    @Test
    void collatz() {
        assertEquals(0, ControlFlow.collatzSteps(1));
        assertEquals(8, ControlFlow.collatzSteps(6));
        assertEquals(111, ControlFlow.collatzSteps(27));
        // промежуточные значения выходят за пределы int
        assertEquals(986, ControlFlow.collatzSteps(670_617_279L));
        assertThrows(IllegalArgumentException.class, () -> ControlFlow.collatzSteps(0));
    }

    @Test
    void findAboveDiagonal() {
        int[][] m = {
                {5, 1, 2},
                {9, 5, 7},
                {5, 5, 5}
        };
        assertArrayEquals(new int[]{1, 2}, ControlFlow.findAboveDiagonal(m, 7));
        assertArrayEquals(new int[]{-1, -1}, ControlFlow.findAboveDiagonal(m, 5));
        assertArrayEquals(new int[]{0, 1}, ControlFlow.findAboveDiagonal(m, 1));
    }

    @ParameterizedTest
    @CsvSource({"2024,true", "2023,false", "1900,false", "2000,true", "2100,false"})
    void leap(int year, boolean expected) {
        assertEquals(expected, ControlFlow.isLeap(year));
    }
}
