package com.framework.patterns.dynamic_programming;

/**
 * Pattern 33: DP - 1D Array (0/1 Knapsack Subset Sum Style)
 */
public class DpZeroOneKnapsack {

    /*
     * =================================================================================
     * CONDITIONS FOR APPLICABILITY
     * =================================================================================
     *
     * 1.  Input Data Structure: An array of items, where each item has certain properties (like weight, value, etc.).
     *
     * 2.  Problem Goal: You need to make a decision for each item: either take it or leave it (0/1 choice).
     *     The goal is to find if a subset of items satisfies a condition (e.g., sums up to a target)
     *     or to maximize/minimize a value subject to a constraint (e.g., max value within a weight capacity).
     *     Crucially, each item can be used **at most once**.
     *     Examples: "Subset Sum", "Partition Equal Subset Sum", "0/1 Knapsack".
     *
     * 3.  Logic (Bottom-Up DP with 1D Array Optimization):
     *     -   A 2D DP table `dp[i][j]` is the standard way to think about it, where `dp[i][j]` could mean
     *         "is it possible to get sum `j` using the first `i` items?".
     *     -   This can be optimized to a 1D DP array. Let `dp[j]` be a boolean indicating if sum `j` is achievable.
     *     -   To prevent using the same item multiple times in the same subset, the inner loop that updates
     *         the `dp` array must iterate **backwards** from the target sum down to the current item's value.
     *     -   The outer loop iterates through each `item` in the input array.
     *     -   For each `item`, the inner loop goes from `j = target` down to `item`.
     *     -   The recurrence is: `dp[j] = dp[j] || dp[j - item]`. This means sum `j` is possible if it was
     *         already possible, OR if sum `j - item` was possible (and we now add the current `item`).
     *         Iterating backwards ensures that `dp[j - item]` reflects the state *before* considering the current `item`.
     *
     * =================================================================================
     * GENERIC TEMPLATE (Partition Equal Subset Sum)
     * =================================================================================
     */

    /**
     * Determines if an array can be partitioned into two subsets with equal sum.
     *
     * @param nums The input array of numbers.
     * @return True if the array can be partitioned, false otherwise.
     */
    public boolean canPartition(int[] nums) {
        int totalSum = 0;
        for (int num : nums) {
            totalSum += num;
        }

        // If the total sum is odd, it's impossible to partition into two equal subsets.
        if (totalSum % 2 != 0) {
            return false;
        }

        int targetSum = totalSum / 2;

        // --- Core Pattern Logic: Initialization ---
        // dp[i] will be true if a subset with sum `i` can be formed.
        boolean[] dp = new boolean[targetSum + 1];
        // Base case: a sum of 0 is always possible (by choosing no elements).
        dp[0] = true;
        // -------------------------------------------

        // --- Core Pattern Logic: Iterate through items and sums ---
        // For each number in the input array...
        for (int num : nums) {
            // ...iterate backwards from the target sum.
            for (int j = targetSum; j >= num; j--) {
                // --- Recurrence Relation ---
                // `dp[j]` becomes true if it was already true, or if we can form `j`
                // by adding the current `num` to a previously possible sum `j - num`.
                dp[j] = dp[j] || dp[j - num];
                // -------------------------
            }
        }
        // ---------------------------------------------------------

        // The final answer is whether the target sum was achievable.
        return dp[targetSum];
    }
}
