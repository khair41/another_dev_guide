package com.framework.patterns.tree_traversal;

import java.util.Arrays;
import java.util.LinkedList;
import java.util.Queue;

/**
 * Pattern 17: Tree - Serialization and Deserialization
 */
public class TreeSerialization {

    // Definition for a binary tree node.
    static class TreeNode {
        int val;
        TreeNode left;
        TreeNode right;
        TreeNode(int x) { val = x; }
    }

    /*
     * =================================================================================
     * CONDITIONS FOR APPLICABILITY
     * =================================================================================
     *
     * 1.  Input Data Structure: A binary tree.
     *
     * 2.  Problem Goal: You need to convert a tree into a string or byte stream (serialize)
     *     and then be able to perfectly reconstruct the original tree from that stream (deserialize).
     *     This is useful for storing a tree in a file or sending it over a network.
     *
     * 3.  Logic: A tree traversal is used to encode the structure. Preorder traversal is a common choice.
     *
     *     -   **Serialization**: Perform a preorder traversal. For each node, append its value to a
     *         string builder. Crucially, if a node is null, you must append a special marker
     *         (e.g., "#" or "null") to signify the absence of a node. This is how the tree's
     *         structure is preserved.
     *
     *     -   **Deserialization**: Split the serialized string into a queue of values. Create a
     *         recursive helper function that builds the tree. In each call, dequeue one value.
     *         If it's the null marker, return null. Otherwise, create a new node with the value,
     *         then make a recursive call to build its left child, followed by another recursive
     *         call to build its right child. This mirrors the preorder traversal used for serialization.
     *
     * =================================================================================
     * GENERIC TEMPLATE (Serialization/Deserialization using Preorder Traversal)
     * =================================================================================
     */

    private static final String NULL_MARKER = "#";
    private static final String SEPARATOR = ",";

    // Encodes a tree to a single string.
    public String serialize(TreeNode root) {
        StringBuilder sb = new StringBuilder();
        serializeHelper(root, sb);
        return sb.toString();
    }

    private void serializeHelper(TreeNode node, StringBuilder sb) {
        if (node == null) {
            sb.append(NULL_MARKER).append(SEPARATOR);
        } else {
            sb.append(node.val).append(SEPARATOR);
            serializeHelper(node.left, sb);
            serializeHelper(node.right, sb);
        }
    }

    // Decodes your encoded data to tree.
    public TreeNode deserialize(String data) {
        if (data == null || data.isEmpty()) {
            return null;
        }
        Queue<String> nodes = new LinkedList<>(Arrays.asList(data.split(SEPARATOR)));
        return deserializeHelper(nodes);
    }

    private TreeNode deserializeHelper(Queue<String> nodes) {
        String val = nodes.poll();
        if (val == null || val.equals(NULL_MARKER)) {
            return null;
        }
        TreeNode node = new TreeNode(Integer.parseInt(val));
        node.left = deserializeHelper(nodes);
        node.right = deserializeHelper(nodes);
        return node;
    }
}
