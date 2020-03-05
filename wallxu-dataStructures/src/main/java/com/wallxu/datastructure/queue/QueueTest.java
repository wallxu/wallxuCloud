package com.wallxu.datastructure.queue;

public class QueueTest {

    public static void main(String[] args) {


        LoopQueue<Integer> loopQueue = new LoopQueue<Integer>(10);
        long t1 = testQueue(loopQueue, 100000);
        System.out.println("执行时间" + t1  / 10000000 + " 秒");

        ArrayQueue<Integer> arrayQueue = new ArrayQueue<Integer>(10);
        long t2 = testQueue(arrayQueue, 100000);
        System.out.println("执行时间" + t2  / 10000000 + " 秒");

        LinkedListQueue<Integer> linkedListQueue = new LinkedListQueue<Integer>();
        long t3 = testQueue(linkedListQueue, 100000);
        System.out.println("执行时间" + t3  / 10000000 + " 秒");


//        arrayQueueTest();

//        loopQueueTest();
    }



    public static long testQueue(Queue queue, int count){
        long start = System.nanoTime();

        //入队
        for(int i = 0; i < count; i++){
            queue.enqueue(i);
        }

        //出队
        for(int i = 0; i < count; i++){
            queue.dequeue();
        }

        return System.nanoTime() - start;
    }



    private static void loopQueueTest() {
        LoopQueue<Integer> loopQueue = new LoopQueue<Integer>(10);

        for(int i = 0; i < 10; i++){
            loopQueue.enqueue(i);

            if(i % 3 == 2){
                loopQueue.dequeue();
            }
            System.out.println(loopQueue);
        }


        loopQueue.dequeue();
        loopQueue.dequeue();
        loopQueue.dequeue();
        loopQueue.dequeue();
        loopQueue.dequeue();
        System.out.println(loopQueue);
        System.out.println(loopQueue.getFront());

    }

    private static void arrayQueueTest() {
        ArrayQueue<Integer> arrayQueue = new ArrayQueue<Integer>(10);

        for(int i = 0; i < 10; i++){
            arrayQueue.enqueue(i);

            if(i % 3 == 2){
                arrayQueue.dequeue();
            }
            System.out.println(arrayQueue);
        }

        System.out.println(arrayQueue.getFront());
    }
}
