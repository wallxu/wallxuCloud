package com.wallxu.datastructure.queue;


/**
 * 循环队列
 * tail == front 数据为空
 * (tail + 1) % data.length == front 数据为满
 *
 *
 * public void enqueue(E e); O(1) 均摊
 * public E dequeue();       O(1) 均摊
 * @param <E>

 */
public class LoopQueue<E> implements Queue<E> {

    private E[] data;
    private int size;
    private int front, tail;

    public LoopQueue(int capacity){
        //浪费一个位置
        this.data = (E[]) new Object[capacity + 1];
        this.size = 0;
        this.front = 0;
        this.tail = 0;
    }

    public LoopQueue(){
        this(10);
    }

    @Override
    public void enqueue(E e) {
        //入队，判断队列是否已满
        if ((this.tail + 1) % data.length == this.front){
            //满了，扩容
            resize(this.getSize() * 2);
        }

        data[this.tail] = e;
        this.tail = (this.tail + 1) % data.length;
        this.size ++;
    }

    private void resize(int newCapacity) {
        //新队列，扩容后
        E[] newData = (E[]) new Object[newCapacity + 1];

        //把数据拷入新队列里。
        for(int i=0; i<size; i++){
            newData[i] = data[(i + front) % data.length];
        }

        data = newData;
        front = 0;
        tail = size;
    }



    @Override
    public E dequeue() {
        //出队
        if (isEmpty()){
            //队列空
            throw new ArrayIndexOutOfBoundsException("队列已空，无法出队");
        }

        E old = data[front];
        data[front] = null;
        this.front = (this.front + 1) % data.length;
        this.size --;

        //队列数据不足四分之一，缩容
        if(this.size == (this.getSize() / 4) && (this.getSize() /2 != 0)){
            resize(this.getSize() / 2);
        }

        return old;
    }

    @Override
    public E getFront() {
        if (isEmpty()){
            //队列空
            throw new ArrayIndexOutOfBoundsException("队列已空，无法出队");
        }
        return data[front];
    }

    @Override
    public int getSize() {
        return this.data.length - 1;
    }

    @Override
    public boolean isEmpty() {
        //tail == front 数据为空
        return this.front == this.tail;
    }

    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder();

        int size = this.getSize();

        sb.append(String.format("LoopQueue: size = %d \n", size));
        sb.append("front [");

//        for (int i=0; i < size; i++){
//            if(data[i] != null){
//                sb.append(data[i]);
//                if (i != size -1){
//                    sb.append(", ");
//                }
//            }
//        }

        for (int i = this.front; i != this.tail; i=(i + 1) % data.length){
            sb.append(data[i]);
            if ((i + 1) % data.length != this.tail){
                sb.append(", ");
            }
        }

        sb.append(" ] tail ");
        return sb.toString();
    }
}
