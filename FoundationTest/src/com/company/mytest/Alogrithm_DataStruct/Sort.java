package com.company.mytest.Alogrithm_DataStruct;

/**
 * 待排序的元素需要判断两个元素的大小，因此需实现Comparable接口，该接口有compareTo()，用了
 *
 * @param <T>
 */
public abstract class Sort<T extends Comparable<T>> {
    public abstract void sort(T[] nums);
    public boolean less(T v,T w){
        return v.compareTo(w)<0;
    }
    public void swap(T[] nums,int i,int j){
        T temp = nums[i];
        nums[i] = nums[j];
        nums[j] = temp;
    }
}

class SlectionSort<T extends Comparable<T>> extends Sort<T>{

    @Override
    public void sort(T[] nums) {
        int N = nums.length;
        for(int i=0;i<N;i++){
            int min = i;
            for(int j=i+1;j<N;j++){
                if(less(nums[j],nums[min])){
                    min = j;
                }
            }
            swap(nums,i,min);
        }
    }
}
class BubbleSort1<T extends Comparable<T>> extends Sort<T>{
    @Override
    public void sort(T[] nums){
        int N = nums.length;
        boolean isSorted = false;
        for(int i=N-1;i>0 && !isSorted;i--){
            isSorted = true;
            for(int j=0;j<i;j++){
                if(less(nums[j+1],nums[j]))
                    swap(nums,j,j+1);
            }
        }
    }
}

 class InsertionSort<T extends Comparable<T>> extends Sort<T> {

    @Override
    public void sort(T[] nums) {
        int N = nums.length;
        for (int i = 1; i < N; i++) {
            //寻找nums[i]合适的插入位置
            for (int j = i; j > 0 && less(nums[j], nums[j - 1]); j--) {
                swap(nums, j, j - 1);
            }
        }
    }
    public void sort(T[] nums,int chooise){
        for(int i=1;i<nums.length;i++){
            T e = nums[i];
            int j;
            for(j=i;j>0 && less(e,nums[j-1]);j--){
                nums[j] = nums[j-1];
            }
            nums[j] = e;
        }
    }
    //对arr[l...r]进行插入排序
    public void sort(T[] arr,int l,int r){
        for(int i=l+1;i<=r;i++){
            T tmp = arr[i];
            int j = i;
            for(;j>l && less(tmp,arr[j-1]);j--){
                arr[j] = arr[j-1];
            }
            arr[j] = tmp;
        }
    }

}

class ShellSort<T extends Comparable<T>> extends Sort<T>{

    @Override
    public void sort(T[] nums) {
        int N = nums.length;
        int h = 1;
        while(h < N/3)
            h = 3*h +1;
        while(h>=1){
            for(int i=h;i<N;i++){

                //for(int j=i;j>=h && less(nums[j],nums[j-h]);j-=h) swap(nums,j,j-h);
                T e = nums[i];
                int j = i;
                for(;j>=h && less(e,nums[j-h]);j-=h){
                    nums[j] = nums[j-h];
                }
                nums[j] = e;
            }
            h = h/3;
        }
    }
}


