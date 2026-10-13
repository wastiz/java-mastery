package com.javamastery.core.c14_memory;

import org.junit.jupiter.api.Test;

import java.lang.ref.WeakReference;
import java.util.ArrayList;
import java.util.List;
import java.util.function.Consumer;

import static org.junit.jupiter.api.Assertions.*;

class MemoryTest {

    private static boolean collected(WeakReference<?> ref) throws InterruptedException {
        for (int i = 0; i < 100 && ref.get() != null; i++) {
            System.gc();
            Thread.sleep(10);
        }
        return ref.get() == null;
    }

    @Test
    void stackDoesNotLeak() throws Exception {
        LeakyStack<Object> s = new LeakyStack<>();
        Object payload = new byte[1 << 20];
        WeakReference<Object> ref = new WeakReference<>(payload);
        s.push(payload);
        s.push("other");
        s.pop();
        s.pop();
        payload = null;
        assertTrue(collected(ref), "стек всё ещё держит ссылку на вытолкнутый объект");
        assertEquals(0, s.size());
    }

    @Test
    void registryUsesWeakRefs() throws Exception {
        ListenerRegistry<String> r = new ListenerRegistry<>();
        List<String> got = new ArrayList<>();
        Consumer<String> kept = got::add;
        Consumer<String> dropped = e -> got.add("dropped:" + e);
        r.register(kept);
        r.register(dropped);
        r.publish("a");
        assertEquals(2, got.size());
        WeakReference<Object> ref = new WeakReference<>(dropped);
        dropped = null;
        assertTrue(collected(ref), "реестр не должен удерживать слушателя от сборки мусора");
        assertEquals(1, r.size());
        r.publish("b");
        assertEquals("b", got.get(got.size() - 1));
        r.unregister(kept);
        assertEquals(0, r.size());
    }

    @Test
    void quiz() {
        int[] answers = {JvmQuiz.Q1, JvmQuiz.Q2, JvmQuiz.Q3, JvmQuiz.Q4, JvmQuiz.Q5, JvmQuiz.Q6, JvmQuiz.Q7, JvmQuiz.Q8, JvmQuiz.Q9, JvmQuiz.Q10};
        // ответы закодированы, чтобы не подсматривать :)
        int[] hashed = {22, 22, 11, 22, 33, 33, 22, 22, 22, 22};
        List<String> wrong = new ArrayList<>();
        for (int i = 0; i < answers.length; i++) if (answers[i] * 11 != hashed[i]) wrong.add("Q" + (i + 1));
        assertTrue(wrong.isEmpty(), "неверные ответы: " + wrong + " — перечитай теорию C14");
    }
}
