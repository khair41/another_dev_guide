package com.practice.tree;

public abstract class BinaryHeap  {

    protected static final int FRONT = 1;

    abstract void add(int val);
    abstract void delete(int pos);
    abstract int getRoot(); //read
    abstract int extractRoot(); //delete
    abstract void alterKeyValue(int pos, int data);
    abstract int [] toArray();



}
