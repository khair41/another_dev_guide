package patterns.two_pointers;

/**
 * A template for the "Same Direction" (or Fast/Slow) Two Pointers pattern.
 * This pattern is often used for in-place array modifications or for finding specific elements
 * without using extra space.
 */
public class SameDirection {

    /**
     * Generic template for the Same Direction pattern, often used for in-place modifications.
     * @param arr An array of integers.
     * @return The length of the modified, valid portion of the array.
     */
    public int template(int[] arr) {
        // 1. Initialize a `slow` pointer to mark the end of the valid/processed section.
        int slow = 0;

        // 2. Initialize a `fast` pointer to iterate through the entire array.
        for (int fast = 1; fast < arr.length; fast++) {
            // 3. Define a condition for when to include the `fast` pointer's element.
            // This is the core logic of the problem (e.g., if the element is not a duplicate,
            // or if it's not a zero to be moved to the end).
            if (/* some condition is met, e.g., arr[fast] != arr[slow] */ false) {
                // 4. When the condition is met, advance the `slow` pointer...
                slow++;
                // ...and update the element at the `slow` pointer's new position.
                arr[slow] = arr[fast];
            }
            // If the condition is not met, only the `fast` pointer advances, effectively skipping
            // or leaving behind the unwanted element.
        }

        // 5. The length of the valid sub-array is `slow + 1`.
        return slow + 1;
    }
}
