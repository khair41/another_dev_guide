# Topic: Stack

**Last Reviewed**: 2024-07-30
**Confidence Score (1-5)**: 4

## 🎯 Core Concept: The Power of LIFO

A Stack is a data structure that follows the **Last-In, First-Out (LIFO)** principle. The last element you add is the first one you can access or remove. Think of it like a stack of plates or a browser's back button history.

This LIFO property is not just a limitation; it's a powerful feature. It gives us a way to "remember" a history of states and reverse back through them. The ability to only process the "head" or `top` of the stack is what makes it perfect for problems involving nested structures, backtracking, or, as we will see, managing and restoring state.


---

## ✅ Solved Problems Checklist

| Problem | Difficulty | Local Path | Implemented Solutions |
| :--- | :--- | :--- | :--- |
| **Valid Parentheses** | Easy | [Problem](./problems/ValidParentheses.java) | [Stack](./solutions/ValidParenthesesStackSolution.java) |
| **Min Stack** | Medium | [Problem](./problems/MinStackProblem.java) | [Two Stacks](./solutions/MinStackSolution.java), [Single Stack w/ Pairs](./solutions/MinStackSingleStackSolution.java), [Single Stack w/ Var](./solutions/MinStackSingleStackAndVarSolution.java) |
