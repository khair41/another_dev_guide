package com.framework.patterns.array_matrix;

/**
 * Pattern 79: Array/Matrix - In-place Rotation
 */
public class ArrayMatrixInPlaceRotation {

    /*
     * =================================================================================
     * CONDITIONS FOR APPLICABILITY
     * =================================================================================
     *
     * 1.  Input Data Structure: An N x N square matrix.
     *
     * 2.  Problem Goal: You need to rotate the matrix by 90 degrees (clockwise or counter-clockwise)
     *     without using another matrix for storage (i.e., in-place, with O(1) extra space).
     *
     * 3.  Logic (for Clockwise Rotation):
     *     -   A 90-degree clockwise rotation can be achieved by performing two distinct operations:
     *         1.  **Transpose the matrix**: Swap the element at `(r, c)` with the element at `(c, r)`
     *             for all `r < c`. This effectively flips the matrix over its main diagonal.
     *         2.  **Reverse each row**: After transposing, iterate through each row of the matrix and
     *             reverse the elements in that row.
     *
     *     -   **Example**:
     *         Original      Transpose       Reverse Each Row
     *         [1, 2, 3]     [1, 4, 7]         [7, 4, 1]
     *         [4, 5, 6]  -> [2, 5, 8]      -> [8, 5, 2]
     *         [7, 8, 9]     [3, 6, 9]         [9, 6, 3]
     *
     * =================================================================================
     * GENERIC TEMPLATE (Rotate Image Clockwise)
     * =================================================================================
     */

    /**
     * Rotates an n x n 2D matrix by 90 degrees clockwise in-place.
     *
     * @param matrix The n x n matrix.
     */
    public void rotate(int[][] matrix) {
        if (matrix == null || matrix.length == 0) {
            return;
        }
        int n = matrix.length;

        // --- Core Pattern Logic: Step 1 - Transpose the matrix ---
        for (int i = 0; i < n; i++) {
            for (int j = i + 1; j < n; j++) {
                int temp = matrix[i][j];
                matrix[i][j] = matrix[j][i];
                matrix[j][i] = temp;
            }
        }
        // ---------------------------------------------------------

        // --- Core Pattern Logic: Step 2 - Reverse each row ---
        for (int i = 0; i < n; i++) {
            int left = 0;
            int right = n - 1;
            while (left < right) {
                int temp = matrix[i][left];
                matrix[i][left] = matrix[i][right];
                matrix[i][right] = temp;
                left++;
                right--;
            }
        }
        // -----------------------------------------------------
    }
}
