package com.framework.patterns.stack;

import java.util.Stack;

/**
 * Pattern 69: Stack - Largest Rectangle in Histogram
 */
public class StackLargestRectangleHistogram {

    /*
     * =================================================================================
     * CONDITIONS FOR APPLICABILITY
     * =================================================================================
     *
     * 1.  Input Data Structure: An array of numbers representing the heights of bars in a histogram.
     *
     * 2.  Problem Goal: You need to find the area of the largest rectangle that can be formed under
     *     the histogram.
     *
     * 3.  Logic: This problem can be solved efficiently using a monotonic (increasing) stack. The key
     *     idea is that for any bar, the largest rectangle that has this bar as its height extends to the
     *     left and right until it hits a bar that is strictly shorter.
     *
     *     -   The monotonic stack will store the *indices* of the bars in increasing order of their height.
     *     -   Iterate through the histogram bars, including a virtual bar of height 0 at the end to ensure
     *       all bars in the stack are processed.
     *     -   For each bar `i`:
     *         -   **While the stack is not empty and the current bar's height is less than the height of the
     *           bar at the index on top of the stack**:
     *             -   This means the current bar `i` is the "next smaller element" for the bar on top of the stack.
     *             -   Pop the index from the stack, let's call it `h_idx`. The height of the rectangle is `heights[h_idx]`.
     *             -   The right boundary of the rectangle is the current index `i`.
     *             -   The left boundary is the index of the new top element of the stack (or -1 if the stack is now empty).
     *             -   Calculate the width: `width = i - (stack.isEmpty() ? -1 : stack.peek()) - 1`.
     *             -   Calculate the area and update the global maximum area.
     *         -   Push the current index `i` onto the stack.
     *
     * =================================================================================
     * GENERIC TEMPLATE (Largest Rectangle in Histogram)
     * =================================================================================
     */

    /**
     * Finds the area of the largest rectangle in the histogram.
     *
     * @param heights An array of integers representing the histogram's bar heights.
     * @return The area of the largest rectangle.
     */
    public int largestRectangleArea(int[] heights) {
        if (heights == null || heights.length == 0) {
            return 0;
        }

        Stack<Integer> stack = new Stack<>(); // Stores indices
        int maxArea = 0;

        // --- Core Pattern Logic ---
        for (int i = 0; i <= heights.length; i++) {
            // Add a virtual bar of height 0 at the end to process all remaining bars in the stack.
            int currentHeight = (i == heights.length) ? 0 : heights[i];

            // While stack is not empty and the bar at stack.peek() is taller than the current bar
            while (!stack.isEmpty() && heights[stack.peek()] > currentHeight) {
                int h = heights[stack.pop()]; // Height of the rectangle is the popped bar's height.
                
                // Width is from the new stack top to the current index `i`.
                int w = stack.isEmpty() ? i : i - stack.peek() - 1;
                
                maxArea = Math.max(maxArea, h * w);
            }

            // Push the current index onto the stack.
            stack.push(i);
        }
        // --------------------------

        return maxArea;
    }
}
