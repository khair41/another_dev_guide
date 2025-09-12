package com.problems.binary_search.problems;

import com.framework.*;
import com.problems.binary_search.solutions.BinarySearchSolution;

import java.util.List;
import java.util.Map;

public class BinarySearchProblem implements Problem<BinarySearchProblem.BinarySearchInput, Integer> {

    // A record to hold the input for this problem
    public record BinarySearchInput(int[] nums, int target) {}

    @Override
    public String getID() {
        return "0704";
    }

    @Override
    public Difficulty getDifficulty() {
        return Difficulty.EASY;
    }

    @Override
    public String getName() {
        return "Binary Search";
    }

    @Override
    public String getDescription() {
        return """
                Given an array of integers nums which is sorted in ascending order, and an integer target, write a function to search target in nums. If target exists, then return its index. Otherwise, return -1.

                You must write an algorithm with O(log n) runtime complexity.""";
    }

    @Override
    public Source getSource() {
        return Source.LEETCODE;
    }

    @Override
    public String getExternalLink() {
        return "https://leetcode.com/problems/binary-search/";
    }

    @Override
    public List<Topic> getTopics() {
        return List.of(Topic.BINARY_SEARCH, Topic.ARRAY);
    }

    @Override
    public Map<String, Solution<BinarySearchInput, Integer>> getSolutions() {
        return Map.of(
                "Iterative", new BinarySearchSolution()
        );
    }

    @Override
    public List<TestCase<BinarySearchInput, Integer>> getTestCases() {
        return List.of(
                new TestCase<>(new BinarySearchInput(new int[]{-1, 0, 3, 5, 9, 12}, 9), 4),
                new TestCase<>(new BinarySearchInput(new int[]{-1, 0, 3, 5, 9, 12}, 2), -1),
                new TestCase<>(new BinarySearchInput(new int[]{5}, 5), 0),
                new TestCase<>(new BinarySearchInput(new int[]{5}, -5), -1)
        );
    }

    public static void main(String[] args) {
        Problem<BinarySearchInput, Integer> problem = new BinarySearchProblem();
        TestRunner.run(problem);
    }
}
