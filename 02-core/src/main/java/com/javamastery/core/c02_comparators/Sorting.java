package com.javamastery.core.c02_comparators;

import java.util.Comparator;
import java.util.List;
import java.util.Map;

/**
 * C02 · Comparable и Comparator, цепочки компараторов, обработка null.
 */
public final class Sorting {
    private Sorting() {}

    /** Сортировка по фамилии, затем по имени, затем по возрасту по УБЫВАНИЮ. Используй Comparator.comparing().thenComparing(). Вернуть новый список. */
    public static List<Person> byNameThenAgeDesc(List<Person> people) {
        throw new UnsupportedOperationException("TODO");
    }

    /** Сортировка по городу, где null-города идут в КОНЦЕ, внутри города — по возрасту по возрастанию. */
    public static List<Person> byCityNullsLast(List<Person> people) {
        throw new UnsupportedOperationException("TODO");
    }

    /**
     * Компаратор строк «как в проводнике» (natural sort): числа внутри строк сравниваются как числа.
     * "file2" &lt; "file10", "a1b2" &lt; "a1b10", "x" &lt; "x1". Регистр учитывается как в String.compareTo.
     * Числа могут быть длиннее long — сравнивай без парсинга (по длине без ведущих нулей, затем лексикографически);
     * при равных числах с разным числом ведущих нулей ("01" vs "1") меньшим считается более короткая запись.
     */
    public static Comparator<String> naturalOrder() {
        throw new UnsupportedOperationException("TODO");
    }

    /**
     * Топ-k самых частых слов. При равной частоте — по алфавиту. Слова сравниваются без учёта регистра
     * (в результат — в нижнем регистре). Разделители — всё, что не буква и не цифра.
     */
    public static List<Map.Entry<String, Integer>> topWords(String text, int k) {
        throw new UnsupportedOperationException("TODO");
    }
}
