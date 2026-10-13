package com.javamastery.algorithms.a05_trees;

import com.javamastery.algorithms.common.TreeNode;
import org.junit.jupiter.api.Test;

import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

class TreesTest {

    @Test
    void depth() {
        assertEquals(3, Trees.maxDepth(TreeNode.of(3, 9, 20, null, null, 15, 7)));
        assertEquals(0, Trees.maxDepth(null));
    }

    @Test
    void inorder() {
        assertEquals(List.of(1, 3, 2), Trees.inorder(TreeNode.of(1, null, 2, 3)));
        assertEquals(List.of(4, 2, 5, 1, 3), Trees.inorder(TreeNode.of(1, 2, 3, 4, 5)));
    }

    @Test
    void levels() {
        assertEquals(List.of(List.of(3), List.of(9, 20), List.of(15, 7)), Trees.levelOrder(TreeNode.of(3, 9, 20, null, null, 15, 7)));
        assertEquals(List.of(), Trees.levelOrder(null));
    }

    @Test
    void bst() {
        assertTrue(Trees.isValidBST(TreeNode.of(2, 1, 3)));
        assertFalse(Trees.isValidBST(TreeNode.of(5, 1, 4, null, null, 3, 6)));
        assertFalse(Trees.isValidBST(TreeNode.of(5, 4, 6, null, null, 3, 7)));
        assertTrue(Trees.isValidBST(TreeNode.of(Integer.MAX_VALUE)));
        assertTrue(Trees.isValidBST(TreeNode.of(Integer.MIN_VALUE, null, Integer.MAX_VALUE)));
        assertFalse(Trees.isValidBST(TreeNode.of(1, 1)));
    }

    @Test
    void invert() {
        assertEquals(List.of(List.of(4), List.of(7, 2), List.of(9, 6, 3, 1)),
                Trees.levelOrder(Trees.invert(TreeNode.of(4, 2, 7, 1, 3, 6, 9))));
    }

    @Test
    void lca() {
        TreeNode root = TreeNode.of(3, 5, 1, 6, 2, 0, 8, null, null, 7, 4);
        assertEquals(3, Trees.lowestCommonAncestor(root, TreeNode.find(root, 5), TreeNode.find(root, 1)).val);
        assertEquals(5, Trees.lowestCommonAncestor(root, TreeNode.find(root, 5), TreeNode.find(root, 4)).val);
    }

    @Test
    void maxPath() {
        assertEquals(6, Trees.maxPathSum(TreeNode.of(1, 2, 3)));
        assertEquals(42, Trees.maxPathSum(TreeNode.of(-10, 9, 20, null, null, 15, 7)));
        assertEquals(-3, Trees.maxPathSum(TreeNode.of(-3)));
    }

    @Test
    void serialization() {
        TreeNode t = TreeNode.of(1, 2, 3, null, null, 4, 5, -6);
        TreeNode back = Trees.deserialize(Trees.serialize(t));
        assertEquals(Trees.levelOrder(t), Trees.levelOrder(back));
        assertEquals(Trees.inorder(t), Trees.inorder(back));
        assertNull(Trees.deserialize(Trees.serialize(null)));
    }
}
