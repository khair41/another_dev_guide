package com.practice.purgatory;

import java.util.ArrayList;
import java.util.List;

public class ClosestValueInBST {
    public static void main(String [] args){
        BST tree = new BST(1);
        tree.left = new BST(2);
        tree.right = new BST(3);
        tree.left.left = new BST(4);
        tree.left.right = new BST(5);

        tree.right.left = new BST(6);
        tree.right.right = new BST(7);

//        System.out.println(findClosestValueInBst(tree, 12));
        System.out.println(branchSums(tree));
    }

    public static List<Integer> branchSums(BST root) {
        List<Integer> res = new ArrayList<Integer>();
        rec(root, res, 0);
        return res;
    }

    public static void rec(BST tree, List<Integer> res, int sum){
        if(tree == null) return;

        int currSum = sum + tree.value;
        if(tree.left == null && tree.right == null) {
            res.add(currSum);
            return;
        }

        rec(tree.left, res, currSum);
        rec(tree.right, res, currSum);

    }

    public static int findClosestValueInBst(BST tree, int target) {

        int diff = Integer.MAX_VALUE;
        int treeVal = tree.value;
        while(tree != null){
            int currDiff = Math.abs(tree.value - target);
            if(currDiff == 0) return tree.value;
            if(currDiff < diff){
                treeVal = tree.value;
                diff = currDiff;
            }
            if(target < tree.value) tree = tree.left;
            else tree = tree.right;
        }

        return treeVal;
    }

    static class BST {
        public int value;
        public BST left;
        public BST right;

        public BST(int value) {
            this.value = value;
        }
    }
}
