package com.framework.patterns.graph_traversal;

import java.util.ArrayList;
import java.util.List;

/**
 * Pattern 20: Graph DFS - Cycle Detection (Directed Graph)
 */
public class GraphDfsCycleDetectionDirected {

    /*
     * =================================================================================
     * CONDITIONS FOR APPLICABILITY
     * =================================================================================
     *
     * 1.  Input Data Structure: A directed graph, typically represented by an adjacency list.
     *
     * 2.  Problem Goal: You need to determine if there is a cycle (a "back edge") in the graph.
     *     This is a critical step before performing operations like Topological Sort.
     *
     * 3.  Logic: The standard way to detect a cycle in a directed graph is to use DFS and maintain
     *     track of the nodes currently in the recursion stack.
     *
     *     - We need three states for each node:
     *         1.  UNVISITED: We haven't processed this node at all.
     *         2.  VISITING: We are currently in the recursion stack for this node (i.e., we are
     *             exploring its descendants). This is the key state.
     *         3.  VISITED: We have finished exploring all descendants of this node.
     *
     *     - The algorithm works as follows:
     *         - Start a DFS from each unvisited node in the graph.
     *         - In the DFS function for a node `u`:
     *             - Mark `u` as VISITING.
     *             - For each neighbor `v` of `u`:
     *                 - If `v` is in the VISITING state, you have found a back edge from `u` to `v`.
     *                   This means a cycle exists. Return `true`.
     *                 - If `v` is UNVISITED, recursively call DFS on `v`. If the recursive call
     *                   returns `true`, propagate `true` up the call stack.
     *             - After exploring all neighbors, mark `u` as VISITED (it is no longer in the
     *               current recursion path).
     *         - If the DFS completes without finding a cycle, return `false`.
     *
     * =================================================================================
     * GENERIC TEMPLATE (Cycle Detection in Directed Graph)
     * =================================================================================
     */

    /**
     * Checks if a directed graph contains a cycle.
     *
     * @param numCourses The total number of nodes (courses).
     * @param prerequisites An array of edges, where [a, b] means b -> a.
     * @return True if a cycle exists, false otherwise.
     */
    public boolean canFinish(int numCourses, int[][] prerequisites) {
        // Build adjacency list representation of the graph
        List<List<Integer>> adj = new ArrayList<>(numCourses);
        for (int i = 0; i < numCourses; i++) {
            adj.add(new ArrayList<>());
        }
        for (int[] prereq : prerequisites) {
            adj.get(prereq[1]).add(prereq[0]);
        }

        // 0 = UNVISITED, 1 = VISITING, 2 = VISITED
        int[] visited = new int[numCourses];

        // --- Core Pattern Logic: Call DFS for each node ---
        for (int i = 0; i < numCourses; i++) {
            if (visited[i] == 0) { // If unvisited
                if (hasCycle(i, adj, visited)) {
                    return false; // Cycle detected, so cannot finish
                }
            }
        }
        // --------------------------------------------------

        return true; // No cycle detected
    }

    /**
     * Recursive DFS helper to detect a cycle.
     * @param u The current node.
     * @param adj The adjacency list.
     * @param visited The state array for each node.
     * @return True if a cycle is detected starting from this path, false otherwise.
     */
    private boolean hasCycle(int u, List<List<Integer>> adj, int[] visited) {
        // --- Core Pattern Logic: Mark current node as being visited ---
        visited[u] = 1; // 1 means VISITING
        // ------------------------------------------------------------

        for (int v : adj.get(u)) {
            // --- Core Pattern Logic: Check for back edge ---
            if (visited[v] == 1) {
                // Found a neighbor that is currently in our recursion stack.
                return true; // CYCLE DETECTED
            }
            // ----------------------------------------------

            if (visited[v] == 0) { // If the neighbor is unvisited
                if (hasCycle(v, adj, visited)) {
                    return true; // Propagate cycle detection result
                }
            }
            // If visited[v] == 2, it means we have already processed that node and found
            // no cycles from it, so we can safely ignore it.
        }

        // --- Core Pattern Logic: Mark current node as fully visited ---
        visited[u] = 2; // 2 means VISITED
        // -----------------------------------------------------------

        return false; // No cycle found from this path
    }
}
