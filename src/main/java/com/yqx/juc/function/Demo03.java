package com.yqx.juc.function;

import java.util.function.Consumer;

/**
 * Consumer 消费型接口: 只有输入，没有返回值
 *
 * @author YangChingyu-k
 * @date 2020/2/27 15:20
 */
public class Demo03 {

    public static void main(String[] args) {

        // Consumer<String> consumer = new Consumer<String>() {
        //     @Override
        //     public void accept(String s) {
        //         System.out.println(s);
        //     }
        // };

        Consumer<String> consumer = (s)->{System.out.println(s);};
        consumer.accept("ABC");
    }

}
