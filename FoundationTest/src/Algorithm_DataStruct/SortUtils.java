package Algorithm_DataStruct;

import java.lang.reflect.Method;
import java.util.Arrays;
import java.util.List;

public final class SortUtils {

    public static <T> void swap(T[] array,int i,int j){
        T tem = array[i];
        array[i] = array[j];
        array[j] = tem;
    }

    /**
     * This method checks if the first element is less than the other element
     * @param v -first element
     * @param w -other element
     * @param <T> -extends Comparable,in order to use the ComparaTo()
     * @return -if the first element is less than the second element, return true
     */
    public static <T extends Comparable<T>> boolean less(T v,T w){
        return v.compareTo(w)<0;
    }

    /**
     * Print list
     * @param toPrint - a list which should be printed
     */
    public static void print(List<?> toPrint){
        toPrint.stream()
                .map(Object::toString)
                .map(str -> str + " ")
                .forEach(System.out::print);
        System.out.println();
    }

    /**
     * Prints an array
     * @param toPrint -the array which should be printed
     */
    public static void print(Object[] toPrint){
        System.out.println(Arrays.toString(toPrint));
    }

    // 生成有n个元素的随机数组,每个元素的随机范围为[rangeL, rangeR]
    public static Integer[] generateRandomArray(int n, int rangeL, int rangeR) {

        assert rangeL <= rangeR;

        Integer[] arr = new Integer[n];

        for (int i = 0; i < n; i++)
            arr[i] = new Integer((int)(Math.random() * (rangeR - rangeL + 1) + rangeL));
        return arr;
    }

    // 生成一个近乎有序的数组
    // 首先生成一个含有[0...n-1]的完全有序数组, 之后随机交换swapTimes对数据
    // swapTimes定义了数组的无序程度:
    // swapTimes == 0 时, 数组完全有序
    // swapTimes 越大, 数组越趋向于无序
    public static Integer[] generateNearlyOrderedArray(int n, int swapTimes){

        Integer[] arr = new Integer[n];
        for( int i = 0 ; i < n ; i ++ )
            arr[i] = new Integer(i);

        for( int i = 0 ; i < swapTimes ; i ++ ){
            int a = (int)(Math.random() * n);
            int b = (int)(Math.random() * n);
            int t = arr[a];
            arr[a] = arr[b];
            arr[b] = t;
        }

        return arr;
    }

    //生成完全有序的数组
    public static Integer[] generateOrderedArray(int n){
        return generateNearlyOrderedArray(n,0);
    }
    //生成完全逆序的数组
    public static Integer[] generateInversedArray(int n){
        Integer[] arr = generateOrderedArray(n);
        for(int i=n/2-1;i>=0;i--){
            int j = n-i-1;
            swap(arr,i,j);
        }
        return arr;
    }

    // 判断arr数组是否有序
    public static boolean isSorted(Comparable[] arr){

        for( int i = 0 ; i < arr.length - 1 ; i ++ )
            if( arr[i].compareTo(arr[i+1]) > 0 )
                return false;
        return true;
    }

    // 测试sortClassName所对应的排序算法排序arr数组所得到结果的正确性和算法运行时间
    public static void testSort(String sortClassName, Comparable[] arr){

        // 通过Java的反射机制，通过排序的类名，运行排序函数
        try{
            // 通过sortClassName获得排序函数的Class对象
            Class sortClass = Class.forName(sortClassName);
            // 通过排序函数的Class对象获得排序方法
            Method sortMethod = sortClass.getMethod("sort",Comparable[].class);
            // 排序参数只有一个，是可比较数组arr
            Object[] params = new Object[]{arr};

            long startTime = System.currentTimeMillis();
            // 调用排序函数
            //System.out.println(sortMethod);

            sortMethod.invoke(sortClass.newInstance(),params);
            //sortMethod.invoke(null,params);
            long endTime = System.currentTimeMillis();

            assert isSorted( arr );
            System.out.println( sortClass.getSimpleName()+ " : " + (endTime-startTime) + "ms" );
            SortUtils.print(arr);
        }
        catch(Exception e){
            e.printStackTrace();
        }
    }
}
