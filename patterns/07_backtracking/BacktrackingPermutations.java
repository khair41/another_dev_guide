package com.framework.patterns.backtracking;

import java.util.ArrayList;
import java.util.List;

/**
 * Pattern 47: Backtracking - Permutations
 */
public class BacktrackingPermutations {

    /*
     * =================================================================================
     * CONDITIONS FOR APPLICABILITY
     * =================================================================================
     *
     * 1.  Input Data Structure: A set of distinct elements (e.g., an array of numbers).
     *
     * 2.  Problem Goal: You need to generate all possible orderings (permutations) of the given set.
     *
     * 3.  Logic: The backtracking approach explores every possible placement for each element.
     *
     *     -   A recursive helper function is used. The state it tracks can be the current permutation
     *         being built.
     *     -   To avoid re-using the same element within a single permutation, we need a way to track
     *         which elements have already been included. A boolean `used` array or checking for the
     *         element's existence in the current permutation list works well.
     *
     *     -   **Recursive Step**:
     *         1.  **Base Case**: If the current permutation's size is equal to the input array's size,
     *             we have a complete permutation. Add a copy of it to the results list and return.
     *         2.  **Explore**: Iterate through all the numbers in the original input array.
     *         3.  **Choose**: If the current number has NOT been used yet:
     *             -   Mark it as used.
     *             -   Add it to the current permutation.
     *         4.  **Recurse**: Make a recursive call to continue building the permutation.
     *         5.  **Unchoose (Backtrack)**: After the recursive call returns, undo the choices:
     *             -   Remove the number from the current permutation.
     *             -   Mark it as not used, so it can be part of other permutations.
     *
     * =================================================================================
     * GENERIC TEMPLATE (Generate All Permutations)
     * =================================================================================
     */

    /**
     * Generates all possible permutations of a given set of distinct integers.
     *
     * @param nums An array of distinct integers.
     * @return A list of lists, where each inner list is a permutation.
     */
    public List<List<Integer>> permute(int[] nums) {
        List<List<Integer>> result = new ArrayList<>();
        if (nums == null) {
            return result;
        }
        backtrack(nums, new ArrayList<>(), new boolean[nums.length], result);
        return result;
    }

    /**
     * The recursive backtracking helper function.
     *
     * @param nums The original input array.
     * @param currentPermutation The permutation being built in the current recursion path.
     * @param used An array to keep track of which elements have been used.
     * @param result The list to store all generated permutations.
     */
    private void backtrack(int[] nums, List<Integer> currentPermutation, boolean[] used, List<List<Integer>> result) {
        // --- Base Case: A complete permutation is found ---
        if (currentPermutation.size() == nums.length) {
            result.add(new ArrayList<>(currentPermutation));
            return;
        }
        // ------------------------------------------------

        // --- Explore all choices ---
        for (int i = 0; i < nums.length; i++) {
            // If the element is already used in the current permutation, skip it.
            if (used[i]) {
                continue;
            }

            // 1. Choose: Include the element at index `i`.
            used[i] = true;
            currentPermutation.add(nums[i]);

            // 2. Recurse: Explore further with the new state.
            backtrack(nums, currentPermutation, used, result);

            // 3. Unchoose (Backtrack): Remove the element to explore other possibilities.
            currentPermutation.remove(currentPermutation.size() - 1);
            used[i] = false;
        }
        // ---------------------------
    }
}
