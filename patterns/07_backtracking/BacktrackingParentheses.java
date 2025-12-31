package com.framework.patterns.backtracking;

import java.util.ArrayList;
import java.util.List;

/**
 * Pattern 49: Backtracking - Parentheses Generation
 */
public class BacktrackingParentheses {

    /*
     * =================================================================================
     * CONDITIONS FOR APPLICABILITY
     * =================================================================================
     *
     * 1.  Input Data Structure: An integer `n` representing the number of pairs of parentheses.
     *
     * 2.  Problem Goal: You need to generate all combinations of well-formed (valid) parentheses.
     *     A string of parentheses is well-formed if every opening parenthesis has a corresponding
     *     closing parenthesis and they are correctly nested.
     *
     * 3.  Logic: The backtracking function builds a string character by character, deciding at each
     *     step whether to add an opening `(` or a closing `)` parenthesis.
     *
     *     -   The state tracked by the recursive function includes the current string being built,
     *         the number of open parentheses used so far (`open_count`), and the number of closed
     *         parentheses used so far (`close_count`).
     *
     *     -   **Constraints for valid choices**:
     *         1.  You can add an opening parenthesis `(` only if `open_count < n`.
     *         2.  You can add a closing parenthesis `)` only if `close_count < open_count`.
     *             This is the key constraint that ensures the string remains well-formed at all times.
     *
     *     -   **Recursive Step**:
     *         1.  **Base Case**: If the length of the current string is `2 * n`, a complete and valid
     *             combination has been formed. Add it to the results and return.
     *         2.  **Choice 1 (Add Open)**: If `open_count < n`, add `(` to the string and make a recursive
     *             call with `open_count + 1`.
     *         3.  **Backtrack**: After the recursive call returns, remove the `(` from the string.
     *         4.  **Choice 2 (Add Close)**: If `close_count < open_count`, add `)` to the string and make a
     *             recursive call with `close_count + 1`.
     *         5.  **Backtrack**: After the recursive call returns, remove the `)` from the string.
     *
     * =================================================================================
     * GENERIC TEMPLATE (Generate Well-Formed Parentheses)
     * =================================================================================
     */

    /**
     * Generates all combinations of well-formed parentheses.
     *
     * @param n The number of pairs of parentheses.
     * @return A list of all valid parenthesis combinations.
     */
    public List<String> generateParenthesis(int n) {
        List<String> result = new ArrayList<>();
        if (n <= 0) {
            return result;
        }
        backtrack(n, 0, 0, new StringBuilder(), result);
        return result;
    }

    /**
     * The recursive backtracking helper function.
     *
     * @param n The total number of pairs required.
     * @param openCount The number of open parentheses used so far.
     * @param closeCount The number of close parentheses used so far.
     * @param currentString The string being built.
     * @param result The list to store all valid combinations.
     */
    private void backtrack(int n, int openCount, int closeCount, StringBuilder currentString, List<String> result) {
        // --- Base Case: The string is complete ---
        if (currentString.length() == n * 2) {
            result.add(currentString.toString());
            return;
        }
        // -----------------------------------------

        // --- Choice 1: Add an open parenthesis ---
        if (openCount < n) {
            // Choose
            currentString.append("(");
            // Recurse
            backtrack(n, openCount + 1, closeCount, currentString, result);
            // Unchoose (Backtrack)
            currentString.deleteCharAt(currentString.length() - 1);
        }
        // -----------------------------------------

        // --- Choice 2: Add a close parenthesis ---
        if (closeCount < openCount) {
            // Choose
            currentString.append(")");
            // Recurse
            backtrack(n, openCount, closeCount + 1, currentString, result);
            // Unchoose (Backtrack)
            currentString.deleteCharAt(currentString.length() - 1);
        }
        // -----------------------------------------
    }
}
