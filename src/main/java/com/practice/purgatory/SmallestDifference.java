package com.practice.purgatory;

import java.util.Arrays;

public class SmallestDifference {
    public static void main(String [] args){
        int [] arrayOne = {-1, 5, 10, 20, 28, 3};
        int [] arrayTwo = {26, 134, 135, 15, 17};
        int [] r = smallestDifference(arrayOne, arrayTwo);
        System.out.println(r[0] + " " + r[1]) ;
    }

    public static int[] smallestDifference(int[] arrayOne, int[] arrayTwo) {
        // Write your code here.

        Arrays.sort(arrayOne);
        Arrays.sort(arrayTwo);

        int i = 0;
        int one_size = arrayOne.length;
        int j = 0;
        int two_size = arrayTwo.length;

        int currentDiff = Integer.MAX_VALUE;
        int [] res = null;

        while(i < one_size && j < two_size){
            int l = arrayOne[i];
            int r = arrayTwo[j];
            if(l == r) {
                return new int[] {l, r};
            } else if(l < r) {
                int diff = r - l;
                i++;
                if(diff < currentDiff){
                    res = new int[] {l,r};
                }
            } else {
                int diff = l - r;
                j++;
                if(diff < currentDiff){
                    res = new int[] {l,r};
                }
            }
        }
        return res;
    }
}
