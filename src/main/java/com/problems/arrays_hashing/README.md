# Topic: Hashing

**Last Reviewed**: 2024-07-30
**Confidence Score (1-5)**: 4

## 🎯 Core Concept

* **What is it?** Hashing is like creating a super-efficient filing system for your data. You take a piece of data (like a string or number), run it through a "hash function," and get a unique-ish key for it. We use this key to store the data in a special data structure, usually a `HashMap` or `HashSet`. Think of it like a coat check: you hand over your coat (the data), you get a small ticket (the hash key), and you can get your coat back instantly just by showing the ticket, without searching through all the other coats.

* **Why is it useful?** Speed! A well-implemented hash table gives us **O(1)** average time complexity for inserting, deleting, and—most importantly—searching for data. This is a massive improvement over searching through an array, which takes O(n) time. The trade-off is that we use extra memory (space) to store the hash table itself.

---

## 🤔 When to Use This Pattern

Look for these key indicators in a problem description:

* You need to **count the frequency** of items in a list (e.g., "find the most common element").
* You need to check for the **existence of duplicates** or if you've "seen" an element before.
* You need to **group items** together based on a shared property (like grouping anagrams).
* The problem asks you to find a pair of numbers that add up to a target (like in "Two Sum"). Hashing lets you instantly check if the complement `(target - num)` exists.
* The problem constraints allow for O(n) space complexity, suggesting that a time-for-space trade-off is acceptable.

---

## ⚙️ Common Patterns & Variations

Here are the main ways we've used hashing in our solutions.

### 1. Existence Checking (`HashSet`)

* **Description**: This is the simplest pattern. You use a `HashSet` to keep track of all the elements you've encountered so far. Before processing a new element, you check if it's already in the set.
* **Best for**: Problems like "Contains Duplicate" or "Valid Sudoku" where you just need a fast yes/no answer to the question, "Have I seen this before?"

### 2. Frequency Counting (`HashMap`)

* **Description**: Use a `HashMap` to map each unique element to its frequency (count). You iterate through the input, and for each element, you increment its count in the map.
* **Best for**: Problems like "Top K Frequent Elements" where you need to know not just *if* an element exists, but *how many times* it appears.

### 3. Grouping by a Canonical Key (`HashMap`)

* **Description**: This is a powerful variation where you compute a "canonical" (standard) representation for each item and use that as the key in a `HashMap`. All items that map to the same key belong to the same group.
* **Best for**: Problems like "Group Anagrams." Anagrams, when sorted, produce the same string. This sorted string is the perfect canonical key.

---

## ☕ Java-Specific Tips & Tricks

Here are a few handy Java-specific methods and patterns that make implementing hashing solutions cleaner and more efficient.

### 1. The Power of `getOrDefault` for Frequency Counting
Instead of writing an `if/else` block to handle the first time you see an element, you can use `map.getOrDefault(key, 0) + 1` as a clean one-liner to increment a frequency count. It's readable and concise.

* **Used in**: `Top K Frequent Elements`
* **Code Snippet**:
    ```java
    Map<Integer, Integer> counts = new HashMap<>();
    for (int num : nums) {
        counts.put(num, counts.getOrDefault(num, 0) + 1);
    }
    ```

### 2. `computeIfAbsent` for Grouping
This is the perfect tool for the grouping pattern. `map.computeIfAbsent(key, k -> new ArrayList<>())` will look up a key. If the key is missing, it will execute the lambda function (creating a new `ArrayList`), put it in the map, and return it. If the key already exists, it just returns the existing value. This lets you get a list and add to it in a single, elegant line.

* **Used in**: `Group Anagrams`
* **Code Snippet**:
    ```java
    Map<String, List<String>> groups = new HashMap<>();
    for (String s : strs) {
        String key = createCanonicalKey(s);
        groups.computeIfAbsent(key, k -> new ArrayList<>()).add(s);
    }
    ```

### 3. Using an `int[]` as a Simple Frequency Map
When you know the character set is limited (e.g., only lowercase English letters), you can use a simple `int[26]` array instead of a `HashMap`. This is faster and uses less memory. The character itself helps you find the index.

* **Used in**: `Valid Anagram`
* **Code Snippet**:
    ```java
    int[] charCounts = new int[26];
    for (char c : s.toCharArray()) {
        charCounts[c - 'a']++; // 'a' becomes index 0, 'b' becomes 1, etc.
    }
    ```

### 4. Crafting Unique String Keys for Complex Lookups
For a problem like "Valid Sudoku," you need to check rows, columns, AND 3x3 boxes. Instead of managing nine different `HashSet`s for the boxes, you can use a single set by creating unique string keys. By encoding the coordinates into the key, you can validate everything with one data structure.

* **Used in**: `Valid Sudoku`
* **Code Snippet**:
    ```java
    Set<String> seen = new HashSet<>();
    for (int i = 0; i < 9; i++) {
        for (int j = 0; j < 9; j++) {
            char currentVal = board[i][j];
            if (currentVal != '.') {
                if (!seen.add("row" + i + "-" + currentVal) ||
                    !seen.add("col" + j + "-" + currentVal) ||
                    !seen.add("box" + (i/3) + "-" + (j/3) + "-" + currentVal)) {
                    return false; // Duplicate found
                }
            }
        }
    }
    ```

---

## 🔗 Additional Problems Checklist

| Problem                                       | Difficulty | My Key Takeaway                                                              |
| --------------------------------------------- | ---------- | ---------------------------------------------------------------------------- |
| [Contains Duplicate](https://leetcode.com/problems/contains-duplicate/) | Easy       | The simplest use of a `HashSet`. Just add elements and check if they exist.  |
| [Valid Anagram](https://leetcode.com/problems/valid-anagram/) | Easy       | A great intro to frequency counting. An `int[26]` array is often better than a `HashMap`. |
| [Two Sum](https://leetcode.com/problems/two-sum/) | Easy       | The classic "check for existence" problem. For each `num`, check if `target - num` is in the map. |
| [Group Anagrams](https://leetcode.com/problems/group-anagrams/) | Medium     | The canonical key pattern is king here. Sorting the string is a great way to create the key. |
| [Top K Frequent Elements](https://leetcode.com/problems/top-k-frequent-elements/) | Medium     | A two-step hash problem: first, use a `HashMap` for frequency counting, then use a heap or bucket sort to find the top K. |
| [Product of Array Except Self](https://leetcode.com/problems/product-of-array-except-self/) | Medium     | While not a hashing problem, it's often grouped here. The key is using prefix/suffix products, not division. |
| [Valid Sudoku](https://leetcode.com/problems/valid-sudoku/) | Medium     | Use `HashSet`s to track seen numbers. Crafting unique string keys for rows, cols, and boxes is a clever trick. |
| [Encode and Decode Strings](https://leetcode.com/problems/encode-and-decode-strings/) | Medium     | A design problem. The key is creating a simple protocol (e.g., `length#string`) to handle all edge cases. |
| [Longest Consecutive Sequence](https://leetcode.com/problems/longest-consecutive-sequence/) | Medium     | A clever use of a `HashSet`. Only start counting a sequence from a number that doesn't have a smaller consecutive number (`num - 1`) in the set. |
