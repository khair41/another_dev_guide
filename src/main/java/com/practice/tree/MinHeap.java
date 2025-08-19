package com.practice.tree;

import java.util.Arrays;

public class MinHeap extends BinaryHeap {

    private int [] heap;
    private int size;
    private int maxSize;

    public static void main(String [] args){
        MinHeap mh = new MinHeap(15);
        mh.add(5);
        mh.add(3);
        mh.add(17);
        mh.add(10);
        mh.add(84);
        mh.add(19);
        mh.add(6);
        mh.add(22);
        mh.add(9);

        System.out.println(Arrays.toString(mh.toArray()));
        System.out.println("ROOT: " + mh.extractRoot());
        System.out.println(Arrays.toString(mh.toArray()));
    }

    public MinHeap(int maxSize){
        this.size = 0;
        this.maxSize = maxSize;
        heap = new int[this.maxSize + 1];
        heap[0] = Integer.MIN_VALUE;
    }

    private int parent(int pos){
        return pos / 2 ;
    }

    private int leftChild(int pos){
        return pos * 2;
    }

    private int rightChild(int pos){
        return pos * 2 + 1;
    }

    private void swap(int pos1, int pos2){
        int temp = heap[pos1];
        heap[pos1] = heap[pos2];
        heap[pos2] = temp;
    }


    @Override
    void add(int val) {
        if(this.size >= this.maxSize) return;
        heap[++this.size] = val;
        int current = this.size;
        while(this.heap[current] < this.heap[this.parent(current)]){
            swap(current, this.parent(current));
            current = this.parent(current);
        }
    }

    @Override
    void delete(int pos) {
        swap(pos, this.size--);
        minHeapify(pos);
        /*TODO
        * extract method bubble up for reuse
        * */
    }

    @Override
    int getRoot() {
        return this.heap[super.FRONT];
    }

    @Override
    int extractRoot() {
        int root = this.heap[super.FRONT];
        swap(super.FRONT, this.size--);
        minHeapify(super.FRONT);
        return root;
    }

    private void minHeapify (int pos){
        if(!(pos >= this.size / 2 && pos <= this.size)) {
            if (this.heap[pos] > this.heap[this.leftChild(pos)]
                    || this.heap[pos] > this.heap[this.rightChild(pos)]) {
                if (this.rightChild(pos) < this.leftChild(pos)) {
                    swap(pos, this.rightChild(pos));
                    minHeapify(this.rightChild(pos));
                } else {
                    swap(pos, this.leftChild(pos));
                    minHeapify(this.leftChild(pos));
                }
            }
        }
    }

    @Override
    void alterKeyValue(int pos, int data) {
        //add bubble up and bubble down
    }

    @Override
    int [] toArray() {
        return Arrays.copyOfRange(this.heap, super.FRONT, this.size + 1);
    }

}
