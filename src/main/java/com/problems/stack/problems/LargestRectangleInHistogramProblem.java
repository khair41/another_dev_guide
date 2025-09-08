package com.problems.stack.problems;

import com.framework.*;
import com.problems.stack.solutions.LargestRectangleInHistogramPrefixSuffixSolution;
import com.problems.stack.solutions.LargestRectangleInHistogramStackSolution;

import java.util.List;
import java.util.Map;

public class LargestRectangleInHistogramProblem implements Problem<int[], Integer> {

    @Override
    public String getID() {
        return "0084";
    }

    @Override
    public Difficulty getDifficulty() {
        return Difficulty.HARD;
    }

    @Override
    public String getName() {
        return "Largest Rectangle in Histogram";
    }

    @Override
    public String getDescription() {
        return """
                Given an array of integers heights representing the histogram's bar height where the width of each bar is 1, return the area of the largest rectangle in the histogram.""";
    }

    @Override
    public Source getSource() {
        return Source.LEETCODE;
    }

    @Override
    public String getExternalLink() {
        return "https://leetcode.com/problems/largest-rectangle-in-histogram/";
    }

    @Override
    public List<Topic> getTopics() {
        return List.of(Topic.STACK, Topic.ARRAY, Topic.MONOTONIC_STACK);
    }

    @Override
    public Map<String, Solution<int[], Integer>> getSolutions() {
        return Map.of(
//                "Prefix/Suffix (DP-like)", new LargestRectangleInHistogramPrefixSuffixSolution(), // TODO
                "Monotonic Stack", new LargestRectangleInHistogramStackSolution()
        );
    }

    @Override
    public List<TestCase<int[], Integer>> getTestCases() {
        return List.of(
                new TestCase<>(new int[]{2, 1, 5, 6, 2, 3}, 10),
                new TestCase<>(new int[]{2, 4}, 4),
                new TestCase<>(new int[]{1}, 1),
                new TestCase<>(new int[]{0, 9}, 9)
        );
    }

    public static void main(String[] args) {
        Problem<int[], Integer> problem = new LargestRectangleInHistogramProblem();
        TestRunner.run(problem);
    }
}
