package com.problems.stack.solutions;

import com.framework.Solution;

public class LargestRectangleInHistogramPrefixSuffixSolution implements Solution<int[], Integer> {

    /*
     * --- APPROACH ---
     * For each bar `i`, the largest rectangle that can be formed with this bar as the
     * shortest side extends to the first bar on its left that is shorter (`prevSmaller`)
     * and the first bar on its right that is shorter (`nextSmaller`).
     * This approach pre-calculates these boundaries for all bars in two passes.
     *
     * --- INTUITION ---
     * The brute-force O(N^2) approach is slow because we repeatedly scan for boundaries.
     * We can optimize this by creating two arrays: `prevSmaller` and `nextSmaller`. We can populate
     * these arrays in O(N) time each. Once we have these boundaries, a third pass can calculate
     * the area for each bar `i` as `heights[i] * (nextSmaller[i] - prevSmaller[i] - 1)`.
     *
     * --- COMPLEXITY ---
     *
     *   - Time: O(N)
     *     [We make three separate passes through the array, which simplifies to O(N).]
     *
     *   - Space: O(N)
     *     [We use two additional arrays of size N to store the `prevSmaller` and `nextSmaller` indices.]
     */

    @Override
    public Integer execute(int[] heights) {
        // TODO: Implement the Prefix/Suffix solution logic here.
        return 0; // Placeholder
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
