package com.wallxu.datastructure.array;

/**
 * Created by Administrator on 2018/8/28.
 *
 *
 * 动态数组时间复杂度
 * 添加 O(n)
 * 删除 O(n)
 * 修改 已知索引O(1)； 未知索引O(n)
 * 查询 已知索引O(1)； 未知索引O(n)
 * resize O(n)
 * addLast、removeLast 均摊复杂度 O(1)
 * 复杂度震荡：出现问题的原因：removeLast时resize过于着急（Eager）
 * 解决方案：lazy， 当size = capacity/4时，才将capacity/2。
 *
 *
 */
public class Array<E> {
    private static final int DEFAULT_CAPACITY = 10;
    //数组
    private E[] data;
    //长度
    private int size;

    //无参构造函数
    public Array(){
        //默认长度
       this(DEFAULT_CAPACITY);
    }

    public Array(int capacity){
        this.data = (E[])new Object[capacity];
        this.size = 0;
    }

    //末尾添加数据 O(1)
    public void addLast(E e){
        add(size, e);
    }

    //头部添加数据 O(n)
    public void addFirst(E e){
        add(0, e);
    }

    //指定位置添加数据 O(n)
    public void add(int index, E e){


        if(index < 0 || index > size){
            throw new ArrayIndexOutOfBoundsException("index not valid");
        }

        if(size == data.length){
            resize(data.length * 2);
        }

        for(int i = size - 1; i > index ; i--){
            //数据后移一位
            data[i+1] = data[i];
        }
        //赋值
        data[index] = e;
        size++;
    }

    //扩容
    private void resize(int newCapacity) {
        E[] newData = (E[])new Object[newCapacity];
        for(int i = 0; i < size; i++){
            newData[i] = data[i];
        }
        data = newData;
        newData = null;
    }

    public int getSize() {
        return size;
    }

    public boolean isEmpty(){
        return size == 0;
    }

    public E get(int index) {
        if(index < 0 || index >= size){
            throw new ArrayIndexOutOfBoundsException("index not valid");
        }
        return data[index];
    }

    public E getLast(){
        return get(size - 1);
    }

    public E getFirst(){
        return get(0);
    }

    //修改
    public void update(int index, E e){
        if(index < 0 || index >= size){
            throw new ArrayIndexOutOfBoundsException("index not valid");
        }
        data[index] = e;
    }

    //删除元素，并返回值
    public E remove(int index){
        if(index < 0 || index >= size){
            throw new ArrayIndexOutOfBoundsException("index not valid");
        }

        E rs = data[index];
        //数据往前移动，i=index+1
        for(int i = index + 1; i < size ; i++){
            //数据往前移动一位
            data[i-1] = data[i];
        }
        //遍历时看不到size位置的数据，所以不需要特殊处理
        data[size-1] = null;
        size--;

        //四分之一时，缩容
        if (size == data.length / 4 && data.length / 2 != 0){
            resize(data.length / 2);
        }
        return rs;
    }

    //移除第一个
    public E removeFirst(){
        return remove(0);
    }

    //移除最后一个
    public E removeLast(){
        return remove(size - 1);
    }

    //判断某个元素是否存在
    public boolean contains(E e){
        for(int i = 0; i < size; i++){
            if(data[i] == e){
                return true;
            }
        }
        return false;
    }

    //查找某个元素的位置
    public int find(E e){
        for(int i = 0; i < size; i++){
            if (data[i].equals(e)) {
                return i;
            }
        }
        return -1;
    }

    public void removeElement(E e){
        int r = find(e);
        if (r != -1){
            //数据存在
            remove(r);
        }
    }

    @Override
    public String toString(){
        StringBuilder sb = new StringBuilder();
        sb.append(String.format("Array: size = %d, capacity = %d \n", size, data.length));
        sb.append("[");

        for (int i=0; i < size; i++){
            sb.append(data[i]);
            if (i != size -1){
                sb.append(", ");
            }
        }
        sb.append("]");
        return sb.toString();
    }


    public static void main(String[] args) {
        Array<Integer> array = new Array<Integer>();

        for (int i = 0; i < 8; i++) {
            array.addLast(i);
        }
        array.addLast(55);
        System.out.println(array);
        array.addFirst(101);
        array.add(1,1);
        array.update(2,555);
        System.out.println(array);
        array.remove(4);
        System.out.println(array);
        array.removeLast();
        System.out.println(array);
        array.removeFirst();
        System.out.println(array);

        array.add(1,3);
    }


}
