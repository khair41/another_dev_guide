package com.practice.purgatory;

public class GenericLinkedList {
    private Node head;

    public void insert(int data) {

        Node newNode = new Node(data);
        if(head == null){
            head = newNode;
        } else {
            Node last = head;
            while(last.next != null){
                last = last.next;
            }
            last.next = newNode;
        }
    }

    public void printList(){
        if(head == null) System.out.println("List is empty");

        Node current = head;
        while(current != null){
            System.out.println(current.data);
            current = current.next;
        }
    }

    static class Node<T> {
        T data;
        Node next;
        Node(T t){
            data = t;
        }
    }

    public static void main(String args []){

        GenericLinkedList list = new GenericLinkedList();
        list.insert(1);
        list.insert(2);
        list.insert(3);

        list.printList();
        // Insert the values
//        list = insert(list, 1);
//        list = insert(list, 2);
//        list = insert(list, 3);
//        list = insert(list, 4);
//        list = insert(list, 5);
//        list = insert(list, 6);
//        list = insert(list, 7);
//        list = insert(list, 8);
//
//        // Print the LinkedList
//        printList(list);
    }
}
