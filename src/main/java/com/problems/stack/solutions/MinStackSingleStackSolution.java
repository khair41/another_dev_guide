package com.problems.stack.solutions;

import com.framework.Solution;
import com.problems.stack.problems.MinStackProblem.Operation;

import java.util.ArrayList;
import java.util.List;
import java.util.Stack;

public class MinStackSingleStackSolution implements Solution<List<Operation>, List<Integer>> {

    /*
     * --- APPROACH ---
     * This solution uses a single stack where each element is an array of two integers: `[value, currentMinimum]`.
     * This avoids creating a custom class or record, sometimes offering a slight performance benefit.
     *
     * --- INTUITION ---
     * The logic is identical to the approach using pairs or custom objects. The stack maintains not just the
     * value, but also a snapshot of the minimum element at the time of the push. When we push a new value `val`,
     * we look at the current minimum (from the pair at the top of the stack). We calculate the new minimum
     * (`Math.min(val, currentMin)`) and then push the array `[val, newMin]` onto the stack.
     *
     * This ensures the top of the stack always contains the most recent state. `stack.peek()[0]` gives the top
     * value, and `stack.peek()[1]` gives the minimum of the entire stack in O(1) time.
     *
     * --- COMPLEXITY ---
     *
     *   - Time: O(1) for all operations.
     *     Each operation is a simple O(1) operation on the underlying stack.
     *
     *   - Space: O(N)
     *     The stack stores one `int[2]` array for each of the N elements pushed onto it.
     */

    class MinStack {
        private final Stack<int[]> stack;

        public MinStack() {
            stack = new Stack<>();
        }

        public void push(int val) {
            int newMin;
            if (stack.isEmpty()) {
                newMin = val;
            } else {
                int currentMin = stack.peek()[1]; // The min is at index 1
                newMin = Math.min(val, currentMin);
            }
            // Push a new array containing the value and the calculated minimum.
            stack.push(new int[]{val, newMin});
        }

        public void pop() {
            if (!stack.isEmpty()) {
                stack.pop();
            }
        }

        public int top() {
            // The top value is at index 0 of the array at the top of the stack.
            return stack.peek()[0];
        }

        public int getMin() {
            // The minimum value is at index 1 of the array at the top of the stack.
            return stack.peek()[1];
        }
    }

    // This is the driver method that our TestRunner will call.
    @Override
    public List<Integer> execute(List<Operation> operations) {
        MinStack minStack = null;
        List<Integer> results = new ArrayList<>();

        for (Operation op : operations) {
            switch (op.name()) {
                case "MinStack":
                    minStack = new MinStack();
                    break;
                case "push":
                    minStack.push(op.value());
                    break;
                case "pop":
                    minStack.pop();
                    break;
                case "top":
                    results.add(minStack.top());
                    break;
                case "getMin":
                    results.add(minStack.getMin());
                    break;
            }
        }
        return results;
    }

    @Override
    public String getTimeComplexity() {
        return "O(1) for all operations";
    }

    @Override
    public String getSpaceComplexity() {
        return "O(N)";
    }
}
