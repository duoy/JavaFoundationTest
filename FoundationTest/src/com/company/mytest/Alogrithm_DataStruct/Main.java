package com.company.mytest.Alogrithm_DataStruct;

import Algorithm_DataStruct.SortUtils;

import java.util.Arrays;

public class Main {
    public static void main(String[] args) {

        int N = 20000;

        // 测试1 一般测试
        System.out.println("Test for random array, size = " + N + " , random range [0, " + N + "]");

        Integer[] arr1 = SortUtils.generateRandomArray(N, 0, N);
        Integer[] arr2 = Arrays.copyOf(arr1, arr1.length);
        Integer[] arr3 = Arrays.copyOf(arr1, arr1.length);

        SortUtils.testSort("com.company.mytest.Alogrithm_DataStruct.InsertionSort", arr1);
        SortUtils.testSort("bobo.algo.SelectionSort", arr2);
        SortUtils.testSort("bobo.algo.SelectionSort2", arr3);

        System.out.println();


        // 测试2 有序性更强的测试
        System.out.println("Test for more ordered random array, size = " + N + " , random range [0,3]");

        arr1 = SortUtils.generateRandomArray(N, 0, 3);
        arr2 = Arrays.copyOf(arr1, arr1.length);
        arr3 = Arrays.copyOf(arr1, arr1.length);

        SortUtils.testSort("bobo.algo.InsertionSort", arr1);
        SortUtils.testSort("bobo.algo.SelectionSort", arr2);
        SortUtils.testSort("bobo.algo.SelectionSort2", arr3);

        System.out.println();


        // 测试3 测试近乎有序的数组
        int swapTimes = 100;
        System.out.println("Test for nearly ordered array, size = " + N + " , swap time = " + swapTimes);

        arr1 = SortUtils.generateNearlyOrderedArray(N, swapTimes);
        arr2 = Arrays.copyOf(arr1, arr1.length);
        arr3 = Arrays.copyOf(arr1, arr1.length);

        SortUtils.testSort("bobo.algo.InsertionSort", arr1);
        SortUtils.testSort("bobo.algo.SelectionSort", arr2);
        SortUtils.testSort("bobo.algo.SelectionSort2", arr3);

        return;
    }

}
