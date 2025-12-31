# Pattern: Union-Find (Disjoint Set Union)

This guide provides a detailed explanation and a reusable code template for the Union-Find data structure. It is a specialized data structure that is highly optimized for tracking the connectivity of elements within a set.

---

## 🎯 Core Concept

*   **What is it?** The Union-Find data structure tracks a set of elements partitioned into a number of disjoint (non-overlapping) subsets. Think of it as managing social circles in a large group of people. You can quickly determine if two people are in the same circle, or merge two circles into one.

*   **Why is it useful?** It provides a nearly constant-time (`O(α(n))`, where `α(n)` is the Inverse Ackermann function) method for adding new sets, merging sets, and finding the representative member of a set. This is exceptionally fast.

---

## 🤔 When to Use This Pattern

Look for these key indicators in a problem description:

*   The problem involves **grouping** or **partitioning** items into disjoint sets.
*   You need to efficiently check if two elements are **connected** (directly or indirectly).
*   The problem involves finding **redundant connections** or **cycles** in an undirected graph.
*   You are building up a graph or a network component by component (e.g., Kruskal's algorithm for Minimum Spanning Trees).

---

## ⚙️ The Core Operations

There are two primary operations, which give the data structure its name:

1.  **`find(i)`**: Determines the representative (or "root") of the set to which element `i` belongs. If two elements have the same root, they are in the same set.
2.  **`union(i, j)`**: Merges the two sets containing elements `i` and `j` into a single set.

A third, helper operation is often exposed:

*   **`connected(i, j)`**: Returns `true` if elements `i` and `j` are in the same set, and `false` otherwise. This is simply implemented as `find(i) == find(j)`.

---

## 💡 Implementation: The Optimized Template

The most efficient implementation of Union-Find uses two key optimizations: **Path Compression** and **Union by Rank/Size**. Without these, the performance can degrade to O(n) for the `find` operation in the worst case.

### The Data Structure

We use two arrays:
*   `parent[]`: An array where `parent[i]` stores the parent of element `i`. If `parent[i] == i`, then `i` is the root of its set.
*   `rank[]` or `size[]`: An array to store the "rank" (a rough measure of tree depth) or the "size" of the set rooted at `i`. This is used to keep the trees flat during unions.

### The Code Template

This template implements both optimizations and is the standard for competitive programming.

```java
class UnionFind {
    private int[] parent;
    private int[] rank; // Using Union by Rank

    // Initialize the data structure with N elements, each in its own set.
    public UnionFind(int n) {
        parent = new int[n];
        rank = new int[n];
        for (int i = 0; i < n; i++) {
            parent[i] = i; // Each element is its own parent initially.
            rank[i] = 1;   // Each set has a rank of 1 initially.
        }
    }

    /**
     * Finds the representative (root) of the set containing element `i`.
     * Implements Path Compression.
     */
    public int find(int i) {
        // Base case: if we are at the root, return it.
        if (parent[i] == i) {
            return i;
        }
        // Path Compression: As we recurse up to find the root, we set the parent
        // of each node along the way directly to the root. This flattens the tree.
        parent[i] = find(parent[i]);
        return parent[i];
    }

    /**
     * Merges the sets containing elements `i` and `j`.
     * Implements Union by Rank.
     * @return true if the sets were merged, false if they were already connected.
     */
    public boolean union(int i, int j) {
        // 1. Find the roots of both elements.
        int rootI = find(i);
        int rootJ = find(j);

        // 2. If they have the same root, they are already in the same set.
        if (rootI == rootJ) {
            return false;
        }

        // 3. Union by Rank: Attach the smaller-rank tree under the root of the higher-rank tree.
        if (rank[rootI] > rank[rootJ]) {
            parent[rootJ] = rootI;
        } else if (rank[rootI] < rank[rootJ]) {
            parent[rootI] = rootJ;
        } else {
            // If ranks are the same, make one the root and increment its rank.
            parent[rootJ] = rootI;
            rank[rootI]++;
        }
        return true;
    }

    // Optional helper method
    public boolean connected(int i, int j) {
        return find(i) == find(j);
    }
}
```

### Complexity Analysis

*   **Time Complexity**: With both Path Compression and Union by Rank/Size, the time complexity of `find` and `union` operations is **O(α(n))**, where `α(n)` is the Inverse Ackermann function. This function grows so slowly that it is less than 5 for any practical value of `n`. For all intents and purposes, it is **nearly constant time**.
*   **Space Complexity**: **O(n)** to store the `parent` and `rank` arrays.

---

## 🔗 Classic Problems

*   [Number of Connected Components in an Undirected Graph](https://leetcode.com/problems/number-of-connected-components-in-an-undirected-graph/)
*   [Redundant Connection](https://leetcode.com/problems/redundant-connection/)
*   [Graph Valid Tree](https://leetcode.com/problems/graph-valid-tree/)
*   [Accounts Merge](https://leetcode.com/problems/accounts-merge/)
