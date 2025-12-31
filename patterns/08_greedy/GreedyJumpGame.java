package com.framework.patterns.greedy;

/**
 * Pattern 54: Greedy - Jump Game Reachability/Minimization
 */
public class GreedyJumpGame {

    /*
     * =================================================================================
     * CONDITIONS FOR APPLICABILITY
     * =================================================================================
     *
     * 1.  Input Data Structure: An array where each element represents a potential action
     *     (e.g., the maximum number of steps you can jump forward).
     *
     * 2.  Problem Goal: You need to determine if the end of the array is reachable, or find the
     *     minimum number of jumps to reach the end.
     *
     * 3.  Logic (for Reachability - Jump Game I):
     *     -   The greedy idea is to always keep track of the `max_reach`, which is the farthest
     *         index you can possibly get to from your current position or any previous position.
     *     -   Iterate through the array from left to right.
     *     -   At each index `i`, first check if `i` is beyond your `max_reach`. If it is, it means
     *         the current position is unreachable, so the end is also unreachable.
     *     -   **Greedy Choice**: Update the `max_reach` by considering the farthest you can jump from
     *         the current index `i`. `max_reach = max(max_reach, i + nums[i])`.
     *     -   If you successfully iterate through the entire array without falling short, it means
     *         the end was reachable.
     *
     * 4.  Logic (for Minimization - Jump Game II):
     *     -   This is a slightly more complex greedy approach (often considered a level-order BFS on the array).
     *     -   You maintain `jumps` (the number of jumps taken), `current_end` (the end of the range for the current jump),
     *         and `farthest` (the farthest you can reach from the current range).
     *     -   Iterate through the array. For each index `i`, update `farthest = max(farthest, i + nums[i])`.
     *     -   If you reach the `current_end` of the current jump's range, it means you must make another jump.
     *         Increment `jumps` and update `current_end` to the new `farthest` point.
     *
     * =================================================================================
     * GENERIC TEMPLATE (Jump Game I - Reachability)
     * =================================================================================
     */

    /**
     * Determines if you can reach the last index of the array.
     *
     * @param nums An array where `nums[i]` is the maximum jump length from index `i`.
     * @return True if the last index is reachable, false otherwise.
     */
    public boolean canJump(int[] nums) {
        if (nums == null || nums.length == 0) {
            return false;
        }

        // `maxReach` stores the farthest index we can get to.
        int maxReach = 0;

        // --- Core Pattern Logic: Iterate and update max reach ---
        for (int i = 0; i < nums.length; i++) {
            // --- Problem-Specific Logic: Check for unreachability ---
            // If the current index `i` is beyond our max reach, we are stuck.
            if (i > maxReach) {
                return false;
            }
            // -------------------------------------------------------

            // --- Greedy Choice: Update the farthest possible reach ---
            maxReach = Math.max(maxReach, i + nums[i]);
            // -------------------------------------------------------

            // Optimization: if maxReach covers the last index, we are done.
            if (maxReach >= nums.length - 1) {
                return true;
            }
        }

        return true;
    }
}
