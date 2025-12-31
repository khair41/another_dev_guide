package com.framework.patterns.graph_traversal;

/**
 * Pattern 25: Graph - Union-Find (Disjoint Set Union - DSU)
 */
public class GraphUnionFind {

    /*
     * =================================================================================
     * CONDITIONS FOR APPLICABILITY
     * =================================================================================
     *
     * 1.  Input Data Structure: A set of elements that can be partitioned into a number of
     *     disjoint (non-overlapping) sets.
     *
     * 2.  Problem Goal: You need to efficiently perform two operations:
     *     -   **Find (or `findSet`)**: Determine which set a particular element belongs to.
     *     -   **Union (or `uniteSets`)**: Merge two sets into a single set.
     *     This is useful for problems like checking if a graph has a cycle (in an undirected graph),
     *     finding the number of connected components, or checking if two nodes are connected.
     *
     * 3.  Logic: The DSU data structure is typically implemented with an array, `parent`.
     *
     *     -   **`parent` array**: `parent[i]` stores the parent of element `i`. If `parent[i] == i`,
     *         then `i` is the "representative" (or root) of its set.
     *     -   **`find(i)` operation**: To find the set of `i`, you follow the parent pointers from `i`
     *         until you reach a root (an element whose parent is itself). To make this fast,
     *         we use **Path Compression**: after finding the root, we make all nodes on the path
     *         point directly to the root. This flattens the tree for future lookups.
     *     -   **`union(i, j)` operation**: To merge the sets containing `i` and `j`, you first find
     *         the roots of their respective sets, say `rootI` and `rootJ`. If they are not already
     *         in the same set (`rootI != rootJ`), you make one root the parent of the other.
     *         To keep the trees flat, we use **Union by Rank/Size**: attach the smaller tree to the
     *         root of the larger tree.
     *
     * =================================================================================
     * GENERIC TEMPLATE (DSU with Path Compression and Union by Rank)
     * =================================================================================
     */

    private int[] parent;
    private int[] rank; // Used for Union by Rank
    private int count;  // Can be used to track number of disjoint sets

    /**
     * Initializes the DSU data structure.
     * @param size The total number of elements.
     */
    public void initialize(int size) {
        parent = new int[size];
        rank = new int[size];
        count = size;
        for (int i = 0; i < size; i++) {
            parent[i] = i; // Initially, each element is its own parent.
            rank[i] = 0;   // Initially, all ranks are 0.
        }
    }

    /**
     * Finds the representative (root) of the set containing element `i`, with path compression.
     * @param i The element to find.
     * @return The representative of the set.
     */
    public int find(int i) {
        // --- Core Pattern Logic: Path Compression ---
        if (parent[i] != i) {
            parent[i] = find(parent[i]); // Recursively find the root and update the parent.
        }
        return parent[i];
        // -------------------------------------------
    }

    /**
     * Merges the sets containing elements `i` and `j`, using union by rank.
     * @param i An element in the first set.
     * @param j An element in the second set.
     * @return True if the sets were merged, false if they were already in the same set.
     */
    public boolean union(int i, int j) {
        // --- Core Pattern Logic: Find roots of both elements ---
        int rootI = find(i);
        int rootJ = find(j);
        // ------------------------------------------------------

        if (rootI != rootJ) {
            // --- Core Pattern Logic: Union by Rank ---
            if (rank[rootI] > rank[rootJ]) {
                parent[rootJ] = rootI;
            } else if (rank[rootI] < rank[rootJ]) {
                parent[rootI] = rootJ;
            } else {
                parent[rootJ] = rootI;
                rank[rootI]++; // If ranks are the same, increment one.
            }
            // -----------------------------------------
            count--; // Decrement the number of disjoint sets.
            return true;
        }

        return false; // Elements were already in the same set.
    }

    /**
     * Returns the number of disjoint sets.
     */
    public int getCount() {
        return count;
    }
}
