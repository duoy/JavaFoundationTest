package com.company.mytest;

/**
 * 父类引用指向子类实例时只能访问子类从父类继承的变量和方法（或子类覆盖父类的方法）
 */
interface peopleBehavior{

    void eat();
    default void sleep(){
        System.out.println("sleeping ");
    }
    void method();
}

class Father{
    String str = "string in ather";
    int x = 1;
    void method(){
        System.out.println("method in Father");
    }
}
class Child extends Father implements peopleBehavior{
    String str = "string in Child";
    int x = 2;
    int y;

    @Override
    public void eat() {
        System.out.println("eating");
    }

    public void method(){
        System.out.println("method in Child");
    }
    void newDefin(){
        System.out.println("newDefMethod in Child");
    }
}


public class ExtendsTest {
    public static void main(String[] args){
        Father a = new Father();
        System.out.println(a);
        System.out.println("the variables declared in "+a+" : " +a.str+" "+a.x); //the variables in Father :string in Father   1
        a.method(); //method in Father
        System.out.println("-------------------");
        Father b = new Child();
        System.out.println(b.toString());
        System.out.println("the variables declared in "+b +" ： "+b.str+" "+b.x); //the variables in Child :string in Father   1
        b.method(); //method in Child
        System.out.println("-------------------");
        Child c = new Child();
        System.out.println(c.toString());
        System.out.println("the variables declared in "+c+" ： "+c.str+" "+c.x); //the variables in Child :string in Child   2
        c.method(); //method in C
        c.newDefin();
    }

}
