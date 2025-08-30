package com.framework;

/**
 * A simple, immutable data holder for a single test case.
 * It uses a Java Record for conciseness, which is ideal for Java 16+.
 *
 * @param <I> The type of the input.
 * @param <O> The type of the expected output.
 * @param orderAgnostic Whether the order of the output matters.
 */
public record TestCase<I, O>(I input, O expectedOutput, boolean orderAgnostic) {
    /**
     * A constructor for when the order of the output does not matter.
     * @param input The input.
     * @param expectedOutput The expected output.
     */
    public TestCase(I input, O expectedOutput) {
        this(input, expectedOutput, false);
    }
}
