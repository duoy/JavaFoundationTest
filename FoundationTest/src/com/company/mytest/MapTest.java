package com.company.mytest;

import java.util.Collection;
import java.util.HashMap;
import java.util.Map;
import java.util.Set;

public class MapTest {
    public static void main(String[] args){
        //测试HashMap的多线程put时死循环问题；JDK1.8已经解决了HashMap进行put操作时死循环问题
        /*
        map.put(5,"C");
        new Thread("Thread1"){
            public void run(){
                map.put(7,"B");
                System.out.println(map);
            }
        }.start();
        new Thread("Thread2"){
            public void run(){
                map.put(3,"A");
                System.out.println(map);
            }
        }.start();
        */
        HashMap<String,String> map = new HashMap<>();
        //键不能重复，值可以重复
        map.put("san","张三");
        map.put("si","李四");
        map.put("wu","王五");
        map.put("wang","老王");
        map.put("wang","老王2");  //老王被覆盖
        map.put("lao", "老王");
        System.out.println("-------直接输出hashmap:-------");
        System.out.println(map);
        /**
         * 遍历HashMap
         */
        // 1. 获取Map中所有键
        System.out.println("-------foreach获取Map中所有的键:------");
        Set<String> keys=map.keySet();
        for(String key:keys){
            System.out.println(key+" ");
        }
        System.out.println();
        // 2.获取Map中所有值
        System.out.println("-------foreach获取Map中所有的值:------");
        Collection<String> values=map.values();
        for(String value:values){
            System.out.println(value+" ");
        }
        System.out.println();//换行
        // 3.得到key的值的同时得到key所对应的值
        System.out.println("-------得到key的值的同时得到key所对应的值:-------");
        Set<String> key2 = map.keySet();
        for(String key:keys){
            System.out.println(key+": "+map.get(key)+" ");
        }
        /**
         * 另外一种不常用的遍历方式
         */
        //当调用put(key,value)时，首先会将key和value封装到Entry这个静态内部类中，再把Entry对象添加到数组中
        Set<Map.Entry<String,String>> entrys = map.entrySet();
        for(Map.Entry<String,String> entry:entrys){
            System.out.println(entry.getKey()+"- -"+entry.getValue());
        }

        //HashMap其他常用方法
        System.out.println("map.size() : "+map.size());
        System.out.println("map.isEmpty() : "+map.isEmpty());
        System.out.println("map.remove() : "+map.remove("san"));
        System.out.println("map.get(i) : "+map.get("si"));
        System.out.println("map.containsKey() : "+map.containsKey("si"));
        System.out.println("map.containsValue() : "+map.containsValue("李四"));
        System.out.println("map.replace() : "+map.replace("si","李四2"));
        System.out.println(map);
    }
}
