package com.problems.arrays_hashing.problems;

import com.framework.*;
import com.problems.arrays_hashing.solutions.ProductOfArrayExceptSelfBruteForce;
import com.problems.arrays_hashing.solutions.ProductOfArrayExceptSelfPrefixSuffixOptimalSolution;
import com.problems.arrays_hashing.solutions.ProductOfArrayExceptSelfPrefixSuffixSolution;

import java.util.List;
import java.util.Map;

public class ProductOfArrayExceptSelf implements Problem<int[], int[]> {
    @Override
    public String getID() {
        return "0238";
    }

    @Override
    public Difficulty getDifficulty() {
        return Difficulty.MEDIUM;
    }

    @Override
    public String getName() {
        return "Product of Array Except Self";
    }

    @Override
    public String getDescription() {
        return """
                Given an integer array nums, return an array answer such that answer[i] is equal to the product of all the elements of nums except nums[i].
                
                The product of any prefix or suffix of nums is guaranteed to fit in a 32-bit integer.
                
                You must write an algorithm that runs in O(n) time and without using the division operation.""" ;
    }

    @Override
    public Source getSource() {
        return Source.LEETCODE;
    }

    @Override
    public String getExternalLink() {
        return "https://leetcode.com/problems/product-of-array-except-self/";
    }

    @Override
    public List<Topic> getTopics() {
        return List.of(Topic.ARRAY, Topic.PREFIX_SUM);
    }

    @Override
    public Map<String, Solution<int[], int[]>> getSolutions() {
        return Map.of(
                "Brute Force", new ProductOfArrayExceptSelfBruteForce(),
                "PrefixSuffix Solution", new ProductOfArrayExceptSelfPrefixSuffixSolution(),
                "PrefixSuffix Optimal", new ProductOfArrayExceptSelfPrefixSuffixOptimalSolution()
        );
    }

    @Override
    public List<TestCase<int[], int[]>> getTestCases() {
        return List.of(
                // Provided test cases
                new TestCase<>(new int[]{1, 2, 3, 4}, new int[]{24, 12, 8, 6}, true),
                new TestCase<>(new int[]{-1, 1, 0, -3, 3}, new int[]{0, 0, 9, 0, 0}, true),

                // Additional cases
                new TestCase<>(new int[]{2, 3}, new int[]{3, 2}, true), // Two elements
                new TestCase<>(new int[]{0, 0}, new int[]{0, 0}, true), // Two zeros
                new TestCase<>(new int[]{1, 0, 3, 4}, new int[]{0, 12, 0, 0}, true), // Zero in the middle
                new TestCase<>(new int[]{-1, -2, -3, -4}, new int[]{-24, -12, -8, -6}, true), // All negative
                new TestCase<>(new int[]{1, -2, 3, -4}, new int[]{24, -12, 8, -6}, true) // Alternating signs
        );
    }

    public static void main(String[] args) {
        Problem<int[], int[]> problem = new ProductOfArrayExceptSelf();
        TestRunner.run(problem);
    }
}
