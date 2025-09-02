package com.problems.two_pointers.problems;

import com.framework.*;
import com.problems.two_pointers.solutions.TwoSumIIBinarySearchSolution;
import com.problems.two_pointers.solutions.TwoSumIITwoPointersSolution;

import java.util.List;
import java.util.Map;

public class TwoSumII implements Problem<TwoSumII.TwoSumIIInput, int[]> {

    public record TwoSumIIInput(int[] numbers, int target) {}

    @Override
    public String getID() {
        return "0167";
    }

    @Override
    public Difficulty getDifficulty() {
        return Difficulty.MEDIUM;
    }

    @Override
    public String getName() {
        return "Two Sum II - Input Array Is Sorted";
    }

    @Override
    public String getDescription() {
        return """
                Given a 1-indexed array of integers numbers that is already sorted in non-decreasing order, find two numbers such that they add up to a specific target number. Let these two numbers be numbers[index1] and numbers[index2] where 1 <= index1 < index2 <= numbers.length.

                Return the indices of the two numbers, index1 and index2, added by one as an integer array [index1, index2] of length 2.

                The tests are generated such that there is exactly one solution. You may not use the same element twice.

                Your solution must use only constant extra space.""";
    }

    @Override
    public Source getSource() {
        return Source.LEETCODE;
    }

    @Override
    public String getExternalLink() {
        return "https://leetcode.com/problems/two-sum-ii-input-array-is-sorted/";
    }

    @Override
    public List<Topic> getTopics() {
        return List.of(Topic.TWO_POINTERS, Topic.ARRAY, Topic.BINARY_SEARCH);
    }

    @Override
    public Map<String, Solution<TwoSumIIInput, int[]>> getSolutions() {
        return Map.of(
                "Two Pointers", new TwoSumIITwoPointersSolution(),
                "Binary Search", new TwoSumIIBinarySearchSolution()
        );
    }

    @Override
    public List<TestCase<TwoSumIIInput, int[]>> getTestCases() {
        return List.of(
                new TestCase<>(new TwoSumIIInput(new int[]{2, 7, 11, 15}, 9), new int[]{1, 2}),
                new TestCase<>(new TwoSumIIInput(new int[]{2, 3, 4}, 6), new int[]{1, 3}),
                new TestCase<>(new TwoSumIIInput(new int[]{-1, 0}, -1), new int[]{1, 2})
        );
    }

    public static void main(String[] args) {
        Problem<TwoSumIIInput, int[]> problem = new TwoSumII();
        TestRunner.run(problem);
    }
}
