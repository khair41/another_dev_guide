package com.framework.patterns.dynamic_programming;

/**
 * Pattern 37: DP - 2D Array (Unique Paths on Grid)
 */
public class DpUniquePathsOnGrid {

    /*
     * =================================================================================
     * CONDITIONS FOR APPLICABILITY
     * =================================================================================
     *
     * 1.  Input Data Structure: A 2D grid or matrix.
     *
     * 2.  Problem Goal: You need to count the number of unique paths from a starting point
     *     (usually top-left) to an ending point (usually bottom-right). The movement is typically
     *     restricted (e.g., you can only move right or down).
     *     The problem can also include obstacles in the grid.
     *
     * 3.  Recurrence Relation: Let `dp[r][c]` be the number of unique paths to reach the cell at `(r, c)`.
     *     -   Since you can only move right or down, to reach cell `(r, c)`, you must have come from
     *         either the cell above, `(r-1, c)`, or the cell to the left, `(r, c-1)`.
     *     -   Therefore, the total number of ways to reach `(r, c)` is the sum of the ways to reach
     *         the cells it can be reached from.
     *     -   `dp[r][c] = dp[r-1][c] + dp[r][c-1]`
     *
     * 4.  Logic (Bottom-Up DP):
     *     -   Create a 2D DP array, `dp`, of the same size as the grid.
     *     -   Initialize the base cases. The starting cell `dp[0][0]` is 1. All cells in the first row
     *         (`dp[0][c]`) and first column (`dp[r][0]`) have only one way to be reached (by moving
     *         only right or only down), so they are also initialized to 1 (unless there is an obstacle).
     *     -   Iterate through the grid, starting from `(1, 1)`, and fill `dp[r][c]` using the recurrence relation.
     *     -   If the grid has obstacles, if `grid[r][c]` is an obstacle, then `dp[r][c]` should be set to 0.
     *     -   The final answer is the value in the bottom-right cell of the DP table.
     *
     * =================================================================================
     * GENERIC TEMPLATE (Unique Paths)
     * =================================================================================
     */

    /**
     * Calculates the number of unique paths from the top-left to the bottom-right of a grid.
     *
     * @param m The number of rows.
     * @param n The number of columns.
     * @return The number of unique paths.
     */
    public int uniquePaths(int m, int n) {
        if (m <= 0 || n <= 0) {
            return 0;
        }

        // --- Core Pattern Logic: Initialization ---
        int[][] dp = new int[m][n];

        // Base Cases: There is only one way to reach any cell in the first row or first column.
        for (int r = 0; r < m; r++) {
            dp[r][0] = 1;
        }
        for (int c = 0; c < n; c++) {
            dp[0][c] = 1;
        }
        // -------------------------------------------

        // --- Core Pattern Logic: Bottom-up calculation ---
        for (int r = 1; r < m; r++) {
            for (int c = 1; c < n; c++) {
                // --- Recurrence Relation ---
                dp[r][c] = dp[r - 1][c] + dp[r][c - 1];
                // -------------------------
            }
        }
        // ------------------------------------------------

        return dp[m - 1][n - 1];
    }
}
