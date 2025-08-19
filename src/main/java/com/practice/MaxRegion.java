// package com.practice;

// import javafx.util.Pair;

// import java.util.ArrayDeque;
// import java.util.Queue;

// public class MaxRegion {

//     public static void main (String [] args){
//         int [][] a = new int[5][5];
//         int [] v1 = {1, 0, 1, 1, 0};
//         int [] v2 = {1, 1, 0, 0, 1};
//         int [] v3 = {0, 1, 1, 1, 0};
//         int [] v4 = {0, 0, 0, 0, 1};
//         int [] v5 = {1, 1, 1, 0, 0};

//         a[0] = v1;
//         a[1] = v2;
//         a[2] = v3;
//         a[3] = v4;
//         a[4] = v5;

//         System.out.println(maxRegion(a));

//     }


//     static int maxRegion(int[][] grid) {
//         int [][] visited = new int [grid.length][grid[0].length];
//         int solution = 0;
//         Queue<Pair<Integer,Integer>> q = new ArrayDeque<>();
//         for(int i = 0; i < grid.length; i++){
//             for(int j = 0; j < grid[0].length; j++){
//                 if(grid[i][j] == 1 && visited[i][j] == 0){
//                     visited[i][j] = 1;

//                     q.add(new Pair<>(i,j));

//                     int currentSolution = 1;
//                     while(!q.isEmpty()){
//                         Pair<Integer,Integer> current = q.poll();
//                         //adj right
//                         if(current.getValue() + 1 <= grid[0].length - 1
//                                 && grid[current.getKey()][current.getValue() + 1] == 1
//                                 && visited[current.getKey()][current.getValue() + 1] == 0){
//                             // right
//                             q.add(new Pair<>(current.getKey(),current.getValue() + 1));
//                             visited[current.getKey()][current.getValue() + 1] = 1;
//                             currentSolution++;
//                         }

//                         //top right
//                         if(current.getKey() - 1 >= 0
//                                 && current.getValue() + 1 <= grid[0].length - 1
//                                 && grid[current.getKey() - 1][current.getValue() + 1] == 1
//                                 && visited[current.getKey() - 1][current.getValue() + 1] == 0){
//                             q.add(new Pair<>(current.getKey() - 1,current.getValue() + 1));
//                             visited[current.getKey() - 1][current.getValue() + 1] = 1;
//                             currentSolution++;
//                         }

//                         //bot right
//                         if(current.getValue() + 1 <= grid[0].length - 1
//                                 && current.getKey() + 1 <= grid.length - 1
//                                 && grid[current.getKey() + 1][current.getValue() + 1] == 1
//                                 && visited[current.getKey() + 1][current.getValue() + 1] == 0) {
//                             q.add(new Pair<>(current.getKey() + 1,current.getValue() + 1));
//                             visited[current.getKey() + 1][current.getValue() + 1] = 1;
//                             currentSolution++;
//                         }


//                         //adj left
//                         if(current.getValue() - 1 >= 0
//                                 && grid[current.getKey()][current.getValue() - 1] == 1
//                                 && visited[current.getKey()][current.getValue() - 1] == 0){
//                             //left
//                             q.add(new Pair<>(current.getKey(),current.getValue() - 1));
//                             visited[current.getKey()][current.getValue() - 1] = 1;
//                             currentSolution++;

//                         }

//                         //top left
//                         if(current.getValue() - 1 >= 0
//                                 &&current.getKey() - 1 >= 0
//                                 && grid[current.getKey() - 1][current.getValue() - 1] == 1
//                                 && visited[current.getKey() - 1][current.getValue() - 1] == 0
//                                 ) {
//                             q.add(new Pair<>(current.getKey() - 1,current.getValue() - 1));
//                             visited[current.getKey() - 1][current.getValue() - 1] = 1;
//                             currentSolution++;
//                         }


//                         //bot left
//                         if(current.getValue() - 1 >= 0
//                                 && current.getKey() + 1 <= grid.length - 1
//                                 && grid[current.getKey() + 1][current.getValue() - 1] == 1
//                                 && visited[current.getKey() + 1][current.getValue() - 1] == 0){
//                             q.add(new Pair<>(current.getKey() + 1,current.getValue() - 1));
//                             visited[current.getKey() + 1][current.getValue() - 1] = 1;
//                             currentSolution++;
//                         }

//                         //adj top
//                         if(current.getKey() - 1 >= 0
//                                 && grid[current.getKey() - 1][current.getValue()] == 1
//                                 && visited[current.getKey() - 1][current.getValue()] == 0) {
//                             q.add(new Pair<>(current.getKey() - 1,current.getValue()));
//                             visited[current.getKey() - 1][current.getValue()] = 1;
//                             currentSolution++;
//                         }


//                         //adj bot
//                         if(current.getKey() + 1 <= grid.length - 1
//                                 && grid[current.getKey() + 1][current.getValue()] == 1
//                                 && visited[current.getKey() + 1][current.getValue()] == 0) {
//                             q.add(new Pair<>(current.getKey() + 1,current.getValue()));
//                             visited[current.getKey() + 1][current.getValue()] = 1;
//                             currentSolution++;
//                         }

//                     }
//                     solution = Math.max(currentSolution, solution);
//                 }
//             }
//         }
//         return solution;
//     }
// }
