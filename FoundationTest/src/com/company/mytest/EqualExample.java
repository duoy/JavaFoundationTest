package com.company.mytest;

import java.util.HashSet;

public class EqualExample {
    private int x,y,z;

    public EqualExample(int x,int y,int z){
        this.x = x;
        this.y = y;
        this.z = z;
    }

    @Override
    public boolean equals(Object o){
        if(this == o){
            return true;
        }
        if(o == null || getClass()!=o.getClass()){
            return false;
        }
        EqualExample e = (EqualExample)o;
        if(x != e.x)    return false;
        if(y != e.y)    return false;
        return z == e.z;
    }

    @Override
    public int hashCode(){
        int result = 17;
        result = 31*result+x;
        result = 31*result+y;
        result = 31*result+z;
        return result;
    }

    public static void main(String[] args) {
        EqualExample e1 = new EqualExample(1, 1, 1);
        EqualExample e2 = new EqualExample(1, 1, 1);
        System.out.println(e1 == e2);
        System.out.println(e1.equals(e2));
        HashSet<EqualExample> set = new HashSet<>();
        set.add(e1);
        set.add(e2);
        System.out.println(set.size());
    }

}
