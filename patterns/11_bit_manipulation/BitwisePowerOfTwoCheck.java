package com.framework.patterns.bit_manipulation;

/**
 * Pattern 73: Bitwise Operations - Power of Two/Four Check
 */
public class BitwisePowerOfTwoCheck {

    /*
     * =================================================================================
     * CONDITIONS FOR APPLICABILITY
     * =================================================================================
     *
     * 1.  Problem Goal: You need to determine if a given integer is a power of two, a power of four,
     *     or has similar properties related to having only a single bit set.
     *
     * 2.  Logic (Power of Two):
     *     -   A number `n` is a power of two if and only if it is positive and has exactly one
     *       set bit in its binary representation (e.g., 1, 2, 4, 8 -> 0001, 0010, 0100, 1000).
     *     -   The expression `n & (n - 1)` clears the least significant set bit.
     *     -   If `n` is a power of two, it has only one set bit. Therefore, clearing this bit will
     *       result in 0.
     *     -   The condition is: `n > 0 && (n & (n - 1)) == 0`.
     *
     * 3.  Logic (Power of Four):
     *     -   A number must first be a power of two.
     *     -   Additionally, its single set bit must appear in an "even" position (0th, 2nd, 4th, etc.).
     *       e.g., 4 is `100` (bit at pos 2), 16 is `10000` (bit at pos 4).
     *     -   This can be checked in two main ways:
     *         1.  **Bitmask**: A special bitmask `0xAAAAAAAA` (in hex) has all its odd-positioned bits set
     *             (binary `10101010...`). If we AND a power of four with this mask, the result will be 0
     *             because its single set bit is in an even position.
     *             Condition: `isPowerOfTwo(n) && (n & 0xAAAAAAAA) == 0`.
     *         2.  **Modulo Arithmetic**: Any power of four minus 1 is divisible by 3.
     *             (4-1=3, 16-1=15, 64-1=63). This property does not hold for other powers of two (2-1=1, 8-1=7).
     *             Condition: `isPowerOfTwo(n) && (n - 1) % 3 == 0`.
     *
     * =================================================================================
     * GENERIC TEMPLATE (Power of Two / Power of Four)
     * =================================================================================
     */

    /**
     * Checks if a given integer is a power of two.
     *
     * @param n The input integer.
     * @return True if n is a power of two, false otherwise.
     */
    public boolean isPowerOfTwo(int n) {
        // A power of two must be positive and have exactly one set bit.
        // The `n & (n - 1)` trick clears the LSB. If there was only one bit, the result is 0.
        return n > 0 && (n & (n - 1)) == 0;
    }

    /**
     * Checks if a given integer is a power of four.
     *
     * @param n The input integer.
     * @return True if n is a power of four, false otherwise.
     */
    public boolean isPowerOfFour(int n) {
        // A number must first be a power of two.
        if (!isPowerOfTwo(n)) {
            return false;
        }
        // Second, among powers of two, powers of four have their single set bit
        // at an even position. (n-1) for a power of 4 is always divisible by 3.
        return (n - 1) % 3 == 0;
    }
}
