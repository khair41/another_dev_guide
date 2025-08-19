package com.practice.linkedlist;

public class SingleLinkedList {

    public Node head = null;
    public Node tail = null;

    public static void main (String [] args){
        SingleLinkedList l1 = new SingleLinkedList();
        l1.add(1);
        l1.add(2);
        l1.add(3);
        l1.print();

        SingleLinkedList l2_constantTime = new SingleLinkedList();
        l2_constantTime.addConstantTime(1);
        l2_constantTime.addConstantTime(2);
//        l2_constantTime.addConstantTime(3);
//        l2_constantTime.addConstantTime(3);
//        l2_constantTime.addConstantTime(3);
        l2_constantTime.print();
    }

    public void add(int data){
        if(head == null){
            head = new Node(data);
        } else {
            Node temp = head;
            while(temp.next != null){
                temp = temp.next;
            }
            temp.next = new Node(data);
        }
    }

    public void addConstantTime(int data){
        if(head == null){
            head = new Node(data);
            tail = head;
        } else {
            Node temp = new Node(data);
            tail.next = temp;
            tail = temp;
        }
    }

    public void print(){
        Node temp = head;
        while(temp != null){
            System.out.println(temp.data);
            temp = temp.next;
        }
    }

    public class Node {
        public int data;
        public Node next;

        public Node(int data){
            this.data = data;
        }
    }
}
