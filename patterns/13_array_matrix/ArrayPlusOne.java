package com.framework.patterns.array_matrix;

/**
 * Pattern 83: Array - Plus One (Handling Carry)
 */
public class ArrayPlusOne {

    /*
     * =================================================================================
     * CONDITIONS FOR APPLICABILITY
     * =================================================================================
     *
     * 1.  Input Data Structure: An array of digits representing a non-negative integer.
     *     The most significant digit is at the head of the list.
     *
     * 2.  Problem Goal: You need to increment the large integer represented by the array by one.
     *
     * 3.  Logic: This problem simulates manual addition with a carry, starting from the least
     *     significant digit (the end of the array).
     *
     *     -   Iterate through the array from right to left.
     *     -   For each digit, increment it by one.
     *     -   If the digit becomes 10, set it to 0 and continue to the next iteration (this is the "carry").
     *     -   If the digit is less than 10 after incrementing, there is no carry. You can stop and
     *         return the modified array immediately.
     *     -   **Edge Case**: If you iterate through the entire array and still have a carry (which happens
     *       for an input like `[9, 9, 9]`), it means the number of digits has increased. You need to
     *       create a new, larger array. The new array will have a `1` at the beginning, followed by
     *       all zeros.
     *
     * =================================================================================
     * GENERIC TEMPLATE (Plus One)
     * =================================================================================
     */

    /**
     * Increments a large integer represented by an array of digits by one.
     *
     * @param digits An array of digits representing a non-negative integer.
     * @return The resulting array of digits.
     */
    public int[] plusOne(int[] digits) {
        int n = digits.length;

        // --- Core Pattern Logic: Iterate from right to left ---
        for (int i = n - 1; i >= 0; i--) {
            // Increment the current digit
            digits[i]++;

            // --- Handle Carry ---
            if (digits[i] < 10) {
                // If no carry, we are done.
                return digits;
            }

            // If there is a carry, set the current digit to 0 and continue the loop.
            digits[i] = 0;
        }
        // -----------------------------------------------------

        // --- Edge Case: Handle carry-out from the most significant digit (e.g., 999 -> 1000) ---
        // If we get here, it means all digits were 9s.
        int[] newNumber = new int[n + 1];
        newNumber[0] = 1;
        // The rest of the elements in the new array are 0 by default.
        return newNumber;
        // ----------------------------------------------------------------------------------
    }
}
