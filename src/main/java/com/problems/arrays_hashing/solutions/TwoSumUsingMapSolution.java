package com.problems.arrays_hashing.solutions;

import com.framework.Solution;
import com.problems.arrays_hashing.problems.TwoSum.TwoSumInput;

import java.util.HashMap;
import java.util.Map;

public class TwoSumUsingMapSolution implements Solution<TwoSumInput, int[]> {
    @Override
    public int[] execute(TwoSumInput input) {
        Map<Integer, Integer> complements = new HashMap<>();
        int [] nums = input.nums();
        int target = input.target();
        int n = nums.length;
        for (int i = 0; i < n; i++) {
            // If we have processed a complement return the answer
            if (complements.containsKey(nums[i])) {
                return new int[]{complements.get(nums[i]), i};
            }
            // add a complement for later processing
            complements.put(nums[i] - target, i);
        }
        return new int[]{};
    }

    @Override
    public String getTimeComplexity() {
        return "O(N) where N is the length of the input array";
    }

    @Override
    public String getSpaceComplexity() {
        return "O(N) where N is the length of the input array";
    }
}
