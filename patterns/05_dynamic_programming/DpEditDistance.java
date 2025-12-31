package com.framework.patterns.dynamic_programming;

/**
 * Pattern 36: DP - 2D Array (Edit Distance / Levenshtein Distance)
 */
public class DpEditDistance {

    /*
     * =================================================================================
     * CONDITIONS FOR APPLICABILITY
     * =================================================================================
     *
     * 1.  Input Data Structure: Two strings (`word1`, `word2`).
     *
     * 2.  Problem Goal: You need to find the minimum number of operations (insert, delete, or replace)
     *     required to transform `word1` into `word2`.
     *
     * 3.  Recurrence Relation: This problem uses a 2D DP table, similar to Longest Common Subsequence.
     *     Let `dp[i][j]` be the minimum edit distance between the first `i` characters of `word1` and
     *     the first `j` characters of `word2`.
     *     -   If `word1.charAt(i-1) == word2.charAt(j-1)`:
     *         The characters match, so no operation is needed at this step. The cost is the same as the
     *         edit distance for the strings without these characters.
     *         `dp[i][j] = dp[i-1][j-1]`
     *     -   If `word1.charAt(i-1) != word2.charAt(j-1)`:
     *         The characters do not match. We must perform one operation. We take the minimum of the three
     *         possible operations:
     *         1.  **Replace**: Change `word1.charAt(i-1)` to `word2.charAt(j-1)`. Cost is `1 + dp[i-1][j-1]`.
     *         2.  **Delete**: Delete `word1.charAt(i-1)`. Cost is `1 + dp[i-1][j]`.
     *         3.  **Insert**: Insert `word2.charAt(j-1)` into `word1`. Cost is `1 + dp[i][j-1]`.
     *         `dp[i][j] = 1 + min(dp[i-1][j-1], dp[i-1][j], dp[i][j-1])`
     *
     * 4.  Logic (Bottom-Up DP):
     *     -   Create a 2D DP array, `dp`, of size `(word1.length() + 1) x (word2.length() + 1)`.
     *     -   Initialize the base cases: The first row and column represent the cost of transforming a
     *         prefix into an empty string, which is simply the length of the prefix (all deletions/insertions).
     *         `dp[i][0] = i` and `dp[0][j] = j`.
     *     -   Iterate with a nested loop and fill `dp[i][j]` using the recurrence relation.
     *     -   The final answer is `dp[word1.length()][word2.length()]`.
     *
     * =================================================================================
     * GENERIC TEMPLATE (Edit Distance)
     * =================================================================================
     */

    /**
     * Calculates the minimum edit distance between two words.
     *
     * @param word1 The first word.
     * @param word2 The second word.
     * @return The minimum number of operations (insert, delete, replace) to transform word1 to word2.
     */
    public int minDistance(String word1, String word2) {
        int m = word1.length();
        int n = word2.length();

        int[][] dp = new int[m + 1][n + 1];

        // --- Core Pattern Logic: Base Cases ---
        // Cost to transform a prefix of word1 into an empty string is i deletions.
        for (int i = 0; i <= m; i++) {
            dp[i][0] = i;
        }
        // Cost to transform an empty string into a prefix of word2 is j insertions.
        for (int j = 0; j <= n; j++) {
            dp[0][j] = j;
        }
        // ---------------------------------------

        // --- Core Pattern Logic: Bottom-up calculation ---
        for (int i = 1; i <= m; i++) {
            for (int j = 1; j <= n; j++) {
                // --- Recurrence Relation ---
                if (word1.charAt(i - 1) == word2.charAt(j - 1)) {
                    // Characters are the same, no cost.
                    dp[i][j] = dp[i - 1][j - 1];
                } else {
                    // Characters are different, take the minimum of the 3 operations.
                    int replaceCost = dp[i - 1][j - 1];
                    int deleteCost = dp[i - 1][j];
                    int insertCost = dp[i][j - 1];
                    dp[i][j] = 1 + Math.min(replaceCost, Math.min(deleteCost, insertCost));
                }
                // -------------------------
            }
        }
        // ------------------------------------------------

        return dp[m][n];
    }
}
