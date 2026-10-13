package com.javamastery.concurrency.k02_deadlock;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.Timeout;

import java.util.Arrays;
import java.util.concurrent.ThreadLocalRandom;

import static org.junit.jupiter.api.Assertions.*;

class BankTest {

    @Test
    void simple() {
        Bank bank = new Bank();
        Bank.Account a = new Bank.Account(1, 100), b = new Bank.Account(2, 0);
        assertTrue(bank.transfer(a, b, 70));
        assertFalse(bank.transfer(a, b, 31));
        assertEquals(30, a.balance());
        assertEquals(70, b.balance());
        assertThrows(IllegalArgumentException.class, () -> bank.transfer(a, a, 1));
    }

    @Test
    @Timeout(20)
    void noDeadlockAndMoneyIsConserved() throws Exception {
        Bank bank = new Bank();
        Bank.Account[] accounts = new Bank.Account[5];
        for (int i = 0; i < accounts.length; i++) accounts[i] = new Bank.Account(i, 1000);
        Thread[] threads = new Thread[8];
        for (int t = 0; t < threads.length; t++) {
            threads[t] = new Thread(() -> {
                ThreadLocalRandom rnd = ThreadLocalRandom.current();
                for (int i = 0; i < 50_000; i++) {
                    int x = rnd.nextInt(accounts.length), y = rnd.nextInt(accounts.length);
                    if (x != y) bank.transfer(accounts[x], accounts[y], rnd.nextInt(50));
                }
            });
            threads[t].start();
        }
        for (Thread t : threads) t.join();
        long total = Arrays.stream(accounts).mapToLong(Bank.Account::balance).sum();
        assertEquals(5000, total);
        assertTrue(Arrays.stream(accounts).allMatch(a -> a.balance() >= 0));
    }
}
