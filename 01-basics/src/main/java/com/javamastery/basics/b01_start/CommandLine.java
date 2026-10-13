package com.javamastery.basics.b01_start;

import java.util.List;
import java.util.Map;

/**
 * B01 · Разминка: разбор аргументов командной строки.
 *
 * <p>Любая консольная утилита начинается с {@code public static void main(String[] args)}.
 * Реализуй маленький парсер аргументов.
 *
 * <p>Правила:
 * <ul>
 *   <li>{@code --key=value} — именованная опция (key → value);</li>
 *   <li>{@code --flag} без знака равно — флаг, значение {@code "true"};</li>
 *   <li>{@code -v} (один дефис, одна буква) — короткий флаг, ключ {@code "v"}, значение {@code "true"};</li>
 *   <li>всё остальное — позиционные аргументы в исходном порядке;</li>
 *   <li>{@code --} — всё, что после него, считается позиционным, даже если начинается с дефиса;</li>
 *   <li>если опция повторяется, побеждает последнее значение;</li>
 *   <li>{@code --=x} или {@code --} с пустым ключом перед {@code =} — {@link IllegalArgumentException}.</li>
 * </ul>
 */
public final class CommandLine {

    private CommandLine() {}

    public static ParsedArgs parse(String[] args) {
        throw new UnsupportedOperationException("TODO");
    }

    /** Результат разбора. Класс уже готов — менять не нужно. */
    public static final class ParsedArgs {
        private final Map<String, String> options;
        private final List<String> positional;

        public ParsedArgs(Map<String, String> options, List<String> positional) {
            this.options = Map.copyOf(options);
            this.positional = List.copyOf(positional);
        }

        public Map<String, String> options() { return options; }
        public List<String> positional() { return positional; }

        public String get(String key, String defaultValue) { return options.getOrDefault(key, defaultValue); }

        public boolean flag(String key) { return "true".equals(options.get(key)); }
    }
}
