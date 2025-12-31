package com.framework.patterns.bit_manipulation;

/**
 * Pattern 71: Bitwise AND - Counting Set Bits (Hamming Weight)
 */
public class BitwiseAndCountingSetBits {

    /*
     * =================================================================================
     * CONDITIONS FOR APPLICABILITY
     * =================================================================================
     *
     * 1.  Input Data Structure: An integer.
     *
     * 2.  Problem Goal: You need to count the number of '1's in the binary representation of the integer.
     *     This is also known as the Hamming weight or population count.
     *
     * 3.  Logic: There are a few ways to do this, but a particularly clever and efficient bit manipulation
     *     trick uses the bitwise AND operator.
     *
     *     -   **The Trick**: The operation `n & (n - 1)` clears the least significant set bit (the rightmost '1').
     *         -   Example: `n = 12` (binary `1100`). `n - 1 = 11` (binary `1011`).
     *           `1100 & 1011 = 1000`. The rightmost '1' has been cleared.
     *         -   Next step: `n = 8` (binary `1000`). `n - 1 = 7` (binary `0111`).
     *           `1000 & 0111 = 0000`. The rightmost '1' has been cleared.
     *
     *     -   **Algorithm**: Initialize a `count` to 0. While the number `n` is not 0, repeatedly apply
     *       the operation `n = n & (n - 1)` and increment the `count` in each iteration. The number of
     *       iterations will be exactly equal to the number of set bits.
     *
     * =================================================================================
     * GENERIC TEMPLATE (Hamming Weight)
     * =================================================================================
     */

    /**
     * Counts the number of set bits (1s) in the binary representation of an integer.
     *
     * @param n The input integer. Can be positive or negative.
     * @return The number of set bits.
     */
    public int hammingWeight(int n) {
        int count = 0;

        // --- Core Pattern Logic ---
        // The loop will run exactly as many times as there are set bits.
        while (n != 0) {
            // This operation clears the least significant set bit.
            n = n & (n - 1);
            count++;
        }
        // --------------------------

        return count;
    }
}
