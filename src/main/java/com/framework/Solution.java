package com.framework;

/**
 * Defines the contract for a single solution to a problem.
 * This interface ensures that every solution provides an implementation, along with its time and space complexity.
 *
 * @param <I> The type of the input for the solution.
 * @param <O> The type of the output for the solution.
 */
public interface Solution<I, O> {

    /**
     * Executes the solution's logic on the given input.
     *
     * @param input The input data for the problem.
     * @return The result of the solution.
     */
    O execute(I input);

    /**
     * @return The time complexity of the solution (e.g., "O(n)").
     */
    String getTimeComplexity();

    /**
     * @return The space complexity of the solution (e.g., "O(1)").
     */
    String getSpaceComplexity();
}
