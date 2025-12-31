package com.problems.interview;

import java.util.*;

public class AverageLastNNumbers {


    public static void main(String[] args) {
        s.addAll(Arrays.asList(1, 2, 0, -1, 6, 7, 8, 2, -9, 10, 11, -4, 5, 1, 3));

        // System.out.println(getAverage()); //3.36

        for (int i : s) {
            System.out.println(getAverage2(i));
        }
    }

    static Stack<Integer> s = new Stack<>();
    static int N = 10;

    static double getAverage() {
        if (s.size() < N)
            return -1;
        int count = 0;

        List<Integer> subset = new ArrayList<>();
        while (count < N) {
            subset.add(s.pop());
            count++;
        }
        Collections.sort(subset);
        int tenPercent = (subset.size() * 10) / 100;
        subset = subset.subList(tenPercent, subset.size() - tenPercent);
        return subset.stream().mapToDouble(Integer::doubleValue).average().orElse(-1);

    }

    static TreeMap<Integer, Integer> min = new TreeMap<>();
    static TreeMap<Integer, Integer> mid = new TreeMap<>();
    static TreeMap<Integer, Integer> max = new TreeMap<>();

    static private Queue<Integer> inserted = new LinkedList();

    static double getAverage2(int num) {
        inserted.add(num);
        // calculate on which side the num will be inserted
        mid.compute(num, (k, v) -> v == null ? 1 : v + 1);
        int tenPercent = (inserted.size() * 10) / 100;

        if (tenPercent >= 1 && num >= mid.firstKey() && num <= mid.lastKey()) {
            int left = mid.headMap(num).size();
            int right = mid.tailMap(num).size();

            // element will be inserted in right side therefore
            // to maintain balance we should remove an element from left
            if (left > right) {
                int remove = mid.firstKey();
                mid.computeIfPresent(remove, (k, v) -> v == 1 ? mid.remove(k) : v - 1);
                min.compute(remove, (k, v) -> v == null ? 1 : v + 1);
            } else {
                int remove = mid.lastKey();
                mid.computeIfPresent(remove, (k, v) -> v == 1 ? mid.remove(k) : v - 1);
                max.compute(remove, (k, v) -> v == null ? 1 : v + 1);
            }

            if (num < mid.firstKey())
                min.compute(num, (k, v) -> v == null ? 1 : v + 1);
            else if (num > mid.lastKey())
                max.compute(num, (k, v) -> v == null ? 1 : v + 1);

            if (inserted.size() > N) {
                int remove = inserted.poll();
                min.computeIfPresent(remove, (k, v) -> v == 1 ? min.remove(k) : v - 1);
                if (mid.containsKey(remove)) {
                    left = mid.headMap(remove).size();
                    right = mid.tailMap(remove).size();
                    if (left > right) {
                        int rightMin = max.firstKey();
                        max.computeIfPresent(rightMin,
                                (k, v) -> v == 1 ? max.remove(rightMin) : v - 1);
                        mid.compute(rightMin, (k, v) -> v == null ? 1 : v + 1);
                    } else {
                        int leftMax = min.lastKey();
                        min.computeIfPresent(leftMax,
                                (k, v) -> v == 1 ? min.remove(leftMax) : v - 1);
                        mid.compute(leftMax, (k, v) -> v == null ? 1 : v + 1);
                    }
                    mid.computeIfPresent(remove, (k, v) -> v == 1 ? mid.remove(k) : v - 1);
                }
                max.computeIfPresent(remove, (k, v) -> v == 1 ? max.remove(k) : v - 1);
            }
        }
        return mid.headMap(num, true).entrySet().stream()
                .mapToDouble(e -> e.getKey() * e.getValue()).sum() / N;
    }


}
