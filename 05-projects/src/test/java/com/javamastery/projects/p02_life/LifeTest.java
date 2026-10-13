package com.javamastery.projects.p02_life;

import org.junit.jupiter.api.Test;

import java.util.HashSet;
import java.util.Set;

import static org.junit.jupiter.api.Assertions.*;

class LifeTest {

    @Test
    void blinker() {
        Life l = Life.parse(".....\n..O..\n..O..\n..O..\n.....", false);
        Life n = l.next();
        assertEquals(".....\n.....\n.OOO.\n.....\n.....", n.toString());
        assertEquals(l, n.next());
        assertEquals(2, l.periodOf(10));
        assertEquals(3, n.population());
    }

    @Test
    void immutable() {
        Life l = Life.parse("...\nOOO\n...", false);
        String before = l.toString();
        l.next();
        assertEquals(before, l.toString());
    }

    @Test
    void block() {
        Life block = Life.parse("....\n.OO.\n.OO.\n....", false);
        assertEquals(block, block.next());
        assertEquals(1, block.periodOf(5));
    }

    @Test
    void gliderWrapsAround() {
        Life g = Life.parse(".O....\n..O...\nOOO...\n......\n......\n......", true);
        Life after = g.step(24);   // глайдер проходит тор 6x6 за 24 поколения
        assertEquals(g, after);
        assertEquals(5, after.population());
    }

    @Test
    void gliderDiesAtEdgeWithoutWrap() {
        Life g = Life.parse(".O..\n..O.\nOOO.\n....", false);
        Life later = g.step(20);
        assertEquals(1, later.periodOf(3)); // превратился в «блок»
        assertEquals(4, later.population());
    }

    @Test
    void equalsAndHash() {
        Set<Life> set = new HashSet<>();
        set.add(Life.parse("O.\n.O", false));
        set.add(Life.parse("O.\n.O", false));
        set.add(Life.parse("O.\n.O", true));
        assertEquals(2, set.size());
        assertNotEquals(Life.parse("O.", false), Life.parse("O..", false));
    }

    @Test
    void noRepeat() {
        // «r-пентамино» долго не стабилизируется
        Life r = Life.parse(String.join("\n",
                "..........", "..........", "..........", ".....OO...", "....OO....", ".....O....",
                "..........", "..........", "..........", ".........."), false);
        assertEquals(-1, r.periodOf(3));
    }
}
