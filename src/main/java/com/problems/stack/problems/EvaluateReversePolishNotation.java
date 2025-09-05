package com.problems.stack.problems;

import com.framework.*;
import com.problems.stack.solutions.EvaluateReversePolishNotationLinkedListSolution;
import com.problems.stack.solutions.EvaluateReversePolishNotationRecursiveSolution;
import com.problems.stack.solutions.EvaluateReversePolishNotationStackSolution;

import java.util.List;
import java.util.Map;

public class EvaluateReversePolishNotation implements Problem<String[], Integer> {

    @Override
    public String getID() {
        return "0150";
    }

    @Override
    public Difficulty getDifficulty() {
        return Difficulty.MEDIUM;
    }

    @Override
    public String getName() {
        return "Evaluate Reverse Polish Notation";
    }

    @Override
    public String getDescription() {
        return """
                Evaluate the value of an arithmetic expression in Reverse Polish Notation.

                Valid operators are +, -, *, /. Each operand may be an integer or another expression.

                Note that division between two integers should truncate toward zero.""";
    }

    @Override
    public Source getSource() {
        return Source.LEETCODE;
    }

    @Override
    public String getExternalLink() {
        return "https://leetcode.com/problems/evaluate-reverse-polish-notation/";
    }

    @Override
    public List<Topic> getTopics() {
        return List.of(Topic.STACK, Topic.ARRAY, Topic.MATH);
    }

    @Override
    public Map<String, Solution<String[], Integer>> getSolutions() {
        return Map.of(
                "Stack", new EvaluateReversePolishNotationStackSolution(),
                "Recursion", new EvaluateReversePolishNotationRecursiveSolution(),
                "Doubly Linked List as Stack", new EvaluateReversePolishNotationLinkedListSolution()
        );
    }

    @Override
    public List<TestCase<String[], Integer>> getTestCases() {
        return List.of(
                new TestCase<>(new String[]{"2", "1", "+", "3", "*"}, 9),
                new TestCase<>(new String[]{"4", "13", "5", "/", "+"}, 6),
                new TestCase<>(new String[]{"10", "6", "9", "3", "+", "-11", "*", "/", "*", "17", "+", "5", "+"}, 22)
        );
    }

    public static void main(String[] args) {
        Problem<String[], Integer> problem = new EvaluateReversePolishNotation();
        TestRunner.run(problem);
    }
}
