package com.framework.patterns.heap;

import java.util.PriorityQueue;

/**
 * Pattern 43: Heap - Two Heaps for Median Finding
 */
public class HeapTwoHeapsMedian {

    /*
     * =================================================================================
     * CONDITIONS FOR APPLICABILITY
     * =================================================================================
     *
     * 1.  Input Data Structure: A stream of numbers arriving one by one.
     *
     * 2.  Problem Goal: You need to efficiently find the median of the numbers seen so far after
     *     each new number arrives. The median is the middle value in a sorted list.
     *
     * 3.  Logic: The core idea is to maintain two heaps that represent the two halves of the sorted list.
     *
     *     -   A **Max-Heap (`smallHalf`)**: Stores the smaller half of the numbers. The top of this heap
     *         is the largest number in the smaller half.
     *     -   A **Min-Heap (`largeHalf`)**: Stores the larger half of the numbers. The top of this heap
     *         is the smallest number in the larger half.
     *
     *     -   **Balancing Rule**: The heaps must be kept balanced or nearly balanced. The size of `smallHalf`
     *         should always be equal to or one greater than the size of `largeHalf`.
     *
     *     -   **Adding a number `num`**:
     *         1.  Add `num` to the `smallHalf` (the max-heap). This keeps all new numbers initially in the smaller half.
     *         2.  To maintain the property that `smallHalf` only contains smaller numbers, take the largest element
     *             from `smallHalf` (its root) and move it to `largeHalf` (the min-heap).
     *         3.  Enforce the balancing rule. If `largeHalf` has more elements than `smallHalf`, move the smallest
     *             element from `largeHalf` (its root) back to `smallHalf`.
     *
     *     -   **Finding the Median**:
     *         -   If the total number of elements is odd, the median is the root of `smallHalf` (the larger heap).
     *         -   If the total number of elements is even, the median is the average of the roots of both heaps.
     *
     * =================================================================================
     * GENERIC TEMPLATE (Find Median from Data Stream)
     * =================================================================================
     */

    private PriorityQueue<Integer> smallHalf; // Max-heap
    private PriorityQueue<Integer> largeHalf; // Min-heap

    /**
     * Initializes the data structure.
     */
    public HeapTwoHeapsMedian() {
        // `smallHalf` is a max-heap, so we use a custom comparator.
        smallHalf = new PriorityQueue<>((a, b) -> b - a);
        // `largeHalf` is a min-heap by default.
        largeHalf = new PriorityQueue<>((a, b) -> a - b);
    }

    /**
     * Adds a number to the data structure.
     * @param num The number to add.
     */
    public void addNum(int num) {
        // --- Core Pattern Logic: Step 1 & 2 - Add to small, move max to large ---
        smallHalf.offer(num);
        largeHalf.offer(smallHalf.poll());
        // ----------------------------------------------------------------------

        // --- Core Pattern Logic: Step 3 - Balance the heaps ---
        if (smallHalf.size() < largeHalf.size()) {
            smallHalf.offer(largeHalf.poll());
        }
        // -----------------------------------------------------
    }

    /**
     * Finds the median of all numbers added so far.
     * @return The median.
     */
    public double findMedian() {
        // --- Core Pattern Logic: Calculate median from heap tops ---
        if (smallHalf.size() > largeHalf.size()) {
            // If total numbers are odd, the median is the top of the larger heap.
            return smallHalf.peek();
        } else {
            // If total numbers are even, the median is the average of the two heap tops.
            return (smallHalf.peek() + largeHalf.peek()) / 2.0;
        }
        // ----------------------------------------------------------
    }
}
