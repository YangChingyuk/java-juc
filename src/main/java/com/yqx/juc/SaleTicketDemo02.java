package com.yqx.juc;

import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;

/**
 * 并发2.0：Lock
 *
 * @author YangChingyu-k
 * @date 2020/2/26 14:02
 */
public class SaleTicketDemo02 {

    public static void main(String[] args) {

    Ticket2 ticket2 = new Ticket2();

        new Thread(() -> {
            for (int i = 1; i < 40; i++) {
                ticket2.sale();
            }
        }, "A").start();
        new Thread(() -> {
            for (int i = 1; i < 40; i++) {
                ticket2.sale();
            }
        }, "B").start();
        new Thread(() -> {
            for (int i = 1; i < 40; i++) {
                ticket2.sale();
            }
        }, "C").start();
        new Thread(() -> {
            for (int i = 1; i < 40; i++) {
                ticket2.sale();
            }
        }, "D").start();

    }

}

// Lock三部曲
// new ReentrantLock()
// lock.lock() 加锁
// lock.unlock() 解锁
class Ticket2 {
    // 属性，方法
    private int number = 30;

    Lock lock = new ReentrantLock();

    public void sale() {

        lock.lock();    // 加锁
        try {
            // 业务代码
            if (number > 0) {
                System.out.println(Thread.currentThread().getName() + "卖出了" + (number--) + "票，剩余：" + number);
            }
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            lock.unlock();  // 解锁
        }

    }
}
