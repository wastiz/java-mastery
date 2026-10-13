package com.javamastery.core.c16_patterns;

import java.util.Optional;

/**
 * C16 · Decorator: добавляем поведение (кэш, логирование, метрики), не меняя исходный класс.
 * Именно так работают Spring AOP-прокси (@Cacheable, @Transactional).
 */
public final class Repositories {
    private Repositories() {}

    public interface UserRepository {
        Optional<String> findName(long id);

        void rename(long id, String newName);
    }

    /**
     * Кэширующий декоратор: findName кэширует результат (в т.ч. «не найдено»), rename — сбрасывает кэш для этого id
     * и делегирует. Кэш ограничен maxEntries — при переполнении удаляется самая старая (по добавлению) запись.
     */
    public static UserRepository caching(UserRepository delegate, int maxEntries) {
        throw new UnsupportedOperationException("TODO");
    }

    /**
     * Логирующий декоратор: перед каждым вызовом добавляет в log строку "findName(5)" / "rename(5, Bob)",
     * после успешного — "-> Optional[Bob]" (для findName) или "-> ok" (для rename),
     * при исключении — "!! " + сообщение исключения, и исключение пробрасывается дальше.
     */
    public static UserRepository logging(UserRepository delegate, java.util.List<String> log) {
        throw new UnsupportedOperationException("TODO");
    }
}
