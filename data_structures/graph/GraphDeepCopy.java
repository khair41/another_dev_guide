package com.framework.patterns.graph_traversal;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * Pattern 22: Graph - Deep Copy / Cloning
 */
public class GraphDeepCopy {

    // Definition for a graph node.
    static class Node {
        public int val;
        public List<Node> neighbors;
        public Node(int _val) {
            val = _val;
            neighbors = new ArrayList<Node>();
        }
    }

    /*
     * =================================================================================
     * CONDITIONS FOR APPLICABILITY
     * =================================================================================
     *
     * 1.  Input Data Structure: A graph, which could be directed or undirected and may contain cycles.
     *
     * 2.  Problem Goal: You need to create a complete, independent copy of the graph. This means
     *     creating a new node for every original node, and ensuring that the new nodes are connected
     *     in exactly the same way as the original nodes. The copy must not share any nodes with
     *     the original graph.
     *
     * 3.  Logic: The main challenge is handling cycles and ensuring that each original node maps to
     *     exactly one new node. A hash map is essential for this.
     *
     *     -   Create a `Map<Node, Node>` (or `Map<Integer, Node>`) to store the mapping from an
     *         original node to its corresponding newly created copy.
     *     -   Use a traversal algorithm (DFS or BFS) to visit every node in the original graph.
     *     -   The recursive DFS function will take an original node as input and return its copy.
     *     -   Inside the DFS function for a node `u`:
     *         -   If `u` is already in the map, it means we have already created its copy. Return
     *             the copy from the map to avoid infinite loops in a cycle.
     *         -   If `u` is not in the map, create a new node `u_copy`.
     *         -   Add the mapping `(u, u_copy)` to the map. **This must be done before traversing neighbors**
     *             to handle cycles correctly.
     *         -   Iterate through each neighbor `v` of the original node `u`.
     *         -   Make a recursive call `clone(v)` to get the copy of the neighbor, `v_copy`.
     *         -   Add `v_copy` to the neighbors list of `u_copy`.
     *     -   Return `u_copy`.
     *
     * =================================================================================
     * GENERIC TEMPLATE (Clone Graph using DFS)
     * =================================================================================
     */

    // Map to store the mapping from original node to its clone.
    private Map<Node, Node> visited = new HashMap<>();

    /**
     * Clones a graph.
     *
     * @param node The starting node of the graph to clone.
     * @return The corresponding node in the cloned graph.
     */
    public Node cloneGraph(Node node) {
        if (node == null) {
            return null;
        }

        // --- Core Pattern Logic: Check if we have already cloned this node ---
        // If so, return the clone from the map to avoid cycles.
        if (visited.containsKey(node)) {
            return visited.get(node);
        }
        // ------------------------------------------------------------------

        // --- Problem-Specific Logic: Create the clone and map it ---
        // Create the new node.
        Node cloneNode = new Node(node.val);
        // Add it to the visited map. THIS MUST BE DONE BEFORE THE RECURSIVE CALLS.
        visited.put(node, cloneNode);
        // ----------------------------------------------------------

        // --- Core Pattern Logic: Traverse neighbors ---
        // Iterate through the original node's neighbors.
        for (Node neighbor : node.neighbors) {
            // Recursively call cloneGraph on the neighbors and add the result to the clone's neighbors list.
            cloneNode.neighbors.add(cloneGraph(neighbor));
        }
        // ---------------------------------------------

        return cloneNode;
    }
}
