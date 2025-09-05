# Data Structures

This directory contains detailed study guides for common data structures. Each data structure has its own sub-directory with a `README.md` that explains its core concepts, common patterns, Java-specific implementations, and a checklist of relevant problems.

---

## 🎯 Core Principles

The foundation of efficient problem-solving is choosing the right tool for the job, and in DSA, that tool is the data structure. The way you store and organize your data directly impacts the time and space complexity of your algorithms. Understanding the trade-offs between different data structures is the most critical first step in designing an optimal solution.

---

## 🎨 The Art of the Trade-off: A Final Word

There is no perfect data structure. An `ArrayList` gives you instant access but slow insertions. A `LinkedList` gives you instant insertions but slow access. A `HashMap` gives you fast searches but uses more memory and offers no order.

This is the **central trade-off** in all of data structures. Your goal as a problem-solver is not to memorize a single "best" solution, but to become an expert at weighing these trade-offs. When you read a problem, ask yourself: "What is the most frequent operation I need to perform? Access, search, insertion, or deletion?" The answer will almost always point you to the right data structure for the job.

---

## 📜 Interface vs. Implementation: A Key CS Concept

In Java, it's important to distinguish between an **interface** (the Abstract Data Type or ADT) and its **implementation** (the concrete data structure).

*   **The Interface (ADT)**: Defines *what* a data structure should do. It's a contract that specifies a set of methods (e.g., a `List` interface guarantees you'll have `add()`, `get()`, `remove()` methods).
*   **The Implementation**: Defines *how* the data structure does it. `ArrayList` and `LinkedList` are both implementations of the `List` interface, but they have very different internal workings and performance trade-offs.

**Why does this matter?** Good practice is to "code to the interface." This makes your code more flexible. For example, you can write a method that accepts a `List` and it will work whether you pass in an `ArrayList` or a `LinkedList`.

```java
// Good Practice: Declare with the interface, instantiate with the implementation.
List<String> myList = new ArrayList<>(); 
Queue<Integer> myQueue = new ArrayDeque<>();
Map<String, Integer> myMap = new HashMap<>();
```

---

## 🖼️ Visualizing the Structures

| Data Structure | Real-World Analogy | ASCII Art Diagram |
| :--- | :--- | :--- |
| **Array** | A numbered street of houses | `[0] [1] [2] [3] [4]` |
| **Linked List** | A scavenger hunt (each clue tells you where to find the next one) | `[A] -> [B] -> [C] -> null` |
| **Hash Table** | A coat check at a theater (a ticket (key) leads directly to your coat (value)) | `Key -> [Value]` |
| **Stack** | A stack of plates | `[C] <- Top` <br> `[B]` <br> `[A]` |
| **Queue** | A line at a checkout counter | `Front -> [A] [B] [C] <- Back` |
| **Tree** | A family tree or an organizational chart | `( A )` <br> `/   \` <br> `(B)   (C)` |

---

## 🤔 Problem-First Thinking: Mapping Problems to Data Structures

*   **If the problem involves...**
    *   "Find duplicates"
    *   "Count the frequency of..."
    *   "Check if an item exists"
    *   **Think**: `HashMap` or `HashSet`. These are your go-to tools for anything involving fast lookups, existence checks, or counting.

*   **If the problem involves...**
    *   Processing items in a "Last-In, First-Out" (LIFO) manner
    *   Backtracking, or reversing the order of something
    *   Parsing nested parentheses or tags
    *   **Think**: `Stack`.

*   **If the problem involves...**
    *   Processing items in a "First-In, First-Out" (FIFO) manner
    *   Level-order traversal of a tree (BFS)
    *   Managing tasks in the order they were received
    *   **Think**: `Queue`.

*   **If the problem involves...**
    *   Finding the "top K" items (largest, smallest, most frequent)
    *   Finding the median in a stream of numbers
    *   Always needing access to the min or max element
    *   **Think**: `Heap` (implemented as `PriorityQueue` in Java).

*   **If the problem involves...**
    *   Searching for a prefix
    *   Building an auto-complete system or a dictionary
    *   **Think**: `Trie`.

---

## ⚖️ At-a-Glance Comparison

This table provides a simplified overview of the average time complexities for common data structures.

| Data Structure | Access | Search | Insertion | Deletion | Space | Common Java Implementation |
| :--- | :---: | :---: | :---: | :---: | :---: | :--- |
| **Array** | **O(1)** | O(n) | O(n) | O(n) | O(n) | `int[]`, `String[]`, `ArrayList` |
| **Linked List** | O(n) | O(n) | **O(1)** | **O(1)** | O(n) | `java.util.LinkedList` |
| **Hash Table** | O(n) | **O(1)** | **O(1)** | **O(1)** | O(n) | `java.util.HashMap`, `HashSet` |
| **Binary Search Tree** | O(log n) | O(log n) | O(log n) | O(log n) | O(n) | `java.util.TreeSet`, `TreeMap` |
| **Stack** | O(n) | O(n) | **O(1)** | **O(1)** | O(n) | `java.util.Stack`, `ArrayDeque` |
| **Queue** | O(n) | O(n) | **O(1)** | **O(1)** | O(n) | `java.util.Queue`, `LinkedList` |
| **Heap** | O(n) | O(n) | O(log n) | O(log n) | O(n) | `java.util.PriorityQueue` |

---

## 💡 Implementation Pro-Tips & Gotchas

*   **Prefer `ArrayDeque` for Stacks and Queues**: The legacy `Stack` class is synchronized (thread-safe), which adds unnecessary overhead for competitive programming. `ArrayDeque` is the modern, faster, and recommended choice for implementing both Stacks (`push`/`pop`) and Queues (`offer`/`poll`).

*   **`PriorityQueue` is a Min-Heap by Default**: Remember that `new PriorityQueue<>()` in Java creates a **min-heap**. To create a **max-heap**, you must provide a reverse order comparator: `new PriorityQueue<>(Collections.reverseOrder());` or `new PriorityQueue<>((a, b) -> b - a);`.

*   **The `hashCode` and `equals` Contract**: If you use a custom object as a key in a `HashMap` or an element in a `HashSet`, you **must** correctly override both the `hashCode()` and `equals()` methods. If you don't, the hash table will not be able to find your objects correctly. Java Records handle this for you automatically, making them a great choice for custom keys.

*   **Need Order in Your Map?**: A standard `HashMap` does not guarantee any order. If you need to maintain the order in which items were inserted, use a `LinkedHashMap` instead.

---

## 📚 Data Structures Covered

*   **[Arrays](../src/main/java/com/problems/arrays_hashing/README.md)**
    *   *A foundational, contiguous block of memory. Arrays provide the fastest possible O(1) access to elements by index but are rigid, with a fixed size and slow O(n) insertions and deletions.*

*   **[Stacks & Queues](../src/main/java/com/problems/stack/README.md)**
    *   *Abstract data types that enforce specific access patterns (LIFO for Stacks, FIFO for Queues). Often implemented with Arrays or Linked Lists.*

*   **Linked Lists** (Coming Soon)

*   **Trees** (Coming Soon)

*   **Heaps** (Coming Soon)

*   **Tries** (Coming Soon)
