package com.problems.two_pointers.solutions;

import com.framework.Solution;

import java.util.Stack;

public class TrappingRainWaterStackSolution implements Solution<int[], Integer> {

    /*
     * --- APPROACH ---
     * Use a monotonic decreasing stack. The stack will store the indices of bars that are in
     * strictly decreasing order of height. When we encounter a bar that is taller than the one
     * at the top of the stack, it signifies a potential right wall of a container, allowing us
     * to calculate the trapped water.
     *
     * --- INTUITION ---
     * Think of the stack as holding the left boundaries of potential containers. When we find a `current`
     * bar that is taller than the bar at `stack.peek()`, we know that the bar at `stack.peek()` is at the
     * bottom of a potential "tub." The `current` bar acts as the right wall, and the new `stack.peek()`
     * (after we pop) acts as the left wall.
     *
     * The amount of water trapped on top of the popped bar (`bottom`) is determined by the height of the
     * shorter of the two walls (`min(leftWall, rightWall)`). We calculate this volume and add it to our
     * total. We repeat this process until the stack is empty or the bar at the top is taller than the
     * `current` bar, at which point the `current` bar becomes a new potential left boundary.
     *
     * --- COMPLEXITY ---
     *
     *   - Time: O(N)
     *     Each bar's index is pushed onto and popped from the stack at most once. This means we process
     *     each element a constant number of times, resulting in a single-pass linear time complexity.
     *
     *   - Space: O(N)
     *     In the worst-case scenario (e.g., a steadily decreasing elevation map like [5, 4, 3, 2, 1]),
     *     the stack could hold all N bar indices.
     */

    @Override
    public Integer execute(int[] height) {
        if (height == null || height.length == 0) {
            return 0;
        }

        Stack<Integer> stack = new Stack<>();
        int totalWater = 0;

        for (int i = 0; i < height.length; i++) {
            // While the stack is not empty and the current bar is taller than the bar at the stack's top
            while (!stack.isEmpty() && height[i] > height[stack.peek()]) {

                // This element is less than the current processing element
                // this means that we have find the right wall and the bottom of a container
                int bottomIndex = stack.pop();

                // If the stack is now empty, there is no left wall, so we can't trap water.
                if (stack.isEmpty()) {
                    break;
                }

                // Since we have popped the bottom of the container, the next element could be the left wall
                int leftWallIndex = stack.peek();

                // At this point we have accessed the 3 possible elements to create a container, the right wall,
                // the bottom, and the left wall.


                // The distance between the walls is the width of the container. We are processing the distance
                // between the current position and the possible left wall.
                int distance = i - leftWallIndex - 1;

                // The height of the water is limited by the shorter of the two walls.
                // We attempt to process the current container, it could be possible that the bottom of the container
                // and the left wall are of the same height, therefore we cannot accumulate water.
                int boundedHeight = Math.min(height[i], height[leftWallIndex]) - height[bottomIndex];

                // Add the trapped water to the total.
                totalWater += distance * boundedHeight;
            }
            // Push the current bar's index onto the stack as a potential left boundary.
            stack.push(i);
        }

        return totalWater;
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
