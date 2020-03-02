package com.wallxu.datastructure.linkedlist;

public class LinkedList<E> {

    private int size; //元素个数
    private Node dummyHead; //虚拟头结点


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

    public LinkedList() {
        //虚拟头结点，没数据
        this.dummyHead = new Node(null, null);
        this.size = 0;
    }

    //是否为空
    public boolean isEmpty(){
        return this.size == 0;
    }

    public int getSize(){
        return this.size;
    }

    //指定位置插入元素
    public void add(int index, E e){
        if (index < 0 || index > size){
            throw new ArrayIndexOutOfBoundsException("index is valid");
        }

        //前一个结点数据
        //获取要插入的位置的前一个结点数据
        Node prev = dummyHead;
        for (int i = 0; i < index; i++){
            prev = prev.next;
        }

        //前一个结点
        prev.next = new Node(e, prev.next);
        size ++;

    }

    public void addFirst(E e){
        add(0, e);
    }

    public void addLast(E e){
        add(size, e);
    }

    //获取指定位置元素
    public E get(int index){
        if (index < 0 || index > size){
            throw new ArrayIndexOutOfBoundsException("index is valid");
        }

        Node cur = dummyHead.next;

        for (int i = 0; i < index; i++){
            cur = cur.next;
        }
        return cur.e;
    }

    public E getLast(){
        return get(size);
    }


    public E remove(int index){
        if (index < 0 || index > size){
            throw new ArrayIndexOutOfBoundsException("index is valid");
        }

        Node prev = dummyHead;

        for (int i = 0; i < index; i++){
            prev = prev.next;
        }

        Node cur = prev.next;

        if (cur.next == null){
            //移除的是最后一个元素
            prev.next = null;
        }else {
            prev.next = cur.next;
        }
        size --;
        cur.next = null;

        return cur.e;
    }

    public E removeFirst(){
        return remove(0);
    }

    public E removeLast(){
        return remove(size -1);
    }

    @Override
    public String toString(){
        StringBuffer sb = new StringBuffer();

        sb.append(String.format("LinkedList: size = %d \n", size));
        sb.append(" [");

        Node cur = dummyHead.next;
        int index = 0;
        //遍历
        while(index++ < size){
            sb.append(cur.e);
            if (index < size){

                sb.append("--->");
            }
            cur = cur.next;
        }

        sb.append(" ] ");
        return sb.toString();
    }

    public static void main(String[] args) {
        LinkedList<Integer> list = new LinkedList<Integer>();

        for(int i=0; i < 10; i++){
            list.add(i, i);
        }

        list.add(5, 55);

        list.add(0, 505);
        System.out.println(list);


        System.out.println(list.get(6));

        list.remove(2);
        System.out.println(list);

        list.removeLast();
        System.out.println(list);

    }


}
