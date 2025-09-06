package com.problems.stack.solutions;

import com.framework.Solution;

public class DailyTemperaturesDPSolution implements Solution<int[], int[]> {

    /*
     * --- APPROACH ---
     * Iterate through the temperatures array from right to left. Use the results
     * from days we've already processed to "jump" to the next potential warmer day, skipping
     * all the days in between.
     *
     * --- INTUITION ---
     * When we are at day `i`, we look at the next day, `j = i + 1`. If `temperatures[j]`
     * is warmer, our answer is 1. If not, we know that day `j` itself has a waiting period
     * before its next warmer day. We can jump directly to that future day `j + answer[j]`
     * and check its temperature, repeating the process until we find a warmer day or fall off the array.
     *
     * --- COMPLEXITY ---
     *
     *   - Time: O(N)
     *     [Although there is a nested while loop, each index is visited at most twice, leading to an amortized O(N) time.]
     *
     *   - Space: O(1)
     *     [Excluding the space for the result array, we only use a few variables.]
     */

    @Override
    public int[] execute(int[] temperatures) {
        // TODO: Implement the Dynamic Programming solution logic here.
        return new int[temperatures.length]; // Placeholder
    }

    @Override
    public String getTimeComplexity() {
        return "O(N)";
    }

    @Override
    public String getSpaceComplexity() {
        return "O(1)";
    }
}
