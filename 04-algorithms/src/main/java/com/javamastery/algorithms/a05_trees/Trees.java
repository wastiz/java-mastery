package com.javamastery.algorithms.a05_trees;

import com.javamastery.algorithms.common.TreeNode;

import java.util.List;

/**
 * A05 · Деревья: DFS (рекурсия), BFS (очередь), BST.
 */
public final class Trees {
    private Trees() {}

    /** LeetCode 104. Глубина дерева. */
    public static int maxDepth(TreeNode root) {
        throw new UnsupportedOperationException("TODO");
    }

    /** LeetCode 94. Inorder-обход (левый, корень, правый) — ИТЕРАТИВНО, со стеком. */
    public static List<Integer> inorder(TreeNode root) {
        throw new UnsupportedOperationException("TODO");
    }

    /** LeetCode 102. Обход по уровням (BFS). */
    public static List<List<Integer>> levelOrder(TreeNode root) {
        throw new UnsupportedOperationException("TODO");
    }

    /** LeetCode 98. Является ли дерево корректным BST (строгое неравенство). Осторожно с Integer.MIN/MAX_VALUE в узлах! */
    public static boolean isValidBST(TreeNode root) {
        throw new UnsupportedOperationException("TODO");
    }

    // TODO: вспомогательные методы

    /** LeetCode 226. Отразить дерево (зеркально). Вернуть корень. */
    public static TreeNode invert(TreeNode root) {
        throw new UnsupportedOperationException("TODO");
    }

    /** LeetCode 236. Наименьший общий предок двух узлов (в обычном бинарном дереве). */
    public static TreeNode lowestCommonAncestor(TreeNode root, TreeNode p, TreeNode q) {
        throw new UnsupportedOperationException("TODO");
    }

    /** LeetCode 124. Максимальная сумма пути (путь может начинаться и заканчиваться в любых узлах). Hard. */
    public static int maxPathSum(TreeNode root) {
        throw new UnsupportedOperationException("TODO");
    }

    /** LeetCode 297 (упрощённо). Сериализовать дерево в строку и обратно. Формат любой — тест проверяет только round-trip. */
    public static String serialize(TreeNode root) {
        throw new UnsupportedOperationException("TODO");
    }

    public static TreeNode deserialize(String data) {
        throw new UnsupportedOperationException("TODO");
    }

    // TODO: вспомогательные методы сериализации
}
