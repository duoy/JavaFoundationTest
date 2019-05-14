package com.company.mytest.Alogrithm_DataStruct;

import Algorithm_DataStruct.QuickSort;
import Algorithm_DataStruct.SortUtils;

public class Selection {

    //寻找nums数组中第k大的元素
    public static <T extends Comparable<T>> T solve(T[] nums,int k){
        int l =0;
        int r = nums.length-1;
        while(l<r){
            int j = partition(nums,l,r);

            if(j == k) {
                return nums[k];
            }
            else if(j > k){
                r = j - 1;
            }else{
                l = j+1;
            }
        }
        return nums[k];

    }
    private static <T extends Comparable<T>> int partition(T[] arr,int l,int r){
        int random = (int)Math.random()*(r-l+1)+1;
        SortUtils.swap(arr,l,random);
        T v = arr[l];

        int i = l+1;
        int j = r;

        while(true){
            while(l<=r && arr[i].compareTo(v)<0){
                ++i;
            }
            while(j>=l+1 && v.compareTo(arr[j])<0){
                --j;
            }
            if(i>j) {
                break;
            }
            SortUtils.swap(arr,i,j);
            ++i;
            --j;
        }
        SortUtils.swap(arr,l,j);
        return j;
    }

}
