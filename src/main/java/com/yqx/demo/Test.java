package com.yqx.demo;


import com.alibaba.fastjson.JSONObject;

import java.text.DecimalFormat;
import java.text.SimpleDateFormat;
import java.util.*;

/**
 * @author YangChingyu-k
 * @date 2019/9/5 13:08
 */
public class Test {

    public static void main(String[] args) throws Exception {

        String str = "k1:v1;k2:v2;k3:v3";
        String[] s = str.split(";");
        System.out.println(Arrays.asList(s));
        List<String> list = Arrays.asList(s);
        list.forEach(System.out::println);
        System.out.println("----------------");

        Map<String, Object> map = new HashMap<>();
        for (String s1 : list) {
            String[] ss = s1.split(":");
            map.put(ss[0], ss[1]);
        }
        for (Map.Entry<String, Object> a : map.entrySet()) {
            System.out.println(a.getKey() + ":" + a.getValue());
        }
        System.out.println("map: " + map.get("k1"));

    }



}
