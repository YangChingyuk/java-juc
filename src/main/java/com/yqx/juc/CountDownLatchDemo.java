package com.yqx.juc;

import java.util.concurrent.CountDownLatch;

/**
 * 常用辅助类：1.CountDownLatch （-1减法计数器）
 * 原理：
 *      countDownLatch.countDown(); 数量 -1
 *      countDownLatch.await(); 等待计数器归零，然后再想下执行
 *      每次有线程调用 countDown() 数量-1，假设计数器变为0，await() 就会被唤醒，继续执行！
 *
 * @author YangChingyu-k
 * @date 2020/2/27 9:44
 */
public class CountDownLatchDemo {

    public static void main(String[] args) throws InterruptedException {

        // 总数为6，必须执行任务的时候，再使用
        CountDownLatch countDownLatch = new CountDownLatch(6);

        for (int i=1;i<=6;i++) {
            new Thread(()->{
                System.out.println(Thread.currentThread().getName() + " Go out");
                countDownLatch.countDown(); // 数量 -1
            },String.valueOf(i)).start();
        }

        countDownLatch.await(); // 等待计数器归零，然后再想下执行

        System.out.println("Close Door！");

    }

}
