package com.framework.patterns.two_pointers;

/**
 * Pattern 4: Two Pointers - In-place Array Modification
 */
public class TwoPointersInPlaceArrayModification {

    /*
     * =================================================================================
     * CONDITIONS FOR APPLICABILITY
     * =================================================================================
     *
     * 1.  Input Data Structure: An array or list.
     *
     * 2.  Problem Goal: You need to modify an array in-place, often by moving certain elements
     *     to the beginning or end of the array. This is done to save space (O(1) space complexity).
     *     Common problems include "Remove Duplicates from Sorted Array" or "Move Zeroes to End".
     *
     * 3.  Logic: Two pointers are used to manage different parts of the array.
     *
     *     - A "write pointer" (or `slow` pointer) keeps track of the position where the next
     *       valid element should be written.
     *     - A "read pointer" (or `fast` pointer) iterates through the array to find elements
     *       that meet a certain condition.
     *     - When the `read` pointer finds a valid element, its value is copied to the `write`
     *       pointer's position, and the `write` pointer is advanced.
     *
     * =================================================================================
     * GENERIC TEMPLATE (Remove Duplicates from Sorted Array)
     * =================================================================================
     */

    /**
     * Removes duplicates from a sorted array in-place.
     *
     * @param sortedArr A sorted integer array.
     * @return The new "length" of the array with unique elements.
     */
    public int removeDuplicates(int[] sortedArr) {
        if (sortedArr == null || sortedArr.length == 0) {
            return 0;
        }

        // The `writePointer` indicates the position for the next unique element.
        // It starts at 1 because the first element is always considered unique.
        int writePointer = 1;

        // The `readPointer` scans the array from the second element.
        for (int readPointer = 1; readPointer < sortedArr.length; readPointer++) {
            
            // --- Core Pattern Logic ---
            // We are looking for an element that is NOT a duplicate of the one before it.
            if (sortedArr[readPointer] != sortedArr[readPointer - 1]) {
                
                // --- Problem-Specific Logic ---
                // When we find a new unique element, we copy it to the `writePointer`'s location.
                sortedArr[writePointer] = sortedArr[readPointer];
                
                // Advance the write pointer to the next available slot.
                writePointer++;
                // -----------------------------
            }
            // If the element is a duplicate, we do nothing but advance the `readPointer` (done by the loop).
            // -------------------------
        }

        // The `writePointer` now holds the count of unique elements, which is the new logical length.
        return writePointer;
    }
}
