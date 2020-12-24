package com.demo;

import java.util.ArrayList;
import java.util.List;

/**
 * @author shihao
 * @create 2020-11-02 16:03
 */
public class Test {

    public static void main(String[] args) {
        int i=12;
        Integer j=12;

        String a="abd";
        String b="ce";
        String c=a+b;
        String d="abd"+ "ce";
        StringBuilder stringBuilder=new StringBuilder("abd");
        stringBuilder.append("ce");
        String s = stringBuilder.toString();


        System.out.println(i==j);
        System.out.println(c==d);
        System.out.println(s==d);



        List<?> list=new ArrayList<>();

    }


}
