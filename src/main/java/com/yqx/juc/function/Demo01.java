package com.yqx.juc.function;

import java.util.function.Function;

/**
 * Function函数型接口，有一个输入参数，有一个输出
 * 只要是函数型接口 可以 用lombda表达式简化
 *
 * @author YangChingyu-k
 * @date 2020/2/27 15:09
 */
public class Demo01 {

    public static void main(String[] args) {

        // Function<String, String> function = new Function<String, String>() {
        //     @Override
        //     public String apply(String str) {
        //         return str;
        //     }
        // };

        Function<String, String> function = (str)->{return str;};
        System.out.println(function.apply("ABC"));

    }

}
