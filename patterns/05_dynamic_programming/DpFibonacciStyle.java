package com.framework.patterns.dynamic_programming;

/**
 * Pattern 30: DP - 1D Array (Fibonacci Style)
 */
public class DpFibonacciStyle {

    /*
     * =================================================================================
     * CONDITIONS FOR APPLICABILITY
     * =================================================================================
     *
     * 1.  Input Data Structure: The problem is typically defined by an integer `n`.
     *
     * 2.  Problem Goal: You need to find the `n`-th term of a sequence where each term is a
     *     function of the preceding one or two terms. This is the simplest form of Dynamic Programming.
     *     Examples: "Fibonacci Number", "Climbing Stairs", "House Robber" (with a slight variation).
     *
     * 3.  Recurrence Relation: The core of the problem can be described by a recurrence relation.
     *     -   For Climbing Stairs: `ways(n) = ways(n-1) + ways(n-2)`
     *     -   For Fibonacci: `fib(n) = fib(n-1) + fib(n-2)`
     *
     * 4.  Logic (Bottom-Up DP):
     *     -   Instead of using recursion (which can be slow due to re-computing the same subproblems),
     *         we build the solution from the ground up.
     *     -   Create a DP array (or table) of size `n+1`.
     *     -   Initialize the base cases (e.g., `dp[0] = 1`, `dp[1] = 1`).
     *     -   Iterate from the base cases up to `n`, filling the DP table using the recurrence relation:
     *         `dp[i] = dp[i-1] + dp[i-2]`.
     *     -   The final answer is `dp[n]`.
     *
     * 5.  Space Optimization:
     *     -   Notice that to calculate `dp[i]`, you only need the previous two values (`dp[i-1]` and `dp[i-2]`),
     *         not the entire array. This allows for a space optimization where you only store the last
     *         two values in variables, reducing space complexity from O(n) to O(1).
     *
     * =================================================================================
     * GENERIC TEMPLATE (Climbing Stairs)
     * =================================================================================
     */

    /**
     * Calculates the number of distinct ways to climb to the top of a staircase of `n` steps,
     * taking either 1 or 2 steps at a time. (Array-based DP version)
     *
     * @param n The number of stairs.
     * @return The number of distinct ways to climb.
     */
    public int climbStairs_Array(int n) {
        if (n <= 1) {
            return 1;
        }

        // dp[i] will store the number of ways to reach step i.
        int[] dp = new int[n + 1];

        // Base cases
        dp[0] = 1; // Ways to reach step 0 (the ground)
        dp[1] = 1; // Ways to reach step 1

        // Fill the DP table using the recurrence relation
        for (int i = 2; i <= n; i++) {
            dp[i] = dp[i - 1] + dp[i - 2];
        }

        return dp[n];
    }

    /**
     * Space-optimized version of the climbing stairs problem.
     *
     * @param n The number of stairs.
     * @return The number of distinct ways to climb.
     */
    public int climbStairs_Optimized(int n) {
        if (n <= 1) {
            return 1;
        }

        // `one_step_back` stores the ways to reach step (i-1)
        // `two_steps_back` stores the ways to reach step (i-2)
        int one_step_back = 1;
        int two_steps_back = 1;

        for (int i = 2; i <= n; i++) {
            int current_ways = one_step_back + two_steps_back;
            // Update the pointers for the next iteration
            two_steps_back = one_step_back;
            one_step_back = current_ways;
        }

        return one_step_back; // This holds the ways for step n at the end
    }
}
