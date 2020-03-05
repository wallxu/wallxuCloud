package com.wallxu.datastructure.stack;

import com.wallxu.datastructure.linkedlist.LinkedList;

public class LinkedListStack<E> implements Stack<E> {

    private LinkedList<E> linkedList;

    public LinkedListStack(){
        this.linkedList = new LinkedList<E>();
    }

    @Override
    public boolean isEmpty() {
        return this.linkedList.isEmpty();
    }

    @Override
    public int getSize() {
        return this.linkedList.getSize();
    }

    @Override
    public void push(E e) {
        this.linkedList.addLast(e);
    }

    @Override
    public E pop() {
        return this.linkedList.removeLast();
    }

    @Override
    public E peek() {
        return this.linkedList.getLast();
    }


    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder();

        sb.append(String.format("LinkedListStack: size = %d \n", linkedList.getSize()));
        sb.append("[");

        for (int i=0; i < linkedList.getSize(); i++){
            sb.append(linkedList.get(i));
            if (i != linkedList.getSize() -1){
                sb.append(", ");
            }
        }
        sb.append(" ] top ");
        return sb.toString();
    }
}
