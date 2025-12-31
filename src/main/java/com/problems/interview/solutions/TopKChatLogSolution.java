package com.problems.interview.solutions;

import com.framework.Solution;
import com.problems.interview.problems.TopKChatLogProblem;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.Comparator;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

public class TopKChatLogSolution implements Solution<TopKChatLogProblem.Input, List<String>> {

    @Override
    public List<String> execute(TopKChatLogProblem.Input input) {
        String filename = input.filename();
        int k = input.k();

        var counts = new HashMap<String, Integer>();

        try (BufferedReader br = new BufferedReader(new FileReader(filename))) {
            String line;
            while ((line = br.readLine()) != null) {
                int start = line.indexOf('<');
                int end = line.indexOf('>');
                if (start == -1 || end == -1)
                    continue;

                String name = line.substring(start + 1, end);
                String message = line.substring(end + 1);

                counts.merge(name, message.split("\\s+").length, Integer::sum);
            }
        } catch (IOException e) {
            throw new RuntimeException("Error reading file: " + filename, e);
        }

        return counts.entrySet().stream()
                .sorted(Map.Entry.comparingByValue(Comparator.reverseOrder())).limit(k)
                .map(Map.Entry::getKey).collect(Collectors.toList());
    }

    @Override
    public String getTimeComplexity() {
        return "O(N * L) where N is number of lines and L is line length";
    }

    @Override
    public String getSpaceComplexity() {
        return "O(U) where U is number of unique users";
    }
}
