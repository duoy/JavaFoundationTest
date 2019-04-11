package com.company.mytest;

/**
 * 静态内部类实现单例模式
 * 当Singleton类加载时，静态内部类SingletonHolder没有被加载进内存，只有当调用getUniqueInstance()方法从而触发SingletonHolder.INSTANCE时，
 * SingletonHolder才会被加载，此时初始化INSTACNCE实例，并且JVM能保证INSTANCE制备实例化一次。
 */
public class Singleton {
    //private构造方法，避免使用默认的构造方法创建对象
    private Singleton(){};

    //声明private的静态内部类，只能在该Singleton类中被访问
    private static class SingletonHolder{
        private static final Singleton INSTANCE = new Singleton();
    }
    public static Singleton getUniqueInstance(){
        return SingletonHolder.INSTANCE;
    }
}
