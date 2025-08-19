package com.practice.graph;
import java.util.*;
import java.util.stream.Collectors;

public class ShortestReachInAGraph {

    public static void main(String[] args) {
        /* Enter your code here. Read input from STDIN. Print output to STDOUT. Your class should be named Solution. */
        System.out.println("6 6 -1");
        System.out.println("-1 6");

        Scanner scanner = new Scanner(System.in);
        int q = scanner.nextInt();
        for(int i = 1; i <= q; i++){
            String [] nodesData = scanner.next().split(" ");
            int n = Integer.parseInt(nodesData[0]);
            int e = Integer.parseInt(nodesData[1]);

            Graph graph = new Graph(n);

            for(int edge = 1; edge <= e; edge++){
                String [] edgeData = scanner.next().split(" ");
                int from = Integer.parseInt(edgeData[0]);
                int to = Integer.parseInt(edgeData[1]);
                graph.addEdge(from, to);
            }
            int start = scanner.nextInt();
            graph.start = start;
            graph.print();
        }
        scanner.close();

    }

    public static class Graph {
        public List<Integer> [] adj;
        public int start = -1;

        public Graph(int size){
            adj = new ArrayList[size + 1];
            for(int i = 1; i < adj.length; i++){
                adj[i] = new ArrayList<>();
            }
        }

        public void addEdge(int from, int to){
            adj[from].add(to);
            adj[to].add(from);
        }

        public void print(){
            System.out.println("Adjacency list: ");
            for(int i = 1; i < this.adj.length; i++){
                System.out.println("[" + i + "] : "
                        + String.join(" -> ",
                        this.adj[i].stream().map(v -> String.valueOf(v)).collect(Collectors.toList())));
            }
        }
    }
}