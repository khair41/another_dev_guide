package com.framework.patterns.string_manipulation;

/**
 * Pattern 92: String - Repeated Substring Pattern Detection
 */
public class StringRepeatedSubstring {

    /*
     * =================================================================================
     * CONDITIONS FOR APPLICABILITY
     * =================================================================================
     *
     * 1.  Input Data Structure: A non-empty string.
     *
     * 2.  Problem Goal: You need to check if the string can be constructed by taking a substring
     *     of it and appending multiple copies of the substring together.
     *
     * 3.  Logic: While this can be solved by checking all possible substring lengths, there is a
     *     very clever one-line trick.
     *
     *     -   **The Insight**: Let the string be `s`. Create a new string `ss` by concatenating `s` with
     *         itself (`ss = s + s`).
     *     -   Example: If `s = "abab"`, then `ss = "abababab"`.
     *     -   Now, remove the first and last characters of `ss`. This gives `s_trimmed = "bababa"`.
     *     -   Check if the original string `s` exists within this `s_trimmed`.
     *     -   In our example, `"abab"` is a substring of `"bababa"`. If it is, the original string
     *         is composed of a repeated substring.
     *
     *     -   **Why it works**: If `s` is made of a repeating pattern (e.g., `p * k`), then `s+s` is
     *       `p * 2k`. When you remove the first and last characters, you are removing part of the
     *       first `p` and part of the last `p`. The original `s` (`p * k`) must still exist in the
     *       middle of this new string.
     *       If `s` is not made of a repeating pattern, this property will not hold.
     *
     * =================================================================================
     * GENERIC TEMPLATE (Repeated Substring Pattern)
     * =================================================================================
     */

    /**
     * Checks if a string can be constructed by taking a substring of it and appending
     * multiple copies of the substring together.
     *
     * @param s The input string.
     * @return True if the string has a repeated substring pattern, false otherwise.
     */
    public boolean repeatedSubstringPattern(String s) {
        if (s == null || s.length() <= 1) {
            return false;
        }

        // --- Core Pattern Logic: The Concatenation Trick ---
        String ss = s + s;

        // Create a substring of `ss` that excludes the first and last characters.
        String trimmed_ss = ss.substring(1, ss.length() - 1);

        // Check if the original string `s` is contained within this trimmed version.
        return trimmed_ss.contains(s);
        // -----------------------------------------------------
    }
}
