package com.javamastery.basics.b06_classes;

/**
 * B06 · Статические фабричные методы вместо конструкторов.
 *
 * <p>Конструктор приватный. Создание — только через {@code ofCelsius}, {@code ofFahrenheit}, {@code ofKelvin}.
 * Внутри храни одно значение в Кельвинах. Температура ниже абсолютного нуля → IllegalArgumentException.
 * Сравнение двух температур — {@code isWarmerThan}.
 */
public final class Temperature {
    // TODO: поле с кельвинами

    private Temperature(double kelvin) {
        throw new UnsupportedOperationException("TODO");
    }

    public static Temperature ofCelsius(double c) {
        throw new UnsupportedOperationException("TODO");
    }

    public static Temperature ofFahrenheit(double f) {
        throw new UnsupportedOperationException("TODO");
    }

    public static Temperature ofKelvin(double k) {
        throw new UnsupportedOperationException("TODO");
    }

    public double celsius() {
        throw new UnsupportedOperationException("TODO");
    }

    public double fahrenheit() {
        throw new UnsupportedOperationException("TODO");
    }

    public double kelvin() {
        throw new UnsupportedOperationException("TODO");
    }

    public boolean isWarmerThan(Temperature other) {
        throw new UnsupportedOperationException("TODO");
    }
}
