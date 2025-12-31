package com.framework.patterns.tree_traversal;

import java.util.ArrayList;
import java.util.List;

/**
 * Pattern 15: Tree DFS - Recursive Postorder Traversal
 */
public class TreeDfsPostorder {

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
     * 2.  Problem Goal: You need to process a node's children before processing the node itself.
     *     Postorder traversal is used when you need to compute a value for a node based on the
     *     computed values of its children. A classic example is finding the height of a tree or
     *     any problem that involves "bottom-up" calculations.
     *
     * 3.  Logic: Depth-First Search (DFS) implemented with recursion. The order of operations
     *     for Postorder is: **Left -> Right -> Root**.
     *
     *     - The recursive function takes a node as input.
     *     - The base case for the recursion is when the node is null.
     *     - Make a recursive call on the node's **left** child.
     *     - Make a recursive call on the node's **right** child.
     *     - **Process the current node** (e.g., add its value to a list).
     *
     * =================================================================================
     * GENERIC TEMPLATE (Recursive Postorder Traversal)
     * =================================================================================
     */

    /**
     * Performs a postorder traversal on a binary tree.
     *
     * @param root The root of the binary tree.
     * @return A list of node values in postorder.
     */
    public List<Integer> postorderTraversal(TreeNode root) {
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

        // --- Core Pattern Logic: Postorder (Left -> Right -> Root) ---
        // 1. Recursively traverse the left subtree
        traverse(node.left, result);

        // 2. Recursively traverse the right subtree
        traverse(node.right, result);

        // 3. Process the current node (the "Root")
        result.add(node.val);
        // -------------------------------------------------------------
    }
}
