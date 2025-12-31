package com.framework.patterns.graph_traversal;

import java.util.Arrays;

/**
 * Pattern 24: Graph - Shortest Path (Bellman-Ford / BFS+K)
 */
public class GraphBellmanFordShortestPath {

    /*
     * =================================================================================
     * CONDITIONS FOR APPLICABILITY
     * =================================================================================
     *
     * 1.  Input Data Structure: A weighted directed graph.
     *
     * 2.  Edge Weights: It can handle NEGATIVE edge weights. This is its main advantage over Dijkstra.
     *
     * 3.  Problem Goal:
     *     a) Find the shortest path from a single source to all other nodes (like Dijkstra).
     *     b) Detect if the graph contains a "negative weight cycle" (a cycle whose edges sum to a negative value).
     *     c) Find the shortest path given a constraint on the maximum number of edges allowed (e.g., "at most K stops").
     *
     * 4.  Logic (Classic Bellman-Ford):
     *     - The core idea is to "relax" all edges repeatedly.
     *     - Initialize a `distances` array with infinity, and the source distance to 0.
     *     - Loop `V-1` times (where `V` is the number of vertices).
     *     - In each iteration, loop through ALL edges `(u, v)` with weight `w` and update the distance to `v` if a shorter path is found through `u` (`distances[u] + w < distances[v]`).
     *     - After `V-1` iterations, perform one final iteration. If any distance can still be improved, a negative weight cycle exists.
     *
     * 5.  Logic (BFS with K stops variation):
     *     - This is a modification suitable for problems like "cheapest flight with at most K stops".
     *     - It works like a level-by-level BFS, where each level corresponds to using one more edge.
     *     - Maintain a `distances` array. In each of the `K+1` iterations, you calculate the next level's distances based on the current level's distances, ensuring you don't use the results from the same iteration.
     *
     * =================================================================================
     * GENERIC TEMPLATE (Cheapest Flights Within K Stops - a Bellman-Ford variation)
     * =================================================================================
     */

    /**
     * Finds the cheapest price from a source `src` to a destination `dst` with at most `k` stops.
     *
     * @param n The number of cities (nodes).
     * @param flights The flight data as `[from, to, price]`.
     * @param src The source city.
     * @param dst The destination city.
     * @param k The maximum number of stops allowed.
     * @return The cheapest price, or -1 if no such route exists.
     */
    public int findCheapestPrice(int n, int[][] flights, int src, int dst, int k) {
        // --- Core Pattern Logic: Initialization ---
        // Distances array for the current level of edges.
        int[] distances = new int[n];
        Arrays.fill(distances, Integer.MAX_VALUE);
        distances[src] = 0;

        // Bellman-Ford runs for k+1 iterations (for k stops, we can have k+1 edges).
        for (int i = 0; i <= k; i++) {
            // --- Problem-Specific Logic: Use a temp array ---
            // We use a temporary array to store the next level's distances.
            // This ensures that each iteration only uses distances from the previous level (i-1 edges).
            int[] tempDistances = Arrays.copyOf(distances, n);

            for (int[] flight : flights) {
                int u = flight[0];
                int v = flight[1];
                int price = flight[2];

                if (distances[u] != Integer.MAX_VALUE) {
                    // --- Core Pattern Logic: Relaxation ---
                    if (distances[u] + price < tempDistances[v]) {
                        tempDistances[v] = distances[u] + price;
                    }
                    // -------------------------------------
                }
            }
            // Update the main distances array for the next iteration.
            distances = tempDistances;
        }

        // The final result is the distance to the destination after k+1 iterations.
        return distances[dst] == Integer.MAX_VALUE ? -1 : distances[dst];
    }
}
