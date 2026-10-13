package com.javamastery.projects.p03_snake;

import java.util.List;
import java.util.function.Supplier;

/**
 * P03 · Движок «Змейки». Deque, record, enum, инъекция зависимостей для тестируемости.
 *
 * <ul>
 *   <li>поле width × height, змейка изначально длины 3: голова в (startX, startY), тело тянется влево
 *       (startX-1, startY), (startX-2, startY); направление — RIGHT;</li>
 *   <li>{@code turn(dir)}: разворот на 180° (RIGHT → LEFT) игнорируется; поворот применяется на следующем tick();
 *       если между тиками вызвать turn несколько раз — действует последний валидный (относительно текущего направления движения!);</li>
 *   <li>{@code tick()}: голова сдвигается; если на клетке еда — змейка растёт (хвост не убирается), score++, еда появляется
 *       в новом месте (берётся из foodSpawner, пока не выпадет свободная клетка); врезалась в стену или в себя → DEAD;</li>
 *   <li>движение в клетку, где СЕЙЧАС хвост, разрешено (хвост успевает уйти), если змейка в этот ход не растёт;</li>
 *   <li>после смерти tick() ничего не делает и возвращает DEAD.</li>
 * </ul>
 */
public class SnakeGame {

    public enum Direction {
        UP(0, -1), DOWN(0, 1), LEFT(-1, 0), RIGHT(1, 0);

        public final int dx, dy;

        Direction(int dx, int dy) {
            this.dx = dx;
            this.dy = dy;
        }

        public boolean isOpposite(Direction o) {
            return dx + o.dx == 0 && dy + o.dy == 0;
        }
    }

    public record Point(int x, int y) {}

    public enum Event { MOVED, ATE, DEAD }

    // TODO: поля

    public SnakeGame(int width, int height, int startX, int startY, Supplier<Point> foodSpawner) {
        throw new UnsupportedOperationException("TODO");
    }

    public void turn(Direction d) {
        throw new UnsupportedOperationException("TODO");
    }

    public Event tick() {
        throw new UnsupportedOperationException("TODO");
    }

    /** Клетки змейки от головы к хвосту. */
    public List<Point> snake() {
        throw new UnsupportedOperationException("TODO");
    }

    public Point food() {
        throw new UnsupportedOperationException("TODO");
    }

    public int score() {
        throw new UnsupportedOperationException("TODO");
    }

    public boolean isDead() {
        throw new UnsupportedOperationException("TODO");
    }

    // TODO: вспомогательные методы
}
