package com.javamastery.basics.b11_records;

import com.javamastery.basics.b11_records.Expr.*;
import org.junit.jupiter.api.Test;

import java.util.Map;

import static org.junit.jupiter.api.Assertions.*;

class ExprTest {

    // (x + 2) * -(3)
    private final Expr e = new Mul(new Add(new Var("x"), new Num(2)), new Neg(new Num(3)));

    @Test
    void eval() {
        assertEquals(-15, Expr.eval(e, Map.of("x", 3.0)), 1e-9);
        assertThrows(IllegalArgumentException.class, () -> Expr.eval(e, Map.of()));
    }

    @Test
    void show() {
        assertEquals("((x + 2) * -3)", Expr.show(e));
        assertEquals("2.5", Expr.show(new Num(2.5)));
    }

    @Test
    void simplify() {
        assertEquals(new Var("x"), Expr.simplify(new Add(new Var("x"), new Num(0))));
        assertEquals(new Var("x"), Expr.simplify(new Mul(new Num(1), new Var("x"))));
        assertEquals(new Num(0), Expr.simplify(new Mul(new Var("y"), new Num(0))));
        assertEquals(new Var("z"), Expr.simplify(new Neg(new Neg(new Var("z")))));
        assertEquals(new Num(7), Expr.simplify(new Add(new Num(3), new Num(4))));
        // вложенное: (x * (2 - 1)) + (0 * y)  где 2-1 записано как 2 + -1
        Expr nested = new Add(new Mul(new Var("x"), new Add(new Num(2), new Neg(new Num(1)))), new Mul(new Num(0), new Var("y")));
        assertEquals(new Var("x"), Expr.simplify(nested));
    }

    @Test
    void recordsHaveValueSemantics() {
        assertEquals(new Add(new Num(1), new Var("a")), new Add(new Num(1), new Var("a")));
        assertEquals("Num[value=1.0]", new Num(1).toString());
    }
}
