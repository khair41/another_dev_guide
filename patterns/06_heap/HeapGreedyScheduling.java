package com.framework.patterns.heap;

import java.util.Arrays;
import java.util.PriorityQueue;

/**
 * Pattern 45: Heap - Scheduling / Minimum Cost (Greedy with Priority Queue)
 */
public class HeapGreedyScheduling {

    /*
     * =================================================================================
     * CONDITIONS FOR APPLICABILITY
     * =================================================================================
     *
     * 1.  Input Data Structure: A collection of items with properties like start/end times, costs, or profits.
     *
     * 2.  Problem Goal: You need to make a sequence of greedy choices to find an optimal result.
     *     The problem often involves processing items in a specific order (e.g., by start time)
     *     and using a heap to maintain the best available choice at any given moment.
     *     Examples: "Meeting Rooms II" (min rooms needed), "Course Schedule III" (max courses to take).
     *
     * 3.  Logic: This pattern combines sorting with a priority queue to make informed greedy decisions.
     *
     *     -   **Step 1: Sort the Input**: Sort the items based on a primary attribute. For scheduling
     *         problems, this is almost always the **start time**.
     *
     *     -   **Step 2: Iterate and Use a Heap**: Iterate through the sorted items. The heap is used to
     *         keep track of the "active" items or choices.
     *         -   For a scheduling problem like "Meeting Rooms II", the heap (a min-heap) would store
     *             the **end times** of meetings currently in progress.
     *         -   When considering a new meeting, check the root of the heap. If the new meeting's
     *             start time is after the earliest ending meeting in the heap, you can reuse that room.
     *             To do this, `poll()` the heap and `offer()` the new meeting's end time.
     *         -   If the new meeting starts before any current meetings end, you need a new room.
     *             You just `offer()` the new meeting's end time. The size of the heap will grow.
     *         -   The maximum size the heap reaches during the process is the answer.
     *
     * =================================================================================
     * GENERIC TEMPLATE (Meeting Rooms II)
     * =================================================================================
     */

    /**
     * Finds the minimum number of conference rooms required for a set of meetings.
     *
     * @param intervals An array of meeting time intervals, where `intervals[i] = [start, end]`.
     * @return The minimum number of conference rooms required.
     */
    public int minMeetingRooms(int[][] intervals) {
        if (intervals == null || intervals.length == 0) {
            return 0;
        }

        // --- Core Pattern Logic: Step 1 - Sort by start time ---
        Arrays.sort(intervals, (a, b) -> a[0] - b[0]);
        // -------------------------------------------------------

        // --- Core Pattern Logic: Step 2 - Use a Min-Heap for end times ---
        // The heap stores the end times of the meetings currently in progress.
        PriorityQueue<Integer> minHeap = new PriorityQueue<>();

        // Add the end time of the first meeting.
        minHeap.offer(intervals[0][1]);

        // Iterate through the rest of the meetings.
        for (int i = 1; i < intervals.length; i++) {
            int currentStartTime = intervals[i][0];
            int currentEndTime = intervals[i][1];

            // --- Problem-Specific Greedy Choice ---
            // Check the meeting that finishes earliest (the root of the min-heap).
            if (currentStartTime >= minHeap.peek()) {
                // If the current meeting starts after or at the same time the earliest one ends,
                // we can reuse that room. We do this by polling the old meeting and adding the new one.
                minHeap.poll();
            }
            // In either case, add the current meeting's end time to the heap.
            minHeap.offer(currentEndTime);
            // -------------------------------------
        }

        // The size of the heap at the end is the maximum number of concurrent meetings,
        // which is the number of rooms required.
        return minHeap.size();
    }
}
