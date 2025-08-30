package com.problems.arrays_hashing.solutions;

import com.framework.Solution;

import java.util.ArrayList;
import java.util.List;

public class EncodeAndDecodeStringsSolution implements Solution<List<String>, List<String>> {

    @Override
    public List<String> execute(List<String> input) {
        // The 'execute' method simulates the round-trip process.
        String encoded = encode(input);
        return decode(encoded);
    }

    // Encodes a list of strings to a single string.
    private String encode(List<String> strs) {
        StringBuilder sb = new StringBuilder();
        for (String s : strs) {
            sb.append(s.length()).append('#').append(s);
        }
        return sb.toString();
    }

    // Decodes a single string to a list of strings.
    private List<String> decode(String s) {
        List<String> result = new ArrayList<>();
        int i = 0;
        while (i < s.length()) {
            int j = i;
            while (s.charAt(j) != '#') {
                j++;
            }
            int length = Integer.parseInt(s.substring(i, j));
            i = j + 1 + length;
            result.add(s.substring(j + 1, i));
        }
        return result;
    }

    @Override
    public String getTimeComplexity() {
        return "O(N) where N is the total number of characters in the list";
    }

    @Override
    public String getSpaceComplexity() {
        return "O(N) where N is the total number of characters in the list";
    }
}
