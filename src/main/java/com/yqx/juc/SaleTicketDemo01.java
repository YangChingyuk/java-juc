package com.yqx.juc;

/**
 * 并发1.0：synchronized
 *
 * @author YangChingyu-k
 * @date 2020/2/26 13:46
 */
public class SaleTicketDemo01 {

    public static void main(String[] args) {
        // 并发：多线程操作统一资源，把资源类丢入线程
        Ticket ticket = new Ticket();

        new Thread(() -> {
            for (int i = 1; i < 40; i++) {
                ticket.sale();
            }
        }, "A").start();
        new Thread(() -> {
            for (int i = 1; i < 40; i++) {
                ticket.sale();
            }
        }, "B").start();
        new Thread(() -> {
            for (int i = 1; i < 40; i++) {
                ticket.sale();
            }
        }, "C").start();
        new Thread(() -> {
            for (int i = 1; i < 40; i++) {
                ticket.sale();
            }
        }, "D").start();

    }

}

// OOP
class Ticket {
    // 属性，方法
    private int number = 30;

    // 买票方式
    // synchronized本质：队列，锁
    public synchronized void sale() {
        if (number > 0) {
            System.out.println(Thread.currentThread().getName() + "卖出了" + (number--) + "票，剩余：" + number);
        }
    }

}
