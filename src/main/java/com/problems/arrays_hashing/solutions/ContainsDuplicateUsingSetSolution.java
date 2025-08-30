package com.problems.arrays_hashing.solutions;

import com.framework.Solution;

import java.util.HashSet;
import java.util.Set;

public class ContainsDuplicateUsingSetSolution implements Solution<int[], Boolean> {
    @Override
    public Boolean execute(int[] nums) {
        Set<Integer> seen = new HashSet<>();
        for (int num : nums) {
            if (seen.contains(num)) {
                return true;
            }
            seen.add(num);
        }
        return false;
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
