package Algorithm_DataStruct;



public class SelectionSort implements Sort {
    @Override
    public <T extends Comparable<T>> T[] sort(T[] arr) {
        int N = arr.length;
        for(int i=0;i<N-1;i++){
            int min = i;
            for(int j=i+1;j<N;j++){
                if(SortUtils.less(arr[j],arr[min]))
                    min = j;
            }
            SortUtils.swap(arr,i,min);
        }
        return arr;
    }

    public static void main(String[] args) {

        int N = 100000;
        Integer[] arr = SortUtils.generateRandomArray(N, 0, 100000);
        SortUtils.print(arr);
        SortUtils.testSort("Algorithm_DataStruct.SelectionSort", arr);
    }
}
