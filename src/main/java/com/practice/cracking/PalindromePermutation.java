package com.practice.cracking;

/*
* Brute force:
* Create a permutation of the string then validate if its a palindrome
* Complexity: O(n! * n/2)
* Palindrome check with 2 pointer from right and left to get n/2
*
* Next approach (n lg n + n)
* Sort the string aaabbbccccddd
* aaa -> 3a % 2 = 1
* bbb -> 3b % 2 = 1
* cccc -> 4c % 2 = 0
*
* Final approach O(2 n)
* boolean arr[char] == 1 -> 0
*
*
* */


import java.util.HashSet;
import java.util.Set;

public class PalindromePermutation {


    public static void main(String[] args) {
        Set<Character> chars = new HashSet<>();
        String s = "Tac Coa".toLowerCase();

        for(char c : s.toCharArray()){
            if(c != ' '){
                if(chars.contains(c)) chars.remove(c);
                else chars.add(c);
            }
        }

        if(chars.size() <= 1) System.out.println(true);
        else System.out.println(false);
    }


}
