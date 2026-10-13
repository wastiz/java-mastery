package com.javamastery.projects.p04_miniredis;

import java.util.function.LongSupplier;

/**
 * P04 · Мини-Redis: in-memory key-value хранилище с TTL и текстовыми командами.
 * Коллекции, парсинг, время, потокобезопасность. Бонус (без тестов): TCP-сервер на ServerSocket + виртуальные потоки,
 * чтобы подключаться через {@code nc localhost 6380}.
 *
 * <p>{@code execute(String commandLine)} возвращает строку-ответ:
 * <ul>
 *   <li>{@code SET key value} → "OK"; {@code SET key value EX seconds} — с TTL;</li>
 *   <li>{@code GET key} → значение или "(nil)"; GET по ключу-списку → "ERR wrong type";</li>
 *   <li>{@code DEL k1 k2 ...} → число удалённых ключей, например "2";</li>
 *   <li>{@code INCR key} → новое значение (нет ключа = 0 → 1); не число → "ERR not an integer";</li>
 *   <li>{@code EXPIRE key seconds} → "1" если ключ есть, "0" если нет;</li>
 *   <li>{@code TTL key} → оставшиеся СЕКУНДЫ (округлить вверх), "-1" если без TTL, "-2" если ключа нет;</li>
 *   <li>{@code KEYS pattern} → ключи через запятую, по алфавиту ("" если нет); шаблон — glob: * и ?;</li>
 *   <li>{@code LPUSH key v1 v2 ...} → длина списка; элементы добавляются в голову по очереди (как в Redis);</li>
 *   <li>{@code RPOP key} → элемент с хвоста или "(nil)";</li>
 *   <li>{@code LRANGE key start stop} → элементы через запятую; индексы как в Redis (включительно, -1 — последний);</li>
 *   <li>значения могут быть в двойных кавычках с пробелами: {@code SET greeting "hello world"};</li>
 *   <li>имя команды без учёта регистра; неизвестная команда → "ERR unknown command 'XYZ'"; неверное число аргументов → "ERR wrong number of arguments".</li>
 * </ul>
 * Просроченные ключи должны вести себя как отсутствующие (ленивое удаление при обращении достаточно).
 * Время — из clock (миллисекунды). Класс должен быть потокобезопасным.
 */
public class MiniRedis {
    // TODO: поля

    public MiniRedis(LongSupplier millisClock) {
        throw new UnsupportedOperationException("TODO");
    }

    public MiniRedis() {
        this(System::currentTimeMillis);
    }

    public String execute(String commandLine) {
        throw new UnsupportedOperationException("TODO");
    }

    // TODO: вспомогательные методы
}
