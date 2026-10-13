package com.javamastery.algorithms.common;

/** Узел бинарного дерева (как на LeetCode). Готов. */
public class TreeNode {
    public int val;
    public TreeNode left, right;

    public TreeNode(int val) {
        this.val = val;
    }

    public TreeNode(int val, TreeNode left, TreeNode right) {
        this.val = val;
        this.left = left;
        this.right = right;
    }

    /** Построить дерево из массива в формате LeetCode (level order, null — пропуск): [3,9,20,null,null,15,7]. */
    public static TreeNode of(Integer... values) {
        if (values.length == 0 || values[0] == null) return null;
        TreeNode root = new TreeNode(values[0]);
        java.util.ArrayDeque<TreeNode> q = new java.util.ArrayDeque<>();
        q.add(root);
        int i = 1;
        while (i < values.length) {
            TreeNode n = q.poll();
            if (i < values.length && values[i] != null) { n.left = new TreeNode(values[i]); q.add(n.left); }
            i++;
            if (i < values.length && values[i] != null) { n.right = new TreeNode(values[i]); q.add(n.right); }
            i++;
        }
        return root;
    }

    public static TreeNode find(TreeNode root, int val) {
        if (root == null) return null;
        if (root.val == val) return root;
        TreeNode l = find(root.left, val);
        return l != null ? l : find(root.right, val);
    }
}
