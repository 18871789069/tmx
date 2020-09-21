package com.tmx.spring.service.impl;

import com.tmx.spring.dao.UserDao;
import com.tmx.spring.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * Created By Riven on 2020-9-21
 */
@Service
public class UserServiceImpl implements UserService {

    @Autowired
    private UserDao userDao;

    @Override
    public void add() {
        userDao.add("1","111","123","riven", 18);
        System.out.println("新增用户！");
    }

    @Override
    public void del() {
        System.out.println("删除用户！");
    }
}
