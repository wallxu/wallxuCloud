package com.wallxu.datastructure.stack;


/**
 * 栈
 * 栈是一种后进先出的数据结构
 * Last In First Out(LIFO)
 * @param <E>
 */

public interface Stack<E> {

    public boolean isEmpty();   //O(1)
    public int getSize();       //O(1)

    public void push(E e); //入栈  O(1)均摊
    public E pop();         //出栈 O(1)均摊
    public E peek();        //查看栈顶元素 O(1)
}
