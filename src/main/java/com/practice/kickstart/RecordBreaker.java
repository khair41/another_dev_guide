package com.practice.kickstart;

import java.util.*;
import java.io.*;
public class RecordBreaker {
    public static void main(String[] args) {
        Scanner in = new Scanner(new BufferedReader(new InputStreamReader(System.in)));
        int t = in.nextInt(); // Scanner has functions to read ints, longs, strings, chars, etc.
        for (int i = 1; i <= t; i++) {
            int n = in.nextInt();
            int v[] = new int[n];
            for (int j = 1; j <= n; j++) {
                v[j - 1] = in.nextInt();
            }

            System.out.println("Case #" + i + ": " + recordBreaker(v));
        }
    }

    public static int recordBreaker(int [] a){
        if(a.length <= 1) return 0;

        int maxPrev = a[0];
        int rb = 0;
        if(a[0] > a[1]) rb++;
        for(int i = 1; i < a.length - 1; i++){
            if(a[i] > maxPrev && a[i] > a[i+1]){
                rb++;
                maxPrev = a[i];
            }
        }
        if(a[a.length - 1] > maxPrev) rb++;

        return rb;
    }
}
