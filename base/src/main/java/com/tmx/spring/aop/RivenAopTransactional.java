package com.tmx.spring.aop;

import com.tmx.annotation.RivenTransactional;
import com.tmx.spring.util.TransactionUtil;
import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.AfterThrowing;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Pointcut;
import org.aspectj.lang.reflect.MethodSignature;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.transaction.TransactionStatus;

import java.lang.reflect.Method;

/**
 * Created By Riven on 2020-9-23
 */
@Component
@Aspect
public class RivenAopTransactional {

    @Autowired
    private TransactionUtil transactionUtil;

    /**
     * 异常处理
     */
    @AfterThrowing("execution(* com.tmx.spring.service.impl.*.*(..))")
    public void afterThrowing() {
        transactionUtil.rollback();
    }

    /**
     * 环绕处理
     * @param proceedingJoinPoint
     * @throws Throwable
     */
    @Around("execution(* com.tmx.spring.service.impl.*.*(..))")
    public void around(ProceedingJoinPoint proceedingJoinPoint) throws Throwable {
        RivenTransactional annotation = getRivenTransactional(proceedingJoinPoint);
        // 有注解开启事务
        TransactionStatus transactionStatus = begin(annotation);
        // 代理执行方法
        proceedingJoinPoint.proceed();
        commit(transactionStatus);
    }

    public void commit(TransactionStatus transactionStatus) {
        if (transactionStatus != null) {
            transactionUtil.commit(transactionStatus);
        }
    }

    public TransactionStatus begin(RivenTransactional annotation) {
        if (annotation == null) {
            return null;
        }
        return transactionUtil.begin();
    }

    /**
     * 获取方法上的注解
     * @param proceedingJoinPoint
     * @return
     */
    public RivenTransactional getRivenTransactional(ProceedingJoinPoint proceedingJoinPoint) throws NoSuchMethodException {
        // 获取方法
        String methodName = proceedingJoinPoint.getSignature().getName();
        // 获取当前目标类
        Class<?> aClass = proceedingJoinPoint.getTarget().getClass();
        // 获取目标类型
        Class[] parameterTypes = ((MethodSignature) proceedingJoinPoint.getSignature()).getParameterTypes();

        Method method = aClass.getMethod(methodName, parameterTypes);
        RivenTransactional declaredAnnotation = method.getDeclaredAnnotation(RivenTransactional.class);
        return declaredAnnotation;
    }
}
