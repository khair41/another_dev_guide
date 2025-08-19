package com.practice.linkedlist;

public class DoubleLinkedList {

    public Node head = null;
    public Node tail = null;

    public static void main (String [] args){
        DoubleLinkedList dll = new DoubleLinkedList();

//        dll.addBeginning(1, null);
//        dll.addBeginning(2, null);
//        dll.addBeginning(3, null);
//
//        dll.addEnd(1, null);
//        dll.addEnd(2, null);
//        dll.addEnd(3, null);
        dll.testAddBefore();
//        dll.testAddAfter();


    }

    public void testAddBefore(){
        Node n1 = new Node(1);
        Node n2 = new Node(2);
        Node n3 = new Node(3);
        char c = '0';
        boolean b = c == '0' ? true : false;

        DoubleLinkedList dll = new DoubleLinkedList();
//        dll.addBeginning(-1, n1);
//        dll.addBeginning(-1, n2);
//        dll.addBeginning(-1, n3);
        dll.addBefore(n1, 4);
        dll.print();
    }

    public void testAddAfter(){
        Node n1 = new Node(1);
        Node n2 = new Node(2);
        Node n3 = new Node(3);

        DoubleLinkedList dll = new DoubleLinkedList();
        dll.addBeginning(-1, n1);
        dll.addBeginning(-1, n2);
        dll.addBeginning(-1, n3);
        dll.addAfter(n1, 4);
        dll.print();
    }

    public void addBeginning(int data, Node node){
        if(head == null){
            head = node == null ? new Node(data) : node;
            tail = head;
        } else {
            Node temp = node == null ? new Node(data) : node;
            temp.next = head;
            head.prev = temp;
            head = temp;
        }
    }

    public void addEnd(int data, Node node){
        if(head == null){
            head = node == null ? new Node(data) : node;
            tail = head;
        } else {
            Node temp = node == null ? new Node(data) : node;
            temp.prev = tail;
            tail.next = temp;
            tail = temp;
        }
    }

    public void addBefore(Node node, int data){
        if(node.prev == null){
            this.addBeginning(data, null);
        } else {
            Node temp = new Node(data);
            temp.next = node;
            temp.prev = node.prev;
            node.prev.next = temp;
            node.prev = temp;
            //1 -> (4) -> *2 -> 3 ->
        }
    }

    public void addAfter(Node node, int data){
        if(node.next == null){
            this.addEnd(data, null);
        } else {
            Node temp = new Node(data);
            temp.prev = node;
            temp.next = node.next;
            node.next.prev = temp;
            node.next = temp;
            //*1 -> (4) -> 2 -> 3 ->
        }
    }

    public void printFwd(){
        Node temp = head;
        while(temp != null){
            System.out.print(temp.data + " -> ");
            temp = temp.next;
        }
        System.out.println();
    }

    public void printBck(){
        Node temp = tail;
        while(temp != null){
            System.out.print(temp.data + " <- ");
            temp = temp.prev;
        }
        System.out.println();
    }

    public void print(){
        printFwd();
        printBck();
    }

    public class Node {
        int data;
        Node next;
        Node prev;

        public Node(int data){
            this.data = data;
        }
    }
}
