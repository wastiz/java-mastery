package com.javamastery.basics.b12_exceptions;

import org.junit.jupiter.api.Test;

import java.util.LinkedHashMap;

import static org.junit.jupiter.api.Assertions.*;

class WarehouseTest {

    @Test
    void exceptionIsChecked() {
        assertTrue(Exception.class.isAssignableFrom(InsufficientStockException.class));
        assertFalse(RuntimeException.class.isAssignableFrom(InsufficientStockException.class));
    }

    @Test
    void takeAndAdd() throws Exception {
        Warehouse w = new Warehouse();
        w.add("SKU-1", 5);
        w.add("SKU-1", 2);
        w.take("SKU-1", 4);
        assertEquals(3, w.stock("SKU-1"));
        assertEquals(0, w.stock("nope"));
        assertThrows(IllegalArgumentException.class, () -> w.add("SKU-1", 0));
    }

    @Test
    void notEnough() {
        Warehouse w = new Warehouse();
        w.add("SKU-1", 2);
        var ex = assertThrows(InsufficientStockException.class, () -> w.take("SKU-1", 5));
        assertEquals("Not enough SKU-1: requested 5, available 2", ex.getMessage());
        assertEquals("SKU-1", ex.getSku());
        assertEquals(5, ex.getRequested());
        assertEquals(2, ex.getAvailable());
        assertEquals(2, w.stock("SKU-1"));
    }

    @Test
    void takeAllIsAtomic() {
        Warehouse w = new Warehouse();
        w.add("A", 5);
        w.add("B", 1);
        w.add("C", 10);
        LinkedHashMap<String, Integer> order = new LinkedHashMap<>();
        order.put("A", 3);
        order.put("B", 2);
        order.put("C", 1);
        var ex = assertThrows(InsufficientStockException.class, () -> w.takeAll(order));
        assertEquals("B", ex.getSku());
        assertEquals(5, w.stock("A"));
        assertEquals(1, w.stock("B"));
        assertEquals(10, w.stock("C"));
    }

    @Test
    void takeAllSuccess() throws Exception {
        Warehouse w = new Warehouse();
        w.add("A", 5);
        w.add("B", 2);
        LinkedHashMap<String, Integer> order = new LinkedHashMap<>();
        order.put("A", 5);
        order.put("B", 2);
        w.takeAll(order);
        assertEquals(0, w.stock("A"));
        assertEquals(0, w.stock("B"));
    }
}
