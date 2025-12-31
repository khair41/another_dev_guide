package com.framework.patterns.greedy;

import java.util.Arrays;

/**
 * Pattern 57: Greedy - Task Scheduling (Frequency Based)
 */
public class GreedyTaskScheduling {

    /*
     * =================================================================================
     * CONDITIONS FOR APPLICABILITY
     * =================================================================================
     *
     * 1.  Input Data Structure: A list of tasks and a cooldown period `n`.
     *
     * 2.  Problem Goal: You need to find the minimum time required to execute all tasks, given that
     *     the same task cannot be executed again until a cooldown period `n` has passed.
     *     Example: "Task Scheduler".
     *
     * 3.  Logic: The minimum time is determined by the task that occurs most frequently, as it creates
     *     the most "idle" slots that need to be filled.
     *
     *     -   **Greedy Insight**: The schedule's length is constrained by the most frequent task. Let's say
     *         task 'A' is the most frequent, occurring `max_freq` times. This creates `max_freq - 1`
     *         chunks of time between the 'A's that need to be filled.
     *     -   Each of these chunks must have a length of at least `n` (the cooldown period).
     *         For example: `A -> [n slots] -> A -> [n slots] -> ... -> A`.
     *     -   The total time for these chunks is `(max_freq - 1) * (n + 1)`.
     *     -   After these main chunks, we need to add the number of tasks that have the same maximum frequency.
     *         Let `tasks_with_max_freq` be this count.
     *     -   So, a formula for the minimum time is `(max_freq - 1) * (n + 1) + tasks_with_max_freq`.
     *
     *     -   **Edge Case**: What if the tasks are very diverse and we don't need any idle time? For example,
     *         `[A, B, C, D, E]` with `n=2`. The schedule is just `A, B, C, D, E` with length 5. The formula
     *         might give a smaller number. In this case, the minimum time is simply the total number of tasks.
     *     -   Therefore, the final answer is `max(total_tasks, formula_result)`.
     *
     * =================================================================================
     * GENERIC TEMPLATE (Task Scheduler)
     * =================================================================================
     */

    /**
     * Calculates the least time required to finish all tasks with a cooldown period.
     *
     * @param tasks An array of characters representing tasks.
     * @param n The non-negative cooldown period.
     * @return The minimum time units required.
     */
    public int leastInterval(char[] tasks, int n) {
        if (n == 0) {
            return tasks.length;
        }

        // --- Core Pattern Logic: Step 1 - Calculate Frequencies ---
        int[] frequencies = new int[26];
        for (char task : tasks) {
            frequencies[task - 'A']++;
        }
        // ---------------------------------------------------------

        // --- Core Pattern Logic: Step 2 - Find Max Frequency ---
        Arrays.sort(frequencies);
        int maxFreq = frequencies[25];
        // -------------------------------------------------------

        // --- Greedy Insight: Calculate idle time based on max frequency ---
        // The number of idle slots is determined by the gaps between the most frequent task.
        int idleTime = (maxFreq - 1) * n;
        // ----------------------------------------------------------------

        // --- Problem-Specific Logic: Fill idle slots with other tasks ---
        // Iterate from the second most frequent task to see how many idle slots we can fill.
        for (int i = 24; i >= 0 && frequencies[i] > 0; i--) {
            // The number of tasks we can place in the idle gaps is limited by `maxFreq - 1`.
            // For example, if maxFreq is 3 (A..A..A), there are 2 gaps. If another task B appears 3 times,
            // we can place it as A,B..A,B..A,B. The third B doesn't fill an idle slot.
            idleTime -= Math.min(maxFreq - 1, frequencies[i]);
        }
        // ---------------------------------------------------------------

        // If idleTime is negative, it means we have more than enough tasks to fill the gaps.
        // In this case, no idle time is needed.
        idleTime = Math.max(0, idleTime);

        // The total time is the original number of tasks plus the remaining idle time.
        return tasks.length + idleTime;
    }
}
