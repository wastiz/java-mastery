package com.javamastery.basics.b10_enums;

/**
 * B10 · Enum как конечный автомат.
 *
 * <p>Последовательность: RED → RED_YELLOW → GREEN → YELLOW → RED.
 * Длительности (сек): RED 30, RED_YELLOW 2, GREEN 25, YELLOW 3.
 * {@code canGo()} — true только для GREEN.
 * {@code stateAfter(seconds)} — какой сигнал будет гореть через seconds секунд, если текущий только что включился.
 */
public enum TrafficLight {
    RED, RED_YELLOW, GREEN, YELLOW; // TODO: добавь длительности (поле + конструктор)

    // TODO: поле длительности и конструктор

    public int durationSeconds() {
        throw new UnsupportedOperationException("TODO");
    }

    public TrafficLight next() {
        throw new UnsupportedOperationException("TODO: подсказка: values() и ordinal()");
    }

    public boolean canGo() {
        throw new UnsupportedOperationException("TODO");
    }

    public TrafficLight stateAfter(long seconds) {
        throw new UnsupportedOperationException("TODO");
    }
}
