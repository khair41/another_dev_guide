package com.problems.arrays_hashing.solutions;

import com.framework.Solution;

public class ProductOfArrayExceptSelfPrefixSuffixOptimalSolution implements Solution<int[], int[]> {
    @Override
    public int[] execute(int[] nums) {
        int n = nums.length;

        int[] res = new int[n];
        res[0] = 1;

        for(int i = 1; i < n; i++){
            res[i] = nums[i - 1] * res[i - 1];
        }

        int post = 1;
        for(int i = n - 1; i >= 0; i--){
            res[i] *= post;
            post *= nums[i];
        }

        return res;
    }

    @Override
    public String getTimeComplexity() {
        return "O(N)";
    }

    @Override
    public String getSpaceComplexity() {
        return "O(1) extra space";
    }
}
