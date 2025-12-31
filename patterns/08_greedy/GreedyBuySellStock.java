5549package com.framework.patterns.greedy;

/**
 * Pattern 55: Greedy - Buy/Sell Stock
 */
public class GreedyBuySellStock {

    /*
     * =================================================================================
     * CONDITIONS FOR APPLICABILITY
     * =================================================================================
     *
     * 1.  Input Data Structure: An array of stock prices.
     *
     * 2.  Problem Goal: You need to maximize profit from buying and selling stocks. This greedy
     *     pattern is particularly applicable to the version where you can complete as many
     *     transactions as you like (i.e., "Best Time to Buy and Sell Stock II").
     *
     * 3.  Logic: The greedy insight is that you should capture every opportunity for profit, no matter
     *     how small. You don't need to worry about finding the absolute lowest buy point and highest
     *     sell point over a long period.
     *
     *     -   Iterate through the prices array from the second day (`i = 1`).
     *     -   **Greedy Choice**: If the price today (`prices[i]`) is greater than the price yesterday
     *         (`prices[i-1]`), it represents a profit opportunity.
     *     -   You can think of this as "buying yesterday and selling today." Add the profit
     *         (`prices[i] - prices[i-1]`) to your total profit.
     *     -   This works because the sum of all incremental profits is equivalent to buying at a local
     *         minimum and selling at the next local maximum. For example, a climb from 1 to 5 gives a
     *         profit of 4. This is the same as (2-1) + (3-2) + (4-3) + (5-4) = 4.
     *
     * =================================================================================
     * GENERIC TEMPLATE (Best Time to Buy and Sell Stock II)
     * =================================================================================
     */

    /**
     * Calculates the maximum profit that can be achieved from buying and selling stocks with
     * an unlimited number of transactions.
     *
     * @param prices An array where `prices[i]` is the price of the stock on day `i`.
     * @return The maximum achievable profit.
     */
    public int maxProfit(int[] prices) {
        if (prices == null || prices.length < 2) {
            return 0;
        }

        int totalProfit = 0;

        // --- Core Pattern Logic: Iterate and capture all profits ---
        for (int i = 1; i < prices.length; i++) {
            // --- Greedy Choice: If today's price is higher, we profit ---
            if (prices[i] > prices[i - 1]) {
                totalProfit += prices[i] - prices[i - 1];
            }
            // ---------------------------------------------------------
        }
        // -----------------------------------------------------------

        return totalProfit;
    }
}
