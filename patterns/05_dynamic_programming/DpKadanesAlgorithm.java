package com.framework.patterns.dynamic_programming;

/**
 * Pattern 31: DP - 1D Array (Kadane's Algorithm for Max/Min Subarray)
 */
public class DpKadanesAlgorithm {

    /*
     * =================================================================================
     * CONDITIONS FOR APPLICABILITY
     * =================================================================================
     *
     * 1.  Input Data Structure: An array of numbers.
     *
     * 2.  Problem Goal: You need to find the contiguous subarray within the array that has the
     *     largest (or smallest) sum.
     *
     * 3.  Recurrence Relation: The core idea of Kadane's algorithm is based on a simple DP relation.
     *     Let `current_max` be the maximum sum of a subarray ending at the current position `i`.
     *     To get the `current_max` at position `i`, you have two choices:
     *     a) Start a new subarray at `arr[i]`. The sum would be just `arr[i]`.
     *     b) Extend the previous subarray to include `arr[i]`. The sum would be `previous_current_max + arr[i]`.
     *     You choose the larger of the two: `current_max_at_i = max(arr[i], current_max_at_{i-1} + arr[i])`.
     *
     * 4.  Logic:
     *     -   You maintain two variables:
     *         -   `current_max`: The maximum sum of a subarray ending at the current position.
     *         -   `global_max`: The maximum sum found so far across the entire array.
     *     -   Iterate through the array from the first element.
     *     -   For each element, update `current_max` using the recurrence relation described above.
     *     -   After updating `current_max`, compare it with `global_max` and update `global_max` if
     *         `current_max` is larger.
     *     -   The final answer is `global_max`.
     *     -   This is an O(1) space complexity solution because you only need to track the current and global maximums.
     *
     * =================================================================================
     * GENERIC TEMPLATE (Maximum Subarray Sum)
     * =================================================================================
     */

    /**
     * Finds the maximum sum of a contiguous subarray using Kadane's Algorithm.
     *
     * @param nums The input array of integers.
     * @return The maximum subarray sum.
     */
    public int maxSubArray(int[] nums) {
        if (nums == null || nums.length == 0) {
            return 0;
        }

        // `currentMax` is the max sum of a subarray ending at the current position.
        // `globalMax` is the max sum found anywhere in the array so far.
        int currentMax = nums[0];
        int globalMax = nums[0];

        // Start from the second element
        for (int i = 1; i < nums.length; i++) {
            int num = nums[i];

            // --- Core Pattern Logic: Decide whether to extend the previous subarray or start a new one ---
            currentMax = Math.max(num, currentMax + num);
            // -----------------------------------------------------------------------------------------

            // --- Problem-Specific Logic: Update the overall maximum ---
            if (currentMax > globalMax) {
                globalMax = currentMax;
            }
            // ---------------------------------------------------------
        }

        return globalMax;
    }
}
