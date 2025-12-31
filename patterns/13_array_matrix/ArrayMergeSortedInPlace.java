package com.framework.patterns.array_matrix;

/**
 * Pattern 84: Array - Merge Sorted Array (In-place from End)
 */
public class ArrayMergeSortedInPlace {

    /*
     * =================================================================================
     * CONDITIONS FOR APPLICABILITY
     * =================================================================================
     *
     * 1.  Input Data Structure: Two sorted arrays, `nums1` and `nums2`. The first array, `nums1`,
     *     has enough buffer space at its end to hold all the elements of `nums2`.
     *
     * 2.  Problem Goal: You need to merge `nums2` into `nums1` as one single sorted array, modifying
     *     `nums1` in-place.
     *
     * 3.  Logic: A naive approach of merging from the beginning would require shifting elements of `nums1`
     *     to make space, which is inefficient (O(m*n)). The key insight is to merge from the **end**
     *     of the arrays, not the beginning.
     *
     *     -   **Algorithm**:
     *         1.  Initialize three pointers:
     *             -   `p1`: Points to the last valid element of `nums1` (`m - 1`).
     *             -   `p2`: Points to the last element of `nums2` (`n - 1`).
     *             -   `p_write`: Points to the last available position in the `nums1` buffer (`m + n - 1`).
     *         2.  Loop as long as `p2` is non-negative (i.e., there are still elements in `nums2` to merge).
     *         3.  In each iteration, compare the elements at `nums1[p1]` and `nums2[p2]`.
     *             -   Place the larger of the two elements at the `p_write` position in `nums1`.
     *             -   Decrement the pointer (`p1` or `p2`) of the array from which the element was taken.
     *             -   Decrement the `p_write` pointer.
     *         4.  If `p1` becomes negative first, it means all remaining elements in `nums2` are smaller
     *             than any in `nums1`, so the rest of the loop will just copy the remaining `nums2` elements.
     *
     * =================================================================================
     * GENERIC TEMPLATE (Merge Sorted Array)
     * =================================================================================
     */

    /**
     * Merges `nums2` into `nums1` as one sorted array, in-place.
     *
     * @param nums1 The first sorted array, with buffer space at the end.
     * @param m The number of valid elements in `nums1`.
     * @param nums2 The second sorted array.
     * @param n The number of elements in `nums2`.
     */
    public void merge(int[] nums1, int m, int[] nums2, int n) {
        // --- Core Pattern Logic: Initialize pointers ---
        int p1 = m - 1;       // Pointer for last valid element in nums1
        int p2 = n - 1;       // Pointer for last element in nums2
        int p_write = m + n - 1; // Pointer for where to write the next largest element in nums1
        // -----------------------------------------------

        // --- Core Pattern Logic: Merge from the end ---
        // Loop backwards as long as there are elements in nums2 to be merged.
        while (p2 >= 0) {
            // If p1 is still valid and its element is larger, use it.
            if (p1 >= 0 && nums1[p1] > nums2[p2]) {
                nums1[p_write] = nums1[p1];
                p1--;
            } else {
                // Otherwise, use the element from nums2.
                nums1[p_write] = nums2[p2];
                p2--;
            }
            p_write--;
        }
        // ---------------------------------------------
        // Note: If nums2 is exhausted first, the remaining elements in nums1 are already
        // in their correct sorted positions, so no further action is needed.
    }
}
