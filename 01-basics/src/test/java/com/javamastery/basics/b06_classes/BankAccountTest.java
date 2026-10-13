package com.javamastery.basics.b06_classes;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.lang.reflect.Field;
import java.lang.reflect.Modifier;

import static org.junit.jupiter.api.Assertions.*;

class BankAccountTest {

    @BeforeEach
    void reset() {
        BankAccount.resetCounter();
    }

    @Test
    void generatesNumbers() {
        var a = new BankAccount("Ann");
        var b = new BankAccount("Bob", 500);
        assertEquals("ACC-0001", a.getNumber());
        assertEquals("ACC-0002", b.getNumber());
        assertEquals(2, BankAccount.getCreatedCount());
        assertEquals(500, b.getBalanceCents());
        assertEquals("Bob", b.getOwner());
    }

    @Test
    void validatesOwner() {
        assertThrows(IllegalArgumentException.class, () -> new BankAccount(" "));
        assertThrows(IllegalArgumentException.class, () -> new BankAccount(null));
    }

    @Test
    void depositAndWithdraw() {
        var a = new BankAccount("Ann");
        a.deposit(1000);
        a.withdraw(300);
        assertEquals(700, a.getBalanceCents());
        assertThrows(IllegalArgumentException.class, () -> a.deposit(0));
        assertThrows(IllegalArgumentException.class, () -> a.withdraw(-5));
        var ex = assertThrows(IllegalStateException.class, () -> a.withdraw(701));
        assertEquals("Insufficient funds", ex.getMessage());
        assertEquals(700, a.getBalanceCents());
    }

    @Test
    void transfer() {
        var a = new BankAccount("Ann", 1000);
        var b = new BankAccount("Bob");
        a.transferTo(b, 400);
        assertEquals(600, a.getBalanceCents());
        assertEquals(400, b.getBalanceCents());
        assertThrows(IllegalStateException.class, () -> b.transferTo(a, 1000));
        assertEquals(400, b.getBalanceCents());
        assertEquals(600, a.getBalanceCents());
        assertThrows(IllegalArgumentException.class, () -> a.transferTo(a, 1));
    }

    @Test
    void fieldsAreEncapsulated() {
        for (Field f : BankAccount.class.getDeclaredFields()) {
            assertTrue(Modifier.isPrivate(f.getModifiers()), "поле " + f.getName() + " должно быть private");
        }
    }
}
