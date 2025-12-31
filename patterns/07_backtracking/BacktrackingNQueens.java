package com.framework.patterns.backtracking;

import java.util.ArrayList;
import java.util.List;

/**
 * Pattern 51: Backtracking - N-Queens / Constraint Satisfaction
 */
public class BacktrackingNQueens {

    /*
     * =================================================================================
     * CONDITIONS FOR APPLICABILITY
     * =================================================================================
     *
     * 1.  Problem Goal: You need to place `N` items (e.g., queens) on an `N x N` board subject
     *     to a set of constraints. The goal is often to find one or all valid configurations.
     *     For N-Queens, the constraint is that no two queens can attack each other (they cannot be
     *     in the same row, column, or diagonal).
     *
     * 2.  Logic: This is a perfect fit for backtracking. We try to place one queen in each row, one by one.
     *
     *     -   The recursive function attempts to place a queen in a specific row (`row`).
     *     -   It iterates through every column (`col`) in that row.
     *     -   For each column, it checks if placing a queen at `(row, col)` is a **valid move**.
     *     -   **Constraint Checking**: To be valid, the new position must not be under attack by any
     *         queens placed in previous rows. This requires checking:
     *         1.  The same column.
     *         2.  The upper-left diagonal.
     *         3.  The upper-right diagonal.
     *         (We don't need to check the row, as our algorithm places only one queen per row).
     *         This check can be optimized using helper sets or boolean arrays to store occupied columns and diagonals.
     *
     *     -   **Recursive Step**:
     *         1.  **Base Case**: If `row == N`, we have successfully placed all `N` queens. A valid solution
     *             has been found. Add it to the results and return.
     *         2.  **Explore**: Iterate through each column `col` for the current `row`.
     *         3.  **Check & Choose**: If placing a queen at `(row, col)` is valid:
     *             -   Place the queen (update the board state).
     *         4.  **Recurse**: Make a recursive call for the next row (`row + 1`).
     *         5.  **Unchoose (Backtrack)**: After the call returns, remove the queen from `(row, col)` to
     *             explore other possibilities for the current row.
     *
     * =================================================================================
     * GENERIC TEMPLATE (N-Queens)
     * =================================================================================
     */

    /**
     * Solves the N-Queens problem.
     *
     * @param n The size of the board and the number of queens.
     * @return A list of all distinct solutions to the N-Queens puzzle.
     */
    public List<List<String>> solveNQueens(int n) {
        List<List<String>> result = new ArrayList<>();
        char[][] board = new char[n][n];
        for (int i = 0; i < n; i++) {
            for (int j = 0; j < n; j++) {
                board[i][j] = '.';
            }
        }
        backtrack(n, 0, board, result);
        return result;
    }

    /**
     * The recursive backtracking helper function.
     *
     * @param n The size of the board.
     * @param row The current row we are trying to place a queen in.
     * @param board The current state of the board.
     * @param result The list to store all valid solutions.
     */
    private void backtrack(int n, int row, char[][] board, List<List<String>> result) {
        // --- Base Case: All queens have been placed successfully ---
        if (row == n) {
            result.add(constructSolution(board));
            return;
        }
        // ---------------------------------------------------------

        // --- Explore: Try placing a queen in each column of the current row ---
        for (int col = 0; col < n; col++) {
            // --- Check: See if the current position is valid ---
            if (isValid(board, row, col)) {
                // 1. Choose: Place the queen.
                board[row][col] = 'Q';

                // 2. Recurse: Move to the next row.
                backtrack(n, row + 1, board, result);

                // 3. Unchoose (Backtrack): Remove the queen.
                board[row][col] = '.';
            }
        }
        // ---------------------------------------------------------------------
    }

    /**
     * Checks if it's valid to place a queen at board[row][col].
     */
    private boolean isValid(char[][] board, int row, int col) {
        int n = board.length;
        // Check column
        for (int i = 0; i < row; i++) {
            if (board[i][col] == 'Q') return false;
        }
        // Check upper-left diagonal
        for (int i = row - 1, j = col - 1; i >= 0 && j >= 0; i--, j--) {
            if (board[i][j] == 'Q') return false;
        }
        // Check upper-right diagonal
        for (int i = row - 1, j = col + 1; i >= 0 && j < n; i--, j++) {
            if (board[i][j] == 'Q') return false;
        }
        return true;
    }

    /**
     * Constructs a solution from the final board state.
     */
    private List<String> constructSolution(char[][] board) {
        List<String> solution = new ArrayList<>();
        for (char[] row : board) {
            solution.add(new String(row));
        }
        return solution;
    }
}
