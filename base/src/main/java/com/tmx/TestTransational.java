package com.tmx;

import com.tmx.spring.service.UserService;
import org.springframework.context.support.ClassPathXmlApplicationContext;

/**
 * Created By Riven on 2020-9-22
 */
public class TestTransational {

    public static void main(String[] args) {
        ClassPathXmlApplicationContext applicationContext = new ClassPathXmlApplicationContext("spring.xml");
        UserService serviceImpl = (UserService) applicationContext.getBean("userServiceImpl");
        serviceImpl.add();
        serviceImpl.del();
    }
}
