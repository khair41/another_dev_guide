package com.problems.binary_search.problems;

import com.framework.*;
import com.problems.binary_search.solutions.MedianOfTwoSortedArraysSolution;

import java.util.List;
import java.util.Map;

public class MedianOfTwoSortedArraysProblem implements Problem<MedianOfTwoSortedArraysProblem.MedianOfTwoSortedArraysInput, Double> {

    // A record to hold the input for this problem
    public record MedianOfTwoSortedArraysInput(int[] nums1, int[] nums2) {}

    @Override
    public String getID() {
        return "0004";
    }

    @Override
    public Difficulty getDifficulty() {
        return Difficulty.HARD;
    }

    @Override
    public String getName() {
        return "Median of Two Sorted Arrays";
    }

    @Override
    public String getDescription() {
        return """
                Given two sorted arrays nums1 and nums2 of size m and n respectively, return the median of the two sorted arrays.

                The overall run time complexity should be O(log (m+n)).""";
    }

    @Override
    public Source getSource() {
        return Source.LEETCODE;
    }

    @Override
    public String getExternalLink() {
        return "https://leetcode.com/problems/median-of-two-sorted-arrays/";
    }

    @Override
    public List<Topic> getTopics() {
        return List.of(Topic.BINARY_SEARCH, Topic.ARRAY, Topic.DIVIDE_AND_CONQUER);
    }

    @Override
    public Map<String, Solution<MedianOfTwoSortedArraysInput, Double>> getSolutions() {
        return Map.of(
                "Binary Search on Partition", new MedianOfTwoSortedArraysSolution()
        );
    }

    @Override
    public List<TestCase<MedianOfTwoSortedArraysInput, Double>> getTestCases() {
        return List.of(
                new TestCase<>(new MedianOfTwoSortedArraysInput(new int[]{1, 3}, new int[]{2}), 2.0),
                new TestCase<>(new MedianOfTwoSortedArraysInput(new int[]{1, 2}, new int[]{3, 4}), 2.5),
                new TestCase<>(new MedianOfTwoSortedArraysInput(new int[]{0, 0}, new int[]{0, 0}), 0.0),
                new TestCase<>(new MedianOfTwoSortedArraysInput(new int[]{}, new int[]{1}), 1.0),
                new TestCase<>(new MedianOfTwoSortedArraysInput(new int[]{2}, new int[]{}), 2.0)
        );
    }

    public static void main(String[] args) {
        Problem<MedianOfTwoSortedArraysInput, Double> problem = new MedianOfTwoSortedArraysProblem();
        TestRunner.run(problem);
    }
}
