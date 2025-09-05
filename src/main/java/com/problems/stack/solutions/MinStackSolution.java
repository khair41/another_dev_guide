package com.problems.stack.solutions;

import com.framework.Solution;
import com.problems.stack.problems.MinStackProblem.Operation;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;
import java.util.Stack;

public class MinStackSolution implements Solution<List<Operation>, List<Integer>> {

    /*
     * --- APPROACH ---
     * A common approach is to use two stacks. One stack stores the actual values,
     * and a second stack keeps track of the minimum value seen so far at each stage.
     *
     * --- INTUITION ---
     * By keeping a second stack of minimums, the current minimum is always at the top
     * of the min-stack, allowing for an O(1) `getMin` operation. You must carefully handle
     * push and pop operations to keep this second stack synchronized.
     *
     * --- COMPLEXITY ---
     *
     *   - Time: O(1) for all operations (push, pop, top, getMin).
     *
     *
     *   - Space: O(N)
     *     In the worst case, both stacks will store N elements.]
     */

    // This is the class you will implement.
    class MinStack {

        Stack<Integer> stack;
        Stack<Integer> minStack;
        int min = Integer.MAX_VALUE;


        public MinStack() {
            stack = new Stack<>();
            minStack = new Stack<>();

        }

        public void push(int val) {
            stack.push(val);
            // important to take in mind that multiple equal mins can exist, popping just 1 min can lead to errors
            if(val <= min){
                min = val;
                minStack.push(val);
            }
        }

        public void pop() {
            if(stack.isEmpty()) return;

            if(minStack.peek() == stack.pop()){
                minStack.pop();
                // always consider possible scenarios where the stack is empty, you need to reset the state
                min = minStack.isEmpty() ? Integer.MAX_VALUE : minStack.peek();
            }

        }

        public int top() {
            return stack.peek();
        }

        public int getMin() {
            return min;
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
