package Algorithm_DataStruct;

public class InsertionSort implements Sort{
    @Override
    public <T extends Comparable<T>> T[] sort(T[] nums) {
        int N = nums.length;
        for(int i = 1;i<N;i++){
            T e = nums[i];
            int j;
            for(j=i;j>0 && SortUtils.less(e,nums[j-1]);j--){
                nums[j] = nums[j-1];
            }
            nums[j] = e;
        }
        return  nums;
    }

    //对arr[l...r]进行插入排序
    public static <T extends Comparable<T>> void sort(T[] arr,int l,int r){
        for(int i=l+1;i<=r;i++){
            T tmp = arr[i];
            int j = i;
            for(;j>l && SortUtils.less(tmp,arr[j-1]);j--){
                arr[j] = arr[j-1];
            }
            arr[j] = tmp;
        }
    }

    public static void main(String[] args) {

        int N = 100000;
        Integer[] arr = SortUtils.generateRandomArray(N, 0, 100000);
        SortUtils.print(arr);
        SortUtils.testSort("Algorithm_DataStruct.InsertionSort", arr);
    }


}
