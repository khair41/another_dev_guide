package com.framework.patterns.dynamic_programming;

import java.util.Arrays;

/**
 * Pattern 32: DP - 1D Array (Coin Change / Unbounded Knapsack Style)
 */
public class DpUnboundedKnapsack {

    /*
     * =================================================================================
     * CONDITIONS FOR APPLICABILITY
     * =================================================================================
     *
     * 1.  Input Data Structure: An array of items (e.g., coins, weights) and a target value (e.g., amount, capacity).
     *
     * 2.  Problem Goal: You need to find the minimum number of items, maximum value, or total number of ways
     *     to reach a target value. The key feature is that you have an **unlimited supply** of each item.
     *     This is why it's called "Unbounded Knapsack".
     *     Examples: "Coin Change", "Minimum Cost For Tickets".
     *
     * 3.  Recurrence Relation: The problem can be broken down into subproblems.
     *     Let `dp[i]` be the minimum number of coins to make amount `i`.
     *     To compute `dp[i]`, we can try using each available coin `c`.
     *     If we use coin `c`, the remaining amount is `i - c`. We need `dp[i - c]` coins for the remainder,
     *     plus the one coin `c` we just used. So, we take the minimum over all possible coins.
     *     `dp[i] = min(dp[i], 1 + dp[i - c])` for each coin `c` where `i >= c`.
     *
     * 4.  Logic (Bottom-Up DP):
     *     -   Create a DP array, `dp`, of size `amount + 1`.
     *     -   Initialize the array with a value indicating impossibility (e.g., infinity or `amount + 1`).
     *     -   Set the base case: `dp[0] = 0` (it takes 0 coins to make an amount of 0).
     *     -   Iterate from `i = 1` to `amount`. For each amount `i`:
     *         -   Iterate through each available `coin`.
     *         -   If `i >= coin`, apply the recurrence relation: `dp[i] = min(dp[i], 1 + dp[i - coin])`.
     *     -   The final answer is `dp[amount]`.
     *
     * =================================================================================
     * GENERIC TEMPLATE (Coin Change - Minimum Coins)
     * =================================================================================
     */

    /**
     * Calculates the fewest number of coins needed to make up a certain amount.
     *
     * @param coins An array of available coin denominations.
     * @param amount The target amount.
     * @return The minimum number of coins, or -1 if the amount cannot be made up.
     */
    public int coinChange(int[] coins, int amount) {
        if (amount < 0) {
            return -1;
        }

        // --- Core Pattern Logic: Initialization ---
        // dp[i] will store the minimum number of coins to make amount i.
        int[] dp = new int[amount + 1];
        // Initialize with a value larger than any possible answer.
        Arrays.fill(dp, amount + 1);

        // Base case: 0 coins are needed to make an amount of 0.
        dp[0] = 0;
        // -------------------------------------------

        // --- Core Pattern Logic: Bottom-up calculation ---
        // Iterate through all amounts from 1 to the target amount.
        for (int i = 1; i <= amount; i++) {
            // For each amount, try every coin.
            for (int coin : coins) {
                if (i >= coin) {
                    // --- Recurrence Relation ---
                    // The number of coins for amount `i` is potentially the minimum of its current value
                    // and (1 + the number of coins for the remaining amount `i - coin`).
                    dp[i] = Math.min(dp[i], 1 + dp[i - coin]);
                    // -------------------------
                }
            }
        }
        // ------------------------------------------------

        // If dp[amount] is still our placeholder value, it means the amount is impossible to make.
        return dp[amount] > amount ? -1 : dp[amount];
    }
}
