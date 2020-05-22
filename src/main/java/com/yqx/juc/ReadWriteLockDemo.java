package com.yqx.juc;

import java.util.HashMap;
import java.util.Map;
import java.util.concurrent.locks.ReadWriteLock;
import java.util.concurrent.locks.ReentrantReadWriteLock;

/**
 * 读写锁
 *
 * @author YangChingyu-k
 * @date 2020/2/27 10:32
 */
public class ReadWriteLockDemo {

    public static void main(String[] args) {

        MyCacheLock cache = new MyCacheLock();

        // 写入
        for (int i=1;i<=5;i++) {
            final int temp = i;
            new Thread(()->{
                cache.put(temp+"", temp+"");
            }, String.valueOf(i)).start();
        }

        // 读取
        for (int i=1;i<=5;i++) {
            final int temp = i;
            new Thread(()->{
                cache.get(temp+"");
            }).start();
        }

    }
}

class MyCacheLock {

    private volatile Map<String, Object> map = new HashMap<>();

    // 读写锁：更加细粒度的控制
    private ReadWriteLock readWriteLock = new ReentrantReadWriteLock();

    // 写入：写入时只希望一个线程在写
    public void put(String key, Object value) {
        readWriteLock.writeLock().lock();
        try {
            System.out.println(Thread.currentThread().getName() + "写入" + key);
            map.put(key, value);
            System.out.println(Thread.currentThread().getName() + "写入OK");
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            readWriteLock.writeLock().unlock();
        }
    }

    // 读取：所有人可以同时读取
    public void get(String key) {
        readWriteLock.readLock().lock();
        try {
            System.out.println(Thread.currentThread().getName() + "读取" + key);
            Object o = map.get(key);
            System.out.println(Thread.currentThread().getName() + "读取OK");
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            readWriteLock.readLock().unlock();
        }
    }
}
