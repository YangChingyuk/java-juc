package com.yqx.juc;

import java.util.concurrent.TimeUnit;

/**
 * 3、 增加了一个普通方法后！先执行发短信还是Hello？ 普通方法
 * 4、 两个对象，两个同步方法， 发短信还是 打电话？ // 打电话
 *
 * @author YangChingyu-k
 * @date 2020/2/26 16:13
 */
public class Test02 {

    public static void main(String[] args) {
        Phone phone = new Phone();
        Phone2 phone2 = new Phone2();

        new Thread(() -> {
            phone.sendSms();
        }, "A").start();
        try {
            TimeUnit.SECONDS.sleep(1);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        new Thread(() -> {
            phone2.call();
        }, "B").start();
    }
}

class Phone2 {

    public synchronized void sendSms () {
        try {
            TimeUnit.SECONDS.sleep(4);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        System.out.println("发短信");
    }

    public synchronized void call () {
        System.out.println("打电话");
    }

    // 这里没有锁，不是同步方法，不受锁的影响
    public void sayHello () {
        System.out.println("Hello");
    }
}
