# Topic: Stack

**Last Reviewed**: 2024-07-30
**Confidence Score (1-5)**: 4

## 🎯 Core Concept: The Power of LIFO

A Stack is a data structure that follows the **Last-In, First-Out (LIFO)** principle. The last element you add is the first one you can access or remove. Think of it like a stack of plates or a browser's back button history.

This LIFO property is not just a limitation; it's a powerful feature. It gives us a way to "remember" a history of states and reverse back through them. The ability to only process the `top` of the stack is what makes it perfect for problems involving nested structures, backtracking, or managing and restoring state.

---

## ⚙️ Common Patterns & Variations

### 1. Basic Stack (Validation & Calculation)

*   **Description**: The most direct use of a stack. You iterate through a sequence, pushing elements onto the stack. When you encounter a specific trigger (like a closing parenthesis or an operator), you pop elements, perform a calculation or validation, and push the result back.
*   **Best for**: Problems where the LIFO order directly matches the problem's logic, like parsing expressions or matching pairs.
*   **Used in**: `Valid Parentheses`, `Evaluate Reverse Polish Notation`.

### 2. Monotonic Stack

*   **Description**: A powerful variation where you maintain a strict order (either increasing or decreasing) of elements in the stack. When an incoming element would break this order, you pop elements from the stack and process them until the order is restored.
*   **Best for**: Problems that involve finding the "next greater/smaller element" or calculating the range of influence for an element (e.g., how far it can extend before hitting a smaller bar).
*   **Used in**: `Daily Temperatures`, `Largest Rectangle in Histogram`, `Trapping Rain Water`.

### 3. State Encoding & Design

*   **Description**: An advanced pattern where the stack is used not just to store data, but to manage the state of a system. This often involves storing custom objects, pairs, or encoded values that allow you to restore a previous state when an element is popped.
*   **Best for**: "Design" problems where you need to implement a data structure with special constraints, like O(1) retrieval of a minimum value.
*   **Used in**: `Min Stack`.

---

## ✅ Solved Problems Checklist

| Problem | Difficulty | Local Path | Implemented Solutions |
| :--- | :--- | :--- | :--- |
| **Valid Parentheses** | Easy | [Problem](./problems/ValidParentheses.java) | [Stack](./solutions/ValidParenthesesStackSolution.java) |
| **Min Stack** | Medium | [Problem](./problems/MinStackProblem.java) | [Two Stacks](./solutions/MinStackSolution.java), [Single Stack w/ Pairs](./solutions/MinStackSingleStackSolution.java), [Single Stack w/ Var](./solutions/MinStackSingleStackAndVarSolution.java) |
| **Evaluate RPN** | Medium | [Problem](./problems/EvaluateReversePolishNotation.java) | [Stack](./solutions/EvaluateReversePolishNotationStackSolution.java), [Recursion](./solutions/EvaluateReversePolishNotationRecursiveSolution.java), [LinkedList](./solutions/EvaluateReversePolishNotationLinkedListSolution.java) |
| **Generate Parentheses**| Medium | [Problem](./problems/GenerateParentheses.java) | [Backtracking](./solutions/GenerateParenthesesRecursiveSolution.java) |
| **Daily Temperatures** | Medium | [Problem](./problems/DailyTemperatures.java) | [Brute Force](./solutions/DailyTemperaturesBruteForceSolution.java), [Monotonic Stack](./solutions/DailyTemperaturesStackSolution.java), [DP](./solutions/DailyTemperaturesDPSolution.java) |
| **Car Fleet** | Medium | [Problem](./problems/CarFleetProblem.java) | [Iterative](./solutions/CarFleetIterativeSolution.java), [Stack](./solutions/CarFleetStackSolution.java) |
| **Largest Rectangle in Histogram** | Hard | [Problem](./problems/LargestRectangleInHistogramProblem.java) | [Prefix/Suffix](./solutions/LargestRectangleInHistogramPrefixSuffixSolution.java), [Monotonic Stack](./solutions/LargestRectangleInHistogramStackSolution.java) |

---

## 📝 TODO: Practice Problems

*   [Asteroid Collision](https://leetcode.com/problems/asteroid-collision/) (Medium)
*   [Simplify Path](https://leetcode.com/problems/simplify-path/) (Medium)
*   [Decode String](https://leetcode.com/problems/decode-string/) (Medium)
*   [Sliding Window Maximum](https://leetcode.com/problems/sliding-window-maximum/) (Medium)
*   [Next Greater Element II](https://leetcode.com/problems/next-greater-element-ii/) (Medium)
*   [Max chunks to make sorted](https://leetcode.com/problems/max-chunks-to-make-sorted/) (Medium)