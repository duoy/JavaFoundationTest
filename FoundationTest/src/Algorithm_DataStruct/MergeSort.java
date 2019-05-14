package Algorithm_DataStruct;

public class MergeSort implements Sort{
    @Override
    public <T extends Comparable<T>> T[] sort(T[] unsorted) {
        T[] tmp = (T[]) new Comparable[unsorted.length];

        // 1. 使用递归 自顶向下
        mergeSort(unsorted,tmp,0,unsorted.length-1);

        // 2. 自底向上
        /*for(int size=1;size<=unsorted.length;size+=size){
            for(int i=0;i<unsorted.length-size;i+=size+size){
                //对 arr[i...i+size-1] 和 arr[i+size...i+2*size-1] 进行归并
                merger(unsorted,tmp,i,i+size-1,Math.min(i+2*size-1,unsorted.length-1));
            }
        }*/
        return unsorted;
    }
    private static <T extends Comparable<T>> void mergeSort(T[] arr,T[] tmp,int left,int right){
        if(left < right){
            int mid = left + (right - left)/2;
            mergeSort(arr,tmp,left,mid);
            mergeSort(arr,tmp,mid+1,right);

            //插入排序的改进
            /**
             * 1. if(arr[mid]<=arr[mid+1]   再进行merge
             * 2. 当数据量较小时，用插入排序 替换掉 merge
             *      if(right - left < xx)   InsertionSort(arr,l,r)
             */

            merger(arr,tmp,left,mid,right);
        }

    }

    private static <T extends Comparable<T>> void merger(T[] arr,T[] tmp,int left,int mid,int right){
        System.arraycopy(arr,left,tmp,left,right-left+1);
        int i = left;
        int j = mid+1;
        int k = left;

        while(i<=mid && j<=right){
            if(tmp[i].compareTo(tmp[j])<=0){
                arr[k++] = tmp[i++];
            }else{
                arr[k++] = tmp[j++];
            }
        }
        while(i<=mid){
            arr[k++] = tmp[i++];
        }
        while(j<=right){
            arr[k++] = tmp[j++];
        }
    }


    public static void main(String[] args) {

        int N = 100000;
        Integer[] arr = SortUtils.generateRandomArray(N, 0, 100000);
        SortUtils.print(arr);
        SortUtils.testSort("Algorithm_DataStruct.MergeSort", arr);
    }

}
