package com.problems.two_pointers.solutions;

import com.framework.Solution;

import java.util.*;

public class ThreeSumTwoPointersSolution implements Solution<int[], List<List<Integer>>> {

    /*
     * --- APPROACH ---
     * Since we are looking for triplets, a naive approach would be to create a 3 nested for loops, this will take O(N^3) time
     * To improve the time, we can relly on sorting the array, now we can ensure that values after and before any pointer
     * will be greater or smaller respectively
     *
     * --- INTUITION ---
     * Sorting will give a linear change of looking at combinations, dus reducing the complexity
     *
     * --- COMPLEXITY ---
     *
     *   - Time: O(N^2)
     *     Despite of sorting the array, we still need to create n^2 combinations
     *
     *   - Space: O(1) or O(N)
     *     Depends on sorting implementation
     */

    @Override
    public List<List<Integer>> execute(int[] nums) {
        Set<List<Integer>> res = new HashSet<>();
        Arrays.sort(nums);
        int n = nums.length;
        for(int i = 0; i < n; i++){
            int l = i + 1;
            int r = n - 1;
            while(l < r){
                int sum = nums[i] + nums[l] + nums[r];
                if(sum == 0){
                    res.add(Arrays.asList(nums[i], nums[l], nums[r]));
                    l++; r--; // dont forget to move the pointers
                } else if(sum < 0) { // look to increase
                    l++;
                } else { // look to reduce
                    r--;
                }
            }
        }
        
        return new ArrayList<>(res);
    }

    @Override
    public String getTimeComplexity() {
        return "O(N^2)";
    }

    @Override
    public String getSpaceComplexity() {
        return "O(1) or O(N) depending on sorting implementation";
    }
}
