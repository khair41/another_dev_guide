# Data Structure: TreeSet & TreeMap (Balanced Trees)

## 🎯 Core Concept

*   **What are they?** `TreeSet` and `TreeMap` are Java's implementations of self-balancing binary search trees (specifically, Red-Black Trees). Their defining feature is that they **always keep their elements in sorted order**.
    *   `TreeMap` is a map that stores key-value pairs, sorted by its keys.
    *   `TreeSet` is a set that stores unique elements, sorted in order. It is internally backed by a `TreeMap`.

*   **Why are they useful?** They bridge the gap between a sorted array and a hash map. They provide efficient `O(log N)` insertion, deletion, and search, while also maintaining the collection in a perpetually sorted state. This makes them incredibly powerful for problems involving order and range queries.

---

## 🤔 When to Use & Real-World Scenarios

Choose a `TreeSet` or `TreeMap` over a `HashSet` or `HashMap` when the problem requires more than just fast lookups. Look for these indicators:

*   You need to keep a collection **sorted at all times** while efficiently adding and removing elements.
*   The problem asks to find the **"next greatest"** or **"next smallest"** element relative to a given value.
*   You need to perform **range queries** (e.g., "find all elements between X and Y").
*   You need to find the **minimum or maximum** element in a dynamic collection of items.

### Real-World Implementations

*   **Leaderboards**: A `TreeMap<Integer, List<String>>` is perfect for a gaming leaderboard. The score is the key, and the map automatically keeps the scores sorted. When a new high score comes in, it's inserted in O(log N) time.

*   **Calendaring and Event Booking**: A `TreeMap<LocalDateTime, Event>` can store events. When a user wants to book a new event, you can use `floorEntry()` and `ceilingEntry()` in O(log N) time to quickly find the events immediately before and after the desired time to check for overlaps.

*   **Stock Market Order Books**: A `TreeMap<Double, OrderList>` can model a stock's order book. The price is the key. The map keeps buy and sell orders sorted by price, which is essential for matching trades.

---

## 💪 Strengths & Weaknesses (Time Complexity)

| Operation | Time Complexity | Relevant Java Methods |
| :--- | :---: | :--- |
| **Insertion** | **O(log N)** | `add()`, `put()` |
| **Deletion** | **O(log N)** | `remove()` |
| **Search** | **O(log N)** | `contains()`, `containsKey()`, `get()` |
| **Get Min/Max** | **O(log N)** | `first()`, `last()`, `firstKey()`, `lastKey()` |
| **Get Next/Prev** | **O(log N)** | `floor()`, `ceiling()`, `higher()`, `lower()` (and `*Key` variants) |
| **Get Size** | **O(1)** | `size()`, `isEmpty()` |

**The Core Trade-Off**: You choose a `TreeSet` over a `HashSet` when you need **ordered data** and are willing to trade O(1) for O(log N) on `add`, `remove`, and `contains` operations to gain access to powerful ordered methods like `floor()`, `ceiling()`, `first()`, and `last()`.

---

## ☕ Java-Specific Implementations & Key Methods

To use `TreeSet` or `TreeMap` with custom objects, the object **must** either implement the `Comparable` interface or you must provide a `Comparator` to the constructor. For primitive types like `Integer` and `String`, this is handled automatically.

### 1. `TreeMap<K, V>`

A map that keeps its entries sorted by key.

```java
// Declaration
SortedMap<Integer, String> map = new TreeMap<>();
```

*   **Standard Methods**: `put(key, value)`, `get(key)`, `remove(key)`, `containsKey(key)`.

*   **Special Methods (The Superpowers)**:
    *   `firstKey()` / `lastKey()`: Returns the smallest or largest key.
    *   `floorKey(key)`: Returns the greatest key less than or equal to the given `key`.
    *   `ceilingKey(key)`: Returns the smallest key greater than or equal to the given `key`.
    *   `higherKey(key)`: Returns the smallest key strictly greater than the given `key`.
    *   `lowerKey(key)`: Returns the greatest key strictly less than the given `key`.

### 2. `TreeSet<E>`

A set that keeps its unique elements sorted.

```java
// Declaration
SortedSet<Integer> set = new TreeSet<>();
```

*   **Standard Methods**: `add(element)`, `contains(element)`, `remove(element)`.

*   **Special Methods (The Superpowers)**:
    *   `first()` / `last()`: Returns the smallest or largest element.
    *   `floor(element)`: Returns the greatest element less than or equal to the given `element`.
    *   `ceiling(element)`: Returns the smallest element greater than or equal to the given `element`.
    *   `higher(element)` / `lower(element)`: Same as above, but strictly greater/less.

---

## ⚙️ Internal Mechanics (Deep Dives)

Understanding *how* `TreeSet` and `TreeMap` achieve their performance guarantees is key to using them effectively.

*   **[Deep Dive: How Auto-Balancing Works](./deep_dives/red_black_trees.md)**: Learn how Red-Black Trees, the engine behind `TreeSet` and `TreeMap`, use rotations and re-coloring to maintain a balanced height of O(log N), preventing the worst-case O(n) performance of a simple BST.

*   **[Deep Dive: How Backing Views Work](./deep_dives/backing_views.md)**: Discover how methods like `subSet()` provide an incredibly fast, O(1) "live view" into the original data, enabling highly efficient range queries without copying any elements.

---

## 🔗 Classic Problems

*   [Contains Duplicate III](https://leetcode.com/problems/contains-duplicate-iii/): Use a `TreeSet` as a sliding window to efficiently find if there is a nearby element within a certain value range using `floor()` or `ceiling()`.
*   [My Calendar I](https://leetcode.com/problems/my-calendar-i/): Use a `TreeMap` to store booked intervals and use `floorEntry`/`ceilingEntry` to check for overlaps in O(log N) time.
*   [Data Stream as Disjoint Intervals](https://leetcode.com/problems/data-stream-as-disjoint-intervals/): A perfect use case for `TreeSet` to manage and merge sorted intervals.
