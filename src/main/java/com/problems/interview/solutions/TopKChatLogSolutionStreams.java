package com.problems.interview.solutions;

import com.framework.Solution;
import com.problems.interview.problems.TopKChatLogProblem;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.Comparator;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;
import java.util.stream.Stream;

public class TopKChatLogSolutionStreams
        implements Solution<TopKChatLogProblem.Input, List<String>> {

    @Override
    public List<String> execute(TopKChatLogProblem.Input input) {
        String filename = input.filename();
        int k = input.k();

        try (Stream<String> lines = Files.lines(Path.of(filename))) {
            return lines.map(TopKChatLogSolutionStreams::parseLine)
                    .collect(Collectors.toMap(Map.Entry::getKey, Map.Entry::getValue, Integer::sum))
                    .entrySet().stream()
                    .sorted(Map.Entry.comparingByValue(Comparator.reverseOrder())).limit(k)
                    .map(Map.Entry::getKey).collect(Collectors.toList());

        } catch (IOException e) {
            throw new RuntimeException(e);
        }

    }

    private static Map.Entry<String, Integer> parseLine(String line) {
        int start = line.indexOf('<');
        int end = line.indexOf('>');
        if (start == -1 || end == -1)
            return null;

        String name = line.substring(start + 1, end);
        String message = line.substring(end + 1);

        return Map.entry(name, message.split("\\s+").length);
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
