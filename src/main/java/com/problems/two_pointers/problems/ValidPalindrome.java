package com.problems.two_pointers.problems;

import com.framework.*;
import com.problems.two_pointers.solutions.ValidPalindromeReverseStringSolution;
import com.problems.two_pointers.solutions.ValidPalindromeTwoPointersSolution;

import java.util.List;
import java.util.Map;

public class ValidPalindrome implements Problem<String, Boolean> {
    @Override
    public String getID() {
        return "0125";
    }

    @Override
    public Difficulty getDifficulty() {
        return Difficulty.EASY;
    }

    @Override
    public String getName() {
        return "Valid Palindrome";
    }

    @Override
    public String getDescription() {
        return """
                A phrase is a palindrome if, after converting all uppercase letters into lowercase letters and removing all non-alphanumeric characters, it reads the same forward and backward." +
                "Given a string s, return true if it is a palindrome, or false otherwise.""";
    }

    @Override
    public Source getSource() {
        return Source.LEETCODE;
    }

    @Override
    public String getExternalLink() {
        return "https://leetcode.com/problems/valid-palindrome/";
    }

    @Override
    public List<Topic> getTopics() {
        return List.of(Topic.TWO_POINTERS, Topic.STRING);
    }

    @Override
    public Map<String, Solution<String, Boolean>> getSolutions() {
        return Map.of(
                "Reverse String", new ValidPalindromeReverseStringSolution(),
                "Two Pointers", new ValidPalindromeTwoPointersSolution()
        );
    }

    @Override
    public List<TestCase<String, Boolean>> getTestCases() {
        return List.of(
                new TestCase<>("A man, a plan, a canal: Panama", true),
                new TestCase<>("race a car", false),
                new TestCase<>(" ", true) // An empty string after filtering
        );
    }

    public static void main(String[] args) {
        Problem<String, Boolean> problem = new ValidPalindrome();
        TestRunner.run(problem);
    }
}
