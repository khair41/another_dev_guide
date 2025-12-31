# The Problem-Solving Cheatsheet: Clarifying Questions for Interviews

The goal of the first 5 minutes of a coding interview is not to write code, but to demonstrate that you are a thoughtful, structured problem-solver. Asking the right questions is the key. This is not an interrogation; it's a dialogue to build a shared understanding with your interviewer.

---

## How to Use This Guide

Don't just read these questions off a list. Internalize the categories. When you get a problem, pause, and think out loud: "Okay, before I jump into a solution, I want to make sure I fully understand the problem space. I'd like to clarify a few things about the inputs, outputs, and any potential edge cases." Then, group your questions naturally.

---

## 📥 1. Input Clarification

This is usually the most important category. The nature of the input dictates the algorithms you can use.

| Question | Why You Ask It |
| :--- | :--- |
| **What is the data type of the input elements?** (Integers, strings, etc.) | Confirms the basic building blocks. |
| **Are the numbers positive, negative, or can they be zero?** | Affects calculations and edge cases. |
| **Are the numbers integers or floating-point?** | Floating-point numbers introduce precision issues. |
| **For an array/list: Is it sorted?** | **CRITICAL**. This is the #1 question. If yes, it opens the door to Binary Search (O(log N)), Two Pointers, etc. If no, you're likely looking at O(N) or O(N log N) solutions. |
| **For an array/list: Can it contain duplicates?** | **CRITICAL**. Affects logic for hashmaps, sets, and finding unique elements. |
| **For a string: What is the character set?** (ASCII, Unicode) | Determines the size of a frequency array (26 for lowercase, 128 for ASCII, etc.). |
| **For a string: Is it case-sensitive?** ("a" is different from "A") | Determines if you need to normalize the input (e.g., `.toLowerCase()`). |
| **For a graph/tree: Is it directed or undirected?** | Changes how you build your adjacency list and traverse. |
| **For a graph/tree: Is it weighted or unweighted?** | Determines if you use BFS (unweighted) or Dijkstra/Bellman-Ford (weighted) for shortest path. |
| **For a graph: Can it have cycles? Is it connected?** | Affects algorithm choice (e.g., Topological Sort requires a DAG). |
| **For a tree: Is it a Binary Tree? A Binary Search Tree (BST)? Is it guaranteed to be balanced?** | Each type has specific properties that can be exploited. An unbalanced BST can degrade to O(N) performance. |
| **What should I assume for invalid inputs?** (e.g., a `null` array) | Shows you're thinking about robustness. The interviewer will likely say "you can assume valid inputs," but asking is a good signal. |

---

## 📤 2. Output Clarification

Don't assume the output format. Clarifying it prevents simple but costly mistakes.

| Question | Why You Ask It |
| :--- | :--- |
| **What is the data type of the output?** (A single value, a list, a boolean?) | Defines the "shape" of your return statement. |
| **If there is no valid solution, what should I return?** (`null`, an empty list, `-1`, `0`?) | **CRITICAL**. This is a very common source of ambiguity. |
| **If there are multiple valid solutions, which one should I return?** (Any one? The first one found? The lexicographically smallest? All of them?) | Drastically changes the scope of the problem. Finding one solution is often much easier than finding all of them. |
| **Does the order of the output matter?** (e.g., for a list of results) | Determines if you need to sort your final result. |
| **Can the output be the same as the input?** (e.g., for an in-place algorithm) | Clarifies if you are allowed to modify the input data structure. |

---

## ⚖️ 3. Constraints & Edge Cases

This section signals that you are thinking about performance, scale, and reliability.

| Question | Why You Ask It |
| :--- | :--- |
| **How large can the input be?** (e.g., "What's the range of N?") | **CRITICAL**. This is your biggest clue for the required time complexity. <br> - N <= 1,000 -> O(N^2) is likely okay. <br> - N <= 100,000 -> O(N log N) is likely needed. <br> - N >= 1,000,000 -> O(N) or O(log N) is required. |
| **What is the range of values for the elements in the input?** | **CRITICAL**. This tells you if you need to worry about integer overflow. If values can be up to 10^9, adding two of them will overflow a standard 32-bit integer. You may need to use a `long`. |
| **Is memory a concern? Do I need to solve this in-place?** | This is a hint towards the required space complexity. "In-place" means O(1) extra space. |
| **What about empty inputs?** (e.g., an empty array, an empty string) | The most basic edge case. |
| **What about single-element inputs?** | The next most basic edge case. |

---

## 🧠 4. Assumptions & Logic ("What Ifs")

Use these questions to talk through a small example and confirm you understand the core logic.

| Question | Why You Ask It |
| :--- | :--- |
| "Just to confirm my understanding, if the input is `[3, 1, 2]`, the output should be `[1, 2, 3]`. Is that correct?" | Validates your core understanding with a simple test case. |
| "For the 'meeting rooms' problem, if one meeting ends at 10:00 and another starts at 10:00, can they use the same room?" | Clarifies how to handle boundary conditions (inclusive vs. exclusive intervals). |
| "When you say 'substring', does that mean a contiguous block of characters? And for 'subsequence', the order is maintained but they don't have to be contiguous?" | Confirms your understanding of standard terminology. |
| "The problem asks for *a* path. Does it need to be the shortest path, or can any valid path be returned?" | Prevents you from over-engineering a solution (e.g., building a complex Dijkstra's solution when a simple DFS would suffice). |
