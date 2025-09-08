package com.problems.stack.solutions;

import com.framework.Solution;

import java.util.ArrayList;
import java.util.List;

public class GenerateParenthesesRecursiveSolution implements Solution<Integer, List<String>> {

    /*
     * --- APPROACH ---
     *  Use a backtracking (or recursive) helper function that builds the string character by character.
     * The function will keep track of the number of open and closed parentheses used so far.
     *
     * --- INTUITION ---
     *  A string of parentheses is valid only if two conditions are met at all times:
     *   1. The number of open parentheses used is less than the target `n`.
     *   2. The number of closed parentheses is less than the number of open parentheses.
     * The backtracking function explores all possible valid paths. It adds an open parenthesis if it can,
     * and it adds a closed parenthesis if it can. When the string reaches the desired length (2*n),
     * a valid combination has been found.
     *
     * --- COMPLEXITY ---
     *
     *   - Time: O(4^n / sqrt(n))
     *     [This is related to Catalan numbers. It's complex; a simple O(2^n) is an acceptable high-level bound.]
     *
     *   - Space: O(n)
     *     [The space is dominated by the recursion depth, which will go at most 2*n deep.]
     */

    @Override
    public List<String> execute(Integer n) {
        List<String> res = new ArrayList();
        backtracking(res, "", 0, 0, n);
        return res;
    }

    void backtracking(List<String> ans, String cur, int open, int close, int max){
        if(cur.length() == max * 2){
            ans.add(cur);
            return;
        }

        if(open < max){
            // the stack remembers what is the state of curr and open
            // when the callback comes back, we are just attempting to build a different combination
            // that's why we don't need an additional variables to trace the string state
            backtracking(ans, cur + "(", open + 1, close, max);
        }
        if(close < open){
            backtracking(ans, cur + ")", open, close + 1, max);
        }
    }

    @Override
    public String getTimeComplexity() {
        return "O(4^n / sqrt(n))";
    }

    @Override
    public String getSpaceComplexity() {
        return "O(n)";
    }
}
