package com.problems.two_pointers.solutions;

import com.framework.Solution;

public class IsPalindromeTwoPointersSolution implements Solution<String, Boolean> {
    @Override
    public Boolean execute(String s) {
        if (s == null) return false;

        int left = 0;
        int right = s.length() - 1;

        while (left < right) {
            while (left < right && !Character.isLetterOrDigit(s.charAt(left))) {
                left++;
            }
            while (left < right && !Character.isLetterOrDigit(s.charAt(right))) {
                right--;
            }
            if (Character.toLowerCase(s.charAt(left)) != Character.toLowerCase(s.charAt(right))) {
                return false;
            }
            left++;
            right--;
        }
        return true;
    }

    @Override
    public String getTimeComplexity() {
        return "O(N) where N is the length of the string";
    }

    @Override
    public String getSpaceComplexity() {
        return "O(1) since we are using two pointers";
    }
}
