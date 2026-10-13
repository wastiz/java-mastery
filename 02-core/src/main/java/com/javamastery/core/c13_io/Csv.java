package com.javamastery.core.c13_io;

import java.io.IOException;
import java.io.Reader;
import java.io.Writer;
import java.util.List;

/**
 * C13 · Мини CSV-парсер по RFC 4180 (это частая «домашка» на собесах).
 *
 * <ul>
 *   <li>разделитель — запятая, строки — \n или \r\n;</li>
 *   <li>поле в двойных кавычках может содержать запятые, переносы строк и экранированные кавычки ("");</li>
 *   <li>пустые поля — пустые строки; пустая последняя строка (перевод строки в конце файла) — игнорируется.</li>
 * </ul>
 * Читать посимвольно из Reader (BufferedReader внутри — по желанию).
 */
public final class Csv {
    private Csv() {}

    public static List<List<String>> read(Reader in) throws IOException {
        throw new UnsupportedOperationException("TODO");
    }

    /** Записать строки; поле экранируется кавычками, только если содержит запятую, кавычку или перевод строки. Строки разделяются \n, в конце тоже \n. */
    public static void write(List<List<String>> rows, Writer out) throws IOException {
        throw new UnsupportedOperationException("TODO");
    }
}
