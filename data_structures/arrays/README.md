# Data Structure: Arrays

## 🎯 Core Concept

*   **What is it?** An array is the most fundamental data structure. It's a fixed-size, contiguous block of memory that holds elements of the same data type. Think of it as a numbered row of mailboxes, where each mailbox can hold one item and you can instantly access any mailbox just by knowing its number (the index).

*   **Key Characteristics**:
    *   **O(1) Access**: The biggest advantage of an array is its speed. Accessing any element by its index is an extremely fast, constant-time operation.
    *   **Fixed Size**: Once an array is created, its size cannot be changed. If you need more space, you have to create a new, larger array and copy the old elements over.
    *   **Contiguous Memory**: Elements are stored right next to each other in memory, which can be very efficient for the CPU to read (good cache locality).

---

## 💪 Strengths &  weaknesses

| Operation                      | Time Complexity | Note                                                                                             |
| ------------------------------ | --------------- | ------------------------------------------------------------------------------------------------ |
| **Access by Index**            | **O(1)**        | The primary strength of arrays.                                                                  |
| **Search (Unsorted)**          | O(n)            | You have to check every element one by one.                                                      |
| **Search (Sorted)**            | O(log n)        | You can use binary search, which is very efficient.                                              |
| **Insertion / Deletion (Middle)** | O(n)            | The main weakness. You have to shift all subsequent elements, which is slow.                     |
| **Insertion / Deletion (End)** | O(1)            | This is fast *if* the array has spare capacity. In Java, `ArrayList` handles this automatically. |

---

## ⚙️ Common Array Traversal Patterns

Here are some of the most powerful techniques for solving array-based problems.

*   **Two Pointers**: Use two different pointers to iterate through the array in a coordinated way. This is often used to avoid nested loops.
    *   *Opposite Ends*: One pointer at the start, one at the end, moving towards each other. Great for sorted arrays (e.g., Two Sum II).
    *   *Fast/Slow*: Both pointers start at the beginning, but one moves faster than the other. Great for cycle detection or problems like "Remove Duplicates."

*   **Sliding Window**: A sub-array (the "window") of a fixed or variable size slides over the main array. This is perfect for problems involving finding the best sub-array (e.g., "find the sub-array with the maximum sum").

*   **Prefix/Suffix Sums**: Pre-compute the sums of all prefixes (or suffixes) of an array. This allows you to calculate the sum of any sub-array in O(1) time, which is a huge performance win for problems with many range sum queries.
    *   *Used in*: `Product of Array Except Self`.

*   **Index as a Hash Key**: A clever trick for arrays where the values are in a known, limited range (e.g., numbers from 1 to N). You can use the *value* of an element as an *index* to store information. For example, to mark a number as "seen," you could negate the value at its corresponding index (`nums[value] = -nums[value]`).

---

## ☕ Java-Specific Tips & Tricks

*   **Initialization**: `int[] arr = new int[10];` or `int[] arr = {1, 2, 3};`
*   **Sorting**: `Arrays.sort(arr);` // Sorts the array in-place.
*   **Copying**: `int[] newArr = Arrays.copyOf(originalArr, originalArr.length);`
*   **Filling**: `Arrays.fill(arr, -1);` // Fills the entire array with -1.
*   **Printing**: `System.out.println(Arrays.toString(arr));` For 2D arrays, use `Arrays.deepToString(matrix);`.
*   **Comparing**: `Arrays.equals(arr1, arr2);` For 2D arrays, use `Arrays.deepEquals(matrix1, matrix2);`.

---

## 🔗 Problem Checklist

This is a list of problems where arrays are the primary data structure. Many of these also leverage hashing techniques.

| Problem                                       | Difficulty | Key Takeaway                                                              |
| --------------------------------------------- | ---------- | ---------------------------------------------------------------------------- |
| [Contains Duplicate](https://leetcode.com/problems/contains-duplicate/) | Easy       | A great warm-up. Can be solved with a sort (O(n log n)) or a HashSet (O(n)). |
| [Valid Anagram](https://leetcode.com/problems/valid-anagram/) | Easy       | Sorting both strings is a simple array-based solution.                       |
| [Two Sum](https://leetcode.com/problems/two-sum/) | Easy       | The brute-force approach uses nested loops (O(n^2)). Hashing provides an O(n) solution. |
| [Group Anagrams](https://leetcode.com/problems/group-anagrams/) | Medium     | The key is to find a canonical representation for anagrams, like a sorted string. |
| [Top K Frequent Elements](https://leetcode.com/problems/top-k-frequent-elements/) | Medium     | A multi-step problem. Often involves frequency counting (hashing) and then sorting or using a heap. |
| [Product of Array Except Self](https://leetcode.com/problems/product-of-array-except-self/) | Medium     | The classic use case for prefix and suffix products to achieve an O(n) solution without division. |
| [Valid Sudoku](https://leetcode.com/problems/valid-sudoku/) | Medium     | Involves iterating through a 2D array (matrix) and using sets to validate rows, columns, and boxes. |
| [Longest Consecutive Sequence](https://leetcode.com/problems/longest-consecutive-sequence/) | Medium     | Sorting is an intuitive O(n log n) first step, but a `HashSet` allows for a more optimal O(n) solution. |
