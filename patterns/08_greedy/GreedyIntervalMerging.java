package com.framework.patterns.greedy;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

/**
 * Pattern 53: Greedy - Interval Merging/Scheduling
 */
public class GreedyIntervalMerging {

    /*
     * =================================================================================
     * CONDITIONS FOR APPLICABILITY
     * =================================================================================
     *
     * 1.  Input Data Structure: A collection of intervals, where each interval has a start and an end time.
     *
     * 2.  Problem Goal: You need to merge overlapping intervals, find non-overlapping intervals, or schedule
     *     events in a way that maximizes or minimizes some value.
     *     Examples: "Merge Intervals", "Non-overlapping Intervals", "Meeting Rooms".
     *
     * 3.  Logic: The core of the greedy strategy is to process the intervals in a specific sorted order.
     *
     *     -   **Step 1: Sort**: Sort the intervals based on their **start times**. This is the most critical step.
     *
     *     -   **Step 2: Iterate and Merge**:
     *         -   Initialize a results list with the first interval.
     *         -   Iterate through the rest of the sorted intervals.
     *         -   For each `current_interval`, compare it with the `last_interval` in the results list.
     *         -   **Greedy Choice**: If the `current_interval` overlaps with the `last_interval`
     *             (i.e., `current_interval.start <= last_interval.end`), then there is a merge opportunity.
     *             Update the `last_interval`'s end time to be the maximum of the two intervals' end times.
     *             `last_interval.end = max(last_interval.end, current_interval.end)`.
     *         -   If there is no overlap, the `current_interval` is distinct. Add it as a new `last_interval`
     *             to the results list.
     *
     * =================================================================================
     * GENERIC TEMPLATE (Merge Intervals)
     * =================================================================================
     */

    /**
     * Merges all overlapping intervals.
     *
     * @param intervals An array of intervals, where `intervals[i] = [start, end]`.
     * @return An array of the non-overlapping intervals that cover all the intervals in the input.
     */
    public int[][] merge(int[][] intervals) {
        if (intervals == null || intervals.length <= 1) {
            return intervals;
        }

        // --- Core Pattern Logic: Step 1 - Sort by start time ---
        Arrays.sort(intervals, (a, b) -> Integer.compare(a[0], b[0]));
        // -------------------------------------------------------

        List<int[]> mergedIntervals = new ArrayList<>();
        // Initialize with the first interval
        mergedIntervals.add(intervals[0]);

        // --- Core Pattern Logic: Step 2 - Iterate and Merge ---
        for (int i = 1; i < intervals.length; i++) {
            int[] currentInterval = intervals[i];
            int[] lastInterval = mergedIntervals.get(mergedIntervals.size() - 1);

            // --- Greedy Choice: Check for overlap ---
            if (currentInterval[0] <= lastInterval[1]) {
                // Overlap exists, so merge.
                // Update the end time of the last interval in our list.
                lastInterval[1] = Math.max(lastInterval[1], currentInterval[1]);
            } else {
                // No overlap, so add the current interval as a new one.
                mergedIntervals.add(currentInterval);
            }
            // ---------------------------------------
        }
        // -----------------------------------------------------

        return mergedIntervals.toArray(new int[mergedIntervals.size()][]);
    }
}
