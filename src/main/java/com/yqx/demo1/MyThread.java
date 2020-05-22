package com.yqx.demo1;

/**
 * 自定义线程
 *
 * @author YangChingyu-k
 * @date 2019/9/12 14:38
 */
public class MyThread extends Thread{


    public MyThread(String name) {
        super(name);
    }

    @Override
    public void run() {
        for (int i=0;i<10;i++) {
            System.out.println(getName() + ":正在执行!" + i);
        }
    }
}
