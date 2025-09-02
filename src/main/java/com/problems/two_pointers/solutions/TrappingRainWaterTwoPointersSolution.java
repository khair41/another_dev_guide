package com.problems.two_pointers.solutions;

import com.framework.Solution;

public class TrappingRainWaterTwoPointersSolution implements Solution<int[], Integer> {

    /*
     * --- APPROACH ---
     * Use two pointers, `left` and `right`, at the ends of the array, along with two variables,
     * `leftMax` and `rightMax`, to track the tallest wall seen so far from each side.
     *
     * --- INTUITION ---
     * The amount of water that can be trapped at any position is determined by the shorter of the
     * tallest wall to its left and the tallest wall to its right. By moving our pointers inward from the
     * side with the lower `max` wall, we can confidently calculate the trapped water for that position.
     * If `leftMax < rightMax`, we know the water at the `left` pointer is determined by `leftMax`, and vice-versa.
     *
     * --- COMPLEXITY ---
     *
     *   - Time: O(N)
     *     The left and right pointers each traverse the array once.
     *
     *   - Space: O(1)
     *     We only use a few variables to store pointers and max heights.
     */

    @Override
    public Integer execute(int[] height) {
        int n = height.length;
        if(n <= 2) return 0;

        int l = 0;
        int r = n - 1;
        int leftMax = height[l];
        int rightMax = height[r];
        int res = 0;

        while(l < r){
            if(leftMax < rightMax){
                l++;
                leftMax = Math.max(leftMax, height[l]);
                res += leftMax - height[l];
            } else {
                r--;
                rightMax = Math.max(rightMax, height[r]);
                res += rightMax - height[r];
            }
        }


        return res;
    }

    @Override
    public String getTimeComplexity() {
        return "O(N)";
    }

    @Override
    public String getSpaceComplexity() {
        return "O(1)";
    }
}
