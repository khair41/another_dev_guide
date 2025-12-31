package com.framework.patterns.stack;

import java.util.Stack;

/**
 * Pattern 68: Stack - Min Stack Design
 */
public class StackMinStackDesign {

    /*
     * =================================================================================
     * CONDITIONS FOR APPLICABILITY
     * =================================================================================
     *
     * 1.  Problem Goal: You need to design a stack data structure that, in addition to the standard
     *     `push`, `pop`, and `top` operations, also supports a `getMin` operation that retrieves the
     *     minimum element currently in the stack. All operations must run in O(1) constant time.
     *
     * 2.  Logic: The challenge is tracking the minimum element efficiently as items are pushed and popped.
     *     A single stack is not enough. The common solution is to use two stacks or to store pairs of values.
     *
     *     -   **Two-Stack Approach**:
     *         -   One stack (`mainStack`) behaves like a normal stack.
     *         -   A second stack (`minStack`) stores the minimum value seen *at or below* the current level
     *           of the `mainStack`.
     *         -   **`push(x)`**: Push `x` onto `mainStack`. Then, compare `x` with the top of `minStack`.
     *           Push the smaller of the two onto `minStack`. If `minStack` is empty, just push `x`.
     *           `minStack.push(min(x, minStack.peek()))`.
     *         -   **`pop()`**: Pop from both `mainStack` and `minStack`.
     *         -   **`top()`**: Return `mainStack.peek()`.
     *         -   **`getMin()`**: Return `minStack.peek()`.
     *
     *     -   **Single Stack of Pairs Approach**:
     *         -   The stack stores pairs `(value, current_minimum)`.
     *         -   When pushing a new value `x`, the `current_minimum` for that pair is `min(x, stack.peek().current_minimum)`.
     *
     * =================================================================================
     * GENERIC TEMPLATE (Min Stack using Two Stacks)
     * =================================================================================
     */

    private Stack<Integer> mainStack;
    private Stack<Integer> minStack;

    /**
     * Initializes the MinStack object.
     */
    public StackMinStackDesign() {
        mainStack = new Stack<>();
        minStack = new Stack<>();
    }

    /**
     * Pushes the element val onto the stack.
     * @param val The value to push.
     */
    public void push(int val) {
        // --- Push onto the main stack ---
        mainStack.push(val);

        // --- Core Pattern Logic: Update the min stack ---
        int currentMin = val;
        if (!minStack.isEmpty()) {
            currentMin = Math.min(val, minStack.peek());
        }
        minStack.push(currentMin);
        // ----------------------------------------------
    }

    /**
     * Removes the element on the top of the stack.
     */
    public void pop() {
        // --- Pop from both stacks to keep them in sync ---
        mainStack.pop();
        minStack.pop();
        // ------------------------------------------------
    }

    /**
     * Gets the top element of the stack.
     * @return The top element.
     */
    public int top() {
        return mainStack.peek();
    }

    /**
     * Retrieves the minimum element in the stack.
     * @return The minimum element.
     */
    public int getMin() {
        // --- The minimum is always at the top of the min stack ---
        return minStack.peek();
        // -------------------------------------------------------
    }
}
