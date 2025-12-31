package com.framework.patterns.dynamic_programming;

/**
 * Pattern 39: DP - Catalan Numbers
 */
public class DpCatalanNumbers {

    /*
     * =================================================================================
     * CONDITIONS FOR APPLICABILITY
     * =================================================================================
     *
     * 1.  Problem Goal: The problem asks to count the number of ways to form a certain structure.
     *     The underlying counting principle follows the Catalan sequence.
     *
     * 2.  Common Problems:
     *     -   Counting the number of unique Binary Search Trees (BSTs) with `n` nodes.
     *     -   Counting the number of valid (well-formed) parentheses expressions with `n` pairs.
     *     -   Counting the number of ways to triangulate a convex polygon with `n+2` sides.
     *     -   Counting the number of full binary trees with `n+1` leaves.
     *
     * 3.  Recurrence Relation: The `n`-th Catalan number, `C_n`, is defined by the recurrence:
     *     `C_n = C_0*C_{n-1} + C_1*C_{n-2} + ... + C_{n-1}*C_0`
     *     In other words: `C_n = sum(C_i * C_{n-1-i})` for `i` from `0` to `n-1`.
     *     The base case is `C_0 = 1`.
     *
     * 4.  Logic (DP Interpretation for Unique BSTs):
     *     -   Let `dp[n]` be the number of unique BSTs with `n` nodes.
     *     -   To form a BST with `n` nodes, we can choose any node `i` (from 1 to `n`) to be the root.
     *     -   If we choose `i` as the root, then `i-1` nodes must go into the left subtree, and `n-i` nodes
     *         must go into the right subtree.
     *     -   The number of ways to form the left subtree is `dp[i-1]`. The number of ways to form the
     *         right subtree is `dp[n-i]`.
     *     -   The total number of BSTs with root `i` is `dp[i-1] * dp[n-i]`.
     *     -   To get the total for `n` nodes, we sum this product over all possible roots `i`.
     *     -   `dp[n] = sum(dp[i-1] * dp[n-i])` for `i` from `1` to `n`.
     *
     * =================================================================================
     * GENERIC TEMPLATE (Unique Binary Search Trees)
     * =================================================================================
     */

    /**
     * Calculates the number of structurally unique BST's (binary search trees) which have
     * exactly `n` nodes of unique values from 1 to `n`.
     *
     * @param n The number of nodes.
     * @return The number of unique BSTs.
     */
    public int numTrees(int n) {
        if (n < 0) return 0;

        // --- Core Pattern Logic: Initialization ---
        // dp[i] will store the number of unique BSTs with i nodes.
        int[] dp = new int[n + 1];

        // Base cases
        dp[0] = 1; // An empty tree is one unique structure.
        if (n > 0) {
            dp[1] = 1; // A single-node tree is one unique structure.
        }
        // -------------------------------------------

        // --- Core Pattern Logic: Bottom-up calculation ---
        // `i` is the number of nodes we are trying to find the count for.
        for (int i = 2; i <= n; i++) {
            // `j` is the root of the tree.
            for (int j = 1; j <= i; j++) {
                // --- Recurrence Relation ---
                // Left subtree has `j-1` nodes.
                // Right subtree has `i-j` nodes.
                dp[i] += dp[j - 1] * dp[i - j];
                // -------------------------
            }
        }
        // ------------------------------------------------

        return dp[n];
    }
}
