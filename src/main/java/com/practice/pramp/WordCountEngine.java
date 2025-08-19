package com.practice.pramp;

//import com.sun.source.tree.Tree;
/*
*
* [1,2,3,5,6,8]
* [1,3,5,6,8]
*
*
*
*
* */
import java.util.*;
import java.util.stream.Collectors;
import java.util.stream.Stream;

public class WordCountEngine {
    public static void main(String[] args) {
        wordCountEngine("Practice makes perfect. you'll only get Perfect by practice. just practice! perfect");



    }

    public static String[][] wordCountEngine(String document) {
        document = document.replaceAll("[^a-zA-Z ]", "").toLowerCase();
        String [] words = document.split(" ");
        System.out.println(Arrays.toString(words));
        LinkedHashMap<String, List<Integer>> map = new LinkedHashMap<>();




        for(int i = 0; i < words.length; i++){
            map.computeIfAbsent(words[i], s -> new ArrayList<>()).add(i);
        }

        String s = Stream.of(words)
                .map(w -> w.toUpperCase())
//                .filter(w -> w.startsWith("P"))
                .sorted()
        .collect(Collectors.joining("|"));
        System.out.println(s);

        map = map.entrySet().stream().sorted((stringListEntry, t1) -> {
            List<Integer> l1 = stringListEntry.getValue();
            List<Integer> l2 = t1.getValue();
            if(l1.size() == l2.size()){
                return l1.get(0) - l2.get(0);
            }
            else return l2.size() - l1.size();
        }).collect(Collectors.toMap(
                Map.Entry::getKey,
                Map.Entry::getValue,
                (oldV, newV) -> oldV, LinkedHashMap::new
        ));
        System.out.println(map);

//        ValueComparator vc = new ValueComparator(map);
//        System.out.println(map);
//
//        TreeMap<String, List<Integer>> sortedMap = new TreeMap<>(vc);
//        sortedMap.putAll(map);
//        System.out.println(sortedMap);

        return null;


    }


    static class ValueComparator implements Comparator<String>{
        HashMap<String, List<Integer>> map;
        public ValueComparator(HashMap<String, List<Integer>> map){
            this.map = map;
        }
        @Override
        public int compare(String s1, String s2) {
            if(map.get(s1).size() == map.get(s2).size()){
                return map.get(s1).get(0) - map.get(s2).get(0);
            }
            else return map.get(s2).size() - map.get(s1).size();
        }
    }

}
