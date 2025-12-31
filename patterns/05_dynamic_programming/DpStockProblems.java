package com.framework.patterns.dynamic_programming;

/**
 * Pattern 41: DP - Stock problems
 */
public class DpStockProblems {

    /*
     * =================================================================================
     * CONDITIONS FOR APPLICABILITY
     * =================================================================================
     *
     * 1.  Input Data Structure: An array of prices, where `prices[i]` is the price of a stock on day `i`.
     *
     * 2.  Problem Goal: You need to find the maximum profit that can be made by buying and selling the stock.
     *     There are many variations:
     *     -   Best Time to Buy and Sell Stock I: One transaction allowed.
     *     -   Best Time to Buy and Sell Stock II: Unlimited transactions allowed.
     *     -   Best Time to Buy and Sell Stock III: At most two transactions allowed.
     *     -   Best Time to Buy and Sell Stock IV: At most `k` transactions allowed.
     *     -   Best Time to Buy and Sell Stock with Cooldown: A one-day cooldown period after selling.
     *     -   Best Time to Buy and Sell Stock with Transaction Fee: A fee is applied to each transaction.
     *
     * 3.  Logic (General State Machine Approach):
     *     -   Most stock problems can be modeled using states. For any given day `i`, you can be in one of two states:
     *         1.  `held`: You are holding a share of stock.
     *         2.  `not_held`: You are not holding a share of stock.
     *     -   The goal is to find the maximum profit in the `not_held` state on the last day.
     *     -   The transitions between states define the recurrence relation:
     *         -   `held[i]`: The max profit on day `i` while holding a stock. You could have either:
     *             a) Held the stock from day `i-1`: `held[i-1]`
     *             b) Bought the stock today: `not_held[i-1] - prices[i]`
     *             `held[i] = max(held[i-1], not_held[i-1] - prices[i])`
     *         -   `not_held[i]`: The max profit on day `i` without holding a stock. You could have either:
     *             a) Not held the stock from day `i-1`: `not_held[i-1]`
     *             b) Sold the stock today: `held[i-1] + prices[i]`
     *             `not_held[i] = max(not_held[i-1], held[i-1] + prices[i])`
     *     -   Variations (like cooldowns, transaction limits) add complexity to these state transitions.
     *
     * =================================================================================
     * GENERIC TEMPLATE (Best Time to Buy and Sell Stock with Cooldown)
     * =================================================================================
     */

    /**
     * Calculates the maximum profit from buying and selling stocks, with a one-day cooldown after selling.
     *
     * @param prices An array of stock prices for each day.
     * @return The maximum profit.
     */
    public int maxProfitWithCooldown(int[] prices) {
        if (prices == null || prices.length < 2) {
            return 0;
        }

        // --- State Initialization (Space Optimized) ---
        // `held` represents the max profit if we are holding a stock today.
        // Initialize to a very small number to ensure the first buy is `0 - price`.
        int held = -prices[0];

        // `not_held` represents the max profit if we are not holding a stock today and can buy.
        int not_held = 0;

        // `cooldown` represents the max profit if we just sold and are in a cooldown period.
        int cooldown = 0;
        // ----------------------------------------------

        // --- State Transition Loop ---
        for (int i = 1; i < prices.length; i++) {
            int prev_held = held;
            int prev_not_held = not_held;
            int prev_cooldown = cooldown;

            // --- Recurrence Relations ---
            // To be in the `held` state today, we either held yesterday or bought from the `not_held` state.
            held = Math.max(prev_held, prev_not_held - prices[i]);

            // To be in the `not_held` state today, we must have been in `not_held` or `cooldown` yesterday.
            not_held = Math.max(prev_not_held, prev_cooldown);

            // To be in the `cooldown` state today, we must have sold from the `held` state yesterday.
            cooldown = prev_held + prices[i];
            // --------------------------
        }
        // ---------------------------

        // The final answer is the max profit on the last day, which can only be in a `not_held` or `cooldown` state.
        return Math.max(not_held, cooldown);
    }
}
