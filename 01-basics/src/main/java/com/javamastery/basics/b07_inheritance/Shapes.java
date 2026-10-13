package com.javamastery.basics.b07_inheritance;

import java.util.List;

/** B07 · Полиморфизм: работаем с фигурами через базовый тип. */
public final class Shapes {
    private Shapes() {}

    /** Суммарная площадь. */
    public static double totalArea(List<? extends Shape> shapes) {
        throw new UnsupportedOperationException("TODO");
    }

    /** Фигура с наибольшим периметром или null для пустого списка. */
    public static Shape maxPerimeter(List<? extends Shape> shapes) {
        throw new UnsupportedOperationException("TODO");
    }

    /** Сколько прямоугольников в списке (квадрат — тоже прямоугольник!). */
    public static long countRectangles(List<? extends Shape> shapes) {
        throw new UnsupportedOperationException("TODO");
    }
}
