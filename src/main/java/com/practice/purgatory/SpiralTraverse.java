package com.practice.purgatory;

import java.util.*;

public class SpiralTraverse {
    int o;
    String s;
    public static void main(String [] args){
        int[][] input =
                new int[][] {
                        {1, 2, 3},
                        {12, 13, 4},
                        {11, 14, 5},
                        {10, 15, 6},
                        {9, 8, 7}
                };
        System.out.println(spiralTraverse(input));
    }

    public static List<Integer> spiralTraverse(int[][] array) {
        // Write your code here.
        Queue<Integer> q = new ArrayDeque<>();

        String res = "";
        int v1 = 0;
        int v2 = array.length - 1;
        int h1 = 0;
        int h2 = array[0].length - 1;


        ArrayList[] adj = new ArrayList[3];
        List<Integer> spiral = new ArrayList<>();

        while(v1 <= v2 && h1 <= h2){
            System.out.println(spiral);
            for(int i = h1; i <= h2; i++){
                spiral.add(array[v1][i]);
            }
            v1++;
            System.out.println(spiral);
            for(int i = v1; i <= v2; i++){
                spiral.add(array[i][h2]);
            }
            h2--;
            System.out.println(spiral);
            for(int i = h2; i >= h1; i--){
                spiral.add(array[v2][i]);
            }
            v2--;
            System.out.println(spiral);
            for(int i = v2; i >= v1 && v1!=v2; i--){
                spiral.add(array[i][h1]);
            }
            h1++;

        }
        return spiral;
    }

    @Override
    public boolean equals(Object o1) {
        if (this == o1) return true;
        if (o1 == null || getClass() != o1.getClass()) return false;

        SpiralTraverse that = (SpiralTraverse) o1;

        if (o != that.o) return false;
        return s != null ? s.equals(that.s) : that.s == null;
    }



    @Override
    public int hashCode() {
        int result = o;
        result = 31 * result + (s != null ? s.hashCode() : 0);
        return result;
    }
}
