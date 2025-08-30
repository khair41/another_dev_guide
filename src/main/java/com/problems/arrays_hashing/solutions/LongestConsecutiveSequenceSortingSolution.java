package com.problems.arrays_hashing.solutions;

import com.framework.Solution;

import java.util.Arrays;

public class LongestConsecutiveSequenceSortingSolution implements Solution<int[], Integer> {
    @Override
    public Integer execute(int[] nums) {
        int res = 0;
        int n = nums.length;
        if(n == 0) return 0;
        Arrays.sort(nums);
        int streak = 0, curr = nums[0];
        for(int i = 0; i < nums.length;){
            if(curr != nums[i]){
                curr = nums[i];
                streak = 0;
            }
            while(i < n && curr == nums[i]){
                i++;
            }
            curr++;
            streak++;
            res = Math.max(res, streak);

        }
        return res; 
    }

    @Override
    public String getTimeComplexity() {
        return "O(N Log N)";
    }

    @Override
    public String getSpaceComplexity() {
        return "O(1) extra space";
    }
}
