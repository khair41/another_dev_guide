package com.framework.patterns.heap;

import java.util.HashMap;
import java.util.Map;
import java.util.PriorityQueue;

/**
 * Pattern 42: Heap - Top K Elements (Selection/Frequency)
 */
public class HeapTopKElements {

    /*
     * =================================================================================
     * CONDITIONS FOR APPLICABILITY
     * =================================================================================
     *
     * 1.  Input Data Structure: An array or list of elements.
     *
     * 2.  Problem Goal: You need to find the `k` largest, smallest, or most/least frequent
     *     elements in a collection. The key is that you only need the top `k` elements, not
     *     the entire sorted collection.
     *     Examples: "Kth Largest Element in an Array", "Top K Frequent Elements".
     *
     * 3.  Logic: A heap (PriorityQueue in Java) is the ideal data structure for this. It maintains
     *     a partially sorted order and provides efficient access to the min/max element.
     *
     *     -   To find the **Top K Largest** elements, use a **Min-Heap** of size `k`.
     *         -   Iterate through the input array.
     *         -   For each element, add it to the heap.
     *         -   If the heap's size exceeds `k`, remove the smallest element (the heap's root).
     *         -   After iterating through all elements, the heap will contain the `k` largest elements.
     *         -   The Kth largest element will be at the root of the heap.
     *
     *     -   To find the **Top K Smallest** elements, use a **Max-Heap** of size `k`.
     *         -   The logic is the same, but you remove the largest element when the size exceeds `k`.
     *
     *     -   This approach is more efficient (O(N log k)) than sorting the entire array (O(N log N)),
     *         especially when `k` is much smaller than `N`.
     *
     * =================================================================================
     * GENERIC TEMPLATE (Top K Frequent Elements)
     * =================================================================================
     */

    /**
     * Finds the `k` most frequent elements in an array.
     *
     * @param nums The input array.
     * @param k The number of top frequent elements to return.
     * @return An array containing the `k` most frequent elements.
     */
    public int[] topKFrequent(int[] nums, int k) {
        // --- Problem-Specific Logic: Step 1 - Count frequencies ---
        Map<Integer, Integer> frequencyMap = new HashMap<>();
        for (int num : nums) {
            frequencyMap.put(num, frequencyMap.getOrDefault(num, 0) + 1);
        }
        // ---------------------------------------------------------

        // --- Core Pattern Logic: Step 2 - Use a Min-Heap ---
        // The heap will store Map.Entry objects, comparing them by frequency (the value).
        PriorityQueue<Map.Entry<Integer, Integer>> minHeap = new PriorityQueue<>(
            (a, b) -> a.getValue() - b.getValue()
        );

        for (Map.Entry<Integer, Integer> entry : frequencyMap.entrySet()) {
            minHeap.offer(entry);
            // If the heap size is greater than k, remove the element with the lowest frequency.
            if (minHeap.size() > k) {
                minHeap.poll();
            }
        }
        // -----------------------------------------------------

        // --- Problem-Specific Logic: Step 3 - Extract results ---
        int[] result = new int[k];
        for (int i = k - 1; i >= 0; i--) {
            result[i] = minHeap.poll().getKey();
        }
        // -------------------------------------------------------

        return result;
    }
}
