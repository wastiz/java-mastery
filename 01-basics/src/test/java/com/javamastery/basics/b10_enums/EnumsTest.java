package com.javamastery.basics.b10_enums;

import org.junit.jupiter.api.Test;

import java.util.EnumSet;
import java.util.Set;

import static com.javamastery.basics.b10_enums.Permissions.Permission.*;
import static org.junit.jupiter.api.Assertions.*;

class EnumsTest {

    @Test
    void operations() {
        assertEquals(5, Operation.PLUS.apply(2, 3));
        assertEquals(-1, Operation.MINUS.apply(2, 3));
        assertEquals(6, Operation.MULTIPLY.apply(2, 3));
        assertEquals(3, Operation.DIVIDE.apply(7, 2));
        assertEquals(1024, Operation.POWER.apply(2, 10));
        assertEquals(1, Operation.POWER.apply(5, 0));
        assertThrows(ArithmeticException.class, () -> Operation.DIVIDE.apply(1, 0));
        assertThrows(IllegalArgumentException.class, () -> Operation.POWER.apply(2, -1));
    }

    @Test
    void fromSymbol() {
        assertEquals(Operation.POWER, Operation.fromSymbol("^"));
        assertEquals("*", Operation.MULTIPLY.symbol());
        assertThrows(IllegalArgumentException.class, () -> Operation.fromSymbol("%"));
        assertEquals(5, Operation.values().length);
    }

    @Test
    void trafficLight() {
        assertEquals(TrafficLight.RED_YELLOW, TrafficLight.RED.next());
        assertEquals(TrafficLight.RED, TrafficLight.YELLOW.next());
        assertTrue(TrafficLight.GREEN.canGo());
        assertFalse(TrafficLight.RED_YELLOW.canGo());
        assertEquals(30, TrafficLight.RED.durationSeconds());
    }

    @Test
    void stateAfter() {
        assertEquals(TrafficLight.RED, TrafficLight.RED.stateAfter(0));
        assertEquals(TrafficLight.RED, TrafficLight.RED.stateAfter(29));
        assertEquals(TrafficLight.RED_YELLOW, TrafficLight.RED.stateAfter(30));
        assertEquals(TrafficLight.GREEN, TrafficLight.RED.stateAfter(32));
        assertEquals(TrafficLight.YELLOW, TrafficLight.RED.stateAfter(57));
        assertEquals(TrafficLight.RED, TrafficLight.RED.stateAfter(60));
        assertEquals(TrafficLight.GREEN, TrafficLight.YELLOW.stateAfter(3 + 30 + 2 + 60L * 1_000_000));
    }

    @Test
    void permissions() {
        assertEquals(EnumSet.of(READ, EXECUTE), Permissions.parse("r-x"));
        assertEquals(EnumSet.noneOf(Permissions.Permission.class), Permissions.parse("---"));
        assertThrows(IllegalArgumentException.class, () -> Permissions.parse("rxw"));
        assertThrows(IllegalArgumentException.class, () -> Permissions.parse("rw"));
        assertEquals("rw-", Permissions.format(Set.of(WRITE, READ)));
        assertEquals(7, Permissions.toOctalDigit(Permissions.parse("rwx")));
        assertEquals(5, Permissions.toOctalDigit(Permissions.parse("r-x")));
        assertEquals(0, Permissions.toOctalDigit(Set.of()));
    }

    @Test
    void parseReturnsEnumSet() {
        assertInstanceOf(EnumSet.class, Permissions.parse("rw-"));
    }
}
