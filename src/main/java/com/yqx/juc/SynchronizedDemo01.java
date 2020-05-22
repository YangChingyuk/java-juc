package com.yqx.juc;

/**
 * synchronized (关键字版)
 * 线程之间通信问题：生产者和消费者，等待唤醒，通知唤醒
 * 线程交替执行 A  B 操作同一变量 num = 0
 * A num+1
 * B num-1
 * 当只有A B两个线程时，使用if不会有问题；但是当存在A B C D多个线程时，会存在问题，“虚假唤醒”， 应改为while！
 *
 * @author YangChingyu-k
 * @date 2020/2/26 14:18
 */
public class SynchronizedDemo01 {

    public static void main(String[] args) {
        Data data = new Data();

        new Thread(() -> {
            for (int i = 0; i < 10; i++) {
                try {
                    data.increment();
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }
        }, "A").start();
        new Thread(() -> {
            for (int i = 0; i < 10; i++) {
                try {
                    data.decrement();
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }
        }, "B").start();
        new Thread(() -> {
            for (int i = 0; i < 10; i++) {
                try {
                    data.increment();
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }
        }, "C").start();

        new Thread(() -> {
            for (int i = 0; i < 10; i++) {
                try {
                    data.decrement();
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }
        }, "D").start();
    }

}

class Data {

    private int number = 0;
    // +1
    public synchronized void increment () throws InterruptedException {
        while (number != 0) {
            // 等待
            this.wait();
        }
        number++;
        System.out.println(Thread.currentThread().getName() + "=>" + number);
        // 通知其他线程，+1完毕
        this.notifyAll();
    }
    // -1
    public synchronized void decrement () throws InterruptedException {
        while (number == 0) {
            // 等待
            this.wait();
        }
        number--;
        System.out.println(Thread.currentThread().getName() + "=>" + number);
        // 通知
        this.notifyAll();
    }
}
