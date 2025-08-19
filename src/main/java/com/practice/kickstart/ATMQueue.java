package com.practice.kickstart;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.*;

public class ATMQueue {
    public static void main (String [] args){

        Scanner in = new Scanner(new BufferedReader(new InputStreamReader(System.in)));
        int t = in.nextInt(); // Scanner has functions to read ints, longs, strings, chars, etc.
        for (int i = 1; i <= t; i++) {
            int n = in.nextInt();
            int w = in.nextInt();


            Map<Integer, Integer> counts = new HashMap<>();
            Queue<Integer> q = new ArrayDeque<>();

            int [] a = new int[n];
            for (int j = 1; j <= n; j++) {
                a[j - 1] = in.nextInt();
                counts.put(j - 1, a[j -1]);
                q.add(j - 1);
            }
            System.out.println("Case #" + i + ": " + res(a, w, counts, q));
        }
    }

    static String res(int [] a, int maxWithdrawl, Map<Integer, Integer> counts, Queue<Integer> q){

        List<Integer> res = new ArrayList<>();

        while(!q.isEmpty()){
            int temp = q.poll();
            if(counts.get(temp) <= maxWithdrawl){
                res.add(temp + 1);
            } else {
                int w = counts.get(temp);
                counts.put(temp, w - maxWithdrawl);
                q.add(temp);
            }
        }
        StringBuilder sb = new StringBuilder();
        for(int i : res){
            sb.append(i + " ");
        }
        return sb.toString().trim();
    }
}

