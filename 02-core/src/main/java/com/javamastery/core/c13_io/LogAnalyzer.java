package com.javamastery.core.c13_io;

import java.io.IOException;
import java.nio.file.Path;
import java.util.List;
import java.util.Map;

/**
 * C13 · Ввод-вывод: java.nio.file.Files, Path, BufferedReader, try-with-resources.
 *
 * <p>Формат строки лога: {@code 2026-10-13T14:00:01 LEVEL [component] message}.
 * Пример: {@code 2026-10-13T14:00:01 ERROR [db] Connection refused}.
 * Некорректные строки (не по формату) пропускаются.
 */
public final class LogAnalyzer {
    private LogAnalyzer() {}

    public record Entry(String timestamp, String level, String component, String message) {}

    /** Разобрать строку, null — если не по формату. Уровни: TRACE, DEBUG, INFO, WARN, ERROR. */
    public static Entry parseLine(String line) {
        throw new UnsupportedOperationException("TODO");
    }

    /** Количество строк по уровням. Читай файл ЛЕНИВО: Files.lines в try-with-resources (файл может быть огромным). */
    public static Map<String, Long> countByLevel(Path log) throws IOException {
        throw new UnsupportedOperationException("TODO");
    }

    /** Записать все ERROR-строки (как есть) в файл out (перезаписать). Вернуть их количество. */
    public static int extractErrors(Path log, Path out) throws IOException {
        throw new UnsupportedOperationException("TODO");
    }

    /** Топ-n компонентов по числу WARN+ERROR (при равенстве — по имени). */
    public static List<String> noisiestComponents(Path log, int n) throws IOException {
        throw new UnsupportedOperationException("TODO");
    }

    /** Суммарный размер всех файлов *.log в каталоге и всех подкаталогах (Files.walk). */
    public static long totalLogSize(Path dir) throws IOException {
        throw new UnsupportedOperationException("TODO");
    }
}
