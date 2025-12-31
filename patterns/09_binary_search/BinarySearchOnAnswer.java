package com.framework.patterns.binary_search;

/**
 * Pattern 61: Binary Search - On Answer / Condition Function
 */
public class BinarySearchOnAnswer {

    /*
     * =================================================================================
     * CONDITIONS FOR APPLICABILITY
     * =================================================================================
     *
     * 1.  Problem Goal: You are asked to find the minimum or maximum value that satisfies a
     *     set of conditions. For example, "minimize the maximum capacity" or "maximize the minimum distance".
     *
     * 2.  Monotonicity of the Condition: There must be a monotonic relationship between a potential
     *     answer and whether it is "possible" to achieve. If a value `x` is a possible solution,
     *     then all values on one side of `x` (e.g., all values `> x`) must also be possible solutions.
     *     -   Example: If we can ship all packages with a ship of capacity `C`, we can definitely ship them
     *         with a larger capacity `C+1`. This allows us to use binary search.
     *
     * 3.  Logic: Instead of searching for an index in an array, you are searching for the optimal
     *     value within a range of possible answers.
     *
     *     -   **Step 1: Define the Search Space**: Determine the `low` and `high` bounds for the answer.
     *         For example, the minimum possible capacity of a ship is the weight of the heaviest package,
     *         and the maximum is the sum of all package weights.
     *
     *     -   **Step 2: Create a Condition Function**: Write a helper function `isPossible(guess)` that takes
     *         a potential answer (`guess`) and returns `true` if it satisfies the problem's constraints,
     *         and `false` otherwise.
     *
     *     -   **Step 3: Binary Search**: Perform a binary search on the answer space (`low` to `high`).
     *         -   In each step, calculate `mid` as your guessed answer.
     *         -   If `isPossible(mid)` is true, it means `mid` is a valid solution. We might be able to do
     *           better (find a smaller capacity), so we record `mid` as a potential answer and try the
     *           left half: `high = mid - 1`.
     *         -   If `isPossible(mid)` is false, `mid` is not a valid solution. We need a larger capacity,
     *           so we must try the right half: `low = mid + 1`.
     *
     * =================================================================================
     * GENERIC TEMPLATE (Capacity To Ship Packages Within D Days)
     * =================================================================================
     */

    /**
     * Finds the least weight capacity of a ship that will result in all packages being shipped within `D` days.
     *
     * @param weights An array of package weights.
     * @param D The number of days.
     * @return The minimum possible ship capacity.
     */
    public int shipWithinDays(int[] weights, int D) {
        // --- Step 1: Define the Search Space for the answer (capacity) ---
        int low = 0; // Min possible capacity is the weight of the heaviest single package.
        int high = 0; // Max possible capacity is the sum of all weights (ship in 1 day).
        for (int w : weights) {
            low = Math.max(low, w);
            high += w;
        }

        int minCapacity = high;

        // --- Step 3: Binary Search on the Answer ---
        while (low <= high) {
            int mid_capacity = low + (high - low) / 2;

            if (isPossible(weights, D, mid_capacity)) {
                // This capacity is possible. Try for a smaller one.
                minCapacity = mid_capacity;
                high = mid_capacity - 1;
            } else {
                // This capacity is too small. Need a larger one.
                low = mid_capacity + 1;
            }
        }

        return minCapacity;
    }

    /**
     * --- Step 2: Condition Function ---
     * Checks if it's possible to ship all packages within D days given a certain capacity.
     */
    private boolean isPossible(int[] weights, int D, int capacity) {
        int daysRequired = 1;
        int currentWeight = 0;
        for (int w : weights) {
            currentWeight += w;
            if (currentWeight > capacity) {
                // The current package doesn't fit, so move to the next day.
                daysRequired++;
                // Start the new day with the current package.
                currentWeight = w;
            }
        }
        return daysRequired <= D;
    }
}
