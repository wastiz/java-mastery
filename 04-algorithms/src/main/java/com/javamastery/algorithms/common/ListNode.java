package com.javamastery.algorithms.common;

/** Узел односвязного списка (как на LeetCode). Готов. */
public class ListNode {
    public int val;
    public ListNode next;

    public ListNode(int val) {
        this.val = val;
    }

    public ListNode(int val, ListNode next) {
        this.val = val;
        this.next = next;
    }

    public static ListNode of(int... values) {
        ListNode dummy = new ListNode(0), cur = dummy;
        for (int v : values) {
            cur.next = new ListNode(v);
            cur = cur.next;
        }
        return dummy.next;
    }

    public static java.util.List<Integer> toList(ListNode head) {
        java.util.List<Integer> r = new java.util.ArrayList<>();
        for (ListNode n = head; n != null && r.size() < 10_000; n = n.next) r.add(n.val);
        return r;
    }
}
