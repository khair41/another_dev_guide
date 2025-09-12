package com.problems.binary_search.solutions;

import com.framework.Solution;
import com.problems.binary_search.problems.SearchInRotatedSortedArrayProblem.SearchInRotatedSortedArrayInput;

public class SearchInRotatedSortedArraySolution implements Solution<SearchInRotatedSortedArrayInput, Integer> {

    /*
     * --- APPROACH ---
     * [TODO: Explain the core idea of the algorithm.]
     * Hint: Use a modified binary search. The key insight is that when you pick a middle element,
     * at least one of the two halves of the array (from `left` to `mid` or from `mid` to `right`)
     * must be sorted.
     *
     * --- INTUITION ---
     * [TODO: Why does this approach work? Explain the logic in plain English.]
     * Hint: In each step of the binary search, first identify which half is sorted. You can do this
     * by comparing `nums[mid]` with `nums[left]`. If `nums[left] <= nums[mid]`, the left half is sorted.
     * Otherwise, the right half is sorted.
     *
     * Once you know which half is sorted, you can check if the `target` lies within the range of that
     * sorted half. If it does, you search within that half. If it doesn't, you know the target must be
     * in the other, unsorted half. This allows you to discard half the search space in every step.
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
    public Integer execute(SearchInRotatedSortedArrayInput input) {
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
