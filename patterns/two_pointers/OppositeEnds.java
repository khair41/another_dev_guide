package patterns.two_pointers;

/**
 * A template for the "Opposite Ends" Two Pointers pattern.
 * This pattern is primarily used on sorted arrays to find a pair of elements that satisfy a condition.
 */
public class OppositeEnds {

    /**
     * Generic template for the Opposite Ends pattern.
     * @param arr A sorted array of integers.
     */
    public void template(int[] arr) {
        // 1. Initialize pointers at the start and end of the array.
        int left = 0;
        int right = arr.length - 1;

        // 2. Loop until the pointers meet or cross.
        while (left < right) {
            // 3. Perform some logic with the elements at the pointers.
            int currentSum = arr[left] + arr[right];

            // 4. Based on the result, move one of the pointers inward.
            // The condition here is the core of the problem (e.g., comparing to a target).
            if (/* condition is met, e.g., currentSum == target */ false) {
                // Found a solution. Handle it.
                // You might return, or move pointers to find more solutions.
                left++;
                right--;
            } else if (/* sum is too small */ false) {
                // If the current pair is too small, we need a larger value.
                // Move the left pointer to the right to increase the sum.
                left++;
            } else { // sum is too large
                // If the current pair is too large, we need a smaller value.
                // Move the right pointer to the left to decrease the sum.
                right--;
            }
        }
        // 5. Handle the case where no solution is found after the loop finishes.
    }
}
