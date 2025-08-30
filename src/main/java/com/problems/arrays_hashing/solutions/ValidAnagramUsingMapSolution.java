package com.problems.arrays_hashing.solutions;

import com.framework.Solution;
import com.problems.arrays_hashing.problems.ValidAnagram.ValidAnagramInput;

public class ValidAnagramUsingMapSolution implements Solution<ValidAnagramInput, Boolean> {
    @Override
    public Boolean execute(ValidAnagramInput input) {
        String s = input.s();
        String t = input.t();

        if (s.length() != t.length()) {
            return false;
        }

        int[] charCounts = new int[26];
        for (char c : s.toCharArray()) {
            charCounts[c - 'a']++;
        }

        for (char c : t.toCharArray()) {
            charCounts[c - 'a']--;
            if (charCounts[c - 'a'] < 0) {
                return false;
            }
        }

        return true;
    }

    @Override
    public String getTimeComplexity() {
        return "O(N) where N is the length of the strings";
    }

    @Override
    public String getSpaceComplexity() {
        return "O(1) since we are using a fixed-size array for character counts";
    }
}
