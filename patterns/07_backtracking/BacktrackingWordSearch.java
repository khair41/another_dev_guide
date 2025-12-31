package com.framework.patterns.backtracking;

/**
 * Pattern 50: Backtracking - Word Search / Path Finding in Grid
 */
public class BacktrackingWordSearch {

    /*
     * =================================================================================
     * CONDITIONS FOR APPLICABILITY
     * =================================================================================
     *
     * 1.  Input Data Structure: A 2D grid (matrix) and a word to find.
     *
     * 2.  Problem Goal: You need to find if a word can be formed by starting at any character
     *     in the grid and moving to adjacent cells (horizontally or vertically) without reusing
     *     the same cell in a single path.
     *
     * 3.  Logic: This is a classic application of DFS/backtracking on a grid.
     *
     *     -   The main function iterates through every cell of the grid. If a cell's character
     *         matches the first character of the word, it triggers a backtracking search starting
     *         from that cell.
     *     -   The recursive backtracking function tries to find the rest of the word.
     *
     *     -   **Recursive Step**:
     *         1.  **Base Case 1 (Failure)**: Check for boundary conditions (out of grid) or if the
     *             current cell does not match the required character of the word.
     *         2.  **Base Case 2 (Success)**: If you have successfully found all characters of the word,
     *             return `true`.
     *         3.  **Choose**: To prevent reusing a cell, mark the current cell as visited. A common
     *             trick is to temporarily change the character in the grid (e.g., to '#').
     *         4.  **Explore**: Make recursive calls for all four adjacent neighbors (up, down, left, right),
     *             advancing to the next character in the word.
     *         5.  **Check Result**: If any of the recursive calls return `true`, it means a path was found.
     *             Propagate this `true` result up the call stack.
     *         6.  **Unchoose (Backtrack)**: Before returning, restore the cell's original character. This is
     *             the crucial step that makes the cell available for other starting paths.
     *
     * =================================================================================
     * GENERIC TEMPLATE (Word Search)
     * =================================================================================
     */

    /**
     * Determines if a word exists in a grid of characters.
     *
     * @param board The 2D grid of characters.
     * @param word The word to search for.
     * @return True if the word is found, false otherwise.
     */
    public boolean exist(char[][] board, String word) {
        if (board == null || board.length == 0 || word == null || word.isEmpty()) {
            return false;
        }

        int numRows = board.length;
        int numCols = board[0].length;

        // --- Trigger search from every cell ---
        for (int r = 0; r < numRows; r++) {
            for (int c = 0; c < numCols; c++) {
                if (board[r][c] == word.charAt(0) && backtrack(board, word, r, c, 0)) {
                    return true;
                }
            }
        }
        // -------------------------------------

        return false;
    }

    /**
     * The recursive backtracking helper function.
     *
     * @param board The grid.
     * @param word The target word.
     * @param r The current row.
     * @param c The current column.
     * @param index The index of the character we are currently looking for in the word.
     * @return True if a path is found from this cell, false otherwise.
     */
    private boolean backtrack(char[][] board, String word, int r, int c, int index) {
        // --- Base Case 1: Success ---
        if (index == word.length()) {
            return true;
        }

        // --- Base Case 2: Failure (Boundary checks) ---
        if (r < 0 || r >= board.length || c < 0 || c >= board[0].length || board[r][c] != word.charAt(index)) {
            return false;
        }

        // --- 1. Choose: Mark the cell as visited ---
        char temp = board[r][c];
        board[r][c] = '#'; // Any character not in the alphabet works

        // --- 2. Explore: Recurse on neighbors ---
        boolean found = backtrack(board, word, r + 1, c, index + 1) ||
                        backtrack(board, word, r - 1, c, index + 1) ||
                        backtrack(board, word, r, c + 1, index + 1) ||
                        backtrack(board, word, r, c - 1, index + 1);

        // --- 3. Unchoose (Backtrack): Restore the cell ---
        board[r][c] = temp;

        return found;
    }
}
