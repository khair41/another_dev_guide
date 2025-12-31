package com.framework.patterns.two_pointers;

/**
 * Pattern 3: Two Pointers - Fixed Separation (Nth Node from End)
 */
public class TwoPointersFixedSeparation {

    // Definition for a singly-linked list node.
    static class ListNode {
        int val;
        ListNode next;
        ListNode(int x) { val = x; }
    }

    /*
     * =================================================================================
     * CONDITIONS FOR APPLICABILITY
     * =================================================================================
     *
     * 1.  Input Data Structure: A sequence that can be traversed linearly, like a Linked List
     *     or an array.
     *
     * 2.  Problem Goal: You need to find an element that is a fixed distance `n` from another
     *     element, or from the end of the sequence. The most common problem is "Find the Nth
     *     node from the end of a linked list."
     *
     * 3.  Traversal Logic: The core idea is to maintain a fixed separation between two pointers.
     *
     *     - A "leading" pointer is advanced `n` steps into the list first.
     *     - Then, both a "trailing" and the "leading" pointer advance one step at a time.
     *     - When the `leading` pointer reaches the end of the list, the `trailing` pointer
     *       will be at the desired position (the Nth node from the end).
     *
     * =================================================================================
     * GENERIC TEMPLATE (Nth Node from End)
     * =================================================================================
     */

    /**
     * Finds the nth node from the end of a linked list.
     *
     * @param head The head of the linked list.
     * @param n    The position from the end (1-based).
     * @return The nth node from the end, or null if n is invalid.
     */
    public ListNode findNthFromEnd(ListNode head, int n) {
        if (head == null || n <= 0) {
            return null;
        }

        ListNode leadingPointer = head;
        ListNode trailingPointer = head;

        // --- Core Pattern Logic: Phase 1 ---
        // Advance the leading pointer n steps forward.
        for (int i = 0; i < n; i++) {
            // If n is larger than the list size, the leading pointer will become null.
            if (leadingPointer == null) {
                // --- Problem-Specific Logic ---
                // Handle the case where n is out of bounds.
                return null;
                // -----------------------------
            }
            leadingPointer = leadingPointer.next;
        }
        // -----------------------------------

        // --- Core Pattern Logic: Phase 2 ---
        // Now, move both pointers together until the leading pointer reaches the end.
        while (leadingPointer != null) {
            leadingPointer = leadingPointer.next;
            trailingPointer = trailingPointer.next;
        }
        // -----------------------------------

        // At this point, the trailing pointer is at the nth node from the end.
        return trailingPointer;
    }
}
