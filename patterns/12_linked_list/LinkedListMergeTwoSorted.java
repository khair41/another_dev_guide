package com.framework.patterns.linked_list;

/**
 * Pattern 75: Linked List - Merging Two Sorted Lists
 */
public class LinkedListMergeTwoSorted {

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
     * 1.  Input Data Structure: Two linked lists that are already sorted in ascending order.
     *
     * 2.  Problem Goal: You need to merge the two lists into a single, new sorted list that contains
     *     all the nodes from both input lists.
     *
     * 3.  Logic: The iterative approach uses a temporary `dummy` head node to simplify the code.
     *
     *     -   Create a `dummy` node to serve as the starting point of the merged list.
     *     -   Create a `tail` pointer, initially pointing to `dummy`, to build the new list.
     *     -   Use two pointers, `l1` and `l2`, to traverse the two input lists.
     *     -   **Algorithm**:
     *         -   While both `l1` and `l2` are not `null`:
     *             -   Compare the values at `l1` and `l2`.
     *             -   Append the smaller of the two nodes to the `tail` of the merged list.
     *             -   Advance the pointer of the list from which the node was taken.
     *             -   Advance the `tail` pointer.
     *         -   After the loop, one of the lists may still have remaining nodes. Append the
     *           entire remaining list to the `tail`.
     *         -   The merged list starts at `dummy.next`.
     *
     * =================================================================================
     * GENERIC TEMPLATE (Merge Two Sorted Lists)
     * =================================================================================
     */

    /**
     * Merges two sorted linked lists and returns it as a new sorted list.
     *
     * @param l1 The head of the first sorted list.
     * @param l2 The head of the second sorted list.
     * @return The head of the merged sorted list.
     */
    public ListNode mergeTwoLists(ListNode l1, ListNode l2) {
        // A dummy node to simplify handling the head of the new list.
        ListNode dummy = new ListNode(0);
        // `tail` will point to the last node in the merged list.
        ListNode tail = dummy;

        // --- Core Pattern Logic: Traverse both lists ---
        while (l1 != null && l2 != null) {
            if (l1.val <= l2.val) {
                tail.next = l1;
                l1 = l1.next;
            } else {
                tail.next = l2;
                l2 = l2.next;
            }
            // Move the tail pointer forward.
            tail = tail.next;
        }
        // -----------------------------------------------

        // --- Append the remaining list ---
        // At this point, at most one of l1 or l2 can be non-null.
        if (l1 != null) {
            tail.next = l1;
        } else {
            tail.next = l2;
        }
        // ---------------------------------

        return dummy.next;
    }
}
