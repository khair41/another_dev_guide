package com.framework.patterns.linked_list;

/**
 * Pattern 78: Linked List - Reordering / Partitioning
 */
public class LinkedListReordering {

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
     * 2.  Problem Goal: You need to reorder the list in-place according to a specific pattern.
     *     A common example is to reorder it from `L0→L1→...→Ln-1→Ln` to `L0→Ln→L1→Ln-1→...`.
     *
     * 3.  Logic: This problem is a combination of several fundamental linked list operations.
     *
     *     -   **Step 1: Find the Middle of the List**.
     *         -   Use the Fast & Slow Pointer pattern (Pattern 2). The `slow` pointer will be at
     *           the middle of the list when the `fast` pointer reaches the end.
     *
     *     -   **Step 2: Split and Reverse the Second Half**.
     *         -   Split the list into two halves at the middle point.
     *         -   Reverse the second half of the list in-place (Pattern 74).
     *
     *     -   **Step 3: Merge the Two Halves**.
     *         -   You now have two lists: the first half and the reversed second half.
     *         -   Merge them by interleaving their nodes. Take one node from the first half, then one
     *           node from the second half, and so on, until the second half is exhausted.
     *
     * =================================================================================
     * GENERIC TEMPLATE (Reorder List)
     * =================================================================================
     */

    /**
     * Reorders a singly linked list to the form L0→Ln→L1→Ln-1→...
     *
     * @param head The head of the list.
     */
    public void reorderList(ListNode head) {
        if (head == null || head.next == null) {
            return;
        }

        // --- Step 1: Find the middle of the list ---
        ListNode slow = head, fast = head;
        while (fast != null && fast.next != null) {
            slow = slow.next;
            fast = fast.next.next;
        }

        // --- Step 2: Split and reverse the second half ---
        ListNode secondHalf = reverseList(slow.next);
        slow.next = null; // Split the list into two.

        // --- Step 3: Merge the two halves ---
        ListNode firstHalf = head;
        while (secondHalf != null) {
            ListNode temp1 = firstHalf.next;
            ListNode temp2 = secondHalf.next;

            firstHalf.next = secondHalf;
            secondHalf.next = temp1;

            firstHalf = temp1;
            secondHalf = temp2;
        }
    }

    /**
     * Helper function to reverse a linked list (from Pattern 74).
     */
    private ListNode reverseList(ListNode head) {
        ListNode prev = null;
        ListNode current = head;
        while (current != null) {
            ListNode nextTemp = current.next;
            current.next = prev;
            prev = current;
            current = nextTemp;
        }
        return prev;
    }
}
