package com.framework.patterns.greedy;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

/**
 * Pattern 58: Greedy - Sorting Based
 */
public class GreedySortingBased {

    /*
     * =================================================================================
     * CONDITIONS FOR APPLICABILITY
     * =================================================================================
     *
     * 1.  Input Data Structure: A collection of items where each item has multiple properties.
     *
     * 2.  Problem Goal: You need to find an optimal arrangement or selection of these items.
     *     The greedy choice is not immediately obvious and depends on a clever sorting order.
     *     Example: "Queue Reconstruction by Height".
     *
     * 3.  Logic: The core of the pattern is to find the correct sorting criteria that allows for
     *     a simple greedy placement strategy.
     *
     *     -   **The Sorting Insight (for Queue Reconstruction)**: Sort the people based on two criteria:
     *         1.  Primary: Decreasing order of height (`h`).
     *         2.  Secondary: Increasing order of the number of people in front (`k`).
     *
     *     -   **Why this works**: By processing the tallest people first, you can place them in the queue
     *         at their desired `k` position. When you later process shorter people, inserting them into
     *         the queue will NOT affect the `k` value of the taller people who are already placed
     *         (since `k` only counts people of the same height or taller).
     *
     *     -   **The Greedy Algorithm**:
     *         1.  Sort the input array using the custom comparator described above.
     *         2.  Create an empty list to represent the reconstructed queue.
     *         3.  Iterate through the sorted people. For each person `p`, insert them into the list at the
     *             index specified by their `k` value (`list.add(p.k, p)`).
     *         4.  The list at the end will be the correctly reconstructed queue.
     *
     * =================================================================================
     * GENERIC TEMPLATE (Queue Reconstruction by Height)
     * =================================================================================
     */

    /**
     * Reconstructs a queue from a list of people and their `k` values.
     *
     * @param people An array where `people[i] = [h, k]`, `h` is height and `k` is the number of
     *               people in front of this person who have a height greater than or equal to `h`.
     * @return The reconstructed queue.
     */
    public int[][] reconstructQueue(int[][] people) {
        // --- Core Pattern Logic: Step 1 - Custom Sort ---
        // Sort by height descending. If heights are equal, sort by k-value ascending.
        Arrays.sort(people, (a, b) -> {
            if (a[0] != b[0]) {
                return b[0] - a[0]; // Taller people first
            } else {
                return a[1] - b[1]; // If same height, smaller k-value first
            }
        });
        // --------------------------------------------------

        List<int[]> result = new ArrayList<>();

        // --- Core Pattern Logic: Step 2 & 3 - Greedy Insertion ---
        for (int[] person : people) {
            // Insert the person at the index specified by their k-value.
            result.add(person[1], person);
        }
        // ---------------------------------------------------------

        return result.toArray(new int[people.length][]);
    }
}
