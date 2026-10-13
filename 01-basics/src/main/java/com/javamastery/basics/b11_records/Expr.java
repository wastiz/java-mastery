package com.javamastery.basics.b11_records;

/**
 * B11 · sealed interface + records + pattern matching в switch (Java 21) — алгебраические типы данных.
 *
 * <p>Арифметическое выражение. Иерархия уже объявлена; реализуй статические методы ниже,
 * используя {@code switch} с паттернами по типам (record patterns: {@code case Add(Expr l, Expr r) -> ...}).
 * Компилятор сам проверит, что все случаи покрыты, — именно в этом сила sealed.
 */
public sealed interface Expr {

    record Num(double value) implements Expr {}

    record Var(String name) implements Expr {}

    record Add(Expr left, Expr right) implements Expr {}

    record Mul(Expr left, Expr right) implements Expr {}

    record Neg(Expr inner) implements Expr {}

    /** Вычислить значение. Переменные берутся из vars; отсутствующая переменная → IllegalArgumentException. */
    static double eval(Expr e, java.util.Map<String, Double> vars) {
        throw new UnsupportedOperationException("TODO");
    }

    /**
     * Красивая печать: Num(2.0) → "2", Num(2.5) → "2.5", Var → имя, Add → "(a + b)", Mul → "(a * b)", Neg → "-a".
     */
    static String show(Expr e) {
        throw new UnsupportedOperationException("TODO");
    }

    /**
     * Упрощение (рекурсивно, снизу вверх):
     * x + 0 → x, 0 + x → x, x * 1 → x, 1 * x → x, x * 0 → 0, 0 * x → 0, -(-x) → x,
     * Num + Num → Num(сумма), Num * Num → Num(произведение), -Num(v) → Num(-v).
     * Подсказка: guard'ы в switch — {@code case Add(Num(var a), Num(var b)) -> ...} или {@code case X x when ... ->}.
     */
    static Expr simplify(Expr e) {
        throw new UnsupportedOperationException("TODO");
    }
}
