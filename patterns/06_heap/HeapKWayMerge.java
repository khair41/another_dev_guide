package com.framework.patterns.heap;

import java.util.PriorityQueue;

/**
 * Pattern 44: Heap - K-way Merge
 */
public class HeapKWayMerge {

    // Definition for a singly-linked list node.
    static class ListNode {
        int val;
        ListNode next;
        ListNode(int x) { val = x; }
    }

    /*
     * =================================================================================
     * CONDITIONS FOR APPLICABILITY
     * =================================================================================
     *
     * 1.  Input Data Structure: You are given `k` sorted lists, arrays, or streams.
     *
     * 2.  Problem Goal: You need to merge these `k` sorted lists into a single sorted list, or
     *     find the Kth smallest element in the combined (conceptual) list.
     *     Examples: "Merge K Sorted Lists", "Find Kth Smallest Element in a Sorted Matrix".
     *
     * 3.  Logic: A Min-Heap is used to efficiently keep track of the smallest element among all `k` lists.
     *
     *     -   The heap will store one element from each of the `k` lists.
     *     -   **Initialization**: Add the first element from each of the `k` lists to the min-heap.
     *         The heap now contains the smallest elements from every list.
     *
     *     -   **Extraction and Insertion**:
     *         1.  Extract the minimum element from the heap. This is the absolute smallest element
     *             across all lists and is the next element in the final merged list.
     *         2.  Add the extracted element to your result list.
     *         3.  If the list from which the element was extracted still has more elements, add the
     *             next element from that same list to the heap.
     *         4.  Repeat this process until the heap is empty.
     *
     *     -   This approach is efficient because the heap always provides the next smallest element in
     *         O(log k) time, and you perform this `N` times (where `N` is the total number of elements),
     *         leading to an O(N log k) time complexity.
     *
     * =================================================================================
     * GENERIC TEMPLATE (Merge K Sorted Lists)
     * =================================================================================
     */

    /**
     * Merges `k` sorted linked lists into one sorted linked list.
     *
     * @param lists An array of sorted linked lists.
     * @return The head of the merged sorted linked list.
     */
    public ListNode mergeKLists(ListNode[] lists) {
        if (lists == null || lists.length == 0) {
            return null;
        }

        // --- Core Pattern Logic: Use a Min-Heap ---
        // The heap will store ListNode objects, comparing them by their `val`.
        PriorityQueue<ListNode> minHeap = new PriorityQueue<>((a, b) -> a.val - b.val);

        // --- Initialization: Add the head of each list to the heap ---
        for (ListNode head : lists) {
            if (head != null) {
                minHeap.offer(head);
            }
        }
        // -----------------------------------------------------------

        ListNode dummyHead = new ListNode(0);
        ListNode tail = dummyHead;

        // --- Core Pattern Logic: Extraction and Insertion ---
        while (!minHeap.isEmpty()) {
            // 1. Extract the smallest node from the heap.
            ListNode smallestNode = minHeap.poll();

            // 2. Add it to the result list.
            tail.next = smallestNode;
            tail = tail.next;

            // 3. If the extracted node's list has more elements, add the next one to the heap.
            if (smallestNode.next != null) {
                minHeap.offer(smallestNode.next);
            }
        }
        // -----------------------------------------------------

        return dummyHead.next;
    }
}
