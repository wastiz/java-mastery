package com.javamastery.basics.b11_records;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class RangeTest {

    @Test
    void validation() {
        assertThrows(IllegalArgumentException.class, () -> new Range(5, 1));
        assertDoesNotThrow(() -> Range.of(3, 3));
    }

    @Test
    void basics() {
        Range r = Range.of(2, 5);
        assertEquals(3, r.length());
        assertTrue(r.contains(2));
        assertTrue(r.contains(4));
        assertFalse(r.contains(5));
        assertTrue(Range.of(1, 1).isEmpty());
    }

    @Test
    void overlaps() {
        assertTrue(Range.of(0, 5).overlaps(Range.of(4, 10)));
        assertFalse(Range.of(0, 5).overlaps(Range.of(5, 10)));
        assertFalse(Range.of(3, 3).overlaps(Range.of(0, 10)));
    }

    @Test
    void intersect() {
        assertEquals(Range.of(4, 5), Range.of(0, 5).intersect(Range.of(4, 10)));
        assertEquals(Range.of(0, 0), Range.of(0, 5).intersect(Range.of(7, 10)));
        assertEquals(Range.of(2, 3), Range.of(2, 3).intersect(Range.of(0, 100)));
    }

    @Test
    void isRecord() {
        assertTrue(Range.class.isRecord());
    }
}
