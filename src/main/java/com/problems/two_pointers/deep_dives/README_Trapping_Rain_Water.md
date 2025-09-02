# Deep Dive: The Stack Solution for Trapping Rain Water

This document provides a detailed, illustrated walkthrough of the stack-based solution for the "Trapping Rain Water" problem. The core of this algorithm is a monotonic decreasing stack, but its true power lies in how it calculates trapped water by processing layers of a basin.

---

### The Core Idea

The stack stores the indices of bars that form a potential **left boundary** of a container. We iterate through the elevation map, and when we find a bar that is taller than the bar at the top of the stack, we know we have found a **right boundary**. This allows us to calculate the water trapped on the bar(s) that were just popped.

The key insight is that the algorithm calculates water in **horizontal layers**. It finds a basin, calculates the water in the lowest part of it, and then, if a taller wall appears later, it calculates the water in the upper parts of the same basin.

---

### Illustrated Walkthrough

Let's trace the most revealing scenario: `height = [5, 3, 2, 3, 5]`

#### Initial State

We process `5`, `3`, and `2`. Since each is smaller than the last, they are all pushed onto the stack.

*   **Stack (indices):** `[0, 1, 2]` (corresponding to heights `[5, 3, 2]`)

```
      █
      █
      █  █
      █  █  █
      █  █  █
    -----------
idx:  0  1  2
```

#### Step 1: The First Right Wall Appears (the second `3`)

*   **`current` bar:** `height[3]` which is `3`.
*   The condition `height[current] >= height[stack.peek()]` (`3 >= 2`) is **true**.

**Action:** We pop the `2` (at index 2) and treat it as the `bottom` of a basin.
*   **Left Wall:** `height[stack.peek()]` -> `height[1]` -> `3`
*   **Right Wall:** `height[current]` -> `height[3]` -> `3`
*   **Water Level:** `min(3, 3) = 3`
*   **Trapped Water:** `(waterLevel - bottomHeight) * width` -> `(3 - 2) * (3 - 1 - 1) = 1 * 1 = 1`

```
      █
      █
      █  █
      █  █ W █   (W = Water, total = 1)
      █  █ █ █
    -----------
idx:  0  1 2 3
```

Now, the `while` loop continues because `height[current]` (3) is still `>=` the new `stack.peek()` (3). We pop the first `3` (at index 1).

*   **Left Wall:** `height[stack.peek()]` -> `height[0]` -> `5`
*   **Right Wall:** `height[current]` -> `height[3]` -> `3`
*   **Water Level:** `min(5, 3) = 3`
*   **Trapped Water:** `(waterLevel - bottomHeight)` -> `(3 - 3) = 0`. No new water is calculated, which is correct.

Finally, we push the current index `3` onto the stack.

*   **Stack:** `[0, 3]` (corresponding to heights `[5, 3]`)
*   **Total Water:** `1`

#### Step 2: The Final Wall Appears (the final `5`)

This is the most important step.

*   **`current` bar:** `height[4]` which is `5`.
*   The condition `height[current] >= height[stack.peek()]` (`5 >= 3`) is **true**.

**Action:** We pop the `3` (at index 3) and treat it as the `bottom`.
*   **Left Wall:** `height[stack.peek()]` -> `height[0]` -> `5`
*   **Right Wall:** `height[current]` -> `height[4]` -> `5`
*   **Water Level:** `min(5, 5) = 5`
*   **Trapped Water:** `(waterLevel - bottomHeight) * width` -> `(5 - 3) * (4 - 0 - 1) = 2 * 3 = 6`

This is where the magic happens. We are not just calculating water over the single bar we popped. The `width` of `3` covers the entire basin (`[3, 2, 3]`). We are calculating the water in the **upper layer** of the basin.

```
      █ W W W █   (New water layer, height=2, width=3. Total = 1 + 6 = 7)
      █ W W W █
      █ █ W █ █
      █ █ W █ █
      █ █ █ █ █
    -----------
idx:  0 1 2 3 4
```

*   **Total Water:** `1 (from lower layer) + 6 (from upper layer) = 7`

### Conclusion

The stack does not need to remember every single bar that was part of a basin's floor. It only needs to remember the **left-side boundaries**. When a right wall appears, the algorithm calculates the water trapped on top of the popped `bottom` bar. If a *taller* right wall appears later, the algorithm will re-process the basin and calculate the water in the **new, higher layers**. This layered calculation ensures that every drop of water is counted exactly once.
