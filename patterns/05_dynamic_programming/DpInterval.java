package com.framework.patterns.dynamic_programming;

/**
 * Pattern 38: DP - Interval DP
 */
public class DpInterval {

    /*
     * =================================================================================
     * CONDITIONS FOR APPLICABILITY
     * =================================================================================
     *
     * 1.  Input Data Structure: A sequence or array.
     *
     * 2.  Problem Goal: You need to find an optimal value (max or min) over an interval `[i, j]`.
     *     The problem has optimal substructure, meaning the solution for interval `[i, j]` can be
     *     derived by combining solutions for smaller, contained sub-intervals.
     *     Examples: "Burst Balloons", "Minimum Cost to Triangulate Polygon", "Guess Number Higher or Lower II".
     *
     * 3.  Recurrence Relation: The key is to define `dp[i][j]` as the optimal solution for the interval
     *     from index `i` to `j`. You then iterate through all possible split points `k` within the interval `[i, j]`.
     *     The recurrence relation typically looks like:
     *     `dp[i][j] = max/min (dp[i][k] + dp[k][j] + cost_of_combining)` for `k` from `i` to `j`.
     *
     * 4.  Logic (Bottom-Up DP):
     *     -   The DP table `dp[i][j]` stores the result for the interval `[i, j]`.
     *     -   The iteration order is crucial. You must solve for smaller intervals before larger ones.
     *     -   The outer loop iterates over the `length` of the interval, from 2 up to `n`.
     *     -   The next loop iterates over the `start` index `i` of the interval.
     *     -   The `end` index `j` is calculated as `i + length - 1`.
     *     -   The innermost loop iterates over the split point `k` from `i` to `j-1` to calculate `dp[i][j]`.
     *
     * =================================================================================
     * GENERIC TEMPLATE (Burst Balloons)
     * =================================================================================
     */

    /**
     * Calculates the maximum coins you can collect by bursting the balloons.
     *
     * @param nums An array of balloon values.
     * @return The maximum coins collected.
     */
    public int maxCoins(int[] nums) {
        // Add virtual balloons with value 1 at both ends.
        int n = nums.length;
        int[] paddedNums = new int[n + 2];
        paddedNums[0] = 1;
        paddedNums[n + 1] = 1;
        for (int i = 0; i < n; i++) {
            paddedNums[i + 1] = nums[i];
        }

        // dp[i][j] = max coins from bursting balloons in the open interval (i, j).
        int[][] dp = new int[n + 2][n + 2];

        // --- Core Pattern Logic: Iterate by interval length ---
        // `len` is the length of the interval of balloons to be burst.
        for (int len = 1; len <= n; len++) {
            // `i` is the left boundary of the interval.
            for (int i = 1; i <= n - len + 1; i++) {
                int j = i + len - 1; // `j` is the right boundary.

                // --- Recurrence Relation ---
                // `k` is the last balloon to be burst in the interval (i, j).
                for (int k = i; k <= j; k++) {
                    int coins = paddedNums[i - 1] * paddedNums[k] * paddedNums[j + 1];
                    int remainingCoins = dp[i][k - 1] + dp[k + 1][j];
                    dp[i][j] = Math.max(dp[i][j], coins + remainingCoins);
                }
                // -------------------------
            }
        }
        // -----------------------------------------------------

        return dp[1][n];
    }
}
