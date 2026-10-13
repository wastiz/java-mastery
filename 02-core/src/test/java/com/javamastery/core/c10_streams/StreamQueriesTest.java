package com.javamastery.core.c10_streams;

import com.javamastery.core.c10_streams.Shop.*;
import org.junit.jupiter.api.Test;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.time.Month;
import java.util.*;

import static org.junit.jupiter.api.Assertions.*;

class StreamQueriesTest {

    static final Customer ANN = new Customer(1, "Ann", "Tallinn", 3);
    static final Customer BOB = new Customer(2, "Bob", "Tartu", 1);
    static final Customer EVE = new Customer(3, "Eve", "Tallinn", 3);
    static final Customer DAN = new Customer(4, "Dan", "Narva", 2);

    static final Product BOOK = new Product(1, "Book", Category.BOOKS, new BigDecimal("12.50"));
    static final Product MOUSE = new Product(2, "Mouse", Category.ELECTRONICS, new BigDecimal("25.00"));
    static final Product GAME = new Product(3, "Game", Category.GAMES, new BigDecimal("60.00"));
    static final Product APPLE = new Product(4, "Apple", Category.FOOD, new BigDecimal("0.40"));

    static Order order(long id, Customer c, String date, Status s, OrderLine... lines) {
        return new Order(id, c, LocalDate.parse(date), s, List.of(lines));
    }

    static final List<Order> ORDERS = List.of(
            order(1, ANN, "2026-01-10", Status.PAID, new OrderLine(BOOK, 2), new OrderLine(MOUSE, 1)),      // 50.00
            order(2, BOB, "2026-01-15", Status.SHIPPED, new OrderLine(GAME, 1)),                              // 60.00
            order(3, ANN, "2026-02-01", Status.CANCELLED, new OrderLine(GAME, 3)),                            // 180.00
            order(4, EVE, "2026-02-20", Status.CANCELLED, new OrderLine(APPLE, 10)),                          // 4.00
            order(5, DAN, "2026-03-05", Status.NEW, new OrderLine(APPLE, 50), new OrderLine(BOOK, 1), new OrderLine(MOUSE, 2)), // 82.50
            order(6, BOB, "2026-03-07", Status.PAID, new OrderLine(BOOK, 4)));                                // 50.00

    @Test
    void customerNames() {
        assertEquals(List.of("Ann", "Eve"), StreamQueries.customerNamesIn(ORDERS, "Tallinn"));
        assertEquals(List.of(), StreamQueries.customerNamesIn(ORDERS, "Pärnu"));
    }

    @Test
    void revenue() {
        assertEquals(0, new BigDecimal("242.50").compareTo(StreamQueries.revenue(ORDERS)));
        assertEquals(BigDecimal.ZERO, StreamQueries.revenue(List.of()));
    }

    @Test
    void countByStatus() {
        Map<Status, Long> m = StreamQueries.countByStatus(ORDERS);
        assertEquals(4, m.size());
        assertEquals(2L, m.get(Status.PAID));
        assertEquals(2L, m.get(Status.CANCELLED));
        assertEquals(0L, StreamQueries.countByStatus(List.of()).get(Status.NEW));
    }

    @Test
    void mostExpensive() {
        assertEquals(5, StreamQueries.mostExpensive(ORDERS).orElseThrow().id());
        assertTrue(StreamQueries.mostExpensive(List.of(ORDERS.get(2))).isEmpty());
    }

    @Test
    void unitsSold() {
        Map<String, Integer> m = StreamQueries.unitsSoldByProduct(ORDERS);
        assertEquals(List.of("Apple", "Book", "Game", "Mouse"), new ArrayList<>(m.keySet()));
        assertEquals(50, m.get("Apple"));
        assertEquals(7, m.get("Book"));
        assertEquals(1, m.get("Game"));
        assertEquals(3, m.get("Mouse"));
    }

    @Test
    void revenueByCategory() {
        Map<Category, BigDecimal> m = StreamQueries.revenueByCategory(ORDERS);
        assertEquals(0, new BigDecimal("87.50").compareTo(m.get(Category.BOOKS)));
        assertEquals(0, new BigDecimal("75.00").compareTo(m.get(Category.ELECTRONICS)));
        assertEquals(0, new BigDecimal("20.00").compareTo(m.get(Category.FOOD)));
        assertFalse(m.containsKey(Category.TOYS));
    }

    @Test
    void topCustomers() {
        assertEquals(List.of("Bob", "Dan", "Ann"), StreamQueries.topCustomers(ORDERS, 5));
        assertEquals(List.of("Bob"), StreamQueries.topCustomers(ORDERS, 1));
    }

    @Test
    void partition() {
        Map<Boolean, List<Long>> m = StreamQueries.partitionBySize(ORDERS, new BigDecimal("60"));
        assertEquals(List.of(2L, 3L, 5L), m.get(true));
        assertEquals(List.of(1L, 4L, 6L), m.get(false));
    }

    @Test
    void byMonth() {
        Map<Month, BigDecimal> m = StreamQueries.revenueByMonth(ORDERS);
        assertEquals(List.of(Month.JANUARY, Month.MARCH), new ArrayList<>(m.keySet()));
        assertEquals(0, new BigDecimal("132.50").compareTo(m.get(Month.MARCH)));
    }

    @Test
    void categoriesByCity() {
        Map<String, Set<Category>> m = StreamQueries.categoriesByCity(ORDERS);
        assertEquals(EnumSet.of(Category.BOOKS, Category.ELECTRONICS, Category.GAMES, Category.FOOD), m.get("Tallinn"));
        assertEquals(EnumSet.of(Category.GAMES, Category.BOOKS), m.get("Tartu"));
    }

    @Test
    void productNames() {
        assertEquals("[Apple, Book, Mouse]", StreamQueries.productNames(ORDERS, 5));
        assertEquals("[]", StreamQueries.productNames(ORDERS, 999));
    }

    @Test
    void stats() {
        IntSummaryStatistics s = StreamQueries.linesStats(ORDERS);
        assertEquals(1, s.getMin());
        assertEquals(3, s.getMax());
        assertEquals(9, s.getSum());
    }

    @Test
    void vip() {
        assertTrue(StreamQueries.anyVipWithOnlyCancelled(ORDERS));       // Eve
        assertFalse(StreamQueries.anyVipWithOnlyCancelled(ORDERS.subList(0, 3)));
    }

    @Test
    void infinite() {
        assertEquals(List.of(9, 36, 81, 144), StreamQueries.squaresDivisibleBy3(4));
    }
}
