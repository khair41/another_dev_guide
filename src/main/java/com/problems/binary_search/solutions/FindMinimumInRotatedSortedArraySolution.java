package com.problems.binary_search.solutions;

import com.framework.Solution;

public class FindMinimumInRotatedSortedArraySolution implements Solution<int[], Integer> {

    /*
     * --- APPROACH ---
     * [TODO: Explain the core idea of the algorithm.]
     * Hint: Use a modified binary search. The goal is to find the "pivot" or "inflection point"
     * where the numbers change from being large to small. This inflection point is the minimum element.
     *
     * --- INTUITION ---
     * [TODO: Why does this approach work? Explain the logic in plain English.]
     * Hint: In each step, compare the middle element `nums[mid]` with the element at the right boundary
     * `nums[right]`. This comparison tells us which side of `mid` the pivot (the minimum value) is on.
     *  - If `nums[mid] > nums[right]`, it means the pivot must be in the right half (because the left
     *    half from `left` to `mid` is sorted correctly and is larger than the right-most element).
     *    So, we search in the right half: `left = mid + 1`.
     *  - If `nums[mid] <= nums[right]`, it means the section from `mid` to `right` is sorted correctly.
     *    The pivot must be in the left half (including `mid` itself, which could be the minimum).
     *    So, we search in the left half: `right = mid`.
     *
     * --- COMPLEXITY ---
     *
     *   - Time: O(log N)
     *     [This is a binary search, so we reduce the search space by half in each step.]
     *
     *   - Space: O(1)
     *     [We only use a few variables to store pointers.]
     */

    @Override
    public Integer execute(int[] nums) {
        // TODO: Implement the modified binary search logic here.
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
