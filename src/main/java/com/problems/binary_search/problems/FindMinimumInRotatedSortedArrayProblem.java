package com.problems.binary_search.problems;

import com.framework.*;
import com.problems.binary_search.solutions.FindMinimumInRotatedSortedArraySolution;

import java.util.List;
import java.util.Map;

public class FindMinimumInRotatedSortedArrayProblem implements Problem<int[], Integer> {

    @Override
    public String getID() {
        return "0153";
    }

    @Override
    public Difficulty getDifficulty() {
        return Difficulty.MEDIUM;
    }

    @Override
    public String getName() {
        return "Find Minimum in Rotated Sorted Array";
    }

    @Override
    public String getDescription() {
        return """
                Suppose an array of length n sorted in ascending order is rotated between 1 and n times. For example, the array nums = [0,1,2,4,5,6,7] might become:
                - [4,5,6,7,0,1,2] if it was rotated 4 times.
                - [0,1,2,4,5,6,7] if it was rotated 7 times.

                Notice that rotating an array [a[0], a[1], ..., a[n-1]] 1 time results in the array [a[n-1], a[0], a[1], ..., a[n-2]].

                Given the sorted rotated array nums of unique elements, return the minimum element of this array.

                You must write an algorithm that runs in O(log n) time.""";
    }

    @Override
    public Source getSource() {
        return Source.LEETCODE;
    }

    @Override
    public String getExternalLink() {
        return "https://leetcode.com/problems/find-minimum-in-rotated-sorted-array/";
    }

    @Override
    public List<Topic> getTopics() {
        return List.of(Topic.BINARY_SEARCH, Topic.ARRAY);
    }

    @Override
    public Map<String, Solution<int[], Integer>> getSolutions() {
        return Map.of(
                "Modified Binary Search", new FindMinimumInRotatedSortedArraySolution()
        );
    }

    @Override
    public List<TestCase<int[], Integer>> getTestCases() {
        return List.of(
                new TestCase<>(new int[]{3, 4, 5, 1, 2}, 1),
                new TestCase<>(new int[]{4, 5, 6, 7, 0, 1, 2}, 0),
                new TestCase<>(new int[]{11, 13, 15, 17}, 11),
                new TestCase<>(new int[]{1}, 1),
                new TestCase<>(new int[]{2, 1}, 1)
        );
    }

    public static void main(String[] args) {
        Problem<int[], Integer> problem = new FindMinimumInRotatedSortedArrayProblem();
        TestRunner.run(problem);
    }
}
