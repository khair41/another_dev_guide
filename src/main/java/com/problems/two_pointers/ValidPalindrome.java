package com.problems.two_pointers;

import com.framework.*;
import com.problems.two_pointers.solutions.IsPalindromeStringBuilderReverseSolution;
import com.problems.two_pointers.solutions.IsPalindromeTwoPointersSolution;

import java.util.List;
import java.util.Map;

public class ValidPalindrome implements Problem<String, Boolean> {

    @Override
    public String getID() {
        return "0125";
    }

    @Override
    public String getName() {
        return "Valid Palindrome";
    }

    @Override
    public String getDescription() {
        return "A phrase is a palindrome if, after converting all uppercase letters into lowercase letters and removing all non-alphanumeric characters," +
                " it reads the same forward and backward. Alphanumeric characters include letters and numbers.\n" +
                "Given a string s, return true if it is a palindrome, or false otherwise.";
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
        return List.of(Topic.STRING, Topic.TWO_POINTERS, Topic.HASHING);
    }

    @Override
    public Difficulty getDifficulty() {
        return Difficulty.EASY;
    }

    @Override
    public Map<String, Solution<String, Boolean>> getSolutions() {
        return Map.of(
            "StringBuilder Reverse", new IsPalindromeStringBuilderReverseSolution(),
            "Two Pointers", new IsPalindromeTwoPointersSolution()
        );
    }

    @Override
    public List<TestCase<String, Boolean>> getTestCases() {
        return List.of(
            new TestCase<>("racecar", true),
            new TestCase<>("hello", false),
            new TestCase<>("", true),
            new TestCase<>("a", true),
            new TestCase<>("madam", true),
            new TestCase<>("step on no pets", true),
            new TestCase<>("A man, a plan, a canal: Panama", true)
        );
    }

    public static void main(String[] args) {
        Problem<String, Boolean> problem = new ValidPalindrome();
        TestRunner.run(problem);
    }
}
