package com.problems.binary_search.solutions;

import com.framework.Solution;
import com.problems.binary_search.problems.KokoEatingBananasProblem.KokoEatingBananasInput;

import java.util.Arrays;

public class KokoEatingBananasSolution implements Solution<KokoEatingBananasInput, Integer> {

    /*
     * --- APPROACH ---
     * This is a "Binary Search on the Answer" problem. The range of possible answers for Koko's
     * eating speed `k` is from 1 to the maximum number of bananas in any single pile. We can use
     * binary search to efficiently find the minimum valid speed `k` in this range.
     *
     * --- INTUITION ---
     * The problem has a monotonic property: If Koko can finish all bananas with a speed of `k`,
     * she can definitely also finish them with any speed greater than `k`. This allows us to use
     * binary search. For a given `mid` speed from our search range, we have a helper function `canFinish`
     * that checks if it's a possible solution. If she can finish, we record `mid` as a potential answer
     * and try a smaller speed to see if we can do better (move `right` pointer left). If she cannot finish,
     * `mid` is too slow, and we must try a larger speed (move `left` pointer right).
     *
     * It is easier to undersand the problem with a table
     *  piles =  [3,6,7,11] h = 8
     *      ceil(pile/k)
     * k    time            h       valid
     * 1    3, 6, 7, 11     27      no
     * 2    2, 3, 4, 6      15      no
     * 3    1, 2, 3, 4      10      no
     * 4    1, 2, 2, 3      8       yes
     * 5    1, 2, 2, 5      8       yes
     * 6    1, 1, 2, 2      6       yes
     * 7    1, 1, 1, 2      5       yes
     * ...
     * 11   1, 1, 1, 1      4       yes
     *
     *
     *
     * --- COMPLEXITY ---
     *
     *   - Time: O(N * log(M))
     *     [Where N is the number of piles and M is the range of possible speeds (from 1 to max pile size).
     *      The binary search takes log(M) steps, and for each step, we iterate through all N piles to check the speed.]
     *
     *   - Space: O(1)
     *     [We only use a few variables to store pointers and the result.]
     */

    @Override
    public Integer execute(KokoEatingBananasInput input) {
        int[] piles = input.piles();
        int h = input.h();

        // 1. Define the search space for `k`.
        int left = 1;
        int right = 0;
        for (int pile : piles) {
            right = Math.max(right, pile);
        }

        int result = right; // The result will be at most the max pile size.

        // 2. Perform binary search.
        while (left <= right) {
            int k = left + (right - left) / 2;
            if (k == 0) { // Avoid division by zero if left starts at 0
                left = 1;
                continue;
            }

            // Calculate the hours needed for the current speed `k`.
            long hoursNeeded = 0;
            for (int pile : piles) {
                // Math.ceil(a / b) is equivalent to (a + b - 1) / b for positive integers.
                hoursNeeded += (long) (pile + k - 1) / k;
            }

            // 3. Check if the speed is valid and adjust search space.
            if (hoursNeeded <= h) {
                // This speed `k` is a possible answer. Store it and try for a smaller speed.
                result = k;
                right = k - 1;
            } else {
                // This speed `k` is too slow. We must increase our speed.
                left = k + 1;
            }
        }

        return result;
    }

    @Override
    public String getTimeComplexity() {
        return "O(N * log(M))";
    }

    @Override
    public String getSpaceComplexity() {
        return "O(1)";
    }
}
