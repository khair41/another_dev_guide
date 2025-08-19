package com.practice.cracking;

public class IsUnique {
    public static void main(String [] args){
        System.out.println(isUnique("abcdefg"));
    }

    static boolean isUnique(String s){
        char [] letters = new char[26];
        for(char c : s.toCharArray()){
            int pos = c - 97;
            if(letters[pos] == 0) letters[pos] = 1;
            else return false;
        }
        return true;
    }
}
