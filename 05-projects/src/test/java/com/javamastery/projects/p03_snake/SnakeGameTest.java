package com.javamastery.projects.p03_snake;

import com.javamastery.projects.p03_snake.SnakeGame.*;
import org.junit.jupiter.api.Test;

import java.util.ArrayDeque;
import java.util.List;
import java.util.function.Supplier;

import static org.junit.jupiter.api.Assertions.*;

class SnakeGameTest {

    static Supplier<Point> foods(Point... ps) {
        ArrayDeque<Point> q = new ArrayDeque<>(List.of(ps));
        return () -> q.isEmpty() ? new Point(0, 9) : q.poll();
    }

    @Test
    void initialState() {
        SnakeGame g = new SnakeGame(10, 10, 5, 5, foods(new Point(9, 9)));
        assertEquals(List.of(new Point(5, 5), new Point(4, 5), new Point(3, 5)), g.snake());
        assertEquals(new Point(9, 9), g.food());
        assertEquals(0, g.score());
    }

    @Test
    void moveAndTurn() {
        SnakeGame g = new SnakeGame(10, 10, 5, 5, foods(new Point(9, 9)));
        assertEquals(Event.MOVED, g.tick());
        assertEquals(new Point(6, 5), g.snake().get(0));
        g.turn(Direction.LEFT);  // 180° — игнор
        g.tick();
        assertEquals(new Point(7, 5), g.snake().get(0));
        g.turn(Direction.UP);
        g.tick();
        assertEquals(List.of(new Point(7, 4), new Point(7, 5), new Point(6, 5)), g.snake());
    }

    @Test
    void quickDoubleTurnCannotReverse() {
        SnakeGame g = new SnakeGame(10, 10, 5, 5, foods(new Point(9, 9)));
        g.turn(Direction.UP);
        g.turn(Direction.LEFT);   // относительно ТЕКУЩЕГО направления (RIGHT) — это разворот, игнор
        g.tick();
        assertEquals(new Point(5, 4), g.snake().get(0));
    }

    @Test
    void eatAndGrow() {
        SnakeGame g = new SnakeGame(10, 10, 5, 5, foods(new Point(6, 5), new Point(4, 5), new Point(1, 1)));
        assertEquals(Event.ATE, g.tick());
        assertEquals(1, g.score());
        assertEquals(4, g.snake().size());
        assertEquals(new Point(1, 1), g.food(), "еда не должна появляться на змейке — (4,5) занята");
        g.tick();
        assertEquals(4, g.snake().size());
    }

    @Test
    void wallKills() {
        SnakeGame g = new SnakeGame(5, 5, 3, 2, foods(new Point(0, 0)));
        assertEquals(Event.MOVED, g.tick());
        assertEquals(Event.DEAD, g.tick());
        assertTrue(g.isDead());
        assertEquals(Event.DEAD, g.tick());
        assertEquals(new Point(4, 2), g.snake().get(0));
    }

    @Test
    void selfCollision() {
        // растим змейку до 5 и закручиваем в себя
        SnakeGame g = new SnakeGame(10, 10, 5, 5, foods(new Point(6, 5), new Point(7, 5), new Point(0, 0)));
        g.tick();
        g.tick();
        assertEquals(5, g.snake().size());
        g.turn(Direction.DOWN);
        g.tick();
        g.turn(Direction.LEFT);
        g.tick();
        g.turn(Direction.UP);
        assertEquals(Event.DEAD, g.tick());
    }

    @Test
    void canFollowOwnTail() {
        // змейка длины 4 ходит по кругу 2x2: голова каждый раз заходит в клетку, откуда в этот же ход уходит хвост
        SnakeGame g = new SnakeGame(10, 10, 5, 5, foods(new Point(6, 5), new Point(0, 0)));
        assertEquals(Event.ATE, g.tick());   // (6,5) (5,5) (4,5) (3,5)
        Direction[] loop = {Direction.DOWN, Direction.LEFT, Direction.UP, Direction.RIGHT};
        for (int i = 0; i < 12; i++) {
            g.turn(loop[i % 4]);
            assertEquals(Event.MOVED, g.tick(), "шаг " + i);
        }
        assertEquals(4, g.snake().size());
    }
}
