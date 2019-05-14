package Algorithm_DataStruct;

import java.util.Arrays;

public class IndexMaxHeap <T extends Comparable<T>> {
    private T[] data;   //数据
    private int[] indexes;  //索引,存储在堆中
    private int capacity;
    private int size;

    public IndexMaxHeap(int capacity){
        data = (T[]) new Comparable[capacity+1];
        indexes = new int[capacity+1];
        this.capacity = capacity;
        size = 0;
    }

    //返回堆中元素个数
    public int getSize(){
        return size;
    }
    //判断堆是否为空
    public boolean isEmpty(){
        return size==0;
    }
    // 交换索引堆中的索引i和j
    private void swapIndexes(int i, int j){
        int t = indexes[i];
        indexes[i] = indexes[j];
        indexes[j] = t;
    }


    //向最大索引堆中添加元素，新元素索引为index，元素值为v
    // index从 0 开始索引
    public void insert(int index,T v){
        if(size + 1 <= capacity && (index+1 >=1 && index+1 <= capacity)){
            index+=1;
            data[index] = v;
            indexes[++size] = index;
            shiftUp(size);
        }
    }

    //从最大索引堆中取出最大元素
    public T popMax(){
        if(size<1)
            return null;
        T res = data[indexes[1]];
        swapIndexes(1,size--);
        shiftDown(1);

        return res;
    }

    //从最大索引堆中取出堆顶元素的索引
    public int popMaxIndex(){
        assert size >0;

        int res = indexes[1] - 1;
        swapIndexes(1,size--);
        shiftDown(1);

        return res;
    }

    //获取最大索引堆中的堆顶元素
    public T getMax(){
        if(size<1)
            return null;
        return data[indexes[1]];
    }

    //获取最大索引堆的堆顶元素的索引
    public int getMaxIndex(){
        assert size > 0;
        return indexes[1]-1;
    }

    //获取最大索引堆中索引为i的元素
    public T getData(int index){
        if(index+1 >=1 && index+1 <=capacity){
            return data[index+1];
        }
        return null;
    }

    //将最大索引堆中索引为index 的元素修改为 vv
    public void change(int index,T vv){
        index+=1;
        data[index] = vv;

        //找到 indexes[j]=index,j表示data[index]在堆中存储位置；之后shiftUp(j),再shiftDown(j)
        for(int j=1;j<=size;j++){
            if(indexes[j] == index){
                shiftUp(j);
                shiftDown(j);
                return;
            }
        }
    }

    // 索引堆中, 数据之间的比较根据data的大小进行比较, 但实际操作的是索引indexes
    private void shiftUp(int k){
        while( k>1 && data[indexes[k/2]].compareTo(data[indexes[k]])<0){
            swapIndexes(k/2,k);
            k /= 2;
        }
    }
    //索引堆中，根据 data 的大小进行比较，但实际操作的是 indexes
    private void shiftDown(int k){
        while( 2*k <=size){
            int j = 2*k;
            if( j+1 <=size && data[indexes[j+1]].compareTo(data[indexes[j]])>0){
                j++;
            }
            if(data[indexes[k]].compareTo(data[indexes[j]]) >= 0)
                break;
            swapIndexes(k,j);
            k = j;
        }
    }

    // 注意:这个测试在向堆中插入元素以后, 不进行extract操作有效
    public boolean testIndexes(){

        int[] copyIndexes = new int[size+1];

        for( int i = 0 ; i <= size ; i ++ )
            copyIndexes[i] = indexes[i];

        copyIndexes[0] = 0;
        Arrays.sort(copyIndexes);

        // 在对索引堆中的索引进行排序后, 应该正好是1...count这count个索引
        boolean res = true;
        for( int i = 1 ; i <= size ; i ++ )
            if( copyIndexes[i-1] + 1 != copyIndexes[i] ){
                res = false;
                break;
            }

        if( !res ){
            System.out.println("Error!");
            return false;
        }

        return true;
    }

    // 测试 IndexMaxHeap
    public static void main(String[] args) {

        int N = 10;
        IndexMaxHeap<Integer> indexMaxHeap = new IndexMaxHeap<Integer>(N);
        System.out.println("indexes为： ");
        for( int i = 0 ; i < N ; i ++ )
            indexMaxHeap.insert( i , (int)(Math.random()*N) );
        for(int i:indexMaxHeap.indexes)
            System.out.print(i+" ");
        System.out.println();
        System.out.println("data为： ");
        SortUtils.print(indexMaxHeap.data);
        assert indexMaxHeap.testIndexes();
    }



}
