package com.framework.patterns.linked_list;

/**
 * Pattern 74: Linked List - In-place Reversal
 */
public class LinkedListInPlaceReversal {

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
     * 1.  Input Data Structure: A singly linked list.
     *
     * 2.  Problem Goal: You need to reverse the order of the nodes in the list. The reversal must
     *     be done "in-place", meaning you should not create new nodes but rather modify the `next`
     *     pointers of the existing nodes. This requires O(1) extra space.
     *
     * 3.  Logic: The standard iterative approach uses three pointers to traverse the list and reverse
     *     the connections.
     *
     *     -   `prev`: A pointer to the previous node, initialized to `null`.
     *     -   `current`: A pointer to the current node being processed, initialized to `head`.
     *     -   `next_temp`: A temporary pointer to store the next node in the original list before
     *       its link is broken.
     *
     *     -   **Algorithm**:
     *         -   While `current` is not `null`:
     *             1.  Store the next node: `next_temp = current.next`.
     *             2.  Reverse the pointer of the current node: `current.next = prev`.
     *             3.  Move the `prev` and `current` pointers one step forward for the next iteration:
     *                 `prev = current`
     *                 `current = next_temp`
     *         -   After the loop, `current` will be `null`, and `prev` will be pointing to the new head
     *           of the reversed list.
     *
     * =================================================================================
     * GENERIC TEMPLATE (Reverse Linked List)
     * =================================================================================
     */

    /**
     * Reverses a singly linked list in-place.
     *
     * @param head The head of the linked list.
     * @return The new head of the reversed linked list.
     */
    public ListNode reverseList(ListNode head) {
        ListNode prev = null;
        ListNode current = head;

        // --- Core Pattern Logic: Traverse and reverse pointers ---
        while (current != null) {
            // Store the next node before we overwrite current.next
            ListNode next_temp = current.next;

            // Reverse the current node's pointer
            current.next = prev;

            // Move pointers one position ahead.
            prev = current;
            current = next_temp;
        }
        // -------------------------------------------------------

        // `prev` is now the new head of the reversed list.
        return prev;
    }
}
