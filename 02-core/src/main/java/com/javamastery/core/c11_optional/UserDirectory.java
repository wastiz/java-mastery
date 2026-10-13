package com.javamastery.core.c11_optional;

import java.util.Map;
import java.util.Optional;

/**
 * C11 · Optional. Никаких {@code if (x != null)} и никаких {@code optional.get()} — только map/flatMap/filter/orElse*.
 */
public class UserDirectory {

    public record Address(String city, String zip) {}

    /** address может быть null — legacy-модель. */
    public record User(String login, String email, Address address, Long managerId) {}

    private final Map<Long, User> users;

    public UserDirectory(Map<Long, User> users) {
        this.users = users;
    }

    public Optional<User> findById(long id) {
        throw new UnsupportedOperationException("TODO");
    }

    /** Город пользователя или "unknown" (если нет пользователя, адреса или города). */
    public String cityOf(long id) {
        throw new UnsupportedOperationException("TODO");
    }

    /** Email менеджера пользователя (если есть пользователь, у него есть менеджер, и менеджер существует). flatMap! */
    public Optional<String> managerEmail(long id) {
        throw new UnsupportedOperationException("TODO");
    }

    /** Домен корпоративной почты: email оканчивается на "@corp.ee" → "corp.ee", иначе empty. filter! */
    public Optional<String> corporateDomain(long id) {
        throw new UnsupportedOperationException("TODO");
    }

    /** Логин или исключение NoSuchElementException("User 42 not found"). orElseThrow с сообщением. */
    public String loginOrThrow(long id) {
        throw new UnsupportedOperationException("TODO");
    }

    /**
     * Дорогая операция по умолчанию — должна вызываться ТОЛЬКО если пользователя нет.
     * Разница между orElse и orElseGet — классический вопрос.
     */
    public String loginOrCompute(long id, java.util.function.Supplier<String> expensiveDefault) {
        throw new UnsupportedOperationException("TODO");
    }
}
