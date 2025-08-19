package com.practice.leetcode;

import java.util.Arrays;
import java.util.Iterator;
import java.util.LinkedHashSet;
import java.util.Set;

public class Interview {
    public static void main(String[] args) {
        String [] wordlist = {"KiTe","kite","hare","Hare"};
        String [] queries = {"HARE","Hear","hear","keti","keet","keto"};
        Solution solution = new Solution();
        System.out.println(solution.spellchecker(wordlist,queries));
    }
    /*
Trie lvl 0
[b,c,d... ,*] '*' if it contains

char from query can be
    same case
        append to same case answer
    no same case
        append to no same case answer
    same vowel
        same case
            append to same case answer
        no same case
            append to no same case answer
    no same vowel
*/


    static class Solution {
        static int ALP_SIZE = 128;
        static Set<Character> vowels = new LinkedHashSet<>();

        public String[] spellchecker(String[] wordlist, String[] queries) {

            vowels.add('A');
            vowels.add('E');
            vowels.add('I');
            vowels.add('O');
            vowels.add('U');

            vowels.add('a');
            vowels.add('e');
            vowels.add('i');
            vowels.add('o');
            vowels.add('u');

            TrieNode root = new TrieNode();

            //First insert all words as they are in the list
            for(String word : wordlist){
                TrieNode current = root;
                for(char c : word.toCharArray()){
                    if(current.children[c] == null) current.children[c] = new TrieNode();
                    current = current.children[c];
                }
                current.isWord = true;
            }
            int count = 0;
            String [] result = new String[queries.length];
            //Read and retreive the word from the trie;
            for(String query : queries){
                TrieNode current = root;
                StringBuilder sb = new StringBuilder();
                for(char c : query.toCharArray()){
                    if(vowels.contains(c)){
                        Iterator<Character> it = vowels.iterator();
                        boolean found = false;
                        while(it.hasNext()){
                            char v = it.next();
                            if(current.children[v] != null){
                                found = true;
                                current = current.children[v];
                                sb.append(v);
                                break;
                            }
                        }
                        if(!found) {
                            sb = new StringBuilder();
                            break;
                        }
                    } else if(current.children[c] != null) {
                        current = current.children[c];
                        sb.append(c);
                    } else if(Character.isUpperCase(c) && current.children[c] == null){
                        if(current.children[Character.toLowerCase(c)] == null){
                            sb = new StringBuilder();
                            break;
                        } else {
                            sb.append(Character.toLowerCase(c));
                            current = current.children[Character.toLowerCase(c)];
                        }
                    } else if(Character.isLowerCase(c) && current.children[c] == null){
                        if(current.children[Character.toUpperCase(c)] == null){
                            sb = new StringBuilder();
                            break;
                        } else {
                            sb.append(Character.toUpperCase(c));
                            current = current.children[Character.toUpperCase(c)];
                        }
                    }
                }
                if(!current.isWord) sb = new StringBuilder();
                result[count++] = sb.toString();
            }
            return result;
        }

        static class TrieNode {
            TrieNode [] children = new TrieNode [ALP_SIZE];
            boolean isWord = false;
            TrieNode() {}
            @Override
            public String toString(){
                return Arrays.toString(this.children);
            }
        }
    }
}
