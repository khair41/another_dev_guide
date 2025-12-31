package com.framework.patterns.sliding_window;

import java.util.Deque;
import java.util.LinkedList;

/**
 * Pattern 10: Sliding Window - Monotonic Queue for Max/Min
 */
public class SlidingWindowMonotonicQueue {

    /*
     * =================================================================================
     * CONDITIONS FOR APPLICABILITY
     * =================================================================================
     *
     * 1.  Input Data Structure: An array or list.
     *
     * 2.  Problem Goal: You need to find the maximum (or minimum) element in every
     *     contiguous subarray of a fixed size `k`. This is known as the "Sliding Window Maximum"
     *     or "Sliding Window Minimum" problem.
     *
     * 3.  Logic: A naive approach of scanning each window is slow (O(N*k)). A more efficient
     *     approach uses a Deque (Double-Ended Queue) to maintain a "monotonic" sequence of
     *     indices. For a sliding window maximum, this would be a monotonically decreasing queue.
     *
     *     - The Deque stores indices of elements, not the elements themselves.
     *     - The elements corresponding to the indices in the Deque are in decreasing order.
     *       This means the head of the Deque always holds the index of the maximum element in the current window.
     *
     *     - For each element `arr[i]` you process:
     *         1.  Remove indices from the *front* of the Deque that are no longer in the current window.
     *         2.  Remove indices from the *back* of the Deque whose corresponding elements are smaller
     *             than `arr[i]`. This maintains the decreasing monotonic property.
     *         3.  Add the current index `i` to the *back* of the Deque.
     *         4.  Once the window is fully formed (i >= k - 1), the maximum element for that window
     *             is `arr[deque.peekFirst()]`.
     *
     * =================================================================================
     * GENERIC TEMPLATE (Sliding Window Maximum)
     * =================================================================================
     */

    /**
     * Finds the maximum value in each contiguous subarray of size `k`.
     *
     * @param arr The input array.
     * @param k   The fixed size of the subarray window.
     * @return An array containing the maximum of each window.
     */
    public int[] maxSlidingWindow(int[] arr, int k) {
        if (arr == null || arr.length == 0 || k <= 0 || k > arr.length) {
            return new int[0];
        }

        int[] result = new int[arr.length - k + 1];
        int resultIndex = 0;

        // The Deque will store indices of elements.
        Deque<Integer> deque = new LinkedList<>();

        for (int i = 0; i < arr.length; i++) {

            // --- Core Pattern Logic: Step 1 - Remove out-of-window indices ---
            // If the index at the front is k steps behind the current index, it's no longer in the window.
            if (!deque.isEmpty() && deque.peekFirst() <= i - k) {
                deque.pollFirst();
            }
            // ------------------------------------------------------------------

            // --- Core Pattern Logic: Step 2 - Maintain monotonic property ---
            // Remove all elements from the back that are smaller than the current element.
            while (!deque.isEmpty() && arr[deque.peekLast()] < arr[i]) {
                deque.pollLast();
            }
            // ----------------------------------------------------------------

            // --- Core Pattern Logic: Step 3 - Add current index ---
            deque.offerLast(i);
            // ------------------------------------------------------

            // --- Problem-Specific Logic: Step 4 - Record the result ---
            // Once the first full window is formed, start recording the maximums.
            if (i >= k - 1) {
                // The maximum element of the current window is at the front of the deque.
                result[resultIndex++] = arr[deque.peekFirst()];
            }
            // -----------------------------------------------------------
        }

        return result;
    }
}
