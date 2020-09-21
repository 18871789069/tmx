package com.tmx.spring.invocation;

import java.lang.reflect.InvocationHandler;
import java.lang.reflect.Method;

/**
 * Created By Riven on 2020-9-21
 */
public class InvocationHandlerImpl implements InvocationHandler {

    private Object target;

    public InvocationHandlerImpl(Object target) {
        this.target = target;
    }
    @Override
    public Object invoke(Object proxy, Method method, Object[] args) throws Throwable {
        Object result = null;
        System.out.println("调用开始处理");
        result = method.invoke(target, args);
        System.out.println("调用结束处理");
        return result;
    }

}
