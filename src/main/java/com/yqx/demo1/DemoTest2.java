package com.yqx.demo1;

/**
 * 实现Runable方式线程
 *
 * @author YangChingyu-k
 * @date 2019/9/12 14:48
 */
public class DemoTest2 {

    public static void main(String[] args) {

        Runnable runnable = new MyRunable();
        Thread th1 = new Thread(runnable);
        Thread th2 = new Thread(runnable);

        th1.start();
        th2.start();

        for (int i=0;i<10;i++) {
            System.out.println("main线程正在执行!" + i);
        }

    }

}
