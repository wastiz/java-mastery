package com.javamastery.projects.p01_minesweeper;

import java.util.Set;

/**
 * P01 · Сапёр. Мини-проект на ООП, массивы, рекурсию/BFS и enum'ы.
 * После того как тесты пройдут — запусти {@link MinesweeperConsole} и поиграй!
 *
 * <p>Правила:
 * <ul>
 *   <li>поле rows × cols, мины заданы явно (для тестов) или случайно через {@link #random};</li>
 *   <li>{@code reveal(r, c)}: открыть клетку. Мина → игра проиграна (LOST). Клетка без соседних мин (0) →
 *       открываются все соседи рекурсивно (flood fill), пока не упрёмся в клетки с числами;</li>
 *   <li>открытие уже открытой или помеченной флагом клетки — ничего не делает;</li>
 *   <li>{@code toggleFlag(r, c)}: поставить/снять флаг на закрытой клетке;</li>
 *   <li>победа (WON) — когда открыты все клетки без мин;</li>
 *   <li>после окончания игры reveal/toggleFlag бросают IllegalStateException;</li>
 *   <li>координаты вне поля → IndexOutOfBoundsException;</li>
 *   <li>{@code render()} — строки поля через '\n' (без '\n' в конце): '#' закрыто, 'F' флаг, '.' открытая пустая,
 *       '1'..'8' — число мин рядом, '*' — мина (показываются ВСЕ мины, только если игра проиграна).</li>
 * </ul>
 */
public class Minesweeper {

    public enum Status { PLAYING, WON, LOST }

    public record Cell(int row, int col) {}

    // TODO: поля: размеры, мины, открытые клетки, флаги, статус

    public Minesweeper(int rows, int cols, Set<Cell> mines) {
        throw new UnsupportedOperationException("TODO");
    }

    /** Случайное поле с заданным числом мин. */
    public static Minesweeper random(int rows, int cols, int mines, java.util.Random rnd) {
        throw new UnsupportedOperationException("TODO");
    }

    public Status status() {
        throw new UnsupportedOperationException("TODO");
    }

    /** Сколько мин вокруг клетки (8 соседей). */
    public int adjacentMines(int r, int c) {
        throw new UnsupportedOperationException("TODO");
    }

    public void reveal(int r, int c) {
        throw new UnsupportedOperationException("TODO");
    }

    public void toggleFlag(int r, int c) {
        throw new UnsupportedOperationException("TODO");
    }

    public boolean isOpen(int r, int c) {
        throw new UnsupportedOperationException("TODO");
    }

    public String render() {
        throw new UnsupportedOperationException("TODO");
    }

    // TODO: вспомогательные методы
}
