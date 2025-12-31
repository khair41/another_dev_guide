package com.framework.patterns.two_pointers;

/**
 * Pattern 6: Two Pointers - Expanding From Center (Palindromes)
 */
public class TwoPointersExpandingFromCenter {

    /*
     * =================================================================================
     * CONDITIONS FOR APPLICABILITY
     * =================================================================================
     *
     * 1.  Input Data Structure: A string or an array.
     *
     * 2.  Problem Goal: You need to find the longest, or count all, palindromic substrings.
     *     A palindrome reads the same forwards and backward.
     *
     * 3.  Logic: The core idea is that any palindrome has a center. This center can be a single
     *     character (for odd-length palindromes like "racecar") or a pair of characters
     *     (for even-length palindromes like "aabbaa").
     *
     *     - Iterate through every possible center of the string.
     *     - For each character in the string, treat it as a potential center for an odd-length palindrome.
     *     - For each pair of adjacent characters, treat them as a potential center for an even-length palindrome.
     *     - From each center, use two pointers (left and right) and expand outwards as long as the
     *       characters at `left` and `right` are the same and the pointers are within the bounds of the string.
     *
     * =================================================================================
     * GENERIC TEMPLATE (Finding the Longest Palindromic Substring)
     * =================================================================================
     */

    private int longestPalindromeStart = 0;
    private int longestPalindromeLength = 0;

    /**
     * Finds the longest palindromic substring in a given string.
     *
     * @param s The input string.
     * @return The longest palindromic substring.
     */
    public String longestPalindrome(String s) {
        if (s == null || s.length() < 1) {
            return "";
        }

        // --- Core Pattern Logic: Iterate through all possible centers ---
        for (int i = 0; i < s.length(); i++) {
            // Case 1: Odd-length palindrome (center is a single character)
            expandFromCenter(s, i, i);

            // Case 2: Even-length palindrome (center is between two characters)
            expandFromCenter(s, i, i + 1);
        }
        // -------------------------------------------------------------

        return s.substring(longestPalindromeStart, longestPalindromeStart + longestPalindromeLength);
    }

    /**
     * Helper method to expand from a center and update the longest palindrome found.
     * @param s The input string.
     * @param left The initial left pointer.
     * @param right The initial right pointer.
     */
    private void expandFromCenter(String s, int left, int right) {
        // --- Core Pattern Logic: Expand while characters match and pointers are in bounds ---
        while (left >= 0 && right < s.length() && s.charAt(left) == s.charAt(right)) {
            // --- Problem-Specific Logic: Update the result ---
            int currentLength = right - left + 1;
            if (currentLength > longestPalindromeLength) {
                longestPalindromeLength = currentLength;
                longestPalindromeStart = left;
            }
            // ------------------------------------------------

            // Move pointers outwards for the next check.
            left--;
            right++;
        }
        // ----------------------------------------------------------------------------------
    }
}
