package com.framework.patterns.backtracking;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

/**
 * Pattern 48: Backtracking - Combination Sum
 */
public class BacktrackingCombinationSum {

    /*
     * =================================================================================
     * CONDITIONS FOR APPLICABILITY
     * =================================================================================
     *
     * 1.  Input Data Structure: A set of numbers (candidates) and a target sum.
     *
     * 2.  Problem Goal: You need to find all unique combinations of numbers from the set that
     *     sum up to the target. Variations include:
     *     -   Combination Sum I: Candidates can be reused, and candidates are distinct.
     *     -   Combination Sum II: Candidates cannot be reused, and the candidate list may contain duplicates.
     *
     * 3.  Logic: The backtracking function explores different combinations.
     *
     *     -   The recursive function takes the current index, the remaining target sum, the current
     *         combination, and the results list.
     *     -   To handle uniqueness and avoid duplicate combinations (like `[2, 2, 3]` and `[2, 3, 2]`),
     *         the recursive call for the next element is made with the current index `i`, not `i+1`
     *         (if elements can be reused) or `i+1` (if they cannot). This ensures we only generate
     *         combinations in a non-decreasing order.
     *
     *     -   **Recursive Step (for Combination Sum I)**:
     *         1.  **Base Case 1 (Success)**: If `remaining_target == 0`, we have found a valid combination.
     *             Add a copy of it to the results and return.
     *         2.  **Base Case 2 (Failure)**: If `remaining_target < 0`, the current path is invalid. Return.
     *         3.  **Explore**: Iterate from the `start` index to the end of the candidates array.
     *         4.  **Choose**: Add the candidate `candidates[i]` to the current combination.
     *         5.  **Recurse**: Make a recursive call with the updated `remaining_target` (`target - candidates[i]`)
     *             and, crucially, pass the same `start` index `i` to allow for reuse of the element.
     *         6.  **Unchoose (Backtrack)**: Remove the candidate from the current combination.
     *
     * =================================================================================
     * GENERIC TEMPLATE (Combination Sum I - candidates can be reused)
     * =================================================================================
     */

    /**
     * Finds all unique combinations in `candidates` where the candidate numbers sum to `target`.
     *
     * @param candidates The array of distinct candidate numbers.
     * @param target The target sum.
     * @return A list of all unique combinations.
     */
    public List<List<Integer>> combinationSum(int[] candidates, int target) {
        List<List<Integer>> result = new ArrayList<>();
        if (candidates == null) {
            return result;
        }
        // Sorting is not required for this version, but is crucial for duplicate handling in Combination Sum II.
        // Arrays.sort(candidates);
        backtrack(candidates, target, 0, new ArrayList<>(), result);
        return result;
    }

    /**
     * The recursive backtracking helper function.
     *
     * @param candidates The original candidate array.
     * @param remainingTarget The target sum we still need to reach.
     * @param start The starting index for the current exploration.
     * @param currentCombination The combination being built.
     * @param result The list to store all valid combinations.
     */
    private void backtrack(int[] candidates, int remainingTarget, int start, List<Integer> currentCombination, List<List<Integer>> result) {
        // --- Base Case 1: Success ---
        if (remainingTarget == 0) {
            result.add(new ArrayList<>(currentCombination));
            return;
        }

        // --- Base Case 2: Failure ---
        if (remainingTarget < 0) {
            return;
        }

        // --- Explore all choices ---
        for (int i = start; i < candidates.length; i++) {
            // 1. Choose: Add the candidate.
            currentCombination.add(candidates[i]);

            // 2. Recurse: Explore with the new state.
            // We pass `i` instead of `i+1` to allow the same element to be reused.
            backtrack(candidates, remainingTarget - candidates[i], i, currentCombination, result);

            // 3. Unchoose (Backtrack): Remove the candidate.
            currentCombination.remove(currentCombination.size() - 1);
        }
        // ---------------------------
    }
}
