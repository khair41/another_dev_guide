package com.problems.arrays_hashing.solutions;

import com.framework.Solution;

import java.util.*;


public class GroupAnagramsUsingSortSolution implements Solution<String[], List<List<String>>> {

    @Override
    public List<List<String>> execute(String[] input) {
        Map<String, List<String>> mp = new HashMap<>();
        for (String word : input) {
            char[] chars = word.toCharArray();
            Arrays.sort(chars);
            String sortedWord = new String(chars);
            mp.computeIfAbsent(sortedWord, k -> new ArrayList<>()).add(word);
        }
        return new ArrayList<>(mp.values());
    }

    @Override
    public String getTimeComplexity() {
        return "O(N * K log K) N is the number of strings, K is the max length of a string";
    }

    @Override
    public String getSpaceComplexity() {
        return "O(N * K) To store the map entries";
    }

}
