package com.demo;

import juc.EnumTest;

/**
 * @author shihao
 * @create 2020-11-06 11:28
 */
public class Singtelon {

    private static volatile Singtelon singtelon=null;

    private Singtelon(){

    }

    private static  Singtelon getInstance(){
        if(singtelon==null){

            synchronized (Singtelon.class){
                if(singtelon==null){
                    singtelon=new Singtelon();
                }
            }
        }

        return singtelon;
    }


    public static void main(String[] args) {

        for (int i = 0; i <10 ; i++) {
            new Thread(() ->{
                System.out.println(Thread.currentThread().getName()+"==="+Singtelon.getInstance().hashCode());
            },"Singtelon").start();
        }


    }
}
