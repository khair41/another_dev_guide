package com.problems.graphs.problems;

import java.util.*;

public class NetworkDelayTime {

    public static void main(String[] args) {



    }

    public int networkDelayTime(int[][] times, int n, int k) {
        Map<Integer, List<int[]>> edges = new HashMap<>();
        for(int[] time : times){
            edges.computeIfAbsent(time[0],
                    key -> new ArrayList<>()).add(new int[]{time[1], time[2]});
        }

        // This is also correct but more verbose than necessary.
        PriorityQueue<int[]> minHeap = new PriorityQueue<>((e1, e2) -> e1[0] - e2[0]);

        minHeap.offer(new int[]{0, k});

        Set<Integer> visited = new HashSet<>();

        int t = 0;
        while(!minHeap.isEmpty()){
            int[] curr = minHeap.poll();

            int w1 = curr[0];
            int n1 = curr[1];

            if(visited.contains(n1)) continue;

            visited.add(n1);
            t = w1;

            if(edges.containsKey(n1)){
                for(int[] neig : edges.get(n1)){
                    int n2 = neig[0];
                    int w2 = neig[1];
                    if(!visited.contains(n2)){
                        minHeap.offer(new int[] {w1 + w2, n2});
                    }
                }
            }
        }

        return visited.size() == n ? t : -1;
    }

}
