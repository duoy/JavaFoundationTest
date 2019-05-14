package Algorithm_DataStruct;

public class QuickSort implements Sort {
    @Override
    public <T extends Comparable<T>> T[] sort(T[] array) {
        //quickSort(array,0,array.length-1);
        quickSort3ways(array,0,array.length-1);
        return array;
    }

    private static <T extends Comparable<T>> void quickSort(T[] arr,int left,int right){
        // 对于小规模数组, 使用插入排序
        /*if( right - left <= 15 ){
            InsertionSort.sort(arr, left, right);
            return;
        }*/

        if(left<right) {
            int pivot = partition(arr, left, right);
            quickSort(arr, left, pivot - 1);
            quickSort(arr, pivot + 1, right);
        }

    }
    private static <T extends Comparable<T>> void quickSort3ways(T[] arr,int l,int r){
        // 对于小规模数组, 使用插入排序
        /*if( r - l <= 15 ){
            InsertionSort.sort(arr,l,r);
            return;
        }*/
        if(l<r){
            /*
            SortUtils.swap(arr,l,(int)Math.random()*(r-l+1)+l);
            T v = arr[l];

            int lt = l; // lt指向最后一个小于v的位置 arr[l+1...lt]<v
            int gt = r+1;   //gt指向第一个大于v的位置 arr[gt...r]>v
            int i =l+1; //arr[lt+1...i] = v
            while(i<gt){
                if(SortUtils.less(arr[i],v)){
                    SortUtils.swap(arr,i,lt+1);
                    ++i;
                    ++lt;
                }else if(SortUtils.less(v,arr[i])){
                    SortUtils.swap(arr,i,gt-1);
                    --gt;
                }else{
                    ++i;
                }
            }
            SortUtils.swap(arr,l,lt);

            quickSort3ways(arr,l,lt-1);
            quickSort3ways(arr,gt,r);
            */

            //   arr[l...lt-1] < v
            //   arr[lt...i] = v    i<=gt
            //   arr[gt+1...r] > v

            int lt = l, i = l + 1, gt = r;  // lt始终指向v(即最后一个小于v的下一位置
                                            // gt始终指向大于v的前一个元素
            T v =arr[l];
            while (i <= gt) {
                int cmp = arr[i].compareTo(v);
                if (cmp < 0) {
                    SortUtils.swap(arr, lt++, i++);
                } else if (cmp > 0) {
                    SortUtils.swap(arr, i, gt--);
                } else {
                    i++;
                }
            }
            quickSort3ways(arr, l, lt - 1);
            quickSort3ways(arr, gt + 1, r);


        }


    }

    //对arr[l...r]进行partition操作
    //返回p,使得 arr[l...p-1]<arr[p]; arr[p+1...r]>arr[p]
    private static <T extends Comparable<T>> int partition(T[] arr,int l,int r){
        SortUtils.swap(arr,l,(int) Math.random()*(r-l+1)+l);
        T v = arr[l];
        int i = l+1;
        int j = r;
        // arr[l+1...i) <= v; arr(j...r] >= v
        while (true){
            while(i<=r && SortUtils.less(arr[i],v)){
                ++i;
            }
            while(j>=l+1 && SortUtils.less(v,arr[j])){
                --j;
            }
            if(i>j){
                break;
            }
            SortUtils.swap(arr,i,j);
            ++i;
            --j;

        }
        // j最后一个小于等于v 的位置 ；   i指向第一个大于等于v 的位置
        SortUtils.swap(arr,l,j);

        return j;

    }
    public static void main(String[] args) {

        int N = 100000;
        Integer[] arr = SortUtils.generateRandomArray(N, 0, 100000);
        SortUtils.print(arr);

        SortUtils.testSort("Algorithm_DataStruct.QuickSort", arr);
    }

}
