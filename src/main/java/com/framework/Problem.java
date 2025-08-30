package com.framework;

import java.util.List;
import java.util.Map;

/**
 * Represents a single solvable problem in the playbook.
 * This interface defines the "contract" that every problem must adhere to
 * in order to be discovered and run by the com.framework.
 *
 * @param <I> The type of the input for the solution.
 * @param <O> The type of the output for the solution.
 */
public interface Problem<I, O> {

    /**
     * @return A unique, machine-readable ID for the problem (e.g., "is-palindrome").
     */
    String getID();

    /**
     * @return The difficulty level of the problem (e.g., EASY, MEDIUM, HARD).
     */
    Difficulty getDifficulty();

    /**
     * @return The display name of the problem (e.g., "Is Palindrome").
     */
    String getName();

    /**
     * @return A brief description of what the problem is about.
     */
    String getDescription();

    /**
     * @return The source of the problem (e.g., LeetCode, HackerRank).
     */
    Source getSource();

    /**
     * @return A URL to the problem's description online. Can be empty.
     */
    String getExternalLink();

    /**
     * @return A list of topics that this problem falls under.
     */
    List<Topic> getTopics();

    /**
     * Provides a map of all available solutions for this problem.
     * The key is a descriptive name for the approach (e.g., "Iterative", "Recursive"),
     * and the value is the solution object, containing the implementation and complexity analysis.
     *
     * @return A map of named solution functions.
     */
    Map<String, Solution<I, O>> getSolutions();

    /**
     * @return A list of test cases to verify the solution's correctness.
     */
    List<TestCase<I, O>> getTestCases();
}
