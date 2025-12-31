package com.framework.patterns.two_pointers;

/**
 * Pattern 5: Two Pointers - String Comparison with Backspaces
 */
public class TwoPointersStringComparisonWithBackspaces {

    /*
     * =================================================================================
     * CONDITIONS FOR APPLICABILITY
     * =================================================================================
     *
     * 1.  Input Data Structure: Two strings or arrays that need to be compared.
     *
     * 2.  Problem Goal: You need to determine if two strings are equal after processing
     *     special characters, most commonly backspaces ('#'). The comparison needs to be
     *     done efficiently, often in O(N) time and O(1) space.
     *
     * 3.  Logic: The key insight is to process the strings from END to START. This is because
     *     a backspace character affects the character *before* it, which is easier to handle
     *     when iterating backward.
     *
     *     - Two pointers are initialized to the end of each string.
     *     - The pointers are moved backward to find the next valid character to compare.
     *     - While moving a pointer backward, if a backspace is encountered, a `backspace_count`
     *       is incremented. The pointer continues to move backward, skipping characters
     *       as long as `backspace_count` > 0.
     *     - Once both pointers are at valid characters, the characters are compared. If they
     *       don't match, the strings are not equal.
     *
     * =================================================================================
     * GENERIC TEMPLATE (Backspace String Compare)
     * =================================================================================
     */

    /**
     * Compares two strings to see if they are equal after processing backspace characters ('#').
     *
     * @param s The first string.
     * @param t The second string.
     * @return True if the strings are equal after backspaces, false otherwise.
     */
    public boolean backspaceCompare(String s, String t) {
        int p1 = s.length() - 1; // Pointer for string s
        int p2 = t.length() - 1; // Pointer for string t

        while (p1 >= 0 || p2 >= 0) {
            // --- Core Pattern Logic: Find next valid character in String s ---
            int skipS = 0;
            while (p1 >= 0) {
                if (s.charAt(p1) == '#') {
                    skipS++;
                    p1--;
                } else if (skipS > 0) {
                    skipS--;
                    p1--;
                } else {
                    break; // Found a valid character
                }
            }
            // ----------------------------------------------------------------

            // --- Core Pattern Logic: Find next valid character in String t ---
            int skipT = 0;
            while (p2 >= 0) {
                if (t.charAt(p2) == '#') {
                    skipT++;
                    p2--;
                } else if (skipT > 0) {
                    skipT--;
                    p2--;
                } else {
                    break; // Found a valid character
                }
            }
            // ----------------------------------------------------------------

            // --- Problem-Specific Logic: Compare the valid characters ---
            // If one pointer is out of bounds but the other isn't, they are different lengths.
            if ((p1 >= 0) != (p2 >= 0)) {
                return false;
            }

            // If both pointers are still in bounds, compare the characters.
            if (p1 >= 0 && p2 >= 0 && s.charAt(p1) != t.charAt(p2)) {
                return false;
            }
            // ---------------------------------------------------------

            // Move pointers to continue the comparison from the next characters.
            p1--;
            p2--;
        }

        // If the loop completes, it means all valid characters matched.
        return true;
    }
}
