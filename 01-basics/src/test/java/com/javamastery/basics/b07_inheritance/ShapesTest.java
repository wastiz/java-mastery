package com.javamastery.basics.b07_inheritance;

import org.junit.jupiter.api.Test;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

class ShapesTest {

    @Test
    void areasAndPerimeters() {
        assertEquals(Math.PI * 4, new Circle(2).area(), 1e-9);
        assertEquals(12, new Rectangle(3, 4).area(), 1e-9);
        assertEquals(14, new Rectangle(3, 4).perimeter(), 1e-9);
        assertEquals(25, new Square(5).area(), 1e-9);
        assertEquals(6, new Triangle(3, 4, 5).area(), 1e-9);
        assertEquals(12, new Triangle(3, 4, 5).perimeter(), 1e-9);
    }

    @Test
    void names() {
        assertEquals("Square", new Square(1).name());
        assertEquals("Rectangle", new Rectangle(1, 2).name());
        assertEquals("Circle", new Circle(1).name());
        assertEquals("Triangle[area=6.00, perimeter=12.00]", new Triangle(3, 4, 5).describe());
    }

    @Test
    void validation() {
        assertThrows(IllegalArgumentException.class, () -> new Circle(0));
        assertThrows(IllegalArgumentException.class, () -> new Rectangle(-1, 2));
        assertThrows(IllegalArgumentException.class, () -> new Square(0));
        assertThrows(IllegalArgumentException.class, () -> new Triangle(1, 2, 3));
        assertThrows(IllegalArgumentException.class, () -> new Triangle(1, 10, 2));
    }

    @Test
    void hierarchy() {
        Shape s = new Square(2);
        assertInstanceOf(Rectangle.class, s);
        assertEquals(Rectangle.class, Square.class.getSuperclass());
    }

    @Test
    void polymorphism() {
        List<Shape> shapes = List.of(new Circle(1), new Square(2), new Rectangle(1, 10), new Triangle(3, 4, 5));
        assertEquals(Math.PI + 4 + 10 + 6, Shapes.totalArea(shapes), 1e-9);
        assertInstanceOf(Rectangle.class, Shapes.maxPerimeter(shapes));
        assertEquals(22, Shapes.maxPerimeter(shapes).perimeter(), 1e-9);
        assertEquals(2, Shapes.countRectangles(shapes));
        assertNull(Shapes.maxPerimeter(List.of()));
    }

    @Test
    void sortedByArea() {
        List<Shape> shapes = new ArrayList<>(List.of(new Square(3), new Circle(1), new Triangle(3, 4, 5)));
        Collections.sort(shapes);
        assertEquals(List.of("Circle", "Triangle", "Square"), shapes.stream().map(Shape::name).toList());
    }
}
