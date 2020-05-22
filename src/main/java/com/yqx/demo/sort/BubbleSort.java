package com.yqx.demo.sort;

import java.util.Arrays;

/**
 * 冒泡排序
 *
 * @author YangChingyu-k
 * @date 2020/3/23 14:29
 */
public class BubbleSort {

    public static void main(String[] args) {
        int[] arr = {2,1,5,4,9,8,12,3};
        System.out.println(Arrays.toString(arr));
        sort(arr, arr.length);
        System.out.println(Arrays.toString(arr));
    }


    private static void sort (int[] arr, int num) {
        if (num <=1) {
            return;
        }
        for (int i=0; i<num - 1; i++) {
            // 标识，是否为有序状态
            boolean flag = false;
            for (int j=0; j<num - i - 1; j++) {
                if (arr[j] > arr[j+1]) {
                    int temp = arr[j];
                    arr[j] = arr[j+1];
                    arr[j + 1] = temp;
                    // 交换之后改变标识状态
                    flag = true;
                }
            }
            if (!flag) {
                break;
            }
        }
    }

}
