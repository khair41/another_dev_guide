package com.problems.arrays_hashing.problems;

import com.framework.*;
import com.problems.arrays_hashing.solutions.ValidAnagramUsingMapSolution;
import com.problems.arrays_hashing.solutions.ValidAnagramUsingSortSolution;

import java.util.List;
import java.util.Map;

public class ValidAnagram implements Problem<ValidAnagram.ValidAnagramInput, Boolean> {

    public record ValidAnagramInput(String s, String t) {}

    @Override
    public String getID() {
        return "0242";
    }

    @Override
    public Difficulty getDifficulty() {
        return Difficulty.EASY;
    }

    @Override
    public String getName() {
        return "Valid Anagram";
    }

    @Override
    public String getDescription() {
        return """
                Given two strings s and t, return true if t is an anagram of s, and false otherwise." +
              
                An Anagram is a word or phrase formed by rearranging the letters of a different word or phrase, typically using all the original letters exactly once.""" ;
    }

    @Override
    public Source getSource() {
        return Source.LEETCODE;
    }

    @Override
    public String getExternalLink() {
        return "https://leetcode.com/problems/valid-anagram/";
    }

    @Override
    public List<Topic> getTopics() {
        return List.of(Topic.STRING, Topic.HASHING, Topic.SORTING);
    }

    @Override
    public Map<String, Solution<ValidAnagramInput, Boolean>> getSolutions() {
        return Map.of(
                "Using Map", new ValidAnagramUsingMapSolution(),
                "Using Sort", new ValidAnagramUsingSortSolution()
        );
    }

    @Override
    public List<TestCase<ValidAnagramInput, Boolean>> getTestCases() {
        return List.of(
                // Provided test cases
                new TestCase<>(new ValidAnagramInput("anagram", "nagaram"), true),
                new TestCase<>(new ValidAnagramInput("rat", "car"), false),

                // Edge cases
                new TestCase<>(new ValidAnagramInput("", ""), true), // Both empty
                new TestCase<>(new ValidAnagramInput("a", ""), false), // Different lengths
                new TestCase<>(new ValidAnagramInput("a", "b"), false), // Same length, not anagrams

                // Additional cases
                new TestCase<>(new ValidAnagramInput("listen", "silent"), true),
                new TestCase<>(new ValidAnagramInput("hello", "ollhe"), true),
                new TestCase<>(new ValidAnagramInput("aabbcc", "abcabc"), true),
                new TestCase<>(new ValidAnagramInput("apple", "pleap"), true),
//                new TestCase<>(new ValidAnagramInput("rail safety", "fairy tales"), true), // With spaces
//                new TestCase<>(new ValidAnagramInput("dormitory", "dirty room"), true), // With spaces
//                new TestCase<>(new ValidAnagramInput("hello", "Hello"), false), // Different casing
                new TestCase<>(new ValidAnagramInput("aacc", "ccac"), false) // Same characters, different counts
        );
    }

    public static void main(String[] args) {
        Problem<ValidAnagramInput, Boolean> problem = new ValidAnagram();
        TestRunner.run(problem);
    }

}
