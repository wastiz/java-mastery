package com.javamastery.basics.b12_exceptions;

import org.junit.jupiter.api.Test;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.atomic.AtomicInteger;

import static org.junit.jupiter.api.Assertions.*;

class ResourcesTest {

    @Test
    void closesInReverseOrder() {
        List<String> log = new ArrayList<>();
        Resources.useTwo(log, false, false);
        assertEquals(List.of("open A", "open B", "use A", "use B", "close B", "close A"), log);
    }

    @Test
    void suppressedCloseException() {
        List<String> log = new ArrayList<>();
        var ex = assertThrows(IllegalStateException.class, () -> Resources.useTwo(log, true, true));
        assertEquals("use failed: B", ex.getMessage());
        assertEquals(1, ex.getSuppressed().length);
        assertEquals("close failed: A", ex.getSuppressed()[0].getMessage());
        assertEquals("close A", log.get(log.size() - 1));
    }

    @Test
    void closeFailureAlone() {
        List<String> log = new ArrayList<>();
        var ex = assertThrows(IllegalStateException.class, () -> Resources.useTwo(log, false, true));
        assertEquals("close failed: A", ex.getMessage());
    }

    @Test
    void retrySucceeds() throws Exception {
        AtomicInteger calls = new AtomicInteger();
        String r = Resources.retry(() -> {
            if (calls.incrementAndGet() < 3) throw new IOException("flaky " + calls.get());
            return "ok";
        }, 5);
        assertEquals("ok", r);
        assertEquals(3, calls.get());
    }

    @Test
    void retryGivesUp() {
        AtomicInteger calls = new AtomicInteger();
        var ex = assertThrows(IOException.class, () -> Resources.retry(() -> {
            throw new IOException("attempt " + calls.incrementAndGet());
        }, 3));
        assertEquals("attempt 3", ex.getMessage());
        assertEquals(2, ex.getSuppressed().length);
        assertEquals("attempt 1", ex.getSuppressed()[0].getMessage());
        assertEquals(3, calls.get());
        assertThrows(IllegalArgumentException.class, () -> Resources.retry(() -> 1, 0));
    }

    // ===== Вопросы-ловушки: впиши свои ответы вместо -1 =====
    static final int MY_ANSWER_TRICKY = -1;
    static final int MY_ANSWER_TRICKIER = -1;

    @Test
    void trickyQuestions() {
        assertEquals(Resources.tricky(), MY_ANSWER_TRICKY, "Что вернёт tricky()? Подумай без запуска, а потом впиши ответ в MY_ANSWER_TRICKY");
        assertEquals(Resources.trickier(), MY_ANSWER_TRICKIER, "Что вернёт trickier()? Куда делось исключение?");
    }
}
