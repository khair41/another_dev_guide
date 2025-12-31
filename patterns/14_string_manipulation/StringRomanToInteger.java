package com.framework.patterns.string_manipulation;

import java.util.Map;
import java.util.HashMap;

/**
 * Pattern 88: String - Roman to Integer Conversion
 */
public class StringRomanToInteger {

    /*
     * =================================================================================
     * CONDITIONS FOR APPLICABILITY
     * =================================================================================
     *
     * 1.  Input Data Structure: A string representing a Roman numeral.
     *
     * 2.  Problem Goal: You need to convert the Roman numeral string into its corresponding integer value.
     *
     * 3.  Logic: The key to parsing Roman numerals is the subtraction rule. While numerals are generally
     *     written from largest to smallest, there are exceptions where a smaller value preceding a larger
     *     one indicates subtraction (e.g., IV = 4, IX = 9, XL = 40, etc.).
     *
     *     -   **Algorithm**: A simple way to handle this is to iterate from left to right.
     *         1.  Create a map to store the integer value of each Roman numeral character (I=1, V=5, etc.).
     *         2.  Initialize a `total` sum to 0.
     *         3.  Iterate through the string from `i = 0` to `n-1`.
     *         4.  For each character at index `i`, get its value. Look ahead at the character at `i+1`.
     *         5.  **If `value(s[i]) < value(s[i+1])`**: This is the subtraction case. Subtract `value(s[i])`
     *             from the total.
     *         6.  **Otherwise**: This is the normal addition case. Add `value(s[i])` to the total.
     *     -   An even simpler variation is to iterate from right to left, which avoids the lookahead.
     *
     * =================================================================================
     * GENERIC TEMPLATE (Roman to Integer)
     * =================================================================================
     */

    /**
     * Converts a Roman numeral to an integer.
     *
     * @param s The Roman numeral string.
     * @return The integer value.
     */
    public int romanToInt(String s) {
        Map<Character, Integer> romanMap = new HashMap<>();
        romanMap.put('I', 1);
        romanMap.put('V', 5);
        romanMap.put('X', 10);
        romanMap.put('L', 50);
        romanMap.put('C', 100);
        romanMap.put('D', 500);
        romanMap.put('M', 1000);

        int total = 0;

        // --- Core Pattern Logic: Iterate left-to-right with lookahead ---
        for (int i = 0; i < s.length(); i++) {
            int currentValue = romanMap.get(s.charAt(i));

            // Check if there is a next character and if it represents a larger value.
            if (i < s.length() - 1 && currentValue < romanMap.get(s.charAt(i + 1))) {
                // Subtraction case
                total -= currentValue;
            } else {
                // Addition case
                total += currentValue;
            }
        }
        // ----------------------------------------------------------------

        return total;
    }
}
