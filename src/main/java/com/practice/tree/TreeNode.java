package com.practice.tree;

import java.util.*;

public class TreeNode {
    public int data;
    public TreeNode left;
    public TreeNode right;

    public TreeNode(int data){
        this.data = data;
    }

    public TreeNode(){}

    public void print(){
        System.out.println(recPrint(this));
    }

    private String recPrint(TreeNode node){
        if(node == null) return "null";
        String l = recPrint(node.left);
        String r = recPrint(node.right);
        if(l.equals("null") && r.equals("null")) return Integer.toString(node.data);
        else return node.data + " ( " + recPrint(node.left) +" , " + recPrint(node.right)+ " ) ";
    }

    public int getHeight(){
        return recHeight(this);
    }

    public int getNonRecHeight2(){
        List<TreeNode> lvlNodes = new ArrayList<TreeNode>();
        lvlNodes.add(this);
        int count = -1;
        while (lvlNodes.size() > 0){
            List<TreeNode> tempLvlNodes = new ArrayList<TreeNode>();
            for(TreeNode temp : lvlNodes){
                if(temp.left != null) tempLvlNodes.add(temp.left);
                if(temp.right != null) tempLvlNodes.add(temp.right);
            }
            lvlNodes = tempLvlNodes;
            count++;
        }
        return count;
    }

    public int getNonRecHeight(){
        /**
         *  Get all nodes per level
         *          4
         *        3     5
         *                  6
         *                      7
         *                          8
         *
         *      4 -> 3, 5
         *      3 ->
         *      5 -> 6
         *      6 -> 7
         *      7 -> 8
         *      8 ->
         */

        Map<TreeNode, Queue<TreeNode>> adj = new HashMap<>();

        Queue<TreeNode> queue = new LinkedList<>();
        queue.add(this);
        while(queue.size() > 0){
            TreeNode tempQueueNode = queue.poll();
            Queue<TreeNode> tempChildList = new LinkedList<>();
            if(tempQueueNode.left != null) {
                queue.add(tempQueueNode.left);
                tempChildList.add(tempQueueNode.left);

            }
            if(tempQueueNode.right != null) {
                queue.add(tempQueueNode.right);
                tempChildList.add(tempQueueNode.right);
            }
            adj.put(tempQueueNode, tempChildList);
        }

        int count = 0;
        for(TreeNode rootChild : adj.get(this)){
            TreeNode temp = rootChild;
            int currentCount = 0;
            while(temp != null){
                currentCount++;
                Queue<TreeNode> childAdjacentQueue = adj.get(temp);
                temp = childAdjacentQueue.poll();
            }
            count = Math.max(count, currentCount);
        }
        return count;
    }

    private int recHeight(TreeNode node){
        if(node == null) return -1;
        int l = recHeight(node.left);
        int r = recHeight(node.right);
        return Math.max(++l, ++r);
    }



}
