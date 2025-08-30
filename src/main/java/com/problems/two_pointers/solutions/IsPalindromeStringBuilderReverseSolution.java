package com.problems.two_pointers.solutions;

import com.framework.Solution;

public class IsPalindromeStringBuilderReverseSolution implements Solution<String, Boolean> {
    @Override
    public Boolean execute(String s) {
        if (s == null) return false;
        s = s.replaceAll("[^a-zA-Z0-9]", "").toLowerCase();
        return new StringBuilder(s).reverse().toString().equals(s);
    }

    @Override
    public String getTimeComplexity() {
        return "O(N) where N is the length of the string";
    }

    @Override
    public String getSpaceComplexity() {
        return "O(N) where N is the length of the string";
    }
}
