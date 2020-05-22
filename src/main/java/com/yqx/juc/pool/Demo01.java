package com.yqx.juc.pool;

import java.util.concurrent.*;

/**
 * Executors 工具类，3大方法
 *
 * ThreadPoolExecutor 4种拒绝策略：
 *      new ThreadPoolExecutor.AbortPolicy() // 满了，还有人进来，不处理这个人的，抛出异常
 *      new ThreadPoolExecutor.CallerRunsPolicy() // 哪来的去哪里！
 *      new ThreadPoolExecutor.DiscardPolicy() // 满了，丢掉任务，不会抛出异常！
 *      new ThreadPoolExecutor.DiscardOldestPolicy() // 满了，尝试去和最早的竞争，也不会抛出异常！
 *
 * @author YangChingyu-k
 * @date 2020/2/27 14:04
 */
public class Demo01 {

    public static void main(String[] args) {

        // ExecutorService threadPool = Executors.newSingleThreadExecutor();   // 创建单个线程
        // ExecutorService threadPool1 = Executors.newFixedThreadPool(5);  // 创建一个固定线程池的大小
        // ExecutorService threadPool2 = Executors.newCachedThreadPool();  // 可伸缩的，遇强则强，遇弱则弱
        //
        // try {
        //     for (int i=0;i<100;i++) {
        //         threadPool.execute(()->{
        //             System.out.println(Thread.currentThread().getName() + " OK");
        //         });
        //
        //     }
        // } catch (Exception e) {
        //     e.printStackTrace();
        // } finally {
        //     threadPool.shutdown();
        // }
        // ------------------------------------------------------------------------------------------ //

        // 获取CPU核数
        System.out.println("CPU核数：" + Runtime.getRuntime().availableProcessors());

        // 手动创建线程池
        // 自定义线程池！工作 ThreadPoolExecutor
        ThreadPoolExecutor threadPoolExecutor = new ThreadPoolExecutor(
                2,
                Runtime.getRuntime().availableProcessors(),
                3,
                TimeUnit.SECONDS,
                new LinkedBlockingDeque<>(3),
                Executors.defaultThreadFactory(),
                new ThreadPoolExecutor.DiscardOldestPolicy()    // 队列满了，尝试去和最早的竞争，也不会抛出异常
        );

        try {
            // 最大承载：Deque + max
            // 超过 RejectedExecutionHandler
            for (int i=1;i<=9;i++) {
                threadPoolExecutor.execute(()->{
                    System.out.println(Thread.currentThread().getName() + " OK");
                });
            }
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            // 线程池用完，程序结束，关闭线程池
            threadPoolExecutor.shutdown();
        }

    }

}
