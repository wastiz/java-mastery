package com.javamastery.basics.b01_start;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.CsvSource;

import static org.junit.jupiter.api.Assertions.*;

class GreeterTest {

    @ParameterizedTest
    @CsvSource({
            "5, 'Good morning, Ann!'",
            "11, 'Good morning, Ann!'",
            "12, 'Good afternoon, Ann!'",
            "17, 'Good afternoon, Ann!'",
            "18, 'Good evening, Ann!'",
            "22, 'Good evening, Ann!'",
            "23, 'Good night, Ann!'",
            "0, 'Good night, Ann!'",
            "4, 'Good night, Ann!'"
    })
    void greetsByHour(int hour, String expected) {
        assertEquals(expected, Greeter.greet("Ann", hour));
    }

    @Test
    void trimsName() {
        assertEquals("Good morning, Ann!", Greeter.greet("  Ann ", 9));
    }

    @Test
    void blankName() {
        assertEquals("Good evening, stranger!", Greeter.greet("   ", 20));
        assertEquals("Good evening, stranger!", Greeter.greet(null, 20));
    }

    @Test
    void invalidHour() {
        assertThrows(IllegalArgumentException.class, () -> Greeter.greet("Ann", 24));
        assertThrows(IllegalArgumentException.class, () -> Greeter.greet("Ann", -1));
    }
}
