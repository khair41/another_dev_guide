package com.framework.patterns.graph_traversal;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;
import java.util.Queue;

/**
 * Pattern 21: Graph BFS - Topological Sort (Kahn's Algorithm)
 */
public class GraphBfsTopologicalSort {

    /*
     * =================================================================================
     * CONDITIONS FOR APPLICABILITY
     * =================================================================================
     *
     * 1.  Input Data Structure: A Directed Acyclic Graph (DAG). If the graph is not directed
     *     or if it contains a cycle, topological sort is not possible.
     *
     * 2.  Problem Goal: You need to find a linear ordering of the graph's nodes such that for
     *     every directed edge from node `u` to node `v`, `u` comes before `v` in the ordering.
     *     This is common in task scheduling problems where some tasks must be completed before others.
     *
     * 3.  Logic (Kahn's Algorithm): This is a BFS-based approach.
     *
     *     -   **Step 1: Initialization**
     *         -   Build an adjacency list for the graph.
     *         -   Create an `inDegree` map or array to count how many incoming edges each node has.
     *
     *     -   **Step 2: Find Sources**
     *         -   Initialize a queue with all nodes that have an in-degree of 0. These are the "source" nodes
     *             that have no prerequisites.
     *
     *     -   **Step 3: Traversal**
     *         -   Loop while the queue is not empty.
     *         -   Dequeue a node `u`. This node is the next one in our sorted order, so add it to the result list.
     *         -   For each neighbor `v` of the dequeued node `u`:
     *             -   Decrement the in-degree of `v` (since we have now "completed" task `u`).
     *             -   If the in-degree of `v` becomes 0, it means all its prerequisites are met.
     *                 Add `v` to the queue.
     *
     *     -   **Step 4: Validation**
     *         -   After the loop, if the number of nodes in the result list is not equal to the total
     *             number of nodes in the graph, it means there was a cycle.
     *
     * =================================================================================
     * GENERIC TEMPLATE (Course Schedule using Kahn's Algorithm)
     * =================================================================================
     */

    /**
     * Finds a valid order to take courses given the prerequisites.
     *
     * @param numCourses The total number of nodes (courses).
     * @param prerequisites An array of edges, where [a, b] means b -> a.
     * @return An array representing a valid topological sort, or an empty array if a cycle exists.
     */
    public int[] findOrder(int numCourses, int[][] prerequisites) {
        // --- Core Pattern Logic: Step 1 - Initialization ---
        Map<Integer, List<Integer>> adj = new HashMap<>();
        Map<Integer, Integer> inDegree = new HashMap<>();

        for (int i = 0; i < numCourses; i++) {
            adj.put(i, new ArrayList<>());
            inDegree.put(i, 0);
        }

        for (int[] prereq : prerequisites) {
            int parent = prereq[1];
            int child = prereq[0];
            adj.get(parent).add(child);
            inDegree.put(child, inDegree.get(child) + 1);
        }
        // ---------------------------------------------------

        // --- Core Pattern Logic: Step 2 - Find Sources ---
        Queue<Integer> sources = new LinkedList<>();
        for (Map.Entry<Integer, Integer> entry : inDegree.entrySet()) {
            if (entry.getValue() == 0) {
                sources.offer(entry.getKey());
            }
        }
        // -------------------------------------------------

        List<Integer> sortedOrder = new ArrayList<>();

        // --- Core Pattern Logic: Step 3 - Traversal ---
        while (!sources.isEmpty()) {
            int u = sources.poll();
            sortedOrder.add(u);

            for (int v : adj.get(u)) {
                inDegree.put(v, inDegree.get(v) - 1);
                if (inDegree.get(v) == 0) {
                    sources.offer(v);
                }
            }
        }
        // -----------------------------------------------

        // --- Core Pattern Logic: Step 4 - Validation ---
        if (sortedOrder.size() != numCourses) {
            return new int[0]; // Cycle detected
        }
        // -----------------------------------------------

        return sortedOrder.stream().mapToInt(i -> i).toArray();
    }
}
