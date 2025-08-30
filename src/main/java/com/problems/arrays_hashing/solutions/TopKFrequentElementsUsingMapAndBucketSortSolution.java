package com.problems.arrays_hashing.solutions;

import com.framework.Solution;
import com.problems.arrays_hashing.problems.TopKFrequentElements;

import java.util.*;

public class TopKFrequentElementsUsingMapAndBucketSortSolution implements Solution<TopKFrequentElements.TopKFrequentElementsInput, int[]> {
    @Override
    public int[] execute(TopKFrequentElements.TopKFrequentElementsInput input) {
        int[] nums = input.nums();
        int k = input.k();

        int n = nums.length;

        if(n == 0) return new int []{};

        Map<Integer, Integer> counts = new HashMap<>();
        for(int num : nums){
            counts.put(num, counts.getOrDefault(num, 0) + 1);
        }

        List<Integer>[] buckets = new ArrayList[n + 1];
        for(int i = 1; i < buckets.length; i++){
            buckets[i] = new ArrayList<>();
        }
        for(Map.Entry<Integer, Integer> entry : counts.entrySet()){
            buckets[entry.getValue()].add(entry.getKey());
        }

        int [] res = new int[k];

        for(int i = n; i > 0; i--){
            for(int num : buckets[i]){
                res[--k] = num;
                if(k == 0) return res;
            }
        }
        return res;
    }

    @Override
    public String getTimeComplexity() {
        return "O(N + K) where N is the length of the input array and K is the value of k";
    }

    @Override
    public String getSpaceComplexity() {
        return "O(N + K) where N is the length of the input array and K is the value of k";
    }
}
