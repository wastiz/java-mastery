package com.javamastery.basics.b10_enums;

import java.util.EnumSet;
import java.util.Set;

/**
 * B10 · EnumSet / EnumMap — самые быстрые коллекции для enum'ов (внутри — битовая маска).
 *
 * <p>Права доступа к файлу как в Unix: строка "rwx", "r-x", "---".
 */
public final class Permissions {
    public enum Permission { READ, WRITE, EXECUTE }

    private Permissions() {}

    /** "r-x" → {READ, EXECUTE}. Строка не из 3 символов или неверный символ на позиции → IllegalArgumentException. */
    public static Set<Permission> parse(String s) {
        throw new UnsupportedOperationException("TODO");
    }

    /** Обратно: {READ, WRITE} → "rw-". */
    public static String format(Set<Permission> perms) {
        throw new UnsupportedOperationException("TODO");
    }

    /** Восьмеричная цифра: rwx → 7, r-x → 5, --- → 0. */
    public static int toOctalDigit(Set<Permission> perms) {
        throw new UnsupportedOperationException("TODO");
    }
}
