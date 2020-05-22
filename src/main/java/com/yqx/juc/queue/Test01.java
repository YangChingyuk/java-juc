package com.yqx.juc.queue;

import java.util.concurrent.ArrayBlockingQueue;
import java.util.concurrent.TimeUnit;

/**
 * @author YangChingyu-k
 * @date 2020/2/27 11:05
 */
public class Test01 {

    public static void main(String[] args) throws InterruptedException {

        ArrayBlockingQueue<Object> queue = new ArrayBlockingQueue<>(3);

        // System.out.println(queue.add("A"));
        // System.out.println(queue.add("B"));
        // System.out.println(queue.add("C"));
        // java.lang.IllegalStateException: Queue full      抛出异常
        // System.out.println(queue.add("D"));

        // System.out.println(queue.remove());
        // System.out.println(queue.remove());
        // System.out.println(queue.remove());
        // java.util.NoSuchElementException     抛出异常
        // System.out.println(queue.remove());
        // ----------------------------------------------------------- //

        // System.out.println(queue.offer("A"));
        // System.out.println(queue.offer("B"));
        // System.out.println(queue.offer("C"));
        // 输出false，但不会抛出异常
        // System.out.println(queue.offer("D"));

        // System.out.println(queue.poll());
        // System.out.println(queue.poll());
        // System.out.println(queue.poll());
        // 输出null， 但不会抛出异常
        // System.out.println(queue.poll());
        // ----------------------------------------------------------- //

        // queue.put("A");
        // queue.put("B");
        // queue.put("C");
        // 队列没有位置了，一直阻塞
        // queue.put("D");

        // System.out.println(queue.take());
        // System.out.println(queue.take());
        // System.out.println(queue.take());
        // 没有元素了，一直阻塞
        // System.out.println(queue.take());

        queue.offer("A");
        queue.offer("B");
        queue.offer("C");
        // 等待超过2s就退出
        queue.offer("D", 2, TimeUnit.SECONDS);
        System.out.println(queue.poll());
        System.out.println(queue.poll());
        System.out.println(queue.poll());
        // 等待超过2s就返回退出
        System.out.println(queue.poll(2, TimeUnit.SECONDS));
    }

}
