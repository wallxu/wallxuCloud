package com.wallxu.datastructure.stack;

import com.wallxu.datastructure.array.Array;

public class ArrayStack<E> implements Stack<E> {

    private Array<E> array;

    //构造函数
    public ArrayStack(int capacity) {
        this.array = new Array<>(capacity);
    }

    public ArrayStack() {
        this.array = new Array<>();
    }

    @Override
    public boolean isEmpty() {
        return this.array.isEmpty();
    }

    @Override
    public int getSize() {
        return this.array.getSize();
    }

    @Override
    public void push(E e) {
        this.array.addLast(e);
    }

    @Override
    public E pop() {
        return this.array.removeLast();
    }

    @Override
    public E peek() {
        return this.array.getLast();
    }

    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder();

        int size = array.getSize();

        sb.append(String.format("ArrayStack: size = %d \n", size));
        sb.append("[");

        for (int i=0; i < size; i++){
            sb.append(array.get(i));
            if (i != size -1){
                sb.append(", ");
            }
        }
        sb.append(" ] top ");
        return sb.toString();
    }
}
