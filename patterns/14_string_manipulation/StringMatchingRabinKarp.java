package com.framework.patterns.string_manipulation;

/**
 * Pattern 91: String Matching - Naive / KMP / Rabin-Karp
 */
public class StringMatchingRabinKarp {

    /*
     * =================================================================================
     * CONDITIONS FOR APPLICABILITY
     * =================================================================================
     *
     * 1.  Input Data Structure: A `haystack` string and a `needle` string.
     *
     * 2.  Problem Goal: You need to find the first occurrence of the `needle` string within the
     *     `haystack` string.
     *
     * 3.  Logic (Rabin-Karp Algorithm):
     *     -   This algorithm avoids the O(N*M) complexity of naive string comparison by using a "rolling hash".
     *       Instead of comparing the strings character by character, it compares the hash values of the
     *       `needle` and the current window of the `haystack`.
     *
     *     -   **Algorithm**:
     *         1.  Calculate the hash of the `needle` string.
     *         2.  Calculate the hash of the initial window in the `haystack` (of the same length as the `needle`).
     *         3.  Iterate through the `haystack`, sliding the window one character at a time.
     *         4.  In each step, efficiently update the hash of the `haystack` window. This is the "rolling hash".
     *             It's done by subtracting the hash contribution of the character leaving the window and adding
     *             the hash contribution of the character entering the window.
     *         5.  If `hash(haystack_window) == hash(needle)`, it's a potential match. Because hash collisions
     *             can occur, you must then do a character-by-character comparison of the window and the needle
     *             to confirm the match.
     *         6.  If the character comparison is successful, you have found the match and can return the starting index.
     *
     * =================================================================================
     * GENERIC TEMPLATE (strStr() using Rabin-Karp)
     * =================================================================================
     */

    /**
     * Finds the first occurrence of the needle in a haystack using Rabin-Karp.
     *
     * @param haystack The string to search in.
     * @param needle The string to search for.
     * @return The index of the first occurrence, or -1 if not found.
     */
    public int strStr(String haystack, String needle) {
        int n = haystack.length();
        int m = needle.length();
        if (m == 0) return 0;
        if (n < m) return -1;

        // Using a simple polynomial rolling hash with a prime base and modulus
        long prime = 31;
        long modulus = 1_000_000_007;

        long needleHash = 0;
        long haystackHash = 0;
        long power = 1; // To calculate hash and for rolling

        // --- Calculate initial hashes and the power for the rolling hash ---
        for (int i = 0; i < m; i++) {
            needleHash = (needleHash * prime + needle.charAt(i)) % modulus;
            haystackHash = (haystackHash * prime + haystack.charAt(i)) % modulus;
            if (i < m - 1) {
                power = (power * prime) % modulus;
            }
        }
        // ------------------------------------------------------------------

        // --- Core Pattern Logic: Slide the window and roll the hash ---
        for (int i = 0; i <= n - m; i++) {
            // Check for hash match
            if (needleHash == haystackHash) {
                // If hashes match, do a full string comparison to avoid collisions.
                if (haystack.substring(i, i + m).equals(needle)) {
                    return i;
                }
            }

            // Roll the hash for the next window
            if (i < n - m) {
                long firstCharVal = haystack.charAt(i);
                long nextCharVal = haystack.charAt(i + m);

                haystackHash = (haystackHash - firstCharVal * power) % modulus;
                if (haystackHash < 0) haystackHash += modulus; // Ensure positive
                haystackHash = (haystackHash * prime + nextCharVal) % modulus;
            }
        }
        // -------------------------------------------------------------

        return -1;
    }
}
