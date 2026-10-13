package com.javamastery.basics.b06_classes;

/**
 * B06 · Классы, конструкторы, инкапсуляция, static.
 *
 * <p>Банковский счёт:
 * <ul>
 *   <li>номер счёта генерируется автоматически: "ACC-0001", "ACC-0002"... (общий static-счётчик);</li>
 *   <li>владелец — непустая строка, иначе IllegalArgumentException;</li>
 *   <li>баланс хранится в центах (long), снаружи напрямую не меняется;</li>
 *   <li>deposit/withdraw: сумма должна быть &gt; 0, иначе IllegalArgumentException;</li>
 *   <li>withdraw больше баланса → IllegalStateException("Insufficient funds");</li>
 *   <li>transferTo(other, amount) — снимает с этого счёта и кладёт на другой; перевод самому себе — IllegalArgumentException;</li>
 *   <li>getCreatedCount() — сколько счетов создано всего;</li>
 *   <li>resetCounter() — сбросить счётчик (нужен для тестов).</li>
 * </ul>
 */
public class BankAccount {
    // TODO: поля: static-счётчик, номер, владелец, баланс

    public BankAccount(String owner) {
        throw new UnsupportedOperationException("TODO");
    }

    public BankAccount(String owner, long initialCents) {
        throw new UnsupportedOperationException("TODO: вызови другой конструктор через this(...)");
    }

    public String getNumber() {
        throw new UnsupportedOperationException("TODO");
    }

    public String getOwner() {
        throw new UnsupportedOperationException("TODO");
    }

    public long getBalanceCents() {
        throw new UnsupportedOperationException("TODO");
    }

    public void deposit(long cents) {
        throw new UnsupportedOperationException("TODO");
    }

    public void withdraw(long cents) {
        throw new UnsupportedOperationException("TODO");
    }

    public void transferTo(BankAccount other, long cents) {
        throw new UnsupportedOperationException("TODO");
    }

    public static int getCreatedCount() {
        throw new UnsupportedOperationException("TODO");
    }

    public static void resetCounter() {
        throw new UnsupportedOperationException("TODO");
    }
}
