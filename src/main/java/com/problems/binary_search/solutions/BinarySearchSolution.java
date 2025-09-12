package com.problems.binary_search.solutions;

import com.framework.Solution;
import com.problems.binary_search.problems.BinarySearchProblem.BinarySearchInput;

public class BinarySearchSolution implements Solution<BinarySearchInput, Integer> {

    /*
     * --- APPROACH ---
     * Use two pointers, `left` and `right`, to define a search space. Repeatedly
     * look at the middle element of the search space and compare it to the target. Based
     * on the comparison, discard half of the search space.
     *
     * --- INTUITION ---
     * Because the array is sorted, if we check the middle element, we can make a
     * definitive conclusion. If the middle element is too small, we know the target must be
     * in the right half. If it's too large, the target must be in the left half. This allows
     * us to eliminate half of the remaining elements with every single check.
     *
     * --- COMPLEXITY ---
     *
     *   - Time: O(log N)
     *     [With each comparison, we reduce the search space by half.]
     *
     *   - Space: O(1)
     *     [We only use a few variables to store pointers.]
     */

    @Override
    public Integer execute(BinarySearchInput input) {
        int [] nums = input.nums();
        int target = input.target();

        int left = 0;
        int right = nums.length - 1;

        while (left <= right) {
            // remember to add left to have the absolute position of the mid pointer
            int mid = left + (right - left) / 2;
            if(nums[mid] == target) return mid;
            // we have discarded mid hence we can exclude it for the next iteration
            if(nums[mid] < target) left = mid + 1;
            else right = mid - 1;
        }
        return -1; // Placeholder
    }

    @Override
    public String getTimeComplexity() {
        return "O(log N)";
    }

    @Override
    public String getSpaceComplexity() {
        return "O(1)";
    }
}
