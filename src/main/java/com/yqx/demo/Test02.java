package com.yqx.demo;

/**
 * @author YangChingyu-k
 * @date 2020/3/22 19:36
 */
public class Test02 {

    public static void main(String[] args) {
        String s1 = "Programming";
        String s2 = new String("Programming");
        String s3 = "Program" + "ming";
        System.out.println(s1 == s2);
        System.out.println(s1 == s3);
        System.out.println(s1 == s1.intern());
    }

}
