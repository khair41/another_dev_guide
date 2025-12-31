package com.framework.patterns.graph_traversal;

import java.util.LinkedList;
import java.util.Queue;

/**
 * Pattern 19: Graph BFS - Connected Components / Island Counting
 */
public class GraphBfsIslandCounting {

    /*
     * =================================================================================
     * CONDITIONS FOR APPLICABILITY
     * =================================================================================
     *
     * 1.  Input Data Structure: A 2D grid (matrix) or an adjacency list/matrix representing a graph.
     *
     * 2.  Problem Goal: Same as the DFS version (Pattern 18). You need to count the number of
     *     distinct, disconnected groups of nodes (islands or connected components).
     *
     * 3.  Logic: The overall logic is identical to the DFS approach. You iterate through each
     *     cell, and if you find an unvisited part of an island, you increment a counter and start
     *     a traversal to find and mark all parts of that island. The only difference is that
     *     the traversal is done using Breadth-First Search (BFS).
     *
     *     -   **BFS Traversal**: BFS uses a Queue to explore nodes level by level. When you start
     *         a traversal at `(r, c)`, you add it to a queue and mark it as visited.
     *     -   Then, you loop until the queue is empty. In each step, you dequeue a cell, and for
     *         each of its valid, unvisited neighbors, you mark them and add them to the queue.
     *
     * =================================================================================
     * GENERIC TEMPLATE (Number of Islands using BFS)
     * =================================================================================
     */

    /**
     * Counts the number of islands in a 2D grid using BFS.
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

        for (int r = 0; r < numRows; r++) {
            for (int c = 0; c < numCols; c++) {
                if (grid[r][c] == '1') {
                    islandCount++;
                    grid[r][c] = '0'; // Mark as visited immediately
                    bfs(grid, r, c);
                }
            }
        }

        return islandCount;
    }

    /**
     * Iterative BFS helper to find and sink all parts of an island.
     * @param grid The grid.
     * @param r    The starting row.
     * @param c    The starting column.
     */
    private void bfs(char[][] grid, int r, int c) {
        int numRows = grid.length;
        int numCols = grid[0].length;
        Queue<int[]> queue = new LinkedList<>();
        queue.offer(new int[]{r, c});

        int[][] directions = {{1, 0}, {-1, 0}, {0, 1}, {0, -1}}; // Down, Up, Right, Left

        while (!queue.isEmpty()) {
            int[] current = queue.poll();
            int currentRow = current[0];
            int currentCol = current[1];

            // Explore neighbors
            for (int[] dir : directions) {
                int nextRow = currentRow + dir[0];
                int nextCol = currentCol + dir[1];

                // Check for valid next cell
                if (nextRow >= 0 && nextRow < numRows && nextCol >= 0 && nextCol < numCols && grid[nextRow][nextCol] == '1') {
                    grid[nextRow][nextCol] = '0'; // Mark as visited
                    queue.offer(new int[]{nextRow, nextCol});
                }
            }
        }
    }
}
