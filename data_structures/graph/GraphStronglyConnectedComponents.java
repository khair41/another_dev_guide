package com.framework.patterns.graph_traversal;

import java.util.ArrayList;
import java.util.List;
import java.util.Stack;

/**
 * Pattern 26: Strongly Connected Components (Kosaraju / Tarjan)
 */
public class GraphStronglyConnectedComponents {

    /*
     * =================================================================================
     * CONDITIONS FOR APPLICABILITY
     * =================================================================================
     *
     * 1.  Input Data Structure: A directed graph.
     *
     * 2.  Problem Goal: You need to partition the graph's nodes into its Strongly Connected
     *     Components (SCCs). An SCC is a subgraph where for any two vertices `u` and `v` in the
     *     subgraph, there is a path from `u` to `v` and a path from `v` to `u`.
     *
     * 3.  Logic (Tarjan's Algorithm): This is a DFS-based algorithm that finds SCCs in a single pass.
     *
     *     -   It maintains two arrays for each node `u`: `disc[u]` (the discovery time of `u` during DFS)
     *         and `low[u]` (the lowest discovery time reachable from `u`, including itself).
     *     -   A global timer is used to assign discovery times.
     *     -   A stack is used to keep track of the nodes in the current exploration path.
     *     -   When performing DFS from a node `u`:
     *         -   Set `disc[u]` and `low[u]` to the current timer value.
     *         -   Push `u` onto the stack.
     *         -   For each neighbor `v` of `u`:
     *             -   If `v` is unvisited, recursively call DFS on `v`. Afterwards, update `low[u] = min(low[u], low[v])`.
     *             -   If `v` is visited and is on the stack, it means we found a back edge. Update `low[u] = min(low[u], disc[v])`.
     *         -   After visiting all neighbors, if `low[u] == disc[u]`, it means `u` is the root of an SCC.
     *           Pop nodes from the stack until `u` is popped. All popped nodes form one SCC.
     *
     * =================================================================================
     * GENERIC TEMPLATE (Tarjan's Algorithm for SCCs)
     * =================================================================================
     */

    private int timer;
    private List<List<Integer>> sccs;
    private int[] disc;
    private int[] low;
    private boolean[] onStack;
    private Stack<Integer> stack;
    private List<List<Integer>> adj;

    /**
     * Finds all strongly connected components in a directed graph.
     *
     * @param n The number of nodes.
     * @param connections The list of directed edges.
     * @return A list of lists, where each inner list is an SCC.
     */
    public List<List<Integer>> findSccs(int n, List<List<Integer>> connections) {
        // Initialization
        adj = new ArrayList<>();
        for (int i = 0; i < n; i++) adj.add(new ArrayList<>());
        for (List<Integer> conn : connections) adj.get(conn.get(0)).add(conn.get(1));

        timer = 0;
        sccs = new ArrayList<>();
        disc = new int[n];
        low = new int[n];
        onStack = new boolean[n];
        stack = new Stack<>();
        Arrays.fill(disc, -1); // -1 indicates unvisited

        // --- Core Pattern Logic: Start DFS from each unvisited node ---
        for (int i = 0; i < n; i++) {
            if (disc[i] == -1) {
                dfs(i);
            }
        }
        // -------------------------------------------------------------

        return sccs;
    }

    private void dfs(int u) {
        // --- Core Pattern Logic: Initialize node state ---
        disc[u] = low[u] = timer++;
        stack.push(u);
        onStack[u] = true;
        // ------------------------------------------------

        // --- Core Pattern Logic: Explore neighbors ---
        for (int v : adj.get(u)) {
            if (disc[v] == -1) { // If neighbor is unvisited
                dfs(v);
                // Update low-link value from the callback
                low[u] = Math.min(low[u], low[v]);
            } else if (onStack[v]) {
                // If neighbor is on the stack, it's a back edge
                low[u] = Math.min(low[u], disc[v]);
            }
        }
        // ---------------------------------------------

        // --- Core Pattern Logic: Check if u is the root of an SCC ---
        if (low[u] == disc[u]) {
            List<Integer> scc = new ArrayList<>();
            while (true) {
                int node = stack.pop();
                onStack[node] = false;
                scc.add(node);
                if (node == u) break;
            }
            sccs.add(scc);
        }
        // -----------------------------------------------------------
    }
}
