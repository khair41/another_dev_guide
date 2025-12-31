package com.framework.patterns.linked_list;

/**
 * Pattern 76: Linked List - Addition of Numbers
 */
public class LinkedListAddition {

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
     * 1.  Input Data Structure: Two linked lists where each node represents a digit of a number.
     *     The digits are often stored in reverse order.
     *
     * 2.  Problem Goal: You need to add the two numbers represented by the linked lists and return
     *     the sum as a new linked list.
     *
     * 3.  Logic: This problem simulates elementary school addition, column by column, while keeping
     *     track of a `carry` value.
     *
     *     -   Create a `dummy` head node for the result list and a `tail` pointer to build it.
     *     -   Initialize a `carry` variable to 0.
     *     -   Use pointers to traverse both input lists, `l1` and `l2`.
     *     -   **Algorithm**:
     *         -   Loop as long as `l1` is not null, `l2` is not null, or `carry` is not 0.
     *         -   In each iteration, get the values from the current nodes of `l1` and `l2`.
     *           If a list is shorter than the other, treat the value of the null node as 0.
     *         -   Calculate the `sum` for the current digit: `sum = val1 + val2 + carry`.
     *         -   Update the `carry` for the next digit: `carry = sum / 10`.
     *         -   The value for the new node is the last digit of the sum: `newNodeValue = sum % 10`.
     *         -   Create a new node with this value and append it to the result list using the `tail` pointer.
     *         -   Advance `l1`, `l2`, and `tail` pointers.
     *     -   Return `dummy.next`.
     *
     * =================================================================================
     * GENERIC TEMPLATE (Add Two Numbers)
     * =================================================================================
     */

    /**
     * Adds two numbers represented by linked lists.
     *
     * @param l1 The head of the first list, representing a number in reverse order.
     * @param l2 The head of the second list, representing a number in reverse order.
     * @return The head of a new list representing the sum.
     */
    public ListNode addTwoNumbers(ListNode l1, ListNode l2) {
        ListNode dummy = new ListNode(0);
        ListNode tail = dummy;
        int carry = 0;

        // --- Core Pattern Logic: Loop until both lists are done and no carry is left ---
        while (l1 != null || l2 != null || carry != 0) {
            // Get values, defaulting to 0 if a list is exhausted.
            int val1 = (l1 != null) ? l1.val : 0;
            int val2 = (l2 != null) ? l2.val : 0;

            // --- Simulation of grade-school addition ---
            int sum = val1 + val2 + carry;
            carry = sum / 10;
            int newNodeVal = sum % 10;
            // -----------------------------------------

            // Create and append the new node.
            tail.next = new ListNode(newNodeVal);
            tail = tail.next;

            // Advance list pointers if they are not null.
            if (l1 != null) l1 = l1.next;
            if (l2 != null) l2 = l2.next;
        }
        // -----------------------------------------------------------------------------

        return dummy.next;
    }
}
