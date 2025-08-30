package com.practice.purgatory;

import java.util.*;

public class AreAnagrams {
    private static final int NO_OF_CHARS = 26;

    public void longestPalindrome(String s) {
        System.out.println(Integer.MAX_VALUE < Double.MAX_VALUE);
    }

    static boolean isPalindrome(String s, int l, int r){
        while(l <= r){
            if(s.charAt(l) != s.charAt(r)) return false;
            l++; r--;
        }
        return true;
    }
    public static void main(String [] args){

//        int [] t = new int[] {1,2};
//        System.out.println(Arrays.toString(twoNumberSum(new int []{11,-1,6}, 10)));

//        System.out.println(areAnagrams("abz", "abz"));
//        "".hashCode();
    }

    static int []test (){
        return new int[] {1,2};
    }

    public static boolean areAnagrams(String s1, String s2){
        if(s1.length() != s2.length()) return false;

        char [] chars = new char[NO_OF_CHARS];

        char [] ch1 = s1.toCharArray();
        char [] ch2 = s2.toCharArray();

        for(int i = 0; i < ch1.length && i < ch2.length; i++){
            chars[ch1[i] - 97]++;
            chars[ch2[i] - 97]--;
        }

        for(int i = 0; i < chars.length; i++){
            if(chars[i] != 0) return false;
        }

        return true;
    }

    public static int[] twoNumberSum(int[] array, int targetSum) {
        Map<Integer, Integer> complements = new HashMap<>();
        for(int i = 0; i < array.length; i++){
            int complement = targetSum - array[i];
            if(complements.containsKey(array[i])){
                return new int[] {array[i], complement};
            } else {
                complements.put(complement, i);
            }
        }
        // Write your code here.
        return new int [] {};
    }
}
