package com.problems.arrays_hashing.problems;

import com.framework.*;
import com.problems.arrays_hashing.solutions.ContainsDuplicateUsingSetSolution;
import com.problems.arrays_hashing.solutions.ContainsDuplicateUsingSortSolution;

import java.util.List;
import java.util.Map;

public class ContainsDuplicate implements Problem<int[], Boolean> {

    @Override
    public String getID() {
        return "0217";
    }

    @Override
    public Difficulty getDifficulty() {
        return Difficulty.EASY;
    }

    @Override
    public String getName() {
        return "Contains Duplicate";
    }

    @Override
    public String getDescription() {
        return """
                Given an integer array nums, return true if any value appears at least twice in the array, and return false if every element is distinct.""";

    }

    @Override
    public Source getSource() {
        return Source.LEETCODE;
    }

    @Override
    public String getExternalLink() {
        return "https://leetcode.com/problems/contains-duplicate/";
    }

    @Override
    public List<Topic> getTopics() {
        return List.of(Topic.ARRAY, Topic.HASHING, Topic.SORTING);
    }

    @Override
    public Map<String, Solution<int[], Boolean>> getSolutions() {
        return Map.of(
                "Using Set", new ContainsDuplicateUsingSetSolution(),
                "Using Sort", new ContainsDuplicateUsingSortSolution()
        );
    }

    @Override
    public List<TestCase<int[], Boolean>> getTestCases() {
        return List.of(
                // Provided test cases
                new TestCase<>(new int[]{1, 2, 3, 1}, true),
                new TestCase<>(new int[]{1, 2, 3, 4}, false),
                new TestCase<>(new int[]{1, 1, 1, 3, 3, 4, 3, 2, 4, 2}, true),

                // Edge cases
                new TestCase<>(new int[]{}, false), // Empty array
                new TestCase<>(new int[]{1}, false), // Single element

                // Additional cases
                new TestCase<>(new int[]{5, 5, 5, 5}, true), // All elements are the same
                new TestCase<>(new int[]{-1, -2, -3, -1}, true), // With negative numbers
                new TestCase<>(new int[]{0, 1, 2, 0}, true), // With zero
                new TestCase<>(new int[]{10, 20, 30, 40, 50, 60, 70, 80, 90, 100}, false), // No duplicates
                new TestCase<>(new int[]{4, 5, 6, 7, 8, 9, 10, 4}, true) // Duplicates at the ends
        );
    }

    public static void main(String[] args) {
        Problem<int[], Boolean> problem = new ContainsDuplicate();
        TestRunner.run(problem);
    }
}
