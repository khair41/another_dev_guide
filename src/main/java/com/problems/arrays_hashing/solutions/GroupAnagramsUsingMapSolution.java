package com.problems.arrays_hashing.solutions;

import com.framework.Solution;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class GroupAnagramsUsingMapSolution implements Solution<String[], List<List<String>>> {

    @Override
    public List<List<String>> execute(String[] input) {
        Map<String, List<String>> mp = new HashMap<>();
        for (String word : input) {
            int[] chars = new int[26];
            for (char c : word.toCharArray()) {
                chars[c - 'a']++;
            }
            String key = Arrays.toString(chars);
            mp.computeIfAbsent(key, k -> new ArrayList<>()).add(word);
        }
        return new ArrayList<>(mp.values());
    }

    @Override
    public String getTimeComplexity() {
        return "O(N * K) N is the number of strings, K is the max length of a string";
    }

    @Override
    public String getSpaceComplexity() {
        return "O(N * K) To store the map entries";
    }
}
