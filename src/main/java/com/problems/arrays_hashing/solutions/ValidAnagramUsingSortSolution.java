package com.problems.arrays_hashing.solutions;

import com.framework.Solution;
import com.problems.arrays_hashing.problems.ValidAnagram.ValidAnagramInput;

import java.util.Arrays;

public class ValidAnagramUsingSortSolution implements Solution<ValidAnagramInput, Boolean> {
    @Override
    public Boolean execute(ValidAnagramInput input) {
        String s = input.s();
        String t = input.t();

        if (s.length() != t.length()) {
            return false;
        }

        char[] sChars = s.toCharArray();
        char[] tChars = t.toCharArray();

        Arrays.sort(sChars);
        Arrays.sort(tChars);

        return Arrays.equals(sChars, tChars);
    }

    @Override
    public String getTimeComplexity() {
        return "O(N log N) where N is the length of the strings";
    }

    @Override
    public String getSpaceComplexity() {
        return "O(1) since we are sorting in place";
    }
}
