package com.problems.two_pointers.solutions;

import com.framework.Solution;

public class TrappingRainWaterBruteForceSolution implements Solution<int[], Integer> {

    /*
     * --- APPROACH ---
     * For each bar in the elevation map, find the tallest bar to its left and the tallest bar to its right.
     * The amount of water trapped above the current bar is determined by the shorter of these two walls.
     *
     * --- INTUITION ---
     * Water can only be trapped at a specific position if there are walls on both sides that are taller
     * than the current bar. The water level will rise to the height of the shorter of the two tallest
     * surrounding walls. The amount of trapped water is then `min(leftMax, rightMax) - height[i]`.
     * We do this for every single bar and sum the results.
     *
     * --- COMPLEXITY ---
     *
     *   - Time: O(N^2)
     *     For each of the N bars, we iterate to the left (up to i times) and to the right (up to N-i times)
     *     to find the max heights. This results in a nested loop structure.
     *
     *   - Space: O(1)
     *     We only use a few variables to store the max heights and the total water.
     */

    @Override
    public Integer execute(int[] height) {
        int n = height.length;
        if(n == 0) return 0;

        int res = 0;
        for(int i = 0; i < n; i++){
            int maxLeft = 0;
            int maxRight= 0;

            for(int l = 0; l < i; l++){
                maxLeft = Math.max(maxLeft, height[l]);
            }

            for(int r = i + 1; r < n; r++){
                maxRight = Math.max(maxRight, height[r]);
            }

            res += Math.max(0, Math.min(maxLeft, maxRight) - height[i]);

        }
        return res;
    }

    @Override
    public String getTimeComplexity() {
        return "O(N^2)";
    }

    @Override
    public String getSpaceComplexity() {
        return "O(1)";
    }
}
