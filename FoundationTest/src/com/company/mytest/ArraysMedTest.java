package com.company.mytest;

import java.util.Arrays;

/**
 * Arrays方法测试
 *  sort（）
 *  binarySearch()
 *  equals()
 *  fill()
 *  asList()
 *  toString()
 *  copyOf()
 */
public class ArraysMedTest {
    public static void main(String[] args){
        /**
         * 排序
         */
        int[] a = {1,3,2,7,6,5,4,9};
        Arrays.sort(a);
        System.out.println("Arrays.sort(a):"+a);
        System.out.println();

        //sort(int[]a,int fromIndex,int toIndex)
        int b[] = { 1, 3, 2, 7, 6, 5, 4, 9 };
        Arrays.sort(b, 2, 6);
        System.out.println("Arrays.sort(b, 2, 6):"+b);
        System.out.println();

        // *************比较 equals****************
        char[] e = { 'a', 'f', 'b', 'c', 'e', 'A', 'C', 'B' };
        char[] f = { 'a', 'f', 'b', 'c', 'e', 'A', 'C', 'B' };
        System.out.println("Arrays.equals(e, f):" + Arrays.equals(e, f));

        // *************填充fill(批量初始化)****************
        int[] g = { 1, 2, 3, 3, 3, 3, 6, 6, 6 };
        // 数组中所有元素重新分配值
        Arrays.fill(g, 3);
        System.out.println("Arrays.fill(g, 3)："+g);
        // 输出结果：333333333
        System.out.println();

        int[] h = { 1, 2, 3, 3, 3, 3, 6, 6, 6, };
        // 数组中指定范围元素重新分配值
        Arrays.fill(h, 0, 2, 9);
        System.out.println("Arrays.fill(h, 0, 2, 9);："+h);

        // copyOf 方法实现数组复制,h为数组，6为复制的长度
        int[] c = { 1, 2, 3, 3, 3, 3, 6, 6, 6, };
        int i[] = Arrays.copyOf(c, 6);
        System.out.println("Arrays.copyOf(h, 6);："+i);// 输出结果：123333

        // copyOfRange将指定数组的指定范围复制到新数组中
        int j[] = Arrays.copyOfRange(h, 6, 11);
        System.out.println("Arrays.copyOfRange(h, 6, 11)："+j);
        // 输出结果66600(h数组只有9个元素这里是从索引6到索引11复制所以不足的就为0)

        System.out.println();

    }
}
