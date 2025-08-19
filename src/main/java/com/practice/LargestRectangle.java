package com.practice;

public class LargestRectangle {

    static long largestRectangle(int[] h) {
        int max = -1;

        for(int i = 0; i < h.length - 1; i++){
            for(int j = i + 1; j < h.length; j++){
                if(h[i] <= h[j]){
                    max = Math.max(max, h[i] * (j + 1 - i));
                } else {
                    for(int k = j - 1, l = 1; k >= 0 && h[k] >= h[j]; k--, l++){
                        if(h[j] <= h[k]){
                            max = Math.max(max, h[j] * (l+1));
                        } else {
                            break;
                        }
                    }
                }

            }
        }

        return max;

    }

    public static void main(String [] args){
        System.out.println(largestRectangle(new int [] {11,11,10,10,10}));
    }
}
