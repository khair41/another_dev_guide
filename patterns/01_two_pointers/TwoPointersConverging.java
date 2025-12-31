package com.framework.patterns;

/**
 * Pattern 1: Two Pointers - Converging (from a sorted array)
 */
public class TwoPointersConverging {

    /*
     * =================================================================================
     * CONDITIONS FOR APPLICABILITY
     * =================================================================================
     *
     * 1.  Input Data Structure: The input must be a SORTED array, list, or sequence.
     *     If it's not sorted, you might consider sorting it first if the cost is acceptable (O(N log N)).
     *
     * 2.  Problem Goal: You are searching for a PAIR of elements (or sometimes a triplet)
     *     that collectively satisfy a specific condition.
     *
     * 3.  Condition Logic: The condition usually involves comparing the sum (or another property)
     *     of the two elements against a target value. Based on this comparison, you have a
     *     clear, monotonic decision on which pointer to move.
     *
     *     - If `arr[left] + arr[right] < target`, you need a larger sum, so you must move the `left` pointer forward.
     *     - If `arr[left] + arr[right] > target`, you need a smaller sum, so you must move the `right` pointer backward.
     *
     * =================================================================================
     * GENERIC TEMPLATE
     * =================================================================================
     */

    /**
     * Finds a pair of elements in a sorted array that sum up to a target value.
     *
     * @param sortedArr A sorted integer array.
     * @param target    The target sum.
     * @return An array containing the indices of the two numbers, or {-1, -1} if not found.
     */
    public int[] findPairWithTargetSum(int[] sortedArr, int target) {
        if (sortedArr == null || sortedArr.length < 2) {
            return new int[]{-1, -1}; // Or throw an exception
        }

        int left = 0;
        int right = sortedArr.length - 1;

        while (left < right) {
            int currentSum = sortedArr[left] + sortedArr[right];

            if (currentSum == target) {
                // --- Problem-Specific Logic ---
                // Found the pair. Return their indices or values.
                return new int[]{left, right};
                // If you need to find all pairs, you would add them to a list
                // and then move both pointers to continue the search.
                // left++;
                // right--;
                // -----------------------------
            } else if (currentSum < target) {
                // --- Core Pattern Logic ---
                // The sum is too small, we need a larger value.
                // The only way to guarantee a larger sum is to move the left pointer to the right.
                left++;
                // -------------------------
            } else { // currentSum > target
                // --- Core Pattern Logic ---
                // The sum is too large, we need a smaller value.
                // The only way to guarantee a smaller sum is to move the right pointer to the left.
                right--;
                // -------------------------
            }
        }

        // If the loop completes, no pair was found.
        return new int[]{-1, -1};
    }
}
