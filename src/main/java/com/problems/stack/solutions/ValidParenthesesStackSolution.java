package com.problems.stack.solutions;

import com.framework.Solution;

import java.util.HashMap;
import java.util.Map;
import java.util.Stack;

public class ValidParenthesesStackSolution implements Solution<String, Boolean> {

    /*
     * --- APPROACH ---
     * Use a Stack to keep track of open brackets. When we encounter a closing bracket,
     * we check if it matches the most recently opened bracket by looking at the top of the stack.
     * We are storing not processed items in the stack, the FIFO property of this DS is perfect for this problem
     * since we need to wait until the respective opening parenthesis is processed.
     *
     * --- INTUITION ---
     * The Last-In, First-Out (LIFO) nature of a stack perfectly mirrors the nested structure
     * of parentheses. The last bracket we open must be the first one we close. If we find a closing
     * bracket, but the stack is empty or its top doesn't match, the string is invalid.
     * After iterating through the whole string, the stack must be empty for it to be valid.
     *
     * --- COMPLEXITY ---
     *
     *   - Time: O(N)
     *     We iterate through the string of N characters once.
     *
     *   - Space: O(N)
     *     In the worst case (e.g., "((("), we store all N characters on the stack.
     */

    @Override
    public Boolean execute(String s) {
        Stack<Character> stack = new Stack<>();
        Map<Character, Character> map = new HashMap<>();

        // Add the respective configuration of parenthesis
        map.put(')', '(');
        map.put('}', '{');
        map.put(']', '[');

        for(char c : s.toCharArray()){
            // it its not a closing parenthesis, we add it to the stack for later processing
            if(!map.containsKey(c)){
                stack.push(c);
            } else {
                // We found a closing parenthesis so it's time to validate if the last processed element
                // corresponds to the opening parenthesis configuration.
                // Considering that the stack is empty is an important edge case to consider. You get to this point
                // by quickly testing the approach with 'weird' inputs i.e. '))))' you process the first parenthesis
                // therefore you try to get the last processing element but the stack is empty so the string is invalid
                if(stack.isEmpty() || stack.pop() != map.get(c)){
                    return false;
                }
            }
        }
        // Another weird input to test this condition is '((((' meaning we just add parenthesis but never get to evaluate
        // this particular check serves to handle preprocessed correct cases
        return stack.isEmpty();
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
