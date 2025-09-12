package com.problems.binary_search.problems;

import com.framework.*;
import com.problems.binary_search.solutions.KokoEatingBananasSolution;

import java.util.List;
import java.util.Map;

public class KokoEatingBananasProblem implements Problem<KokoEatingBananasProblem.KokoEatingBananasInput, Integer> {

    // A record to hold the input for this problem
    public record KokoEatingBananasInput(int[] piles, int h) {}

    @Override
    public String getID() {
        return "0875";
    }

    @Override
    public Difficulty getDifficulty() {
        return Difficulty.MEDIUM;
    }

    @Override
    public String getName() {
        return "Koko Eating Bananas";
    }

    @Override
    public String getDescription() {
        return """
                Koko loves to eat bananas. There are n piles of bananas, the ith pile has piles[i] bananas. The guards have gone and will come back in h hours.

                Koko can decide her bananas-per-hour eating speed of k. Each hour, she chooses some pile of bananas and eats k bananas from that pile. If the pile has less than k bananas, she eats all of them instead and will not eat any more bananas during this hour.

                Koko likes to eat slowly but still wants to finish all the bananas before the guards come back.

                Return the minimum integer k such that she can eat all the bananas within h hours.""";
    }

    @Override
    public Source getSource() {
        return Source.LEETCODE;
    }

    @Override
    public String getExternalLink() {
        return "https://leetcode.com/problems/koko-eating-bananas/";
    }

    @Override
    public List<Topic> getTopics() {
        return List.of(Topic.BINARY_SEARCH, Topic.ARRAY);
    }

    @Override
    public Map<String, Solution<KokoEatingBananasInput, Integer>> getSolutions() {
        return Map.of(
                "Binary Search on Answer", new KokoEatingBananasSolution()
        );
    }

    @Override
    public List<TestCase<KokoEatingBananasInput, Integer>> getTestCases() {
        return List.of(
                new TestCase<>(new KokoEatingBananasInput(new int[]{3, 6, 7, 11}, 8), 4),
                new TestCase<>(new KokoEatingBananasInput(new int[]{30, 11, 23, 4, 20}, 5), 30),
                new TestCase<>(new KokoEatingBananasInput(new int[]{30, 11, 23, 4, 20}, 6), 23)
        );
    }

    public static void main(String[] args) {
        Problem<KokoEatingBananasInput, Integer> problem = new KokoEatingBananasProblem();
        TestRunner.run(problem);
    }
}
