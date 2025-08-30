package com.problems.arrays_hashing.problems;

import com.framework.*;
import com.problems.arrays_hashing.solutions.EncodeAndDecodeStringsSolution;

import java.util.List;
import java.util.Map;

public class EncodeAndDecodeStrings implements Problem<List<String>, List<String>> {
    @Override
    public String getID() {
        return "0271";
    }

    @Override
    public Difficulty getDifficulty() {
        return Difficulty.MEDIUM;
    }

    @Override
    public String getName() {
        return "Encode and Decode Strings";
    }

    @Override
    public String getDescription() {
        return "Design an algorithm to encode a list of strings to a string. The encoded string is then sent over the network and is decoded back to the original list of strings.";
    }

    @Override
    public Source getSource() {
        return Source.LEETCODE;
    }

    @Override
    public String getExternalLink() {
        return "https://leetcode.com/problems/encode-and-decode-strings/";
    }

    @Override
    public List<Topic> getTopics() {
        return List.of(Topic.STRING, Topic.ARRAY);
    }

    @Override
    public Map<String, Solution<List<String>, List<String>>> getSolutions() {
        return Map.of(
                "Using Character Delimiter", new EncodeAndDecodeStringsSolution()
        );
    }

    @Override
    public List<TestCase<List<String>, List<String>>> getTestCases() {
        return List.of(
                // Provided test cases
                new TestCase<>(List.of("hello", "world"), List.of("hello", "world")),
                new TestCase<>(List.of("lint", "code", "love", "you"), List.of("lint", "code", "love", "you")),
                
                // Edge cases
                new TestCase<>(List.of(""), List.of("")), // Single empty string
                new TestCase<>(List.of(), List.of()), // Empty list
                new TestCase<>(List.of("", ""), List.of("", "")), // Multiple empty strings

                // Additional cases
                new TestCase<>(List.of("a#b", "c#d"), List.of("a#b", "c#d")), // Strings with delimiter character
                new TestCase<>(List.of("123", "!@$", " "), List.of("123", "!@$", " ")), // With numbers and special characters
                new TestCase<>(List.of("a".repeat(100)), List.of("a".repeat(100))) // A single long string
        );
    }

    public static void main(String[] args) {
        Problem<List<String>, List<String>> problem = new EncodeAndDecodeStrings();
        TestRunner.run(problem);
    }
}
