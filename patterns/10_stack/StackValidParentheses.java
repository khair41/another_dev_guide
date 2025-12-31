package com.framework.patterns.stack;

import java.util.Stack;

/**
 * Pattern 64: Stack - Valid Parentheses Matching
 */
public class StackValidParentheses {

    /*
     * =================================================================================
     * CONDITIONS FOR APPLICABILITY
     * =================================================================================
     *
     * 1.  Input Data Structure: A string containing different types of opening and closing brackets
     *     (e.g., `()`, `{}`, `[]`).
     *
     * 2.  Problem Goal: You need to determine if the brackets in the string are "well-formed" or "valid".
     *     This means every opening bracket must have a corresponding closing bracket of the same type,
     *     and they must be properly nested.
     *
     * 3.  Logic: A stack is the perfect data structure for this problem because it naturally handles
     *     the LIFO (Last-In, First-Out) nature of nested structures. The most recently opened bracket
     *     must be the first one to be closed.
     *
     *     -   Iterate through the input string character by character.
     *     -   **If you see an opening bracket** (`(`, `{`, `[`): Push it onto the stack.
     *     -   **If you see a closing bracket** (`)`, `}`, `]`):
     *         -   Check if the stack is empty. If it is, there is no matching opening bracket, so the
     *             string is invalid.
     *         -   Pop the top element from the stack.
     *         -   Check if the popped opening bracket correctly matches the current closing bracket.
     *             If they don't match, the string is invalid.
     *     -   **After the loop**: If the stack is empty, it means every opening bracket found a matching
     *       closing bracket. The string is valid. If the stack is not empty, it means there are
     *       unclosed opening brackets, so the string is invalid.
     *
     * =================================================================================
     * GENERIC TEMPLATE (Valid Parentheses)
     * =================================================================================
     */

    /**
     * Checks if a string containing just the characters '(', ')', '{', '}', '[' and ']' is valid.
     *
     * @param s The input string.
     * @return True if the input string is valid, false otherwise.
     */
    public boolean isValid(String s) {
        if (s == null || s.length() % 2 != 0) {
            return false; // An odd-length string can never be valid.
        }

        Stack<Character> stack = new Stack<>();

        // --- Core Pattern Logic ---
        for (char c : s.toCharArray()) {
            if (c == '(' || c == '{' || c == '[') {
                // If it's an opening bracket, push it onto the stack.
                stack.push(c);
            } else {
                // If it's a closing bracket, check for a match.
                if (stack.isEmpty()) {
                    return false; // Closing bracket with no corresponding opener.
                }

                char top = stack.pop();
                if ((c == ')' && top != '(') ||
                    (c == '}' && top != '{') ||
                    (c == ']' && top != '[')) {
                    return false; // Mismatched brackets.
                }
            }
        }
        // --------------------------

        // If the stack is empty at the end, all brackets were matched correctly.
        return stack.isEmpty();
    }
}
