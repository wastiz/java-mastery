package com.javamastery.basics.b07_inheritance;

/**
 * B07 · Абстрактный класс. Не меняй этот класс — реализуй наследников: Circle, Rectangle, Square, Triangle.
 *
 * <p>Square наследуется от Rectangle. Triangle задаётся тремя сторонами; если такой треугольник
 * невозможен (неравенство треугольника) — IllegalArgumentException. Площадь треугольника — по формуле Герона.
 * Любая сторона/радиус &lt;= 0 → IllegalArgumentException.
 */
public abstract class Shape implements Comparable<Shape> {

    private final String name;

    protected Shape(String name) {
        this.name = name;
    }

    public String name() {
        return name;
    }

    public abstract double area();

    public abstract double perimeter();

    /** Шаблонный метод: общая логика, которая использует абстрактные методы наследников. */
    public String describe() {
        return String.format(java.util.Locale.ROOT, "%s[area=%.2f, perimeter=%.2f]", name, area(), perimeter());
    }

    /** Фигуры сравниваются по площади. */
    @Override
    public int compareTo(Shape o) {
        return Double.compare(area(), o.area());
    }
}
