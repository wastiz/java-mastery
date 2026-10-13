package com.javamastery.core.c09_lambdas;

import org.junit.jupiter.api.Test;

import java.util.List;
import java.util.concurrent.atomic.AtomicInteger;
import java.util.function.Function;
import java.util.function.Predicate;
import java.util.function.Supplier;
import java.util.function.UnaryOperator;

import static org.junit.jupiter.api.Assertions.*;

class FunctionsTest {

    @Test
    void pipeline() {
        UnaryOperator<String> p = Functions.pipeline(String::trim, String::toUpperCase, s -> s + "!");
        assertEquals("HI!", p.apply("  hi "));
        assertEquals("x", Functions.<String>pipeline().apply("x"));
    }

    @Test
    void memoize() {
        AtomicInteger calls = new AtomicInteger();
        Function<Integer, String> slow = x -> {
            calls.incrementAndGet();
            return x % 2 == 0 ? null : "odd" + x;
        };
        Function<Integer, String> fast = Functions.memoize(slow);
        assertEquals("odd1", fast.apply(1));
        assertEquals("odd1", fast.apply(1));
        assertNull(fast.apply(2));
        assertNull(fast.apply(2));
        assertEquals(2, calls.get());
    }

    @Test
    void curry() {
        Function<Integer, Function<Integer, Integer>> add = Functions.curry(Integer::sum);
        Function<Integer, Integer> add5 = add.apply(5);
        assertEquals(8, add5.apply(3));
        assertEquals(7, Functions.uncurry(add).apply(3, 4));
    }

    @Test
    void lazy() {
        AtomicInteger calls = new AtomicInteger();
        Supplier<String> s = Functions.lazy(() -> {
            calls.incrementAndGet();
            return "value";
        });
        assertEquals(0, calls.get());
        assertEquals("value", s.get());
        assertEquals("value", s.get());
        assertEquals(1, calls.get());
    }

    @Test
    void ids() {
        Supplier<String> a = Functions.idGenerator("A"), b = Functions.idGenerator("B");
        assertEquals("A-1", a.get());
        assertEquals("A-2", a.get());
        assertEquals("B-1", b.get());
    }

    @Test
    void allOf() {
        Predicate<Object> notNull = o -> o != null;
        Predicate<String> p = Functions.allOf(notNull, s -> s.length() > 2, s -> s.startsWith("j"));
        assertTrue(p.test("java"));
        assertFalse(p.test("js"));
        assertFalse(p.test(null));
        assertTrue(Functions.allOf().test("anything"));
    }

    @Test
    void triFunction() {
        Functions.TriFunction<Integer, Integer, Integer, Integer> sum3 = (a, b, c) -> a + b + c;
        assertEquals("6", sum3.andThen(String::valueOf).apply(1, 2, 3));
    }

    @Test
    void methodRefs() {
        assertEquals(42, Functions.parseInt().apply("42"));
        assertEquals("ABC", Functions.toUpper().apply("abc"));
        assertTrue(Functions.equalsIgnoringCase("Java").test("JAVA"));
        assertFalse(Functions.equalsIgnoringCase("Java").test("Kotlin"));
        List<String> l1 = Functions.listFactory().get(), l2 = Functions.listFactory().get();
        l1.add("x");
        assertTrue(l2.isEmpty());
    }
}
