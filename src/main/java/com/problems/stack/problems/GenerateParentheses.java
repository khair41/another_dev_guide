package com.problems.stack.problems;

import com.framework.*;
import com.problems.stack.solutions.GenerateParenthesesRecursiveSolution;

import java.util.List;
import java.util.Map;

public class GenerateParentheses implements Problem<Integer, List<String>> {

    @Override
    public String getID() {
        return "0022";
    }

    @Override
    public Difficulty getDifficulty() {
        return Difficulty.MEDIUM;
    }

    @Override
    public String getName() {
        return "Generate Parentheses";
    }

    @Override
    public String getDescription() {
        return """
                Given n pairs of parentheses, write a function to generate all combinations of well-formed parentheses.""";
    }

    @Override
    public Source getSource() {
        return Source.LEETCODE;
    }

    @Override
    public String getExternalLink() {
        return "https://leetcode.com/problems/generate-parentheses/";
    }

    @Override
    public List<Topic> getTopics() {
        return List.of(Topic.STACK, Topic.STRING, Topic.BACKTRACKING, Topic.DYNAMIC_PROGRAMMING);
    }

    @Override
    public Map<String, Solution<Integer, List<String>>> getSolutions() {
        return Map.of(
                "Backtracking/Recursion", new GenerateParenthesesRecursiveSolution()
        );
    }

    @Override
    public List<TestCase<Integer, List<String>>> getTestCases() {
        return List.of(
                new TestCase<>(3, List.of("((()))", "(()())", "(())()", "()(())", "()()()"), true),
                new TestCase<>(1, List.of("()"), true)
        );
    }

    public static void main(String[] args) {
        Problem<Integer, List<String>> problem = new GenerateParentheses();
        TestRunner.run(problem);
    }
}
