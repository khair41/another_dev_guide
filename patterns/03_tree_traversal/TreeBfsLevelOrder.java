package com.framework.patterns.tree_traversal;

import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;
import java.util.Queue;

/**
 * Pattern 12: Tree BFS - Level Order Traversal
 */
public class TreeBfsLevelOrder {

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
     * 1.  Input Data Structure: A tree or a graph.
     *
     * 2.  Problem Goal: You need to traverse the tree in a level-by-level fashion. This is
     *     useful for problems where you need to find the shortest path from a root to another
     *     node, or for problems that involve processing nodes at the same "depth" or "level".
     *     Examples: "Binary Tree Level Order Traversal", "Find the minimum depth of a binary tree".
     *
     * 3.  Logic: Breadth-First Search (BFS) is implemented using a Queue.
     *
     *     - Start by adding the root node to a Queue.
     *     - Loop as long as the queue is not empty.
     *     - In each iteration, first determine the number of nodes currently in the queue (`levelSize`).
     *       This represents all the nodes at the current level.
     *     - Create a list to store the nodes for the current level.
     *     - Loop `levelSize` times, doing the following in each inner loop iteration:
     *         - Dequeue a node.
     *         - Add its value to the current level's list.
     *         - Enqueue its children (left and right) if they are not null.
     *     - After the inner loop finishes, the current level is fully processed. Add the level's list
     *       to the final result list.
     *
     * =================================================================================
     * GENERIC TEMPLATE (Binary Tree Level Order Traversal)
     * =================================================================================
     */

    /**
     * Performs a level order traversal on a binary tree.
     *
     * @param root The root of the binary tree.
     * @return A list of lists, where each inner list contains the nodes at one level.
     */
    public List<List<Integer>> levelOrder(TreeNode root) {
        List<List<Integer>> result = new ArrayList<>();
        if (root == null) {
            return result;
        }

        // --- Core Pattern Logic: Initialize the queue ---
        Queue<TreeNode> queue = new LinkedList<>();
        queue.offer(root);
        // ----------------------------------------------

        // --- Core Pattern Logic: Loop while there are nodes to process ---
        while (!queue.isEmpty()) {
            // --- Problem-Specific Logic: Process one level at a time ---
            int levelSize = queue.size();
            List<Integer> currentLevel = new ArrayList<>(levelSize);

            for (int i = 0; i < levelSize; i++) {
                TreeNode currentNode = queue.poll();
                currentLevel.add(currentNode.val);

                // Enqueue children for the next level.
                if (currentNode.left != null) {
                    queue.offer(currentNode.left);
                }
                if (currentNode.right != null) {
                    queue.offer(currentNode.right);
                }
            }
            result.add(currentLevel);
            // ---------------------------------------------------------
        }
        // ----------------------------------------------------------------

        return result;
    }
}
