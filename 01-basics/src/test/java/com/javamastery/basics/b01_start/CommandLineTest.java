package com.javamastery.basics.b01_start;

import org.junit.jupiter.api.Test;

import java.util.List;
import java.util.Map;

import static org.junit.jupiter.api.Assertions.*;

class CommandLineTest {

    @Test
    void parsesOptionsFlagsAndPositional() {
        var r = CommandLine.parse(new String[]{"--name=Valeri", "build", "-v", "--dry-run", "src"});
        assertEquals(Map.of("name", "Valeri", "v", "true", "dry-run", "true"), r.options());
        assertEquals(List.of("build", "src"), r.positional());
        assertTrue(r.flag("v"));
        assertFalse(r.flag("quiet"));
    }

    @Test
    void lastValueWins() {
        var r = CommandLine.parse(new String[]{"--level=1", "--level=3"});
        assertEquals("3", r.get("level", "0"));
    }

    @Test
    void valueMayContainEquals() {
        var r = CommandLine.parse(new String[]{"--expr=a=b"});
        assertEquals("a=b", r.get("expr", null));
    }

    @Test
    void doubleDashStopsOptions() {
        var r = CommandLine.parse(new String[]{"-x", "--", "--not-an-option", "-y"});
        assertEquals(Map.of("x", "true"), r.options());
        assertEquals(List.of("--not-an-option", "-y"), r.positional());
    }

    @Test
    void negativeNumberIsPositional() {
        var r = CommandLine.parse(new String[]{"-5", "-ab"});
        assertTrue(r.options().isEmpty());
        assertEquals(List.of("-5", "-ab"), r.positional());
    }

    @Test
    void emptyKeyIsRejected() {
        assertThrows(IllegalArgumentException.class, () -> CommandLine.parse(new String[]{"--=x"}));
    }

    @Test
    void emptyArgs() {
        var r = CommandLine.parse(new String[0]);
        assertTrue(r.options().isEmpty());
        assertTrue(r.positional().isEmpty());
    }
}
