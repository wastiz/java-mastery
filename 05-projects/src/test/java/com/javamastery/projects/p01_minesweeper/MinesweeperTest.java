package com.javamastery.projects.p01_minesweeper;

import com.javamastery.projects.p01_minesweeper.Minesweeper.Cell;
import com.javamastery.projects.p01_minesweeper.Minesweeper.Status;
import org.junit.jupiter.api.Test;

import java.util.Random;
import java.util.Set;

import static org.junit.jupiter.api.Assertions.*;

class MinesweeperTest {

    // мины в (0,3) и (2,1)
    Minesweeper game() {
        return new Minesweeper(4, 5, Set.of(new Cell(0, 3), new Cell(2, 1)));
    }

    @Test
    void counts() {
        Minesweeper g = game();
        assertEquals(1, g.adjacentMines(0, 2));
        assertEquals(2, g.adjacentMines(1, 2));
        assertEquals(0, g.adjacentMines(3, 4));
        assertEquals(1, g.adjacentMines(3, 0));
    }

    @Test
    void floodFill() {
        Minesweeper g = game();
        g.reveal(3, 4);
        assertEquals(String.join("\n",
                "#####",
                "##211",
                "##1..",
                "##1.."), g.render());
        assertEquals(Status.PLAYING, g.status());
    }

    @Test
    void numberCellOpensOnlyItself() {
        Minesweeper g = game();
        g.reveal(1, 2);
        assertEquals(String.join("\n", "#####", "##2##", "#####", "#####"), g.render());
    }

    @Test
    void flags() {
        Minesweeper g = game();
        g.toggleFlag(0, 0);
        g.reveal(0, 0);
        assertFalse(g.isOpen(0, 0), "клетка с флагом не открывается");
        assertTrue(g.render().startsWith("F"));
        g.toggleFlag(0, 0);
        assertTrue(g.render().startsWith("#"));
    }

    @Test
    void loseShowsMines() {
        Minesweeper g = game();
        g.reveal(3, 4);
        g.reveal(2, 1);
        assertEquals(Status.LOST, g.status());
        assertEquals(String.join("\n",
                "###*#",
                "##211",
                "#*1..",
                "##1.."), g.render());
        assertThrows(IllegalStateException.class, () -> g.reveal(0, 0));
        assertThrows(IllegalStateException.class, () -> g.toggleFlag(0, 0));
    }

    @Test
    void win() {
        Minesweeper g = game();
        for (int r = 0; r < 4; r++)
            for (int c = 0; c < 5; c++)
                if (!(r == 0 && c == 3) && !(r == 2 && c == 1) && g.status() == Status.PLAYING) g.reveal(r, c);
        assertEquals(Status.WON, g.status());
    }

    @Test
    void bounds() {
        assertThrows(IndexOutOfBoundsException.class, () -> game().reveal(4, 0));
        assertThrows(IndexOutOfBoundsException.class, () -> game().toggleFlag(0, -1));
    }

    @Test
    void randomBoard() {
        Minesweeper g = Minesweeper.random(9, 9, 10, new Random(1));
        // открываем клетки подряд, пока не наткнёмся на мину — тогда render покажет все мины
        outer:
        for (int r = 0; r < 9; r++)
            for (int c = 0; c < 9; c++)
                if (!g.isOpen(r, c)) {
                    g.reveal(r, c);
                    if (g.status() != Status.PLAYING) break outer;
                }
        assertEquals(Status.LOST, g.status());
        long mines = g.render().chars().filter(ch -> ch == '*').count();
        assertEquals(10, mines);
        assertThrows(IllegalArgumentException.class, () -> Minesweeper.random(2, 2, 4, new Random()));
    }

    @Test
    void bigEmptyBoardNoStackOverflow() {
        Minesweeper g = new Minesweeper(1000, 1000, Set.of(new Cell(999, 999)));
        g.reveal(0, 0);
        assertEquals(Status.WON, g.status());
    }
}
