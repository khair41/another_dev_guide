package com.framework.patterns.two_pointers;

/**
 * Pattern 2: Two Pointers - Fast & Slow (Cycle Detection)
 */
public class TwoPointersFastSlow {

    // Definition for a singly-linked list node.
    static class ListNode {
        int val;
        ListNode next;
        ListNode(int x) {
            val = x;
            next = null;
        }
    }

    /*
     * =================================================================================
     * CONDITIONS FOR APPLICABILITY
     * =================================================================================
     *
     * 1.  Input Data Structure: The problem involves a sequence that can be traversed one step
     *     at a time, most commonly a Linked List. It can also be applied to arrays where
     *     array values represent indices to jump to.
     *
     * 2.  Problem Goal: You need to detect a cycle, find the middle of a list, or find the
     *     start of a cycle. The key idea is that the two pointers will eventually meet if
     *     a cycle exists, and their relative positions can reveal properties of the structure.
     *
     * 3.  Traversal Logic: The core of the pattern is having two pointers moving at different
     *     speeds. The "slow" pointer moves one step at a time, while the "fast" pointer
     *     moves two steps at a time.
     *
     *     - If `fast` reaches the end (`null`), there is no cycle.
     *     - If `slow == fast` at any point, a cycle is detected.
     *
     * =================================================================================
     * GENERIC TEMPLATE (Cycle Detection)
     * =================================================================================
     */

    /**
     * Detects if a cycle exists in a linked list.
     *
     * @param head The head of the linked list.
     * @return True if a cycle exists, false otherwise.
     */
    public boolean hasCycle(ListNode head) {
        if (head == null || head.next == null) {
            return false;
        }

        ListNode slow = head;
        ListNode fast = head;

        // The fast pointer moves two steps at a time, slow moves one.
        while (fast != null && fast.next != null) {
            slow = slow.next;
            fast = fast.next.next;

            // --- Core Pattern Logic ---
            // If the pointers meet, a cycle is confirmed.
            if (slow == fast) {
                // --- Problem-Specific Logic ---
                // For simple cycle detection, we just return true.
                // For finding the cycle start, a second phase of the algorithm would begin here.
                return true;
                // -----------------------------
            }
            // -------------------------
        }

        // If the loop finishes, the fast pointer reached the end, so no cycle exists.
        return false;
    }
}
