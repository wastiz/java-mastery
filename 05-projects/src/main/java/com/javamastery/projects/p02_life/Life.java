package com.javamastery.projects.p02_life;

/**
 * P02 · Игра «Жизнь» Конвея. Двумерные массивы, неизменяемые поколения, equals/hashCode.
 *
 * <ul>
 *   <li>живая клетка с 2 или 3 живыми соседями выживает, иначе умирает;</li>
 *   <li>мёртвая клетка с ровно 3 соседями оживает;</li>
 *   <li>режим wrap=true — поле «тор» (края склеены), false — за краем всё мертво;</li>
 *   <li>{@code next()} возвращает НОВОЕ поколение (объект неизменяем);</li>
 *   <li>parse/toString: строки через '\n', 'O' — живая, '.' — мёртвая;</li>
 *   <li>equals/hashCode — по содержимому и размерам (подсказка: Arrays.deepEquals);</li>
 *   <li>{@code periodOf(maxSteps)} — через сколько поколений узор повторится (осциллятор «мигалка» — 2,
 *       «натюрморт» — 1); нет повтора за maxSteps → -1. Подсказка: HashMap&lt;Life, Integer&gt;.</li>
 * </ul>
 */
public final class Life {
    // TODO: поля

    private Life(boolean[][] cells, boolean wrap) {
        throw new UnsupportedOperationException("TODO");
    }

    public static Life parse(String pattern, boolean wrap) {
        throw new UnsupportedOperationException("TODO");
    }

    public boolean isAlive(int r, int c) {
        throw new UnsupportedOperationException("TODO");
    }

    public int population() {
        throw new UnsupportedOperationException("TODO");
    }

    public Life next() {
        throw new UnsupportedOperationException("TODO");
    }

    public Life step(int generations) {
        throw new UnsupportedOperationException("TODO");
    }

    public int periodOf(int maxSteps) {
        throw new UnsupportedOperationException("TODO");
    }

    @Override
    public boolean equals(Object o) {
        throw new UnsupportedOperationException("TODO");
    }

    @Override
    public int hashCode() {
        throw new UnsupportedOperationException("TODO");
    }

    @Override
    public String toString() {
        throw new UnsupportedOperationException("TODO");
    }
}
