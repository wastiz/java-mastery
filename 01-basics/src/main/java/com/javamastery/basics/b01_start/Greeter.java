package com.javamastery.basics.b01_start;

/**
 * B01 · Приветствие по времени суток.
 *
 * <ul>
 *   <li>5..11 → "Good morning, NAME!"</li>
 *   <li>12..17 → "Good afternoon, NAME!"</li>
 *   <li>18..22 → "Good evening, NAME!"</li>
 *   <li>23, 0..4 → "Good night, NAME!"</li>
 * </ul>
 * Имя обрезается от пробелов по краям; пустое или null имя → "stranger".
 * Час вне диапазона 0..23 → {@link IllegalArgumentException}.
 */
public final class Greeter {
    private Greeter() {}

    public static String greet(String name, int hour) {
        throw new UnsupportedOperationException("TODO");
    }
}
