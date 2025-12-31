package com.framework.patterns.sliding_window;

/**
 * Pattern 9: Sliding Window - Variable Size (Condition-Based)
 */
public class SlidingWindowVariableSize {

    /*
     * =================================================================================
     * CONDITIONS FOR APPLICABILITY
     * =================================================================================
     *
     * 1.  Input Data Structure: An array or list.
     *
     * 2.  Problem Goal: You need to find the longest or shortest contiguous subarray that
     *     satisfies a certain condition. The size of this subarray is not fixed.
     *     Examples: "Find the length of the longest substring with no more than K distinct characters"
     *     or "Find the smallest subarray whose sum is >= S".
     *
     * 3.  Logic: This pattern involves expanding and shrinking a window to maintain a valid state.
     *
     *     - A `windowStart` pointer marks the beginning of the window (initially at 0).
     *     - A `windowEnd` pointer iterates through the array, expanding the window one element at a time.
     *     - As the window expands, update some state (e.g., sum, character counts).
     *     - A `while` loop checks if the current window is "invalid" (violates the condition).
     *     - If the window is invalid, shrink it from the left by advancing `windowStart` and
     *       updating the state, until the window becomes valid again.
     *     - After each expansion of `windowEnd`, and after ensuring the window is valid, update
     *       the result (e.g., the max length or min length found so far).
     *
     * =================================================================================
     * GENERIC TEMPLATE (Smallest Subarray with Sum >= S)
     * =================================================================================
     */

    /**
     * Finds the length of the smallest contiguous subarray whose sum is greater than or equal to `S`.
     *
     * @param arr The input array of positive integers.
     * @param S   The target sum.
     * @return The length of the smallest subarray, or 0 if no such subarray is found.
     */
    public int findMinSubArray(int[] arr, int S) {
        if (arr == null || arr.length == 0) {
            return 0;
        }

        int windowStart = 0;
        int windowSum = 0;
        int minLength = Integer.MAX_VALUE;

        // --- Core Pattern Logic: Expand the window ---
        for (int windowEnd = 0; windowEnd < arr.length; windowEnd++) {
            // --- Problem-Specific Logic: Add the new element to the window state ---
            windowSum += arr[windowEnd];
            // ---------------------------------------------------------------------

            // --- Core Pattern Logic: Shrink the window until the condition is met ---
            // Keep shrinking as long as the window is valid (sum >= S).
            while (windowSum >= S) {
                // --- Problem-Specific Logic: Update the result ---
                int currentLength = windowEnd - windowStart + 1;
                minLength = Math.min(minLength, currentLength);
                // ------------------------------------------------

                // --- Problem-Specific Logic: Remove the outgoing element from the state ---
                windowSum -= arr[windowStart];
                // ----------------------------------------------------------------------

                // Shrink the window from the left.
                windowStart++;
            }
            // -------------------------------------------------------------------------
        }

        // If minLength was never updated, no valid subarray was found.
        return minLength == Integer.MAX_VALUE ? 0 : minLength;
    }
}
