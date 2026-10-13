package com.javamastery.core.c02_comparators;

import org.junit.jupiter.api.Test;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Map;

import static org.junit.jupiter.api.Assertions.*;

class SortingTest {

    private final List<Person> people = List.of(
            new Person("Anna", "Tamm", 30, "Tallinn"),
            new Person("Jaan", "Saar", 25, null),
            new Person("Anna", "Tamm", 41, "Tartu"),
            new Person("Mari", "Kask", 35, "Tallinn"),
            new Person("Juri", "Tamm", 20, "Narva"),
            new Person("Olev", "Kask", 50, null));

    @Test
    void byNameThenAgeDesc() {
        List<Person> r = Sorting.byNameThenAgeDesc(people);
        assertEquals(List.of("Mari Kask", "Olev Kask", "Jaan Saar", "Anna Tamm", "Anna Tamm", "Juri Tamm"),
                r.stream().map(p -> p.firstName() + " " + p.lastName()).toList());
        assertEquals(41, r.get(3).age());
        assertEquals(30, r.get(4).age());
    }

    @Test
    void nullsLast() {
        List<Person> r = Sorting.byCityNullsLast(people);
        assertEquals(Arrays.asList("Narva", "Tallinn", "Tallinn", "Tartu", null, null), r.stream().map(Person::city).toList());
        assertEquals(30, r.get(1).age());
        assertEquals(25, r.get(4).age());
    }

    @Test
    void naturalOrder() {
        List<String> files = new ArrayList<>(List.of("file10.txt", "file2.txt", "file1.txt", "file02.txt", "a1b10", "a1b2", "x", "x1",
                "img99999999999999999999999.png", "img100000000000000000000000.png"));
        files.sort(Sorting.naturalOrder());
        assertEquals(List.of("a1b2", "a1b10", "file1.txt", "file2.txt", "file02.txt", "file10.txt",
                "img99999999999999999999999.png", "img100000000000000000000000.png", "x", "x1"), files);
    }

    @Test
    void topWords() {
        String text = "The cat and the hat. THE end, and... cat? Кот кот!";
        List<Map.Entry<String, Integer>> top = Sorting.topWords(text, 3);
        assertEquals(List.of(Map.entry("the", 3), Map.entry("and", 2), Map.entry("cat", 2)), top);
        assertEquals(Map.entry("кот", 2), Sorting.topWords(text, 4).get(3));
    }
}
