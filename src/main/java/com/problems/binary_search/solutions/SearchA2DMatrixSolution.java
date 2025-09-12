package com.problems.binary_search.solutions;

import com.framework.Solution;
import com.problems.binary_search.problems.SearchA2DMatrixProblem.SearchA2DMatrixInput;

public class SearchA2DMatrixSolution implements Solution<SearchA2DMatrixInput, Boolean> {

    /*
     * --- APPROACH ---
     * The idea is to look at the boundaries of each row, we always know that a row bellow is going to have
     * greater values and a row above will have lesser values. So if we are going to check a certain row, we attempt
     * to check within the range of that row, start to finish.
     *
     * --- INTUITION ---
     * This approach works because we are trying to find the first the row where it might belong then do another
     * BS on the row if we find it.
     *
     * --- COMPLEXITY ---
     *
     *   - Time: O(log(m * n))
     *     [We are performing a binary search on range of a row then the row itself]
     *
     *   - Space: O(1)
     *     [We only use a few variables to store pointers and dimensions.]
     */

    @Override
    public Boolean execute(SearchA2DMatrixInput input) {
        int [][] matrix = input.matrix();
        int target = input.target();

        int rows = matrix.length;
        int cols = matrix[0].length;

        int top = 0; // or left
        int bot = rows - 1; // or right

        int row = -1;
        while(top <= bot){
            row = top + (bot - top) / 2;
            if(target < matrix[row][0]){ // means look on prev rows
                bot = row - 1;
            } else if (target > matrix[row][cols - 1]){
                top = row + 1;
            } else { // it's in between: target > matrix[midRow][0] && target < matrix[midRow][cols - 1]
                break; // because we find target might be in range of the row
            }


        }

        // it is possible that the search can break the boundaries of the numbers in the matrix
        // therefore either top or bot will break the condition of the main loop top <= bot
        // if this is the case, we know that the target value is not found
        if(top > bot){
            return false;
        }

        //now we search in the column
        int left = 0;
        int right = cols - 1;

        while(left <= right) {
            int mid = left + (right - left) / 2;
            if (target == matrix[row][mid]) {
                return true;
            }
            if (target < matrix[row][mid]) {
                right = mid - 1;
            } else {
                left = mid + 1;
            }
        }

        return false;
    }

    @Override
    public String getTimeComplexity() {
        return "O(log(m * n))";
    }

    @Override
    public String getSpaceComplexity() {
        return "O(1)";
    }
}
