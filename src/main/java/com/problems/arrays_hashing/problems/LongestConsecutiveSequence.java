package com.problems.arrays_hashing.problems;

import com.framework.*;
import com.problems.arrays_hashing.solutions.LongestConsecutiveSequenceBFSolution;
import com.problems.arrays_hashing.solutions.LongestConsecutiveSequenceSortingSolution;
import com.problems.arrays_hashing.solutions.LongestConsecutiveSequenceUsingSetSolution;

import java.util.List;
import java.util.Map;

public class LongestConsecutiveSequence implements Problem<int[], Integer> {
    @Override
    public String getID() {
        return "0128";
    }

    @Override
    public Difficulty getDifficulty() {
        return Difficulty.MEDIUM;
    }

    @Override
    public String getName() {
        return "Longest Consecutive Sequence";
    }

    @Override
    public String getDescription() {
        return "Given an unsorted array of integers nums, return the length of the longest consecutive elements sequence.\n" +
                "You must write an algorithm that runs in O(n) time.";
    }

    @Override
    public Source getSource() {
        return Source.LEETCODE;
    }

    @Override
    public String getExternalLink() {
        return "https://leetcode.com/problems/longest-consecutive-sequence/";
    }

    @Override
    public List<Topic> getTopics() {
        return List.of(Topic.ARRAY, Topic.HASHING, Topic.UNION_FIND);
    }

    @Override
    public Map<String, Solution<int[], Integer>> getSolutions() {
        return Map.of(
                "Brute Force", new LongestConsecutiveSequenceBFSolution(),
                "Sorting", new LongestConsecutiveSequenceSortingSolution(),
                "Using Set", new LongestConsecutiveSequenceUsingSetSolution()
        );
    }

    @Override
    public List<TestCase<int[], Integer>> getTestCases() {
        return List.of(
                // Provided test cases
                new TestCase<>(new int[]{100, 4, 200, 1, 3, 2}, 4),
                new TestCase<>(new int[]{0, 3, 7, 2, 5, 8, 4, 6, 0, 1}, 9),

                // Edge cases
                new TestCase<>(new int[]{}, 0), // Empty array
                new TestCase<>(new int[]{10}, 1), // Single element

                // Additional cases
                new TestCase<>(new int[]{1, 2, 0, 1}, 3), // With duplicates
                new TestCase<>(new int[]{-1, 0, 1, 2}, 4), // With negative numbers
                new TestCase<>(new int[]{1, 3, 5, 7}, 1), // No consecutive numbers
                new TestCase<>(new int[]{9, 1, 4, 7, 3, -1, 0, 5, 8, -2, 6}, 7) // Complex unsorted case
        );
    }

    public static void main(String[] args) {
        Problem<int[], Integer> problem = new LongestConsecutiveSequence();
        TestRunner.run(problem);
    }
}
