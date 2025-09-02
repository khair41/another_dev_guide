package com.problems.two_pointers.solutions;

import com.framework.Solution;

public class TrappingRainWaterPrefixSuffixSolution implements Solution<int[], Integer> {

    /*
     * --- APPROACH ---
     * This approach optimizes the brute-force method by pre-calculating the maximum wall heights.
     * We use two extra arrays: one to store the maximum height to the left of each position (`leftMax`)
     * and another to store the maximum height to the right of each position (`rightMax`).
     *
     * --- INTUITION ---
     * The brute-force O(N^2) complexity comes from repeatedly scanning for `leftMax` and `rightMax`.
     * We can eliminate this repeated work. By making one pass from left to right, we can fill the
     * `leftMax` array. By making a second pass from right to left, we can fill the `rightMax` array.
     * With this pre-computed information, we can then make a third pass to calculate the trapped
     * water at each position in O(1) time using the formula `min(leftMax[i], rightMax[i]) - height[i]`.
     *
     * --- COMPLEXITY ---
     *
     *   - Time: O(N)
     *     We make three separate passes through the array (one to build `leftMax`, one for `rightMax`,
     *     and one to calculate the final sum), which simplifies to O(N).
     *
     *   - Space: O(N)
     *     We use two additional arrays of size N to store the left and right max heights.
     */

    @Override
    public Integer execute(int[] height) {
        int n = height.length;
        if(n == 0) return 0;

        int[] leftMax = new int[n];
        int[] rightMax = new int[n];

        // at the edge, we can't compute anything so we cant capture anything
        int lMax = 0;
        for(int i = 1; i < n; i++){
            // compute the very next possible value which is after the edge
            // keep updating the max value in the variable then update the array
            lMax = Math.max(lMax, height[i - 1]);
            leftMax[i] = lMax;
        }

        int rMax = 0;
        for(int i = n - 2; i >= 0; i--){
            rMax = Math.max(rMax, height[i + 1]);
            rightMax[i] = rMax;
        }

        int res = 0;
        for(int i = 0; i < n; i++){
            res += Math.max(0, Math.min(leftMax[i], rightMax[i]) - height[i]);
        }

        return res;
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
