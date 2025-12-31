package com.framework.patterns.binary_search;

/**
 * Pattern 63: Binary Search - Median / Kth across Two Sorted Arrays
 */
public class BinarySearchMedianOfTwoArrays {

    /*
     * =================================================================================
     * CONDITIONS FOR APPLICABILITY
     * =================================================================================
     *
     * 1.  Input Data Structure: Two sorted arrays.
     *
     * 2.  Problem Goal: You need to find the median of the two sorted arrays combined, or more generally,
     *     the k-th smallest element. This must be done in O(log(m+n)) time, which strongly implies a
     *     binary search approach.
     *
     * 3.  Logic: The core idea is to partition both arrays into a "left part" and a "right part".
     *     We want to find a partition such that all elements in the combined left parts are less than
     *     or equal to all elements in the combined right parts. If we can achieve this, and the total
     *     number of elements in the combined left part is half the total number of elements, then the
     *     median can be found from the boundary elements.
     *
     *     -   Perform a binary search on the smaller of the two arrays (let's say `nums1`). The search
     *         is on the possible partition points, not the values.
     *     -   Let `partitionX` be the partition point in `nums1`. This divides `nums1` into `leftX` and `rightX`.
     *     -   The corresponding partition point in `nums2`, `partitionY`, is determined by the total number
     *         of elements needed in the combined left half.
     *     -   We then check if we have found the correct partition by verifying the condition:
     *         `max(leftX) <= min(rightY)` AND `max(leftY) <= min(rightX)`.
     *     -   If the condition is met, the median is `(max(max(leftX), max(leftY)) + min(min(rightX), min(rightY))) / 2`
     *         for an even number of total elements, or `max(max(leftX), max(leftY))` for an odd number.
     *     -   If the condition is not met, we adjust the binary search on `partitionX` accordingly.
     *
     * =================================================================================
     * GENERIC TEMPLATE (Median of Two Sorted Arrays)
     * =================================================================================
     */

    /**
     * Finds the median of two sorted arrays.
     *
     * @param nums1 The first sorted array.
     * @param nums2 The second sorted array.
     * @return The median of the two arrays combined.
     */
    public double findMedianSortedArrays(int[] nums1, int[] nums2) {
        // Ensure nums1 is the smaller array to optimize the binary search space.
        if (nums1.length > nums2.length) {
            return findMedianSortedArrays(nums2, nums1);
        }

        int x = nums1.length;
        int y = nums2.length;
        int low = 0;
        int high = x;

        while (low <= high) {
            // partitionX is the number of elements taken from nums1 for the left half.
            int partitionX = (low + high) / 2;
            // The remaining elements for the left half must come from nums2.
            int partitionY = (x + y + 1) / 2 - partitionX;

            // --- Get the boundary elements for the partitions ---
            int maxLeftX = (partitionX == 0) ? Integer.MIN_VALUE : nums1[partitionX - 1];
            int minRightX = (partitionX == x) ? Integer.MAX_VALUE : nums1[partitionX];

            int maxLeftY = (partitionY == 0) ? Integer.MIN_VALUE : nums2[partitionY - 1];
            int minRightY = (partitionY == y) ? Integer.MAX_VALUE : nums2[partitionY];
            // --------------------------------------------------

            // --- Core Pattern Logic: Check if partition is correct ---
            if (maxLeftX <= minRightY && maxLeftY <= minRightX) {
                // Correct partition found, calculate the median.
                if ((x + y) % 2 == 0) {
                    // Even number of total elements
                    return (Math.max(maxLeftX, maxLeftY) + Math.min(minRightX, minRightY)) / 2.0;
                } else {
                    // Odd number of total elements
                    return Math.max(maxLeftX, maxLeftY);
                }
            } else if (maxLeftX > minRightY) {
                // We are too far on the right side for partitionX. Go left.
                high = partitionX - 1;
            } else {
                // We are too far on the left side for partitionX. Go right.
                low = partitionX + 1;
            }
            // ---------------------------------------------------------
        }

        // Should not happen if inputs are sorted arrays.
        throw new IllegalArgumentException("Input arrays are not sorted.");
    }
}
