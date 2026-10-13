package com.javamastery.basics.b10_enums;

/**
 * B10 · Enum с полями и абстрактным методом (constant-specific body).
 *
 * <p>Каждая операция знает свой символ и умеет применяться к двум числам.
 * DIVIDE на ноль → ArithmeticException. POWER — возведение в целую степень (b &gt;= 0), иначе IllegalArgumentException.
 * fromSymbol("+") → PLUS, неизвестный символ → IllegalArgumentException.
 */
public enum Operation {
    // TODO: реализуй apply в каждой константе
    PLUS("+") {
        @Override public long apply(long a, long b) { throw new UnsupportedOperationException("TODO"); }
    },
    MINUS("-") {
        @Override public long apply(long a, long b) { throw new UnsupportedOperationException("TODO"); }
    },
    MULTIPLY("*") {
        @Override public long apply(long a, long b) { throw new UnsupportedOperationException("TODO"); }
    },
    DIVIDE("/") {
        @Override public long apply(long a, long b) { throw new UnsupportedOperationException("TODO"); }
    },
    POWER("^") {
        @Override public long apply(long a, long b) { throw new UnsupportedOperationException("TODO"); }
    };

    private final String symbol;

    Operation(String symbol) {
        this.symbol = symbol;
    }

    public String symbol() {
        return symbol;
    }

    public abstract long apply(long a, long b);

    public static Operation fromSymbol(String s) {
        throw new UnsupportedOperationException("TODO");
    }
}
