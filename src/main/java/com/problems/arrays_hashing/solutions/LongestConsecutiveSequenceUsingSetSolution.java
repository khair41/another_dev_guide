package com.problems.arrays_hashing.solutions;

import com.framework.Solution;

import java.util.HashSet;
import java.util.Set;

public class LongestConsecutiveSequenceUsingSetSolution implements Solution<int[], Integer> {
    @Override
    public Integer execute(int[] nums) {
        Set<Integer> seen = new HashSet<>();
        for(int num : nums){
            seen.add(num);
        }
        int res = 0;
        for(int num : nums){
            if(!seen.contains(num - 1)){
                int curr = num, streak = 1;
                while(seen.contains(curr + 1)){
                    curr++; streak++;
                }
                res = Math.max(res, streak);
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
        return "O(N) extra space";
    }
}
