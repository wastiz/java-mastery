package com.javamastery.core.c17_testing;

import com.javamastery.core.c17_testing.OrderService.*;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.ArrayList;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

/**
 * Тесты на «ручных» фейках. ЗАДАНИЕ (после того как они пройдут): создай рядом OrderServiceMockitoTest
 * и проверь то же самое через Mockito (зависимость уже в pom.xml).
 */
class OrderServiceTest {

    static class FakeInventory implements Inventory {
        boolean available = true;
        final List<String> calls = new ArrayList<>();

        public boolean reserve(String sku, int qty) {
            calls.add("reserve " + sku + " " + qty);
            return available;
        }

        public void release(String sku, int qty) {
            calls.add("release " + sku + " " + qty);
        }
    }

    static class FakePayments implements Payments {
        boolean fail;
        final List<String> charges = new ArrayList<>();

        public void charge(String email, long cents) throws PaymentException {
            charges.add(email + ":" + cents);
            if (fail) throw new PaymentException("card declined");
        }
    }

    static class FakeNotifier implements Notifier {
        boolean fail;
        final List<String> sent = new ArrayList<>();

        public void send(String email, String text) {
            if (fail) throw new RuntimeException("smtp down");
            sent.add(email + ":" + text);
        }
    }

    FakeInventory inventory;
    FakePayments payments;
    FakeNotifier notifier;
    OrderService service;

    @BeforeEach
    void setUp() {
        inventory = new FakeInventory();
        payments = new FakePayments();
        notifier = new FakeNotifier();
        service = new OrderService(inventory, sku -> 1999, payments, notifier);
    }

    @Test
    void happyPath() {
        assertEquals(Result.OK, service.placeOrder("ann@x.ee", "BOOK", 2));
        assertEquals(List.of("reserve BOOK 2"), inventory.calls);
        assertEquals(List.of("ann@x.ee:3998"), payments.charges);
        assertEquals(List.of("ann@x.ee:Order confirmed: BOOK x2"), notifier.sent);
    }

    @Test
    void outOfStock() {
        inventory.available = false;
        assertEquals(Result.OUT_OF_STOCK, service.placeOrder("ann@x.ee", "BOOK", 1));
        assertTrue(payments.charges.isEmpty());
        assertTrue(notifier.sent.isEmpty());
    }

    @Test
    void paymentFailedReleasesStock() {
        payments.fail = true;
        assertEquals(Result.PAYMENT_FAILED, service.placeOrder("ann@x.ee", "BOOK", 3));
        assertEquals(List.of("reserve BOOK 3", "release BOOK 3"), inventory.calls);
        assertTrue(notifier.sent.isEmpty());
    }

    @Test
    void notifierFailureIsNotFatal() {
        notifier.fail = true;
        assertEquals(Result.OK, service.placeOrder("ann@x.ee", "BOOK", 1));
    }

    @Test
    void invalidQty() {
        assertThrows(IllegalArgumentException.class, () -> service.placeOrder("ann@x.ee", "BOOK", 0));
        assertTrue(inventory.calls.isEmpty());
    }
}
