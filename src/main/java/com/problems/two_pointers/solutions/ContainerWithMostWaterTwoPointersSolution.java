package com.problems.two_pointers.solutions;

import com.framework.Solution;

public class ContainerWithMostWaterTwoPointersSolution implements Solution<int[], Integer> {

    /*
     * --- APPROACH ---
     * Use two pointers, one at the far left and one at the far right.
     * Calculate the area and then greedily move the pointer pointing to the shorter line inward.
     *
     * --- INTUITION ---
     * The Greedy approach comes from the idea of how to maximize the 2 factors that determine an area w * h
     * To maximize the width we can start by assuming that our max width is the far most left and right pointer
     * To maximize the height we need to discard the min of the left and right pointer
     * The reason why is that the heigh can not have any other height except self
     *
     * --- COMPLEXITY ---
     *
     *   - Time: O(N)
     *     The left and right pointers each traverse the array once
     *
     *   - Space: O(1)
     *     We only use a few variables to store pointers and the max area.
     */

    @Override
    public Integer execute(int[] height) {
        int n = height.length;
        int l = 0;
        int r = n - 1;
        int maxArea = 0;
        while(l < r){
            // calculate the curr area
            int area = Math.min(height[l], height[r]) * (r - l);
            maxArea = Math.max(maxArea, area);
            // we discard the min or either if they are equal because it doesn't matter if we find a taller pillar later
            // the next value will always be smaller because we decremented the distance between the pillars
            // and the max height will remain the same
            if(height[l] < height[r]){
                l++;
            } else {
                r--;
            }
        }
        return maxArea;
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
