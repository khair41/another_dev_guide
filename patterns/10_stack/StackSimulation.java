package com.framework.patterns.stack;

import java.util.Stack;

/**
 * Pattern 67: Stack - Simulation / Backtracking Helper
 */
public class StackSimulation {

    /*
     * =================================================================================
     * CONDITIONS FOR APPLICABILITY
     * =================================================================================
     *
     * 1.  Input Data Structure: A sequence of operations or items.
     *
     * 2.  Problem Goal: You need to process a sequence where later items can cancel out or modify
     *     earlier items. The stack is used to keep track of the "current state" after processing
     *     the items seen so far.
     *     Examples: "Simplify Path", "Backspace String Compare" (can also be solved with two pointers).
     *
     * 3.  Logic: The stack holds the items that are still "active".
     *
     *     -   Iterate through the input sequence of operations/items.
     *     -   For each item, decide how it affects the current state (the stack).
     *     -   **If the item is a constructive operation** (e.g., a directory name, a character):
     *         Push it onto the stack.
     *     -   **If the item is a destructive or modifying operation** (e.g., a ".." to go up a directory,
     *       a backspace character):
     *         -   Check if the stack is not empty.
     *         -   If it's not, pop from the stack to "undo" the last constructive operation.
     *     -   After processing all items, the contents of the stack represent the final, simplified state.
     *     -   You may need to process the stack's contents to format the final output (e.g., join with '/').
     *
     * =================================================================================
     * GENERIC TEMPLATE (Simplify Path)
     * =================================================================================
     */

    /**
     * Simplifies a given absolute Unix-style path.
     *
     * @param path The absolute path string.
     * @return The simplified canonical path.
     */
    public String simplifyPath(String path) {
        Stack<String> stack = new Stack<>();
        // Split the path by '/' and filter out empty strings that result from multiple slashes.
        String[] components = path.split("/");

        // --- Core Pattern Logic ---
        for (String component : components) {
            if (component.equals(".") || component.isEmpty()) {
                // Ignore "." and empty components.
                continue;
            } else if (component.equals("..")) {
                // If we see "..", go up one level by popping from the stack if it's not empty.
                if (!stack.isEmpty()) {
                    stack.pop();
                }
            } else {
                // Otherwise, it's a directory name, so push it onto the stack.
                stack.push(component);
            }
        }
        // --------------------------

        // --- Reconstruct the path from the stack ---
        if (stack.isEmpty()) {
            return "/";
        }
        StringBuilder result = new StringBuilder();
        for (String dir : stack) {
            result.append("/").append(dir);
        }
        return result.toString();
        // -------------------------------------------
    }
}
