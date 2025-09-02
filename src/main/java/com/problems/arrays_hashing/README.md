# Topic: Hashing & Arrays

**Last Reviewed**: 2024-07-30
**Confidence Score (1-5)**: 4

## 🎯 Core Concept

* **What is it?** Hashing is like creating a super-efficient filing system for your data. You take a piece of data (like a string or number), run it through a "hash function," and get a unique-ish key for it. We use this key to store the data in a special data structure, usually a `HashMap` or `HashSet`. This allows for incredibly fast lookups.

* **Why is it useful?** Speed! A well-implemented hash table gives us **O(1)** average time complexity for inserting, deleting, and—most importantly—searching for data. This is a massive improvement over searching through an array, which takes O(n) time. The trade-off is that we use extra memory (space) to store the hash table itself.

---

## 🤔 When to Use This Pattern

Look for these key indicators in a problem description:

* You need to **count the frequency** of items in a list (e.g., "find the most common element").
* You need to check for the **existence of duplicates** or if you've "seen" an element before.
* You need to **group items** together based on a shared property (like grouping anagrams).
* The problem asks you to find a pair of numbers that add up to a target (like in "Two Sum"). Hashing lets you instantly check if the complement `(target - num)` exists.

---

## ✅ Solved Problems Checklist

| Problem | Difficulty | Local Path | Implemented Solutions |
| :--- | :--- | :--- | :--- |
| **Contains Duplicate** | Easy | [Problem](./problems/ContainsDuplicate.java) | [Set](./solutions/ContainsDuplicateUsingSetSolution.java), [Sort](./solutions/ContainsDuplicateUsingSortSolution.java) |
| **Valid Anagram** | Easy | [Problem](./problems/ValidAnagram.java) | [Map](./solutions/ValidAnagramUsingMapSolution.java), [Sort](./solutions/ValidAnagramUsingSortSolution.java) |
| **Two Sum** | Easy | [Problem](./problems/TwoSum.java) | [Map](./solutions/TwoSumUsingMapSolution.java) |
| **Group Anagrams** | Medium | [Problem](./problems/GroupAnagrams.java) | [Map](./solutions/GroupAnagramsUsingMapSolution.java), [Sort](./solutions/GroupAnagramsUsingSortSolution.java) |
| **Top K Frequent Elements** | Medium | [Problem](./problems/TopKFrequentElements.java) | [Sorting](./solutions/TopKFrequentElementsUsingSortingSolution.java), [Heap](./solutions/TopKFrequentElementsUsingMapAndPQSolution.java), [Bucket Sort](./solutions/TopKFrequentElementsUsingMapAndBucketSortSolution.java) |
| **Product of Array Except Self** | Medium | [Problem](./problems/ProductOfArrayExceptSelf.java) | [Brute Force](./solutions/ProductOfArrayExceptSelfBruteForce.java), [Prefix/Suffix](./solutions/ProductOfArrayExceptSelfPrefixSuffixSolution.java), [Optimal](./solutions/ProductOfArrayExceptSelfPrefixSuffixOptimalSolution.java) |
| **Valid Sudoku** | Medium | [Problem](./problems/ValidSudoku.java) | [Sets](./solutions/ValidSudokuUsingSetsSolution.java) |
| **Encode and Decode Strings** | Medium | [Problem](./problems/EncodeAndDecodeStrings.java) | [Delimiter](./solutions/EncodeAndDecodeStringsSolution.java) |
| **Longest Consecutive Sequence** | Medium | [Problem](./problems/LongestConsecutiveSequence.java) | [Brute Force](./solutions/LongestConsecutiveSequenceBFSolution.java), [Sorting](./solutions/LongestConsecutiveSequenceSortingSolution.java), [Set](./solutions/LongestConsecutiveSequenceUsingSetSolution.java) |

---

## 📝 TODO: Practice Problems

*   [Subarray Sum Equals K](https://leetcode.com/problems/subarray-sum-equals-k/) (Medium)
*   [Find All Anagrams in a String](https://leetcode.com/problems/find-all-anagrams-in-a-string/) (Medium)
*   [Insert Delete GetRandom O(1)](https://leetcode.com/problems/insert-delete-getrandom-o1/) (Medium)
