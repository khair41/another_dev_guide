package com.framework.patterns.linked_list;

/**
 * Pattern 77: Linked List - Intersection Detection
 */
public class LinkedListIntersectionDetection {

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
     * 1.  Input Data Structure: Two singly linked lists.
     *
     * 2.  Problem Goal: You need to find the node at which the two lists intersect. If they do not
     *     intersect, you should return null. The intersection is defined by reference, not by value.
     *
     * 3.  Logic: A naive approach would be to use a hash set, but this requires O(N) space. A more
     *     clever O(1) space solution uses two pointers.
     *
     *     -   The insight is that if you have two pointers, `pA` and `pB`, traverse their respective
     *         lists, and when one reaches the end, you have it switch to the head of the *other* list.
     *     -   Let list A have length `lenA` and list B have length `lenB`. Let the common part have
     *         length `lenC`. The non-common parts are `lenA - lenC` and `lenB - lenC`.
     *     -   Pointer `pA` travels `(lenA - lenC) + (lenB - lenC)` and then `lenC` to the intersection.
     *     -   Pointer `pB` travels `(lenB - lenC) + (lenA - lenC)` and then `lenC` to the intersection.
     *     -   Both pointers travel the exact same distance (`lenA + lenB - lenC`) before they meet at the
     *         intersection point.
     *
     *     -   **Algorithm**:
     *         -   Initialize two pointers, `pA` to the head of list A and `pB` to the head of list B.
     *         -   Loop until `pA == pB`.
     *         -   In each iteration, advance `pA` and `pB` one step.
     *         -   If `pA` becomes null, redirect it to the head of list B.
     *         -   If `pB` becomes null, redirect it to the head of list A.
     *     -   The loop will terminate either when the pointers meet at the intersection, or when they both
     *       become null at the same time (after traversing both lists), indicating no intersection.
     *
     * =================================================================================
     * GENERIC TEMPLATE (Intersection of Two Linked Lists)
     * =================================================================================
     */

    /**
     * Finds the node at which the intersection of two singly linked lists begins.
     *
     * @param headA The head of the first list.
     * @param headB The head of the second list.
     * @return The intersecting node, or null if there is no intersection.
     */
    public ListNode getIntersectionNode(ListNode headA, ListNode headB) {
        if (headA == null || headB == null) {
            return null;
        }

        ListNode pA = headA;
        ListNode pB = headB;

        // --- Core Pattern Logic: Two-pointer traversal ---
        // The loop will run at most (lenA + lenB) times.
        while (pA != pB) {
            // When a pointer reaches the end of its list, redirect it to the other list's head.
            pA = (pA == null) ? headB : pA.next;
            pB = (pB == null) ? headA : pB.next;
        }
        // --------------------------------------------------

        // If the lists intersected, pA (or pB) is the intersection node.
        // If they did not, both pA and pB will be null at this point.
        return pA;
    }
}
