package com.problems.binary_search.problems;

import com.framework.*;
import com.problems.binary_search.solutions.SearchA2DMatrixSolution;

import java.util.List;
import java.util.Map;

public class SearchA2DMatrixProblem implements Problem<SearchA2DMatrixProblem.SearchA2DMatrixInput, Boolean> {

    // A record to hold the input for this problem
    public record SearchA2DMatrixInput(int[][] matrix, int target) {}

    @Override
    public String getID() {
        return "0074";
    }

    @Override
    public Difficulty getDifficulty() {
        return Difficulty.MEDIUM;
    }

    @Override
    public String getName() {
        return "Search a 2D Matrix";
    }

    @Override
    public String getDescription() {
        return """
                Write an efficient algorithm that searches for a value target in an m x n integer matrix. This matrix has the following properties:

                - Integers in each row are sorted from left to right.
                - The first integer of each row is greater than the last integer of the previous row.""";
    }

    @Override
    public Source getSource() {
        return Source.LEETCODE;
    }

    @Override
    public String getExternalLink() {
        return "https://leetcode.com/problems/search-a-2d-matrix/";
    }

    @Override
    public List<Topic> getTopics() {
        return List.of(Topic.BINARY_SEARCH, Topic.ARRAY, Topic.MATRIX);
    }

    @Override
    public Map<String, Solution<SearchA2DMatrixInput, Boolean>> getSolutions() {
        return Map.of(
                "Binary Search on Virtual Array", new SearchA2DMatrixSolution()
        );
    }

    @Override
    public List<TestCase<SearchA2DMatrixInput, Boolean>> getTestCases() {
        return List.of(
                // Provided test cases
                new TestCase<>(new SearchA2DMatrixInput(new int[][]{{1, 3, 5, 7}, {10, 11, 16, 20}, {23, 30, 34, 60}}, 3), true),
                new TestCase<>(new SearchA2DMatrixInput(new int[][]{{1, 3, 5, 7}, {10, 11, 16, 20}, {23, 30, 34, 60}}, 13), false),

                // Edge cases for matrix dimensions
                new TestCase<>(new SearchA2DMatrixInput(new int[][]{{1}}, 1), true),
                new TestCase<>(new SearchA2DMatrixInput(new int[][]{{1, 3}}, 3), true),
                new TestCase<>(new SearchA2DMatrixInput(new int[][]{{1}, {3}}, 3), true),

                // Edge cases for target position
                new TestCase<>(new SearchA2DMatrixInput(new int[][]{{1, 3, 5}, {10, 11, 16}}, 1), true), // Target is the very first element
                new TestCase<>(new SearchA2DMatrixInput(new int[][]{{1, 3, 5}, {10, 11, 16}}, 16), true), // Target is the very last element
                new TestCase<>(new SearchA2DMatrixInput(new int[][]{{1, 3, 5}, {10, 11, 16}}, 10), true), // Target is the start of a row
                new TestCase<>(new SearchA2DMatrixInput(new int[][]{{1, 3, 5}, {10, 11, 16}}, 5), true), // Target is the end of a row

                // Edge cases for target value (out of bounds)
                new TestCase<>(new SearchA2DMatrixInput(new int[][]{{10, 20}, {30, 40}}, 5), false), // Target is smaller than all elements
                new TestCase<>(new SearchA2DMatrixInput(new int[][]{{10, 20}, {30, 40}}, 50), false), // Target is larger than all elements

                // Newly added complex cases
                new TestCase<>(new SearchA2DMatrixInput(new int[][]{{1, 3, 5, 7}, {10, 11, 16, 20}, {23, 30, 34, 60}}, 23), true), // Target is first element of a later row
                new TestCase<>(new SearchA2DMatrixInput(new int[][]{{-10, -5, 0}, {5, 10, 15}}, -5), true), // With negative numbers
                new TestCase<>(new SearchA2DMatrixInput(new int[][]{{-10, -5, 0}, {5, 10, 15}}, 2), false), // Miss with negative numbers
                new TestCase<>(new SearchA2DMatrixInput(new int[][]{{1, 1, 1}, {2, 2, 2}}, 1), true), // With duplicate numbers
                new TestCase<>(new SearchA2DMatrixInput(new int[][]{{1, 1, 1}, {2, 2, 2}}, 3), false) // Miss with duplicate numbers
        );
    }

    public static void main(String[] args) {
        Problem<SearchA2DMatrixInput, Boolean> problem = new SearchA2DMatrixProblem();
        TestRunner.run(problem);
    }
}
