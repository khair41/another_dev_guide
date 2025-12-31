package com.framework.patterns.dynamic_programming;

import java.util.Arrays;

/**
 * Pattern 40: DP - Longest Increasing Subsequence (LIS)
 */
public class DpLongestIncreasingSubsequence {

    /*
     * =================================================================================
     * CONDITIONS FOR APPLICABILITY
     * =================================================================================
     *
     * 1.  Input Data Structure: A sequence of numbers.
     *
     * 2.  Problem Goal: You need to find the length of the longest subsequence where all elements
     *     of the subsequence are sorted in increasing order. A subsequence does not have to be contiguous.
     *
     * 3.  Recurrence Relation (O(n^2) DP approach):
     *     -   Let `dp[i]` be the length of the Longest Increasing Subsequence that *ends* at index `i`.
     *     -   To compute `dp[i]`, we look at all previous indices `j` (from `0` to `i-1`).
     *     -   If `nums[i] > nums[j]`, it means we can potentially extend the increasing subsequence that ends at `j`.
     *     -   The new length would be `dp[j] + 1`.
     *     -   We take the maximum over all valid `j`s: `dp[i] = 1 + max(dp[j])` for all `j < i` where `nums[i] > nums[j]`.
     *     -   The base case for `dp[i]` is 1 (the element itself forms a subsequence of length 1).
     *     -   The final answer is the maximum value in the `dp` array, as the LIS can end at any index.
     *
     * 4.  More Efficient Approach (O(n log n)):
     *     -   This approach maintains an auxiliary array (let's call it `tails` or `sub`) which stores the smallest
     *       tail of all increasing subsequences with length `i+1` at `tails[i]`.
     *     -   Iterate through the input numbers. For each `num`:
     *         -   If `num` is greater than all elements in `tails`, append it to `tails`. This extends the LIS.
     *         -   Otherwise, find the smallest element in `tails` that is greater than or equal to `num` and
     *           replace it with `num`. This is done via binary search. This means we have found a potentially
     *           better (smaller) tail for a subsequence of a certain length.
     *     -   The length of the `tails` array at the end is the length of the LIS.
     *
     * =================================================================================
     * GENERIC TEMPLATE (O(n^2) DP Approach)
     * =================================================================================
     */

    /**
     * Calculates the length of the longest increasing subsequence using the O(n^2) DP approach.
     *
     * @param nums The input array of numbers.
     * @return The length of the LIS.
     */
    public int lengthOfLIS_quadratic(int[] nums) {
        if (nums == null || nums.length == 0) {
            return 0;
        }

        // dp[i] = length of LIS ending at index i
        int[] dp = new int[nums.length];
        // Every element is an LIS of length 1 by itself.
        Arrays.fill(dp, 1);

        int maxLength = 1;

        for (int i = 1; i < nums.length; i++) {
            for (int j = 0; j < i; j++) {
                // If the current element is greater, we can potentially extend the LIS ending at j.
                if (nums[i] > nums[j]) {
                    dp[i] = Math.max(dp[i], dp[j] + 1);
                }
            }
            // Update the overall max length found so far.
            maxLength = Math.max(maxLength, dp[i]);
        }

        return maxLength;
    }
}
