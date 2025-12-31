
package com.problems.interview.problems;

import com.framework.*;
import com.problems.interview.solutions.TopKChatLogSolution;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.List;
import java.util.Map;

public class TopKChatLogProblem implements Problem<TopKChatLogProblem.Input, List<String>> {

    public record Input(String filename, int k) {
    }

    @Override
    public String getID() {
        return "Interview-001";
    }

    @Override
    public Difficulty getDifficulty() {
        return Difficulty.MEDIUM;
    }

    @Override
    public String getName() {
        return "Top K Chat Log Users";
    }

    @Override
    public String getDescription() {
        return """
                Given a filename with a chat log in the format "00:00 <Name1> Hello World!" and an integer k,
                return the top k users who have the most words in the chat log.
                """;
    }

    @Override
    public Source getSource() {
        return Source.INTERNAL;
    }

    @Override
    public String getExternalLink() {
        return "";
    }

    @Override
    public List<Topic> getTopics() {
        return List.of(Topic.HASHING, Topic.SORTING, Topic.STRING);
    }

    @Override
    public Map<String, Solution<Input, List<String>>> getSolutions() {
        return Map.of("Default Solution", new TopKChatLogSolution());
    }

    @Override
    public List<TestCase<Input, List<String>>> getTestCases() {
        try {
            return List.of(
                    createTestCase(List.of("00:00 <Alice> Hello World", "00:01 <Bob> Hi",
                            "00:02 <Alice> How are you?", "00:03 <Charlie> I am good thanks"), 2,
                            List.of("Alice", "Charlie")),
                    createTestCase(List.of("00:00 <Alice> Hello", "00:01 <Bob> Hi"), 5,
                            List.of("Alice", "Bob")),
                    createTestCase(List.of(), 5, List.of()));
        } catch (IOException e) {
            throw new RuntimeException("Failed to create test cases", e);
        }
    }

    private TestCase<Input, List<String>> createTestCase(List<String> lines, int k,
            List<String> expected) throws IOException {
        Path tempFile = Files.createTempFile("chatlog", ".txt");
        Files.write(tempFile, lines);
        tempFile.toFile().deleteOnExit();
        return new TestCase<>(new Input(tempFile.toString(), k), expected, true);
    }

    public static void main(String[] args) {
        Problem<Input, List<String>> problem = new TopKChatLogProblem();
        TestRunner.run(problem);
    }
}
