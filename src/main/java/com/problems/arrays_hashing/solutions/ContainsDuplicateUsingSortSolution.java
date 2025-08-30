package com.problems.arrays_hashing.solutions;

import com.framework.Solution;

import java.util.Arrays;

public class ContainsDuplicateUsingSortSolution implements Solution<int[], Boolean> {
    @Override
    public Boolean execute(int[] nums) {
        Arrays.sort(nums);
        for (int i = 1; i < nums.length; i++) {
            if (nums[i] == nums[i - 1]) {
                return true;
            }
        }
        return false;
    }

    @Override
    public String getTimeComplexity() {
        return "O(N log N) where N is the length of the input array";
    }

    @Override
    public String getSpaceComplexity() {
        return "O(1) since we are sorting in place";
    }
}
