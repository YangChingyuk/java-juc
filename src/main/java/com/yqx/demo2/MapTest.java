package com.yqx.demo2;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * @author YangChingyu-k
 * @date 2019/9/24 10:44
 */
public class MapTest {

    public static void main(String[] args) {

        List<String> list = new ArrayList<>();
        list.add("A");
        list.add("B");
        list.add("C");

        StringBuffer sb = new StringBuffer();
        for (String s : list) {
            sb.append(s).append(",");
        }
        System.out.println("结果：" + (sb.deleteCharAt(sb.length()-1).toString()));
    }

}
