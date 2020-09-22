package com.tmx;

import com.tmx.spring.dao.ICompanyDao;
import com.tmx.spring.dao.IUserDao;
import com.tmx.spring.dao.impl.CompanyDao;
import com.tmx.spring.dao.impl.UserDao;
import com.tmx.spring.invocation.InvocationHandlerImpl;
import com.tmx.spring.proxy.UserDaoProxy;

import java.lang.reflect.InvocationHandler;
import java.lang.reflect.Proxy;

/**
 * Created By Riven on 2020-9-21
 */
public class TestProxy {

    public static void main(String[] args) {
        // 静态代理方式
        IUserDao userDao = new UserDao();
        UserDaoProxy userDaoProxy = new UserDaoProxy(userDao);
        userDaoProxy.add();

        // 动态代理
        ICompanyDao companyDao = new CompanyDao();
        InvocationHandler invocationHandlerImpl = new InvocationHandlerImpl(companyDao);
        ClassLoader loader = companyDao.getClass().getClassLoader();
        Class<?>[] interfaces = companyDao.getClass().getInterfaces();
        // 主要装载器、一组接口及调用处理动态代理实例
        ICompanyDao newProxyInstance = (ICompanyDao) Proxy.newProxyInstance(loader, interfaces, invocationHandlerImpl);
        newProxyInstance.add();
    }
}
