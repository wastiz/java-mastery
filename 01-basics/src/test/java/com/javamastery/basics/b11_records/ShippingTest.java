package com.javamastery.basics.b11_records;

import com.javamastery.basics.b11_records.Shipping.*;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class ShippingTest {

    @Test
    void letters() {
        assertEquals(150, Shipping.cost(new Letter(20)));
        assertEquals(300, Shipping.cost(new Letter(21)));
    }

    @Test
    void boxes() {
        assertEquals(600, Shipping.cost(new Box(1000, 10, 10, 10)));
        assertEquals(700, Shipping.cost(new Box(1001, 10, 10, 10)));
        assertEquals(500, Shipping.cost(new Box(0, 10, 10, 10)));
        assertEquals(2600, Shipping.cost(new Box(1, 101, 10, 10)));
    }

    @Test
    void pallets() {
        assertEquals(10000, Shipping.cost(new Pallet(100, false)));
        assertEquals(20000, Shipping.cost(new Pallet(100, true)));
    }
}
