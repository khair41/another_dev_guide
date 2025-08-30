package com.practice.purgatory;

import java.util.ArrayList;
import java.util.List;

public class ProductSum {

    public static void main (String args []){
        List<Object> l = new ArrayList<>();
        l.add(new Integer(1));
        List<Object> sl = new ArrayList<>();
        sl.add(new Integer(2));
        l.add(sl);
        System.out.println(productSum(l));
        int [] t =  new int[] {123};

        int i = -1;
        int j = 15;
        System.out.println(j - i);
    }

    public static int productSum(List<Object> array) {
        return rec(array, 1);
    }

    public static int rec(List<Object> list, int depth){
        int sum = 0;
        for(Object i : list){
            if(i instanceof ArrayList){
                sum += rec((List<Object>) i, depth + 1);
            } else {
                sum += (Integer) i;
            }
        }
        return sum * depth;
    }
}