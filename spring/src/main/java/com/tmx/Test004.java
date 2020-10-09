package com.tmx;

import com.tmx.base.service.impl.UserServiceImpl;
import com.tmx.ioc.springIOC.ClassPathApplicationContext;

/**
 * Created By Riven on 2020-10-9
 */
public class Test004 {

    public static void main(String[] args) throws Exception {
        ClassPathApplicationContext classPathApplicationContext = new ClassPathApplicationContext("com.tmx.base.service.impl");
        UserServiceImpl userServiceImpl = (UserServiceImpl) classPathApplicationContext.getBean("userServiceImpl");
        userServiceImpl.add();
    }
}
