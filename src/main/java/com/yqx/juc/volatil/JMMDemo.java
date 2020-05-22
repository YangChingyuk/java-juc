package com.yqx.juc.volatil;

import java.util.concurrent.TimeUnit;

/**
 * @author YangChingyu-k
 * @date 2020/2/27 17:04
 */
public class JMMDemo {
    // 不加 volatile 程序就会死循环
    // 加 volatile 可以保证可见性
    private volatile static int num = 0;

    public static void main(String[] args) {

        new Thread(()->{
            while (num == 0) {

            }
        }).start();

        try {
            TimeUnit.SECONDS.sleep(1);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        num = 1;
        System.out.println(num);

    }

}
