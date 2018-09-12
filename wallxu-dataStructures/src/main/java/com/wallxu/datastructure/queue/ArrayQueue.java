package com.wallxu.datastructure.queue;

import com.wallxu.datastructure.array.Array;

/**
 * public void enqueue(E e); O(1)
 * public E dequeue();  O(n)
 * @param <E>
 */

public class ArrayQueue<E> implements Queue<E> {


    private Array<E> array;

    //构造函数
    public ArrayQueue(int capacity) {
        this.array = new Array<>(capacity);
    }

    public ArrayQueue() {
        this.array = new Array<>();
    }

    @Override
    public void enqueue(E e) {
        this.array.addLast(e);
    }

    @Override
    public E dequeue() {
        return this.array.removeFirst();
    }

    @Override
    public E getFront() {
        return this.array.getFirst();
    }

    @Override
    public int getSize() {
        return this.array.getSize();
    }

    @Override
    public boolean isEmpty() {
        return this.array.isEmpty();
    }

    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder();

        int size = array.getSize();

        sb.append(String.format("ArrayQueue: size = %d \n", size));
        sb.append("front [");

        for (int i=0; i < size; i++){
            sb.append(array.get(i));
            if (i != size -1){
                sb.append(", ");
            }
        }
        sb.append(" ] tail ");
        return sb.toString();
    }
}
