package com.framework.patterns.two_pointers;

/**
 * Pattern 7: Two Pointers - String Reversal
 */
public class TwoPointersStringReversal {

    /*
     * =================================================================================
     * CONDITIONS FOR APPLICABILITY
     * =================================================================================
     *
     * 1.  Input Data Structure: A mutable sequence of characters, like a char array.
     *     (Note: In Java, String is immutable, so it must be converted to a char array first).
     *
     * 2.  Problem Goal: Reverse the sequence in-place to save memory (O(1) space).
     *
     * 3.  Logic: This is a classic application of the converging pointers pattern.
     *
     *     - A `left` pointer starts at the beginning of the array.
     *     - A `right` pointer starts at the end of the array.
     *     - While `left` is less than `right`, swap the characters at the `left` and `right` indices.
     *     - Move `left` one step to the right and `right` one step to the left.
     *     - The process stops when the pointers meet or cross, at which point the entire array is reversed.
     *
     * =================================================================================
     * GENERIC TEMPLATE (In-place string reversal)
     * =================================================================================
     */

    /**
     * Reverses a character array in-place.
     *
     * @param s The character array to reverse.
     */
    public void reverseString(char[] s) {
        if (s == null || s.length < 2) {
            return;
        }

        int left = 0;
        int right = s.length - 1;

        // --- Core Pattern Logic: Converge pointers and swap ---
        while (left < right) {
            // --- Problem-Specific Logic: Swap the elements ---
            char temp = s[left];
            s[left] = s[right];
            s[right] = temp;
            // -----------------------------------------------

            // Move pointers towards the center.
            left++;
            right--;
        }
        // -----------------------------------------------------
    }
}
