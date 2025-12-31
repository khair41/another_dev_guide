package com.framework.patterns.tree_traversal;

import java.util.ArrayList;
import java.util.List;

/**
 * Pattern 13: Tree DFS - Recursive Preorder Traversal
 */
public class TreeDfsPreorder {

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
     * 2.  Problem Goal: You need to explore nodes in a "root-first" manner. Preorder traversal
     *     is used when you need to process the root node before its descendants. This is common
     *     for tasks like creating a copy of a tree, or serializing a tree to a string/array
     *     where the parent node's value appears before its children.
     *
     * 3.  Logic: Depth-First Search (DFS) is naturally implemented using recursion (which uses the call stack).
     *     The order of operations for Preorder is: **Root -> Left -> Right**.
     *
     *     - The recursive function takes a node as input.
     *     - The base case for the recursion is when the node is null.
     *     - **Process the current node** (e.g., add its value to a list).
     *     - Make a recursive call on the node's **left** child.
     *     - Make a recursive call on the node's **right** child.
     *
     * =================================================================================
     * GENERIC TEMPLATE (Recursive Preorder Traversal)
     * =================================================================================
     */

    /**
     * Performs a preorder traversal on a binary tree.
     *
     * @param root The root of the binary tree.
     * @return A list of node values in preorder.
     */
    public List<Integer> preorderTraversal(TreeNode root) {
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

        // --- Core Pattern Logic: Preorder (Root -> Left -> Right) ---
        // 1. Process the current node (the "Root")
        result.add(node.val);

        // 2. Recursively traverse the left subtree
        traverse(node.left, result);

        // 3. Recursively traverse the right subtree
        traverse(node.right, result);
        // -----------------------------------------------------------
    }
}
