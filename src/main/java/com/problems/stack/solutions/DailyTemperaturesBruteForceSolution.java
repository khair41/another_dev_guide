package com.problems.stack.solutions;

import com.framework.Solution;

public class DailyTemperaturesBruteForceSolution implements Solution<int[], int[]> {

    /*
     * --- APPROACH ---
     * Use a nested loop. For each day `i`, iterate through all the future days `j`
     * to find the first day with a warmer temperature.
     *
     * --- INTUITION ---
     * This is the most direct simulation of the problem statement. It checks every
     * possible future day for each starting day, guaranteeing the correct answer, albeit inefficiently.
     *
     * --- COMPLEXITY ---
     *
     *   - Time: O(N^2)
     *     [In the worst case (e.g., a strictly decreasing array), the inner loop runs N-i times for each of the N elements.]
     *
     *   - Space: O(1)
     *     [Excluding the space for the result array, we only use a few variables.]
     */

    @Override
    public int[] execute(int[] temperatures) {
        int n = temperatures.length;
        int[] res = new int[n];
        for(int i = 0; i < n; i++){
            // check after the immediate next temp
            int j = i + 1;
            while(j < n){
                // the first we find that is greater than curr element is the answer for `i`
                if(temperatures[j] > temperatures[i]){
                    // compute the distance this is the key to calc the value
                    // 4 3 2 1 5
                    //   ^i    ^j
                    // i.e: for num 3 i = 1 and j = 4, the diff between the pointers is the answer
                    res[i] = j - i;
                    break;
                }
                j++;
            }
        }

        return res;
    }

    @Override
    public String getTimeComplexity() {
        return "O(N^2)";
    }

    @Override
    public String getSpaceComplexity() {
        return "O(1)";
    }
}
