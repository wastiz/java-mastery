package com.javamastery.core.c08_iterators;

import org.junit.jupiter.api.Test;

import java.util.*;

import static org.junit.jupiter.api.Assertions.*;

class IteratorsTest {

    private static <T> List<T> toList(Iterator<T> it) {
        List<T> r = new ArrayList<>();
        it.forEachRemaining(r::add);
        return r;
    }

    @Test
    void range() {
        List<Integer> r = new ArrayList<>();
        for (int x : Range.of(0, 10, 3)) r.add(x);
        assertEquals(List.of(0, 3, 6, 9), r);
        assertEquals(List.of(5, 3, 1), toList(Range.of(5, 0, -2).iterator()));
        assertEquals(List.of(), toList(Range.of(5, 5).iterator()));
        assertEquals(List.of(), toList(Range.of(5, 0).iterator()));
        assertThrows(IllegalArgumentException.class, () -> Range.of(0, 1, 0));
        Range rr = Range.of(0, 3);
        assertEquals(toList(rr.iterator()), toList(rr.iterator()));
        assertEquals(List.of(Integer.MAX_VALUE - 1), toList(Range.of(Integer.MAX_VALUE - 1, Integer.MAX_VALUE, 5).iterator()));
    }

    @Test
    void peeking() {
        var it = Iterators.peeking(List.of(1, 2, 3).iterator());
        assertEquals(1, it.peek());
        assertEquals(1, it.peek());
        assertEquals(1, it.next());
        assertEquals(2, it.next());
        assertEquals(3, it.peek());
        assertTrue(it.hasNext());
        assertEquals(3, it.next());
        assertFalse(it.hasNext());
        assertThrows(NoSuchElementException.class, it::peek);
    }

    @Test
    void flatten() {
        List<Iterator<Integer>> its = List.of(List.<Integer>of().iterator(), List.of(1, 2).iterator(),
                List.<Integer>of().iterator(), List.of(3).iterator(), List.<Integer>of().iterator());
        assertEquals(List.of(1, 2, 3), toList(Iterators.flatten(its.iterator())));
    }

    @Test
    void lazyFilterAndLimit() {
        int[] pulled = {0};
        Iterator<Long> counting = new Iterator<>() {
            final Iterator<Long> fib = Iterators.fibonacci();

            public boolean hasNext() {
                return true;
            }

            public Long next() {
                pulled[0]++;
                return fib.next();
            }
        };
        Iterator<Long> evens = Iterators.limit(Iterators.filter(counting, x -> x % 2 == 0), 5);
        assertEquals(0, pulled[0], "ничего не должно вычисляться до первого запроса");
        assertEquals(List.of(0L, 2L, 8L, 34L, 144L), toList(evens));
        assertEquals(13, pulled[0], "лишних элементов из источника брать нельзя");
    }

    @Test
    void fibonacci() {
        assertEquals(List.of(0L, 1L, 1L, 2L, 3L, 5L, 8L), toList(Iterators.limit(Iterators.fibonacci(), 7)));
    }
}
