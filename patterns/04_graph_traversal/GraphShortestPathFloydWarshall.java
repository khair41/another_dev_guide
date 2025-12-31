package com.framework.patterns.graph_traversal;

/**
 * Advanced Graph Pattern: All-Pairs Shortest Path (Floyd-Warshall Algorithm)
 */
public class GraphShortestPathFloydWarshall {

    /*
     * =================================================================================
     * CONDITIONS FOR APPLICABILITY
     * =================================================================================
     *
     * 1.  Input Data Structure: A weighted, directed graph, typically represented by an
     *     adjacency matrix.
     *
     * 2.  Problem Goal: You need to find the shortest distance between **every possible pair**
     *     of nodes in the graph.
     *
     * 3.  Edge Weights: The algorithm can handle both positive and negative edge weights. However,
     *     it cannot handle negative-weight cycles (a cycle where the sum of edge weights is negative).
     *     The algorithm can be used to detect the presence of such cycles.
     *
     * 4.  Logic: This is a dynamic programming algorithm. The core idea is to gradually build up the
     *     solution by allowing more and more vertices to be used as intermediate points in the paths.
     *
     *     -   Let `dist[i][j]` be the shortest distance from node `i` to node `j`.
     *     -   Initialize `dist[i][j]` with the direct edge weight from `i` to `j`, or infinity if no
     *       direct edge exists. The distance from a node to itself, `dist[i][i]`, is 0.
     *     -   The algorithm then iterates through all vertices `k` and considers each as a potential
     *       intermediate node in the path from `i` to `j`.
     *     -   **Recurrence Relation**: For every intermediate node `k`, the shortest path from `i` to `j`
     *       is the minimum of the path that already exists (`dist[i][j]`) and the path that goes
     *       through `k` (`dist[i][k] + dist[k][j]`).
     *     -   `dist[i][j] = min(dist[i][j], dist[i][k] + dist[k][j])`
     *     -   The order of the loops is critical: the outermost loop **must** be for the intermediate vertex `k`.
     *
     * =================================================================================
     * GENERIC TEMPLATE (Floyd-Warshall)
     * =================================================================================
     */

    /**
     * Computes the shortest paths between all pairs of vertices in a weighted directed graph.
     *
     * @param n The number of vertices in the graph.
     * @param edges The list of directed edges `[from, to, weight]`.
     * @return A 2D array `dist` where `dist[i][j]` is the shortest distance from node `i` to `j`,
     *         or `Integer.MAX_VALUE` if no path exists.
     */
    public int[][] floydWarshall(int n, int[][] edges) {
        final int INF = Integer.MAX_VALUE / 2; // Use a large number to avoid overflow
        int[][] dist = new int[n][n];

        // --- Step 1: Initialization ---
        for (int i = 0; i < n; i++) {
            for (int j = 0; j < n; j++) {
                if (i == j) {
                    dist[i][j] = 0;
                } else {
                    dist[i][j] = INF;
                }
            }
        }
        for (int[] edge : edges) {
            dist[edge[0]][edge[1]] = edge[2];
        }
        // --------------------------------

        // --- Core Pattern Logic: Main Algorithm ---
        // The loops MUST be in the order k, i, j.
        for (int k = 0; k < n; k++) {       // k is the intermediate vertex
            for (int i = 0; i < n; i++) {   // i is the source vertex
                for (int j = 0; j < n; j++) { // j is the destination vertex
                    // --- Recurrence Relation ---
                    if (dist[i][k] != INF && dist[k][j] != INF) {
                        dist[i][j] = Math.min(dist[i][j], dist[i][k] + dist[k][j]);
                    }
                    // -------------------------
                }
            }
        }
        // -----------------------------------------

        // Optional: Detect negative weight cycles. If the distance from a node to itself
        // becomes negative, it means it's part of a negative cycle.
        for (int i = 0; i < n; i++) {
            if (dist[i][i] < 0) {
                // Handle negative cycle detection as needed by the problem.
                // For this template, we just note it. The distances may be unreliable.
            }
        }

        return dist;
    }
}
