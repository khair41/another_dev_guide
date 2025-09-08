package com.problems.stack.solutions;

import com.framework.Solution;

import java.util.Stack;

public class LargestRectangleInHistogramStackSolution implements Solution<int[], Integer> {

    /*
     * --- APPROACH ---
     * Use a monotonic increasing stack to store the indices of the histogram bars.
     * When we encounter a bar that is shorter than the bar at the top of the stack, it means
     * the bar at the top of the stack can no longer extend to the right. Its right boundary is the
     * current bar, and its left boundary is the new top of the stack.
     *
     * --- INTUITION ---
     * For any bar, its area is `height * width`. The `width` is determined by how far it can
     * extend left and right before hitting a shorter bar. The monotonic stack is a perfect tool
     * for finding the `nextSmaller` and `prevSmaller` elements for each bar in a single pass.
     * When we pop a bar `h` from the stack, the `current` bar `i` is its `nextSmaller` element, and the
     * new `stack.peek()` is its `prevSmaller` element.
     *
     * --- COMPLEXITY ---
     *
     *   - Time: O(N)
     *     [Each index is pushed onto and popped from the stack at most once.]
     *
     *   - Space: O(N)
     *     [In the worst case (a strictly increasing histogram), the stack could hold all N indices.]
     */

    @Override
    public Integer execute(int[] heights) {
        int r = 0;
        Stack<int[]> s = new Stack<>();

        for(int i = 0; i < heights.length; i++){
            int start = i;

            while(!s.isEmpty() && s.peek()[1] >= heights[i]){
                int[] last = s.pop();

                int idx = last[0];
                int height = last[1];

                r = Math.max(r, height * (i - idx));
                /*            pop          push
                *   |          |
                *   | |        | | (2,3)    | (1,3) note how the pos changed since now we can process
                *   | |   =>   | |     =>   | a rectangle from at least pos = 1 of height = 3
                * | | |      | | |        | |
                * ------
                *     ^ it is possible to expand this height to the left
                * but we need to know until which position we can expand it hence the condition above
                * */
                start = idx;
            }
            // if it's a monotonic increasing stack we will just push the bar height
            s.push(new int[]{start, heights[i]});
        }

        // we process the remaining bars in the stack, they should be only the bars that can reach the ond of the array
        for(int [] bar : s){
            int idx = bar[0];
            int height = bar[1];
            r = Math.max(r, height * (heights.length - idx));
        }
        return r;
    }

    @Override
    public String getTimeComplexity() {
        return "O(N)";
    }

    @Override
    public String getSpaceComplexity() {
        return "O(N)";
    }
}
