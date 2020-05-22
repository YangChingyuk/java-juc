package com.yqx.demo1;

/**
 * 线程测试
 *
 * @author YangChingyu-k
 * @date 2019/9/12 14:41
 */
public class DemoTest {

    public static void main(String[] args) {

        MyThread my = new MyThread("新的线程");
        my.start();
        for (int i=0;i<10;i++) {
            System.out.println("main线程" + i);
        }

    }

}
