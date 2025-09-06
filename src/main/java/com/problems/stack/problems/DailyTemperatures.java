package com.problems.stack.problems;

import com.framework.*;
import com.problems.stack.solutions.DailyTemperaturesBruteForceSolution;
import com.problems.stack.solutions.DailyTemperaturesDPSolution;
import com.problems.stack.solutions.DailyTemperaturesStackSolution;

import java.util.List;
import java.util.Map;

public class DailyTemperatures implements Problem<int[], int[]> {

    @Override
    public String getID() {
        return "0739";
    }

    @Override
    public Difficulty getDifficulty() {
        return Difficulty.MEDIUM;
    }

    @Override
    public String getName() {
        return "Daily Temperatures";
    }

    @Override
    public String getDescription() {
        return """
                Given an array of integers temperatures represents the daily temperatures, return an array answer such that answer[i] is the number of days you have to wait after the ith day to get a warmer temperature.

                If there is no future day for which this is possible, keep answer[i] == 0 instead.""";
    }

    @Override
    public Source getSource() {
        return Source.LEETCODE;
    }

    @Override
    public String getExternalLink() {
        return "https://leetcode.com/problems/daily-temperatures/";
    }

    @Override
    public List<Topic> getTopics() {
        return List.of(Topic.STACK, Topic.ARRAY, Topic.MONOTONIC_STACK);
    }

    @Override
    public Map<String, Solution<int[], int[]>> getSolutions() {
        return Map.of(
                "Brute Force", new DailyTemperaturesBruteForceSolution(),
                "Monotonic Stack", new DailyTemperaturesStackSolution()
//                "Dynamic Programming", new DailyTemperaturesDPSolution() TODO
        );
    }

    @Override
    public List<TestCase<int[], int[]>> getTestCases() {
        return List.of(
                new TestCase<>(new int[]{73, 74, 75, 71, 69, 72, 76, 73}, new int[]{1, 1, 4, 2, 1, 1, 0, 0}),
                new TestCase<>(new int[]{30, 40, 50, 60}, new int[]{1, 1, 1, 0}),
                new TestCase<>(new int[]{30, 60, 90}, new int[]{1, 1, 0})
        );
    }

    public static void main(String[] args) {
        Problem<int[], int[]> problem = new DailyTemperatures();
        TestRunner.run(problem);
    }
}
