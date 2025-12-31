package com.framework.patterns.graph_traversal;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

/**
 * Pattern 27: Bridges & Articulation Points (Tarjan low-link)
 */
public class GraphBridgesAndArticulationPoints {

    /*
     * =================================================================================
     * CONDITIONS FOR APPLICABILITY
     * =================================================================================
     *
     * 1.  Input Data Structure: An UNDIRECTED graph.
     *
     * 2.  Problem Goal: You need to find critical connections in the graph.
     *     -   **Bridges (or Cut Edges)**: Edges whose removal would increase the number of
     *         connected components in the graph.
     *     -   **Articulation Points (or Cut Vertices)**: Vertices whose removal (and all edges
     *         connected to them) would increase the number of connected components.
     *
     * 3.  Logic (Tarjan's Bridge-Finding Algorithm): This is a DFS-based algorithm that uses
     *     discovery times and low-link values, similar to the SCC algorithm but for undirected graphs.
     *
     *     -   Maintain `disc[u]` (discovery time of `u`) and `low[u]` (lowest discovery time
     *         reachable from `u`).
     *     -   Perform a DFS, keeping track of the parent of the current node in the DFS tree to avoid
     *         immediately going back on the same edge.
     *     -   For each node `u`, and for each neighbor `v`:
     *         -   If `v` is the parent, ignore it.
     *         -   If `v` is unvisited, recursively call DFS on `v`. After the call, update
     *           `low[u] = min(low[u], low[v])`.
     *             -   **Bridge Condition**: If `low[v] > disc[u]`, the edge `(u, v)` is a bridge.
     *               This means there is no back edge from `v` or its descendants to `u` or any of its ancestors.
     *             -   **Articulation Point Condition**: If `low[v] >= disc[u]` (and `u` is not the root),
     *               `u` is an articulation point.
     *         -   If `v` is already visited, it means we found a back edge. Update `low[u] = min(low[u], disc[v])`.
     *
     * =================================================================================
     * GENERIC TEMPLATE (Finding Bridges in an Undirected Graph)
     * =================================================================================
     */

    private int timer;
    private int[] disc;
    private int[] low;
    private int[] parent;
    private List<List<Integer>> adj;
    private List<List<Integer>> bridges;

    /**
     * Finds all bridges in an undirected graph.
     *
     * @param n The number of nodes.
     * @param connections The list of undirected edges.
     * @return A list of bridges, where each bridge is a list of two nodes.
     */
    public List<List<Integer>> findBridges(int n, List<List<Integer>> connections) {
        // Initialization
        adj = new ArrayList<>();
        for (int i = 0; i < n; i++) adj.add(new ArrayList<>());
        for (List<Integer> conn : connections) {
            adj.get(conn.get(0)).add(conn.get(1));
            adj.get(conn.get(1)).add(conn.get(0));
        }

        timer = 0;
        bridges = new ArrayList<>();
        disc = new int[n];
        low = new int[n];
        parent = new int[n];
        Arrays.fill(disc, -1);
        Arrays.fill(parent, -1);

        // Start DFS from each unvisited node
        for (int i = 0; i < n; i++) {
            if (disc[i] == -1) {
                dfs(i);
            }
        }

        return bridges;
    }

    private void dfs(int u) {
        disc[u] = low[u] = timer++;

        for (int v : adj.get(u)) {
            if (v == parent[u]) {
                continue; // Don't go back to the direct parent
            }

            if (disc[v] != -1) { // If v is visited (and not parent), it's a back edge
                low[u] = Math.min(low[u], disc[v]);
            } else { // If v is not visited, it's a tree edge
                parent[v] = u;
                dfs(v);

                // After the DFS call returns, update low-link of u
                low[u] = Math.min(low[u], low[v]);

                // --- Core Pattern Logic: Bridge Condition ---
                if (low[v] > disc[u]) {
                    bridges.add(Arrays.asList(u, v));
                }
                // -------------------------------------------
            }
        }
    }
}
