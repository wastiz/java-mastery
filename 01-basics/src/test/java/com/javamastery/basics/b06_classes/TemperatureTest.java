package com.javamastery.basics.b06_classes;

import org.junit.jupiter.api.Test;

import java.lang.reflect.Modifier;

import static org.junit.jupiter.api.Assertions.*;

class TemperatureTest {

    @Test
    void conversions() {
        var t = Temperature.ofCelsius(100);
        assertEquals(212, t.fahrenheit(), 1e-9);
        assertEquals(373.15, t.kelvin(), 1e-9);
        assertEquals(-40, Temperature.ofFahrenheit(-40).celsius(), 1e-9);
        assertEquals(0, Temperature.ofKelvin(273.15).celsius(), 1e-9);
    }

    @Test
    void absoluteZero() {
        assertThrows(IllegalArgumentException.class, () -> Temperature.ofCelsius(-300));
        assertThrows(IllegalArgumentException.class, () -> Temperature.ofKelvin(-0.01));
        assertDoesNotThrow(() -> Temperature.ofKelvin(0));
    }

    @Test
    void compare() {
        assertTrue(Temperature.ofCelsius(20).isWarmerThan(Temperature.ofFahrenheit(60)));
        assertFalse(Temperature.ofCelsius(0).isWarmerThan(Temperature.ofKelvin(273.15)));
    }

    @Test
    void constructorIsPrivate() throws Exception {
        assertTrue(Modifier.isPrivate(Temperature.class.getDeclaredConstructor(double.class).getModifiers()));
    }
}
