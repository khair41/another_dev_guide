package com.framework.patterns.array_matrix;

/**
 * Pattern 85: Array - Cyclic Sort
 */
public class ArrayCyclicSort {

    /*
     * =================================================================================
     * CONDITIONS FOR APPLICABILITY
     * =================================================================================
     *
     * 1.  Input Data Structure: An array of `n` numbers.
     *
     * 2.  Problem Constraint: The numbers in the array are from a specific, contiguous range,
     *     typically from `1` to `n` or `0` to `n-1`.
     *
     * 3.  Problem Goal: You need to sort the array or find a missing number, a duplicate number,
     *     or multiple missing/duplicate numbers in O(n) time and O(1) space.
     *
     * 4.  Logic: The core idea is to place each number at its "correct" index. If the numbers are
     *     in the range `1` to `n`, then the number `x` should be at index `x - 1`.
     *
     *     -   **Algorithm**:
     *         1.  Iterate through the array with a pointer `i` from `0` to `n-1`.
     *         2.  For the number at the current index, `num = nums[i]`, find its correct index,
     *             `correct_idx = num - 1`.
     *         3.  If the number is not already at its correct index (`nums[i] != nums[correct_idx]`),
     *             swap `nums[i]` with `nums[correct_idx]`.
     *         4.  **Crucially**, after a swap, you do *not* advance the pointer `i`. You re-evaluate the
     *             new number that is now at index `i`.
     *         5.  If the number at `nums[i]` is already in its correct place, only then do you advance `i`.
     *     -   **Post-Sort Analysis**: After the array is cyclically sorted, a second pass can easily
     *       reveal the anomaly. For example, to find the missing number, iterate from `0` to `n-1`.
     *       The first index `i` where `nums[i] != i + 1` tells you that `i + 1` is the missing number.
     *
     * =================================================================================
     * GENERIC TEMPLATE (Find the Missing Number)
     * =================================================================================
     */

    /**
     * Finds the missing number in an array containing n distinct numbers taken from the range 0 to n.
     * (Note: This example uses the 0-n range, so the logic is slightly adjusted).
     *
     * @param nums An array containing n distinct numbers from the range [0, n].
     * @return The missing number.
     */
    public int missingNumber(int[] nums) {
        int i = 0;
        int n = nums.length;

        // --- Core Pattern Logic: Cyclic Sort ---
        while (i < n) {
            int correct_idx = nums[i];
            // The number should be at the index equal to its value.
            // We also need to ignore the number `n` since its index `n` is out of bounds.
            if (nums[i] < n && nums[i] != nums[correct_idx]) {
                swap(nums, i, correct_idx);
            } else {
                i++;
            }
        }
        // ---------------------------------------

        // --- Post-Sort Analysis ---
        for (int j = 0; j < n; j++) {
            if (nums[j] != j) {
                return j;
            }
        }
        // ------------------------

        // If all numbers from 0 to n-1 are present, then n is the missing number.
        return n;
    }

    private void swap(int[] nums, int i, int j) {
        int temp = nums[i];
        nums[i] = nums[j];
        nums[j] = temp;
    }
}
