package com.problems.binary_search.problems;

import com.framework.*;
import com.problems.binary_search.solutions.SearchInRotatedSortedArraySolution;

import java.util.List;
import java.util.Map;

public class SearchInRotatedSortedArrayProblem implements Problem<SearchInRotatedSortedArrayProblem.SearchInRotatedSortedArrayInput, Integer> {

    // A record to hold the input for this problem
    public record SearchInRotatedSortedArrayInput(int[] nums, int target) {}

    @Override
    public String getID() {
        return "0033";
    }

    @Override
    public Difficulty getDifficulty() {
        return Difficulty.MEDIUM;
    }

    @Override
    public String getName() {
        return "Search in Rotated Sorted Array";
    }

    @Override
    public String getDescription() {
        return """
                There is an integer array nums sorted in ascending order (with distinct values).

                Prior to being passed to your function, nums is possibly rotated at an unknown pivot index k (1 <= k < nums.length) such that the resulting array is [nums[k], nums[k+1], ..., nums[n-1], nums[0], nums[1], ..., nums[k-1]] (0-indexed). For example, [0,1,2,4,5,6,7] might be rotated at pivot index 3 and become [4,5,6,7,0,1,2].

                Given the array nums after the possible rotation and an integer target, return the index of target if it is in nums, or -1 if it is not in nums.

                You must write an algorithm with O(log n) runtime complexity.""";
    }

    @Override
    public Source getSource() {
        return Source.LEETCODE;
    }

    @Override
    public String getExternalLink() {
        return "https://leetcode.com/problems/search-in-rotated-sorted-array/";
    }

    @Override
    public List<Topic> getTopics() {
        return List.of(Topic.BINARY_SEARCH, Topic.ARRAY);
    }

    @Override
    public Map<String, Solution<SearchInRotatedSortedArrayInput, Integer>> getSolutions() {
        return Map.of(
                "Modified Binary Search", new SearchInRotatedSortedArraySolution()
        );
    }

    @Override
    public List<TestCase<SearchInRotatedSortedArrayInput, Integer>> getTestCases() {
        return List.of(
                new TestCase<>(new SearchInRotatedSortedArrayInput(new int[]{4, 5, 6, 7, 0, 1, 2}, 0), 4),
                new TestCase<>(new SearchInRotatedSortedArrayInput(new int[]{4, 5, 6, 7, 0, 1, 2}, 3), -1),
                new TestCase<>(new SearchInRotatedSortedArrayInput(new int[]{1}, 0), -1),
                new TestCase<>(new SearchInRotatedSortedArrayInput(new int[]{1, 3}, 3), 1),
                new TestCase<>(new SearchInRotatedSortedArrayInput(new int[]{3, 1}, 3), 0)
        );
    }

    public static void main(String[] args) {
        Problem<SearchInRotatedSortedArrayInput, Integer> problem = new SearchInRotatedSortedArrayProblem();
        TestRunner.run(problem);
    }
}
