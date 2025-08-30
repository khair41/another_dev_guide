package com.problems.arrays_hashing.solutions;

import com.framework.Solution;
import com.problems.arrays_hashing.problems.TwoSum.TwoSumInput;

import java.util.HashMap;
import java.util.Map;

public class TwoSumUsingMapSolution implements Solution<TwoSumInput, int[]> {
    @Override
    public int[] execute(TwoSumInput input) {
        Map<Integer, Integer> complements = new HashMap<>();
        for (int i = 0; i < input.nums().length; i++) {
            int complement = input.target() - input.nums()[i];
            if (complements.containsKey(input.nums()[i])) {
                return new int[]{complements.get(input.nums()[i]), i};
            }
            complements.put(complement, i);
        }
        return new int[]{};
    }

    @Override
    public String getTimeComplexity() {
        return "O(N) where N is the length of the input array";
    }

    @Override
    public String getSpaceComplexity() {
        return "O(N) where N is the length of the input array";
    }
}
