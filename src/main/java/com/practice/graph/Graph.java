package com.practice.graph;
import java.util.*;
import java.util.stream.Collectors;

public class Graph {

        private Set<Integer>[] adj;
        private int [][] matrix;

        public Graph(int size){


            this.adj = new HashSet[size + 1];
            for(int i = 1; i < this.adj.length; i++){
                adj[i] = new HashSet<>();
            }
            this.matrix = new int[size + 1][size + 1];
        }

        public void addEdgeDirected(int from, int to){
            this.adj[from].add(to);
            this.matrix[from][to] = 1;
        }

        public void addEdgeUndirected(int from, int to){
            this.addEdgeDirected(from, to);
            this.addEdgeDirected(to, from);
            // new int[]{1,2};
        }

        public void print(){
            System.out.println("Adjacency list: ");
            for(int i = 1; i < this.adj.length; i++){
                System.out.println("[" + i + "] : "
                        + String.join(" -> ",
                        this.adj[i].stream().map(v -> String.valueOf(v)).collect(Collectors.toList())));
            }
            System.out.println();
            System.out.println("Adjacency matrix");
            for(int i = 1; i < this.matrix.length; i++)
                System.out.print("   " + i + "");
            System.out.println();
            for(int i = 1; i < this.matrix.length; i++){
                System.out.print(i + " ");
                for(int j = 1; j < this.matrix[0].length; j++){
                    System.out.print(" [" +this.matrix[i][j] + "]");
                }
                System.out.println();
            }

        }

        static class MyQueue<Integer> {
            public void enqueue(int n){

            }

            public void dequeue(){

            }

            public int peek(){
                return -1;
            }


        }

    public static void main(String [] args){
            MyQueue<Integer> queue = new MyQueue<>();
        Stack<Integer> s1 = new Stack<>();

        System.out.println(s1.isEmpty());
//        Graph graph = new Graph(4);
//        graph.addEdgeUndirected(1,2);
//        graph.addEdgeUndirected(1,3);
//        graph.addEdgeDirected(1,2);
//        graph.addEdgeDirected(1,3);
//        graph.print();
//        HashMap<Integer, Set<Integer>> adj1 = new HashMap<>();

//        System.out.println(adj1);
    }

}
