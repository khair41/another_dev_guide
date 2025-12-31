package com.framework.patterns.graph_traversal;

import java.util.HashSet;
import java.util.LinkedList;
import java.util.Queue;
import java.util.Set;

/**
 * Pattern 29: Bidirectional BFS
 */
public class GraphBidirectionalBfs {

    /*
     * =================================================================================
     * CONDITIONS FOR APPLICABILITY
     * =================================================================================
     *
     * 1.  Input Data Structure: An unweighted graph (or a grid).
     *
     * 2.  Problem Goal: You need to find the shortest path between a known `start` node and a
     *     known `target` node.
     *
     * 3.  Logic: A standard BFS explores outwards from the `start` node until it finds the `target`.
     *     A Bidirectional BFS improves on this by running two simultaneous BFS searches: one starting
     *     from the `start` node (the "forward" search) and one starting from the `target` node
     *     (the "backward" search). The search stops when a node from one search is discovered by
     *     the other search. This is often much faster because the search area grows as the radius squared
     *     (πr²), and two smaller searches are much more efficient than one large one.
     *
     *     -   Initialize two queues, `q_start` and `q_end`, with the start and target nodes.
     *     -   Initialize two `visited` sets, `visited_start` and `visited_end`.
     *     -   Keep track of the level or distance for each search.
     *     -   In each step of the main loop, expand the smaller of the two queues to keep the search
     *         fronts roughly the same size.
     *     -   When expanding a level from one queue (e.g., `q_start`):
     *         -   For each dequeued node, explore its neighbors.
     *         -   If a neighbor has already been visited by the *other* search (`visited_end`), a path
     *           has been found. The total path length is the sum of the levels from both searches.
     *         -   If a neighbor has not been visited by the current search (`visited_start`), add it to
     *           the queue and the visited set.
     *
     * =================================================================================
     * GENERIC TEMPLATE (Shortest Path in a Grid)
     * =================================================================================
     */

    /**
     * Finds the shortest path from a start to a target in a grid.
     * This example is simplified; a full implementation would handle word transformations or similar problems.
     *
     * @param startNode The starting node.
     * @param endNode The ending node.
     * @param graph A representation of the graph, e.g., an adjacency list.
     * @return The length of the shortest path.
     */
    public int shortestPath(int startNode, int endNode, java.util.Map<Integer, java.util.List<Integer>> graph) {
        if (startNode == endNode) return 1;

        Queue<Integer> q_start = new LinkedList<>();
        Queue<Integer> q_end = new LinkedList<>();
        Set<Integer> visited_start = new HashSet<>();
        Set<Integer> visited_end = new HashSet<>();

        q_start.offer(startNode);
        visited_start.add(startNode);
        q_end.offer(endNode);
        visited_end.add(endNode);

        int dist = 1;

        while (!q_start.isEmpty() && !q_end.isEmpty()) {
            // Always expand the smaller queue for efficiency
            if (q_start.size() > q_end.size()) {
                Queue<Integer> tempQ = q_start;
                q_start = q_end;
                q_end = tempQ;

                Set<Integer> tempV = visited_start;
                visited_start = visited_end;
                visited_end = tempV;
            }

            int levelSize = q_start.size();
            for (int i = 0; i < levelSize; i++) {
                int u = q_start.poll();

                for (int v : graph.getOrDefault(u, new java.util.ArrayList<>())) {
                    // --- Core Pattern Logic: Check for intersection ---
                    if (visited_end.contains(v)) {
                        return dist + 1;
                    }
                    // --------------------------------------------------

                    if (!visited_start.contains(v)) {
                        visited_start.add(v);
                        q_start.offer(v);
                    }
                }
            }
            dist++;
        }

        return -1; // Path not found
    }
}
