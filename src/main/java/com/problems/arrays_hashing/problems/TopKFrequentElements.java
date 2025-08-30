package com.problems.arrays_hashing.problems;

import com.framework.*;
import com.problems.arrays_hashing.solutions.TopKFrequentElementsUsingMapAndBucketSortSolution;
import com.problems.arrays_hashing.solutions.TopKFrequentElementsUsingMapAndPQSolution;
import com.problems.arrays_hashing.solutions.TopKFrequentElementsUsingSortingSolution;

import java.util.List;
import java.util.Map;

public class TopKFrequentElements implements Problem<TopKFrequentElements.TopKFrequentElementsInput, int[]> {

    public record TopKFrequentElementsInput(int[] nums, int k){}

    @Override
    public String getID() {
        return "0347";
    }

    @Override
    public Difficulty getDifficulty() {
        return Difficulty.MEDIUM;
    }

    @Override
    public String getName() {
        return "Top K Frequent Elements";
    }

    @Override
    public String getDescription() {
        return """
                Given an integer array nums and an integer k, return the k most frequent elements. You may return the answer in any order.""" ;
    }

    @Override
    public Source getSource() {
        return Source.LEETCODE;
    }

    @Override
    public String getExternalLink() {
        return "https://leetcode.com/problems/top-k-frequent-elements/";
    }

    @Override
    public List<Topic> getTopics() {
        return List.of(Topic.ARRAY, Topic.HASHING, Topic.HEAP);
    }

    @Override
    public Map<String, Solution<TopKFrequentElementsInput, int[]>> getSolutions() {
        return Map.of(
                "Using Sorting", new TopKFrequentElementsUsingSortingSolution(),
                "Using HashMap and PriorityQueue", new TopKFrequentElementsUsingMapAndPQSolution(),
                "Using HashMap and BucketSort", new TopKFrequentElementsUsingMapAndBucketSortSolution()
        );
    }

    @Override
    public List<TestCase<TopKFrequentElementsInput, int[]>> getTestCases() {
        return List.of(
                // Provided test cases
                new TestCase<>(new TopKFrequentElementsInput(new int[]{1,1,1,2,2,3}, 2), new int[]{1,2}, true),
                new TestCase<>(new TopKFrequentElementsInput(new int[]{1}, 1), new int[]{1}, true),

                // Additional cases
                new TestCase<>(new TopKFrequentElementsInput(new int[]{1,2,3,4,5}, 5), new int[]{1,2,3,4,5}, true), // k equals number of unique elements
                new TestCase<>(new TopKFrequentElementsInput(new int[]{-1, -1, -2, -2, 3}, 2), new int[]{-1, -2}, true), // With negative numbers
//                new TestCase<>(new TopKFrequentElementsInput(new int[]{1, 2, 3, 4}, 2), new int[]{1, 2}, true), // All same frequency (any 2 are valid)
                new TestCase<>(new TopKFrequentElementsInput(new int[]{3,3,3,2,2,1}, 2), new int[]{3,2}, true), // Clear top 2
                new TestCase<>(new TopKFrequentElementsInput(new int[]{5,5,5,6,6,7,7,8}, 3), new int[]{5,6,7}, true), // Tie for 2nd/3rd frequent
                new TestCase<>(new TopKFrequentElementsInput(new int[]{4,1,-1,2,-1,2,3}, 2), new int[]{-1,2}, true) // Unsorted with negatives
        );
    }

    public static void main(String[] args) {
        Problem<TopKFrequentElementsInput, int[]> problem = new TopKFrequentElements();
        TestRunner.run(problem);
    }

}
