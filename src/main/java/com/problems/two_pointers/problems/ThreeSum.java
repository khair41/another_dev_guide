package com.problems.two_pointers.problems;

import com.framework.*;
import com.problems.two_pointers.solutions.ThreeSumHashMapSolution;
import com.problems.two_pointers.solutions.ThreeSumTwoPointersSolution;

import java.util.List;
import java.util.Map;

public class ThreeSum implements Problem<int[], List<List<Integer>>> {

    @Override
    public String getID() {
        return "0015";
    }

    @Override
    public Difficulty getDifficulty() {
        return Difficulty.MEDIUM;
    }

    @Override
    public String getName() {
        return "3Sum";
    }

    @Override
    public String getDescription() {
        return """
                Given an integer array nums, return all the triplets [nums[i], nums[j], nums[k]] such that i != j, i != k, and j != k, and nums[i] + nums[j] + nums[k] == 0.

                Notice that the solution set must not contain duplicate triplets.""";
    }

    @Override
    public Source getSource() {
        return Source.LEETCODE;
    }

    @Override
    public String getExternalLink() {
        return "https://leetcode.com/problems/3sum/";
    }

    @Override
    public List<Topic> getTopics() {
        return List.of(Topic.TWO_POINTERS, Topic.ARRAY, Topic.SORTING);
    }

    @Override
    public Map<String, Solution<int[], List<List<Integer>>>> getSolutions() {
        return Map.of(
                "Two Pointers with Sorting", new ThreeSumTwoPointersSolution(),
                "HashMap with Sorting", new ThreeSumHashMapSolution()
        );
    }

    @Override
    public List<TestCase<int[], List<List<Integer>>>> getTestCases() {
        return List.of(
                new TestCase<>(new int[]{-1, 0, 1, 2, -1, -4}, List.of(List.of(-1, -1, 2), List.of(-1, 0, 1)), true),
                new TestCase<>(new int[]{}, List.of(), true),
                new TestCase<>(new int[]{0}, List.of(), true),
                new TestCase<>(new int[]{0, 0, 0}, List.of(List.of(0, 0, 0)), true)
        );
    }

    public static void main(String[] args) {
        Problem<int[], List<List<Integer>>> problem = new ThreeSum();
        TestRunner.run(problem);
    }
}
