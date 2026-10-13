package com.javamastery.basics.b14_immutability;

import org.junit.jupiter.api.Test;

import java.lang.reflect.Field;
import java.lang.reflect.Modifier;
import java.time.LocalDate;
import java.util.*;

import static org.junit.jupiter.api.Assertions.*;

class EmployeeTest {

    private Employee create(List<String> skills, Map<String, Integer> ratings, Date hired) {
        return new Employee("Ann", skills, ratings, hired, LocalDate.of(1995, 5, 1), 3000);
    }

    @Test
    void defensiveCopyOnInput() {
        List<String> skills = new ArrayList<>(List.of("java"));
        Map<String, Integer> ratings = new HashMap<>(Map.of("2025", 5));
        Date hired = new Date(1_000_000L);
        Employee e = create(skills, ratings, hired);
        skills.add("cobol");
        ratings.put("2026", 1);
        hired.setTime(0);
        assertEquals(List.of("java"), e.skills());
        assertEquals(Map.of("2025", 5), e.ratings());
        assertEquals(1_000_000L, e.hiredAt().getTime());
    }

    @Test
    void cannotModifyViaGetters() {
        Employee e = create(List.of("java"), Map.of("2025", 5), new Date(1_000_000L));
        assertThrows(UnsupportedOperationException.class, () -> e.skills().add("x"));
        assertThrows(UnsupportedOperationException.class, () -> e.ratings().put("x", 1));
        e.hiredAt().setTime(42);
        assertEquals(1_000_000L, e.hiredAt().getTime());
    }

    @Test
    void withers() {
        Employee e = create(List.of("java"), Map.of(), new Date(5));
        Employee richer = e.withSalary(5000);
        Employee skilled = e.withSkill("spring");
        assertEquals(3000, e.salary());
        assertEquals(5000, richer.salary());
        assertEquals(List.of("java"), e.skills());
        assertEquals(List.of("java", "spring"), skilled.skills());
        assertNotSame(e, richer);
    }

    @Test
    void structure() {
        assertTrue(Modifier.isFinal(Employee.class.getModifiers()), "класс должен быть final");
        for (Field f : Employee.class.getDeclaredFields()) {
            if (f.isSynthetic()) continue;
            assertTrue(Modifier.isPrivate(f.getModifiers()) && Modifier.isFinal(f.getModifiers()),
                    "поле " + f.getName() + " должно быть private final");
        }
    }
}
