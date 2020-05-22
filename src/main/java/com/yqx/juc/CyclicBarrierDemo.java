package com.yqx.juc;

import java.util.concurrent.BrokenBarrierException;
import java.util.concurrent.CyclicBarrier;

/**
 * 常用辅助类：2.CyclicBarrier 加法计数器
 *
 * @author YangChingyu-k
 * @date 2020/2/27 9:59
 */
public class CyclicBarrierDemo {

    public static void main(String[] args) {

        // 召唤龙珠线程
        // 目标等于7时，执行
        CyclicBarrier cyclicBarrier = new CyclicBarrier(7, () -> {
            System.out.println("召唤神龙！");
        });

        for (int i=1;i<=7;i++) {
            final int temp = i;
            new Thread(()->{
                System.out.println(Thread.currentThread().getName() + "收集" + temp +"颗龙珠");
                try {
                    // 等待
                    cyclicBarrier.await();
                } catch (InterruptedException e) {
                    e.printStackTrace();
                } catch (BrokenBarrierException e) {
                    e.printStackTrace();
                }
            }, String.valueOf(i)).start();


        }

    }

}
