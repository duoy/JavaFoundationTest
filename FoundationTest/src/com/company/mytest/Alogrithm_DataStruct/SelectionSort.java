package com.company.mytest.Alogrithm_DataStruct;

public class SelectionSort {

    public static void swapElements(int[] nums,int i,int j){
        int temp = nums[i];
        nums[i] = nums[j];
        nums[j] = temp;
    }
    public static int indexLowest(int[] nums,int start){
        int lowIndex = start;
        for(int i=start;start<nums.length;i++){
            if(nums[i]<nums[i+1])
                lowIndex = i;
        }
        return lowIndex;
    }

    public static void selectionSort(int[] nums){
        for(int i =0;i<nums.length;i++){
            int j =indexLowest(nums,i);
            swapElements(nums,i,j);
        }
    }
}
