package com.test;

import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

/**
 * @author shihao
 * @create 2020-07-15 19:26
 */
public class TestAopMain {

    public static void main(String[] args) {
        AnnotationConfigApplicationContext context=new AnnotationConfigApplicationContext(BeansConfig.class);
        AopTestService aopTestService = context.getBean(AopTestService.class);
        aopTestService.test();
        context.destroy();

    }

//    public static void main(String[] args){
//        //@SuppressWarnings("resource")
//        //如果是web项目，则使用以下代码加载配置文件，如果是一般的Java项目，则使用注释的方式
//        ApplicationContext appCtx = new ClassPathXmlApplicationContext("applicationContext.xml");
//        //ApplicationContext appCtx = new FileSystemXmlApplicationContext("conf/application.xml");
//        HelloWorld hw1 = (HelloWorld) appCtx.getBean("proxy");
//        HelloWorld hw2 = (HelloWorld) appCtx.getBean("proxy2");
//        hw1.printHelloWorld();
//        System.out.println();
//        hw1.doPrint();
//        System.out.println();
//
//        hw2.printHelloWorld();
//        System.out.println();
//        hw2.doPrint();
//    }
}
