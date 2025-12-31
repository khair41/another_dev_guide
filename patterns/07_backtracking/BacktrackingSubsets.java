package com.framework.patterns.backtracking;

import java.util.ArrayList;
import java.util.List;

/**
 * Pattern 46: Backtracking - Subsets (Include/Exclude)
 */
public class BacktrackingSubsets {

    /*
     * =================================================================================
     * CONDITIONS FOR APPLICABILITY
     * =================================================================================
     *
     * 1.  Input Data Structure: A set of elements (e.g., an array of numbers).
     *
     * 2.  Problem Goal: You need to generate all possible subsets (the power set) of the given set.
     *
     * 3.  Logic: Backtracking is a recursive approach to explore all possible solutions by trying
     *     to build a solution piece by piece. If at any point the partial solution cannot be
     *     completed, we "backtrack" (undo the last choice) and try another option.
     *
     *     -   The core idea for subsets is the "include/exclude" choice for each element.
     *     -   A recursive helper function is used, which takes the current position in the array,
     *         the current subset being built, and the list to store all results.
     *
     *     -   **Recursive Step**:
     *         1.  **Base Case**: First, add the current subset to the results list. This represents
     *             one valid subset.
     *         2.  **Explore**: Iterate from the current position to the end of the input array.
     *         3.  **Choose**: For each element, "include" it by adding it to the current subset.
     *         4.  **Recurse**: Make a recursive call with the next position (`i + 1`) and the updated subset.
     *         5.  **Unchoose (Backtrack)**: After the recursive call returns, remove the element you just
     *             added. This is the crucial backtracking step that allows you to explore other paths
     *             (i.e., the path where the element is *excluded*).
     *
     * =================================================================================
     * GENERIC TEMPLATE (Generate All Subsets)
     * =================================================================================
     */

    /**
     * Generates all possible subsets (the power set) of a given set of distinct integers.
     *
     * @param nums An array of distinct integers.
     * @return A list of lists, where each inner list is a subset.
     */
    public List<List<Integer>> subsets(int[] nums) {
        List<List<Integer>> result = new ArrayList<>();
        if (nums == null) {
            return result;
        }
        backtrack(nums, 0, new ArrayList<>(), result);
        return result;
    }

    /**
     * The recursive backtracking helper function.
     *
     * @param nums The original input array.
     * @param start The current starting index to explore from.
     * @param currentSubset The subset being built in the current recursion path.
     * @param result The list to store all generated subsets.
     */
    private void backtrack(int[] nums, int start, List<Integer> currentSubset, List<List<Integer>> result) {
        // --- Base Case / Add Solution ---
        // Add a copy of the current subset. This is a valid subset at this point.
        result.add(new ArrayList<>(currentSubset));
        // --------------------------------

        // --- Explore all choices from the current state ---
        for (int i = start; i < nums.length; i++) {
            // 1. Choose: Include the element at index `i`.
            currentSubset.add(nums[i]);

            // 2. Recurse: Explore further with the new state.
            backtrack(nums, i + 1, currentSubset, result);

            // 3. Unchoose (Backtrack): Remove the element to explore other possibilities.
            currentSubset.remove(currentSubset.size() - 1);
        }
        // -------------------------------------------------
    }
}
