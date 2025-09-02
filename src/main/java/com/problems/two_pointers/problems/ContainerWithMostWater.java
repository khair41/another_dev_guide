package com.problems.two_pointers.problems;

import com.framework.*;
import com.problems.two_pointers.solutions.ContainerWithMostWaterTwoPointersSolution;

import java.util.List;
import java.util.Map;

public class ContainerWithMostWater implements Problem<int[], Integer> {

    @Override
    public String getID() {
        return "0011";
    }

    @Override
    public Difficulty getDifficulty() {
        return Difficulty.MEDIUM;
    }

    @Override
    public String getName() {
        return "Container With Most Water";
    }

    @Override
    public String getDescription() {
        return """
                You are given an integer array height of length n. There are n vertical lines drawn such that the two endpoints of the ith line are (i, 0) and (i, height[i]).

                Find two lines that together with the x-axis form a container, such that the container contains the most water.

                Return the maximum amount of water a container can store.

                Notice that you may not slant the container.""";
    }

    @Override
    public Source getSource() {
        return Source.LEETCODE;
    }

    @Override
    public String getExternalLink() {
        return "https://leetcode.com/problems/container-with-most-water/";
    }

    @Override
    public List<Topic> getTopics() {
        return List.of(Topic.TWO_POINTERS, Topic.ARRAY, Topic.GREEDY);
    }

    @Override
    public Map<String, Solution<int[], Integer>> getSolutions() {
        return Map.of(
                "Two Pointers", new ContainerWithMostWaterTwoPointersSolution()
        );
    }

    @Override
    public List<TestCase<int[], Integer>> getTestCases() {
        return List.of(
                new TestCase<>(new int[]{1, 8, 6, 2, 5, 4, 8, 3, 7}, 49),
                new TestCase<>(new int[]{1, 1}, 1),
                new TestCase<>(new int[]{4, 3, 2, 1, 4}, 16)
        );
    }

    public static void main(String[] args) {
        Problem<int[], Integer> problem = new ContainerWithMostWater();
        TestRunner.run(problem);
    }
}
