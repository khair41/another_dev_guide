package com.problems.two_pointers.problems;

import com.framework.*;
import com.problems.two_pointers.solutions.TrappingRainWaterBruteForceSolution;
import com.problems.two_pointers.solutions.TrappingRainWaterPrefixSuffixSolution;
import com.problems.two_pointers.solutions.TrappingRainWaterStackSolution;
import com.problems.two_pointers.solutions.TrappingRainWaterTwoPointersSolution;

import java.util.List;
import java.util.Map;

public class TrappingRainWater implements Problem<int[], Integer> {

    @Override
    public String getID() {
        return "0042";
    }

    @Override
    public Difficulty getDifficulty() {
        return Difficulty.HARD;
    }

    @Override
    public String getName() {
        return "Trapping Rain Water";
    }

    @Override
    public String getDescription() {
        return """
                Given n non-negative integers representing an elevation map where the width of each bar is 1, compute how much water it can trap after raining.""";
    }

    @Override
    public Source getSource() {
        return Source.LEETCODE;
    }

    @Override
    public String getExternalLink() {
        return "https://leetcode.com/problems/trapping-rain-water/";
    }

    @Override
    public List<Topic> getTopics() {
        return List.of(Topic.TWO_POINTERS, Topic.ARRAY, Topic.DYNAMIC_PROGRAMMING, Topic.STACK);
    }

    @Override
    public Map<String, Solution<int[], Integer>> getSolutions() {
        return Map.of(
                "Brute Force", new TrappingRainWaterBruteForceSolution(),
                "Prefix/Suffix (DP)", new TrappingRainWaterPrefixSuffixSolution(),
                "Stack", new TrappingRainWaterStackSolution(),
                "Two Pointers", new TrappingRainWaterTwoPointersSolution()
        );
    }

    @Override
    public List<TestCase<int[], Integer>> getTestCases() {
        return List.of(
                // Provided test cases
                new TestCase<>(new int[]{0, 1, 0, 2, 1, 0, 1, 3, 2, 1, 2, 1}, 6),
                new TestCase<>(new int[]{4, 2, 0, 3, 2, 5}, 9),

                // Edge cases
                new TestCase<>(new int[]{}, 0), // Empty array
                new TestCase<>(new int[]{5}, 0), // Single bar
                new TestCase<>(new int[]{5, 8}, 0), // Two bars
                new TestCase<>(new int[]{1, 2, 3, 4, 5}, 0), // Strictly increasing
                new TestCase<>(new int[]{5, 4, 3, 2, 1}, 0), // Strictly decreasing
                new TestCase<>(new int[]{3, 3, 3, 3}, 0), // All same height

                // More complex cases
                new TestCase<>(new int[]{3, 0, 1, 0, 3}, 8), // 'W' shape
                new TestCase<>(new int[]{5, 0, 10, 0, 5}, 10), // High middle section
                new TestCase<>(new int[]{5, 4, 1, 2, 6}, 8), // Descending then ascending
                new TestCase<>(new int[]{0, 0, 5, 0, 0}, 0), // Single peak

                // Final exhaustive cases
                new TestCase<>(new int[]{2, 1, 2, 1, 2, 1, 2}, 3), // Jagged terrain
                new TestCase<>(new int[]{5, 2, 2, 2, 2, 2, 5}, 15), // Wide valley
                new TestCase<>(new int[]{10, 1, 2, 3, 4, 5, 6, 7, 8, 9}, 36), // Corrected Sloped interior
                new TestCase<>(new int[]{4, 1, 3, 1, 5}, 7), // Double dip

                // Plateau and leaky container cases
                new TestCase<>(new int[]{4, 2, 2, 4}, 4), // Plateau in the middle
                new TestCase<>(new int[]{3, 3, 1, 3, 3}, 2), // Leaky container with plateau walls
                new TestCase<>(new int[]{5, 3, 3, 5, 1, 5, 3, 3, 5}, 12), // Multiple plateaus and dips
                new TestCase<>(new int[]{1, 2, 3, 4, 5, 4, 3, 2, 1}, 0) // Pyramid
        );
    }

    public static void main(String[] args) {
        Problem<int[], Integer> problem = new TrappingRainWater();
        TestRunner.run(problem);
    }
}
