package com.problems.arrays_hashing.solutions;

import com.framework.Solution;

public class ProductOfArrayExceptSelfPrefixSuffixSolution implements Solution<int[], int[]> {
    @Override
    public int[] execute(int[] nums) {
        int n = nums.length;

        int[] res = new int[n];
        int[] pref = new int[n];
        int[] suff = new int[n];

        pref[0] = 1;
        for(int i = 1; i < n; i++){
            pref[i] = nums[i - 1] * pref[i - 1];
        }

        suff[n - 1] = 1;
        for(int i = n - 2; i >= 0; i--){
            suff[i] = nums[i + 1] * suff[i + 1]; 
        }

        for(int i = 0; i < n; i++){
            res[i] = pref[i] * suff[i];
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
