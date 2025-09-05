package com.problems.stack.solutions;

import com.framework.Solution;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class EvaluateReversePolishNotationRecursiveSolution implements Solution<String[], Integer> {

    /*
     * --- APPROACH ---
     * Use a recursive helper function that processes the tokens from right to left.
     * When the function encounters an operator, it makes two recursive calls to evaluate the operands
     * that came before it. When it encounters a number, it returns the number.
     *
     * --- INTUITION ---
     * This approach uses the program's call stack as an implicit stack. Processing from right
     * to left, an operator knows it must operate on the results of the two expressions immediately
     * to its left. The recursion naturally evaluates these nested expressions first.
     *
     * --- COMPLEXITY ---
     *
     *   - Time: O(N)
     *
     *   - Space: O(N)
     *
     */

    @Override
    public Integer execute(String[] tokens) {
        List<String> tokensList = new ArrayList(Arrays.asList(tokens));
        return dfs(tokensList);
    }

    static int dfs(List<String> tokens) {
        // at each recursion call we remove the last char from the list
        // initially the notation will end in an operator so we need
        // we keep on the callback stack the current operator we are waiting to process
        String token = tokens.removeLast();

        if(!"+-*/".contains(token)){
            // the recursion call should stop looking when you find an integer, either for left or right
            return Integer.parseInt(token);
        }

        // we jump expecting this will return an integer we can consider for the actual operation
        // once both callbacks return an integer we can return the operation, this does not require
        // further exploration because its granted that the initial chars of the notation are going to be integers
        // so the right operand will always be an element present in the stack and the left will always be an operand
        // waiting to be computed
        int right = dfs(tokens);
        int left = dfs(tokens);

        if(token.equals("+")) return left + right;
        if(token.equals("-")) return left - right;
        if(token.equals("*")) return left * right;
        if(token.equals("/")) return left / right;

        return 0;
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
