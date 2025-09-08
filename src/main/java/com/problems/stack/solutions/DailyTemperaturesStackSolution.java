package com.problems.stack.solutions;

import com.framework.Solution;

import java.util.Stack;

public class DailyTemperaturesStackSolution implements Solution<int[], int[]> {

    /*
     * --- APPROACH ---
     * This solution uses a monotonic decreasing stack to efficiently find the next warmer day for each temperature.
     * We iterate through the `temperatures` array, and for each day, we check if it's warmer than the days
     * whose indices are currently stored in the stack. If it is, we've found their next warmer day.
     *
     * --- INTUITION ---
     * Imagine you're standing on a day `i` and looking for a warmer day. If you look at a previous day `j`
     * (where `j < i`), and `temperatures[j]` is *less than* `temperatures[i]`, then `temperatures[i]` is the
     * first warmer day for `temperatures[j]`. We can then calculate the difference `i - j` and store it.
     *
     * The stack helps us keep track of days for which we *haven't yet found* a warmer day, and importantly,
     * it maintains them in decreasing order of temperature. This way, when a new warmer day `temperatures[i]`
     * comes along, it can quickly resolve all the colder days on the stack that it's warmer than.
     * Each temperature is pushed onto the stack once and popped from the stack at most once, leading to efficiency.
     *
     * --- COMPLEXITY ---
     *
     *   - Time: O(N)
     *     We iterate through the `temperatures` array once. Each element is pushed onto the stack once and
     *     popped from the stack at most once. Therefore, the total number of operations is proportional to N.
     *
     *   - Space: O(N)
     *     In the worst-case scenario (e.g., a strictly decreasing temperature sequence like `[100, 90, 80, ...]`),
     *     all N elements might be pushed onto the stack before any are popped. Thus, the stack can hold up to N indices.
     */

    @Override
    public int[] execute(int[] temperatures) {
        // Initialize an answer array with zeros, as per problem statement.
        int[] answer = new int[temperatures.length];
        // Stack will store indices of temperatures.
        Stack<Integer> stack = new Stack<>();

        for (int i = 0; i < temperatures.length; i++) {
            // While stack is not empty AND current temperature is warmer than the temperature at stack's top
            while (!stack.isEmpty() && temperatures[i] > temperatures[stack.peek()]) {
                int prevDayIndex = stack.pop();
                answer[prevDayIndex] = i - prevDayIndex;
            }
            // Push current day's index onto the stack
            stack.push(i);
        }

        return answer;
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
