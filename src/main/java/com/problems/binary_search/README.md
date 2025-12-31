# Topic: Binary Search

**Last Reviewed**: 2024-07-30
**Confidence Score (1-5)**: 4

## 🎯 Core Concept

*   **What is it?** Binary Search is a highly efficient algorithm for finding an item in a **sorted** collection (like an array). Instead of checking items one by one, it repeatedly divides the search space in half. It compares the target value to the middle element of the collection; if they are not equal, the half in which the target cannot lie is eliminated, and the search continues on the remaining half.

*   **Why is it useful?** Its performance is its key advantage. It has a time complexity of **O(log N)**, which is exceptionally fast and a massive improvement over the O(N) time of a linear scan. It is one of the most fundamental and frequently-used algorithms in computer science.

---

## 🤔 When to Use This Pattern

Look for these key indicators in a problem description:

*   The input array or collection is **sorted**.
*   The problem asks you to find a specific element, the position to insert an element, or the first/last occurrence of an element in a sorted collection.
*   The problem has a **monotonic property**. This is the key for more advanced applications. If a condition is true for a value `x`, it must also be true for all values greater than `x` (or all values less than `x`). This allows you to "binary search for the answer."
*   You need a solution with better than O(N) time complexity.

---

## ⚙️ The Standard Template & Avoiding Infinite Loops

Implementing binary search correctly requires careful handling of pointers and boundary conditions to avoid infinite loops.

```java
int left = 0;
int right = nums.length - 1;

while (left <= right) { // Use <= to include the final element
    // Prevent overflow with (right - left) / 2
    int mid = left + (right - left) / 2;

    if (nums[mid] == target) {
        return mid; // Found the target
    } else if (nums[mid] < target) {
        // The target must be in the right half
        left = mid + 1; // CRITICAL: mid + 1 to shrink the space
    } else {
        // The target must be in the left half
        right = mid - 1; // CRITICAL: mid - 1 to shrink the space
    }
}
return -1; // Target not found
```

**Key to Avoiding Infinite Loops**: The crucial detail is `left = mid + 1` and `right = mid - 1`. By always moving the boundary past the `mid` point, you guarantee that the search space shrinks with every iteration, ensuring the `while` loop will eventually terminate.

---

## 💡 Advanced Pattern: Binary Search on the Answer

Sometimes, you don't search on the input array itself, but on the **range of possible answers**.

*   **Description**: This pattern is used for optimization problems where you need to find the minimum (or maximum) value that satisfies a certain condition. You define a search space for the answer (e.g., from `1` to `max_value`) and then use binary search to find the optimal value in that space.
*   **How it Works**: For each `mid` value in your answer space, you run a check (often an O(N) helper function) to see if this `mid` value is a valid solution. Based on the result, you discard half of the answer space.
*   **Used in**: `Koko Eating Bananas` (we binary search for the speed `k`).

---

## ✅ Solved Problems Checklist

| Problem | Difficulty | Local Path | Implemented Solutions |
| :--- | :--- | :--- | :--- |
| **Binary Search** | Easy | [Problem](./problems/BinarySearchProblem.java) | [Iterative](./solutions/BinarySearchSolution.java) |
| **Search a 2D Matrix** | Medium | [Problem](./problems/SearchA2DMatrixProblem.java) | [Virtual Array](./solutions/SearchA2DMatrixSolution.java) |
| **Koko Eating Bananas**| Medium | [Problem](./problems/KokoEatingBananasProblem.java) | [Binary Search on Answer](./solutions/KokoEatingBananasSolution.java) |
| **Search in Rotated Sorted Array** | Medium | [Problem](./problems/SearchInRotatedSortedArrayProblem.java) | [Modified Binary Search](./solutions/SearchInRotatedSortedArraySolution.java) |

---

## 📝 TODO: Practice Problems

*   [Find Minimum in Rotated Sorted Array](https://leetcode.com/problems/find-minimum-in-rotated-sorted-array/) (Medium)
*   [Time Based Key-Value Store](https://leetcode.com/problems/time-based-key-value-store/) (Medium)
*   [Median of Two Sorted Arrays](https://leetcode.com/problems/median-of-two-sorted-arrays/) (Hard)
