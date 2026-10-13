package com.javamastery.concurrency.k02_deadlock;

/**
 * K02 · Deadlock и как его избежать.
 *
 * <p>Наивная реализация transfer: synchronized(from) { synchronized(to) { ... } } —
 * при одновременных переводах A→B и B→A получаем взаимную блокировку.
 * Реализуй transfer без дедлока: упорядочь захват мониторов (например, по id счёта).
 * Недостаточно средств → вернуть false, ничего не меняя. Перевод на тот же счёт → IllegalArgumentException.
 */
public class Bank {

    public static final class Account {
        private final int id;
        private long balance;

        public Account(int id, long balance) {
            this.id = id;
            this.balance = balance;
        }

        public int id() {
            return id;
        }

        public synchronized long balance() {
            return balance;
        }

        // package-private: меняется только банком под локом
        void add(long delta) {
            balance += delta;
        }

        long rawBalance() {
            return balance;
        }
    }

    public boolean transfer(Account from, Account to, long amount) {
        throw new UnsupportedOperationException("TODO");
    }
}
