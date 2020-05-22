package com.yqx.juc;

import java.util.concurrent.Callable;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.FutureTask;

/**
 * Callable线程 (结果有缓存)
 *
 * @author YangChingyu-k
 * @date 2020/2/26 17:31
 */
public class CallableTest {

    public static void main(String[] args) throws ExecutionException, InterruptedException {
        Mythread mythread = new Mythread();
        FutureTask futureTask = new FutureTask(mythread);

        new Thread(futureTask, "A").start();
        new Thread(futureTask, "B").start();

        Integer o = (Integer)futureTask.get();  // 这个get方法可能产生阻塞，一半放到最后一行执行；
        // 或者使用异步通信来处理
        System.out.println(o);

    }

}

class Mythread implements Callable<Integer> {

    @Override
    public Integer call() throws Exception {
        System.out.println("call()");
        return 1024;
    }
}
