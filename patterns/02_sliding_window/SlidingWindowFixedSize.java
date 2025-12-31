package com.framework.patterns.sliding_window;

/**
 * Pattern 8: Sliding Window - Fixed Size (Subarray Calculation)
 */
public class SlidingWindowFixedSize {

    /*
     * =================================================================================
     * CONDITIONS FOR APPLICABILITY
     * =================================================================================
     *
     * 1.  Input Data Structure: An array or list.
     *
     * 2.  Problem Goal: You need to perform a calculation on all contiguous subarrays of a
     *     specific, FIXED size `k`. Examples include finding the max/min sum, the average,
     *     or any other property of a subarray of size `k`.
     *
     * 3.  Logic: The key to this pattern is efficiency. A naive approach would be to recalculate
     *     the value for each subarray independently, which is slow (O(N*k)). The sliding window
     *     approach is O(N) because you reuse the calculation from the previous window.
     *
     *     - First, calculate the value for the initial window of size `k`.
     *     - Then, iterate from the `k`-th element to the end of the array.
     *     - In each iteration, you "slide" the window one step to the right by:
     *         - ADDING the new element that just entered the window.
     *         - SUBTRACTING the element that just left the window.
     *     - After updating the window's value, compare it with the global best result.
     *
     * =================================================================================
     * GENERIC TEMPLATE (Find Maximum Sum of a Subarray of Size k)
     * =================================================================================
     */

    /**
     * Finds the maximum sum of any contiguous subarray of size `k`.
     *
     * @param arr The input array of numbers.
     * @param k   The fixed size of the subarray window.
     * @return The maximum sum found.
     */
    public int findMaxSumSubarray(int[] arr, int k) {
        if (arr == null || arr.length < k || k <= 0) {
            // Or throw an IllegalArgumentException
            return 0;
        }

        int windowSum = 0;
        int maxSum = Integer.MIN_VALUE;

        // --- Core Pattern Logic: Phase 1 - Calculate sum of the first window ---
        for (int i = 0; i < k; i++) {
            windowSum += arr[i];
        }
        maxSum = windowSum;
        // ---------------------------------------------------------------------

        // --- Core Pattern Logic: Phase 2 - Slide the window ---
        // Start from the k-th element and slide to the end.
        for (int windowEnd = k; windowEnd < arr.length; windowEnd++) {
            // --- Problem-Specific Logic: Update the window sum efficiently ---
            int newItem = arr[windowEnd];
            int oldItem = arr[windowEnd - k]; // This is the item that just left the window
            windowSum = windowSum + newItem - oldItem;
            // --------------------------------------------------------------

            // Update the global maximum.
            maxSum = Math.max(maxSum, windowSum);
        }
        // ---------------------------------------------------------

        return maxSum;
    }
}
