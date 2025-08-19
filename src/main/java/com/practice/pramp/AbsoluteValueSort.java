package com.practice.pramp;

import java.util.Arrays;
import java.util.Comparator;
import java.util.Map;
import java.util.TreeMap;
import java.util.stream.IntStream;
import java.util.stream.Stream;

public class AbsoluteValueSort {

    static boolean foo(){return true;}
    static int[] absSort(int[] arr) {
        boolean f = true;
        f = f || foo();
        Integer[] ar = IntStream.of(arr).boxed().toArray(Integer[]::new);
        Arrays.sort(ar, (i, j) -> {
            if(Math.abs(i) < Math.abs(j)) return -1;
            else if(Math.abs(i) > Math.abs(j)) return 1;
            else return i - j;
//            i = Math.abs(i); j = Math.abs(j);
//            if(i == j) return -1;
//            else return i - j;
        });
        return Arrays.stream(ar).mapToInt(Integer::intValue).toArray();
//        TreeMap<Integer, int[]> map = new TreeMap();
//        int [] r = new int[arr.length];
//        for(int i : arr){
//            boolean isNegative = i < 0 ? true : false;
//            i = Math.abs(i);
//            int[] counts = new int[2];
//            if(map.containsKey(i)){
//                counts = map.get(i);
//
//            } else {
//                counts = new int[]{0,0};
//
//            }
//            if(isNegative) counts[0]++;
//            else counts[1]++;
//            map.put(i, counts);
//        }
//
//        int count = 0;
//        for(Map.Entry e : map.entrySet()){
//            int num = (int) e.getKey();
//            int [] counts = (int[]) e.getValue();
//            for(int n = 0; n < counts[0]; n++){
//                r[count++] = num * -1;
//            }
//            for(int p = 0; p < counts[1]; p++){
//                r[count++] = num;
//            }
//
//        }
//
//        return r;
    }


    public static void main(String[] args) {
        System.out.println(Arrays.toString(absSort(new int[]{-4,9,-1,1,-1,2,-8,-6,3})));
    }

}
