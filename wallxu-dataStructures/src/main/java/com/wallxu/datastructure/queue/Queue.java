package com.wallxu.datastructure.queue;

/**
 * 队列
 * 队列是一种先进先出的数据结构
 * First In First Out(FIFO)
 */
public interface Queue<E> {

    public void enqueue(E e); //入队

    public E dequeue();         //出队

    public E getFront();        //取出队首

    public int getSize();

    public boolean isEmpty();

}
