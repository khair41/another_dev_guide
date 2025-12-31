package com.framework.patterns.string_manipulation;

/**
 * Pattern 87: String - Anagram Check (Frequency Count/Sort)
 */
public class StringAnagramCheck {

    /*
     * =================================================================================
     * CONDITIONS FOR APPLICABILITY
     * =================================================================================
     *
     * 1.  Input Data Structure: Two strings.
     *
     * 2.  Problem Goal: You need to determine if one string is a permutation of the other, i.e.,
     *     if they are anagrams of each other. Anagrams are words or phrases formed by rearranging
     *     the letters of a different word or phrase, typically using all the original letters exactly once.
     *
     * 3.  Logic (Frequency Count Method):
     *     -   This is the most efficient approach, with O(n) time complexity, where n is the length of the strings.
     *     -   **Initial Check**: If the two strings do not have the same length, they cannot be anagrams.
     *     -   **Frequency Map**: Create an array (e.g., of size 26 for lowercase English letters or 256 for ASCII)
     *       to act as a frequency map.
     *     -   **First Pass**: Iterate through the first string and increment the count for each character in the map.
     *     -   **Second Pass**: Iterate through the second string and decrement the count for each character.
     *       During this pass, if a character's count in the map ever drops below zero, it means the second
     *       string has more of that character than the first, so they are not anagrams.
     *     -   **Final Check**: If the second pass completes without any count going below zero, the strings are anagrams.
     *       (There is no need for a third pass to check if all counts are zero, because the initial length check
     *       ensures this implicitly).
     *
     * =================================================================================
     * GENERIC TEMPLATE (Valid Anagram using Frequency Count)
     * =================================================================================
     */

    /**
     * Checks if two strings are anagrams of each other.
     *
     * @param s The first string.
     * @param t The second string.
     * @return True if t is an anagram of s, and false otherwise.
     */
    public boolean isAnagram(String s, String t) {
        // --- Initial Check ---
        if (s.length() != t.length()) {
            return false;
        }

        // --- Core Pattern Logic: Frequency Count ---
        int[] charCounts = new int[26]; // Assuming lowercase English letters

        // First Pass: Increment counts for the first string.
        for (char c : s.toCharArray()) {
            charCounts[c - 'a']++;
        }

        // Second Pass: Decrement counts for the second string.
        for (char c : t.toCharArray()) {
            charCounts[c - 'a']--;
            // If a count goes below zero, it means t has a character that s doesn't
            // have enough of.
            if (charCounts[c - 'a'] < 0) {
                return false;
            }
        }
        // -------------------------------------------

        // If the loop completes, the strings are anagrams.
        return true;
    }
}
