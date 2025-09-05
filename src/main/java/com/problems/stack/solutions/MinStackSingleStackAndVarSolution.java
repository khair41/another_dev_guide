package com.problems.stack.solutions;

import com.framework.Solution;
import com.problems.stack.problems.MinStackProblem.Operation;

import java.util.ArrayList;
import java.util.List;
import java.util.Stack;

public class MinStackSingleStackAndVarSolution implements Solution<List<Operation>, List<Integer>> {

    /*
     * --- APPROACH ---
     * This highly optimized solution uses a single stack and a single variable (`min`) to track the minimum.
     * The stack does not store the actual values, but rather the *difference* between the pushed value
     * and the `min` value at the time of the push. This is a form of differential encoding.
     *
     * --- INTUITION & STATE MANAGEMENT ---
     * The core invariant is that the `min` variable ALWAYS holds the true current minimum of the stack.
     * The stack's job is to store enough information to restore the *previous* minimum when the current one is popped.
     *
     * We can determine the state based on the sign of the value stored on the stack (`diff = val - min`):
     *
     *   - Case 1: Stored value is NON-NEGATIVE (`diff >= 0`)
     *     - What it means: The pushed value `val` was greater than or equal to the `min` when it was pushed.
     *     - Example: `min` is -5. We push `val` = -3. The stored `diff` is `-3 - (-5) = 2`. The `min` remains -5.
     *     - To get the original value: `val = diff + min` -> `2 + (-5) = -3`.
     *     - When popping `2`, the `min` variable is unaffected because the `diff` is not negative.
     *
     *   - Case 2: Stored value is NEGATIVE (`diff < 0`)
     *     - What it means: This is a special MARKER. The pushed value `val` was a new absolute minimum.
     *     - Example: `min` is -5. We push `val` = -8. The stored `diff` is `-8 - (-5) = -3`. We update `min` to -8.
     *     - The original value is the `min` itself.
     *     - When popping this marker (`-3`), we know the true minimum is being removed. We must restore the previous
     *       minimum using the formula: `previousMin = currentMin - diff` -> `-8 - (-3) = -5`.
     *
     * --- COMPLEXITY ---
     *
     *   - Time: O(1) for all operations.
     *   - Space: O(N) for the single stack.
     */

    class MinStack {
        private Stack<Long> stack;
        private long min;

        public MinStack() {
            stack = new Stack<>();
        }

        public void push(int val) {
            long longVal = val;
            if (stack.isEmpty()) {
                // For the first element, the difference is 0.
                stack.push(0L);
                min = longVal;
            } else {
                // Push the difference between the current value and the current minimum.
                stack.push(longVal - min);
                // If the new value is smaller, update the minimum.
                if (longVal < min) {
                    min = longVal;
                }
            }
        }

        public void pop() {
            if (stack.isEmpty()) {
                return;
            }
            long diff = stack.pop();

            // If the popped difference is negative, it was a marker for a previous minimum.
            // We must restore the previous minimum by reversing the push operation.
            if (diff < 0) {
                // previousMin = currentMin - diff
                min = min - diff;
            }
        }

        public int top() {
            long diff = stack.peek();
            if (diff > 0) {
                // If the difference is positive, the real value was `diff + min`.
                return (int) (diff + min);
            } else {
                // If the difference is non-positive, the real value is the current min itself.
                // This covers both the case where val == min (diff is 0) and val < min (diff is negative).
                return (int) min;
            }
        }

        public int getMin() {
            return (int) min;
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
