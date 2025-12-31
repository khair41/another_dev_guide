package com.framework.patterns.graph_traversal;

/**
 * Advanced Graph Data Structure: Union-Find (Disjoint Set Union - DSU)
 */
public class GraphDataStructureUnionFind {

    /*
     * =================================================================================
     * PURPOSE & APPLICABILITY
     * =================================================================================
     *
     * This data structure is used to keep track of a partition of a set of elements into a
     * number of disjoint (non-overlapping) subsets. It is extremely efficient for problems
     * involving connected components in graphs.
     *
     * Key Operations:
     * - `find(i)`: Determines the representative (or root) of the set that element `i` belongs to.
     * - `union(i, j)`: Merges the two sets containing elements `i` and `j`.
     *
     * Common Use Cases:
     * - Kruskal's algorithm for Minimum Spanning Trees.
     * - Detecting cycles in an undirected graph.
     * - Finding the number of connected components.
     * - Checking if two nodes are in the same component (network connectivity).
     *
     * =================================================================================
     * CORE LOGIC & OPTIMIZATIONS
     * =================================================================================
     *
     * The DSU is implemented as a forest (a collection of trees), where each tree represents a set.
     * An array `parent` is used, where `parent[i]` stores the parent of element `i`.
     * The root of a tree is the representative of its set (`parent[root] == root`).
     *
     * To make operations fast, two key optimizations are used:
     *
     * 1.  **Path Compression (in `find` operation)**:
     *     When finding the root of an element, this optimization flattens the tree structure by making
     *     every node on the path point directly to the root. This dramatically speeds up future `find`
     *     operations for any element in that path.
     *
     * 2.  **Union by Rank/Size (in `union` operation)**:
     *     When merging two trees, this optimization avoids creating tall, skinny trees (which would be slow
     *     to traverse). It attaches the smaller tree to the root of the larger tree.
     *     -   *Union by Rank*: The `rank` is a proxy for the height of the tree. Attach the shorter tree to the taller one.
     *     -   *Union by Size*: The `size` is the number of elements in the tree. Attach the smaller tree to the larger one.
     *
     * =================================================================================
     * TIME COMPLEXITY (THE INVERSE ACKERMANN FUNCTION)
     * =================================================================================
     *
     * When both Path Compression and Union by Rank/Size are used, the amortized time complexity
     * for both `find` and `union` operations is nearly constant, specifically O(α(n)), where α(n)
     * is the **Inverse Ackermann function**.
     *
     * The Ackermann function grows incredibly quickly, so its inverse, α(n), grows incredibly slowly.
     * For any practical value of `n` (e.g., more than the number of atoms in the universe), α(n) is less than 5.
     * Therefore, for all intents and purposes in software engineering, the Union-Find data structure
     * provides **amortized constant-time** operations.
     *
     * =================================================================================
     * GENERIC TEMPLATE (DSU with Path Compression and Union by Rank)
     * =================================================================================
     */

    private int[] parent;
    private int[] rank;   // Use `rank` for Union by Rank, or `size` for Union by Size
    private int count;    // Tracks the number of disjoint sets

    /**
     * Initializes the DSU with `n` elements, each in its own set.
     */
    public GraphDataStructureUnionFind(int n) {
        parent = new int[n];
        rank = new int[n];
        count = n;
        for (int i = 0; i < n; i++) {
            parent[i] = i;
            rank[i] = 0;
        }
    }

    /**
     * Finds the representative of the set containing `i` with path compression.
     */
    public int find(int i) {
        if (parent[i] != i) {
            parent[i] = find(parent[i]); // Path compression
        }
        return parent[i];
    }

    /**
     * Merges the sets containing `i` and `j` using union by rank.
     * @return `true` if the sets were merged, `false` if they were already in the same set.
     */
    public boolean union(int i, int j) {
        int rootI = find(i);
        int rootJ = find(j);

        if (rootI == rootJ) {
            return false; // Already in the same set
        }

        // Union by Rank
        if (rank[rootI] < rank[rootJ]) {
            parent[rootI] = rootJ;
        } else if (rank[rootI] > rank[rootJ]) {
            parent[rootJ] = rootI;
        } else {
            parent[rootJ] = rootI;
            rank[rootI]++;
        }

        count--; // One set has been merged into another
        return true;
    }

    /**
     * Returns the number of disjoint sets.
     */
    public int getCount() {
        return count;
    }

    /**
     * Checks if two elements are in the same set.
     */
    public boolean isConnected(int i, int j) {
        return find(i) == find(j);
    }
}
