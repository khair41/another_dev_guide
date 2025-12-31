package com.framework.patterns.array_matrix;

import java.util.ArrayList;
import java.util.List;

/**
 * Pattern 80: Array/Matrix - Spiral Traversal
 */
public class ArrayMatrixSpiralTraversal {

    /*
     * =================================================================================
     * CONDITIONS FOR APPLICABILITY
     * =================================================================================
     *
     * 1.  Input Data Structure: An M x N matrix.
     *
     * 2.  Problem Goal: You need to traverse all the elements of the matrix in a spiral order
     *     (clockwise or counter-clockwise) and return them as a 1D list.
     *
     * 3.  Logic: The key is to simulate the spiral path by keeping track of the boundaries of the
     *     untraversed inner matrix. Four pointers are used to define these boundaries:
     *     `top`, `bottom`, `left`, and `right`.
     *
     *     -   **Algorithm**:
     *         1.  Initialize `top = 0`, `bottom = rows - 1`, `left = 0`, `right = cols - 1`.
     *         2.  Start a loop that continues as long as `top <= bottom` and `left <= right`.
     *         3.  **Traverse Right**: Iterate from `left` to `right` along the `top` row. Add elements to the result.
     *             After this, increment `top` because this row is now fully traversed.
     *         4.  **Traverse Down**: Iterate from `top` to `bottom` along the `right` column. Add elements.
     *             After this, decrement `right`.
     *         5.  **Traverse Left**: Before traversing, check if `top <= bottom`. Iterate from `right` to `left`
     *             along the `bottom` row. Add elements. After this, decrement `bottom`.
     *         6.  **Traverse Up**: Before traversing, check if `left <= right`. Iterate from `bottom` to `top`
     *             along the `left` column. Add elements. After this, increment `left`.
     *         7.  The loop repeats, shrinking the boundaries inwards until the pointers cross.
     *
     * =================================================================================
     * GENERIC TEMPLATE (Spiral Matrix)
     * =================================================================================
     */

    /**
     * Returns all elements of the matrix in spiral order.
     *
     * @param matrix The m x n matrix.
     * @return A list of all elements in spiral order.
     */
    public List<Integer> spiralOrder(int[][] matrix) {
        List<Integer> result = new ArrayList<>();
        if (matrix == null || matrix.length == 0) {
            return result;
        }

        int rows = matrix.length;
        int cols = matrix[0].length;
        int top = 0, bottom = rows - 1;
        int left = 0, right = cols - 1;

        // --- Core Pattern Logic: Shrinking Boundaries ---
        while (top <= bottom && left <= right) {
            // 1. Traverse Right
            for (int c = left; c <= right; c++) {
                result.add(matrix[top][c]);
            }
            top++;

            // 2. Traverse Down
            for (int r = top; r <= bottom; r++) {
                result.add(matrix[r][right]);
            }
            right--;

            // 3. Traverse Left (check boundary condition first)
            if (top <= bottom) {
                for (int c = right; c >= left; c--) {
                    result.add(matrix[bottom][c]);
                }
                bottom--;
            }

            // 4. Traverse Up (check boundary condition first)
            if (left <= right) {
                for (int r = bottom; r >= top; r--) {
                    result.add(matrix[r][left]);
                }
                left++;
            }
        }
        // --------------------------------------------------

        return result;
    }
}
