# A Guide to Defining Recursive Solutions

Recursion is a powerful technique where a function calls itself to solve smaller instances of the same problem. Mastering it requires a structured way of thinking. Follow this guide whenever you need to define a recursive solution.

---

## The 3 Core Components of Any Recursive Solution

Every correct recursive algorithm is built on three fundamental components. Always define them in this order.

### 1. The Base Case(s)

*   **The Question to Ask**: "What is the absolute simplest version of this problem that I can solve directly, without any further recursion?"
*   **What it does**: The base case is the **stopping condition**. It prevents infinite loops and provides the concrete value that the other recursive calls will build upon.

### 2. The Recursive Relation (The "Work")

*   **The Question to Ask**: "How can I use the solution to a slightly *smaller* version of this problem to help me solve the *current* version?"
*   **What it does**: This is the core logic of the recursion. It defines how the problem is broken down into smaller, self-similar subproblems.

### 3. The Combination Step (or "Constraint Step" in Backtracking)

*   **The Question to Ask**: "How do I combine the result from my recursive call(s) with the work I did in the current step?" Or, for backtracking: **"What constraints must I check *before* making a recursive call to ensure I am on a valid path?"**
*   **What it does**: This is where you either merge results from subproblems or, more importantly in backtracking, you prune invalid paths by checking the current state against the problem's rules.

---

## State Management: Call Stack vs. Global Variables

A critical design choice in recursion is how to manage the state of your exploration. There are two main approaches:

1.  **Passing State via Parameters (The Preferred Method)**
    *   **How it Works**: Each recursive call is given its own complete, independent state as part of its parameters (e.g., `backtrack(currentString, open, close)`). The state for a particular path exists only on the call stack for that path.
    *   **Why it's Better**:
        *   **Clean & Self-Contained**: The function doesn't rely on or modify any external variables. This makes it easier to reason about, test, and debug.
        *   **No Manual Cleanup**: When a recursive call finishes, its state simply vanishes as it's popped off the call stack. You don't need to manually "undo" your choices (e.g., `currentString.deleteCharAt(currentString.length() - 1)`).
        *   **Safe for Parallelism**: While not always relevant in interviews, this approach is inherently safer for concurrent execution because it avoids shared mutable state.

2.  **Using a Global or Member Variable (The Mutable State Approach)**
    *   **How it Works**: A single, shared variable (like a `StringBuilder` member variable) is used. Each recursive call modifies this shared variable before making the next call.
    *   **The Critical Flaw & Required Fix**: After a recursive call returns, the shared variable is still in its modified state. You **must** manually revert or "undo" the change you made before the next branch of the exploration can proceed correctly. This is a common source of bugs.

    ```java
    // Example of the mutable state approach - REQUIRES MANUAL CLEANUP
    private void backtrack(List<String> result, StringBuilder currentString, ...) {
        // ... base case ...

        // Explore one path
        currentString.append("(");
        backtrack(result, currentString, ...);
        currentString.deleteCharAt(currentString.length() - 1); // <-- CRITICAL: Manual cleanup/undo

        // Explore another path
        currentString.append(")");
        backtrack(result, currentString, ...);
        currentString.deleteCharAt(currentString.length() - 1); // <-- CRITICAL: Manual cleanup/undo
    }
    ```

**Conclusion**: For interview settings, always prefer passing state through parameters. It leads to cleaner, safer, and easier-to-understand code, which is exactly what interviewers are looking for.

---

## A Practical Example: "Generate Parentheses"

Let's apply the preferred method (passing state via parameters) to the "Generate Parentheses" problem for `n = 2`.

### The Decision Tree: Visualizing the State and Constraints

This tree shows how we start from an empty state and explore paths. At each node, we check our constraints **before** deciding to make the next recursive call.

**The Constraints:**
1.  **Add `(`?** Only if `open < n`.
2.  **Add `)`?** Only if `close < open`.

```
                            (start)
                         o=0, c=0, s=""
                              |
                      (Add '(' ? open<2 -> YES)
                              |
                            ( A )
                         o=1, c=0, s="("
                         /            \
        (Add '(' ? open<2 -> YES)   (Add ')' ? close<open -> YES)
                       /                  \
                     ( B )                ( C )
                  o=2, c=0, s="(("        o=1, c=1, s="()"
                      |                       |
        (Add '(' ? open<2 -> NO)     (Add '(' ? open<2 -> YES)
        (Add ')' ? close<open -> YES)         |
                      |                     ( D )
                    ( E )              o=2, c=1, s="()("
                 o=2, c=1, s="(()"            |
                      |           (Add '(' ? open<2 -> NO)
        (Add '(' ? open<2 -> NO)     (Add ')' ? close<open -> YES)
        (Add ')' ? close<open -> YES)         |
                      |                     ( F )
                    ( G )              o=2, c=2, s="()()"
                 o=2, c=2, s="(())"          |
                      |                  (BASE CASE)
                  (BASE CASE)              (Add to result)
                  (Add to result)
```

### The Final Algorithm

```java
private void backtrack(List<String> result, String currentString, int open, int close, int n) {
    // 1. Base Case: We've successfully built a full string.
    if (currentString.length() == 2 * n) {
        result.add(currentString);
        return;
    }

    // 2. Constraint Check & Recursive Call for '('
    if (open < n) {
        backtrack(result, currentString + "(", open + 1, close, n);
    }

    // 3. Constraint Check & Recursive Call for ')'
    if (close < open) {
        backtrack(result, currentString + ")", open, close + 1, n);
    }
}
```
