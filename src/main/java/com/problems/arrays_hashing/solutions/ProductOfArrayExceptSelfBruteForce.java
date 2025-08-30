package com.problems.arrays_hashing.solutions;

import com.framework.Solution;

public class ProductOfArrayExceptSelfBruteForce implements Solution<int[], int[]> {
    @Override
    public int[] execute(int[] nums) {
        int n = nums.length;
        int [] r = new int [n];
        for(int i = 0; i < n; i++){
            int mult = 1;
            for(int j = 0; j < n; j++){
                if(j == i) continue;
                mult *= nums[j];
            }
            r[i] = mult;
        }
        return r;
    }

    @Override
    public String getTimeComplexity() {
        return "O(N ^ 2) where N is the length of the array ";
    }

    @Override
    public String getSpaceComplexity() {
        return "O(1) extra space";
    }
}
