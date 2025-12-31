package com.framework.patterns.graph_traversal;

import java.util.Arrays;
import java.util.Comparator;

/**
 * Pattern 28: Minimum Spanning Tree (Kruskal / Prim)
 */
public class GraphMinimumSpanningTree {

    /*
     * =================================================================================
     * CONDITIONS FOR APPLICABILITY
     * =================================================================================
     *
     * 1.  Input Data Structure: A weighted, undirected, connected graph.
     *
     * 2.  Problem Goal: You need to find a subgraph that connects all the vertices together,
     *     without any cycles and with the minimum possible total edge weight. This subgraph
     *     is called a Minimum Spanning Tree (MST).
     *
     * 3.  Logic (Kruskal's Algorithm): This is a greedy algorithm that works as follows:
     *
     *     -   **Step 1**: Create a list of all edges in the graph.
     *     -   **Step 2**: Sort all the edges in non-decreasing order of their weights.
     *     -   **Step 3**: Initialize a Disjoint Set Union (DSU) data structure with one set for each vertex.
     *     -   **Step 4**: Iterate through the sorted edges. For each edge `(u, v)` with weight `w`:
     *         -   Check if `u` and `v` are already in the same set using the DSU's `find` operation.
     *         -   If they are in different sets, it means adding this edge will not form a cycle.
     *         -   Add the edge to the MST (e.g., add its weight to a running total) and merge the
     *             sets of `u` and `v` using the DSU's `union` operation.
     *     -   The algorithm stops when `V-1` edges have been added to the MST (where `V` is the number of vertices).
     *
     * =================================================================================
     * GENERIC TEMPLATE (Kruskal's Algorithm for MST)
     * =================================================================================
     */

    // DSU (Union-Find) implementation needed for Kruskal's
    static class DSU {
        private int[] parent;
        public DSU(int n) {
            parent = new int[n];
            for (int i = 0; i < n; i++) parent[i] = i;
        }
        public int find(int i) {
            if (parent[i] == i) return i;
            return parent[i] = find(parent[i]);
        }
        public boolean union(int i, int j) {
            int rootI = find(i);
            int rootJ = find(j);
            if (rootI != rootJ) {
                parent[rootI] = rootJ;
                return true;
            }
            return false;
        }
    }

    /**
     * Calculates the minimum cost to connect all points (vertices).
     *
     * @param n The number of points.
     * @param edges The list of edges with their costs, e.g., `[point1, point2, cost]`.
     * @return The minimum cost to connect all points.
     */
    public int minCostToConnectPoints(int n, int[][] edges) {
        if (n <= 1) {
            return 0;
        }

        // --- Core Pattern Logic: Step 1 & 2 - Sort all edges by weight ---
        Arrays.sort(edges, Comparator.comparingInt(a -> a[2]));
        // ----------------------------------------------------------------

        // --- Core Pattern Logic: Step 3 - Initialize DSU ---
        DSU dsu = new DSU(n);
        // --------------------------------------------------

        int totalCost = 0;
        int edgesUsed = 0;

        // --- Core Pattern Logic: Step 4 - Iterate through sorted edges ---
        for (int[] edge : edges) {
            int u = edge[0];
            int v = edge[1];
            int cost = edge[2];

            // --- Problem-Specific Logic: Check for cycle and unite ---
            if (dsu.union(u, v)) {
                // If union is successful, the edge does not form a cycle.
                totalCost += cost;
                edgesUsed++;
                // Optimization: stop when we have V-1 edges.
                if (edgesUsed == n - 1) {
                    break;
                }
            }
            // ---------------------------------------------------------
        }

        return totalCost;
    }
}
