package com.javamastery.core.c07_queues;

import org.junit.jupiter.api.Test;

import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

class QueuesTest {

    @Test
    void schedulerPriorityThenFifo() {
        TaskScheduler s = new TaskScheduler();
        s.submit("low-1", 1);
        s.submit("high-1", 5);
        s.submit("low-2", 1);
        s.submit("high-2", 5);
        s.submit("mid", 3);
        for (int i = 0; i < 20; i++) s.submit("bulk-" + i, 2);
        assertEquals(25, s.size());
        List<String> order = s.drain().stream().map(TaskScheduler.Task::name).toList();
        assertEquals(List.of("high-1", "high-2", "mid"), order.subList(0, 3));
        for (int i = 0; i < 20; i++) assertEquals("bulk-" + i, order.get(3 + i));
        assertEquals(List.of("low-1", "low-2"), order.subList(23, 25));
        assertNull(s.poll());
    }

    @Test
    void slidingMax() {
        assertArrayEquals(new int[]{3, 3, 5, 5, 6, 7}, QueueAlgorithms.slidingWindowMax(new int[]{1, 3, -1, -3, 5, 3, 6, 7}, 3));
        assertArrayEquals(new int[]{1}, QueueAlgorithms.slidingWindowMax(new int[]{1}, 1));
        assertArrayEquals(new int[]{4, 2, 3}, QueueAlgorithms.slidingWindowMax(new int[]{4, 2, 1, 3}, 2));
    }

    @Test
    void distinct() {
        assertEquals(List.of(3, 1, 2), QueueAlgorithms.distinctKeepOrder(List.of(3, 1, 3, 2, 1)));
    }

    @Test
    void topK() {
        assertEquals(List.of(9, 7, 7), QueueAlgorithms.topK(new int[]{1, 7, 3, 9, 7, 2}, 3));
        assertEquals(List.of(2, 1), QueueAlgorithms.topK(new int[]{1, 2}, 5));
    }

    @Test
    void balanced() {
        assertTrue(QueueAlgorithms.balanced("{[()()]}"));
        assertTrue(QueueAlgorithms.balanced("a(b)c"));
        assertFalse(QueueAlgorithms.balanced("([)]"));
        assertFalse(QueueAlgorithms.balanced("(("));
        assertFalse(QueueAlgorithms.balanced(")("));
    }

    @Test
    void mergeK() {
        assertEquals(List.of(1, 1, 2, 3, 4, 4, 5, 6),
                QueueAlgorithms.mergeKSorted(List.of(List.of(1, 4, 5), List.of(1, 3, 4), List.of(2, 6), List.of())));
    }
}
