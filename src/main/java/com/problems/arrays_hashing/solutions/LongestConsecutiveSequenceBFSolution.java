package com.problems.arrays_hashing.solutions;

import com.framework.Solution;

import java.util.HashSet;
import java.util.Set;

public class LongestConsecutiveSequenceBFSolution implements Solution<int[], Integer> {
    @Override
    public Integer execute(int[] nums) {
        int res = 0;
        Set<Integer> seen = new HashSet<>();
        for(int num : nums){
            seen.add(num);
        }

        for(int num : nums){
            int streak = 0, curr = num;
            while(seen.contains(curr)){
                streak++;
                curr++;
            }

            res = Math.max(res, streak);
        }
        return res;
    }

    @Override
    public String getTimeComplexity() {
        return "O(N ^ 2)";
    }

    @Override
    public String getSpaceComplexity() {
        return "O(N)";
    }
}
