# Data Structure: Stack

## 🎯 Core Concept

*   **What is it?** A Stack is a linear data structure that follows the **Last-In, First-Out (LIFO)** principle. It's a collection of items where additions and removals happen only at one end, conventionally called the "top." Think of it like a stack of plates or a can of Pringles: you can only add a new plate to the top, and you can only remove the top plate.

*   **Key Characteristics**:
    *   **LIFO Access**: This is the defining feature. The last element pushed onto the stack is the first one to be popped.
    *   **Restricted Operations**: Unlike an array, you cannot access an element in the middle of a stack. You can only interact with the `top` element.

---

## 💪 Strengths & Weaknesses

| Operation | Time Complexity | Note                                                                                             |
| :--- | :--- | :--------------------------------------------------------------------------------------------------------------- |
| **Push (Add to top)** | **O(1)** | A fast, constant-time operation.                                                                 |
| **Pop (Remove from top)** | **O(1)** | Also a fast, constant-time operation.                                                            |
| **Peek (View top)** | **O(1)** | Looking at the top element without removing it is also constant-time.                            |
| **Search** | O(n) | The main weakness. To find an element, you have to pop every element above it, which takes linear time. |

---

## 🤔 When to Use This Data Structure

Look for these key indicators in a problem description:

*   The problem involves processing items in **reverse order**.
*   You need to manage a sequence of operations that must be undone (e.g., backtracking).
*   The problem involves parsing or validating **nested structures**, like parentheses, HTML/XML tags, or file paths.
*   You are implementing an algorithm that uses recursion, and you want to do it iteratively (the stack can mimic the program's call stack).

---

## ☕ Java-Specific Implementations

In Java, you have two main choices for a Stack. It's critical to know which one to use.

### 1. `ArrayDeque` (The Recommended Choice)

*   **How to use it**: `Deque<Integer> stack = new ArrayDeque<>();`
*   **Why it's better**: `ArrayDeque` is the modern, faster, and officially recommended implementation for a stack. It is more efficient because its methods are not synchronized, making it the ideal choice for single-threaded contexts like competitive programming.
*   **Key Methods**:
    *   `push(element)`: Adds an element to the top.
    *   `pop()`: Removes and returns the top element.
    *   `peek()`: Returns the top element without removing it.

### 2. `Stack` (The Legacy Class)

*   **How to use it**: `Stack<Integer> stack = new Stack<>();`
*   **Why to avoid it**: This is a legacy class that extends `Vector`. All of its methods are `synchronized`, which means it carries unnecessary overhead for thread safety that you almost never need in problem-solving. While it works, `ArrayDeque` is superior in performance.

---

## 🔗 Problem Checklist

This is a list of problems where a Stack is a key data structure.

| Problem | Key Application |
| :--- | :--- |
| [Valid Parentheses](../../src/main/java/com/problems/stack/problems/ValidParentheses.java) | The canonical use case for matching pairs. |
| [Min Stack](../../src/main/java/com/problems/stack/problems/MinStackProblem.java) | A design problem that extends the stack's functionality. |
| [Evaluate Reverse Polish Notation](../../src/main/java/com/problems/stack/problems/EvaluateReversePolishNotation.java) | Using a stack to manage operands and operators for expression evaluation. |
| [Daily Temperatures](../../src/main/java/com/problems/stack/problems/DailyTemperatures.java) | The classic "Monotonic Stack" pattern for finding the next greater element. |
| [Largest Rectangle in Histogram](../../src/main/java/com/problems/stack/problems/LargestRectangleInHistogramProblem.java) | A hard problem that uses a monotonic stack to find boundaries. |
