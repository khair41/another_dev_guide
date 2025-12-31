package com.problems.binary_search.problems;

import com.framework.*;
import com.problems.binary_search.solutions.TimeBasedKeyValueStoreSolution;

import java.util.List;
import java.util.Map;

public class TimeBasedKeyValueStoreProblem implements Problem<List<TimeBasedKeyValueStoreProblem.Operation>, List<String>> {

    // A record to represent a single operation on the TimeMap
    public record Operation(String name, String key, String value, Integer timestamp) {
        // Constructor for get operations
        public Operation(String name, String key, Integer timestamp) {
            this(name, key, null, timestamp);
        }
    }

    @Override
    public String getID() {
        return "0981";
    }

    @Override
    public Difficulty getDifficulty() {
        return Difficulty.MEDIUM;
    }

    @Override
    public String getName() {
        return "Time Based Key-Value Store";
    }

    @Override
    public String getDescription() {
        return """
                Design a time-based key-value data structure that can store multiple values for the same key at different time stamps and retrieve the key's value at a certain timestamp.

                Implement the TimeMap class:
                - TimeMap() Initializes the object.
                - void set(String key, String value, int timestamp) Stores the key with the value at the given time timestamp.
                - String get(String key, int timestamp) Returns a value such that set was called previously, with a timestamp_prev <= timestamp. If there are multiple such values, it returns the value associated with the largest timestamp_prev. If there are no values, it returns "".""";
    }

    @Override
    public Source getSource() {
        return Source.LEETCODE;
    }

    @Override
    public String getExternalLink() {
        return "https://leetcode.com/problems/time-based-key-value-store/";
    }

    @Override
    public List<Topic> getTopics() {
        return List.of(Topic.BINARY_SEARCH, Topic.HASH_MAP, Topic.DESIGN);
    }

    @Override
    public Map<String, Solution<List<Operation>, List<String>>> getSolutions() {
        return Map.of(
                "HashMap + Binary Search", new TimeBasedKeyValueStoreSolution()
        );
    }

    @Override
    public List<TestCase<List<Operation>, List<String>>> getTestCases() {
        // Test Case 1
        List<Operation> ops1 = List.of(
                new Operation("set", "foo", "bar", 1),
                new Operation("get", "foo", 1),
                new Operation("get", "foo", 3),
                new Operation("set", "foo", "bar2", 4),
                new Operation("get", "foo", 4),
                new Operation("get", "foo", 5)
        );
        List<String> expected1 = List.of("bar", "bar", "bar2", "bar2");

        return List.of(
                new TestCase<>(ops1, expected1)
        );
    }

    public static void main(String[] args) {
        Problem<List<Operation>, List<String>> problem = new TimeBasedKeyValueStoreProblem();
        TestRunner.run(problem);
    }
}
