package com.framework.patterns.binary_search;

/**
 * Pattern 62: Binary Search - Find First/Last Occurrence
 */
public class BinarySearchFirstLastOccurrence {

    /*
     * =================================================================================
     * CONDITIONS FOR APPLICABILITY
     * =================================================================================
     *
     * 1.  Input Data Structure: A sorted array that may contain duplicate elements.
     *
     * 2.  Problem Goal: You need to find the index of the very first or the very last occurrence
     *     of a given `target` value.
     *
     * 3.  Logic: This requires a modification of the standard binary search. When the middle element
     *     equals the target (`array[mid] == target`), you don't stop. Instead, you record the found
     *     index and then try to find an even better (earlier or later) index by continuing the search
     *     in one half of the array.
     *
     *     -   **To find the FIRST occurrence**:
     *         -   If `array[mid] == target`, this is a potential answer. Record `mid` and try to find
     *           an even earlier occurrence by searching in the left half: `high = mid - 1`.
     *         -   If `array[mid] < target`, search the right half: `low = mid + 1`.
     *         -   If `array[mid] > target`, search the left half: `high = mid - 1`.
     *
     *     -   **To find the LAST occurrence**:
     *         -   If `array[mid] == target`, this is a potential answer. Record `mid` and try to find
     *           an even later occurrence by searching in the right half: `low = mid + 1`.
     *         -   The rest of the logic is the same as standard binary search.
     *
     * =================================================================================
     * GENERIC TEMPLATE (Find First and Last Position of Element in Sorted Array)
     * =================================================================================
     */

    /**
     * Finds the starting and ending position of a given target value.
     *
     * @param nums A sorted array of integers.
     * @param target The value to search for.
     * @return An array of two elements `[first, last]` or `[-1, -1]` if the target is not found.
     */
    public int[] searchRange(int[] nums, int target) {
        int first = findFirst(nums, target);
        int last = findLast(nums, target);
        return new int[]{first, last};
    }

    /**
     * Helper function to find the first occurrence of the target.
     */
    private int findFirst(int[] nums, int target) {
        int index = -1;
        int low = 0;
        int high = nums.length - 1;

        while (low <= high) {
            int mid = low + (high - low) / 2;
            if (nums[mid] >= target) {
                // Move left, even if we found the target, to find the *first* occurrence.
                high = mid - 1;
            } else {
                low = mid + 1;
            }

            // If we found the target, record the index.
            if (nums[mid] == target) {
                index = mid;
            }
        }
        return index;
    }

    /**
     * Helper function to find the last occurrence of the target.
     */
    private int findLast(int[] nums, int target) {
        int index = -1;
        int low = 0;
        int high = nums.length - 1;

        while (low <= high) {
            int mid = low + (high - low) / 2;
            if (nums[mid] <= target) {
                // Move right, even if we found the target, to find the *last* occurrence.
                low = mid + 1;
            } else {
                high = mid - 1;
            }

            // If we found the target, record the index.
            if (nums[mid] == target) {
                index = mid;
            }
        }
        return index;
    }
}
