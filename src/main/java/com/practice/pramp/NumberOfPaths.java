package com.practice.pramp;

//output: 5 # since there are five possibilities:
//        # “EEENNN”, “EENENN”, “ENEENN”, “ENENEN”, “EENNEN”,
//        # where the 'E' character stands for moving one step
//        # East, and the 'N' character stands for moving one step
//        # North (so, for instance, the path sequence “EEENNN”
//        # stands for the following steps that the car took:
//        # East, East, East, North, North, North)

//i >= j


import java.util.HashMap;
import java.util.Map;

public class NumberOfPaths {

    public static void main(String[] args) {

        int [][] chars = new int[2][2];
        System.out.println(chars[0][1]);
        System.exit(0);
        int n = 17;
        Math.max(n, n *n );
        System.out.println(rec(0, 0, n));
    }

    static int rec(int i, int j, int n){
        if(i < j || i > n - 1 || j > n - 1) return 0;
        if(i == n - 1 && j == n - 1) return 1;
        else return rec(i + 1, j, n) + rec(i, j + 1, n);
    }
}
