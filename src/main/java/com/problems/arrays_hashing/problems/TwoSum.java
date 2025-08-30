package com.problems.arrays_hashing.problems;

import com.framework.*;
import com.problems.arrays_hashing.solutions.TwoSumUsingMapSolution;

import java.util.List;
import java.util.Map;

public class TwoSum implements Problem<TwoSum.TwoSumInput, int[]> {

    public record TwoSumInput(int[] nums, int target) {}

    @Override
    public String getID() {
        return "0001";
    }

    @Override
    public Difficulty getDifficulty() {
        return Difficulty.EASY;
    }

    @Override
    public String getName() {
        return "Two Sum";
    }

    @Override
    public String getDescription() {
        return """
                Given an array of integers nums and an integer target, return indices of the two numbers such that they add up to target.
                
                "You may assume that each input would have exactly one solution, and you may not use the same element twice.
                
                "You can return the answer in any order.""" ;
    }

    @Override
    public Source getSource() {
        return Source.LEETCODE;
    }

    @Override
    public String getExternalLink() {
        return "https://leetcode.com/problems/two-sum/";
    }

    @Override
    public List<Topic> getTopics() {
        return List.of(Topic.ARRAY, Topic.HASHING);
    }

    @Override
    public Map<String, Solution<TwoSumInput, int[]>> getSolutions() {
        return Map.of(
                "Using Map", new TwoSumUsingMapSolution()
        );
    }

    @Override
    public List<TestCase<TwoSumInput, int[]>> getTestCases() {

        return List.of(
                // Provided test cases
                new TestCase<>(new TwoSumInput(new int[] {2,7,11,15}, 9),
                        new int[] {0,1}, true),
                new TestCase<>(new TwoSumInput(new int[] {3,2,4}, 6),
                        new int[] {1,2}, true),
                new TestCase<>(new TwoSumInput(new int[] {3,3}, 6),
                        new int[] {0,1}, true),

                // Additional cases
                new TestCase<>(new TwoSumInput(new int[] {-1, 0, 1, 2}, 0),
                        new int[] {0,2}, true), // With negative numbers and zero target
                new TestCase<>(new TwoSumInput(new int[] {5, 2, 11, 7}, 9),
                        new int[] {1,3}, true), // Pair at different positions
                new TestCase<>(new TwoSumInput(new int[] {10, -5, 8, -2}, 8),
                        new int[] {0,3}, true), // Positive and negative numbers
                new TestCase<>(new TwoSumInput(new int[] {0, 4, 3, 0}, 0),
                        new int[] {0,3}, true) // Duplicates of zero
        );
    }

    public static void main(String[] args) {
        Problem<TwoSumInput, int[]> problem = new TwoSum();
        TestRunner.run(problem);
    }
}
