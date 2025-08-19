package com.practice.cracking;

//Given two strings, write a method to decide if one is a permutation of the other
/*
* n = abc
* m = asdfgabcasdfg
* Brute Force = O(n! * m/n)
*
* hash(n) = 10
* hash(m(0,n)) = 7
* hash(m(1,n)) = 15
* if(hash(n) == hash(m(i,n)) && n == m(i,n)) perm++
*
* We should understand if the permutation comparison is case sensitive.
* That is: is God a permutation of dog?
* Additionally, we should ask if whitespace is significant.
* We will assume for this problem that the comparison is case sensitive and whitespace is significant.
* So, "god " is different from "dog".
*
* */
public class CheckPermutation {
    public static void main(String[] args) {

        String n = "aba";
        String m = "abalaabdfbaawjskjaba";

        int perm = 0;

        int hashN = hashFromCharsInString(n);
        int hashM = 0;
        for(int i = 0, j = n.length(); j <= m.length() && i < m.length(); i++, j++){
            if(hashM == 0){
                hashM = hashFromCharsInString(m.substring(i, j));
            } else {
                hashM -= m.charAt(i - 1) + "".hashCode();
                hashM += m.charAt(j - 1) + "".hashCode();
            }
            if(hashM == hashN && isWindowEqualToString(i,j,n,m)) perm++;
        }
        System.out.println(perm);
    }

    static boolean isWindowEqualToString(int left, int right, String n, String m){
       int [] chars = new int [26];
       for(int i = left, j = 0; i < right; i++, j++){
           chars[n.charAt(j) - 97]++;
           chars[m.charAt(i) - 97]--;
       }
       for(int i = 0; i < chars.length; i++){
           if(chars[i] != 0) return false;
       }
       return true;
    }

    static int hashFromCharsInString(String s){
        int h = 0;
        for(int k = 0; k < s.length(); k++){
            h += s.charAt(k) + "".hashCode();
        }
        return h;
    }
}
