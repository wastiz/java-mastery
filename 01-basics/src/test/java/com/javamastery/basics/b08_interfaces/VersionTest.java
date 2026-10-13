package com.javamastery.basics.b08_interfaces;

import org.junit.jupiter.api.Test;

import java.util.ArrayList;
import java.util.Collections;
import java.util.HashSet;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

class VersionTest {

    @Test
    void parseAndPrint() {
        assertEquals("1.2.0", Version.parse("1.2").toString());
        assertEquals("3.0.0", Version.parse("3").toString());
        assertEquals("1.10.2", Version.parse("1.10.2").toString());
    }

    @Test
    void invalid() {
        for (String s : new String[]{"", "a.b", "1.2.3.4", "1..2", "-1.0", null}) {
            assertThrows(IllegalArgumentException.class, () -> Version.parse(s), "должно падать для " + s);
        }
    }

    @Test
    void numericOrdering() {
        List<Version> vs = new ArrayList<>();
        for (String s : new String[]{"1.10.0", "1.9.9", "1.2", "0.9.1", "1.2.0"}) vs.add(Version.parse(s));
        Collections.sort(vs);
        assertEquals("[0.9.1, 1.2.0, 1.2.0, 1.9.9, 1.10.0]", vs.toString());
    }

    @Test
    void equalsAndHash() {
        assertEquals(Version.parse("2.1"), Version.parse("2.1.0"));
        var set = new HashSet<>(List.of(Version.parse("2.1"), Version.parse("2.1.0"), Version.parse("2.1.1")));
        assertEquals(2, set.size());
        assertNotEquals(Version.parse("1"), "1.0.0");
    }
}
