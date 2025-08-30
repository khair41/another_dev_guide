package com.problems.arrays_hashing.problems;

import com.framework.*;
import com.problems.arrays_hashing.solutions.GroupAnagramsUsingMapSolution;
import com.problems.arrays_hashing.solutions.GroupAnagramsUsingSortSolution;

import java.util.List;
import java.util.Map;

public class GroupAnagrams implements Problem<String[], List<List<String>>> {

    @Override
    public String getID() {
        return "0049";
    }

    @Override
    public Difficulty getDifficulty() {
        return Difficulty.MEDIUM;
    }

    @Override
    public String getName() {
        return "Group Anagrams";
    }

    @Override
    public String getDescription() {
        return """
                Given an array of strings strs, group the anagrams together. You can return the answer in any order.
                
                An Anagram is a word or phrase formed by rearranging the letters of a different word or phrase, typically using all the original letters exactly once.""" ;
    }

    @Override
    public Source getSource() {
        return Source.LEETCODE;
    }

    @Override
    public String getExternalLink() {
        return "https://leetcode.com/problems/group-anagrams/";
    }

    @Override
    public List<Topic> getTopics() {
        return List.of(Topic.ARRAY, Topic.STRING, Topic.HASHING);
    }

    @Override
    public Map<String, Solution<String[], List<List<String>>>> getSolutions() {
        return Map.of(
                "Using Map", new GroupAnagramsUsingMapSolution(),
                "Using Sort", new GroupAnagramsUsingSortSolution()
        );
    }

    @Override
    public List<TestCase<String[], List<List<String>>>> getTestCases() {
        return List.of(
                // Provided test cases
                new TestCase<>(new String[]{"eat", "tea", "tan", "ate", "nat", "bat"},
                        List.of(List.of("bat"), List.of("nat", "tan"), List.of("ate", "eat", "tea")), true),
                new TestCase<>(new String[]{""}, List.of(List.of("")), true),
                new TestCase<>(new String[]{"a"}, List.of(List.of("a")), true),

                // Additional cases
                new TestCase<>(new String[]{"abc", "def", "ghi"}, 
                        List.of(List.of("abc"), List.of("def"), List.of("ghi")), true), // No anagrams
                new TestCase<>(new String[]{"listen", "silent", "enlist"}, 
                        List.of(List.of("listen", "silent", "enlist")), true), // All anagrams
                new TestCase<>(new String[]{"tea", "ate", "tea"}, 
                        List.of(List.of("tea", "ate", "tea")), true), // With duplicates
                new TestCase<>(new String[]{"", "b", ""}, 
                        List.of(List.of("", ""), List.of("b")), true), // Mixed empty strings
                new TestCase<>(new String[]{"a", "ab", "abc"}, 
                        List.of(List.of("a"), List.of("ab"), List.of("abc")), true) // Different lengths
        );
    }

    public static void main(String[] args) {
        Problem<String[], List<List<String>>> problem = new GroupAnagrams();
        TestRunner.run(problem);
    }
}
