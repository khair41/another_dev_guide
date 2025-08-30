package com.problems.arrays_hashing.solutions;

import com.framework.Solution;
import com.problems.arrays_hashing.problems.TopKFrequentElements;

import java.util.Arrays;
import java.util.HashMap;
import java.util.Map;

public class TopKFrequentElementsUsingSortingSolution implements Solution<TopKFrequentElements.TopKFrequentElementsInput, int[]> {

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

        int[][] arr = new int[counts.size()][2];
        int pos = 0;
        for(Map.Entry<Integer, Integer> entry : counts.entrySet()){
            arr[pos][0] = entry.getValue();
            arr[pos][1] = entry.getKey();
            pos++;
        }
        Arrays.sort(arr, (a, b) -> b[0] - a[0]);
        int[] res = new int[k];
        for(int i = 0; i < k; i++){
            res[i] = arr[i][1];
        }
        return res;
    }

    @Override
    public String getTimeComplexity() {
        return "O(N log N) where N is the length of the input array";
    }

    @Override
    public String getSpaceComplexity() {
        return "O(N + K) where N is the length of the input array";
    }
}
