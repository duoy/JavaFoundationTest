package com.company.mytest;

import java.io.DataInputStream;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.UnsupportedEncodingException;
import java.lang.reflect.Method;
import java.time.Instant;
import java.util.*;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.ThreadPoolExecutor;


public class Test {
    private static int i = 1;
        //private static HashMap<Integer,String> map = new HashMap<>(2,0.75f);
    public int getNext(){
        return i++;
    }
    public static void main(String[] args)throws InterruptedException{
        /*EqualExample e1 = new EqualExample(1,1,1);
        EqualExample e2 = new EqualExample(1,1,1);
        System.out.println(e1==e2);
        System.out.println(e1.equals(e2));
        HashSet<EqualExample> set = new HashSet<>();
        set.add(e1);
        set.add(e2);
        System.out.println(set.size());*/
        /*
        Thread t = new Thread(new Runnable() {
            @Override
            public void run() {
                try{
                    Thread.sleep(2000);
                }catch(InterruptedException e){
                    throw new RuntimeException(e);
                }
                System.out.println("2");
            }
        });
        t.start();
        t.join();
        System.out.println("1");*/

        /*Test test = new Test();
        Test test1 = new Test();
        test.getNext();
        test1.getNext();
        System.out.println(Test.i);
        */
        /*
        try{
            String str1 = "中文";
            byte[] byte1 = str1.getBytes();
            System.out.println(byte1);
            byte[] bytes = str1.getBytes("UTF-8");
            System.out.println(bytes);
            String str2 = new String(bytes,"UTF-8");
            System.out.println(str2);
        }catch (UnsupportedEncodingException e){
            e.printStackTrace();
        }


        System.out.println(Instant.now());
        */

        int[] arr = new int[]{1,2,3,4,5};
        int[] a1 = Arrays.copyOf(arr,arr.length);
        for(int i:a1)
            System.out.print(i+" ");
        System.out.println();

        int[] a2 = Arrays.copyOfRange(arr,2,arr.length);
        for(int i:a2)
            System.out.print(i+" ");
        System.out.println();

        int[] num = new int[arr.length];
        System.arraycopy(arr,0,num,0,arr.length);
        for(int i:num)
            System.out.print(i+" ");






    }
}
