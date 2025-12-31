package com.framework.patterns.graph_traversal;

import java.util.ArrayList;
import java.util.List;
import java.util.PriorityQueue;

/**
 * Advanced Graph Pattern: Minimum Spanning Tree (Prim's Algorithm)
 */
public class GraphMstPrim {

    /*
     * =================================================================================
     * CONDITIONS FOR APPLICABILITY
     * =================================================================================
     *
     * 1.  Input Data Structure: A weighted, undirected, connected graph.
     *
     * 2.  Problem Goal: You need to find a subgraph that connects all the vertices together,
     *     without any cycles and with the minimum possible total edge weight (an MST).
     *     This is the same goal as Kruskal's algorithm.
     *
     * 3.  Logic: Prim's algorithm is a greedy algorithm that "grows" the MST from an arbitrary
     *     starting vertex. It works by adding one vertex at a time to the growing MST.
     *
     *     -   The core idea is to always add the cheapest possible edge that connects a vertex
     *       *inside* the growing MST to a vertex *outside* the MST.
     *     -   A **Priority Queue** is used to efficiently find this cheapest edge at every step.
     *
     *     -   **Algorithm**:
     *         1.  Initialize a `visited` set to keep track of vertices already in the MST.
     *         2.  Initialize a `PriorityQueue` to store edges `[from, to, weight]`. The queue will be
     *             ordered by weight.
     *         3.  Choose an arbitrary starting vertex (e.g., vertex 0). Add all its edges to the priority queue.
     *             Mark the starting vertex as visited.
     *         4.  Loop until the MST includes all vertices (`V-1` edges have been added).
     *         5.  In each iteration:
     *             -   Extract the edge with the minimum weight from the priority queue.
     *             -   If the destination vertex of this edge is already in the `visited` set, skip it
     *               (this is to avoid creating a cycle).
     *             -   If the destination vertex is not visited, this is a valid edge. Add its weight to the
     *               total MST cost, mark the destination vertex as visited, and add all of its outgoing
     *               edges to the priority queue.
     *
     * =================================================================================
     * GENERIC TEMPLATE (Prim's Algorithm for MST)
     * =================================================================================
     */

    /**
     * Calculates the minimum cost to connect all points (vertices) using Prim's algorithm.
     *
     * @param n The number of points.
     * @param edges The list of edges with their costs, e.g., `[point1, point2, cost]`.
     * @return The minimum cost to connect all points.
     */
    public int minCostToConnectPoints(int n, int[][] edges) {
        if (n <= 1) {
            return 0;
        }

        // --- Step 1: Build Adjacency List ---
        List<List<int[]>> adj = new ArrayList<>();
        for (int i = 0; i < n; i++) {
            adj.add(new ArrayList<>());
        }
        for (int[] edge : edges) {
            int u = edge[0], v = edge[1], cost = edge[2];
            adj.get(u).add(new int[]{v, cost});
            adj.get(v).add(new int[]{u, cost});
        }
        // -------------------------------------

        // --- Core Pattern Logic: Initialization ---
        int totalCost = 0;
        int edgesUsed = 0;
        boolean[] visited = new boolean[n];
        // Min-heap to store edges {vertex, cost}
        PriorityQueue<int[]> pq = new PriorityQueue<>((a, b) -> a[1] - b[1]);

        // Start with an arbitrary vertex (e.g., 0)
        pq.offer(new int[]{0, 0});
        // -----------------------------------------

        // --- Core Pattern Logic: Grow the MST ---
        while (!pq.isEmpty() && edgesUsed < n) {
            int[] current = pq.poll();
            int u = current[0];
            int cost = current[1];

            // If the node is already in our MST, skip it to avoid cycles.
            if (visited[u]) {
                continue;
            }

            // This is a valid edge, add it to the MST.
            visited[u] = true;
            totalCost += cost;
            edgesUsed++;

            // Add all outgoing edges from the newly added vertex to the heap.
            for (int[] neighborEdge : adj.get(u)) {
                int v = neighborEdge[0];
                int neighborCost = neighborEdge[1];
                if (!visited[v]) {
                    pq.offer(new int[]{v, neighborCost});
                }
            }
        }
        // ---------------------------------------

        return totalCost;
    }
}
