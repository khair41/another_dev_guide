package com.problems.stack.problems;

import com.framework.*;
import com.problems.stack.solutions.CarFleetIterativeSolution;
import com.problems.stack.solutions.CarFleetStackSolution;

import java.util.List;
import java.util.Map;

public class CarFleetProblem implements Problem<CarFleetProblem.CarFleetInput, Integer> {

    // A record to hold the complex input for this problem
    public record CarFleetInput(int target, int[] position, int[] speed) {}

    @Override
    public String getID() {
        return "0853";
    }

    @Override
    public Difficulty getDifficulty() {
        return Difficulty.MEDIUM;
    }

    @Override
    public String getName() {
        return "Car Fleet";
    }

    @Override
    public String getDescription() {
        return """
                There are n cars going to the same destination along a one-lane road. The destination is target miles away.

                You are given two integer arrays position and speed, both of length n, where position[i] is the position of the ith car and speed[i] is the speed of the ith car (in miles per hour).

                A car can never pass another car ahead of it, but it can catch up to it and drive bumper to bumper at the same speed. The faster car will slow down to match the slower car's speed. The distance between these two cars is ignored (i.e., they are assumed to have the same position).

                A car fleet is some non-empty set of cars driving at the same position and same speed. Note that a single car is also a car fleet.

                If a car catches up to a car fleet right at the destination point, it will still be considered as one car fleet.

                Return the number of car fleets that will arrive at the destination.""";
    }

    @Override
    public Source getSource() {
        return Source.LEETCODE;
    }

    @Override
    public String getExternalLink() {
        return "https://leetcode.com/problems/car-fleet/";
    }

    @Override
    public List<Topic> getTopics() {
        return List.of(Topic.STACK, Topic.ARRAY, Topic.SORTING, Topic.MONOTONIC_STACK);
    }

    @Override
    public Map<String, Solution<CarFleetInput, Integer>> getSolutions() {
        return Map.of(
                "Iterative (Sorting)", new CarFleetIterativeSolution(),
                "Stack", new CarFleetStackSolution()
        );
    }

    @Override
    public List<TestCase<CarFleetInput, Integer>> getTestCases() {
        return List.of(
                new TestCase<>(new CarFleetInput(12, new int[]{10, 8, 0, 5, 3}, new int[]{2, 4, 1, 1, 3}), 3),
                new TestCase<>(new CarFleetInput(10, new int[]{3}, new int[]{3}), 1),
                new TestCase<>(new CarFleetInput(100, new int[]{0, 2, 4}, new int[]{4, 2, 1}), 1)
        );
    }

    public static void main(String[] args) {
        Problem<CarFleetInput, Integer> problem = new CarFleetProblem();
        TestRunner.run(problem);
    }
}
