package com.yqx.juc.forkJoin;

import java.util.concurrent.ExecutionException;
import java.util.concurrent.ForkJoinPool;
import java.util.concurrent.ForkJoinTask;
import java.util.stream.LongStream;

/**
 * 同一任务，不同方法的效率
 *
 * @author YangChingyu-k
 * @date 2020/2/27 16:20
 */
public class Test {

    public static void main(String[] args) throws ExecutionException, InterruptedException {

        test1();    // sum：500000000500000000 time：8389
        test2();    // sum：500000000500000000 time：7530
        test3();    // sum：500000000500000000 time：222

    }

    // 一般方法
    private static void test1() {
        Long sum = 0L;
        Long start = System.currentTimeMillis();
        for (Long i = 0L; i <= 10_0000_0000; i++) {
            sum += i;
        }
        Long end = System.currentTimeMillis();
        System.out.println("test1 => sum：" + sum + " time：" + (end - start));
    }

    // 使用forkjoin
    private static void test2() throws ExecutionException, InterruptedException {
        Long start = System.currentTimeMillis();

        ForkJoinPool forkJoinPool = new ForkJoinPool();
        ForkJoinTask<Long> task = new ForkJoinDemo(0L, 10_0000_0000L);
        ForkJoinTask<Long> submit = forkJoinPool.submit(task);  // 提交任务
        Long sum = submit.get();

        Long end = System.currentTimeMillis();
        System.out.println("test2 => sum：" + sum + " time：" + (end - start));
    }

    // stream并行流
    private static void test3() {
        Long start = System.currentTimeMillis();

        Long sum = LongStream.rangeClosed(0L, 10_0000_0000L).parallel().reduce(0L, Long::sum);

        Long end = System.currentTimeMillis();
        System.out.println("test3 => sum：" + sum + " time：" + (end - start));
    }


}
