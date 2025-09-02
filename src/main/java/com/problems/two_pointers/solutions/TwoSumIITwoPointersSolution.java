package com.problems.two_pointers.solutions;

import com.framework.Solution;
import com.problems.two_pointers.problems.TwoSumII.TwoSumIIInput;

public class TwoSumIITwoPointersSolution implements Solution<TwoSumIIInput, int[]> {

    /*
     * --- APPROACH ---
     * We use 2 pointers to traverse the array checking what is the current sum of those 2 pointers
     *
     * --- INTUITION ---
     * 2 Pointers works here because of the sorted nature of the input array, we are granted that the next value of the left
     * pointer will be greater or equal to the left pointer, meaning that the moving the left pointer will increase
     * the sum. Opposite happens on the right pointer. We can now compare the current sum of the pointers and move
     * the pointers accordingly until the sum equals to target
     *
     * --- COMPLEXITY ---
     *
     *   - Time: O(N)
     *     Since we still need to go over the whole array looking for the combination
     *
     *   - Space: O(1)
     *     Since pointer declaration does not require extra space
     */

    @Override
    public int[] execute(TwoSumIIInput input) {
        int [] nums = input.numbers();
        int target = input.target();

        int l = 0;
        int r = nums.length - 1;

        while(l < r){
            int sum = nums[l] + nums[r];
            if(sum == target) { // Found the unique possible solution
                return new int[]{l + 1, r + 1};
            } else if(sum < target){ // Sum is smaller to target, then attempt to increase the sum
                l++;
            } else { // Sum is greater, then attempt to decrease the sum
                r--;
            }
        }
        return new int[]{}; // invalid solution
    }

    @Override
    public String getTimeComplexity() {
        return "O(N)";
    }

    @Override
    public String getSpaceComplexity() {
        return "O(1)";
    }
}
