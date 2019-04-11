package com.company.mytest;

import java.io.DataInputStream;
import java.io.FileInputStream;
import java.lang.reflect.Method;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Scanner;


public class Test {

    public static void main(String[] args)throws InterruptedException{
        /*EqualExample e1 = new EqualExample(1,1,1);
        EqualExample e2 = new EqualExample(1,1,1);
        System.out.println(e1==e2);
        System.out.println(e1.equals(e2));
        HashSet<EqualExample> set = new HashSet<>();
        set.add(e1);
        set.add(e2);
        System.out.println(set.size());*/
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
        System.out.println("1");




    }
}
