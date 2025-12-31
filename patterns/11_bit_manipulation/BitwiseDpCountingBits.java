package com.framework.patterns.bit_manipulation;

/**
 * Pattern 72: Bitwise DP - Counting Bits Optimization
 */
public class BitwiseDpCountingBits {

    /*
     * =================================================================================
     * CONDITIONS FOR APPLICABILITY
     * =================================================================================
     *
     * 1.  Problem Goal: You need to compute the number of set bits for every number from 0 to `n`
     *     and return the results in an array.
     *
     * 2.  Logic: A naive approach would be to call the `hammingWeight` function (from Pattern 71)
     *     for each number up to `n`. This would be O(n * k) where k is the number of bits.
     *     A more efficient approach uses Dynamic Programming combined with bitwise observations.
     *
     *     -   **DP State**: Let `dp[i]` be the number of set bits in the integer `i`.
     *     -   **Recurrence Relation Insight**: There are several ways to derive the recurrence:
     *         1.  **Using the LSB**: The number of set bits in `i` is the number of set bits in `i / 2`
     *             (which is `i >> 1`) plus 1 if `i` is odd. `dp[i] = dp[i >> 1] + (i & 1)`.
     *         2.  **Using `n & (n-1)`**: The number of set bits in `i` is the number of set bits in
     *             `i & (i-1)` plus 1. `dp[i] = dp[i & (i-1)] + 1`.
     *         3.  **Using Powers of 2**: The number of set bits in `i` is `1 +` the number of set bits
     *             in `i - offset`, where `offset` is the largest power of 2 less than or equal to `i`.
     *             `dp[i] = dp[i - offset] + 1`.
     *
     *     -   **Algorithm (using LSB approach)**:
     *         -   Create a `dp` array of size `n + 1`.
     *         -   `dp[0]` is 0.
     *         -   Iterate from `i = 1` to `n`.
     *         -   For each `i`, calculate `dp[i]` using the recurrence: `dp[i] = dp[i >> 1] + (i & 1)`.
     *         -   This is very efficient as it builds the solution for `i` from a previously computed value.
     *
     * =================================================================================
     * GENERIC TEMPLATE (Counting Bits)
     * =================================================================================
     */

    /**
     * Counts the number of set bits for each number from 0 to n.
     *
     * @param n The upper bound (inclusive).
     * @return An array where `ans[i]` is the number of set bits in `i`.
     */
    public int[] countBits(int n) {
        // dp[i] will store the number of set bits for integer i.
        int[] dp = new int[n + 1];

        // Base case is dp[0] = 0, which is handled by default initialization.

        // --- Core Pattern Logic: Bottom-up DP with bitwise insight ---
        for (int i = 1; i <= n; i++) {
            // --- Recurrence Relation ---
            // The number of set bits in `i` is the number of set bits in `i/2` (i >> 1)
            // plus 1 if the last bit of `i` is set (i & 1).
            dp[i] = dp[i >> 1] + (i & 1);
            // -------------------------
        }
        // -----------------------------------------------------------

        return dp;
    }
}
