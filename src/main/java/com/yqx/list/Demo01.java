package com.yqx.list;



import java.util.*;
import java.util.concurrent.BlockingQueue;

/**
 * @author YangChingyu-k
 * @date 2020/2/28 14:10
 */
public class Demo01 {

    public static void main(String[] args) {

        List<String> arrayList = new ArrayList<>();
        arrayList.add("A");
        arrayList.add("C");
        arrayList.add("D");
        arrayList.add("B");
        arrayList.sort(null);
        arrayList.forEach((s)->{System.out.println(s);});
        System.out.println("arrayList-----------");

        List<String> linkedList = new LinkedList<>();
        linkedList.add("A");
        linkedList.add("C");
        linkedList.add("B");
        linkedList.sort(null);
        linkedList.forEach((s)->{System.out.println(s);});
        System.out.println("linkedList-----------");

        Map<String, Object> hashMap = new HashMap<>();
        hashMap.put("A", 1);
        hashMap.put("B", 2);
        hashMap.put("C", 3);
        hashMap.forEach((k, v)->{System.out.println("key: " + k + " value: " + v);});
        System.out.println("hashMap-----------");

        Set<Object> hashSet = new HashSet<>();
        hashSet.add("A");
        hashSet.add("D");
        hashSet.add("C");
        hashSet.add("B");
        hashSet.forEach((s)->{System.out.println(s);});
        System.out.println("hashSet-----------");

        Map<String, String> treeMap = new TreeMap<String, String>(new Comparator<String>() {
            @Override
            public int compare(String o1, String o2) {
                return o1.compareTo(o2);
            }
        });
        treeMap.put("A", "aaaa");
        treeMap.put("C", "cccc");
        treeMap.put("B", "bbbb");
        treeMap.put("D", "dddd");
        treeMap.forEach((k, v)->{System.out.println("key: " + k + " value: " + v);});
        System.out.println("treeMap-----------");

        String[] str = {"A","B","C","D","E"};
        List<String> list = Arrays.asList(str);
        list.forEach((s)->{System.out.println(s);});
        System.out.println("list-----------");


        // Collections.sort();


    }

}
