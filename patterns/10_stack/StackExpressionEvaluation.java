package com.framework.patterns.stack;

import java.util.Stack;

/**
 * Pattern 66: Stack - Expression Evaluation (RPN/Infix)
 */
public class StackExpressionEvaluation {

    /*
     * =================================================================================
     * CONDITIONS FOR APPLICABILITY
     * =================================================================================
     *
     * 1.  Input Data Structure: A sequence of tokens (numbers and operators) representing a
     *     mathematical expression, typically in Reverse Polish Notation (RPN) or infix notation.
     *
     * 2.  Problem Goal: You need to parse and compute the value of the expression.
     *
     * 3.  Logic (for Reverse Polish Notation - RPN):
     *     -   RPN is convenient because it removes the need for parentheses and operator precedence rules.
     *       The expression `(3 + 4) * 5` is `3 4 + 5 *` in RPN.
     *     -   A stack is used to hold operands (numbers).
     *     -   Iterate through the tokens of the RPN expression.
     *     -   **If the token is a number**: Push it onto the stack.
     *     -   **If the token is an operator** (`+`, `-`, `*`, `/`):
     *         -   Pop the top two operands from the stack. Note the order: the second popped element
     *           is the first operand (`operand1`), and the first popped is the second (`operand2`).
     *         -   Perform the operation: `result = operand1 operator operand2`.
     *         -   Push the `result` back onto the stack.
     *     -   After iterating through all tokens, the stack will contain exactly one number, which is the
     *       final result of the expression.
     *
     * =================================================================================
     * GENERIC TEMPLATE (Evaluate Reverse Polish Notation)
     * =================================================================================
     */

    /**
     * Evaluates the value of an arithmetic expression in Reverse Polish Notation.
     *
     * @param tokens An array of strings, where each string is either an integer or an operator.
     * @return The result of the expression.
     */
    public int evalRPN(String[] tokens) {
        Stack<Integer> stack = new Stack<>();

        // --- Core Pattern Logic ---
        for (String token : tokens) {
            switch (token) {
                case "+": {
                    int operand2 = stack.pop();
                    int operand1 = stack.pop();
                    stack.push(operand1 + operand2);
                    break;
                }
                case "-": {
                    int operand2 = stack.pop();
                    int operand1 = stack.pop();
                    stack.push(operand1 - operand2);
                    break;
                }
                case "*": {
                    int operand2 = stack.pop();
                    int operand1 = stack.pop();
                    stack.push(operand1 * operand2);
                    break;
                }
                case "/": {
                    int operand2 = stack.pop();
                    int operand1 = stack.pop();
                    stack.push(operand1 / operand2);
                    break;
                }
                default: {
                    // If it's not an operator, it must be a number.
                    stack.push(Integer.parseInt(token));
                    break;
                }
            }
        }
        // --------------------------

        // The final result is the last number remaining on the stack.
        return stack.pop();
    }
}
