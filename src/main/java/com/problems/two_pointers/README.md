# Topic: Two Pointers

**Last Reviewed**: 2024-07-30
**Confidence Score (1-5)**: 4

## 🎯 Core Concept

*   **What is it?** The Two Pointers technique is a simple and highly effective pattern for optimizing problems that involve searching for pairs or sub-arrays in a sorted array. Instead of using a slow nested loop (O(n²)), we use two iterators (pointers) that move through the array in a coordinated way, allowing us to solve the problem in a single pass.

*   **Why is it useful?** Its primary benefit is performance. It typically reduces the time complexity from **O(n²)** down to **O(n)**. Furthermore, because it modifies pointers in-place, it often achieves this with **O(1)** extra space, making it very efficient.

---

## 🤔 When to Use This Pattern

Look for these key indicators in a problem description:

*   The input is a **sorted array** (or can be sorted easily). <--- **VERY IMPORTANT**
*   You need to find a **pair, triplet, or sub-array** that satisfies a certain condition (e.g., sums up to a target).
*   The problem involves comparing elements from **opposite ends** of an array.
*   You need to process an array in a way that avoids creating temporary data structures.
*   The problem asks for a solution with **O(1) space complexity**.

---

## ⚙️ Common Patterns & Variations

### 1. Opposite Ends Pattern

*   **Description**: This is the most common variation. One pointer (`left`) starts at the beginning of the array, and the other (`right`) starts at the end. They move towards each other until they meet or cross.
*   **Best for**: Problems on **sorted arrays** where you're looking for a pair that meets a specific condition. The sorted nature of the array allows you to make intelligent decisions about which pointer to move.
*   **Used in**: `Two Sum II`, `3Sum`, `Container With Most Water`.

### 2. Same Direction (Fast/Slow) Pattern

*   **Description**: Two pointers (often called `slow` and `fast`) start at or near the beginning of the array. The `fast` pointer moves ahead to explore the array, while the `slow` pointer only advances when a certain condition is met. This pattern is often used for in-place modifications.
*   **Best for**: Problems like "Remove Duplicates from Sorted Array" or cycle detection in linked lists.

---

## ✅ Solved Problems Checklist

| Problem | Difficulty | Local Path | Implemented Solutions |
| :--- | :--- | :--- | :--- |
| **Valid Palindrome** | Easy | [Problem](./problems/ValidPalindrome.java) | [Two Pointers](./solutions/ValidPalindromeTwoPointersSolution.java) |
| **Two Sum II** | Medium | [Problem](./problems/TwoSumII.java) | [Two Pointers](./solutions/TwoSumIITwoPointersSolution.java), [Binary Search](./solutions/TwoSumIIBinarySearchSolution.java) |
| **3Sum** | Medium | [Problem](./problems/ThreeSum.java) | [Two Pointers](./solutions/ThreeSumTwoPointersSolution.java), [HashMap](./solutions/ThreeSumHashMapSolution.java) |
| **Container With Most Water** | Medium | [Problem](./problems/ContainerWithMostWater.java) | [Two Pointers](./solutions/ContainerWithMostWaterTwoPointersSolution.java) |
| **Trapping Rain Water** | Hard | [Problem](./problems/TrappingRainWater.java) | [Two Pointers](./solutions/TrappingRainWaterTwoPointersSolution.java), [Brute Force](./solutions/TrappingRainWaterBruteForceSolution.java), [Prefix/Suffix](./solutions/TrappingRainWaterPrefixSuffixSolution.java), [Stack](./solutions/TrappingRainWaterStackSolution.java) |

---

## 📝 TODO: Practice Problems

*   [Squares of a Sorted Array](https://leetcode.com/problems/squares-of-a-sorted-array/) (Easy)
*   [Remove Duplicates from Sorted Array](https://leetcode.com/problems/remove-duplicates-from-sorted-array/) (Easy)
*   [4Sum](https://leetcode.com/problems/4sum/) (Medium)
*   [Sort Colors](https://leetcode.com/problems/sort-colors/) (Medium)
