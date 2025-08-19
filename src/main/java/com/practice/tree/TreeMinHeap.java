package com.practice.tree;

import java.util.*;

public class TreeMinHeap extends TreeNode {

    public static void main(String [] args){
        TreeMinHeap mh = new TreeMinHeap(4);
        mh.add(5);
        mh.print();
        mh.add(6);
        mh.print();
        mh.add(7);
        mh.print();
        mh.add(8);
        mh.print();
    }

    public TreeMinHeap(){}

    public TreeMinHeap(int val){
        super(val);
    }
    void add(int val) {
        if(this == null){
            this.data = val;
        } else {
            TreeNode node = findLastParentWithEmptyLeaf(this);
            TreeNode newNode = new TreeNode(val);
            if(node.left == null) node.left = newNode;
            else node.right = newNode;
        }
    }
    //return last parent node with empty leaf
    /*
    * Populate the queue with the root node
    * Then create a new queue with the child nodes
    * The new queue should be populated by adding elements from right to left
    * While reading the queue, if the current element has a left node but not a right node,
    * that should be our answer because the rule is always insert the new child node from left to right
    * so the child nodes of the right most element of a level should be empty
    * While reading the queue, we can find elements with null children that could be the possible answer,
    * each step this variable will be overwritten giving us the left most incomplete element
    * In the case all the levels are complete, the while loop will exit and the result node will be
    * the left most element (last element of the queue since we were adding elements from right to left)
    * */
    private TreeNode findLastParentWithEmptyLeaf(TreeNode node){
        Queue<TreeNode> queue = new ArrayDeque<>();
        queue.add(node);
        TreeNode res = null;
        int lvl = 0;
        while(!queue.isEmpty()){
            lvl++;
            Queue<TreeNode> newQueue = new ArrayDeque<>();
            for(TreeNode tempNode : queue){
                if(tempNode.left == null && tempNode.right != null) return tempNode;
                if(tempNode.right!= null && tempNode.left != null){
                    newQueue.add(tempNode.right);
                    newQueue.add(tempNode.left);
                } else {
                    res = tempNode;
                }
            }
            if(queue.size() == Math.pow(2, lvl - 1) && newQueue.size() < Math.pow(2, lvl)){
                return res;
            }
            queue = newQueue;
        }
        return res;
    }


}