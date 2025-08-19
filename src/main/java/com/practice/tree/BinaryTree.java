package com.practice.tree;

public class BinaryTree extends TreeNode {

    public static void main(String [] args){
        BinaryTree bt = new BinaryTree(4);
        bt.insert(3);
        bt.insert(5);
        bt.insert(6);
        bt.insert(7);


        bt.print();
        System.out.println(bt.getHeight());
        System.out.println(bt.getNonRecHeight());
        System.out.println(bt.getNonRecHeight2());
    }

    public BinaryTree(int data) {
        super(data);
    }

    //handle duplicates
    public void insert(int data){
        TreeNode child = this;
        TreeNode temp = child;
        TreeNode newNode = new TreeNode(data);

        while(child != null){
            if(child != null) temp = child;
            if(data < child.data){
                child = child.left;
            } else {
                child = child.right;
            }
        }
        if(data < temp.data) {
            temp.left = newNode;
        } else {
            temp.right = newNode;
        }
    }


}
