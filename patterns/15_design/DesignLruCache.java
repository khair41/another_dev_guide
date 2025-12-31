package com.framework.patterns.design;

import java.util.HashMap;
import java.util.Map;

/**
 * Pattern 93: Design (General/Specific) - LRU Cache
 */
public class DesignLruCache {

    /*
     * =================================================================================
     * CONDITIONS FOR APPLICABILITY
     * =================================================================================
     *
     * 1.  Problem Goal: You need to design a data structure that holds a certain number of items
     *     (a `capacity`). When the capacity is exceeded and a new item needs to be added, the
     *     "Least Recently Used" (LRU) item must be evicted.
     *
     * 2.  Performance Constraints: The `get` (retrieve an item) and `put` (add/update an item)
     *     operations must be performed in O(1) average time complexity.
     *
     * 3.  Logic: Achieving O(1) for both `get` and `put` requires a combination of two data structures:
     *     -   A **Hash Map**: This provides O(1) access to any node. The map will store the key and a
     *       reference to a node in a doubly linked list.
     *     -   A **Doubly Linked List**: This allows for O(1) addition and removal of nodes. The list will
     *       be used to maintain the order of usage. The most recently used item will be at the head
     *       of the list, and the least recently used item will be at the tail.
     *
     *     -   **`get(key)` operation**:
     *         1.  Look up the key in the hash map. If it doesn't exist, return -1.
     *         2.  If it exists, the map gives you the node. Move this node to the front of the linked list
     *             to mark it as most recently used.
     *         3.  Return the node's value.
     *
     *     -   **`put(key, value)` operation**:
     *         1.  Check if the key already exists in the hash map. If so, update its value and move the
     *             corresponding node to the front of the list.
     *         2.  If the key does not exist, create a new node.
     *         3.  Check if the cache is at full capacity. If so, evict the least recently used item by
     *             removing the node at the tail of the linked list and also removing its key from the hash map.
     *         4.  Add the new node to the front of the linked list and add its key/node pair to the hash map.
     *
     * =================================================================================
     * GENERIC TEMPLATE (LRU Cache)
     * =================================================================================
     */

    // Helper class for the doubly linked list nodes
    private static class Node {
        int key, value;
        Node prev, next;
        Node(int key, int value) {
            this.key = key;
            this.value = value;
        }
    }

    private final int capacity;
    private final Map<Integer, Node> cache;
    private final Node head, tail; // Dummy head and tail nodes

    public DesignLruCache(int capacity) {
        this.capacity = capacity;
        this.cache = new HashMap<>();
        this.head = new Node(-1, -1);
        this.tail = new Node(-1, -1);
        head.next = tail;
        tail.prev = head;
    }

    // Helper to remove a node from the list
    private void remove(Node node) {
        node.prev.next = node.next;
        node.next.prev = node.prev;
    }

    // Helper to add a node to the front of the list (most recently used)
    private void add(Node node) {
        node.next = head.next;
        node.prev = head;
        head.next.prev = node;
        head.next = node;
    }

    public int get(int key) {
        if (!cache.containsKey(key)) {
            return -1;
        }
        Node node = cache.get(key);
        // Move the accessed node to the front.
        remove(node);
        add(node);
        return node.value;
    }

    public void put(int key, int value) {
        if (cache.containsKey(key)) {
            // If key exists, update value and move to front.
            Node node = cache.get(key);
            node.value = value;
            remove(node);
            add(node);
        } else {
            // If key is new, check capacity.
            if (cache.size() == capacity) {
                // Evict the least recently used item (the tail's previous node).
                Node lruNode = tail.prev;
                remove(lruNode);
                cache.remove(lruNode.key);
            }
            // Add the new node to the front.
            Node newNode = new Node(key, value);
            add(newNode);
            cache.put(key, newNode);
        }
    }
}
