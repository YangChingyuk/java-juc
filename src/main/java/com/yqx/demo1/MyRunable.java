package com.yqx.demo1;

/**
 * @author YangChingyu-k
 * @date 2019/9/12 14:46
 */
public class MyRunable implements Runnable {

    @Override
    public void run() {
        for (int i=0;i<10;i++) {
            System.out.println("我的线程:正在执行" + i);
        }
    }
}
