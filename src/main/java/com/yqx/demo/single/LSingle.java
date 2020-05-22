package com.yqx.demo.single;

/**
 * 懒汉式
 *
 * @author YangChingyu-k
 * @date 2020/3/23 15:09
 */
public class LSingle {

    private static LSingle instance;

    private LSingle(){}

    public static LSingle getInstance() {
        if (instance == null) {
            instance = new LSingle();
        }
        return instance;
    }

}
