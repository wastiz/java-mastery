package com.javamastery.basics.b07_inheritance;

public class Rectangle extends Shape {
    // TODO: поля сторон

    public Rectangle(double width, double height) {
        this("Rectangle", width, height);
    }

    /** Защищённый конструктор для наследников (Square передаст своё имя). */
    protected Rectangle(String name, double width, double height) {
        super(name);
        throw new UnsupportedOperationException("TODO");
    }

    @Override
    public double area() {
        throw new UnsupportedOperationException("TODO");
    }

    @Override
    public double perimeter() {
        throw new UnsupportedOperationException("TODO");
    }
}
