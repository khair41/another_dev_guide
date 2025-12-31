package com.framework.patterns.string_manipulation;

/**
 * Pattern 89: String - String to Integer (atoi)
 */
public class StringAtoi {

    /*
     * =================================================================================
     * CONDITIONS FOR APPLICABILITY
     * =================================================================================
     *
     * 1.  Input Data Structure: A string.
     *
     * 2.  Problem Goal: You need to implement the `atoi` function, which converts a string to an integer.
     *
     * 3.  Logic: This is a stateful parsing problem. You must process the string according to a strict
     *     set of rules.
     *
     *     -   **Algorithm**:
     *         1.  **Ignore Whitespace**: Read in and ignore any leading whitespace.
     *         2.  **Check for Sign**: Check if the next character is '-' or '+'. Read this character in if it
     *             exists and store the sign. If both appear, it's an invalid format.
     *         3.  **Read Digits**: Read in the next characters as long as they are digits. Stop at the first
     *             non-digit character.
     *         4.  **Convert and Handle Overflow**: Convert the sequence of digits into an integer. During this
     *             process, you must be extremely careful about integer overflow.
     *             -   Before multiplying the current result by 10 and adding the next digit, check if doing
     *               so would exceed `Integer.MAX_VALUE` or `Integer.MIN_VALUE`.
     *             -   A common check is: `if (result > Integer.MAX_VALUE / 10 || (result == Integer.MAX_VALUE / 10 && digit > 7))`
     *               then an overflow will occur.
     *             -   If an overflow occurs, clamp the result to `Integer.MAX_VALUE` or `Integer.MIN_VALUE`.
     *         5.  **Apply Sign**: Apply the sign determined in step 2.
     *
     * =================================================================================
     * GENERIC TEMPLATE (String to Integer - atoi)
     * =================================================================================
     */

    /**
     * Converts a string to a 32-bit signed integer.
     *
     * @param s The input string.
     * @return The converted integer.
     */
    public int myAtoi(String s) {
        if (s == null || s.isEmpty()) {
            return 0;
        }

        int i = 0;
        int n = s.length();
        int sign = 1;
        int result = 0;

        // 1. Ignore leading whitespace
        while (i < n && s.charAt(i) == ' ') {
            i++;
        }

        // 2. Check for sign
        if (i < n && (s.charAt(i) == '+' || s.charAt(i) == '-')) {
            sign = (s.charAt(i) == '-') ? -1 : 1;
            i++;
        }

        // 3. Read digits and handle overflow
        while (i < n && Character.isDigit(s.charAt(i))) {
            int digit = s.charAt(i) - '0';

            // --- Core Pattern Logic: Overflow Check ---
            if (result > Integer.MAX_VALUE / 10 || (result == Integer.MAX_VALUE / 10 && digit > 7)) {
                return (sign == 1) ? Integer.MAX_VALUE : Integer.MIN_VALUE;
            }
            // -------------------------------------------

            result = result * 10 + digit;
            i++;
        }

        return result * sign;
    }
}
