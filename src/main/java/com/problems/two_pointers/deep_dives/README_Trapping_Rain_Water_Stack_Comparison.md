# Stack Solution for Trapping Rain Water: `>` vs. `>=`

This document provides a detailed comparison of two common implementations of the stack-based solution for the "Trapping Rain Water" problem. The key difference lies in the `while` loop condition: using a strictly decreasing stack (`>`) versus a non-increasing stack (`>=`).

While both approaches are correct, understanding their subtle differences reveals a deeper insight into the algorithm's mechanics.

---

### The Scenario

We will use the following simple test case to trace both implementations:

`height = [3, 2, 2, 5]`

---

## Approach 1: The `>` Condition (Strictly Decreasing Stack)

In this version, the `while` loop only runs if the current bar is **strictly greater** than the bar at the top of the stack. This means equal-height bars are simply pushed onto the stack.

**Rule:** `while (!stack.isEmpty() && height[current] > height[stack.peek()])`

#### Trace

| Current `i` | `height[i]` | Stack (before) | `while` loop? | Action & Calculation | Stack (after) | `totalWater` |
| :--- | :---: | :--- | :---: | :--- | :--- | :---: |
| 0 | 3 | `[]` | No | `push(0)` | `[0]` | 0 |
| 1 | 2 | `[0]` | No | `push(1)` | `[0, 1]` | 0 |
| 2 | 2 | `[0, 1]` | No | `push(2)` | `[0, 1, 2]` | 0 |
| 3 | 5 | `[0, 1, 2]` | **Yes** | Pop `2`. Left wall `1`. `min(2,5)-2=0`. Water=0. | `[0, 1]` | 0 |
| | | `[0, 1]` | **Yes** | Pop `1`. Left wall `0`. `min(3,5)-2=1`. Width=2. Water=2. | `[0]` | **2** |
| | | `[0]` | **Yes** | Pop `0`. Stack empty. Break. | `[]` | 2 |
| | | `[]` | No | `push(3)` | `[3]` | 2 |

*   **Behavior**: This approach accumulates all equal-height bars (the plateau) onto the stack first. It only processes them once a taller right wall is found.
*   **Analogy**: It builds the entire flat floor of a pool first, then calculates the water volume above that floor all at once.

---

## Approach 2: The `>=` Condition (Non-Increasing Stack)

In this version, the `while` loop runs if the current bar is **greater than or equal to** the bar at the top of the stack.

**Rule:** `while (!stack.isEmpty() && height[current] >= height[stack.peek()])`

#### Trace

| Current `i` | `height[i]` | Stack (before) | `while` loop? | Action & Calculation | Stack (after) | `totalWater` |
| :--- | :---: | :--- | :---: | :--- | :--- | :---: |
| 0 | 3 | `[]` | No | `push(0)` | `[0]` | 0 |
| 1 | 2 | `[0]` | No | `push(1)` | `[0, 1]` | 0 |
| 2 | 2 | `[0, 1]` | **Yes** | Pop `1`. Left wall `0`. `min(3,2)-2=0`. Water=0. | `[0]` | 0 |
| | | `[0]` | No | `push(2)` | `[0, 2]` | 0 |
| 3 | 5 | `[0, 2]` | **Yes** | Pop `2`. Left wall `0`. `min(3,5)-2=1`. Width=2. Water=2. | `[0]` | **2** |
| | | `[0]` | **Yes** | Pop `0`. Stack empty. Break. | `[]` | 2 |
| | | `[]` | No | `push(3)` | `[3]` | 2 |

*   **Behavior**: This approach processes an equal-height bar immediately. It uses the new bar as a right wall for the previous one, correctly calculates zero trapped water, and effectively "updates" the position of the plateau's rightmost edge.
*   **Analogy**: It's like sliding a floor tile along. It lays one down, then immediately picks it up and lays the next one down, until it hits the final wall.

---

## Conclusion

| Aspect | `>` (Strictly Decreasing) | `>=` (Non-Increasing) |
| :--- | :--- | :--- |
| **Correctness** | ✅ Correct | ✅ Correct |
| **Time Complexity** | O(N) | O(N) |
| **Key Difference** | Processes plateaus **reactively** (after finding a taller wall). | Processes plateaus **proactively** (as soon as an equal wall is found). |

Both methods are valid and effective. The choice between them is a matter of implementation style, as they both correctly handle all scenarios, including plateaus. Understanding why both lead to the same result demonstrates a deep comprehension of the algorithm.
