# Algorithmic Pattern: Backtracking

Backtracking is a powerful algorithmic technique for solving problems that involve finding all (or some) solutions by incrementally building candidates and abandoning a candidate ("backtracking") as soon as it is determined that it cannot possibly be completed to a valid solution.

---

## 🎯 Core Concept: The Decision Tree

At its heart, backtracking is about exploring a **decision tree**. Even when a problem doesn't explicitly mention a tree, the recursive solution implicitly builds and traverses one in memory via the call stack. The "subsets" problem is a perfect example.

**The Problem**: Given a set `[1, 2, 3]`, find all possible subsets.

**The Decision Tree**: For each number, you have two choices: **include** it in the current subset or **exclude** it. This creates a binary decision tree where each level corresponds to a number from the input set. A path from the root to any node in the tree represents a subset.

```
                        []
                 /            \
            (include 1)      (exclude 1)
               /                \
             [1]                  []
            /   \                /  \
      (inc 2) (exc 2)        (inc 2) (exc 2)
         /       \              /       \
      [1,2]      [1]          [2]        []
      /  \      /  \          /  \      /  \
 (inc 3)(exc 3)(inc 3)(exc 3)(inc 3)(exc 3)(inc 3)(exc 3)
   /      \    /      \    /      \    /      \
[1,2,3] [1,2] [1,3]   [1]  [2,3]   [2]  [3]      []
```

---

## 💡 The Backtracking Mantra: Choose, Recurse, Unchoose

The standard backtracking template directly simulates a traversal of this decision tree.

```java
private void backtrack(State state, List<Solution> results) {
    // Optional: Base case to stop if a valid solution is found.
    if (isACompleteSolution(state)) {
        results.add(format(state));
        return;
    }

    // Iterate through all possible choices from the current state.
    for (Choice choice : getValidChoices(state)) {
        
        // 1. Choose: Make a choice and update the state.
        state.add(choice);

        // 2. Recurse: Explore the consequences of this choice.
        backtrack(state, results);

        // 3. Unchoose (Backtrack): Undo the choice to explore other paths.
        state.remove(choice);
    }
}
```

This mental model is incredibly powerful. When you see a problem that asks for "all possible combinations," "all permutations," or "all valid arrangements," try to frame it as a decision tree. The recursive backtracking solution will often map directly to a traversal of that tree.

---

## 📚 Backtracking Patterns & Templates

This directory contains templates for common backtracking problems:

*   [Subsets](./BacktrackingSubsets.java)
*   [Permutations](./BacktrackingPermutations.java)
*   [Combination Sum](./BacktrackingCombinationSum.java)
*   [Parentheses Generation](./BacktrackingParentheses.java)
*   [Word Search (Grid Path Finding)](./BacktrackingWordSearch.java)
*   [N-Queens (Constraint Satisfaction)](./BacktrackingNQueens.java)
*   [Palindrome Partitioning](./BacktrackingPalindromePartitioning.java)
