package com.framework.patterns.array_matrix;

/**
 * Pattern 82: Array - Product Except Self (Prefix/Suffix Products)
 */
public class ArrayProductExceptSelf {

    /*
     * =================================================================================
     * CONDITIONS FOR APPLICABILITY
     * =================================================================================
     *
     * 1.  Input Data Structure: An array of numbers.
     *
     * 2.  Problem Goal: You need to construct a new array where each element at index `i` is the
     *     product of all the numbers in the original array except the one at `i`. This must typically
     *     be done in O(n) time and without using the division operator.
     *
     * 3.  Logic: The core insight is that the product for index `i` is the product of all elements
     *     to its left (prefix product) multiplied by the product of all elements to its right
     *     (suffix product).
     *
     *     -   **Algorithm**:
     *         1.  Initialize a `result` array of the same size, often with all 1s.
     *         2.  **First Pass (Prefix Products)**: Iterate from left to right. Maintain a running
     *             `prefix_product`. For each index `i`, first set `result[i] = prefix_product`,
     *             and then update `prefix_product` by multiplying it with `nums[i]`.
     *         3.  **Second Pass (Suffix Products)**: Iterate from right to left. Maintain a running
     *             `suffix_product`. For each index `i`, first multiply the existing `result[i]`
     *             (which already contains the prefix product) by the `suffix_product`. Then,
     *             update `suffix_product` by multiplying it with `nums[i]`.
     *     -   This approach calculates the final result in two passes, using O(1) extra space
     *       (if the result array is not counted as extra space).
     *
     * =================================================================================
     * GENERIC TEMPLATE (Product of Array Except Self)
     * =================================================================================
     */

    /**
     * Given an integer array nums, return an array answer such that answer[i] is equal to the
     * product of all the elements of nums except nums[i].
     *
     * @param nums The input array of integers.
     * @return The resulting array.
     */
    public int[] productExceptSelf(int[] nums) {
        if (nums == null || nums.length == 0) {
            return new int[0];
        }
        int n = nums.length;
        int[] result = new int[n];

        // --- Core Pattern Logic: Step 1 - Calculate Prefix Products ---
        // `prefixProduct` stores the product of all elements to the left of the current index.
        int prefixProduct = 1;
        for (int i = 0; i < n; i++) {
            result[i] = prefixProduct;
            prefixProduct *= nums[i];
        }
        // -------------------------------------------------------------

        // --- Core Pattern Logic: Step 2 - Calculate Suffix Products and Final Result ---
        // `suffixProduct` stores the product of all elements to the right of the current index.
        int suffixProduct = 1;
        for (int i = n - 1; i >= 0; i--) {
            // result[i] already contains the prefix product.
            // Multiply it by the suffix product to get the final result.
            result[i] *= suffixProduct;
            suffixProduct *= nums[i];
        }
        // -----------------------------------------------------------------------------

        return result;
    }
}
