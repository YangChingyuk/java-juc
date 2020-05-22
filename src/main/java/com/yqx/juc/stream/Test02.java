package com.yqx.juc.stream;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.Stream;

/**
 * @author YangChingyu-k
 * @date 2020/3/4 13:58
 */
public class Test02 {

    public static void main(String[] args) {
        List<String> list = new ArrayList<>();
        list.add("A");
        list.add("B");
        list.add("C");
        list.add("D");
        list.add("C");
        list.add("D");
        // 获取stream流对象
        Stream<String> stream = list.stream();
        stream.forEach(System.out::println);

        Stream<String> stream2 = list.stream();
        long count = stream2.count();
        System.out.println("count: " + count);

        Stream<String> stream3 = list.stream();
        stream3.distinct().forEach(System.out::println);

        System.out.println("----------");
        Stream<String> stream4 = list.stream();
        List<String> list1 = stream4.distinct().collect(Collectors.toList());
        list1.forEach(System.out::println);

    }

}
