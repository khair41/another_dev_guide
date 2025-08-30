package com.problems.arrays_hashing.problems;

import com.framework.*;
import com.problems.arrays_hashing.solutions.ValidSudokuUsingSetsSolution;

import java.util.List;
import java.util.Map;

public class ValidSudoku implements Problem<char[][], Boolean> {
    @Override
    public String getID() {
        return "0036";
    }

    @Override
    public Difficulty getDifficulty() {
        return Difficulty.MEDIUM;
    }

    @Override
    public String getName() {
        return "Valid Sudoku";
    }

    @Override
    public String getDescription() {
        return """
                Determine if a 9 x 9 Sudoku board is valid. Only the filled cells need to be validated according to the following rules:" +
                1. Each row must contain the digits 1-9 without repetition." +
                2. Each column must contain the digits 1-9 without repetition." +
                3. Each of the nine 3 x 3 sub-boxes of the grid must contain the digits 1-9 without repetition.""" ;
    }

    @Override
    public Source getSource() {
        return Source.LEETCODE;
    }

    @Override
    public String getExternalLink() {
        return "https://leetcode.com/problems/valid-sudoku/";
    }

    @Override
    public List<Topic> getTopics() {
        return List.of(Topic.ARRAY, Topic.HASHING, Topic.MATRIX);
    }

    @Override
    public Map<String, Solution<char[][], Boolean>> getSolutions() {
        return Map.of(
                "Using Sets", new ValidSudokuUsingSetsSolution()
        );
    }

    @Override
    public List<TestCase<char[][], Boolean>> getTestCases() {
        // A valid, partially filled board
        char[][] validBoard = {
            {'5','3','.','.','7','.','.','.','.'},
            {'6','.','.','1','9','5','.','.','.'},
            {'.','9','8','.','.','.','.','6','.'},
            {'8','.','.','.','6','.','.','.','3'},
            {'4','.','.','8','.','3','.','.','1'},
            {'7','.','.','.','2','.','.','.','6'},
            {'.','6','.','.','.','.','2','8','.'},
            {'.','.','.','4','1','9','.','.','5'},
            {'.','.','.','.','8','.','.','7','9'}
        };

        // An invalid board with a duplicate in a row
        char[][] invalidRowBoard = {
            {'5','3','5','.','7','.','.','.','.'}, // Duplicate '5'
            {'6','.','.','1','9','5','.','.','.'},
            {'.','9','8','.','.','.','.','6','.'},
            {'8','.','.','.','6','.','.','.','3'},
            {'4','.','.','8','.','3','.','.','1'},
            {'7','.','.','.','2','.','.','.','6'},
            {'.','6','.','.','.','.','2','8','.'},
            {'.','.','.','4','1','9','.','.','5'},
            {'.','.','.','.','8','.','.','.','7','9'}
        };

        // An invalid board with a duplicate in a column
        char[][] invalidColBoard = {
            {'5','3','.','.','7','.','.','.','.'},
            {'6','.','.','1','9','5','.','.','.'},
            {'.','9','8','.','.','.','.','6','.'},
            {'8','.','.','.','6','.','.','.','3'},
            {'4','.','.','8','.','3','.','.','1'},
            {'7','.','.','.','2','.','.','.','6'},
            {'.','6','.','.','.','.','2','8','.'},
            {'.','.','.','4','1','9','.','.','5'},
            {'5','.','.','.','8','.','.','7','9'} // Duplicate '5' in first column
        };

        // An invalid board with a duplicate in a sub-box
        char[][] invalidBoxBoard = {
            {'5','3','.','.','7','.','.','.','.'},
            {'6','9','.','1','9','5','.','.','.'}, // Duplicate '9' in top-left box
            {'.','9','8','.','.','.','.','6','.'},
            {'8','.','.','.','6','.','.','.','3'},
            {'4','.','.','8','.','3','.','.','1'},
            {'7','.','.','.','2','.','.','.','6'},
            {'.','6','.','.','.','.','2','8','.'},
            {'.','.','.','4','1','9','.','.','5'},
            {'.','.','.','.','8','.','.','.','7','9'}
        };

        // A completely empty board (should be valid)
        char[][] emptyBoard = {
            {'.','.','.','.','.','.','.','.','.'},
            {'.','.','.','.','.','.','.','.','.'},
            {'.','.','.','.','.','.','.','.','.'},
            {'.','.','.','.','.','.','.','.','.'},
            {'.','.','.','.','.','.','.','.','.'},
            {'.','.','.','.','.','.','.','.','.'},
            {'.','.','.','.','.','.','.','.','.'},
            {'.','.','.','.','.','.','.','.','.'},
            {'.','.','.','.','.','.','.','.','.'}
        };
        
        // A completely filled, valid board
        char[][] fullValidBoard = {
            {'5','3','4','6','7','8','9','1','2'},
            {'6','7','2','1','9','5','3','4','8'},
            {'1','9','8','3','4','2','5','6','7'},
            {'8','5','9','7','6','1','4','2','3'},
            {'4','2','6','8','5','3','7','9','1'},
            {'7','1','3','9','2','4','8','5','6'},
            {'9','6','1','5','3','7','2','8','4'},
            {'2','8','7','4','1','9','6','3','5'},
            {'3','4','5','2','8','6','1','7','9'}
        };

        return List.of(
                new TestCase<>(validBoard, true),
                new TestCase<>(invalidRowBoard, false),
                new TestCase<>(invalidColBoard, false),
                new TestCase<>(invalidBoxBoard, false),
                new TestCase<>(emptyBoard, true),
                new TestCase<>(fullValidBoard, true)
        );
    }

    public static void main(String[] args) {
        Problem<char[][], Boolean> problem = new ValidSudoku();
        TestRunner.run(problem);
    }
}
