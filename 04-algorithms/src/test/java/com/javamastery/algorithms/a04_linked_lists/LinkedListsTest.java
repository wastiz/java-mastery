package com.javamastery.algorithms.a04_linked_lists;

import com.javamastery.algorithms.common.ListNode;
import org.junit.jupiter.api.Test;

import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

class LinkedListsTest {

    @Test
    void reverse() {
        assertEquals(List.of(5, 4, 3, 2, 1), ListNode.toList(LinkedLists.reverse(ListNode.of(1, 2, 3, 4, 5))));
        assertNull(LinkedLists.reverse(null));
        assertEquals(List.of(3, 2, 1), ListNode.toList(LinkedLists.reverseRecursive(ListNode.of(1, 2, 3))));
    }

    @Test
    void cycle() {
        ListNode head = ListNode.of(3, 2, 0, -4);
        assertFalse(LinkedLists.hasCycle(head));
        head.next.next.next.next = head.next;
        assertTrue(LinkedLists.hasCycle(head));
        assertFalse(LinkedLists.hasCycle(null));
    }

    @Test
    void merge() {
        assertEquals(List.of(1, 1, 2, 3, 4, 4), ListNode.toList(LinkedLists.mergeTwo(ListNode.of(1, 2, 4), ListNode.of(1, 3, 4))));
        assertEquals(List.of(0), ListNode.toList(LinkedLists.mergeTwo(null, ListNode.of(0))));
    }

    @Test
    void middle() {
        assertEquals(3, LinkedLists.middle(ListNode.of(1, 2, 3, 4, 5)).val);
        assertEquals(4, LinkedLists.middle(ListNode.of(1, 2, 3, 4, 5, 6)).val);
    }

    @Test
    void removeNth() {
        assertEquals(List.of(1, 2, 3, 5), ListNode.toList(LinkedLists.removeNthFromEnd(ListNode.of(1, 2, 3, 4, 5), 2)));
        assertEquals(List.of(), ListNode.toList(LinkedLists.removeNthFromEnd(ListNode.of(1), 1)));
        assertEquals(List.of(2), ListNode.toList(LinkedLists.removeNthFromEnd(ListNode.of(1, 2), 2)));
    }

    @Test
    void addTwo() {
        assertEquals(List.of(7, 0, 8), ListNode.toList(LinkedLists.addTwoNumbers(ListNode.of(2, 4, 3), ListNode.of(5, 6, 4))));
        assertEquals(List.of(8, 9, 9, 9, 0, 0, 0, 1),
                ListNode.toList(LinkedLists.addTwoNumbers(ListNode.of(9, 9, 9, 9, 9, 9, 9), ListNode.of(9, 9, 9, 9))));
    }
}
