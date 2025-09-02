# Deep Dive: The Two Pointers Solution for Trapping Rain Water

This document provides a detailed, illustrated walkthrough of the O(1) space, Two Pointers solution for the "Trapping Rain Water" problem. This approach is a space-optimized version of the Dynamic Programming (Prefix/Suffix) method.

---

### The Core Idea

We know that the water trapped above any bar is determined by the shorter of two walls: the tallest bar to its left (`leftMax`) and the tallest bar to its right (`rightMax`).

`water_at_i = min(leftMax, rightMax) - height[i]`

The brute-force and DP solutions calculate these exact values. The Two Pointers solution works by a clever realization: you don't always need to know the *absolute* `leftMax` and `rightMax`. You only need to know which one is **smaller**.

We use two pointers, `left` and `right`, at the ends of the array, and two variables, `leftMax` and `rightMax`, to track the max height seen so far from each side as the pointers move inward.

### The Key Intuition

This is the most important part of the algorithm.

At any point, we compare `leftMax` and `rightMax`:

1.  **If `leftMax < rightMax`:**
    *   This is a guarantee that, for the current `left` pointer, the `leftMax` is the **limiting wall**. We know `rightMax` is taller, and the true tallest wall on the right side can only be even taller or the same. So, the water level at `left` is determined *only* by `leftMax`.
    *   We can confidently calculate the trapped water at `height[left]` as `leftMax - height[left]` and add it to our total.
    *   We then move the `left` pointer inward.

2.  **If `rightMax <= leftMax`:**
    *   By the same logic, we know that `rightMax` is the **limiting wall** for the current `right` pointer.
    *   We can confidently calculate the trapped water at `height[right]` as `rightMax - height[right]`.
    *   We then move the `right` pointer inward.

By always processing the side with the smaller max wall, we ensure our calculations are correct without needing extra arrays for storage.

---

### Illustrated Walkthrough

Let's trace the scenario: `height = [4, 2, 0, 3, 2, 5]`

**Initial State:**
*   `left = 0`, `right = 5`
*   `leftMax = 0`, `rightMax = 0`
*   `totalWater = 0`

```
          █
█ W W █ W █   (W = Water we will calculate)
█ W w █ W █
█ █ W █ █ █
█ █ W █ █ █
-----------
4 2 0 3 2 5
L         R
```

| `left` | `right` | `leftMax` | `rightMax` | Condition | Action | `totalWater` |
| :---: | :---: | :---: | :---: | :--- | :--- | :---: |
| 0 | 5 | 0 | 0 | - | Initial state | 0 |
| 0 | 5 | 4 | 5 | `leftMax < rightMax` | Process `left`. `leftMax` becomes 4. `left++`. | 0 |
| 1 | 5 | 4 | 5 | `leftMax < rightMax` | Process `left`. `height[1]`=2. Water=`4-2=2`. `left++`. | 2 |
| 2 | 5 | 4 | 5 | `leftMax < rightMax` | Process `left`. `height[2]`=0. Water=`4-0=4`. `left++`. | 6 |
| 3 | 5 | 4 | 5 | `leftMax < rightMax` | Process `left`. `height[3]`=3. Water=`4-3=1`. `left++`. | 7 |
| 4 | 5 | 4 | 5 | `leftMax < rightMax` | Process `left`. `height[4]`=2. Water=`4-2=2`. `left++`. | 9 |
| 5 | 5 | 4 | 5 | Loop terminates | | **9** |

### Conclusion

This Two Pointers approach is a masterpiece of optimization. It correctly deduces the trapped water at each step by only considering the smaller of the two maximum boundary walls, thereby eliminating the need for extra storage arrays. It solves the problem in a single pass with constant extra space, making it the most efficient solution possible.
