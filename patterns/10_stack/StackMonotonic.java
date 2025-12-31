package com.framework.patterns.stack;

import java.util.Stack;

/**
 * Pattern 65: Stack - Monotonic Stack
 */
public class StackMonotonic {

    /*
     * =================================================================================
     * CONDITIONS FOR APPLICABILITY
     * =================================================================================
     *
     * 1.  Input Data Structure: An array of numbers.
     *
     * 2.  Problem Goal: You need to find the "next greater element" or "previous greater element"
     *     (or smaller) for each element in the array. The "next" or "previous" refers to the
     *     first such element found to the right or left.
     *     Examples: "Next Greater Element I/II", "Daily Temperatures", "Largest Rectangle in Histogram".
     *
     * 3.  Logic: A monotonic stack maintains its elements in a strictly increasing or decreasing order.
     *
     *     -   **For Next Greater Element (using a decreasing monotonic stack)**:
     *         -   Initialize an empty stack. This stack will store indices of elements for which we
     *           are still seeking a "next greater element".
     *         -   Iterate through the input array from left to right.
     *         -   For each element `current_num`:
     *             -   **While the stack is not empty AND `current_num` is greater than the element at the
     *               index on top of the stack (`nums[stack.peek()]`)**:
     *                 -   This `current_num` is the "next greater element" for the element at the index
     *                   on top of the stack.
     *                 -   Pop the index from the stack and record the result (e.g., `result[stack.pop()] = current_num`).
     *             -   After the while loop, push the index of the `current_num` onto the stack.
     *         -   Any indices remaining on the stack at the end have no next greater element.
     *
     * =================================================================================
     * GENERIC TEMPLATE (Next Greater Element I)
     * =================================================================================
     */

    /**
     * Finds the next greater element for each element in `nums1` from the elements in `nums2`.
     *
     * @param nums1 A subset of `nums2`.
     * @param nums2 The array to search for next greater elements.
     * @return An array with the next greater element for each element in `nums1`.
     */
    public int[] nextGreaterElement(int[] nums1, int[] nums2) {
        java.util.Map<Integer, Integer> nextGreaterMap = new java.util.HashMap<>();
        Stack<Integer> stack = new Stack<>();

        // --- Core Pattern Logic: Pre-calculate next greater elements for nums2 ---
        for (int num : nums2) {
            // While stack is not empty and the current number is greater than the stack's top element
            while (!stack.isEmpty() && num > stack.peek()) {
                // The current `num` is the next greater element for the element at the top of the stack.
                nextGreaterMap.put(stack.pop(), num);
            }
            // Push the current number onto the stack.
            stack.push(num);
        }
        // ----------------------------------------------------------------------

        // --- Problem-Specific Logic: Map results to nums1 ---
        int[] result = new int[nums1.length];
        for (int i = 0; i < nums1.length; i++) {
            result[i] = nextGreaterMap.getOrDefault(nums1[i], -1);
        }
        // ---------------------------------------------------

        return result;
    }
}
