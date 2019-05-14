package Algorithm_DataStruct;

import java.util.Arrays;

public class MaxHeap<T extends Comparable<T>> {
    private T[] heapData;
    private int capacity;   //容量（最大可存储元素个数）
    private int size;   //堆中实际存储元素个数

    public MaxHeap(int capacity){
        heapData = (T[]) new Comparable[capacity+1];    //从1开始存储
        this.capacity = capacity;
        size = 0;
    }
    public MaxHeap(T[] arr){
        int n = arr.length;
        heapData = (T[]) new Comparable[n+1];

        capacity = n;
        for(int i = 0;i<n;i++){
            heapData[i+1] = arr[i];
        }
        size = n;

        // heapify 过程创建最大堆  从1开始存储，所以i>=1
        for(int i=size/2;i>=1;i--){
            shiftDown(i);
        }
    }
    //返回堆中元素个数
    public int getSize(){
        return size;
    }
    //判断堆是否为空
    public boolean isEmpty(){
        return size==0;
    }
    private void grow() {
        //if(minCapacity < capacity)
        capacity *= 2;
        heapData = Arrays.copyOf(heapData,capacity+1);
    }

    //向堆中插入元素
    public void insert(T v){
        if(size+1 > capacity){
            grow();   //增加其容量
            return ;
        }
        heapData[++size] = v;
        shiftUp(size);
    }
    public T popMax(){
        if(size > 0){
            T max = heapData[1];
            SortUtils.swap(heapData,1,size--);
            shiftDown(1);
            return max;

        }
        return null;
    }
    public T getMax(){
        if(size<1)
            return null;
        return heapData[1];
    }
    //层次打印堆
    public void printHeap(){

    }

    //上浮
    private void shiftUp(int k){
        while(k>1 && heapData[k/2].compareTo(heapData[k])<0){
           SortUtils.swap(heapData,k/2,k);
           k /= 2;
        }
    }
    //从1开始，parent (i）= i/2  left Child(i) = 2*i;  right child(i) = 2*i+1; 最后一个非叶子结点 size/2,最后一个元素 size
    //下浮
    private void shiftDown(int k){
        while(2*k <= size){
            int j = 2*k;

            if(j+1 <= size && heapData[j+1].compareTo(heapData[j])>0)
                j++;
            if(heapData[k].compareTo(heapData[j])>=0)
                break;
            SortUtils.swap(heapData,k,j);   //可以替换为 T e = heapData[k];  heapData[k] = heapData[j];
            k = j;
        }
        // heapData[k] = e;
    }

    //从0开始，parent (i）= （i-1)/2  left Child(i) = 2*i+1;  right child(i) = 2*i+2; 最后一个非叶子结点 (n-1 -1)/2，最后一个元素 n-1
    public static <T extends Comparable<T>> void shiftDown(T[] arr,int n,int k){
        T e = arr[k];
        while(2*k+1 < n ){
            int j = 2*k +1;
            if(j+1<n && arr[j+1].compareTo(arr[j])>0)
                j++;
            if(arr[k].compareTo(arr[j])>=0)
                break;
            arr[k] = arr[j];
            //SortUtils.swap(arr,k,j);
            k = j;
        }
        arr[k] = e;
    }

    //将所有元素依次添加到堆中，再将所有元素从堆中取出，时间复杂度 O(nlogn)
    public static <T extends Comparable<T>> void heapSort1(T[] arr){
        int n = arr.length;
        MaxHeap<T> maxHeap = new MaxHeap<T>(n);
        for(T v:arr)
            maxHeap.insert(v);
        for(int i=n-1;i>=0;i--)
            arr[i] = maxHeap.popMax();
    }

    //对整个数组使用heapSort2排序，借组heapify过程创建堆
    public static <T extends Comparable<T>> void heapSort2(T[] arr){
        MaxHeap<T> maxHeap = new MaxHeap<T>(arr);
        int n = arr.length;
        for(int i = n-1; i>=0; i--){
            arr[i] = maxHeap.popMax();
        }

    }

    // 不使用额外堆空间，直接对原数组进行排序
    public static <T extends Comparable<T>> void heapSort(T[] arr){
        int n = arr.length;
        //用 heapify 过程将arr转为最大堆,arr从0开始存储；size = n-1 为最后一个元素位置； 最后一个非叶子结点 (size-1)/2
        for(int i=(n-1-1)/2;i>=0;i--){
            shiftDown(arr,n,i);
        }
        for(int i = n-1;i>0;i--){
            SortUtils.swap(arr,0,i);
            shiftDown(arr,i,0);
        }

    }

    /**
     * heapSort1 : 将 n 个元素逐个插入到一个空堆中，算法复杂度是 O(nlogn)
     *
     *heapSort2: 通过数组创建堆，heapify的过程，算法复杂度为 O(n)
     *
     * heapSort: 不需要借助堆的额外空间，直接对原数组进行heapify（调用shiftDown),然后 循环 将最大元素swap( arr[0],n-1),再进行heapify将剩下的 n-1 个元素进行heapify,直到i<=0
     */


    // 测试 MaxHeap
    public static void main(String[] args) {

        /*
        MaxHeap<Integer> maxHeap = new MaxHeap<Integer>(100);

        int N = 100; // 堆中元素个数
        int M = 100; // 堆中元素取值范围[0, M)
        for( int i = 0 ; i < N ; i ++ )
            maxHeap.insert( new Integer((int)(Math.random() * M)) );

        Integer[] arr = new Integer[N];
        // 将maxheap中的数据逐渐使用extractMax取出来
        // 取出来的顺序应该是按照从大到小的顺序取出来的
        for( int i = 0 ; i < N ; i ++ ){
            arr[i] = maxHeap.popMax();
            System.out.print(arr[i] + " ");
        }
        System.out.println();
        //从小到大输出
        for(int i = N-1;i>=0;i--){
            arr[i] = maxHeap.popMax();
        }


        // 确保arr数组是从大到小排列的
        for( int i = 1 ; i < N ; i ++ )
            assert arr[i-1] >= arr[i];

         */
        int N = 100000;
        Integer[] arr = SortUtils.generateRandomArray(N, 0, 100000);
        Integer[] arr1 = arr;
        SortUtils.print(arr);
        long start = System.currentTimeMillis();
        MaxHeap.heapSort(arr);
        long end = System.currentTimeMillis();
        System.out.println(end - start+ " ms");



    }
}
