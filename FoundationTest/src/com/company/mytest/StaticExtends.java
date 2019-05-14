package com.company.mytest;

/**
 * 知识点总结：1.父类引用指向子类对象时，只能访问从父类继承的变量/属性和方法（属性被隐藏时依旧访问在父类中定义的属性；方法发生覆盖时访问子类中重写的方法）
 *             2.static变量和static方法只能被继承，static方法不能被覆盖/重写；父类引用访问父类的static变量和static方法
 *             3. 初始化顺序：
 *                  a.父类静态变量、静态代码块
 *                  b.子类静态变量、静态代码块
 *                  c.父类实例变量、普通代码块 /构造代码块
 *                  d.父类构造方法
 *                  e.子类 实例变量、普通代码块
 *                  f.子类构造方法
 *              静态代码块static{} 与 非静态代码块{}（构造代码块）异同：
 *                  相同点：都是在JVM加载类时且在构造方法执行之前，在类中可以定义多个，按定义的顺序执行
 *                  不同点：静态代码块在非静态代码块之前执行
 *                          静态代码块只在第一次new执行一次，之后不再执行；而非静态代码块在每new一次就执行一次
 */

abstract class A{
    public static String staticStr = "A静态属性";
    public String nonStaticStr = "A非静态属性";
    static{
        System.out.println("父类静态代码块");
    }
    {
        System.out.println("父类非静态代码块");
    }
    public static void staticMethod(){
        System.out.println("A静态方法");
    }
    public void nonStaticMethod(){
        System.out.println("A非静态方法");
    }
    abstract void method();
}

class B extends A{
    static{
        staticStr = "在静态代码块中给 staticStr 赋新值 ";
    }
    public int x;   //子类自己定义的属性
    public static String staticStr = "B改写后的静态属性";
    public String nonStaticStr = "B改写后的非静态属性";

    static{
        System.out.println("子类静态代码块");
    }
    {
        System.out.println("子类非静态代码块");
    }
    public static void staticMethod(){
        System.out.println("B改写后的静态方法");
    }
    @Override
    public void nonStaticMethod(){
        //super.nonStaticMethod();
        System.out.println("子类重写父类的nonStaticMethod()");
    }
    public void method(){
        System.out.println("在子类中实现父类定义的抽象方法");
    }

    public static void test(){
        System.out.print("静态方法中的内容! ");
        {
            System.out.print("静态方法中的代码块！");
        }
    }
}


public class StaticExtends {
    public static void main(String[] args) {
        //父类引用指向子类对象,只能调用从父类继承的属性和方法（不能访问B中定义的x);父类引用调用父类中的static属性和方法
/*
        A b1 = new B();
        System.out.println(b1.nonStaticStr); //A非静态属性
        System.out.println(b1.staticStr); //A静态属性
        b1.staticMethod();//父类的静态方法
        b1.nonStaticMethod();//调用子类重写的方法
        System.out.println("-------------------------------");

        B b = new B();
        System.out.println(b.x);
        System.out.println(b.nonStaticStr);
        System.out.println(b.staticStr);
        b.staticMethod();   //子类引用调用子类重写的静态方法
        b.nonStaticMethod();
        System.out.println("-------------------------------");


        B b2 = (B)b1;
        System.out.println(b2.x);
        System.out.println(b2.nonStaticStr);
        System.out.println(b2.staticStr);
        b2.staticMethod();   //子类引用调用子类重写的静态方法
        b2.nonStaticMethod();
        System.out.println("-------------------------------");
        //B b = new B();
        B.test();
        System.out.println(B.staticStr);
        System.out.println();
        System.out.println("==============================");*/

        B bb=null;
        B.staticMethod();
        //System.out.println(B.staticStr);

    }
}
