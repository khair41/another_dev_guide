package com.problems.stack.problems;

import com.framework.*;
import com.problems.stack.solutions.ValidParenthesesStackSolution;

import java.util.List;
import java.util.Map;

public class ValidParentheses implements Problem<String, Boolean> {

    @Override
    public String getID() {
        return "0020";
    }

    @Override
    public Difficulty getDifficulty() {
        return Difficulty.EASY;
    }

    @Override
    public String getName() {
        return "Valid Parentheses";
    }

    @Override
    public String getDescription() {
        return """
                Given a string s containing just the characters '(', ')', '{', '}', '[' and ']', determine if the input string is valid.

                An input string is valid if:
                1. Open brackets must be closed by the same type of brackets.
                2. Open brackets must be closed in the correct order.
                3. Every close bracket has a corresponding open bracket of the same type.""";
    }

    @Override
    public Source getSource() {
        return Source.LEETCODE;
    }

    @Override
    public String getExternalLink() {
        return "https://leetcode.com/problems/valid-parentheses/";
    }

    @Override
    public List<Topic> getTopics() {
        return List.of(Topic.STACK, Topic.STRING);
    }

    @Override
    public Map<String, Solution<String, Boolean>> getSolutions() {
        return Map.of(
                "Stack with HashMap", new ValidParenthesesStackSolution()
        );
    }

    @Override
    public List<TestCase<String, Boolean>> getTestCases() {
        return List.of(
                new TestCase<>("()", true),
                new TestCase<>("()[]{}", true),
                new TestCase<>("(]", false),
                new TestCase<>("([)]", false),
                new TestCase<>("{[]}", true),
                new TestCase<>("", true),
                new TestCase<>("[", false)
        );
    }

    public static void main(String[] args) {
        Problem<String, Boolean> problem = new ValidParentheses();
        TestRunner.run(problem);
    }
}
