package com.framework.patterns.sliding_window;

import java.util.HashMap;
import java.util.Map;

/**
 * Pattern 11: Sliding Window - Character Frequency Matching
 */
public class SlidingWindowCharFrequency {

    /*
     * =================================================================================
     * CONDITIONS FOR APPLICABILITY
     * =================================================================================
     *
     * 1.  Input Data Structure: Two strings, a `text` and a `pattern`.
     *
     * 2.  Problem Goal: You need to find occurrences of the `pattern` string as a permutation
     *     within the `text` string. This means you are looking for an anagram of the `pattern`
     *     as a contiguous substring in the `text`.
     *     Example: Find all occurrences of anagrams of "abc" in "cbacadebabc" -> "cba", "bac".
     *
     * 3.  Logic: This is a combination of a fixed-size sliding window and a variable-size window's state management.
     *
     *     - First, create a frequency map of the characters in the `pattern` string.
     *     - Use a sliding window over the `text`. The window size will be the length of the `pattern`.
     *     - Maintain a state for the current window, typically another frequency map or a counter (`matched`).
     *     - When a character enters the window (`windowEnd`), update the window's state.
     *       - If the character is in the `pattern` map, decrement its count in the map.
     *       - If a character's count becomes zero, it means we have matched all required instances of that character, so we can increment our `matched` counter.
     *     - When the window size exceeds the pattern length, shrink the window from the left (`windowStart`).
     *       - When a character leaves the window, revert the changes made when it entered.
     *       - If the outgoing character's count was zero, it means we are about to lose a fully matched character, so decrement the `matched` counter.
     *       - Increment the character's count in the `pattern` map.
     *     - A match is found when `matched` equals the number of unique characters in the `pattern`.
     *
     * =================================================================================
     * GENERIC TEMPLATE (Find all Anagrams in a String)
     * =================================================================================
     */

    /**
     * Finds all starting indices of the pattern's anagrams in the text.
     *
     * @param text    The string to search in.
     * @param pattern The pattern string whose anagrams we are looking for.
     * @return A list of starting indices.
     */
    public java.util.List<Integer> findStringAnagrams(String text, String pattern) {
        java.util.List<Integer> resultIndices = new java.util.ArrayList<>();
        if (text == null || pattern == null || text.length() < pattern.length()) {
            return resultIndices;
        }

        // --- Core Pattern Logic: Step 1 - Create pattern frequency map ---
        Map<Character, Integer> charFrequencyMap = new HashMap<>();
        for (char c : pattern.toCharArray()) {
            charFrequencyMap.put(c, charFrequencyMap.getOrDefault(c, 0) + 1);
        }
        // ----------------------------------------------------------------

        int windowStart = 0;
        int matched = 0;

        // --- Core Pattern Logic: Step 2 - Slide the window over the text ---
        for (int windowEnd = 0; windowEnd < text.length(); windowEnd++) {
            char rightChar = text.charAt(windowEnd);

            // --- Problem-Specific Logic: Update state with the new character ---
            if (charFrequencyMap.containsKey(rightChar)) {
                charFrequencyMap.put(rightChar, charFrequencyMap.get(rightChar) - 1);
                if (charFrequencyMap.get(rightChar) == 0) {
                    matched++; // This character type is fully matched.
                }
            }
            // ------------------------------------------------------------------

            // --- Problem-Specific Logic: Check for a match ---
            // A match occurs when all characters in the pattern are matched.
            if (matched == charFrequencyMap.size()) {
                resultIndices.add(windowStart);
            }
            // ---------------------------------------------------

            // --- Core Pattern Logic: Step 3 - Shrink the window ---
            // If the window size is equal to the pattern length, we need to shrink it.
            if (windowEnd >= pattern.length() - 1) {
                char leftChar = text.charAt(windowStart++);
                
                // --- Problem-Specific Logic: Update state with the outgoing character ---
                if (charFrequencyMap.containsKey(leftChar)) {
                    if (charFrequencyMap.get(leftChar) == 0) {
                        matched--; // We are losing a fully matched character.
                    }
                    charFrequencyMap.put(leftChar, charFrequencyMap.get(leftChar) + 1);
                }
                // ----------------------------------------------------------------------
            }
            // -------------------------------------------------------
        }

        return resultIndices;
    }
}
