package com.practice.linkedlist;

public class CircularLinkedList {
    public Node tail = null;

    public static void main (String [] args){
        CircularLinkedList l1 = new CircularLinkedList();
        l1.add(1);
        l1.add(2);
        l1.add(3);
        l1.add(4);
        l1.print();

    }

    public void add(int data){
        if(tail == null){
            tail = new Node(data);
            tail.next = tail;
        } else {
            Node temp = new Node(data);
            temp.next = tail.next;
            tail.next = temp;
            tail = temp;
        }
    }

    public void print(){
        Node temp = tail.next;
        while(temp != tail){
            System.out.println(temp.data);
            temp = temp.next;
        }
        System.out.println(tail.data);
    }

    public class Node {
        public int data;
        public Node next;

        public Node(int data){
            this.data = data;
        }
    }
}
