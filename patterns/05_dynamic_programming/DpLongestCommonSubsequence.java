package com.framework.patterns.dynamic_programming;

/**
 * Pattern 35: DP - 2D Array (Longest Common Subsequence - LCS)
 */
public class DpLongestCommonSubsequence {

    /*
     * =================================================================================
     * CONDITIONS FOR APPLICABILITY
     * =================================================================================
     *
     * 1.  Input Data Structure: Two sequences, typically strings (`text1`, `text2`).
     *
     * 2.  Problem Goal: You need to find the length of the longest subsequence that is common to
     *     both sequences. A subsequence is formed by deleting zero or more characters from a string,
     *     maintaining the relative order of the remaining characters.
     *     Example: LCS of "abcde" and "ace" is "ace", with length 3.
     *
     * 3.  Recurrence Relation: This problem requires a 2D DP table.
     *     Let `dp[i][j]` be the length of the LCS of the first `i` characters of `text1` and the
     *     first `j` characters of `text2`.
     *     -   If `text1.charAt(i-1) == text2.charAt(j-1)`:
     *         The characters match. The LCS length is 1 + the LCS of the strings without these characters.
     *         `dp[i][j] = 1 + dp[i-1][j-1]`
     *     -   If `text1.charAt(i-1) != text2.charAt(j-1)`:
     *         The characters do not match. The LCS is the best of either ignoring the character from `text1`
     *         or ignoring the character from `text2`.
     *         `dp[i][j] = max(dp[i-1][j], dp[i][j-1])`
     *
     * 4.  Logic (Bottom-Up DP):
     *     -   Create a 2D DP array, `dp`, of size `(text1.length() + 1) x (text2.length() + 1)`.
     *     -   The first row and column are implicitly 0, representing the LCS with an empty string.
     *     -   Iterate with a nested loop from `i = 1` to `text1.length()` and `j = 1` to `text2.length()`.
     *     -   Inside the loop, fill `dp[i][j]` using the recurrence relation described above.
     *     -   The final answer is `dp[text1.length()][text2.length()]`.
     *
     * =================================================================================
     * GENERIC TEMPLATE (Longest Common Subsequence)
     * =================================================================================
     */

    /**
     * Calculates the length of the longest common subsequence of two strings.
     *
     * @param text1 The first string.
     * @param text2 The second string.
     * @return The length of the LCS.
     */
    public int longestCommonSubsequence(String text1, String text2) {
        int m = text1.length();
        int n = text2.length();

        // --- Core Pattern Logic: Initialization ---
        // dp[i][j] stores the LCS of text1[0..i-1] and text2[0..j-1].
        int[][] dp = new int[m + 1][n + 1];
        // The table is automatically initialized to 0s, which handles the base cases.
        // -------------------------------------------

        // --- Core Pattern Logic: Bottom-up calculation ---
        for (int i = 1; i <= m; i++) {
            for (int j = 1; j <= n; j++) {
                // --- Recurrence Relation ---
                if (text1.charAt(i - 1) == text2.charAt(j - 1)) {
                    // Characters match, so we extend the LCS from the smaller subproblem.
                    dp[i][j] = 1 + dp[i - 1][j - 1];
                } else {
                    // Characters don't match, so we take the best result from the two
                    // previous subproblems (either excluding char from text1 or text2).
                    dp[i][j] = Math.max(dp[i - 1][j], dp[i][j - 1]);
                }
                // -------------------------
            }
        }
        // ------------------------------------------------

        return dp[m][n];
    }
}
