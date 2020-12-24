package com.test;


import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.*;
import org.springframework.context.annotation.EnableAspectJAutoProxy;
import org.springframework.stereotype.Component;


/**
 * @author shihao
 * @create 2020-07-15 19:10
 */
@Component
@EnableAspectJAutoProxy
@Aspect
public class AspectConfig {


    /**
     * 环绕通知
     */
    @Around("execution(* com.test..*(..))")
    public Object testAround(ProceedingJoinPoint joinPoint) throws Throwable {
        System.out.println("切面_执行业务前打印日志...");
        Object obj = joinPoint.proceed();
        System.out.println("切面_执行业务后打印日志...");
        return obj;
    }


}
