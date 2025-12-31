package com.framework.patterns.graph_traversal;

/**
 * Pattern 18: Graph DFS - Connected Components / Island Counting
 */
public class GraphDfsIslandCounting {

    /*
     * =================================================================================
     * CONDITIONS FOR APPLICABILITY
     * =================================================================================
     *
     * 1.  Input Data Structure: A 2D grid (matrix) or an adjacency list/matrix representing a graph.
     *
     * 2.  Problem Goal: You need to count the number of distinct, disconnected groups of nodes.
     *     In a grid, this is often called "counting islands" (groups of '1's) in a sea of '0's.
     *     In a graph, it's called finding the number of "connected components".
     *
     * 3.  Logic: The core idea is to iterate through every node (or cell) of the graph/grid.
     *
     *     - Maintain a `visited` set or modify the grid in-place to keep track of visited nodes.
     *     - Loop through each cell `(r, c)` of the grid.
     *     - If a cell `(r, c)` contains an island part (e.g., '1') and has NOT been visited yet:
     *         - This cell is the start of a new, undiscovered island. Increment your island count.
     *         - Begin a traversal (DFS or BFS) from this cell.
     *         - The traversal will find all connected parts of this specific island and mark them
     *           as visited.
     *     - By the end of the main loop, the count will be accurate because the traversal "consumes"
     *       an entire island each time it's called on an unvisited land cell.
     *
     * =================================================================================
     * GENERIC TEMPLATE (Number of Islands using DFS)
     * =================================================================================
     */

    /**
     * Counts the number of islands in a 2D grid.
     *
     * @param grid A 2D grid where '1' is land and '0' is water.
     * @return The total number of islands.
     */
    public int numIslands(char[][] grid) {
        if (grid == null || grid.length == 0) {
            return 0;
        }

        int numRows = grid.length;
        int numCols = grid[0].length;
        int islandCount = 0;

        // --- Core Pattern Logic: Iterate through every cell ---
        for (int r = 0; r < numRows; r++) {
            for (int c = 0; c < numCols; c++) {
                // --- Problem-Specific Logic: Found the start of a new island ---
                if (grid[r][c] == '1') {
                    islandCount++;
                    // Sink the current island to mark it as visited.
                    dfs(grid, r, c);
                }
                // -----------------------------------------------------------
            }
        }
        // -----------------------------------------------------

        return islandCount;
    }

    /**
     * Recursive DFS helper to find and sink all parts of an island.
     * @param grid The grid.
     * @param r    The current row.
     * @param c    The current column.
     */
    private void dfs(char[][] grid, int r, int c) {
        int numRows = grid.length;
        int numCols = grid[0].length;

        // --- Core Pattern Logic: Base cases for recursion ---
        // Check for out of bounds or if the cell is water.
        if (r < 0 || c < 0 || r >= numRows || c >= numCols || grid[r][c] == '0') {
            return;
        }
        // -----------------------------------------------------

        // --- Problem-Specific Logic: Mark as visited ---
        // Sink the current part of the island by changing it to water.
        grid[r][c] = '0';
        // ----------------------------------------------

        // --- Core Pattern Logic: Explore neighbors ---
        dfs(grid, r + 1, c); // Down
        dfs(grid, r - 1, c); // Up
        dfs(grid, r, c + 1); // Right
        dfs(grid, r, c - 1); // Left
        // --------------------------------------------
    }
}
