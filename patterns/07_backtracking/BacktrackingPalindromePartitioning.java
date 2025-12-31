package com.framework.patterns.backtracking;

import java.util.ArrayList;
import java.util.List;

/**
 * Pattern 52: Backtracking - Palindrome Partitioning
 */
public class BacktrackingPalindromePartitioning {

    /*
     * =================================================================================
     * CONDITIONS FOR APPLICABILITY
     * =================================================================================
     *
     * 1.  Input Data Structure: A string `s`.
     *
     * 2.  Problem Goal: You need to partition the string `s` such that every substring in the
     *     partition is a palindrome. The goal is to find all possible such partitions.
     *
     * 3.  Logic: This problem can be solved by iterating through all possible prefixes of the string,
     *     checking if a prefix is a palindrome, and if it is, recursively partitioning the rest of the string.
     *
     *     -   The recursive function takes the current starting index (`start`) in the string, the
     *         current partition being built, and the list for all results.
     *
     *     -   **Recursive Step**:
     *         1.  **Base Case**: If `start` reaches the end of the string (`start >= s.length()`), it means
     *             we have successfully partitioned the entire string. Add the current partition to the results.
     *         2.  **Explore**: Iterate from the `start` index to the end of the string. Let the iterator be `end`.
     *         3.  **Check & Choose**: For each `end`, check if the substring from `start` to `end` (inclusive)
     *             is a palindrome.
     *             -   If it is a palindrome, add this substring to the current partition.
     *         4.  **Recurse**: Make a recursive call to partition the rest of the string, starting from `end + 1`.
     *         5.  **Unchoose (Backtrack)**: After the recursive call returns, remove the palindrome substring
     *             you just added. This allows you to explore longer partitions starting from the same `start` index.
     *
     * =================================================================================
     * GENERIC TEMPLATE (Palindrome Partitioning)
     * =================================================================================
     */

    /**
     * Finds all possible palindrome partitions of a string.
     *
     * @param s The input string.
     * @return A list of all possible palindrome partitions.
     */
    public List<List<String>> partition(String s) {
        List<List<String>> result = new ArrayList<>();
        if (s == null || s.isEmpty()) {
            return result;
        }
        backtrack(s, 0, new ArrayList<>(), result);
        return result;
    }

    /**
     * The recursive backtracking helper function.
     *
     * @param s The original string.
     * @param start The starting index for the current partition.
     * @param currentPartition The list of palindrome substrings found so far.
     * @param result The list to store all valid partitions.
     */
    private void backtrack(String s, int start, List<String> currentPartition, List<List<String>> result) {
        // --- Base Case: We have successfully partitioned the entire string ---
        if (start >= s.length()) {
            result.add(new ArrayList<>(currentPartition));
            return;
        }
        // ------------------------------------------------------------------

        // --- Explore: Try all possible ending points for the next palindrome ---
        for (int end = start; end < s.length(); end++) {
            // --- Check: If the substring from start to end is a palindrome ---
            if (isPalindrome(s, start, end)) {
                // 1. Choose: Add the palindrome substring to the current partition.
                currentPartition.add(s.substring(start, end + 1));

                // 2. Recurse: Find partitions for the rest of the string.
                backtrack(s, end + 1, currentPartition, result);

                // 3. Unchoose (Backtrack): Remove the substring to explore other possibilities.
                currentPartition.remove(currentPartition.size() - 1);
            }
        }
        // ----------------------------------------------------------------------
    }

    /**
     * Helper function to check if a substring is a palindrome.
     */
    private boolean isPalindrome(String s, int low, int high) {
        while (low < high) {
            if (s.charAt(low++) != s.charAt(high--)) {
                return false;
            }
        }
        return true;
    }
}
