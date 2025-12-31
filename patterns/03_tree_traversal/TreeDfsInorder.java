package com.framework.patterns.tree_traversal;

import java.util.ArrayList;
import java.util.List;

/**
 * Pattern 14: Tree DFS - Recursive Inorder Traversal
 */
public class TreeDfsInorder {

    // Definition for a binary tree node.
    static class TreeNode {
        int val;
        TreeNode left;
        TreeNode right;
        TreeNode(int x) { val = x; }
    }

    /*
     * =================================================================================
     * CONDITIONS FOR APPLICABILITY
     * =================================================================================
     *
     * 1.  Input Data Structure: A tree or a graph.
     *
     * 2.  Problem Goal: You need to explore nodes in a "left-first, root-last" manner. Inorder
     *     traversal is most famously used with a Binary Search Tree (BST), as it visits the
     *     nodes in ascending sorted order.
     *
     * 3.  Logic: Depth-First Search (DFS) implemented with recursion. The order of operations
     *     for Inorder is: **Left -> Root -> Right**.
     *
     *     - The recursive function takes a node as input.
     *     - The base case for the recursion is when the node is null.
     *     - Make a recursive call on the node's **left** child.
     *     - **Process the current node** (e.g., add its value to a list).
     *     - Make a recursive call on the node's **right** child.
     *
     * =================================================================================
     * GENERIC TEMPLATE (Recursive Inorder Traversal)
     * =================================================================================
     */

    /**
     * Performs an inorder traversal on a binary tree.
     *
     * @param root The root of the binary tree.
     * @return A list of node values in inorder.
     */
    public List<Integer> inorderTraversal(TreeNode root) {
        List<Integer> result = new ArrayList<>();
        traverse(root, result);
        return result;
    }

    /**
     * Helper function for the recursive traversal.
     *
     * @param node   The current node being visited.
     * @param result The list to store the traversal results.
     */
    private void traverse(TreeNode node, List<Integer> result) {
        // --- Core Pattern Logic: Base Case ---
        if (node == null) {
            return;
        }
        // -------------------------------------

        // --- Core Pattern Logic: Inorder (Left -> Root -> Right) ---
        // 1. Recursively traverse the left subtree
        traverse(node.left, result);

        // 2. Process the current node (the "Root")
        result.add(node.val);

        // 3. Recursively traverse the right subtree
        traverse(node.right, result);
        // -----------------------------------------------------------
    }
}
