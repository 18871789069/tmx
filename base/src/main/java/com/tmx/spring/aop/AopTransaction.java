package com.tmx.spring.aop;

import com.tmx.spring.util.TransactionUtil;
import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.transaction.TransactionStatus;
import org.springframework.transaction.interceptor.TransactionAspectSupport;

/**
 * Created By Riven on 2020-9-22
 *
 */
@Component
@Aspect
public class AopTransaction {

    @Autowired
    private TransactionUtil transactionUtil;

    // 异常通知
    @AfterThrowing("execution(* com.tmx.spring.service.impl..*(..))")
    public void afterThrowing() {
        System.out.println("异常通知");
        // 获取当前事务并回滚
        TransactionAspectSupport.currentTransactionStatus().setRollbackOnly();
    }

    @Around("execution(* com.tmx.spring.service.impl..*(..))")
    public void around(ProceedingJoinPoint proceedingJoinPoint) throws Throwable {
        // 调用方法之前执行
        System.out.println("环绕通知 调用方法之前执行 开启事务");
        TransactionStatus transactionStatus = transactionUtil.begin();
        proceedingJoinPoint.proceed();
        // 调用方法之后执行
        transactionUtil.commit(transactionStatus);
        System.out.println("环绕通知 调用方法之后执行");
    }
}
