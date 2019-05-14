package com.company.mytest.Alogrithm_DataStruct;

import Algorithm_DataStruct.SortUtils;

import java.util.Arrays;

public class InversionCount {

    public static <T extends Comparable<T>> int solve(T[] arr){
        int n = arr.length;
        return  solve(arr,0,n-1);

    }

    private static <T extends Comparable<T>> int solve(T[] arr,int l,int r){

        if (l >= r)
            return 0;

        int mid = l + (r-l)/2;
        // 求出 arr[l...mid] 范围的逆序数
        int res1 = solve(arr, l, mid);
        // 求出 arr[mid+1...r] 范围的逆序数
        int res2 = solve(arr, mid + 1, r);

        return res1 + res2 + merge(arr, l, mid, r);

    }

    private static <T extends Comparable<T>> int merge(T[] arr,int l,int mid,int r){


        T[] aux = Arrays.copyOfRange(arr, l, r+1);

        // 初始化逆序数对个数 res = 0
        int res = 0;
        // 初始化，i指向左半部分的起始索引位置l；j指向右半部分起始索引位置mid+1
        int i = l, j = mid+1;
        for( int k = l ; k <= r; k ++ ){
            if( i > mid ){  // 如果左半部分元素已经全部处理完毕
                arr[k] = aux[j-l];
                j ++;
            }
            else if( j > r ){   // 如果右半部分元素已经全部处理完毕
                arr[k] = aux[i-l];
                i ++;
            }
            else if( aux[i-l].compareTo(aux[j-l]) <= 0 ){  // 左半部分所指元素 <= 右半部分所指元素
                arr[k] = aux[i-l];
                i ++;
            }
            else{   // 右半部分所指元素 < 左半部分所指元素
                arr[k] = aux[j-l];
                j ++;
                // 此时, 因为右半部分k所指的元素小
                // 这个元素和左半部分的所有未处理的元素都构成了逆序数对
                // 左半部分此时未处理的元素个数为 mid - j + 1
                res += (mid - i + 1);
                //count += (mid - i +1);
            }
        }
        return res;

         /*
        T[] tmp = (T[]) new Comparable[arr.length];
        System.arraycopy(arr,l,tmp,l,r-l+1);
        int i = l;
        int j = mid+1;
        int k = l;
        int res = 0;

        while(i<=mid && j<=r){
            if(tmp[i].compareTo(tmp[j])<=0){
                arr[k++] = tmp[i++];
            }else{
                arr[k++] = tmp[j++];
                res += (mid-i+1);
            }
        }
        while(i<=mid){
            arr[k++] = tmp[i++];
        }
        while(j<=r){
            arr[k++] = tmp[j++];
        }
        return res;

          */

    }

    // 测试 InversionCount
    public static void main(String[] args) {

        int N = 5;

        // 测试1: 测试随机数组
        Integer[] arr = SortUtils.generateRandomArray(N, 0, 100);
        SortUtils.print(arr);
        System.out.println("Test Inversion Count for Random Array, n = " + N + " :" + solve(arr) );

        // 测试2: 测试完全有序的数组
        // 结果应该为0
        arr = SortUtils.generateOrderedArray(N);
        SortUtils.print(arr);
        System.out.println("Test Inversion Count for Ordered Array, n = " + N + " :" + solve(arr) );

        // 测试3: 测试完全逆序的数组
        // 结果应改为 N*(N-1)/2
        arr = SortUtils.generateInversedArray(N);
        SortUtils.print(arr);
        System.out.println("Test Inversion Count for Inversed Array, n = " + N + " :" + solve(arr) );

    }
}
