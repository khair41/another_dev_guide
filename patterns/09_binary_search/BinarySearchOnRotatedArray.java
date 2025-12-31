package com.framework.patterns.binary_search;

/**
 * Pattern 60: Binary Search - Find Min/Max in Rotated Sorted Array
 */
public class BinarySearchOnRotatedArray {

    /*
     * =================================================================================
     * CONDITIONS FOR APPLICABILITY
     * =================================================================================
     *
     * 1.  Input Data Structure: A rotated sorted array. This is an array that was originally
     *     sorted in ascending order but was rotated at some unknown pivot point.
     *     Example: `[0, 1, 2, 4, 5, 6, 7]` might become `[4, 5, 6, 7, 0, 1, 2]`.
     *
     * 2.  Problem Goal: You need to find the minimum or maximum element in this array, or search
     *     for a specific target value. Standard binary search doesn't work directly because the
     *     entire array is not sorted.
     *
     * 3.  Logic: The key is to use a modified binary search to first identify which half of the
     *     array is still properly sorted. The pivot point (the location of the min/max element)
     *     is the only place where the sorted order is broken.
     *
     *     -   Initialize `low` and `high` pointers.
     *     -   In the loop, calculate `mid`.
     *     -   **Comparison Logic (for finding the minimum)**:
     *         -   Compare `nums[mid]` with `nums[high]`. This is the crucial comparison.
     *         -   If `nums[mid] > nums[high]`, it means the pivot point (the minimum element)
     *           must be in the right half of the array (from `mid + 1` to `high`). So, you set
     *           `low = mid + 1`.
     *         -   If `nums[mid] <= nums[high]`, it means the middle element is part of the sorted
     *           right-hand portion of the array, or it *is* the minimum element itself. This implies
     *           the minimum element must be in the left half (including `mid`). So, you set
     *           `high = mid`.
     *     -   The loop continues until `low == high`, at which point `low` (or `high`) will be the
     *       index of the minimum element.
     *
     * =================================================================================
     * GENERIC TEMPLATE (Find Minimum in Rotated Sorted Array)
     * =================================================================================
     */

    /**
     * Finds the minimum element in a rotated sorted array.
     *
     * @param nums A rotated sorted array of unique elements.
     * @return The minimum element in the array.
     */
    public int findMin(int[] nums) {
        if (nums == null || nums.length == 0) {
            throw new IllegalArgumentException("Input array is empty");
        }

        int low = 0;
        int high = nums.length - 1;

        // --- Core Pattern Logic: Modified Binary Search ---
        while (low < high) {
            int mid = low + (high - low) / 2;

            // --- Comparison Logic ---
            // If the mid element is greater than the high element, the pivot (minimum)
            // must be in the right half.
            if (nums[mid] > nums[high]) {
                low = mid + 1;
            } else {
                // Otherwise, the pivot is in the left half (including mid), or mid is the pivot.
                high = mid;
            }
            // ----------------------
        }
        // ---------------------------------------------------

        // When the loop ends, low == high, which is the index of the minimum element.
        return nums[low];
    }
}
