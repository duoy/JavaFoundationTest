package com.company.mytest;

enum AccountType{
    SAVING , FIXED , CURRENT;
    AccountType(){
        System.out.println("It is a account type");
    }
}

public class EunmTest {
    public static void main(String[] args){
        System.out.println(AccountType.FIXED);
        for(AccountType temp:AccountType.values()){
            System.out.println(temp);
        }
        AccountType current = AccountType.valueOf("CURRENT");
        System.out.println(current);

    }
}
