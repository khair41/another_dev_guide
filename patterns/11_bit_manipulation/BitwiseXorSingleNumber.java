package com.framework.patterns.bit_manipulation;

/**
 * Pattern 70: Bitwise XOR - Finding Single/Missing Number
 */
public class BitwiseXorSingleNumber {

    /*
     * =================================================================================
     * CONDITIONS FOR APPLICABILITY
     * =================================================================================
     *
     * 1.  Input Data Structure: An array of numbers.
     *
     * 2.  Problem Goal: You need to find an element that appears an odd number of times while all
     *     other elements appear an even number of times. The most common case is finding the single
     *     number in an array where every other number appears exactly twice.
     *
     * 3.  Logic: This pattern relies on the properties of the XOR (exclusive OR) bitwise operator.
     *     -   `x ^ x = 0` (XORing a number with itself results in zero).
     *     -   `x ^ 0 = x` (XORing a number with zero results in the number itself).
     *     -   `x ^ y = y ^ x` (XOR is commutative).
     *     -   `(x ^ y) ^ z = x ^ (y ^ z)` (XOR is associative).
     *
     *     -   **Algorithm**: Initialize a variable `result` to 0. Iterate through the array and XOR
     *       each number with `result`. `result = result ^ num`.
     *     -   **Why it works**: If a number appears twice, it will be XORed with itself, effectively
     *       canceling out (e.g., `(a ^ b ^ c ^ a ^ b)` becomes `(a^a) ^ (b^b) ^ c`, which simplifies
     *       to `0 ^ 0 ^ c`, which is `c`). The only number remaining after all the pairs have canceled
     *       out is the one that appeared an odd number of times.
     *
     * =================================================================================
     * GENERIC TEMPLATE (Single Number)
     * =================================================================================
     */

    /**
     * Finds the single element in an array where every other element appears twice.
     *
     * @param nums The input array.
     * @return The single number that appears only once.
     */
    public int singleNumber(int[] nums) {
        int result = 0;

        // --- Core Pattern Logic: XOR all elements together ---
        for (int num : nums) {
            result ^= num;
        }
        // -----------------------------------------------------

        // The result will be the number that appeared an odd number of times.
        return result;
    }
}
