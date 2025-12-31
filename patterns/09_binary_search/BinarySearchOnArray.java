package com.framework.patterns.binary_search;

/**
 * Pattern 59: Binary Search - On Sorted Array/List
 */
public class BinarySearchOnArray {

    /*
     * =================================================================================
     * CONDITIONS FOR APPLICABILITY
     * =================================================================================
     *
     * 1.  Input Data Structure: A sorted array, list, or any data structure that allows for
     *     efficient random access (e.g., getting the element at index `i`).
     *
     * 2.  Problem Goal: You need to find if a specific `target` element exists in the array, or
     *     find its position.
     *
     * 3.  Logic: Binary search works by repeatedly dividing the search interval in half. It compares
     *     the middle element of the search space with the target.
     *
     *     -   Initialize `low` and `high` pointers to the start and end of the array.
     *     -   Loop as long as `low <= high`.
     *     -   Calculate the middle index: `mid = low + (high - low) / 2`. This prevents integer overflow.
     *     -   **Comparison**:
     *         -   If `array[mid] == target`, the element is found.
     *         -   If `array[mid] < target`, the target must be in the right half of the array, so we
     *           discard the left half by setting `low = mid + 1`.
     *         -   If `array[mid] > target`, the target must be in the left half of the array, so we
     *           discard the right half by setting `high = mid - 1`.
     *     -   If the loop finishes without finding the target, the element is not in the array.
     *
     * =================================================================================
     * GENERIC TEMPLATE (Standard Binary Search)
     * =================================================================================
     */

    /**
     * Searches for a target value in a sorted array of integers.
     *
     * @param nums A sorted array of integers.
     * @param target The value to search for.
     * @return The index of the target if found, otherwise -1.
     */
    public int search(int[] nums, int target) {
        if (nums == null || nums.length == 0) {
            return -1;
        }

        int low = 0;
        int high = nums.length - 1;

        // --- Core Pattern Logic: Loop until search space is exhausted ---
        while (low <= high) {
            // Calculate mid point to avoid potential overflow
            int mid = low + (high - low) / 2;

            // --- Comparison Logic ---
            if (nums[mid] == target) {
                return mid; // Target found
            } else if (nums[mid] < target) {
                // Target is in the right half
                low = mid + 1;
            } else {
                // Target is in the left half
                high = mid - 1;
            }
            // ----------------------
        }
        // ----------------------------------------------------------------

        // Target was not found in the array
        return -1;
    }
}
