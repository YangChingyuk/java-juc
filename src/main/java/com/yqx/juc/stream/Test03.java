package com.yqx.juc.stream;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Stream;

/**
 * @author YangChingyu-k
 * @date 2020/3/4 14:15
 */
public class Test03 {

    public static void main(String[] args) {

        List<String> list = new ArrayList<>();
        list.add("abcd");
        list.add("abef");
        list.add("afeg");
        list.add("bvcd");
        list.add("gvde");

        Stream<String> stream = list.stream();
        stream.map(s -> s.toUpperCase()).filter(s -> s.contains("A")).forEach(System.out::println);

    }

}
