package com.framework.patterns.array_matrix;

/**
 * Pattern 81: Array/Matrix - Set Matrix Zeroes (In-place Marking)
 */
public class ArrayMatrixSetZeroes {

    /*
     * =================================================================================
     * CONDITIONS FOR APPLICABILITY
     * =================================================================================
     *
     * 1.  Input Data Structure: An M x N matrix.
     *
     * 2.  Problem Goal: If an element in the matrix is 0, you must set its entire row and column to 0.
     *     This must be done in-place.
     *
     * 3.  Logic: A naive approach using extra O(m+n) space would be to have two boolean arrays, one for
     *     rows and one for columns, to mark which ones need to be zeroed out. The O(1) space solution
     *     is the core of this pattern.
     *
     *     -   **The Insight**: We can use the first row and first column of the matrix itself as our markers,
     *         instead of separate arrays. This avoids using extra space.
     *     -   **The Problem**: The first row and column are now dual-purpose. `matrix[0][0]` is especially
     *         problematic as it represents both the first row and the first column. We need separate
     *         variables to track the status of the original first row and first column.
     *
     *     -   **Algorithm**:
     *         1.  Create two boolean flags, `isFirstRowZero` and `isFirstColZero`, to check if the original
     *             first row/column contained any zeros. Scan them initially to set these flags.
     *         2.  Iterate through the *rest* of the matrix (from `[1,1]` onwards). If you find a zero at
     *             `matrix[r][c]`, use the first row/column as markers: set `matrix[r][0] = 0` and
     *             `matrix[0][c] = 0`.
     *         3.  Iterate through the rest of the matrix again (from `[1,1]`). If `matrix[r][0] == 0` or
     *             `matrix[0][c] == 0`, then set `matrix[r][c] = 0`.
     *         4.  Finally, use the `isFirstRowZero` and `isFirstColZero` flags to zero out the first row
     *             and first column if they needed it.
     *
     * =================================================================================
     * GENERIC TEMPLATE (Set Matrix Zeroes)
     * =================================================================================
     */

    /**
     * Sets the entire row and column to 0 if an element is 0, in-place.
     *
     * @param matrix The m x n matrix.
     */
    public void setZeroes(int[][] matrix) {
        if (matrix == null || matrix.length == 0) return;
        int rows = matrix.length;
        int cols = matrix[0].length;

        // --- Step 1: Check if the first row and column need to be zeroed ---
        boolean isFirstRowZero = false;
        for (int c = 0; c < cols; c++) {
            if (matrix[0][c] == 0) {
                isFirstRowZero = true;
                break;
            }
        }
        boolean isFirstColZero = false;
        for (int r = 0; r < rows; r++) {
            if (matrix[r][0] == 0) {
                isFirstColZero = true;
                break;
            }
        }
        // -------------------------------------------------------------------

        // --- Step 2: Use the first row/col as markers for the rest of the matrix ---
        for (int r = 1; r < rows; r++) {
            for (int c = 1; c < cols; c++) {
                if (matrix[r][c] == 0) {
                    matrix[0][c] = 0;
                    matrix[r][0] = 0;
                }
            }
        }
        // -------------------------------------------------------------------------

        // --- Step 3: Zero out cells based on the markers ---
        for (int r = 1; r < rows; r++) {
            for (int c = 1; c < cols; c++) {
                if (matrix[r][0] == 0 || matrix[0][c] == 0) {
                    matrix[r][c] = 0;
                }
            }
        }
        // ---------------------------------------------------

        // --- Step 4: Zero out the first row and column if needed ---
        if (isFirstRowZero) {
            for (int c = 0; c < cols; c++) {
                matrix[0][c] = 0;
            }
        }
        if (isFirstColZero) {
            for (int r = 0; r < rows; r++) {
                matrix[r][0] = 0;
            }
        }
        // -----------------------------------------------------------
    }
}
