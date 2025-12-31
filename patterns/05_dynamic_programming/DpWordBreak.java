package com.framework.patterns.dynamic_programming;

import java.util.List;
import java.util.Set;
import java.util.HashSet;

/**
 * Pattern 34: DP - 1D Array (Word Break Style)
 */
public class DpWordBreak {

    /*
     * =================================================================================
     * CONDITIONS FOR APPLICABILITY
     * =================================================================================
     *
     * 1.  Input Data Structure: A string `s` and a dictionary of words `wordDict`.
     *
     * 2.  Problem Goal: You need to determine if the string `s` can be segmented into a space-separated
     *     sequence of one or more dictionary words.
     *
     * 3.  Recurrence Relation: The problem can be solved by checking all possible prefixes of the string.
     *     Let `dp[i]` be a boolean value indicating whether the substring `s.substring(0, i)` can be segmented.
     *     To compute `dp[i]`, we need to find a split point `j` (where `0 <= j < i`).
     *     `dp[i]` is true if there exists a `j` such that `dp[j]` is true AND the substring from `j` to `i`
     *     (`s.substring(j, i)`) is a word in the dictionary.
     *
     * 4.  Logic (Bottom-Up DP):
     *     -   Create a DP array, `dp`, of size `s.length() + 1`.
     *     -   `dp[i]` will be true if the first `i` characters of `s` can be broken down.
     *     -   Initialize the base case: `dp[0] = true` (an empty string can always be segmented).
     *     -   Create a Set from the `wordDict` for O(1) average time lookups.
     *     -   Iterate from `i = 1` to `s.length()`. This is the `end` of the substring we are trying to validate.
     *         -   Inside this loop, iterate from `j = 0` to `i`. This is the `start` of the substring.
     *         -   Check if `dp[j]` is true (meaning the string up to `j` is valid) AND if the substring
     *             `s.substring(j, i)` exists in the dictionary.
     *         -   If both conditions are met, then we know `dp[i]` is true. We can `break` the inner loop
     *             and move to the next `i`.
     *     -   The final answer is `dp[s.length()]`.
     *
     * =================================================================================
     * GENERIC TEMPLATE (Word Break)
     * =================================================================================
     */

    /**
     * Determines if a string can be segmented into a sequence of dictionary words.
     *
     * @param s The input string.
     * @param wordDict A list of words in the dictionary.
     * @return True if the string can be segmented, false otherwise.
     */
    public boolean wordBreak(String s, List<String> wordDict) {
        // Use a Set for efficient lookups.
        Set<String> wordSet = new HashSet<>(wordDict);

        // --- Core Pattern Logic: Initialization ---
        // dp[i] is true if s.substring(0, i) can be segmented.
        boolean[] dp = new boolean[s.length() + 1];
        // Base case: an empty string is always valid.
        dp[0] = true;
        // -------------------------------------------

        // --- Core Pattern Logic: Bottom-up calculation ---
        // `i` is the length of the prefix we are checking.
        for (int i = 1; i <= s.length(); i++) {
            // `j` is the split point.
            for (int j = 0; j < i; j++) {
                // --- Recurrence Relation ---
                // The string up to `i` is valid if:
                // 1. The string up to `j` is valid (dp[j]).
                // 2. The substring from `j` to `i` is in the dictionary.
                if (dp[j] && wordSet.contains(s.substring(j, i))) {
                    dp[i] = true;
                    break; // Found a valid segmentation for dp[i], no need to check other `j`.
                }
                // -------------------------
            }
        }
        // ------------------------------------------------

        return dp[s.length()];
    }
}
