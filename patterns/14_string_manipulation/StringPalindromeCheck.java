package com.framework.patterns.string_manipulation;

/**
 * Pattern 86: String - Palindrome Check (Two Pointers / Reverse)
 */
public class StringPalindromeCheck {

    /*
     * =================================================================================
     * CONDITIONS FOR APPLICABILITY
     * =================================================================================
     *
     * 1.  Input Data Structure: A string.
     *
     * 2.  Problem Goal: You need to determine if a string is a palindrome, meaning it reads the
     *     same forwards and backward. The check often needs to ignore non-alphanumeric characters
     *     and be case-insensitive.
     *
     * 3.  Logic: The most efficient way to check for a palindrome in-place is using the Two Pointers pattern.
     *
     *     -   Initialize a `left` pointer to the start of the string and a `right` pointer to the end.
     *     -   Loop as long as `left < right`.
     *     -   **Preprocessing (if needed)**: In each iteration, you might need to advance the `left`
     *         pointer forward past any non-alphanumeric characters and advance the `right` pointer
     *         backward past any non-alphanumeric characters.
     *     -   **Comparison**: Compare the characters at the `left` and `right` pointers (often after
     *       converting them to lowercase). If they are not the same, the string is not a palindrome,
     *       and you can return `false` immediately.
     *     -   If the characters match, move the pointers closer to the center: `left++` and `right--`.
     *     -   If the loop completes without finding any mismatches, the string is a palindrome.
     *
     * =================================================================================
     * GENERIC TEMPLATE (Valid Palindrome)
     * =================================================================================
     */

    /**
     * Checks if a string is a palindrome, considering only alphanumeric characters and ignoring cases.
     *
     * @param s The input string.
     * @return True if the string is a palindrome, false otherwise.
     */
    public boolean isPalindrome(String s) {
        if (s == null) {
            return false;
        }

        int left = 0;
        int right = s.length() - 1;

        // --- Core Pattern Logic: Two Pointers Converging ---
        while (left < right) {
            // --- Problem-Specific Logic: Skip non-alphanumeric characters ---
            while (left < right && !Character.isLetterOrDigit(s.charAt(left))) {
                left++;
            }
            while (left < right && !Character.isLetterOrDigit(s.charAt(right))) {
                right--;
            }
            // -------------------------------------------------------------

            // --- Comparison ---
            if (Character.toLowerCase(s.charAt(left)) != Character.toLowerCase(s.charAt(right))) {
                return false;
            }
            // ----------------

            // Move pointers towards the center.
            left++;
            right--;
        }
        // -------------------------------------------------------

        return true;
    }
}
