package com.company.mytest;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.Comparator;

/**
 * Collections类方法测试排序
 */
public class CollectionsMethTest {
    public static void main(String[] args){
        ArrayList<Integer> arrayList = new ArrayList<>();
        arrayList.add(-1);
        arrayList.add(3);
        arrayList.add(3);
        arrayList.add(-5);
        arrayList.add(7);
        arrayList.add(4);
        arrayList.add(-9);
        arrayList.add(-7);
        System.out.println("原始数组:");
        System.out.println(arrayList);

        Integer[] integers = arrayList.toArray(new Integer[0]);
        System.out.println("toArray() : "+integers);
        for(int i:integers)
            System.out.println(i);


        //void reverse(List list)   反转
        Collections.reverse(arrayList);
        System.out.println("Collections.reverse(arrayList):");
        System.out.println(arrayList);

        Collections.rotate(arrayList,4);
        System.out.println("Collections.rotate(arrayList, 4):");
        System.out.println(arrayList);

        //void sort(List list)  按自然排序的升序排序
        Collections.sort(arrayList);
        System.out.println("Collections.sort(arrayList):");
        System.out.println(arrayList);

        //void shuffle(List list)   随机排序
        Collections.shuffle(arrayList);
        System.out.println("Collections.shuffle(arrayList):");
        System.out.println(arrayList);

        //void swao(List list,int i ,int j) 交换两个索引位置的元素
        Collections.swap(arrayList,2,5);
        System.out.println("Collections.swap(arrayList, 2, 5):");
        System.out.println(arrayList);

        //定制排序的用法
        Collections.sort(arrayList, new Comparator<Integer>() {
            @Override
            public int compare(Integer o1, Integer o2) {
                return o2.compareTo(o1);
            }
        });
        System.out.println("定制排序后：");
        System.out.println(arrayList);
        arrayList.sort((Integer::compareTo));


        System.out.println("Collections.max(arrayList):");
        System.out.println(Collections.max(arrayList));

        System.out.println("Collections.min(arrayList):");
        System.out.println(Collections.min(arrayList));

        System.out.println("Collections.replaceAll(arrayList, 3, -3):");
        Collections.replaceAll(arrayList, 3, -3);
        System.out.println(arrayList);

        System.out.println("Collections.frequency(arrayList, -3):");
        System.out.println(Collections.frequency(arrayList, -3));


        System.out.println("Collections.binarySearch(arrayList, 7):");
        // 对List进行二分查找，返回索引，List必须是有序的
        Collections.sort(arrayList);
        System.out.println(Collections.binarySearch(arrayList, 7));


    }
}
