package com.yqx.juc.stream;

import java.util.ArrayList;
import java.util.List;
import java.util.function.Predicate;
import java.util.stream.Stream;

/**
 * @author YangChingyu-k
 * @date 2020/3/4 14:22
 */
public class Test04 {

    public static void main(String[] args) {

        List<String> list = new ArrayList<>();
        list.add("abcd");
        list.add("abef");
        list.add("afeg");
        list.add("bvcd");
        list.add("gvde");

        Stream<String> stream = list.stream();
        stream.skip(2).limit(2).forEach(System.out::println);
        System.out.println("-------------");

        Stream<String> stream2 = list.stream();
        if (stream2.anyMatch(s -> s.contains("v"))) {
            System.out.println("TRUE");
        }
        System.out.println("-------------");

        Stream<String> stream3 = list.stream();
        Predicate<String> predicate = s -> s.contains("a");
        Predicate<String> predicate1 = s -> s.contains("e");
        if (stream3.anyMatch(predicate.and(predicate1))) {
            System.out.println("true");
        }

    }

}
