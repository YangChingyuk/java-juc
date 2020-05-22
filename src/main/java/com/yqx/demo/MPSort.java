package com.yqx.demo;

import java.util.Arrays;

/**
 * bubbleSort
 *
 * @author YangChingyu-k
 * @date 2020/2/17 16:44
 */
public class MPSort {

    public static void main(String[] args) {
        int[] arg = {2,1,33,22,44,55,4,6,8,7,9,10,15};
        System.out.println("结果：" + Arrays.toString(bubbleSort(arg)));
    }

    public static int[] bubbleSort(int[] arr){
        for(int i = 0; i < arr.length; i++){
            for(int j = 0; j < arr.length - i - 1; j++){
                boolean result = true;
                if (arr[j] < arr[j + 1]) {
                    result = false;
                }
                if (result) {
                    if(arr[j] > arr[j + 1]){
                        int temp = arr[j];
                        arr[j] = arr[j + 1];
                        arr[j + 1] = temp;
                    }
                }
            }
        }
        return arr;
    }

}
