package com.practice.purgatory;

public class Fibonacci {
    public static void main(String [] args){
        System.out.println(fibonacci(0));
    }

    public static int fibonacci(int n){
        int f0 = 0;
        int f1 = 1;

        for(int i = 3; i <= n; i++){
            int t = f1;
            f1 += f0;
            f0 = t;
        }

        return f1;
    }
}
