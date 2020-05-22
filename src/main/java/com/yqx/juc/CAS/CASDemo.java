package com.yqx.juc.CAS;

import java.util.concurrent.atomic.AtomicInteger;

/**
 * CAS
 *
 * @author YangChingyu-k
 * @date 2020/2/28 11:51
 */
public class CASDemo {

    // // CAS compareAndSet : 比较并交换！
    public static void main(String[] args) {

        AtomicInteger atomicInteger = new AtomicInteger(2020);

        // 期望，更新
        // 如果我期望的值达到了，那么就更新，否则，就不更新, CAS 是CPU的并发原语！
        System.out.println(atomicInteger.compareAndSet(2020, 2021));
        System.out.println(atomicInteger.get());
        System.out.println("-------------------");

        atomicInteger.getAndIncrement();
        System.out.println(atomicInteger.compareAndSet(2020, 2021));
        System.out.println(atomicInteger.get());
    }

}
