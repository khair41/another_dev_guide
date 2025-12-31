# Data Structure: Union-Find (Disjoint Set Union)

## 🎯 Core Concept

*   **What is it?** A Union-Find (or Disjoint Set Union - DSU) is a data structure that tracks a collection of elements partitioned into a number of non-overlapping (disjoint) subsets. It is specifically designed to be highly efficient at two things: determining if two elements are in the same subset, and merging two subsets.

*   **Analogy**: Imagine a large group of people. Each person starts in their own "circle." The Union-Find structure lets you do two things very quickly:
    1.  **Find**: Check if two people (say, Alice and Bob) are in the same circle.
    2.  **Union**: Merge the circles of two different people (e.g., merge Alice's circle with Carol's circle).

*   **Key Operations**:
    *   `find(i)`: Determines the "representative" (or root) of the set containing element `i`. If `find(i)` equals `find(j)`, then `i` and `j` are in the same set.
    *   `union(i, j)`: Merges the two sets containing elements `i` and `j`.
    *   `count()`: (Optional but useful) Returns the number of distinct sets remaining.

---

## 🤔 When to Use This Data Structure

Union-Find is the perfect tool for problems involving **grouping**, **partitioning**, and **connectivity**. If the problem asks you to group items and check if they belong to the same group, DSU should be one of the first things you think of.

*   **Detecting Cycles in an Undirected Graph**: As you iterate through edges, if you try to form a `union` of two nodes that are already in the same set (i.e., `find(u) == find(v)`), you've found a cycle.
*   **Finding Connected Components**: The number of disjoint sets remaining at the end (`count()`) is the number of connected components in a graph. Problems like "Number of Provinces" are a direct application.
*   **Kruskal's Algorithm for Minimum Spanning Tree (MST)**: DSU is used to efficiently check if adding an edge to the MST would form a cycle.
*   **Checking Network Connectivity**: Determine if any two nodes in a network are connected, directly or indirectly.

---

## ⚙️ Core Implementation & Optimizations

A naive implementation of Union-Find can be slow. The power of the data structure comes from two key optimizations that make its operations nearly constant time.

1.  **Union by Rank (or Size)**
    *   **Problem**: Without optimization, repeatedly merging sets can create a tall, skinny tree structure (like a linked list), making `find` operations take O(n) time.
    *   **Solution**: Always attach the root of the *shorter* tree to the root of the *taller* tree. This is tracked using a `rank` (or `size`) array. This keeps the trees as flat as possible.

2.  **Path Compression**
    *   **Problem**: Even with union by rank, finding a node deep in a tree requires traversing all its ancestors.
    *   **Solution**: During a `find(i)` operation, after the root is found, make every node along the path from `i` to the root point *directly* to the root. This dramatically flattens the tree and speeds up all future `find` operations for those nodes and their descendants.

### Complexity with Optimizations

When both **Union by Rank** and **Path Compression** are used, the amortized time complexity for `find` and `union` is **O(α(n))**, where α(n) is the Inverse Ackermann function. This function grows so slowly that it's less than 5 for any practical value of `n`, making the operations virtually constant time.

| Operation | Amortized Time Complexity | Note |
| :--- | :--- | :--- |
| **Union** | **O(α(n))** | Merging two sets is nearly constant time. |
| **Find** | **O(α(n))** | Finding a representative is nearly constant time. |
| **Constructor** | O(n) | Requires linear time to initialize the arrays. |

---

## ☕ Java Implementation

The standard approach is to create a dedicated `UnionFind` class that encapsulates the `parent` and `rank` arrays and the core logic. This class can then be instantiated in any problem that requires it.

```java
class UnionFind {
    private int[] parent;
    private int[] rank;

    public UnionFind(int n) {
        parent = new int[n];
        rank = new int[n];
        for (int i = 0; i < n; i++) {
            parent[i] = i;
            rank[i] = 1;
        }
    }

    public int find(int i) {
        if (parent[i] != i) {
            parent[i] = find(parent[i]); // Path compression
        }
        return parent[i];
    }

    public boolean union(int i, int j) {
        // ... implementation with union by rank ...
    }
}
```