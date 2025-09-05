package com.problems.stack.problems;

import com.framework.*;
import com.problems.stack.solutions.MinStackSingleStackAndVarSolution;
import com.problems.stack.solutions.MinStackSingleStackSolution;
import com.problems.stack.solutions.MinStackSolution;

import java.util.List;
import java.util.Map;

public class MinStackProblem implements Problem<List<MinStackProblem.Operation>, List<Integer>> {

    // A record to represent a single operation on the MinStack
    public record Operation(String name, Integer value) {
        // Constructor for operations without a value (e.g., pop, top, getMin)
        public Operation(String name) {
            this(name, null);
        }
    }

    @Override
    public String getID() {
        return "0155";
    }

    @Override
    public Difficulty getDifficulty() {
        return Difficulty.MEDIUM;
    }

    @Override
    public String getName() {
        return "Min Stack";
    }

    @Override
    public String getDescription() {
        return """
                Design a stack that supports push, pop, top, and retrieving the minimum element in constant time.

                Implement the MinStack class:
                - MinStack() initializes the stack object.
                - void push(int val) pushes the element val onto the stack.
                - void pop() removes the element on the top of the stack.
                - int top() gets the top element of the stack.
                - int getMin() retrieves the minimum element in the stack.

                You must implement a solution with O(1) time complexity for each function.""";
    }

    @Override
    public Source getSource() {
        return Source.LEETCODE;
    }

    @Override
    public String getExternalLink() {
        return "https://leetcode.com/problems/min-stack/";
    }

    @Override
    public List<Topic> getTopics() {
        return List.of(Topic.STACK, Topic.DESIGN);
    }

    @Override
    public Map<String, Solution<List<Operation>, List<Integer>>> getSolutions() {
        return Map.of(
                "Two Stacks", new MinStackSolution(),
                "Single Stack with Pairs", new MinStackSingleStackSolution(),
                "Single Stack with Variable", new MinStackSingleStackAndVarSolution()
        );
    }

    @Override
    public List<TestCase<List<Operation>, List<Integer>>> getTestCases() {
        // Test Case 1: LeetCode Example
        List<Operation> ops1 = List.of(
                new Operation("MinStack"),
                new Operation("push", -2),
                new Operation("push", 0),
                new Operation("push", -3),
                new Operation("getMin"),
                new Operation("pop"),
                new Operation("top"),
                new Operation("getMin")
        );
        List<Integer> expected1 = List.of(-3, 0, -2);

        // Test Case 2: Popping the minimum element
        List<Operation> ops2 = List.of(
                new Operation("MinStack"),
                new Operation("push", 5),
                new Operation("push", 1),
                new Operation("push", 10),
                new Operation("getMin"), // Expected: 1
                new Operation("pop"),
                new Operation("getMin"), // Expected: 1
                new Operation("pop"),
                new Operation("getMin")  // Expected: 5
        );
        List<Integer> expected2 = List.of(1, 1, 5);

        // Test Case 3: Duplicate minimum values
        List<Operation> ops3 = List.of(
                new Operation("MinStack"),
                new Operation("push", 1),
                new Operation("push", 5),
                new Operation("push", 1),
                new Operation("getMin"), // Expected: 1
                new Operation("pop"),
                new Operation("getMin")  // Expected: 1
        );
        List<Integer> expected3 = List.of(1, 1);

        // Test Case 4: Descending order (min changes on each push)
        List<Operation> ops4 = List.of(
                new Operation("MinStack"),
                new Operation("push", 3),
                new Operation("getMin"), // Expected: 3
                new Operation("push", 2),
                new Operation("getMin"), // Expected: 2
                new Operation("push", 1),
                new Operation("getMin")  // Expected: 1
        );
        List<Integer> expected4 = List.of(3, 2, 1);

        // Test Case 5: Complex sequence
        List<Operation> ops5 = List.of(
                new Operation("MinStack"),
                new Operation("push", 2),
                new Operation("push", 0),
                new Operation("push", 3),
                new Operation("push", 0),
                new Operation("getMin"), // Expected: 0
                new Operation("pop"),
                new Operation("getMin"), // Expected: 0
                new Operation("pop"),
                new Operation("getMin"), // Expected: 0
                new Operation("pop"),
                new Operation("getMin")  // Expected: 2
        );
        List<Integer> expected5 = List.of(0, 0, 0, 2);

        return List.of(
                new TestCase<>(ops1, expected1),
                new TestCase<>(ops2, expected2),
                new TestCase<>(ops3, expected3),
                new TestCase<>(ops4, expected4),
                new TestCase<>(ops5, expected5)
        );
    }

    public static void main(String[] args) {
        Problem<List<Operation>, List<Integer>> problem = new MinStackProblem();
        TestRunner.run(problem);
    }
}
