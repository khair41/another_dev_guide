package com.framework.patterns.graph_traversal;

import java.util.PriorityQueue;
import java.util.HashMap;
import java.util.Map;

/**
 * Advanced Graph Pattern: A* Search Algorithm
 */
public class GraphShortestPathAStar {

    /*
     * =================================================================================
     * CONDITIONS FOR APPLICABILITY
     * =================================================================================
     *
     * 1.  Input Data Structure: A weighted graph, often represented as a grid or a set of nodes with connections.
     *
     * 2.  Problem Goal: You need to find the shortest path from a single source to a single target node.
     *
     * 3.  Key Feature: You must have a **heuristic function**, `h(n)`, which estimates the cost from a node `n`
     *     to the target. A* is an **informed search** algorithm because this heuristic "guides" it towards the goal.
     *
     * 4.  Logic: A* is essentially an extension of Dijkstra's algorithm. It uses a priority queue to explore the
     *     most promising node, but its priority is determined by a combination of two factors:
     *
     *     -   `g(n)`: The exact, known cost of the path from the start node to the current node `n`. (This is what Dijkstra uses).
     *     -   `h(n)`: The estimated (heuristic) cost from the current node `n` to the target node.
     *     -   `f(n) = g(n) + h(n)`: The total estimated cost of the best path that goes through `n`.
     *
     *     The algorithm always picks the node with the lowest `f(n)` value from the priority queue.
     *
     * 5.  Admissible Heuristic: For A* to be guaranteed to find the shortest path, the heuristic `h(n)` must be
     *     **admissible**, meaning it must *never overestimate* the actual cost to reach the target. For grid problems,
     *     the Manhattan distance (`|x1-x2| + |y1-y2|`) is a common and admissible heuristic.
     *
     * =================================================================================
     * GENERIC TEMPLATE (A* on a 2D Grid)
     * =================================================================================
     */

    // Helper class to store node information
    static class Node implements Comparable<Node> {
        int r, c;
        int g; // Cost from start to this node
        int h; // Heuristic cost from this node to target
        int f; // f = g + h

        public Node(int r, int c) {
            this.r = r;
            this.c = c;
        }

        @Override
        public int compareTo(Node other) {
            return Integer.compare(this.f, other.f);
        }
        
        @Override
        public int hashCode() { return r * 31 + c; } // Simple hash for grid cells
        
        @Override
        public boolean equals(Object obj) {
            if (this == obj) return true;
            if (obj == null || getClass() != obj.getClass()) return false;
            Node node = (Node) obj;
            return r == node.r && c == node.c;
        }
    }

    /**
     * Finds the shortest path on a grid using A*.
     */
    public int shortestPath(int[][] grid, int[] start, int[] end) {
        int rows = grid.length;
        int cols = grid[0].length;

        Node startNode = new Node(start[0], start[1]);
        Node endNode = new Node(end[0], end[1]);

        PriorityQueue<Node> openSet = new PriorityQueue<>();
        Map<Node, Integer> gScore = new HashMap<>();

        // Initialize
        startNode.g = 0;
        startNode.h = heuristic(startNode, endNode);
        startNode.f = startNode.g + startNode.h;
        
        openSet.add(startNode);
        gScore.put(startNode, 0);

        int[][] directions = {{0, 1}, {0, -1}, {1, 0}, {-1, 0}};

        while (!openSet.isEmpty()) {
            Node current = openSet.poll();

            if (current.equals(endNode)) {
                return current.g; // Path found
            }

            for (int[] dir : directions) {
                int nr = current.r + dir[0];
                int nc = current.c + dir[1];

                if (nr < 0 || nr >= rows || nc < 0 || nc >= cols || grid[nr][nc] == 1) { // 1 is an obstacle
                    continue;
                }

                Node neighbor = new Node(nr, nc);
                int tentativeGScore = current.g + 1; // Assuming cost of 1 for each step

                if (tentativeGScore < gScore.getOrDefault(neighbor, Integer.MAX_VALUE)) {
                    gScore.put(neighbor, tentativeGScore);
                    neighbor.g = tentativeGScore;
                    neighbor.h = heuristic(neighbor, endNode);
                    neighbor.f = neighbor.g + neighbor.h;
                    openSet.add(neighbor);
                }
            }
        }

        return -1; // Path not found
    }

    /**
     * Admissible heuristic function (Manhattan distance).
     */
    private int heuristic(Node a, Node b) {
        return Math.abs(a.r - b.r) + Math.abs(a.c - b.c);
    }
}
