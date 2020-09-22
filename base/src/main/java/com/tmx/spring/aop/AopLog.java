//package com.tmx.spring.aop;
//
//import org.aspectj.lang.ProceedingJoinPoint;
//import org.aspectj.lang.annotation.*;
//import org.springframework.stereotype.Component;
//
///**
// * Created By Riven on 2020-9-21
// * AOP切面实现
// * 切面类
// */
//@Component
//@Aspect
//public class AopLog {
//
//    @Before("execution(* com.tmx.spring.service.impl..*(..))")
//    public void before() {
//        System.out.println("前置通知");
//    }
//
//    @After("execution(* com.tmx.spring.service.impl..*(..))")
//    public void after() {
//        System.out.println("后置通知");
//    }
//
//     // 运行通知
//     @AfterReturning("execution(* com.tmx.spring.service.impl..*(..))")
//     public void returning() {
//        System.out.println("运行通知");
//     }
//
//     // 异常通知
//     @AfterThrowing("execution(* com.tmx.spring.service.impl..*(..))")
//     public void afterThrowing() {
//        System.out.println("异常通知");
//     }
//
//    @Around("execution(* com.tmx.spring.service.impl..*(..))")
//    public void around(ProceedingJoinPoint proceedingJoinPoint) throws Throwable {
//        // 调用方法之前执行
//        System.out.println("环绕通知 调用方法之前执行");
//        proceedingJoinPoint.proceed();
//        // 调用方法之后执行
//        System.out.println("环绕通知 调用方法之后执行");
//    }
//
//}
