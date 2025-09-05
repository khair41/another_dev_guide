package com.problems.stack.solutions;

import com.framework.Solution;

import java.util.Stack;

public class EvaluateReversePolishNotationStackSolution implements Solution<String[], Integer> {

    /*
     * --- APPROACH ---
     * Iterate through the tokens. If the token is a number, push it onto a stack.
     * If it's an operator, pop the last two numbers from the stack, perform the operation,
     * and push the result back onto the stack.
     *
     * --- INTUITION ---
     * RPN ensures that when you encounter an operator, its operands are the two most
     * recently seen numbers. The LIFO nature of a stack perfectly provides these two operands.
     *
     * --- COMPLEXITY ---
     *
     *   - Time: O(N)
     *    Since we need to iterate all of the tokens
     *
     *   - Space: O(N)
     *     [TODO: Briefly explain why.]
     */

    @Override
    public Integer execute(String[] tokens) {
        String operators = "+-*/";
        Stack<Integer> s = new Stack();
        for (String token : tokens) {
            if (operators.contains(token)) {
                int r = s.pop();
                int l = s.pop();
                if (token.equals("+")) s.push(l + r);
                else if (token.equals("-")) s.push(l - r);
                else if (token.equals("*")) s.push(l * r);
                else if (token.equals("/")) s.push(l / r);
            } else {
                s.push(Integer.parseInt(token));
            }
        }
        return s.pop();
    }

    @Override
    public String getTimeComplexity() {
        return "O(N)";
    }

    @Override
    public String getSpaceComplexity() {
        return "O(N)";
    }
}
