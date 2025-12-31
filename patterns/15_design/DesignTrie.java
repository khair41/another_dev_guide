package com.framework.patterns.design;

/**
 * Pattern 94: Tries (Prefix Tree)
 */
public class DesignTrie {

    /*
     * =================================================================================
     * CONDITIONS FOR APPLICABILITY
     * =================================================================================
     *
     * 1.  Input Data Structure: A collection of strings.
     *
     * 2.  Problem Goal: You need to perform efficient prefix-based searches. This includes operations like:
     *     -   `insert`: Add a word to the dictionary.
     *     -   `search`: Check if a full word exists in the dictionary.
     *     -   `startsWith`: Check if there is any word in the dictionary that starts with a given prefix.
     *
     * 3.  Logic: A Trie is a tree where each node represents a character. A path from the root to a node
     *     represents a prefix. Each node typically contains:
     *     -   An array or map of child nodes (e.g., an array of size 26 for lowercase English letters).
     *     -   A boolean flag (`isEndOfWord`) to indicate if the path to this node forms a complete word.
     *
     *     -   **`insert(word)` operation**:
     *         1.  Start from the root.
     *         2.  For each character in the word, traverse down the tree. If a child node for a character
     *             does not exist, create it.
     *         3.  After processing the last character, mark the final node's `isEndOfWord` flag as `true`.
     *
     *     -   **`search(word)` operation**:
     *         1.  Start from the root.
     *         2.  Traverse the tree according to the characters in the word. If at any point a child node
     *             does not exist, the word is not in the trie. Return `false`.
     *         3.  After processing the last character, the word exists only if the final node's `isEndOfWord`
     *             flag is `true`.
     *
     *     -   **`startsWith(prefix)` operation**:
     *         1.  This is similar to `search`, but you only need to traverse the tree for the characters
     *             in the prefix. If you can successfully traverse the entire prefix, it means a word with
     *             this prefix exists. Return `true`.
     *
     * =================================================================================
     * GENERIC TEMPLATE (Implement Trie)
     * =================================================================================
     */

    // Helper class for the Trie nodes
    static class TrieNode {
        private final TrieNode[] children;
        private boolean isEndOfWord;

        public TrieNode() {
            children = new TrieNode[26]; // For 'a' through 'z'
            isEndOfWord = false;
        }

        public boolean containsKey(char ch) {
            return children[ch - 'a'] != null;
        }
        public TrieNode get(char ch) {
            return children[ch - 'a'];
        }
        public void put(char ch, TrieNode node) {
            children[ch - 'a'] = node;
        }
        public void setEnd() {
            isEndOfWord = true;
        }
        public boolean isEnd() {
            return isEndOfWord;
        }
    }

    private final TrieNode root;

    /** Initializes the trie object. */
    public DesignTrie() {
        root = new TrieNode();
    }

    /** Inserts a word into the trie. */
    public void insert(String word) {
        TrieNode node = root;
        for (char ch : word.toCharArray()) {
            if (!node.containsKey(ch)) {
                node.put(ch, new TrieNode());
            }
            node = node.get(ch);
        }
        node.setEnd();
    }

    /** Returns if the word is in the trie. */
    public boolean search(String word) {
        TrieNode node = searchPrefix(word);
        return node != null && node.isEnd();
    }

    /** Returns if there is any word in the trie that starts with the given prefix. */
    public boolean startsWith(String prefix) {
        return searchPrefix(prefix) != null;
    }

    // Helper method to search for a word or prefix
    private TrieNode searchPrefix(String word) {
        TrieNode node = root;
        for (char ch : word.toCharArray()) {
            if (node.containsKey(ch)) {
                node = node.get(ch);
            } else {
                return null;
            }
        }
        return node;
    }
}
