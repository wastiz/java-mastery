package com.javamastery.basics.b14_immutability;

import java.time.LocalDate;
import java.util.Date;
import java.util.List;
import java.util.Map;

/**
 * B14 · Неизменяемый класс (immutable) — по всем правилам.
 *
 * <ul>
 *   <li>класс final, поля private final;</li>
 *   <li>защитные копии на входе (defensive copy) и неизменяемые представления на выходе;</li>
 *   <li>legacy-поле {@code java.util.Date hiredAt} — изменяемое! Подумай, что с ним делать на входе И на выходе;</li>
 *   <li>"wither"-методы возвращают новый объект: withSalary, withSkill.</li>
 * </ul>
 * Тесты попытаются изменить объект всеми известными способами.
 */
public final class Employee {
    // TODO: поля

    public Employee(String name, List<String> skills, Map<String, Integer> ratings, Date hiredAt, LocalDate birthday, long salary) {
        throw new UnsupportedOperationException("TODO");
    }

    public String name() {
        throw new UnsupportedOperationException("TODO");
    }

    public List<String> skills() {
        throw new UnsupportedOperationException("TODO");
    }

    public Map<String, Integer> ratings() {
        throw new UnsupportedOperationException("TODO");
    }

    public Date hiredAt() {
        throw new UnsupportedOperationException("TODO");
    }

    public LocalDate birthday() {
        throw new UnsupportedOperationException("TODO");
    }

    public long salary() {
        throw new UnsupportedOperationException("TODO");
    }

    public Employee withSalary(long newSalary) {
        throw new UnsupportedOperationException("TODO");
    }

    public Employee withSkill(String skill) {
        throw new UnsupportedOperationException("TODO");
    }
}
