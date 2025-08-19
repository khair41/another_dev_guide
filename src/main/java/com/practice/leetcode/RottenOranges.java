package com.practice.leetcode;

import java.util.*;

public class RottenOranges {

    public static void main(String[] args) {
        int [][] grid = {{2,1,1},{1,1,0},{0,1,1}};
        System.out.println(orangesRotting(grid));
        Queue<Integer> q = new LinkedList<>();
        Stack<Integer> s = new Stack<>();


    }

    public static int orangesRotting(int[][] grid) {

        Queue<int[]> q = new ArrayDeque<>();
        int fresh = 0;
        for(int i = 0; i < grid.length; i++){
            for (int j = 0; j < grid[0].length; j++){
                if(grid[i][j] == 2){
                    q.add(new int []{i,j});
                    grid[i][j] = -1;
                } else if(grid[i][j] == 1) {
                    fresh++;
                }
            }
        }


        if(q.isEmpty()) return -1;

        int allOranges = fresh + q.size();
        int rotten = q.size();
        int minutes = 0;

        int orangesReached = 0;
        while(!q.isEmpty()){
            Queue<int[]> newq = new ArrayDeque<>();
            for(int [] coord : q){
                List<int[]> adj = new ArrayList<>();
                System.out.println("sending: " + coord);
//                getAdj(grid, coord.getKey(), coord.getValue(), adj, false);
                newq.addAll(adj);
            }
            q = newq;
            orangesReached += newq.size();
            minutes++;
        }
        System.out.println(orangesReached);
        System.out.println(rotten);
        System.out.println(fresh);
        return orangesReached == allOranges - rotten ? minutes : -1;

    }


}
