# Data Structure: ArrayDeque

## 🎯 Core Concept

*   **What is it?** A `Deque` (pronounced "deck") stands for **D**ouble **E**nded **Q**ueue. It is a queue that supports adding and removing elements from both the front and the back.

*   **`ArrayDeque` in Java**: This is the primary implementation of the `Deque` interface. It uses a resizable circular array internally, which makes it highly efficient. It is generally preferred over using `LinkedList` for stack and queue operations due to better cache locality and lower memory overhead.

*   **The Ultimate Hybrid**: An `ArrayDeque` can be used as a:
    *   **Queue (FIFO)**: By adding to the `last` and removing from the `first`.
    *   **Stack (LIFO)**: By adding to the `first` and removing from the `first`.
    *   A true double-ended queue.

---

## 💪 Strengths & Weaknesses

| Operation | Time Complexity | Note                                                                                             |
| :--- | :--- | :--------------------------------------------------------------------------------------------------------------- |
| **Add/Remove (First/Last)** | **O(1) amortized** | This is the main strength. Adding or removing from either end is extremely fast. Amortized means that while a single operation might be slow (if the internal array needs to resize), the average time per operation over many operations is constant. |
| **Contains / Search** | **O(N)** | `ArrayDeque` is not designed for searching. You must scan the entire structure. If you need fast searches, use a `HashSet` or `HashMap`. |
| **Get (by index)** | **O(N)** | Unlike an `ArrayList`, you cannot access an element by its index in O(1) time.                               |

**The Core Trade-Off**: You choose an `ArrayDeque` when your primary need is to add and remove elements from the **ends** of a sequence. You trade fast indexed access and searching for O(1) amortized additions and removals at both the front and back.

---

## ⚙️ Deep Dive: `pop()` vs. `removeLast()` - A Common Pitfall

When using `ArrayDeque` as a stack, it is crucial to understand how the methods map to traditional stack operations.

*   The `push(e)` method is defined to be equivalent to `addFirst(e)`. This adds an element to the front of the deque.
*   The `pop()` method is defined to be equivalent to `removeFirst()`. This removes an element from the **front** of the deque.

This maintains a consistent LIFO (Last-In, First-Out) behavior, as both operations work on the same end of the deque.

**The Confusion**: It can be tempting to think that if you `push` (add to the front), you should `removeLast` to get the element. This is incorrect and will reverse the order, turning your stack into a queue.

```java
Deque<String> stack = new ArrayDeque<>();

stack.push("A"); // equivalent to addFirst("A"). Deque: [A]
stack.push("B"); // equivalent to addFirst("B"). Deque: [B, A]
stack.push("C"); // equivalent to addFirst("C"). Deque: [C, B, A]

// Correct LIFO behavior:
String lastIn = stack.pop(); // equivalent to removeFirst(). Returns "C".

// Incorrect behavior (FIFO):
// String firstIn = stack.removeLast(); // This would return "A".
```

**Key Takeaway**: When using `ArrayDeque` as a stack, always use the `push()` and `pop()` methods together. They are designed to work on the same end (the `first` end) of the deque to provide correct LIFO behavior.

---

## ☕ Enqueue/Dequeue at Both Ends

This is the defining feature of a `Deque`. You can treat either end as the "front" or "back" of your queue.

### Operations at the End (Standard Queue Behavior)

These methods behave like a typical queue where you add to the back and remove from the front.

*   `addLast(e)` or `offerLast(e)`: Adds an element to the tail of the deque.
*   `removeFirst()` or `pollFirst()`: Removes an element from the head of the deque.

```java
Deque<String> deque = new ArrayDeque<>();

deque.addLast("A"); // Deque: [A]
deque.addLast("B"); // Deque: [A, B]
deque.addLast("C"); // Deque: [A, B, C]

// Dequeue from the beginning
String firstElement = deque.removeFirst(); // Returns "A"
```

### Operations at the Beginning

These methods allow you to add to the front and remove from the back.

*   `addFirst(e)` or `offerFirst(e)`: Adds an element to the head of the deque.
*   `removeLast()` or `pollLast()`: Removes an element from the tail of the deque.

```java
Deque<String> deque = new ArrayDeque<>();

deque.addFirst("C"); // Deque: [C]
deque.addFirst("B"); // Deque: [B, C]
deque.addFirst("A"); // Deque: [A, B, C]

// Dequeue from the end
String lastElement = deque.removeLast(); // Returns "C"
```

---

## 🤔 When to Use `ArrayDeque`

*   **As a Stack**: When you need a LIFO structure (e.g., for backtracking, DFS, parsing). It is the recommended replacement for the legacy `java.util.Stack` class.
    ```java
    Deque<Integer> stack = new ArrayDeque<>();
    stack.push(1); // push() is equivalent to addFirst()
    stack.pop();   // pop() is equivalent to removeFirst()
    ```
*   **As a Queue**: When you need a standard FIFO structure (e.g., for BFS).
    ```java
    Queue<Integer> queue = new ArrayDeque<>();
    queue.offer(1); // offer() is equivalent to offerLast()
    queue.poll();   // poll() is equivalent to pollFirst()
    ```
*   **Sliding Window Problems**: `ArrayDeque` is the perfect tool for implementing a monotonic queue to find the min/max in a sliding window (see `SlidingWindowMonotonicQueue` pattern).
