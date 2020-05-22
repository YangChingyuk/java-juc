package com.yqx.demo.single;

/**
 * 饿汉式
 *
 * @author YangChingyu-k
 * @date 2020/3/23 15:06
 */
public class ESingle {

    private static ESingle instance = new ESingle();

    private ESingle(){}

    public static ESingle getInstance() {
        return instance;
    }

}
