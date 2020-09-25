package com.tmx;

import com.tmx.base.service.UserService;
import org.springframework.context.support.ClassPathXmlApplicationContext;

/**
 * Created By Riven on 2020-9-24
 */
public class Test001 {

    public static void main(String[] args) {
        ClassPathXmlApplicationContext applicationContext = new ClassPathXmlApplicationContext("spring.xml");
        UserService userService = (UserService) applicationContext.getBean("userService");
        userService.add();
    }
}
