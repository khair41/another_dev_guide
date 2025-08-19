package com.practice.graph;

import java.util.*;

public class FindNearestClone {

    public static void main (String [] args){
        int g_nodes = 5;
        int [] g_from = {1,2,2,3};
        int [] g_to = {2,3,4,5};
        long [] ids = {1,2,3,1,3};
        int target = 1;
        System.out.println(findShortest(g_nodes, g_from, g_to, ids, target));

    }

    static int findShortest(int graphNodes, int[] graphFrom, int[] graphTo, long[] ids, int val) {
        Set<Integer> visited = new HashSet<>();
        List<Integer>[] adj = new ArrayList[graphNodes + 1];
        Queue<Integer> q = new ArrayDeque<>();

        for(int i = 0; i < adj.length; i++){
            adj[i] = new ArrayList<>();
        }

        int start = -1;

        for(int i = 0; i < graphFrom.length; i++){
            if(ids[graphFrom[i] - 1] == val) {
                start = graphFrom[i];
            }
            if(ids[graphTo[i] - 1] == val) {
                start = graphTo[i];
            }
            adj[graphFrom[i]].add(graphTo[i]);
            adj[graphTo[i]].add(graphFrom[i]);
        }

        if(start == -1) return -1;

        int distance = 0;
        int minDistance = Integer.MAX_VALUE;

        q.add(start);
        while(!q.isEmpty()){
            Queue<Integer> newq = new ArrayDeque<>();
            for(int i : q){
                visited.add(i);
                for(int t : adj[i]){
                    if(!visited.contains(t)){
                        newq.add(t);
                    }
                }
                visited.add(i);
                if(ids[i - 1] == val && i != start){
                    minDistance = distance == 0 ? minDistance : Math.min(distance, minDistance);
                    distance = 0;
                }
            }

            if(minDistance == 1) return 1;
            q = newq;
            distance++;
        }

        return minDistance > 0 ? minDistance : -1;
    }



}
