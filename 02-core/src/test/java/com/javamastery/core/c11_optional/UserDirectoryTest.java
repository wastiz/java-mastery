package com.javamastery.core.c11_optional;

import com.javamastery.core.c11_optional.UserDirectory.*;
import org.junit.jupiter.api.Test;

import java.util.Map;
import java.util.NoSuchElementException;
import java.util.Optional;
import java.util.concurrent.atomic.AtomicInteger;

import static org.junit.jupiter.api.Assertions.*;

class UserDirectoryTest {

    private final UserDirectory dir = new UserDirectory(Map.of(
            1L, new User("boss", "boss@corp.ee", new Address("Tallinn", "10111"), null),
            2L, new User("dev", "dev@gmail.com", new Address(null, "51004"), 1L),
            3L, new User("intern", "intern@corp.ee", null, 99L)));

    @Test
    void city() {
        assertEquals("Tallinn", dir.cityOf(1));
        assertEquals("unknown", dir.cityOf(2));
        assertEquals("unknown", dir.cityOf(3));
        assertEquals("unknown", dir.cityOf(42));
    }

    @Test
    void managerEmail() {
        assertEquals(Optional.of("boss@corp.ee"), dir.managerEmail(2));
        assertEquals(Optional.empty(), dir.managerEmail(1));
        assertEquals(Optional.empty(), dir.managerEmail(3));
        assertEquals(Optional.empty(), dir.managerEmail(42));
    }

    @Test
    void domain() {
        assertEquals(Optional.of("corp.ee"), dir.corporateDomain(3));
        assertEquals(Optional.empty(), dir.corporateDomain(2));
    }

    @Test
    void orElseThrow() {
        assertEquals("dev", dir.loginOrThrow(2));
        var ex = assertThrows(NoSuchElementException.class, () -> dir.loginOrThrow(42));
        assertEquals("User 42 not found", ex.getMessage());
    }

    @Test
    void orElseGetIsLazy() {
        AtomicInteger calls = new AtomicInteger();
        assertEquals("boss", dir.loginOrCompute(1, () -> "x" + calls.incrementAndGet()));
        assertEquals(0, calls.get(), "default не должен вычисляться, если пользователь найден");
        assertEquals("x1", dir.loginOrCompute(7, () -> "x" + calls.incrementAndGet()));
    }
}
