package com.tmx;

import com.tmx.base.service.UserService;
import com.tmx.spring.ClassPathXmlApplicationContext;

/**
 * Created By Riven on 2020-9-25
 */
public class Test003 {

    public static void main(String[] args) throws Exception {
        ClassPathXmlApplicationContext classPathXmlApplicationContext = new ClassPathXmlApplicationContext("spring.xml");
        UserService userService = (UserService) classPathXmlApplicationContext.getBean("userService");
        userService.add();
    }
}
