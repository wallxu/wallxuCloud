package com.wallxu.datastructure.queue;

public class LinkedListQueue<E> implements Queue<E> {


    public LinkedListQueue(){
        this.head = null;
        this.size = 0;
        this.tail = null;
    }

    private int size; //元素个数
    private Node head, tail; //头部，尾部


    //Node内部类
    private class Node{
        private E e;
        private Node next;

        public Node(E e, Node next) {
            this.e = e;
            this.next = next;
        }

        public Node(E e) {
            this(e, null);
        }

        public Node() {
            this(null, null);
        }
    }


    @Override
    public void enqueue(E e) {
        if (tail == null){
            tail = new Node(e);
            head = tail;
        }else{
            tail.next = new Node(e);
            tail = tail.next;
        }

        size ++;
    }

    @Override
    public E dequeue() {
        if(isEmpty()){
            throw new ArrayIndexOutOfBoundsException("queue is empty");
        }

        Node cur = head;
        head = head.next;

        cur.next = null;
        if(head == null){
            tail = null;
        }
        size --;

        return cur.e;
    }

    @Override
    public E getFront() {
        return this.head.e;
    }

    @Override
    public int getSize() {
        return this.size;
    }

    @Override
    public boolean isEmpty() {
        return this.size == 0;
    }
}
