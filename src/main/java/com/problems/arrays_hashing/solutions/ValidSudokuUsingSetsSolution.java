package com.problems.arrays_hashing.solutions;

import com.framework.Solution;

import java.util.HashMap;
import java.util.HashSet;
import java.util.Map;
import java.util.Set;

public class ValidSudokuUsingSetsSolution implements Solution<char[][], Boolean> {
    @Override
    public Boolean execute(char[][] board) {
        int ROWS = board.length, COLS = board[0].length;
        Map<Integer, Set<Character>> rows = new HashMap<>();
        Map<Integer, Set<Character>> cols = new HashMap<>();
        Map<String, Set<Character>> squares = new HashMap<>();

        for(int r = 0; r < ROWS; r++){
            for(int c = 0; c < COLS; c++){
                if(board[r][c] == '.') continue;


                String squareKey = (r / 3) + "," + (c / 3);
                if(rows.computeIfAbsent(r, k -> new HashSet<>()).contains(board[r][c])
                || cols.computeIfAbsent(c, k -> new HashSet<>()).contains(board[r][c])
                || squares.computeIfAbsent(squareKey, k -> new HashSet<>()).contains(board[r][c]))
                    return false;

                rows.get(r).add(board[r][c]);
                cols.get(c).add(board[r][c]);
                squares.get(squareKey).add(board[r][c]);
            }
        }



        return true;
    }

    @Override
    public String getTimeComplexity() {
        // The board size is fixed at 9x9, so the complexity is constant.
        return "O(N ^ 2)";
    }

    @Override
    public String getSpaceComplexity() {
        // The space used is also constant due to the fixed board size.
        return "O(N ^ 2)";
    }
}
