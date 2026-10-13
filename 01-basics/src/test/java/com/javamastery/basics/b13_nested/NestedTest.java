package com.javamastery.basics.b13_nested;

import org.junit.jupiter.api.Test;

import java.lang.reflect.Modifier;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.NoSuchElementException;

import static org.junit.jupiter.api.Assertions.*;

class NestedTest {

    @Test
    void stack() {
        LinkedStack<String> s = new LinkedStack<>();
        assertTrue(s.isEmpty());
        s.push("a");
        s.push("b");
        s.push("c");
        assertEquals(3, s.size());
        assertEquals("c", s.peek());
        assertEquals("c", s.pop());
        assertEquals("b", s.pop());
        assertEquals(1, s.size());
        s.pop();
        assertThrows(NoSuchElementException.class, s::pop);
        assertThrows(NoSuchElementException.class, s::peek);
    }

    @Test
    void iteratesTopToBottom() {
        LinkedStack<Integer> s = new LinkedStack<>();
        for (int i = 1; i <= 4; i++) s.push(i);
        List<Integer> seen = new ArrayList<>();
        for (int x : s) seen.add(x);
        assertEquals(List.of(4, 3, 2, 1), seen);
        var it = s.iterator();
        while (it.hasNext()) it.next();
        assertThrows(NoSuchElementException.class, it::next);
    }

    @Test
    void nodeIsStaticNested() {
        var node = Arrays.stream(LinkedStack.class.getDeclaredClasses())
                .filter(c -> c.getSimpleName().equals("Node")).findFirst();
        assertTrue(node.isPresent(), "объяви вложенный класс Node");
        assertTrue(Modifier.isStatic(node.get().getModifiers()), "Node должен быть static");
        assertTrue(Modifier.isPrivate(node.get().getModifiers()), "Node должен быть private");
    }

    @Test
    void anonymousCounter() {
        EventSource src = new EventSource();
        var errors = src.countEvents("error:");
        var all = src.countEvents("");
        src.emit("error: disk");
        src.emit("info: ok");
        src.emit("error: net");
        assertEquals(2, errors.count());
        assertEquals(3, all.count());
    }
}
