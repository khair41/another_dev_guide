package com.framework.patterns.tree_traversal;

/**
 * Pattern 16: Tree - Lowest Common Ancestor (LCA) Finding
 */
public class TreeLowestCommonAncestor {

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
     * 1.  Input Data Structure: A binary tree (though the concept extends to other trees).
     *     It's important to know if it's a regular Binary Tree or a Binary Search Tree (BST),
     *     as the solution for a BST is much simpler.
     *
     * 2.  Problem Goal: You need to find the lowest (i.e., deepest) node in the tree that has
     *     two other given nodes, `p` and `q`, as descendants.
     *
     * 3.  Logic (for a regular Binary Tree): The most elegant solution uses a recursive DFS approach.
     *     The recursive function returns the LCA if it's found in the current subtree, otherwise null.
     *
     *     - Base Case: If the current node is `null`, or if it is `p` or `q`, return the current node.
     *       (If we find `p` or `q`, it could be the LCA itself).
     *
     *     - Recursive Step: Recursively call the function on the left and right children.
     *         - `leftLCA = findLCA(node.left, p, q)`
     *         - `rightLCA = findLCA(node.right, p, q)`
     *
     *     - Combine Results:
     *         - If `leftLCA` and `rightLCA` are BOTH non-null, it means `p` was found in one
     *           subtree and `q` in the other. Therefore, the **current node** is the LCA.
     *         - If only one of them is non-null, it means both `p` and `q` are in that one
     *           subtree. So, we propagate that non-null result up the call stack.
     *         - If both are null, neither `p` nor `q` were found in this subtree, so return null.
     *
     * =================================================================================
     * GENERIC TEMPLATE (LCA for a Binary Tree)
     * =================================================================================
     */

    /**
     * Finds the lowest common ancestor of two nodes in a binary tree.
     *
     * @param root The root of the binary tree.
     * @param p    The first node.
     * @param q    The second node.
     * @return The lowest common ancestor node.
     */
    public TreeNode lowestCommonAncestor(TreeNode root, TreeNode p, TreeNode q) {
        // --- Core Pattern Logic: Base Cases ---
        // 1. If the current node is null, we've reached the end of a branch.
        // 2. If the current node is one of the nodes we are looking for, it is a potential LCA.
        if (root == null || root == p || root == q) {
            return root;
        }
        // ---------------------------------------

        // --- Core Pattern Logic: Recursive Step ---
        // Search for p and q in the left and right subtrees.
        TreeNode leftLCA = lowestCommonAncestor(root.left, p, q);
        TreeNode rightLCA = lowestCommonAncestor(root.right, p, q);
        // -----------------------------------------

        // --- Core Pattern Logic: Combine Results ---
        // Case 1: p and q are found in different subtrees (left and right).
        // This means the current `root` is their first common ancestor.
        if (leftLCA != null && rightLCA != null) {
            return root;
        }

        // Case 2: Both p and q are in the same subtree.
        // The non-null return value from that subtree is the LCA.
        // Propagate this result up the recursion stack.
        return leftLCA != null ? leftLCA : rightLCA;
        // -------------------------------------------
    }
}
