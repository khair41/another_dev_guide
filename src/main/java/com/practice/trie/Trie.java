package com.practice.trie;

import com.practice.tree.TreeNode;

import java.util.ArrayList;
import java.util.List;

public class Trie {
    public static final int ALPHABET_SIZE = 26;
    public char c;
    public boolean isWord = false;
    public Trie [] children = new Trie[ALPHABET_SIZE];

    public Trie(){}
    public Trie(char c){
        this.c = c;
    }

    public TrieNode root = new TrieNode();

    public static void main(String [] args){
        List<Integer> list = new ArrayList<>();
//        Trie trie = new Trie();
//        trie.insert(trie,"aba");
//        trie.insert(trie,"abaa");
//
//        System.out.println(trie.findString(trie,"aca"));
//        System.out.println(trie.findString(trie,"aba"));
//        System.out.println(trie.findString(trie,"abaa"));

        Trie trie = new Trie();
        trie.insert("aba");

        System.out.println(trie.findString("aba"));
        System.out.println(trie.findString("aca"));
        System.out.println(trie.findString("ab"));
    }

    public void insert(Trie trie, String s){
        if(s.length() > 0){
            char tempChar = s.charAt(0);
            int tempCharPos = tempChar - 97;

            Trie newTrie = new Trie(tempChar);
            if(s.length() == 1) newTrie.isWord = true;

            if(trie.children[tempCharPos] == null){
                trie.children[tempCharPos] = newTrie;
            } else {
                newTrie = trie.children[tempCharPos];
            }
            s = s.substring(1);
            newTrie.insert(newTrie, s);
        }
    }

    public boolean findString(Trie trie, String s){
        if(s.length() > 0){
            char tempChar = s.charAt(0);
            int tempCharPos = tempChar - 97;
            if(trie.children[tempCharPos] == null) return false;
            return trie.children[tempCharPos].findString(trie.children[tempCharPos], s.substring(1));
        }
        return trie.isWord;
    }

    public void insert(String s){
        TrieNode tempNode = this.root;
        for(char temp : s.toCharArray()){
            if(tempNode.children[temp - 97] == null)
                tempNode.children[temp - 97] = new TrieNode();
            tempNode = tempNode.children[temp - 97];
        }
        tempNode.isWord = true;
    }

    public boolean findString(String s){
        TrieNode tempNode = this.root;
        for(char temp : s.toCharArray()){
            if(tempNode.children[temp - 97] == null) return false;
            tempNode = tempNode.children[temp - 97];
        }
        return tempNode.isWord;
    }

    static class TrieNode {
        public boolean isWord = false;
        public TrieNode [] children = new TrieNode[ALPHABET_SIZE];
        public TrieNode(){}
    }
    //[1] -> [TrieNode(2,10), ]
    //[3] -> [TrieNode(2, 5), ]
}
