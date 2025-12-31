package com.framework.patterns.string_manipulation;

/**
 * Pattern 90: String - Multiply Strings (Manual Simulation)
 */
public class StringMultiply {

    /*
     * =================================================================================
     * CONDITIONS FOR APPLICABILITY
     * =================================================================================
     *
     * 1.  Input Data Structure: Two non-negative integers represented as strings.
     *
     * 2.  Problem Goal: You need to multiply the two numbers and return the result as a string,
     *     without using any built-in BigInteger libraries or converting the inputs to integers directly.
     *
     * 3.  Logic: The process mimics manual, long-form multiplication.
     *
     *     -   The length of the result will be at most `len1 + len2`.
     *     -   Create an integer array `pos` of size `len1 + len2` to store the intermediate products.
     *     -   Iterate from right to left through the first number (`num1`) with index `i`.
     *     -   Inside that loop, iterate from right to left through the second number (`num2`) with index `j`.
     *     -   For each pair of digits `d1` and `d2`, calculate the product `mul = d1 * d2`.
     *     -   The positions in the `pos` array for this product are `p1 = i + j` (for the carry) and
     *         `p2 = i + j + 1` (for the digit).
     *     -   Add the product to the value already at `pos[p2]`: `sum = mul + pos[p2]`.
     *     -   Update the `pos` array: `pos[p2] = sum % 10` and `pos[p1] += sum / 10`.
     *     -   After the loops, convert the `pos` array into a string, being careful to handle leading zeros.
     *
     * =================================================================================
     * GENERIC TEMPLATE (Multiply Strings)
     * =================================================================================
     */

    /**
     * Multiplies two non-negative integers represented as strings.
     *
     * @param num1 The first number string.
     * @param num2 The second number string.
     * @return The product, as a string.
     */
    public String multiply(String num1, String num2) {
        if (num1.equals("0") || num2.equals("0")) {
            return "0";
        }

        int m = num1.length();
        int n = num2.length();
        int[] pos = new int[m + n];

        // --- Core Pattern Logic: Grade-school multiplication ---
        for (int i = m - 1; i >= 0; i--) {
            for (int j = n - 1; j >= 0; j--) {
                int d1 = num1.charAt(i) - '0';
                int d2 = num2.charAt(j) - '0';
                int mul = d1 * d2;

                int p1 = i + j;     // Position for carry
                int p2 = i + j + 1; // Position for digit

                int sum = mul + pos[p2];
                pos[p1] += sum / 10; // Add carry to the left position
                pos[p2] = sum % 10;  // Set current digit
            }
        }
        // -----------------------------------------------------

        // --- Convert result array to string ---
        StringBuilder sb = new StringBuilder();
        for (int p : pos) {
            // Skip leading zeros.
            if (!(sb.length() == 0 && p == 0)) {
                sb.append(p);
            }
        }
        // -------------------------------------

        return sb.length() == 0 ? "0" : sb.toString();
    }
}
