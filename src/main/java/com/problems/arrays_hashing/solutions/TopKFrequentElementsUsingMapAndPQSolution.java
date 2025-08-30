package com.problems.arrays_hashing.solutions;

import com.framework.Solution;
import com.problems.arrays_hashing.problems.TopKFrequentElements.TopKFrequentElementsInput;

import java.util.Comparator;
import java.util.HashMap;
import java.util.Map;
import java.util.PriorityQueue;

public class TopKFrequentElementsUsingMapAndPQSolution implements Solution<TopKFrequentElementsInput, int[]> {

    @Override
    public int[] execute(TopKFrequentElementsInput input) {
        int[] nums = input.nums();
        int k = input.k();

        Map<Integer, Integer> counts = new HashMap<>();
        for (int num : nums) {
            counts.put(num, counts.getOrDefault(num, 0) + 1);
        }

        // Provide an explicit comparator to the PriorityQueue to sort by frequency (the first element of the array).
        PriorityQueue<int[]> heap = new PriorityQueue<>((a, b) -> a[0] - b[0]);

        for (Map.Entry<Integer, Integer> entry : counts.entrySet()) {
            heap.offer(new int[]{entry.getValue(), entry.getKey()});
            if (heap.size() > k) {
                heap.poll();
            }
        }

        int[] res = new int[k];
        for (int i = 0; i < k; i++) {
            res[i] = heap.poll()[1];
        }
        return res;
    }

    @Override
    public String getTimeComplexity() {
        return "O(N log K) where N is the length of the input array and K is the value of k";
    }

    @Override
    public String getSpaceComplexity() {
        return "O(N + K) where N is the length of the input array and K is the value of k";
    }
}
