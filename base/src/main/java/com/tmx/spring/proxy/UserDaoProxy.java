package com.tmx.spring.proxy;

import com.tmx.spring.dao.IUserDao;

/**
 * Created By Riven on 2020-9-21
 */
public class UserDaoProxy implements IUserDao {

    private IUserDao userDao;

    public UserDaoProxy(IUserDao userDao) {
        this.userDao = userDao;
    };

    @Override
    public void add() {
        System.out.println("开启事物...");
        userDao.add();
        System.out.println("关闭事物...");
    }


}
