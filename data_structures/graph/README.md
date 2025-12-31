# Graph Data Structure

Graphs are the ultimate data structure for representing networks and relationships. From social networks and flight paths to computer networks and dependencies between tasks, graphs model the interconnectedness of modern data.

---

## 🎯 Core Principles

A graph `G` is a set of vertices (or nodes) `V` and a set of edges `E`, where each edge connects a pair of vertices.

*   **Vertices (Nodes)**: The fundamental entities in the graph.
*   **Edges (Links)**: The connections between vertices.

### Types of Graphs:

*   **Undirected vs. Directed**: In an undirected graph, an edge `(u, v)` is the same as `(v, u)`. In a directed graph (or digraph), edges have a direction, so `u -> v` is different from `v -> u`.
*   **Unweighted vs. Weighted**: In a weighted graph, each edge has an associated cost or weight. In an unweighted graph, all edges are considered to have the same weight (usually 1).
*   **Cyclic vs. Acyclic**: A cyclic graph contains at least one path that starts and ends at the same vertex. A Directed Acyclic Graph (DAG) is a special, common type of graph with no directed cycles.

---

## 🎨 The Art of the Trade-off: Graph Representations

The most critical decision when working with graphs is how to represent them in memory. The choice impacts the time and space complexity of every algorithm you run.

| Representation | Space Complexity | Time to add edge | Time to check adjacency | Best For |
| :--- | :---: | :---: | :---: | :--- |
| **Adjacency List** | **O(V + E)** | **O(1)** | O(k) (k=degree) | **Sparse Graphs** (E is close to V) |
| **Adjacency Matrix** | O(V^2) | O(1) | **O(1)** | **Dense Graphs** (E is close to V^2) |

*   **Adjacency List**: An array or map where each index/key `u` stores a list of its adjacent vertices `v`. This is the most common representation for competitive programming as most problems feature sparse graphs.
*   **Adjacency Matrix**: A V x V matrix where `matrix[i][j] = 1` (or the edge weight) if an edge exists from `i` to `j`, and 0 (or infinity) otherwise.

**Conclusion**: For most problems, an **Adjacency List** is the superior choice due to its space efficiency.

---

## 🤔 Problem-First Thinking: Mapping Problems to Algorithms

*   **If the problem involves exploring connected components, finding paths in a maze, or simple connectivity...**
    *   **Think**: **Depth-First Search (DFS)** or **Breadth-First Search (BFS)**.
    *   **Practice Problems**:
        *   LeetCode 200: [Number of Islands](https://leetcode.com/problems/number-of-islands/)
            *   *Why? This is a classic connectivity problem. You need to visit all connected parts of a landmass ('1's) to count it as a single island. Both BFS and DFS are perfect for this kind of traversal.*

*   **If the problem involves finding the shortest path in an *unweighted* graph...**
    *   **Think**: **Breadth-First Search (BFS)**.
    *   **Practice Problems**:
        *   LeetCode 1091: [Shortest Path in Binary Matrix](https://leetcode.com/problems/shortest-path-in-binary-matrix/)
            *   *Why? The graph is unweighted (all steps have a cost of 1). BFS is guaranteed to find the shortest path from a source to a target in an unweighted graph.*

*   **If the problem involves finding the shortest path from a single source in a *weighted* graph with *no negative edges*...**
    *   **Think**: **Dijkstra's Algorithm**.
    *   **Practice Problems**:
        *   LeetCode 743: [Network Delay Time](https://leetcode.com/problems/network-delay-time/)
            *   *Why? This is a direct application. You need to find the shortest path (minimum time) from a single source to all other nodes in a weighted, non-negative edge graph.*

*   **If the problem is like Dijkstra's, but you have a "guiding hint" or heuristic to the target...**
    *   **Think**: **A* Search Algorithm**.
    *   **Practice Problems**:
        *   Classic 8-puzzle or 15-puzzle problems.
        *   Pathfinding for game characters (e.g., in a grid with obstacles).
            *   *Why? A* is an "informed" version of Dijkstra's. If you can estimate the remaining distance to the target (the heuristic), A* can find the shortest path much faster by prioritizing nodes that are not just close to the start, but also seem closer to the end.*

*   **If the problem involves finding the shortest path in a *weighted* graph with *negative edges*...**
    *   **Think**: **Bellman-Ford Algorithm**.
    *   **Practice Problems**:
        *   LeetCode 787: [Cheapest Flights Within K Stops](https://leetcode.com/problems/cheapest-flights-within-k-stops/)
            *   *Why? The constraint "at most K stops" is a perfect fit for Bellman-Ford's iterative nature, where each iteration `i` finds the shortest path using at most `i` edges.*

*   **If the problem involves finding the shortest path between *all possible pairs* of nodes...**
    *   **Think**: **Floyd-Warshall Algorithm**.
    *   **Practice Problems**:
        *   LeetCode 1334: [Find the City With the Smallest Number of Neighbors at a Threshold Distance](https://leetcode.com/problems/find-the-city-with-the-smallest-number-of-neighbors-at-a-threshold-distance/)
            *   *Why? The problem requires knowing the shortest distance between every pair of cities to count neighbors within a threshold. This is exactly what the all-pairs Floyd-Warshall algorithm provides.*

*   **If the problem involves finding the minimum cost to connect all nodes in an *undirected* graph...**
    *   **Think**: **Kruskal's Algorithm** or **Prim's Algorithm** (Minimum Spanning Tree).
    *   **Practice Problems**:
        *   LeetCode 1584: [Min Cost to Connect All Points](https://leetcode.com/problems/min-cost-to-connect-all-points/)
            *   *Why? The problem asks for the minimum cost to make all points connected, which is the definition of a Minimum Spanning Tree. Both Kruskal's and Prim's work perfectly.*

*   **If the problem involves checking for connectivity or grouping items into components dynamically...**
    *   **Think**: **Union-Find (Disjoint Set Union)**.
    *   **Practice Problems**:
        *   LeetCode 547: [Number of Provinces](https://leetcode.com/problems/number-of-provinces/)
            *   *Why? You need to group connected cities into sets (provinces). Union-Find is designed to efficiently merge sets and count the number of disjoint sets remaining.*

*   **If the problem involves ordering tasks with dependencies...**
    *   **Think**: **Topological Sort**.
    *   **Practice Problems**:
        *   LeetCode 207: [Course Schedule](https://leetcode.com/problems/course-schedule/)
            *   *Why? The problem is about dependencies (prerequisites), which form a directed graph. A valid course schedule is possible only if the graph has no cycles, which is the first check in a topological sort.*

---

## ⚠️ The Negative Edge Problem: Dijkstra vs. Bellman-Ford

The presence of negative edge weights is a critical detail that dictates your choice of shortest path algorithm.

### Why Dijkstra's Fails

Dijkstra's algorithm is **greedy**. It works under the assumption that once it pulls a node `u` from the priority queue, it has found the absolute shortest path to `u`. This assumption only holds true if all edge weights are non-negative. A negative edge can create a "shortcut" to a node that has already been finalized, but Dijkstra's will never re-evaluate it.

### The Bellman-Ford & Floyd-Warshall Solution

**Bellman-Ford** is specifically designed to handle negative edges. Instead of a greedy approach, it is a dynamic programming algorithm that systematically relaxes every edge in the graph `V-1` times. This repeated process ensures that the shortest path information from negative edges is correctly propagated throughout the graph.

### The Ultimate Problem: Negative-Weight Cycles

A negative-weight cycle is a cycle in the graph where the sum of the edge weights is negative. If such a cycle is reachable from the source, the concept of a "shortest path" becomes meaningless, because you can traverse the cycle infinitely to achieve an arbitrarily low path cost. Bellman-Ford and Floyd-Warshall can detect these cycles; Dijkstra's cannot.

---

## 💡 Implementation Pro-Tips & Gotchas

*   **Building an Adjacency List**: The most flexible way in Java is `Map<Integer, List<Integer>> adj = new HashMap<>();`. If vertices are numbered 0 to n-1, `List<Integer>[] adj = new ArrayList[n];` is slightly more efficient.

*   **Understanding Relaxation**: The core of Dijkstra's and Bellman-Ford is an operation called "relaxation." It's the process of updating the cost to reach a node with a shorter path found so far. For a detailed explanation, see **[Deep Dive: The Concept of Relaxation](./deep_dives/relaxation_concept.md)**.

*   **Dijkstra's, Prim's, and A* require a PriorityQueue**: These greedy algorithms depend on a `PriorityQueue` to efficiently retrieve the next best item. Remember to store pairs of `(node, cost)` and set the comparator to sort by cost (or `f(n)` for A*).

*   **A* Heuristics**: The heuristic function for A* must be **admissible** (it never overestimates the true cost) for the algorithm to guarantee the shortest path.

*   **Floyd-Warshall Loop Order**: The loops for Floyd-Warshall **must** be in the order `k, i, j`, where `k` is the intermediate vertex. Any other order will produce incorrect results.

---

## 📚 Graph Algorithms & Data Structures

*   **Core Traversal**
    *   [DFS for Island Counting](./GraphDfsIslandCounting.java)
    *   [BFS for Island Counting](./GraphBfsIslandCounting.java)
*   **Topological Sort**
    *   [BFS (Kahn's Algorithm)](./GraphBfsTopologicalSort.java)
*   **Shortest Path**
    *   [Dijkstra's Algorithm](./GraphDijkstraShortestPath.java)
    *   [A* Search Algorithm](./GraphShortestPathAStar.java)
    *   [Bellman-Ford Algorithm](./GraphBellmanFordShortestPath.java)
    *   [Floyd-Warshall Algorithm](./GraphShortestPathFloydWarshall.java)
    *   [Bidirectional BFS](./GraphBidirectionalBfs.java)
*   **Minimum Spanning Tree (MST)**
    *   [Kruskal's Algorithm](./GraphMinimumSpanningTree.java)
    *   [Prim's Algorithm](./GraphMstPrim.java)
*   **Connectivity & Cycles**
    *   [Union-Find Data Structure](./GraphDataStructureUnionFind.java)
    *   [DFS for Cycle Detection (Directed)](./GraphDfsCycleDetectionDirected.java)
    *   [Strongly Connected Components (Tarjan's)](./GraphStronglyConnectedComponents.java)
    *   [Bridges & Articulation Points](./GraphBridgesAndArticulationPoints.java)
*   **Other**
    *   [Cloning a Graph](./GraphDeepCopy.java)
