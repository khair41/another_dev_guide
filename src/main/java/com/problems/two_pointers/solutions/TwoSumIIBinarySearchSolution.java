package com.problems.two_pointers.solutions;

import com.framework.Solution;
import com.problems.two_pointers.problems.TwoSumII.TwoSumIIInput;

import java.util.Arrays;

public class TwoSumIIBinarySearchSolution implements Solution<TwoSumIIInput, int[]> {

    /*
     * --- APPROACH ---
     * Given that the input is sorted, we can do a binary search for the complement of the current element.
     *
     * --- INTUITION ---
     * This works only because the array is sorted. Also, consider the idea that BS before curr element has already
     * been done in a prev iteration.
     * When i = 4, just check whatever is after
     * When i was 0, we will already discard all combinations from range i + 1 -> n - 1
     * [1 2 3 4 5 7 6] 
     *  x x x x i l r
     *
     * --- COMPLEXITY ---
     *
     *   - Time: O(N log N)
     *     Since we are doing BS for each element
     *
     *   - Space: O(1)
     *     Since variable declaration does not constitute extra space
     */

    @Override
    public int[] execute(TwoSumIIInput input) {
        int [] nums = input.numbers();
        int target = input.target();
        int n = nums.length;

        for(int i = 0; i < n; i++){
            // target value will always be greater in value even if it's a negative num
            // use the current position to attempt to search
            int tmp = target - nums[i]; // tmp is the value we are looking for
            int l = i + 1; // left pointer after the curr element
            int r = n - 1;
            while(l <= r){
                int mid = l + (r - l) / 2;
                if(nums[mid] == tmp){ // found solution
                    return new int[]{i + 1, mid + 1}; // where we started and where we found the complement
                } if(nums[mid] < tmp){ // look for a greater value
                    l = mid + 1;
                } else { // look for a smaller value
                    r = mid - 1;
                }
            }

        }

        return new int[]{0}; //invalid solution
    }

    @Override
    public String getTimeComplexity() {
        return "O(N log N)";
    }

    @Override
    public String getSpaceComplexity() {
        return "O(1)";
    }
}
