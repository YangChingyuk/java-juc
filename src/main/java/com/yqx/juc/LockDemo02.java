package com.yqx.juc;

import java.util.concurrent.locks.Condition;
import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;

/**
 * Lock顺序版
 *
 * @author YangChingyu-k
 * @date 2020/2/26 15:29
 */
public class LockDemo02 {

    public static void main(String[] args) {
        Data3 data3 = new Data3();

        new Thread(() -> {
            for (int i = 0; i < 10; i++) {
                data3.printA();
            }
        }, "A").start();
        new Thread(() -> {
            for (int i = 0; i < 10; i++) {
                data3.printB();
            }
        }, "B").start();
        new Thread(() -> {
            for (int i = 0; i < 10; i++) {
                data3.printC();
            }
        }, "C").start();
    }

}

class Data3 {

    Lock lock = new ReentrantLock();
    Condition condition1 = lock.newCondition();
    Condition condition2 = lock.newCondition();
    Condition condition3 = lock.newCondition();
    private int number = 1; // 1A 2B 3C

    public void printA () {
        lock.lock();
        try {
            // 判断->执行->通知
            while (number!=1) {
                condition1.await(); // 等待
            }
            System.out.println(Thread.currentThread().getName() + "=>AAAA");
            number = 2; // 唤醒指定人B
            condition2.signalAll();
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            lock.unlock();
        }
    }

    public void printB() {
        lock.lock();
        try {
            // 判断->执行->通知
            while (number != 2) {
                condition2.await(); // 等待
            }
            System.out.println(Thread.currentThread().getName() + "=>BBBB");
            number = 3; // 唤醒指定人C
            condition3.signalAll();
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            lock.unlock();
        }
    }

    public void printC () {
        lock.lock();
        try {
            // 判断->执行->通知
            while (number!=3) {
                condition3.await(); // 等待
            }
            System.out.println(Thread.currentThread().getName() + "=>CCCC");
            System.out.println("-------");
            number = 1; // 唤醒指定人A
            condition1.signalAll();
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            lock.unlock();
        }
    }

}
