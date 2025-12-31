package com.framework.patterns.graph_traversal;

import java.util.Arrays;
import java.util.HashMap;
import java.util.Map;
import java.util.PriorityQueue;

/**
 * Pattern 23: Graph - Shortest Path (Dijkstra's Algorithm)
 */
public class GraphDijkstraShortestPath {

    /*
     * =================================================================================
     * CONDITIONS FOR APPLICABILITY
     * =================================================================================
     *
     * 1.  Input Data Structure: A weighted directed or undirected graph.
     *
     * 2.  Edge Weights: All edge weights must be NON-NEGATIVE. If there are negative edge
     *     weights, Dijkstra's algorithm will not work correctly, and you must use an algorithm
     *     like Bellman-Ford (Pattern 24).
     *
     * 3.  Problem Goal: You need to find the shortest path from a single source node to all
     *     other nodes in the graph.
     *
     * 4.  Logic: Dijkstra's is a greedy algorithm that uses a Priority Queue to efficiently
     *     explore the graph.
     *
     *     -   **Initialization**:
     *         -   Create a `distances` array to store the shortest distance from the source to every
     *             other node. Initialize all distances to infinity, except for the source node, which is 0.
     *         -   Create a Priority Queue to store pairs of `(distance, node)`. The priority queue
     *             will always return the node with the smallest distance.
     *         -   Add the source node to the priority queue with a distance of 0.
     *
     *     -   **Traversal**:
     *         -   While the priority queue is not empty:
     *             -   Extract the node `u` with the smallest distance from the priority queue.
     *             -   If the extracted distance is greater than the known shortest distance to `u`,
     *               it means we have found a shorter path to `u` already. Skip this entry.
     *             -   For each neighbor `v` of `u`:
     *                 -   Calculate the new potential distance to `v` through `u` (i.e., `distance[u] + weight(u, v)`).
     *                 -   If this new distance is shorter than the current known distance to `v`,
     *                     update `distances[v]` and add the new `(distance, v)` pair to the priority queue.
     *
     * =================================================================================
     * GENERIC TEMPLATE (Network Delay Time using Dijkstra's)
     * =================================================================================
     */

    /**
     * Calculates the time it takes for a signal to reach all nodes from a source node `k`.
     *
     * @param times An array of `[u, v, w]` representing a directed edge from `u` to `v` with weight `w`.
     * @param n The total number of nodes in the graph.
     * @param k The source node (1-based).
     * @return The time for the signal to reach all nodes, or -1 if some nodes are unreachable.
     */
    public int networkDelayTime(int[][] times, int n, int k) {
        // --- Core Pattern Logic: Step 1 - Build Adjacency List ---
        Map<Integer, Map<Integer, Integer>> adj = new HashMap<>();
        for (int[] time : times) {
            adj.computeIfAbsent(time[0], key -> new HashMap<>()).put(time[1], time[2]);
        }
        // ---------------------------------------------------------

        // --- Core Pattern Logic: Step 2 - Initialization ---
        int[] distances = new int[n + 1];
        Arrays.fill(distances, Integer.MAX_VALUE);
        distances[k] = 0;

        // Priority Queue stores {node, distance from source}
        PriorityQueue<int[]> pq = new PriorityQueue<>((a, b) -> a[1] - b[1]);
        pq.offer(new int[]{k, 0});
        // ---------------------------------------------------

        // --- Core Pattern Logic: Step 3 - Traversal ---
        while (!pq.isEmpty()) {
            int[] current = pq.poll();
            int u = current[0];
            int dist_u = current[1];

            // If we've found a shorter path already, skip.
            if (dist_u > distances[u]) {
                continue;
            }

            if (!adj.containsKey(u)) {
                continue;
            }

            // For each neighbor v of u
            for (Map.Entry<Integer, Integer> edge : adj.get(u).entrySet()) {
                int v = edge.getKey();
                int weight_uv = edge.getValue();

                // --- Problem-Specific Logic: Relaxation ---
                if (distances[u] + weight_uv < distances[v]) {
                    distances[v] = distances[u] + weight_uv;
                    pq.offer(new int[]{v, distances[v]});
                }
                // ------------------------------------------
            }
        }
        // ------------------------------------------------

        // --- Problem-Specific Logic: Find the max distance ---
        int maxDist = 0;
        for (int i = 1; i <= n; i++) {
            if (distances[i] == Integer.MAX_VALUE) {
                return -1; // A node is unreachable
            }
            maxDist = Math.max(maxDist, distances[i]);
        }
        // ---------------------------------------------------

        return maxDist;
    }
}
